package presentation.commandprocessor;

import domain.GDService;
import domain.model.House;

public class CalcCommand extends Command {
    public CalcCommand(GDService gdService, House gd) {
        this.gdService = gdService;
        this.gd = gd;
    }

    @Override
    public void execute() {
        this.gdService.calcMoney(gd);
    }
}
