package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.StringUtil;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;

/**
 * Represents the in-memory addressBookModel of the address book data.
 */
public class UserPrefsModelManager implements UserPrefsModel {
    private static final Logger logger = LogsCenter.getLogger(UserPrefsModelManager.class);

    private UserPrefs userPrefs;

    private UserPrefsStorage userPrefsStorage;

    public UserPrefsModelManager() {
        this.userPrefs = new UserPrefs();
    }

    public UserPrefsModelManager(ReadOnlyUserPrefs userPrefs) {
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public UserPrefsModelManager(Path userPrefsFilePath) {
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(userPrefsFilePath);
        this.userPrefs = initPrefs(userPrefsStorage);
    }

    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof UserPrefsModelManager)) {
            return false;
        }

        // state check
        UserPrefsModelManager other = (UserPrefsModelManager) obj;
        return userPrefs.equals(other.userPrefs);
    }
}
