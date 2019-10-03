package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.stage.Stage;
import seedu.address.logic.AddressBookLogic;
import seedu.address.logic.AddressBookLogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.AddressBookUserPrefs;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAddressBookUserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.AddressBookStorageManager;
import seedu.address.storage.DataStorage;
import seedu.address.storage.JsonDataStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.AddressBookUiManager;
import seedu.main.MainApp;
import seedu.main.commons.core.Config;
import seedu.main.commons.core.Version;
import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.commons.util.ConfigUtil;
import seedu.main.commons.util.StringUtil;


/**
 * Runs the application.
 */
public class MainAddressBookApp extends MainApp<Model> {

    public static final Version VERSION = new Version(0, 6, 0, true);

    protected Model model;

    @Override
    public void init() throws Exception {
        LOGGER.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        super.config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        ReadOnlyAddressBookUserPrefs addressBookUserPrefs = initPrefs(userPrefsStorage);
        DataStorage dataStorage = new JsonDataStorage(addressBookUserPrefs.getAddressBookFilePath());
        super.storage = new AddressBookStorageManager(dataStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager((AddressBookStorage) super.storage, addressBookUserPrefs);

        super.logic = new AddressBookLogicManager(model, (AddressBookStorage) super.storage);

        super.ui = new AddressBookUiManager((AddressBookLogic) super.logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code addressBookStorage}'s address book and {@code userPrefs}
     * . <br> The data from the sample address book will be used instead if {@code addressBookStorage}'s address book
     * is not found, or an empty address book will be used instead if errors occur when reading
     * {@code addressBookStorage}'s address book.
     */
    protected Model initModelManager(AddressBookStorage addressBookStorage, ReadOnlyAddressBookUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = addressBookStorage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                LOGGER.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            LOGGER.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }

        return new ModelManager(initialData, userPrefs);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    @Override
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            LOGGER.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        LOGGER.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            LOGGER.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                           + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            LOGGER.warning("Failed to saveAddressBook config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code AddressBookUserPrefs} using the file at {@code addressBookStorage}'s user prefs file path,
     * or a new {@code AddressBookUserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected AddressBookUserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        LOGGER.info("Using prefs file : " + prefsFilePath);

        AddressBookUserPrefs initializedPrefs;
        try {
            Optional<AddressBookUserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new AddressBookUserPrefs());
        } catch (DataConversionException e) {
            LOGGER.warning("AddressBookUserPrefs file at " + prefsFilePath + " is not in the correct format. "
                           + "Using default user prefs");
            initializedPrefs = new AddressBookUserPrefs();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new AddressBookUserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            LOGGER.warning("Failed to saveAddressBook config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting AddressBook " + MainAddressBookApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        LOGGER.info("============================ [ Stopping Address Book ] =============================");
        try {
            AddressBookStorage addressBookStorage = (AddressBookStorage) super.storage;
            addressBookStorage.saveUserPrefs(model.getAddressBookUserPrefs());
        } catch (IOException e) {
            LOGGER.severe("Failed to saveAddressBook preferences " + StringUtil.getDetails(e));
        }
    }

}
