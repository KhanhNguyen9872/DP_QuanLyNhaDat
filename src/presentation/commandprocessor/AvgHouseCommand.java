package presentation.commandprocessor;

import java.util.List;

import domain.TransactionService;
import domain.model.Transaction;

public class AvgHouseCommand extends Command {
    private List<Transaction> listTransactions;
    
    public AvgHouseCommand(TransactionService transactionService, List<Transaction> listTransactions) {
        this.transactionService = transactionService;
        this.listTransactions = listTransactions;
    }

    @Override
    public void execute() {
        this.transactionService.avgMoneyHouse(listTransactions);
    }
}
