package presentation.commandprocessor;

import domain.TransactionService;

public class CountLandCommand extends Command {
    private String loai;

    public CountLandCommand(TransactionService transactionService, String loai) {
        this.transactionService = transactionService;
        this.loai = loai;
    }

    @Override
    public void execute() {
        this.transactionService.sumCountTransaction(this.loai);
    }
}
