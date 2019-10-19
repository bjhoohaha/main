package seedu.address.financialtracker.commands;

import seedu.address.financialtracker.Model.Model;
import seedu.main.logic.commands.CommandResult;

/**
 * List out a summary of your current expenses.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_FAIL = "Unknown error, your expenses are not deleted.";

    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_FAIL, false, false);
    }
}
