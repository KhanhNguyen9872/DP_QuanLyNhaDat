package presentation.commandprocessor;

import domain.TransactionService;

import domain.model.Transaction;

public class CalcCommand extends Command {
    public CalcCommand(TransactionService transactionService, Transaction transaction) {
        this.transactionService = transactionService;
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        this.transactionService.calcMoney(transaction);
    }
}
