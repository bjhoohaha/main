package seedu.address.financialtracker.parser;

import static seedu.main.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.financialtracker.commands.DeleteFinCommand;
import seedu.main.commons.core.index.Index;
import seedu.main.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteFinCommandParser implements Parser<DeleteFinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFinCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteFinCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFinCommand.MESSAGE_USAGE), pe);
        }
    }

}
