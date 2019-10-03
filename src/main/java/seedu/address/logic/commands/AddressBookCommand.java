package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.main.logic.commands.Command;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;

/**
 * Address Book Commands implements the interface {@link Command}.
 */
public abstract class AddressBookCommand implements Command<Model> {
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
