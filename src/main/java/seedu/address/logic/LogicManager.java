package seedu.address.logic;

import java.util.function.Supplier;

import seedu.address.achievements.logic.AchievementsLogic;
import seedu.address.achievements.logic.AchievementsLogicManager;
import seedu.address.achievements.model.StatisticsModel;
import seedu.address.achievements.model.StatisticsModelManager;
import seedu.address.address.logic.AddressBookLogic;
import seedu.address.address.logic.AddressBookLogicManager;
import seedu.address.calendar.logic.CalendarLogic;
import seedu.address.commons.core.GuiSettings;
import seedu.address.diaryfeature.logic.DiaryBookLogic;
import seedu.address.financialtracker.logic.FinancialTrackerLogic;
import seedu.address.itinerary.logic.ItineraryLogic;
import seedu.address.model.UserPrefs;

/**
 * The main AddressBookLogicManager of the app.
 */
public class LogicManager implements Logic {

    private AddressBookLogic addressBookLogic;
    private AchievementsLogic achievementsLogic;
    private UserPrefs userPrefs;
    private DiaryBookLogic diaryLogic;
    private CalendarLogic calendarLogic;
    private FinancialTrackerLogic financialTrackerLogic;
    private ItineraryLogic itineraryLogic;
    private MainLogic mainLogic;

    public LogicManager(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
        this.addressBookLogic = new AddressBookLogicManager(userPrefs);
        this.mainLogic = new MainLogicManager(userPrefs);
        this.diaryLogic = new DiaryBookLogic();
        this.calendarLogic = new CalendarLogic();
        this.financialTrackerLogic = new FinancialTrackerLogic();
        this.itineraryLogic = new ItineraryLogic();
            this.achievementsLogic = new AchievementsLogicManager(new Supplier<StatisticsModel>() {
                @Override
                public StatisticsModel get() {
                    return new StatisticsModelManager(addressBookLogic.getStatistics(),
                            calendarLogic.getStatistics(),
                            diaryLogic.getStatistics(),
                            financialTrackerLogic.getStatistics(),
                            itineraryLogic.getStatistics());
                }
            });
    }

    @Override
    public AddressBookLogic getAddressBookLogic() {
        return addressBookLogic;
    }

    @Override
    public AchievementsLogic getAchievementsLogic() {
        return achievementsLogic;
    }

    @Override
    public DiaryBookLogic getDiaryLogic() {
        return diaryLogic;
    }

    @Override
    public CalendarLogic getCalendarLogic() {
        return calendarLogic;
    }

    public FinancialTrackerLogic getFinancialTrackerLogic() {
        return this.financialTrackerLogic;
    }

    @Override
    public ItineraryLogic getItineraryLogic() {
        return this.itineraryLogic;
    }

    @Override
    public MainLogic getMainLogic() {
        return this.mainLogic;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        userPrefs.setGuiSettings(guiSettings);
    }
}
