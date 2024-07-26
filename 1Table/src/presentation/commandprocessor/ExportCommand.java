package presentation.commandprocessor;

import domain.GDService;

public class ExportCommand extends Command {
    private int month = 0;

    public ExportCommand(GDService gdService, int month) {
        this.gdService = gdService;
        this.month = month;
    }

    @Override
    public void execute() {
        this.gdService.exportData(month);
    }
}
