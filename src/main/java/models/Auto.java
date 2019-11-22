package models;
import javax.persistence.*;

@Entity
@Table(name = "schema.autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "model")
    private String model;

    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    @Column(name = "color")
    private String color;


    @ManyToOne(fetch = FetchType.LAZY) //аннотация @ManyToOne (многим Auto может соответствовать один User)
    @JoinColumn(name = "user_id") //аннотация @JoinColumn. Она указывает, через какой столбец в таблице autos происходит связь с таблицей users (тот самый внешний ключ, о котором мы говорили ранее)
    private User user;

    public Auto() {
    }

    public Auto(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return color + " " + model;
    }
}
