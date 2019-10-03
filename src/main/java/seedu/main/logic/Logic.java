package seedu.main.logic;

import seedu.main.logic.commands.exceptions.CommandException;
import seedu.main.logic.parser.exceptions.ParseException;

/**
 * API of the AddressBookLogic component
 */
public interface Logic<T> {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    T execute(String commandText) throws CommandException, ParseException;
}
