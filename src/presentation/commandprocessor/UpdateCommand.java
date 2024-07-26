package presentation.commandprocessor;

import domain.TransactionService;
import domain.model.Transaction;

public class UpdateCommand extends Command {
    public UpdateCommand(TransactionService transactionService, Transaction transaction) {
        this.transactionService = transactionService;
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        this.transactionService.update(transaction);
    }
}
