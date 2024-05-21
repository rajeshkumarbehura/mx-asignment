package org.example;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    static String filePath = "/Users/vampire/Documents/sellde/mox_test/mx-data-test/src/main/resources/input.txt";

    public static void main(String[] args) {
        try {
            var table = readFixedWidthFile(filePath);

            var expectedCodesList = table
                    /* Sort the data by email*/
                    .sortAscendingOn("EMAIL")
                    .stream()
                    /* validate the domain name length*/
                    .filter(Main::isValidDomainLengthMoreThan20)
                    /* validate userid first character is in lowercase or not */
                    .filter(row -> !isFirstCharLowercase(row))
                    /* fetch only first 5 records */
                    .limit(5)
                    .map(row -> row.getInt("CODE"))
                    .toList();

            System.out.println("Print the Result :");
            System.out.println(expectedCodesList);

            assert !expectedCodesList.isEmpty();
            assert expectedCodesList.toString().equals("[1, 2, 3, 4, 5]");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This file is fixed length file and which required special case to solve this problem.
     *
     * @param filePath
     * @return
     */
    private static Table readFixedWidthFile(String filePath) {
        StringColumn emailColumn = StringColumn.create("EMAIL");
        StringColumn usernameColumn = StringColumn.create("USERNAME");
        StringColumn idColumn = StringColumn.create("ID");
        IntColumn codeColumn = IntColumn.create("CODE");

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> !line.startsWith("EMAIL")) // Skip header line
                    .forEach(line -> {
                        String email = line.substring(0, 43).trim();
                        String username = line.substring(43, 73).trim();
                        String id = line.substring(73, 89).trim();
                        int code = Integer.parseInt(line.substring(89).trim());

                        /* This can be good location to Write the filter to create efficiency */

                        emailColumn.append(email);
                        usernameColumn.append(username);
                        idColumn.append(id);
                        codeColumn.append(code);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Table.create("FixedWidthData", emailColumn, usernameColumn, idColumn, codeColumn);
    }

    private static boolean isValidDomainLengthMoreThan20(Row row) {
        var email = row.getString(0);
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        // Extract the domain part of the email
        String domain = email.substring(email.indexOf('@') + 1);

        // Return domain length
        return domain.length() >= 20;
    }


    private static boolean isFirstCharLowercase(Row row) {
        var id = row.getString(2);
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("The string is null or empty");
        }

        // Get the first character of the string
        char firstChar = id.charAt(0);

        // Check if the first character is a lowercase letter
        return Character.isLowerCase(firstChar);
    }
}


