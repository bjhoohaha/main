package seedu.address.itinerary.commands;

import javafx.fxml.FXML;
import seedu.address.itinerary.model.Model;
import seedu.address.itinerary.ui.SummaryCommandWindow;
import seedu.main.logic.commands.CommandResult;
import seedu.main.logic.commands.exceptions.CommandException;

public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";
    public static final String MESSAGE_SUCCESS = "Currently viewing the itinerary summary window.";
    private SummaryCommandWindow summaryCommandWindow = new SummaryCommandWindow();

    /**
     * Opens the summary window or focuses on it if it's already opened.
     */
    @FXML
    public void handleSummary(int number) {
        if (!summaryCommandWindow.isShowing()) {
            summaryCommandWindow.show();
        } else {
            summaryCommandWindow.focus();
        }
    }

    public CommandResult execute(Model model) throws CommandException {
        handleSummary(model.getFilteredEventList().size());
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
