package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.AddressBookUserPrefs;
import seedu.address.model.ReadOnlyAddressBookUserPrefs;
import seedu.main.commons.exceptions.DataConversionException;
/**
 * Represents a addressBookStorage for {@link AddressBookUserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the AddressBookUserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns AddressBookUserPrefs data from addressBookStorage.
     *   Returns {@code Optional.empty()} if addressBookStorage file is not found.
     * @throws DataConversionException if the data in addressBookStorage is not in the expected format.
     * @throws IOException if there was any problem when reading from the addressBookStorage.
     */
    Optional<AddressBookUserPrefs> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyAddressBookUserPrefs} to the addressBookStorage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyAddressBookUserPrefs userPrefs) throws IOException;

}
