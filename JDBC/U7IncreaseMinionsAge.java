package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class U7IncreaseMinionsAge {

    final static String INCREMENT_THE_AGE_AND_NAME_TO_LOWER_CASE = """
            UPDATE `minions`
            SET `age` = `age` + 1, `name` = LOWER(`name`)
            WHERE `id` IN(?);
            """;

    final static String SELECT_ALL_MINIONS = """
            SELECT `name`, `age`
            FROM `minions`
            """;

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        final String[] ids = scanner.nextLine().split("\\s+");
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection
                .prepareStatement(INCREMENT_THE_AGE_AND_NAME_TO_LOWER_CASE);

        for (String id : ids) {
            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
        }

        final PreparedStatement finalStatement = connection
                .prepareStatement(SELECT_ALL_MINIONS);
        final ResultSet rs = finalStatement.executeQuery();
        while (rs.next())
        System.out.printf("%s %d\n", rs.getString("name")
                , rs.getInt("age"));

        connection.close();
    }
}