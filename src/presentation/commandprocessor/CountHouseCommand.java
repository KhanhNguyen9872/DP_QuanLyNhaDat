package presentation.commandprocessor;

import domain.TransactionService;

public class CountHouseCommand extends Command {
    private String loai;

    public CountHouseCommand(TransactionService transactionService, String loai) {
        this.transactionService = transactionService;
        this.loai = loai;
    }

    @Override
    public void execute() {
        this.transactionService.sumCountTransaction(this.loai);
    }
}
