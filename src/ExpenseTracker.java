import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker
{
    private static final String EXPENSES_JSON_FILE = "expense.json";
    private static List<Expense> expenses = new ArrayList<>();
    private static final Gson gson;

    static
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        gson = gsonBuilder.setPrettyPrinting().create();

        loadExpenses();
    }

    private static void loadExpenses()
    {
        // checks if file exists
        if (new File(EXPENSES_JSON_FILE).exists())
        {
            try
            {
                // Path object representing the file path
                Path paths = Paths.get(EXPENSES_JSON_FILE);

                // Reads from file and creates a byte array
                byte[] arrayByte = Files.readAllBytes(paths);

                // Converts from byte array to string (String([byte]))
                String jsonFile = new String(arrayByte);

                // gson.fromJson: parses string into a java object
                expenses =
                        gson.fromJson(jsonFile,
                        // Anonymous inner class and method tell Gson
                        // the specific type of object to create.
                        // Type information object that says,
                        // "I expect a list of Expense objects."
                        new TypeToken<List<Expense>>(){}.getType());

                int maxId = expenses.stream().mapToInt(Expense::getId).max().orElse(0);

                Expense.setIdCounter(maxId + 1);
            }
            catch (IOException e)
            {
                System.err.println("Error loading json file: " + e.getMessage());
            }
        }
    }

    private static void saveExpenses()
    {
        try (FileWriter fileWriter = new FileWriter(EXPENSES_JSON_FILE))
        {
            gson.toJson(expenses, fileWriter);
        }
        catch (IOException e)
        {
            System.err.println("Error writing to json file" + e.getMessage());
        }
    }

    public static void addExpense(String newExpenseDescription, double newExpenseAmount)
    {
        Expense newExpense = new Expense(newExpenseDescription, newExpenseAmount);

        expenses.add(newExpense);

        saveExpenses();

        System.out.println("Expense added successfully (Expense ID:"
                + newExpense.getId() +")");
    }

    public static void updateExpense(int expenseID,
                                     String updatedNewExpenseDescription,
                                     double updatedExpenseAmount)
    {
        Expense expenseToUpdate = findExpenseById(expenseID);

        System.out.println("Expense update");

        if (expenseToUpdate != null)
        {
            expenseToUpdate.setExpenseDescription(updatedNewExpenseDescription);
            expenseToUpdate.setExpenseAmount(updatedExpenseAmount);

            saveExpenses();
            System.out.println("Expense updated successfully");
        }
        else
        {
            System.err.println("Expense not found");
        }
    }

    private static Expense findExpenseById(int expenseID)
    {
        return expenses.stream()
                .filter(expense ->
                    expense.getId() == expenseID
                )
                .findFirst()
                .orElse(null);
    }

    public static void deleteExpense(int expenseID)
    {
        Expense expenseToDelete = findExpenseById(expenseID);

        if (expenseToDelete != null)
        {
            expenses.remove(expenseToDelete);

            saveExpenses();
            System.out.println("Expense deleted successfully");
        }
        else
        {
            System.err.println("Expense not found");
        }

    }

    public static void displayAllExpenses()
    {
        System.out.println("-".repeat(50));
        System.out.println("ID | Date         | Description "
                +"          | Amount");
        for (Expense expense : expenses)
        {
            System.out.printf("%-2d | %-12s | %-21s | %.2f%n",
                    expense.getId(),
                    expense.getLocalDate(),
                    expense.getExpenseDescription(),
                    expense.getExpenseAmount());
        };
        System.out.println("-".repeat(50));

    }

    public static void displaySummary()
    {
        double totalExpense = expenses
                .stream()
                .mapToDouble(Expense::getExpenseAmount)
                .sum();

        System.out.println("Total expenses: $" + totalExpense);
    }


    public static void displaySummaryByMonth(int monthNumber)
    {
        String month = Month.of(monthNumber).toString().toLowerCase();
        String capitalizedMonth =
                month.substring(0, 1).toUpperCase() + month.substring(1);

        double totalExpenseByMonth = expenses
                .stream()
                .filter(expense ->
                        expense.getLocalDate().getMonthValue() == monthNumber
                        )
                .mapToDouble(Expense::getExpenseAmount)
                .sum();

        if (totalExpenseByMonth > 0.0)
        {
            System.out.println("Total expenses for " + month + ": $" + totalExpenseByMonth);
        }
        else
        {
            System.out.println("No expenses for " + capitalizedMonth);

        }

    }
}
