package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class U4ChangeTownNameCasing {

    final static String MAKE_NAME_UPPERCASE = """
            UPDATE `towns`
            SET `name` = UPPER(`name`)
            WHERE `country` = ?;
            """;

    final static String ALL_TOWNS_BY_COUNTRY_NAMES = """
            SELECT `name`
            FROM `towns`
            WHERE `country` = ?;
            """;

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        final String country = scanner.nextLine();
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement upperCaseStatement = connection
                .prepareStatement(MAKE_NAME_UPPERCASE);
        upperCaseStatement.setString(1, country);

        final int updatedCount = upperCaseStatement.executeUpdate();

        if (updatedCount == 0){
            System.out.println("No town names were affected.");
            return;
        }

        final ArrayList<String> towns = new ArrayList<>();

        final PreparedStatement townsStatement = connection
                .prepareStatement(ALL_TOWNS_BY_COUNTRY_NAMES);
        townsStatement.setString(1, country);
        final ResultSet townsSet = townsStatement.executeQuery();

        while (townsSet.next()) {
            towns.add(townsSet.getString("name"));
        }

        System.out.printf("%d town names were affected.\n", updatedCount);
        System.out.println(towns);
    }
}