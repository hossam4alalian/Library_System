package per;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class database {
	private Connection connect;
	Object[]fields;
	 public database(String host, String user,String password, String databas) {
		 try {
		 Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		 connect= DriverManager.getConnection("jdbc:mysql://"+host+"/"+databas,user,password);

		 }
		 	catch(Exception missfall){
		 		System.out.println("Error"+ missfall);
		 	}
		 }
		 
		 public void execute(String SQL) throws SQLException {
			 Statement stmnt = connect.createStatement();
			 stmnt.execute(SQL);
			 /*
		 try {
			 Statement stmnt = connect.createStatement();
			 stmnt.execute(SQL);
		 }
		 	catch(Exception missfall) {
		 		JOptionPane.showMessageDialog(null, missfall);
		 	}*/
		 }

		 public Object[][] getData(String SQL) {
				Object[][]data=null;
				try {
					Statement stmnt = connect.createStatement();
					
					ResultSet res = stmnt.executeQuery(SQL);
					ResultSetMetaData metd = res.getMetaData();
					Object[] fields = new Object[metd.getColumnCount()];
					for(int i=0;i<fields.length;i++) {
						fields[i]=metd.getColumnName(i+1);
					}
					int nrow=0;
					while(res.next()) {
						nrow++;
					}
					data=new Object[nrow][metd.getColumnCount()];
					res.beforeFirst();
					int rad=0;
					while(res.next()) {
						for(int i=0;i<metd.getColumnCount();i++) {
							data[rad][i]=res.getString(i+1);
						}
						rad++;
					}
				}
				catch(Exception missfall) {
					JOptionPane.showMessageDialog(null, missfall);
				}
				return data;
			}
		 
		 public static void main(String[]args)
		 {
			 
			 database test = new database("localhost","root","","tidredovisning");
		 }
}

