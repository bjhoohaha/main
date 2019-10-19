package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.AddressBookModel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.main.commons.core.GuiSettings;
import seedu.main.commons.core.LogsCenter;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;
import seedu.main.logic.parser.exceptions.ParseException;
import seedu.main.storage.Storage;

/**
 * The main AddressBookLogicManager of the app.
 */
public class AddressBookLogicManager implements AddressBookLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not saveAddressBook data to file: ";
    private final Logger logger = LogsCenter.getLogger(AddressBookLogicManager.class);

    private final AddressBookModel addressBookModel;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    public AddressBookLogicManager(AddressBookModel addressBookModel, Storage storage) {
        this.addressBookModel = addressBookModel;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(addressBookModel);

        try {
            storage.saveAddressBook(addressBookModel.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBookModel.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return addressBookModel.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return addressBookModel.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return addressBookModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        addressBookModel.setGuiSettings(guiSettings);
    }
}
