package presentation.commandprocessor;

import java.util.List;

import domain.TransactionService;
import domain.model.Transaction;

public class AvgLandCommand extends Command {
    private List<Transaction> listTransactions;
    
    public AvgLandCommand(TransactionService transactionService, List<Transaction> listTransactions) {
        this.transactionService = transactionService;
        this.listTransactions = listTransactions;
    }

    @Override
    public void execute() {
        this.transactionService.avgMoneyLand(listTransactions);
    }
}
