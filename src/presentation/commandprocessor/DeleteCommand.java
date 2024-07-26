package presentation.commandprocessor;

import domain.TransactionService;

public class DeleteCommand extends Command {
    public DeleteCommand(TransactionService transactionService, int id) {
        this.transactionService = transactionService;
        this.id = id;
    }

    @Override
    public void execute() {
        this.transactionService.delete(id);
    }
}
