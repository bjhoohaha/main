package seedu.address.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import seedu.address.address.logic.parser.AddressBookParser;
import seedu.address.address.model.AddressBook;
import seedu.address.address.model.AddressBookModel;
import seedu.address.address.model.AddressBookModelManager;
import seedu.address.address.model.ReadOnlyAddressBook;
import seedu.address.address.model.person.Person;
import seedu.address.address.model.util.AddressBookStatistics;
import seedu.address.address.model.util.SampleDataUtil;
import seedu.address.address.storage.AddressBookStorage;
import seedu.address.address.storage.JsonAddressBookStorage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * The main AddressBookLogicManager of the app.
 */
public class AddressBookLogicManager implements AddressBookLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(AddressBookLogicManager.class);

    private final AddressBookModel addressBookModel;
    private final AddressBookStorage addressBookStorage;
    private final AddressBookParser addressBookParser;

    public AddressBookLogicManager(UserPrefs userPrefs) {
        this.addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        this.addressBookModel = initModelManager(addressBookStorage, userPrefs);
        this.addressBookParser = new AddressBookParser();
    }

    /**
     * Returns a {@code AddressBookModelManager} with the data from {@code storage}'s address book and {@code userPrefs}
     * .<br> The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private AddressBookModelManager initModelManager(AddressBookStorage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }
        return new AddressBookModelManager(initialData, userPrefs);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(addressBookModel);

        try {
            addressBookStorage.saveAddressBook(addressBookModel.getAddressBook());
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
    public AddressBookStatistics getStatistics() {
        return new AddressBookStatisticsManager();
    }

    /**
     * Local class for {@link AddressBookStatistics}
     */
    private class AddressBookStatisticsManager implements AddressBookStatistics {
        @Override
        public int getTotalPersons() {
            return addressBookModel.getTotalPersons();
        }

        @Override
        public XYChart.Series<Number, String> getAddressChartData() {
            return addressBookModel.getAddressChartData();
        }
    }
}
