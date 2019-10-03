package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.AddressBookUserPrefs;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAddressBookUserPrefs;
import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.storage.Storage;

/**
 * API of the AddressBookStorage component
 */
public interface AddressBookStorage extends DataStorage, UserPrefsStorage, Storage {

    @Override
    Optional<AddressBookUserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyAddressBookUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
