import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.err.println("No command provided");
        }

        String starterCommand = args[0];

        switch (starterCommand)
        {
            case "add":
            {
                manageAddCommand(args);
                break;
            }
            case "update":
            {
                manageUpdateCommand(args);
                break;
            }
            case "delete":
            {
                manageDeleteCommand(args);
                break;
            }
            case "list":
            {
                ExpenseTracker.displayAllExpenses();
                break;
            }
            case "summary":
            {
                manageSummaryCommand(args);
                break;
            }
            default:
            {
                System.out.println("Not a command");
            }
        }
    }

    private static void manageSummaryCommand(String[] args)
    {
        if (args.length > 1 && args[1].equals("--month"))
        {
            try
            {
                int expenseMonth = Integer.parseInt(args[2]);

                if (expenseMonth < 1 || expenseMonth > 12)
                {
                    throw new IllegalArgumentException("Invalid month number. Enter a value between 1 and 12");
                }

                ExpenseTracker.displaySummaryByMonth(expenseMonth);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(
                        "Invalid month. Enter month number from 1 to 12"
                );
            }

        }
        else
        {
            ExpenseTracker.displaySummary();
        }
    }

    private static void manageDeleteCommand(String[] args)
    {
        if (args.length > 1 && args[1].equals("--id"))
        {
            try
            {
                int expenseId = Integer.parseInt(args[2]);

                if (expenseId < 0.0)
                {
                    throw new IllegalArgumentException("Expense ID must be positive.");
                }

                ExpenseTracker.deleteExpense(expenseId);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(
                        "Invalid ID. ID is required and should be a positive number."
                );
            }
        }
        else
        {
            throw new IllegalArgumentException(
                    "Invalid delete command. Please see recommended usage"
            );
        }

    }

    private static void manageUpdateCommand(String[] args)
    {
        Map<String, String> updateParams = parseArgs(args);

        if (!updateParams.containsKey("id")
                || !updateParams.containsKey("description")
                || !updateParams.containsKey("amount"))
        {
            throw new IllegalArgumentException(
                    "Missing required arguments for updating operation");
        }

        int expenseId = 0;

        try
        {
            expenseId = Integer.parseInt(updateParams.get("id"));

            if (expenseId < 0.0)
            {
                throw new IllegalArgumentException("Expense ID must be positive.");
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid ID.");
        }

        String updatedExpenseDescription = updateParams.get("description");

        double updatedExpenseAmount = 0.0;

        try
        {
            updatedExpenseAmount = Integer.parseInt(updateParams.get("amount"));

            if (updatedExpenseAmount < 0.0)
            {
                throw new IllegalArgumentException("Amount must be positive.");
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid amount.");
        }

        ExpenseTracker.updateExpense(expenseId,
                updatedExpenseDescription, updatedExpenseAmount);
    }

    private static void manageAddCommand(String[] args)
    {
        Map<String, String> addParams = parseArgs(args);

        if (!addParams.containsKey("description") || !addParams.containsKey("amount"))
        {
            throw new IllegalArgumentException(
                    "Missing required arguments for adding operation");
        }

        String newExpenseDescription = addParams.get("description");

        double newExpenseAmount = 0.0;

        try
        {
            newExpenseAmount = Double.parseDouble(addParams.get("amount"));

            if (newExpenseAmount < 0.0)
            {
                throw new IllegalArgumentException("Amount must be positive.");
            }
        }
        catch (NumberFormatException e )
        {
            throw new IllegalArgumentException(
                    "Invalid amount. Please enter a proper amount"
            );
        }

        ExpenseTracker.addExpense(newExpenseDescription, newExpenseAmount);
    }

    private static Map<String, String> parseArgs(String[] args)
    {
        Map<String, String> params = new HashMap<>();

        String key = null;

        for (String arg : args)
        {
            if (arg.startsWith("--"))
            {
                key = arg.substring(2);
            }
            else if (key != null)
            {
                params.put(key, arg);

                key = null;
            }
        }

        return params;
    }
}
