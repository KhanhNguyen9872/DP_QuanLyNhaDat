package presentation.commandprocessor;

import domain.TransactionService;

public class FindByIDCommand extends Command {
    public FindByIDCommand(TransactionService transactionService, int id) {
        this.transactionService = transactionService;
        this.id = id;
    }

    @Override
    public void execute() {
        this.transactionService.find(id);
    }
}
