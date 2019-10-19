//package seedu.address;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//import javafx.application.Application;
//import javafx.stage.Stage;
//import seedu.address.logic.AddressBookLogicManager;
//import seedu.address.model.AddressBookModelManager;
//import seedu.main.AppParameters;
//import seedu.main.commons.core.Config;
//import seedu.main.commons.core.LogsCenter;
//import seedu.main.commons.core.Version;
//import seedu.main.commons.exceptions.DataConversionException;
//import seedu.main.commons.util.ConfigUtil;
//import seedu.main.commons.util.StringUtil;
//import seedu.address.logic.AddressBookLogic;
//import seedu.address.model.AddressBook;
//import seedu.address.model.AddressBookModel;
//import seedu.address.model.ReadOnlyAddressBook;
//import seedu.main.model.ReadOnlyUserPrefs;
//import seedu.main.model.UserPrefs;
//import seedu.address.model.util.SampleDataUtil;
//import seedu.address.storage.AddressBookStorage;
//import seedu.address.storage.JsonAddressBookStorage;
//import seedu.main.storage.JsonUserPrefsStorage;
//import seedu.main.storage.Storage;
//import seedu.main.storage.StorageManager;
//import seedu.main.storage.UserPrefsStorage;
//import seedu.main.ui.Ui;
//import seedu.address.ui.UiManager;
//
///**
// * Runs the application.
// */
//public class AddressBookApp extends Application {
//
//    public static final Version VERSION = new Version(0, 6, 0, true);
//
//    private static final Logger logger = LogsCenter.getLogger(AddressBookApp.class);
//
//    protected Ui ui;
//    protected AddressBookLogic addressBookLogic;
//    protected Storage storage;
//    protected AddressBookModel addressBookModel;
//    protected Config config;
//
//    //Sets up all the components (shared)
//
//    @Override
//    public void init() throws Exception {
//        logger.info("=============================[ Initializing AddressBook ]===========================");
//        AppParameters appParameters = AppParameters.parse(getParameters());
//        config = initConfig(appParameters.getConfigPath());
//        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
//        UserPrefs userPrefs = initPrefs(userPrefsStorage);
//        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
//        storage = new StorageManager(addressBookStorage, null, userPrefsStorage);
//
//        initLogging(config);
//
//        addressBookModel = initModelManager(storage, userPrefs);
//
//        addressBookLogic = new AddressBookLogicManager(addressBookModel, storage);
//
//        ui = new UiManager(addressBookLogic);
//    }
//
//    /**
//     * Returns a {@code AddressBookModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
//     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
//     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
//     */
//    private AddressBookModel initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
//        Optional<ReadOnlyAddressBook> addressBookOptional;
//        ReadOnlyAddressBook initialData;
//        try {
//            addressBookOptional = storage.readAddressBook();
//            if (!addressBookOptional.isPresent()) {
//                logger.info("Data file not found. Will be starting with a sample AddressBook");
//            }
//            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
//        } catch (DataConversionException e) {
//            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
//            initialData = new AddressBook();
//        } catch (IOException e) {
//            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
//            initialData = new AddressBook();
//        }
//
//        return new AddressBookModelManager(initialData, userPrefs);
//    }
//
//    private void initLogging(Config config) {
//        LogsCenter.init(config);
//    }
//
//    /**
//     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
//     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
//     * if {@code configFilePath} is null.
//     */
//    protected Config initConfig(Path configFilePath) {
//        Config initializedConfig;
//        Path configFilePathUsed;
//
//        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
//
//        if (configFilePath != null) {
//            logger.info("Custom Config file specified " + configFilePath);
//            configFilePathUsed = configFilePath;
//        }
//
//        logger.info("Using config file : " + configFilePathUsed);
//
//        try {
//            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
//            initializedConfig = configOptional.orElse(new Config());
//        } catch (DataConversionException e) {
//            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
//                    + "Using default config properties");
//            initializedConfig = new Config();
//        }
//
//        //Update config file in case it was missing to begin with or there are new/unused fields
//        try {
//            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
//        } catch (IOException e) {
//            logger.warning("Failed to saveAddressBook config file : " + StringUtil.getDetails(e));
//        }
//        return initializedConfig;
//    }
//
//    /**
//     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
//     * or a new {@code UserPrefs} with default configuration if errors occur when
//     * reading from the file.
//     */
//    protected UserPrefs initPrefs(UserPrefsStorage storage) {
//        Path prefsFilePath = storage.getUserPrefsFilePath();
//        logger.info("Using prefs file : " + prefsFilePath);
//
//        UserPrefs initializedPrefs;
//        try {
//            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
//            initializedPrefs = prefsOptional.orElse(new UserPrefs());
//        } catch (DataConversionException e) {
//            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
//                    + "Using default user prefs");
//            initializedPrefs = new UserPrefs();
//        } catch (IOException e) {
//            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
//            initializedPrefs = new UserPrefs();
//        }
//
//        //Update prefs file in case it was missing to begin with or there are new/unused fields
//        try {
//            storage.saveUserPrefs(initializedPrefs);
//        } catch (IOException e) {
//            logger.warning("Failed to saveAddressBook config file : " + StringUtil.getDetails(e));
//        }
//
//        return initializedPrefs;
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        logger.info("Starting AddressBook " + AddressBookApp.VERSION);
//        ui.start(primaryStage);
//    }
//
//    @Override
//    public void stop() {
//        logger.info("============================ [ Stopping Address Book ] =============================");
//        try {
//            storage.saveUserPrefs(addressBookModel.getUserPrefs());
//        } catch (IOException e) {
//            logger.severe("Failed to saveAddressBook preferences " + StringUtil.getDetails(e));
//        }
//    }
//}
