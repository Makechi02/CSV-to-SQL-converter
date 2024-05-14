package com.makbe.converter.converter;

import com.makbe.converter.connections.Connections;

import java.io.*;
import java.sql.*;

public class Converter {
	private static final Connections connections = Connections.getInstance();

	public static void populateDatabase(String csvFilePath, String tableName, String databaseName, String username, String password) {
		try (Connection connection = connections.getConnection(databaseName, username, password);
			 BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

			String line;
			String insertQuery = buildInsertQuery(tableName, reader.readLine());

			if (insertQuery == null) {
				System.err.println("Failed to build insert query.");
				return;
			}

			System.out.println("INSERT QUERY: " + insertQuery);

			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

			int lineCount = 0;

			while ((line = reader.readLine()) != null) {
				lineCount++;
				String[] data = line.split(",");

				int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();

				if (data.length != parameterCount) {
					System.err.println("Line " + lineCount + ": Incorrect number of columns. Expected " +
							parameterCount + " but found " + data.length);
					continue;
				}

				for (int i = 0; i < data.length; i++) {
					setObject(preparedStatement, i + 1, data[i]);
				}
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
			System.out.println("Data inserted into MySQL successfully.");
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String buildInsertQuery(String tableName, String headerLine) {
		if (headerLine == null) {
			return null;
		}

		headerLine = headerLine.replaceAll("\\p{C}", "");

		String[] columns = headerLine.split(",");
		StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
		for (int i = 0; i < columns.length; i++) {
			query.append(columns[i]);
			if (i < columns.length - 1) {
				query.append(", ");
			}
		}

		query.append(") VALUES (");

		for (int i = 0; i < columns.length; i++) {
			if (i > 0) {
				query.append(", ");
			}

			if (isDateColumn(columns[i])) {
				query.append("'");
				query.append(convertDate(columns[i]));
				query.append("'");
			} else {
				query.append("'");
				query.append(columns[i]);
				query.append("'");
			}
		}

		query.append(")");

		return query.toString();
	}

	private static void setObject(PreparedStatement preparedStatement, int index, String value) throws SQLException {
		try {
			int intValue = Integer.parseInt(value);
			preparedStatement.setInt(index, intValue);
		} catch (NumberFormatException e1) {
			try {
				double doubleValue = Double.parseDouble(value);
				preparedStatement.setDouble(index, doubleValue);
			} catch (NumberFormatException e2) {
				try {
					preparedStatement.setObject(index, java.sql.Date.valueOf(value));
				} catch (IllegalArgumentException e3) {
					preparedStatement.setString(index, value);
				}
			}
		}
	}

	private static boolean isDateColumn(String columnName) {
		return columnName.contains("date");
	}

	private static String convertDate(String date) {
		String[] parts = date.split("-");
		if (parts.length == 3) {
			String year = parts[2];
			String month = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
			String day = parts[1].length() == 1 ? "0" + parts[1] : parts[1];

			int intYear = Integer.parseInt(year);
			if (intYear >= 0 && intYear <= 69) {
				year = "20" + year;
			} else if (intYear >= 70 && intYear <= 99) {
				year = "19" + year;
			}

			return year + "-" + month + "-" + day;
		} else {
			return date;
		}
	}

}
