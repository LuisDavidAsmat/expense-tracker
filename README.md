# [Expense Tracker](https://roadmap.sh/projects/expense-tracker)
### A backend project idea from [roadmap](https://roadmap.sh/)
##### This is my solution for the [Expense Tracker](https://roadmap.sh/projects/github-user-activity)  backend challenge.
[![](https://avatars.githubusercontent.com/u/120650344?s=40&v=4)](https://roadmap.sh/)

This is a simple expense tracker application to manage your finances. The application allows users to add, delete, and view their expenses. The application also provides a summary of the expenses.

- Practice your programming skills
- Working with APIs
- Handling JSON data
- Building a simple CLI application

## Features -
- **Add Expense**: Add a new expense with a description and amount.
- **Update Expense**: Update an existing expense by its ID.
- **Delete Expense**: Delete an expense by its ID.
- **List Expenses**: View a list of all expenses.
- **Summary**: Get a summary of total expenses, with an option to filter by month.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Gson library

## Usage

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/expense-tracker.git 
   cd expense-tracker/src
   ```
2. The src folder already has the jar for gson library. But if not, you can download it from this link:
   ```sh
   Gson Library - 2.8.9 (https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.9)
   ```
3. Compile the Java files using the following command:
   ```sh
   javac -cp ".;gson-2.8.9.jar" Expense.java ExpenseTracker.java LocalDateTypeAdapter.java Main.java
   ```
4. Running the Application
   ```sh
   java -cp ".;gson-2.8.9.jar" Main <command> [options]
   ```
5. Commands
   5.1. _Add and expense_
   ```bash
   java -cp ".;gson-2.8.9.jar" Main add --description "Lunch" --amount 20.0
   ```
   5.2. Update an  expense
   ```bash
   java -cp ".;gson-2.8.9.jar" Main add --description 			"Lunch" --amount 20.0
   ```
   5.3. List Expenses
   ```bash
   java -cp ".;gson-2.8.9.jar" Main list
   ```
   5.2. Summary of Expenses
   ```bash
   java -cp ".;gson-2.8.9.jar" Main summary
   ```
   5.2. Summary of Expenses by Month
   ```bash
   java -cp ".;gson-2.8.9.jar" Main summary --month 8
   ```
   5.2. Delete an  expense
   ```bash
   java -cp ".;gson-2.8.9.jar" Main delete --id 1
   ```

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes. 