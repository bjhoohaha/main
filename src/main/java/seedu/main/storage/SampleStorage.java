package seedu.main.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.model.ReadOnlySample;

public interface SampleStorage {
    /**
     * Returns the file path of the sample file.
     */
    Path getSampleFilePath();

    /**
     * Returns sample data as a {@code Sample}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySample> readSample() throws DataConversionException, IOException;

    /**
     * @see #getSampleFilePath()
     */
    Optional<ReadOnlySample> readSample(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySample} to the storage.
     *
     * @param sample cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSample(ReadOnlySample sample) throws IOException;

    /**
     * @see #saveSample(ReadOnlySample)
     */
    void saveSample(ReadOnlySample sample, Path filePath) throws IOException;
}
