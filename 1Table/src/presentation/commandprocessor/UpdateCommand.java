package presentation.commandprocessor;

import domain.GDService;
import domain.model.GD;

public class UpdateCommand extends Command {
    public UpdateCommand(GDService gdService, GD gd) {
        this.gdService = gdService;
        this.gd = gd;
    }

    @Override
    public void execute() {
        this.gdService.update(gd);
    }
}
