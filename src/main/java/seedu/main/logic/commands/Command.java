package seedu.main.logic.commands;

import seedu.main.logic.commands.exceptions.CommandException;

/**
 * Represents a command with hidden internal addressBookLogic and the ability to be executed.
 */
public interface Command<M> {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(M model) throws CommandException;

}
