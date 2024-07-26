package presentation.commandprocessor;

import domain.TransactionService;

public class AvgLandCommand extends Command {
    public AvgLandCommand(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute() {
        this.transactionService.avgMoneyLand();
    }
}
