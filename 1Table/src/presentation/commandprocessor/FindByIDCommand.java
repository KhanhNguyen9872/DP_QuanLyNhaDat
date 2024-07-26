package presentation.commandprocessor;

import domain.GDService;

public class FindByIDCommand extends Command {
    public FindByIDCommand(GDService gdService, int id) {
        this.gdService = gdService;
        this.id = id;
    }

    @Override
    public void execute() {
        this.gdService.findGD(id);
    }
}
