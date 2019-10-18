package seedu.address.financialtracker.model.expense;

/**
 * Encapsulates amount, description and country fields into building parts of an expense.
 */
public class Expense {

    private Amount amount;
    private Description desc;
    private Country country;

    public Expense(Amount amount, Description desc, Country country) {
        this.amount = amount;
        this.desc = desc;
        this.country = country;
    }

    public Amount getAmount() {
        return amount;
    }

    public Country getCountry() {
        return country;
    }

    public Description getDesc() {
        return desc;
    }

    /**
     * Returns true if both expense have the same data fields.
     * This defines a stronger notion of equality between two expenses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return otherExpense.amount.equals(this.amount)
                && otherExpense.desc.equals(this.desc)
                && otherExpense.country.equals(this.country);
    }
}
