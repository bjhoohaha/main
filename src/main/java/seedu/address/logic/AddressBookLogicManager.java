package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddressBookCommand;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.AddressBookStorage;
import seedu.main.commons.core.GuiSettings;
import seedu.main.commons.core.LogsCenter;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;
import seedu.main.logic.parser.exceptions.ParseException;

/**
 * The main AddressBookLogicManager of the app.
 */
public class AddressBookLogicManager implements AddressBookLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not saveAddressBook data to file: ";
    private final Logger logger = LogsCenter.getLogger(AddressBookLogicManager.class);

    private final Model model;
    private final AddressBookStorage addressBookStorage;
    private final AddressBookParser addressBookParser;

    public AddressBookLogicManager(Model model, AddressBookStorage addressBookStorage) {
        this.model = model;
        this.addressBookStorage = addressBookStorage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        AddressBookCommand command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            addressBookStorage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
