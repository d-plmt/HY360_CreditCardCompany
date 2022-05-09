import controller.ControllerContainer;
import model.DatabaseConnection;
import model.Generator;
import model.UserLoginQueryHandler;
import model.ccc.CccQueryHandler;
import model.company.CompanyQueryHandler;
import model.privateCitizen.PrivateCitizenQueryHandler;
import model.supplier.SupplierQueryHandler;
import view.GUI;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseConnection ccc_database_connection = new DatabaseConnection("root", "!Abcd1234");
            CompanyQueryHandler.setStatement(ccc_database_connection.getStatement());
            PrivateCitizenQueryHandler.setStatement(ccc_database_connection.getStatement());
            SupplierQueryHandler.setStatement(ccc_database_connection.getStatement());
            CccQueryHandler.setStatement(ccc_database_connection.getStatement());
            UserLoginQueryHandler.setStatement(ccc_database_connection.getStatement());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        GUI gui = new GUI();
        new ControllerContainer(gui);
    }
}
