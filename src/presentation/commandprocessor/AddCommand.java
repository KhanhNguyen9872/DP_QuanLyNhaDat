package presentation.commandprocessor;

import domain.TransactionService;
import domain.model.Transaction;

public class AddCommand extends Command {
    public AddCommand(TransactionService transactionService, Transaction transaction) {
        this.transactionService = transactionService;
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        this.transactionService.add(transaction);
    }
}
