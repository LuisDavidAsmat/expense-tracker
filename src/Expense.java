import java.time.LocalDate;

public class Expense
{
    private static int idCounter = 1;
    private int id;
    private LocalDate localDate;
    private String expenseDescription;
    private double expenseAmount;

    public Expense(String expenseDescription, double expenseAmount)
    {
        this.id = idCounter++;
        this.localDate = LocalDate.now();
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public static void setIdCounter(int idCounter)
    {
        Expense.idCounter = idCounter;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getExpenseDescription()
    {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription)
    {
        this.expenseDescription = expenseDescription;
    }

    public double getExpenseAmount()
    {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount)
    {
        this.expenseAmount = expenseAmount;
    }
}
