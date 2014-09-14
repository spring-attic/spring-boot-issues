package org.springframework.boot.issues.gh1539.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Test model.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
public class TestModel {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
