package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Utils {
    ;

    static Connection getSQLConnection() throws SQLException {

        final Properties props = new Properties();
        props.setProperty(Constants.USER_KEY, Constants.USER_NAME);
        props.setProperty(Constants.PASSWORD_KEY, Constants.USER_PASSWORD);

        return DriverManager
                .getConnection(Constants.JDBC_URL, props);
    }
}
