package presentation.commandprocessor;

import domain.TransactionService;

public class AvgHouseCommand extends Command {
    public AvgHouseCommand(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute() {
        this.transactionService.avgMoneyHouse();
    }
}
