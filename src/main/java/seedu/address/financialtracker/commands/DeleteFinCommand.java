package seedu.address.financialtracker.commands;

import seedu.address.financialtracker.Model.Model;
import seedu.main.commons.core.index.Index;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;

/**
 * delete a financial expense, command for Financial Tracker.
 */
public class DeleteFinCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_FAIL = "Unknown error, your expenses are not deleted.";
    public static final String MESSAGE_SUCCESS = "Your expense has been deleted";
    public static final String MESSAGE_USAGE = "";
    private final Index index;

    public DeleteFinCommand(Index index) {
        this.index = index;
    }

    public CommandResult execute(Model model) throws CommandException {
        model.deleteExpense(index.getOneBased());
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
