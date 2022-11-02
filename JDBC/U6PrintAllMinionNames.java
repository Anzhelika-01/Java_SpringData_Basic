package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class U6PrintAllMinionNames {

    final static String SELECT_ALL_MINIONS_NAMES = """
            SELECT `name`
            FROM `minions`;
            """;

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection
                .prepareStatement(SELECT_ALL_MINIONS_NAMES);
        final ResultSet resultSet = statement.executeQuery();
        ArrayList<String> names = new ArrayList<>();

        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        int i = 0;
        for (int j = names.size() - 1; j >= names.size() / 2 && i != j; j--) {
            System.out.println(names.get(i));
            System.out.println(names.get(j));
            i++;
        }
        connection.close();
    }
}