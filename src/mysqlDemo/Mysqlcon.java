
package mysqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysqlcon {
	public static void main(String[] args) {

        String url =
            "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC";

        String user = "root";
        String password = "root123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                DriverManager.getConnection(url, user, password);

            System.out.println("Connected Successfully!");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}