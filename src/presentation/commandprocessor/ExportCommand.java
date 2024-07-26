package presentation.commandprocessor;

import domain.TransactionService;

public class ExportCommand extends Command {
    private int month = 0;

    public ExportCommand(TransactionService transactionService, int month) {
        this.transactionService = transactionService;
        this.month = month;
    }

    @Override
    public void execute() {
        this.transactionService.exportData(month);
    }
}
