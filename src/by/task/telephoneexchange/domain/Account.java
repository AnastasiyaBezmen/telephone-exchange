package by.task.telephoneexchange.domain;

/**
 * Account.
 * Date: 01/19/2021
 *
 * @author Anastasiya Bezmen
 */
public class Account extends Entity {

    private TariffPlan tariffPlan;
    private Long balance;

    /**
     * Getter for tariffPlan field.
     *
     * @returns the value of the tariffPlan field
     */
    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    /**
     * Setter for tariffPlan field.
     *
     * @param tariffPlan assigns the passed argument to the tariffPlan field
     */
    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    /**
     * Getter for balance field.
     *
     * @return the value of the balance field
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * Setter for balance field.
     *
     * @param balance assigns the passed argument to the balance field
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getTariffPlan() != null ? !getTariffPlan().equals(account.getTariffPlan()) : account.getTariffPlan() != null)
            return false;
        return getBalance() != null ? getBalance().equals(account.getBalance()) : account.getBalance() == null;
    }

    @Override
    public int hashCode() {
        int result = getTariffPlan() != null ? getTariffPlan().hashCode() : 0;
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("tariffPlan='").append(tariffPlan).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
