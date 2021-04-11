package by.task.telephoneexchange.domain;

/**
 * Address.
 * Date: 01/19/2021
 *
 * @author Anastasiya Bezmen
 */
public class Address extends Entity {

    private String city;
    private String street;
    private Integer houseNumber;
    private Integer block;
    private Integer flat;

    /**
     * Getter for city field.
     *
     * @returns the value of the city field
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for city field.
     *
     * @param city assigns the passed argument to the city field
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for street field.
     *
     * @returns the value of the street field
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for street field.
     *
     * @param street assigns the passed argument to the street field
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter for houseNumber field.
     *
     * @returns the value of the houseNumber field
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Setter for houseNumber field.
     *
     * @param houseNumber assigns the passed argument to the houseNumber field
     */
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Getter for block field.
     *
     * @returns the value of the block field
     */
    public Integer getBlock() {
        return block;
    }

    /**
     * Setter for block field.
     *
     * @param block assigns the passed argument to the block field
     */
    public void setBlock(Integer block) {
        this.block = block;
    }

    /**
     * Getter for flat field.
     *
     * @returns the value of the block field
     */
    public Integer getFlat() {
        return flat;
    }

    /**
     * Setter for flat field.
     *
     * @param flat assigns the passed argument to the flat field
     */
    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!getCity().equals(address.getCity())) return false;
        if (!getStreet().equals(address.getStreet())) return false;
        if (!getHouseNumber().equals(address.getHouseNumber())) return false;
        if (!getBlock().equals(address.getBlock())) return false;
        return getFlat().equals(address.getFlat());
    }

    @Override
    public int hashCode() {
        int result = getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getHouseNumber().hashCode();
        result = 31 * result + getBlock().hashCode();
        result = 31 * result + getFlat().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber=").append(houseNumber);
        sb.append(", block=").append(block);
        sb.append(", flat=").append(flat);
        sb.append('}');
        return sb.toString();
    }
}
