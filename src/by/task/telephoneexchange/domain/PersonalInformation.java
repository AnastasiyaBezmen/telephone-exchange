package by.task.telephoneexchange.domain;

/**
 * PersonalInformation.
 * Date: 01/20/2021
 *
 * @author Anastasiya Bezmen
 */
public class PersonalInformation extends Entity {

    private String firstName;
    private String lastName;
    private String patronymicName;
    private String phoneNumber;
    private Address address;

    /**
     * Getter for firstName field.
     *
     * @returns the value of the firstName field
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName field.
     *
     * @param firstName assigns the passed argument to the firstName field
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName field.
     *
     * @returns the value of the lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName field.
     *
     * @param lastName assigns the passed argument to the lastName field
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for patronymicName field.
     *
     * @returns the value of the patronymicName field
     */
    public String getPatronymicName() {
        return patronymicName;
    }

    /**
     * Setter for patronymicName field.
     *
     * @param patronymicName assigns the passed argument to the patronymicName field
     */
    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    /**
     * Getter for phoneNumber field.
     *
     * @returns the value of the phoneNumber field
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for phoneNumber field.
     *
     * @param phoneNumber assigns the passed argument to the phoneNumber field
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for address field.
     *
     * @returns the value of the address field
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for address field.
     *
     * @param address assigns the passed argument to the address field
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalInformation)) return false;

        PersonalInformation that = (PersonalInformation) o;

        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getPatronymicName() != null ? !getPatronymicName().equals(that.getPatronymicName()) : that.getPatronymicName() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() != null)
            return false;
        return getAddress() != null ? getAddress().equals(that.getAddress()) : that.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPatronymicName() != null ? getPatronymicName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonalInformation{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", patronymicName='").append(patronymicName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
