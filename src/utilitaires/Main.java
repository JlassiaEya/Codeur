package utilitaires;

import view.GeneratorView;
import controllers.GeneratorController;
import dao.DatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
 
		Utilitaire.seConnecter("connectionPar.properties");

		Connection connection = Utilitaire.getConnection();


		DatabaseSchema schema = new DatabaseSchema(connection);


		GeneratorView view = new GeneratorView();


		GeneratorController controller = new GeneratorController(view, schema);
        
		view.setVisible(true); 
    }
}
