package seedu.address.diaryfeature.diaryCommands;

import seedu.main.logic.commands.CommandResult;

public class ByeDiaryCommand extends DiaryCommand {
    @Override
    public CommandResult executeCommand() {
        return new CommandResult("bye", false, true);
    }

}
