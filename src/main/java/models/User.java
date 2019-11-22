package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //Главная для нас аннотация — @Entity (сущность).  Эта аннотация позволяет Java-объектам вашего класса быть связанными с БД
@Table (name = "schema.users") //Аннотация @Table над классом указывает как называется таблица, в которую записываются объекты.
public class User {
    @Id //@Id — это указание, что поле является идентификатором объектов этого класса.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //есть несколько стратегий генерации ID. Можете погуглить, но в рамках нашего приложения  id для наших объектов будут генерироваться автоматически, поэтому для id отсутствует сеттер, и в конструкторе мы его тоже не задаем
    private int id;
    @Column(name = "name")
    private String name;
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    @Column(name = "age")
    private int age;

    /*
    аннотация @OneToMany. Она означает, что одному объекту класса user может соответствовать несколько машин.
    Настройка "mappedBY" указывает на поле user класса Auto.
    Таким образом машины и пользователи связаны между с собой.
    Настройка orphanRemoval вполне хорошо переводится с английского — "удалять сирот".
    Если мы удалим юзера из БД — все связанные с ним автомобили также будут удалены.
    В свою очередь в классе Auto вы увидите поле user с аннотацией @ManyToOne (многим Auto может соответствовать один User) и аннотацию @JoinColumn.
    Она указывает, через какой столбец в таблице autos происходит связь с таблицей users (тот самый внешний ключ, о котором мы говорили ранее).
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auto> autos;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        autos = new ArrayList<>();
    }

    public void addAuto(Auto auto) {
        auto.setUser(this);
        autos.add(auto);
    }

    public void removeAuto(Auto auto) {
        autos.remove(auto);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
/*
Чтобы класс мог быть сущностью @Entity, к нему предъявляются следующие требования:
Должен иметь пустой конструктор (public или protected);
Не может быть вложенным, интерфейсом или enum;
Не может быть final и не может содержать final-полей/свойств;
Должен содержать хотя бы одно @Id-поле.
Проверьте ваши entity-классы, эти пункты — очень популярное место для "выстрела себе в ногу". Очень легко что-нибудь забыть.

При этом entity может:
Содержать непустые конструкторы;
Наследоваться и быть наследованным;
Содержать другие методы и реализовывать интерфейсы.



Как видите, класс User во многом похож на таблицу users. Есть поля id, name, age.
Расположенные над ними аннотации в пояснениях особо не нуждаются: и так понятно, что @Id — это указание, что поле является идентификатором объектов этого класса.
Аннотация @Table над классом указывает как называется таблица, в которую записываются объекты.
Обратите внимание на комментарий над полем age: если имя поля в классе и таблице совпадает — можно не добавлять аннотацию @Column, будет работать и так.
Что касаемо указанного в скобках "strategy = GenerationType.IDENTITY": есть несколько стратегий генерации ID.
Можете погуглить, но в рамках нашего приложения можно не заморачиваться.
Главное, что id для наших объектов будут генерироваться автоматически, поэтому для id отсутствует сеттер, и в конструкторе мы его тоже не задаем.
Однако, кое-чем класс User все-таки выделяется.
У него есть список машин!

Над списком висит аннотация @OneToMany. Она означает, что одному объекту класса user может соответствовать несколько машин.
Настройка "mappedBY" указывает на поле user класса Auto.
Таким образом машины и пользователи связаны между с собой.
Настройка orphanRemoval вполне хорошо переводится с английского — "удалять сирот".
Если мы удалим юзера из БД — все связанные с ним автомобили также будут удалены.
В свою очередь в классе Auto вы увидите поле user с аннотацией @ManyToOne (многим Auto может соответствовать один User) и аннотацию @JoinColumn.
Она указывает, через какой столбец в таблице autos происходит связь с таблицей users (тот самый внешний ключ, о котором мы говорили ранее).


 */