# CSV to SQL Converter

This project is a Java application that converts data from a CSV (Comma-Separated Values) file to SQL (Structured Query Language) insert statements, which can be used to populate a MySQL database.

## Overview

The CSV to SQL Converter reads data from a CSV file and generates SQL insert statements based on the data. It supports flexible configuration for handling various data types and formats, including dates, numbers, and text.

## Features

- Converts CSV data to SQL insert statements
- Handles different data types (strings, integers, floats, dates)
- Supports configurable date formats
- Handles special characters (e.g., commas within text fields) properly
- Provides error handling and logging for debugging purposes

## Usage

1. **Setup**: Ensure that you have Java installed on your system.
2. **Configuration**: Edit the `database.properties` file to specify your MySQL database credentials.
3. **Input**: Place your CSV file (e.g., `input.csv`) in the root directory of the project.
4. **Run**: Compile and run the `CsvToSqlConverter.java` class.

## Example

Suppose you have a CSV file `input.csv` with the following data:

"ID","Name","Age"
"1","John Doe","30"
"2","Jane Smith","25"


After running the CSV to SQL Converter, it generates the following SQL statements:

```sql
INSERT INTO example_table (ID, Name, Age) VALUES ('1', 'John Doe', '30');
INSERT INTO example_table (ID, Name, Age) VALUES ('2', 'Jane Smith', '25');
```

