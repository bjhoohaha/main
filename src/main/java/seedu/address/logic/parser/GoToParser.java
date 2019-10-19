package seedu.address.logic.parser;

import static seedu.main.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GoToCommand;
import seedu.address.ui.PageType;
import seedu.main.logic.parser.exceptions.ParseException;

public class GoToParser implements Parser<GoToCommand> {

    public GoToCommand parse(String args) throws ParseException {
        try {
            PageType pageType = ParserUtil.parsePageType(args);
            return new GoToCommand(pageType);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE), pe);
        }
    }
}
