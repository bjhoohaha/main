package seedu.address.itinerary.commands;

import seedu.address.itinerary.model.Model;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;

public abstract class Command {
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code AddressBookModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;
}
