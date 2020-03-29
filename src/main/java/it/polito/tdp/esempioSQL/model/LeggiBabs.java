package it.polito.tdp.esempioSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.polito.tdp.esempioSQL.db.BabsDAO;

public class LeggiBabs {
	
	public void run() {
		//Passo 1 
		String jdbcURL = "jdbc:mysql://localhost/babs?user=root";
		
		//Passo 2: CONN è UN OGGETTO DI UN CERTO TIPO CHE IMPLEMENTA UN'INTERFACCIA
			//ho creato un nuovo oggetto senza sapere il tipo della classe, per questo non ho usato new
			//uso un metodo fornito da un'altra classe che internamente fara new e cnoscera il tipo di classe effettivo
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			/*METODO CON CREATESTATEMENT
			Statement st = conn.createStatement();
			
			//ora posso chiedere all'oggetto statement di eseguire una query
				//ho verificato la validità della stringa nella sezione query di heidi
			String sql = "SELECT NAME FROM station";
			
			//stringa mandata al server che la eseguirà
			st.executeQuery(sql);
			
			//ottengo il risultato: contiene il modo a cui accedere al risultato
			ResultSet res = st.executeQuery(sql);
			*/
			
			//metodo con PREPARESTATEMENT
			String sql = "SELECT name FROM station WHERE landmark = ? ";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,  "Palo Alto");
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				String nomeStazione = res.getString("name");
				
				System.out.println(nomeStazione);
			}
			//chiudo lo statemnet per sicurezza: dimenticati di questo
			st.close();
			
			//posso avviare altri statements
			
			//non è possibile creare troppe connessioni: devo sempre chiudere le connessioni
			conn.close();
			
		} catch (SQLException e) {
			//gestisco l'eccezione
			e.printStackTrace();
		}
		
		// è un nuovo pattern chiamato FACTORY
	}
	
	public static void main(String args[]) {
		LeggiBabs babs = new LeggiBabs();
		babs.run();
	}
}
