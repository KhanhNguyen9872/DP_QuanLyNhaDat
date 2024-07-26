package presentation.commandprocessor;

import domain.GDService;

public class DeleteCommand extends Command {
    public DeleteCommand(GDService gdService, int id) {
        this.gdService = gdService;
        this.id = id;
    }

    @Override
    public void execute() {
        this.gdService.delete(id);
    }
}
