package net.javaguides.todoapp.DaoHibernate;

import net.javaguides.todoapp.model.Doctor;
import net.javaguides.todoapp.model.Patient;
import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.model.User;

import java.sql.SQLException;
import java.util.List;

public interface DashboardH {

    //List<Doctors> selectAllDoctors();

    List<Doctor> selectAllDoctor();


    boolean updateDoctor(Doctor doctor) throws SQLException;

    Doctor selectDoctor(int doctorId);

    void insertDoctor(Doctor doctor) throws SQLException;

    boolean deleteDoctor(int id) throws SQLException;

    //patient
    List<Patient> selectAllPatient();

    boolean updatePatient(Patient patient) throws SQLException;

    Patient selectPatient(int patientId);

    void insertPatient(Patient patient) throws SQLException;

    boolean deletePatient(int id) throws SQLException;

}
