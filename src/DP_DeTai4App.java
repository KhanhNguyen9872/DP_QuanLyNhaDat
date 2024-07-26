import domain.*;
import persistence.*;
import presentation.TransactionUI;
import presentation.commandprocessor.CommandProcessor;

public class DP_DeTai4App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("org.jdesktop.swingx.JXDatePicker");

        CommandProcessor commandProcessor = CommandProcessor.makeCommandProcessor();

        TransactionPersistenceService transactionPersistenceService = new TransactionPersistenceServiceImpl("localhost", "3306", "dbdetai4", "root", "12345678");

        TransactionService transactionService = new TransactionServiceImpl(transactionPersistenceService);

        TransactionUI ui = new TransactionUI(transactionService, commandProcessor);
    }
}
