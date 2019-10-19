package seedu.main.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.main.commons.core.LogsCenter;
import seedu.main.commons.exceptions.DataConversionException;
import seedu.main.commons.exceptions.IllegalValueException;
import seedu.main.commons.util.FileUtil;
import seedu.main.commons.util.JsonUtil;
import seedu.main.model.ReadOnlySample;

/**
 * A class to access Sample data stored as a json file on the hard disk.
 */
public class JsonSampleStorage implements SampleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSampleStorage.class);

    private Path filePath;

    public JsonSampleStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getSampleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySample> readSample() throws DataConversionException {
        return readSample(filePath);
    }

    /**
     * Similar to {@link #readSample()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySample> readSample(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSample> jsonSample = JsonUtil.readJsonFile(
                filePath, JsonSerializableSample.class);
        if (!jsonSample.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSample.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSample(ReadOnlySample sample) throws IOException {
        saveSample(sample, filePath);
    }

    /**
     * Similar to {@link #saveSample(ReadOnlySample)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSample(ReadOnlySample sample, Path filePath) throws IOException {
        requireNonNull(sample);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSample(sample), filePath);
    }

}
