package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class U3AddMinion {
    final static String TOWN_EXISTENCE = """
            SELECT `id` FROM `towns`
            WHERE `name` = ?;
            """;

    final static String INSERT_TOWN = """
            INSERT INTO `towns`(`name`)
            VALUES(?);
            """;

    final static String ADD_MINION = """
            INSERT INTO `minions`(`name`, `age`, `town_id`)
            VALUES(?, ?, ?);
            """;

    final static String VILLAIN_EXISTENCE = """
            SELECT `id` FROM `villains`
            WHERE `name` = ?;
            """;

    final static String ADD_VILLAIN = """
            INSERT INTO `villains`(`name`, `evilness_factor`)
            VALUES(?, "evil");
            """;

    final static String GET_ADDED_MINION = """
            SELECT `id`
            FROM `minions`
            ORDER BY `id` DESC
            LIMIT 1
            """;

    final static String CONNECT_MINION_WITH_VILLAIN = """
            INSERT INTO `minions_villains`(`minion_id`, `villain_id`)
            VALUES(?, ?)
            """;

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        final String[] minionInfo = scanner.nextLine().split("\\s+");
        final String minionName = minionInfo[1];
        final int minionAge = Integer.parseInt(minionInfo[2]);
        final String townName = minionInfo[3];

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement existenceStatement = connection
                .prepareStatement(TOWN_EXISTENCE);
        existenceStatement.setString(1, townName);
        final ResultSet townSet = existenceStatement.executeQuery();
        int townId;

        if (!townSet.next()) {
            final PreparedStatement addTownStatement = connection
                    .prepareStatement(INSERT_TOWN);

            addTownStatement.setString(1, townName);
            addTownStatement.executeUpdate();

            System.out.printf("Town %s was added to the database.\n", townName);

            final ResultSet newResultSet = existenceStatement.executeQuery();
            newResultSet.next();
            townId = newResultSet.getInt("id");
        } else {
            townId = townSet.getInt("id");
        }

        final PreparedStatement minionStatement = connection
                .prepareStatement(ADD_MINION);
        minionStatement.setString(1, minionName);
        minionStatement.setInt(2, minionAge);
        minionStatement.setInt(3, townId);
        minionStatement.executeUpdate();

        final String[] villainInfo = scanner.nextLine().split("\\s+");
        final String villainName = villainInfo[1];

        final PreparedStatement villainStatement = connection
                .prepareStatement(VILLAIN_EXISTENCE);
        villainStatement.setString(1, villainName);
        final ResultSet villainSet = villainStatement.executeQuery();
        int villainId;

        if (!villainSet.next()) {
            final PreparedStatement addVillainStatement = connection
                    .prepareStatement(ADD_VILLAIN);
            addVillainStatement.setString(1, villainName);
            addVillainStatement.executeUpdate();

            System.out.printf("Villain %s was added to the database.\n", villainName);

            final ResultSet newResultSet = existenceStatement.executeQuery();
            newResultSet.next();

            villainId = newResultSet.getInt("id");
        } else {
            villainId = townSet.getInt("id");
        }

        final PreparedStatement lastMinion = connection
                .prepareStatement(GET_ADDED_MINION);

        final ResultSet lastMinionResultSet = lastMinion.executeQuery();
        lastMinionResultSet.next();

        final int addedMinionId = lastMinionResultSet.getInt("id");

        final PreparedStatement connectStatement = connection
                .prepareStatement(CONNECT_MINION_WITH_VILLAIN);
        connectStatement.setInt(1, addedMinionId);
        connectStatement.setInt(2, villainId);
        connectStatement.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.",
                minionName, villainName);
        connection.close();
    }
}