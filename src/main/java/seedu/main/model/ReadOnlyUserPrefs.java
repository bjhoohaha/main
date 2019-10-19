package seedu.main.model;

import java.nio.file.Path;

import seedu.main.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getSampleFilePath();
}
