package seedu.address.financialtracker.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.financialtracker.Model.Expense.Expense;
import seedu.address.ui.UiPart;
import seedu.main.commons.core.LogsCenter;

/**
 * Panel containing the list of expenses.
 */
public class ExpensePanel extends UiPart<Region> {
    private static final String FXML = "ExpensePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpensePanel.class);

    @FXML
    private ListView<Expense> expenseListView;

    public ExpensePanel(ObservableList<Expense> expenseList) {
        super(FXML);
        expenseListView.setItems(expenseList);
        expenseListView.setCellFactory(listView -> new ExpenseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ExpenseListViewCell extends ListCell<Expense> {
        @Override
        protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);

            if (empty || expense == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpenseCard(expense, getIndex() + 1).getRoot());
            }
        }
    }
}
