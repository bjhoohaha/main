package seedu.address.model;

import java.nio.file.Path;

import seedu.main.commons.core.GuiSettings;
import seedu.main.model.ReadOnlyUserPrefs;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyAddressBookUserPrefs extends ReadOnlyUserPrefs {

    @Override
    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();
}
