package seedu.main.model;

import java.util.logging.Logger;

import seedu.address.model.AddressBookModel;
import seedu.main.commons.core.LogsCenter;

/**
 * Represents the in-memory addressBookModel of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    AddressBookModel addressBookModel;

    public ModelManager(AddressBookModel addressBookModel) {
        this.addressBookModel = addressBookModel;

    }

    public AddressBookModel getAddressBookModel() {
        return addressBookModel;
    }
}
