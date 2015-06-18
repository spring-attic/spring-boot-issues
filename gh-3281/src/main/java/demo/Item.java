package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

    private Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
