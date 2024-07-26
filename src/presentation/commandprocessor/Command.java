package presentation.commandprocessor;

import domain.*;
import domain.model.*;

public abstract class Command {
    public TransactionService transactionService;
    public Transaction transaction;
    public int id;
    public abstract void execute();
}
