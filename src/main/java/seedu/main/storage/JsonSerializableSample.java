package seedu.main.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.main.commons.exceptions.IllegalValueException;
import seedu.main.model.ReadOnlySample;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "sample")
class JsonSerializableSample {

    public static final String MESSAGE_DUPLICATE_SAMPLE = "Sample list contains duplicate sample(s).";

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSample() {
        //parameters : @JsonProperty("sample") List<JsonAdaptedPerson> persons
        //        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlySample} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableSample(ReadOnlySample source) {
    }

    /**
     * Converts this sample into the addressBookModel's {@code Sample} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ReadOnlySample toModelType() throws IllegalValueException {
        return null;
    }

}
