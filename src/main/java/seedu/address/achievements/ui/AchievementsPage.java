package seedu.address.achievements.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.achievements.logic.parser.AchievementsParser;
import seedu.address.ui.CommandBox;
import seedu.address.ui.Page;
import seedu.address.ui.PageType;
import seedu.address.ui.ResultDisplay;
import seedu.address.ui.UiPart;

public class AchievementsPage extends UiPart<Region> implements Page {
    private final Logger logger = LogsCenter.getLogger(getClass());

    private final static PageType pagetype = PageType.ACHIEVEMENTS;

    private Logic logic;

    private static final String FXML = "Achievements.fxml";
    @FXML
    Scene achievementsScene;

    @FXML
    ImageView title;

    @FXML
    BorderPane achievementsPane;

    private ResultDisplay resultDisplay;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    public AchievementsPage() {
        super(FXML, new BorderPane());
        achievementsScene = new Scene(achievementsPane);
        title.setImage(new Image(this.getClass().getResourceAsStream("/images/achievements.png")));
        fillInnerParts();
    }

    void fillInnerParts() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = new AchievementsParser().parseCommand(commandText).execute(null);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
            }

            if (commandResult.isExit()) {
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }


    @Override
    public Scene getScene() {
        return achievementsScene;
    }

    @Override
    public PageType getPageType() {
        return pagetype;
    }
}
