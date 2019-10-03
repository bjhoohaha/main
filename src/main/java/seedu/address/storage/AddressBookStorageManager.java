package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.model.AddressBookUserPrefs;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAddressBookUserPrefs;
import seedu.main.commons.core.LogsCenter;
import seedu.main.commons.exceptions.DataConversionException;

/**
 * Manages addressBookStorage of AddressBook data in local addressBookStorage.
 */
public class AddressBookStorageManager implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(AddressBookStorageManager.class);
    private DataStorage dataStorage;
    private UserPrefsStorage userPrefsStorage;


    public AddressBookStorageManager(DataStorage dataStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.dataStorage = dataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ AddressBookUserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<AddressBookUserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyAddressBookUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return dataStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(dataStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to readAdressBook data from file: " + filePath);
        return dataStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, dataStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dataStorage.saveAddressBook(addressBook, filePath);
    }

}
