package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class U2MinionNames {
    private static final String GET_MINION_NAMES = """
            SELECT m.`name`, m.`age`
            FROM `minions` AS m
                    JOIN `minions_villains` AS mv ON m.`id` = mv.`minion_id`
            WHERE mv.`villain_id` = ?;""";

    private static final String GET_VILLAIN_NAME = """
            SELECT `name`
             FROM `villains`
             WHERE `id` = ?;""";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String MINION_AGES_COLUMN = "age";
    private static final String NO_SUCH_VILLAINS
            = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s\n";
    private static final String MINION_FORMAT = "%d. %s %d\n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        final int index = Integer.parseInt(scanner.nextLine());
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement villainStatement =
                connection.prepareStatement(GET_VILLAIN_NAME);
        villainStatement.setInt(1, index);

        final ResultSet villainSet = villainStatement.executeQuery();
        if (!villainSet.next()){
            System.out.printf(NO_SUCH_VILLAINS, index);
            connection.close();
            return;
        }

        final String villainName = villainSet.getString(COLUMN_LABEL_NAME);
        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement minionStatement =
                connection.prepareStatement(GET_MINION_NAMES);
        minionStatement.setInt(1, index);

        final ResultSet minionSet = minionStatement.executeQuery();

        int count = 0;
        while (minionSet.next()) {
            count++;
            final String minionName = minionSet.getString(COLUMN_LABEL_NAME);
            final int minionAge = minionSet.getInt(MINION_AGES_COLUMN);

            System.out.printf(MINION_FORMAT, count, minionName, minionAge);
        }
        connection.close();
    }
}