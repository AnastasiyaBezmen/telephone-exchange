package by.task.telephoneexchange.domain;

/**
 * User.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
public class User extends Entity {

    private String login;
    private String password;
    private Role role;
    private PersonalInformation personalInformation;
    private Account account;
    private Boolean blocked = false;

    /**
     * Getter for login field.
     *
     * @returns the value of the login field
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login field.
     *
     * @param login assigns the passed argument to the login field
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for password field.
     *
     * @returns the value of the password field
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password field.
     *
     * @param password assigns the passed argument to the password field
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for role field.
     *
     * @returns the value of the role field
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter for role field.
     *
     * @param role assigns the passed argument to the role field
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Getter for personalInformation field.
     *
     * @returns the value of the personalInformation field
     */
    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    /**
     * Setter for personalInformation field.
     *
     * @param personalInformation assigns the passed argument to the personalInformation field
     */
    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    /**
     * Getter for account field.
     *
     * @returns the value of the account field
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Setter for account field.
     *
     * @param account assigns the passed argument to the account field
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Getter for blocked field.
     *
     * @returns the value of the blocked field
     */
    public Boolean getBlocked() {
        return blocked;
    }

    /**
     * Setter for blocked field.
     *
     * @param blocked assigns the passed argument to the blocked field
     */
    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getRole() != user.getRole()) return false;
        if (getPersonalInformation() != null ? !getPersonalInformation().equals(user.getPersonalInformation()) : user.getPersonalInformation() != null)
            return false;
        if (getAccount() != null ? !getAccount().equals(user.getAccount()) : user.getAccount() != null) return false;
        return getBlocked() != null ? getBlocked().equals(user.getBlocked()) : user.getBlocked() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getPersonalInformation() != null ? getPersonalInformation().hashCode() : 0);
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        result = 31 * result + (getBlocked() != null ? getBlocked().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", personalInformation=").append(personalInformation);
        sb.append(", account=").append(account);
        sb.append(", blocked=").append(blocked);
        sb.append('}');
        return sb.toString();
    }
}
