package by.task.telephoneexchange.domain;

/**
 * Role.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
public enum Role {
    CLIENT("клиент"),
    ADMINISTRATOR("администратор");

    private String name;

    /**
     * Constructor.
     *
     * @param name role
     */
    Role(String name) {
        this.name = name;
    }

    /**
     * Getter for name field.
     *
     * @returns the value of the name field
     */
    public String getName() {
        return name;
    }
}
