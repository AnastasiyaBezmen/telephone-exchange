package by.task.telephoneexchange.domain;

/**
 * TariffPlan.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public class TariffPlan extends Entity {

    private String name;
    private Long costPerMonth = 0L;

    /**
     * Getter for name field.
     *
     * @returns the value of the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     *
     * @param name assigns the passed argument to the name field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for costPerMonth field.
     *
     * @returns the value of the costPerMonth field
     */
    public Long getCostPerMonth() {
        return costPerMonth;
    }

    /**
     * Setter for costPerMonth field.
     *
     * @param costPerMonth assigns the passed argument to the costPerMonth field
     */
    public void setCostPerMonth(Long costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TariffPlan)) return false;

        TariffPlan that = (TariffPlan) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getCostPerMonth() != null ? getCostPerMonth().equals(that.getCostPerMonth()) : that.getCostPerMonth() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCostPerMonth() != null ? getCostPerMonth().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TariffPlan{");
        sb.append("name='").append(name).append('\'');
        sb.append(", costPerMonth=").append(costPerMonth);
        sb.append('}');
        return sb.toString();
    }
}
