/**
 * 
 */
package jdbctest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author David
 *
 */

public class Test {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/jdbctest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String users = "C:/Users/David/users.csv";
    private static final String users_update = "C:/Users/David/users_update.csv";
    private static final String users_delete = "C:/Users/david/users_delete.csv";
    private static final String users_new = "C:/Users/david/users_new.csv";
    private static BufferedReader br;
    private static String line;
    private static Connection conn;
    private static String query ="LOAD DATA LOCAL INFILE '"
            + users
            + "' INTO TABLE users FIELDS TERMINATED BY ','"
            + " LINES TERMINATED BY '\n' (firstName, lastName, Email, Age, Sex, HairColor) ";
    private static String update = "Update users set age = ? Where email = ?";
    private static PreparedStatement preparedStatement;
    private static final String delete = "delete from users where email = ?";
    private static final String export =  "select * from users";
    

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}


		try {
			conn = DriverManager
			.getConnection(DB_CONNECTION,DB_USERNAME, DB_PASSWORD);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (conn != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	  
	
		deleteData();	  
		writeData();
		updateData();
		exportCSV();
		
	}
		private static void deleteData() {
			try {
				preparedStatement = conn.prepareStatement(delete);
				br = new BufferedReader(new FileReader(users_delete));

				while ((line = br.readLine()) != null) {
                preparedStatement.setString(1,line);
                preparedStatement.executeUpdate();
            }

			}	 catch 	(IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	
		private static void writeData() {
		
		try {
			
		      preparedStatement = conn.prepareStatement(query);
		      preparedStatement.execute(query);
		    }
		    catch (Exception e)
		    {
		      System.out.println("Got an exception!");
		      System.out.println(e.getMessage());
		    }
		  
		}
		
		private static void updateData(){
	         try {
	             preparedStatement = conn.prepareStatement(update);
	             br = new BufferedReader(new FileReader(users_update));

	             String csvSplitBy = ",";
	             while ((line = br.readLine()) != null) {
	                 String[] list = line.split(csvSplitBy);
	                 preparedStatement.setInt(1, Integer.parseInt(list[1]));
	                 preparedStatement.setString(2, list[0]);
	                 preparedStatement.executeUpdate();
	             }
	         } catch (IOException | SQLException e) {
	             e.printStackTrace();
	         }
	     }
		private static void exportCSV() {
	   
			  try {
		            FileWriter fw = new FileWriter(users_new);
		            
		            preparedStatement = conn.prepareStatement(export);
		            ResultSet rs =  preparedStatement.executeQuery(export);
		            while (rs.next()) {
		                fw.append(rs.getString(1));
		                fw.append(',');
		                fw.append(rs.getString(2));
		                fw.append(',');
		                fw.append(rs.getString(3));
		                fw.append(',');
		                fw.append(rs.getString(4));
		                fw.append('\n');
		            }
		            fw.flush();
		            fw.close();
		            conn.close();
		            System.out.println("CSV File is created successfully.");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}	



	
	
		
