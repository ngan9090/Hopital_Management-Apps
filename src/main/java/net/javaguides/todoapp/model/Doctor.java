package net.javaguides.todoapp.model;

import javax.persistence.*;

import java.time.LocalDate;


/**
 * Todo.java
 * This is a model class represents a Todo entity
 * @author Ramesh Fadatare
 *
 */


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="doctorId", nullable=false, unique=true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="position")
    private String position;

    @Column(name="address")
    private String address;

    @Column(name="dateOfBirth")
    private String dateOfBirth;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doctor() {
    }

    public Doctor(String position, String address, String dateOfBirth, String email, String phone, String name) {
        super();
        this.position = position;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    public Doctor(Integer id, String position, String address, String dateOfBirth, String email, String phone, String name) {
        super();
        this.id = id;
        this.position = position;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    @Override
    public String toString() {
        return "doctors{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doctor other = (Doctor) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }


}

