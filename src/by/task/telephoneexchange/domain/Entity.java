package by.task.telephoneexchange.domain;

import java.io.Serializable;

/**
 * Entity.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
abstract public class Entity implements Serializable {

    private String id;

    /**
     * Getter for id field.
     *
     * @returns the value of the id field
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id field.
     *
     * @param id assigns the passed argument to the id field
     */
    public void setId(String id) {
        this.id = id;
    }
}
