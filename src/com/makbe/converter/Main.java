package com.makbe.converter;

import com.makbe.converter.converter.CsvToSqlConverter;

public class Main {
	public static void main(String[] args) {
		CsvToSqlConverter converter = new CsvToSqlConverter();

		String tableName = "superstore_orders";
		String fileName = "Superstore_orders.csv";

		converter.convert(fileName, tableName);
	}
}