package seedu.main;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.main.commons.core.Config;
import seedu.main.commons.core.LogsCenter;
import seedu.main.commons.core.Version;
import seedu.main.logic.Logic;
import seedu.main.storage.Storage;
import seedu.main.ui.Ui;

/**
 * Runs the application.
 */
public abstract class MainApp<T> extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    public static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    //    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        super.init();
    }

    //    /**
    //     * Returns a {@code ModelManager} with the data from {@code addressBookStorage}'s address book and
    //     * {@code userPrefs}. <br> The data from the sample address book will be used instead if
    //     * {@code addressBookStorage}'s address book is not found, or an empty address book will be used instead if
    //     * errors occur when reading {@code addressBookStorage}'s address book.
    //     */
    //    protected abstract T initModelManager(AddressBookStorage addressBookStorage, ReadOnlyAddressBookUserPrefs
    //    userPrefs);

    protected void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected abstract Config initConfig(Path configFilePath);

    //    /**
    //     * Returns a {@code AddressBookUserPrefs} using the file at {@code addressBookStorage}'s user prefs file path,
    //     * or a new {@code AddressBookUserPrefs} with default configuration if errors occur when
    //     * reading from the file.
    //     */
    //    protected abstract AddressBookUserPrefs initPrefs(UserPrefsStorage storage);

    @Override
    public abstract void start(Stage primaryStage);

    @Override
    public abstract void stop();
}
