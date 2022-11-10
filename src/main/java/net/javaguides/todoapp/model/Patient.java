package net.javaguides.todoapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="patientId", nullable=false, unique=true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="dateOfBirth")
    private String dateOfBirth;

    @Column(name="email")
    private String email;

    @Column(name="familyName")
    private String familyName;

    @Column(name="gender")
    private String gender;

    @Column(name="joiningDate")
    private String joiningDate;

    @Column(name="phone")
    private String phone;

    public Patient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Patient( Doctor doctor, String name, String address, String dateOfBirth, String email, String familyName, String gender, String joiningDate, String phone) {
        super();
        this.doctor = doctor;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.familyName = familyName;
        this.gender = gender;
        this.joiningDate = joiningDate;
        this.phone = phone;
    }

    public Patient(Integer id, Doctor doctor, String name, String address, String dateOfBirth, String email, String familyName, String gender, String joiningDate, String phone) {
        super();
        this.id = id;
        this.doctor = doctor;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.familyName = familyName;
        this.gender = gender;
        this.joiningDate = joiningDate;
        this.phone = phone;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "id=" + id +
                ", doctors=" + doctor +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", familyName='" + familyName + '\'' +
                ", gender='" + gender + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", phone='" + phone + '\'' +
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
        Patient other = (Patient) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
