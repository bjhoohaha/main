package seedu.main.logic;

import java.util.logging.Logger;

import seedu.address.logic.AddressBookLogic;
import seedu.address.logic.AddressBookLogicManager;
import seedu.main.commons.core.LogsCenter;
import seedu.main.model.Model;
import seedu.main.storage.Storage;

/**
 * The main AddressBookLogicManager of the app.
 */
public class LogicManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private AddressBookLogic addressBookLogic;
    private Storage storage;

    public LogicManager(Model model, Storage storage) {
        this.addressBookLogic = new AddressBookLogicManager(model.getAddressBookModel(), storage);
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    public AddressBookLogic getAddressBookLogic() {
        return addressBookLogic;
    }
}
