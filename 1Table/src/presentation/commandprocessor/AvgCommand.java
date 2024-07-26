package presentation.commandprocessor;

import domain.GDService;

public class AvgCommand extends Command {
    public AvgCommand(GDService gdService) {
        this.gdService = gdService;
    }

    @Override
    public void execute() {
        this.gdService.avgMoney();
    }
}
