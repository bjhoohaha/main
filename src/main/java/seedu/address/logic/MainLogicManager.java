package seedu.address.logic;

import java.util.logging.Logger;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MainParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.UserPrefs;
import seedu.address.model.UserPrefsModel;
import seedu.address.model.UserPrefsModelManager;

/**
 * Logic Manager for the main page.
 */
public class MainLogicManager implements MainLogic {

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final UserPrefsModel userPrefsModel;

    private final MainParser mainParser;

    public MainLogicManager(UserPrefs userPrefs) {
        this.userPrefsModel = new UserPrefsModelManager(userPrefs);
        this.mainParser = new MainParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command<UserPrefsModel> command = mainParser.parseCommand(commandText);
        commandResult = command.execute(userPrefsModel);

        return commandResult;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefsModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        userPrefsModel.setGuiSettings(guiSettings);
    }
}
