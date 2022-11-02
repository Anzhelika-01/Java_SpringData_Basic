package JDBC;

import java.sql.*;
import java.util.Scanner;

public class U8IncreaseAgeStoredProcedure {
    final static String CREATE_STORED_PROCEDURE_FOR_INCREMENT_THE_AGE_AND_NAME_TO_LOWER_CASE = """
            CREATE PROCEDURE usp_get_older()
            BEGIN
            UPDATE `minions`
                        SET `age` = `age` + 1, `name` = LOWER(`name`)
                        WHERE `id` IN(?);
            END  
            """;

    final static String CALL_STORED_PROCEDURE = "{CALL usp_get_older()}";

    final static String SELECT_MINION = """
            SELECT `name`, `age`
            FROM `minions`
            WHERE `id` = ?;
            """;

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        final int id = Integer.parseInt(scanner.nextLine());
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement
                (CREATE_STORED_PROCEDURE_FOR_INCREMENT_THE_AGE_AND_NAME_TO_LOWER_CASE);
        statement.setInt(1, id);
        statement.execute();

        final CallableStatement callProcedure = connection
                .prepareCall(CALL_STORED_PROCEDURE);
        callProcedure.execute();

        final PreparedStatement finalStatement = connection
                .prepareStatement(SELECT_MINION);
        finalStatement.setInt(1, id);

        final ResultSet rs = finalStatement.executeQuery();
        rs.next();

        System.out.printf("%s %d", rs.getString("name"),
                rs.getInt("age"));

        connection.close();
    }
}