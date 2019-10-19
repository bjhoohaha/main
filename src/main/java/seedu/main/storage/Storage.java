package seedu.main.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.model.ReadOnlySample;
import seedu.main.model.ReadOnlyUserPrefs;
import seedu.main.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, SampleStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getSampleFilePath();

    @Override
    Optional<ReadOnlySample> readSample() throws DataConversionException, IOException;

    @Override
    void saveSample(ReadOnlySample readOnlySample) throws IOException;

}
