package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.main.model.ReadOnlyComponent;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends ReadOnlyComponent {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
