package ru.ramazanov.CRUD_User_App.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min=1, max=100, message = "Имя долэно быть больше 0 и меньше 100 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String name;

    @Column(name = "last_name")
    @Size(min=1, max=100, message = "Фамилия должна быть больше 0 и меньше 100 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String lastName;

    @Column(name = "age")
    @Min(value = 1, message = "Возраст может быть только больше 0!")
    private int age;

    @Column(name = "email")
    @Email(message = "Не верно указан email!")
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min=1, max=100, message = "Email должна быть больше 0 и меньше 100 символов!")
    private String email;

    public User() {

    }

    public User(String name, String lastName, int age, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
