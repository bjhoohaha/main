package seedu.address.itinerary.model.Event;

/**
 * A short description complementing the event in the itinerary.
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description of an event should not contain any special characters and should be "
                + "less than 100 characters.";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*";
    public final String value;

    public Description(String desc) {
        this.value = desc;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
