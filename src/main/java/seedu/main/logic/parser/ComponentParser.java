package seedu.main.logic.parser;

import seedu.main.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public interface ComponentParser<T> {

    //    /**
    //     * Used for initial separation of command word and args.
    //     */
    //    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public T parseCommand(String userInput) throws ParseException;

}
