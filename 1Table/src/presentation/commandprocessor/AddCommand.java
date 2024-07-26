package presentation.commandprocessor;

import domain.GDService;
import domain.model.GD;

public class AddCommand extends Command {
    public AddCommand(GDService gdService, GD gd) {
        this.gdService = gdService;
        this.gd = gd;
    }

    @Override
    public void execute() {
        this.gdService.add(gd);
    }
}
