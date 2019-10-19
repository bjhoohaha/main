package seedu.main.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.main.commons.core.LogsCenter;
import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.model.ReadOnlySample;
import seedu.main.model.ReadOnlyUserPrefs;
import seedu.main.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    // your sample storage
    private SampleStorage sampleStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AddressBookStorage addressBookStorage, SampleStorage sampleStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.sampleStorage = sampleStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to readAddressBook data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ Sample methods ==============================

    @Override
    public Path getSampleFilePath() {
        return sampleStorage.getSampleFilePath();
    }

    @Override
    public Optional<ReadOnlySample> readSample() throws DataConversionException, IOException {
        return sampleStorage.readSample();
    }

    @Override
    public Optional<ReadOnlySample> readSample(Path filePath) throws DataConversionException, IOException {
        return sampleStorage.readSample(filePath);
    }

    @Override
    public void saveSample(ReadOnlySample readOnlySample) throws IOException {
        sampleStorage.saveSample(readOnlySample);
    }

    @Override
    public void saveSample(ReadOnlySample readOnlySample, Path filePath) throws IOException {
        sampleStorage.saveSample(readOnlySample, filePath);
    }
}
