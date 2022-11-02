package JDBC;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class U1VillainsNames {
    private static final String GET_VILLAINS_NAMES = """
            SELECT v.`name`, COUNT(DISTINCT mv.`minion_id`) AS minions_count
            FROM `villains` AS v
                    JOIN `minions_villains` AS mv ON v.`id` = mv.`villain_id`
            GROUP BY v.`name`
            HAVING COUNT(DISTINCT mv.`minion_id`) > 15
            ORDER BY COUNT(mv.`minion_id`) DESC;""";

    private static final String VILLAINS_COLUMN_NAME = "name";
    private static final String MINIONS_COUNT_COLUMN = "minions_count";
    private static final String PRINT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        final ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            final String name = rs.getString(VILLAINS_COLUMN_NAME);
            final int minionsCount = rs.getInt(MINIONS_COUNT_COLUMN);

            System.out.printf(PRINT_FORMAT, name, minionsCount);
        }
        connection.close();
    }
}