package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressBookUserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        AddressBookUserPrefs userPref = new AddressBookUserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        AddressBookUserPrefs addressBookUserPrefs = new AddressBookUserPrefs();
        assertThrows(NullPointerException.class, () -> addressBookUserPrefs.setAddressBookFilePath(null));
    }

}
