package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBookModel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.main.commons.core.GuiSettings;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;
import seedu.main.logic.parser.exceptions.ParseException;

/**
 * API of the AddressBookLogic component
 */
public interface AddressBookLogic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see AddressBookModel#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
