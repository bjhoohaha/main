package seedu.main.logic;

import seedu.address.logic.AddressBookLogic;
import seedu.main.storage.Storage;

/**
 * The main AddressBookLogicManager of the app.
 */
public interface Logic {

    public Storage getStorage();

    public AddressBookLogic getAddressBookLogic();
}
