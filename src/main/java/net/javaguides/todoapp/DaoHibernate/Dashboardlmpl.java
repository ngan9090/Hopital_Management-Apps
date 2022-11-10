package net.javaguides.todoapp.DaoHibernate;

import net.javaguides.todoapp.model.Doctor;
import net.javaguides.todoapp.model.Patient;
import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.model.User;
import net.javaguides.todoapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dashboardlmpl  implements DashboardH{
     Session session;

     Transaction transaction = null;
    private static final String COUNT_PATIENTS = "SELECT COUNT(*) FROM patients;";
    private static final String COUNT_DOCTORS = "SELECT count(*)  FROM doctors;";

    private static final String DELETE_DOCTOR_BY_ID = "delete from doctors where doctorId = :doctorId";

    private static final String DELETE_PATIENT_BY_ID = "delete from patients where patientId = :patientId";

    private static final String ALL_DOCTORS = "SELECT *  FROM doctors;";
    private static final String ALL_PATIENTS = "SELECT * from patients p, doctors d WHERE p.doctorId = d.doctorId ";

    private static final String INSERT_DOCTOR = "INSERT INTO doctors"
            + "  (position, name, address, dateOfBirth, email, phone) VALUES " + " (:position, :name, :address, :dateOfBirth, :email, :phone);";
    private static final String INSERT_PATIENT = "INSERT INTO patients"
            + "  (doctorId, name, address, dateOfBirth, email,familyName, gender, joiningDate, phone) VALUES " + " (:doctorId, :name, :address, :dateOfBirth, :email, :familyName, :gender, :joiningDate, :phone);";
    private static final String UPDATE_DOCTOR = "update doctors set position = :position, name= :name, address = :address, dateOfBirth = :dateOfBirth, email = :email, phone = :phone where doctorId = :doctorId";
    private static final String UPDATE_PATIENT = "update patients set doctorId = :doctorId, name = :name, address = :address, dateOfBirth = :dateOfBirth, email = :email, familyName = :familyName, gender = :gender, joiningDate = :joiningDate, phone = :phone where patientId = :patientId";

    //@Override
    public static BigInteger countDoctors() {
        //int countDoctors = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query sumQuery = session.createSQLQuery(COUNT_DOCTORS);
        System.out.println("Sum of all salary : " + sumQuery.getSingleResult());
        return (BigInteger)sumQuery.getSingleResult();
    }

    public static BigInteger countPatients() {
        //int countDoctors = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query sumQuery = session.createSQLQuery(COUNT_PATIENTS);
        System.out.println("Sum of all salary : " + sumQuery.getSingleResult());
        return (BigInteger)sumQuery.getSingleResult();
    }

    @Override
    public List<Doctor> selectAllDoctor() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Doctor> criteriaQuery = builder.createQuery(Doctor.class);
            Root<Doctor> root = criteriaQuery.from(Doctor.class);
            doctors = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) throws SQLException {
        boolean rowUpdated;
        rowUpdated = false;
        try{
            Query q = session.createSQLQuery(UPDATE_DOCTOR);
            q.setParameter("position",doctor.getPosition() );
            q.setParameter("name",doctor.getName() );
            q.setParameter("address",doctor.getAddress() );
            q.setParameter("dateOfBirth",doctor.getDateOfBirth());
            q.setParameter("email",doctor.getEmail() );
            q.setParameter("phone",doctor.getPhone() );
            q.setParameter("doctorId",doctor.getId() );
            Doctor newDoctor = (Doctor)q.getSingleResult();
            System.out.println(newDoctor);
            System.out.println(doctor.getName());
            newDoctor.setPosition(doctor.getPosition());
            newDoctor.setName(doctor.getName());
            newDoctor.setAddress(doctor.getAddress());
            newDoctor.setDateOfBirth(doctor.getDateOfBirth());
            newDoctor.setEmail(doctor.getEmail());
            newDoctor.setPhone(doctor.getPhone());
            session.update(newDoctor);
            rowUpdated = q.executeUpdate() > 0;
            transaction.commit();}
        catch(Exception e){
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public void insertDoctor(Doctor doctor) throws SQLException {
        System.out.println(INSERT_DOCTOR);
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.save(doctor);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteDoctor(int id) throws SQLException {
        boolean rowDeleted;
        rowDeleted = false;
        try {
            List<Doctor> doctors = session.createSQLQuery(DELETE_DOCTOR_BY_ID)
                    .setParameter("doctorId", id)
                    .list();
            for (Doctor doctor : doctors) {
                session.delete(doctor);
            }
            session.delete(id);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public Doctor selectDoctor(int doctorId) {
        Doctor doctor = null;
        // Step 1: Establishing a Connection
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Doctor> criteriaQuery = builder.createQuery(Doctor.class);
            Root<Doctor> rootSelectDoctor = criteriaQuery.from(Doctor.class);
            criteriaQuery.select(rootSelectDoctor).where(builder.equal(rootSelectDoctor.get("id"), doctorId));
            doctor = session.createQuery(criteriaQuery).getSingleResult();
            Doctor newDoctor = new Doctor();
            newDoctor.setPosition(doctor.getPosition());
            newDoctor.setName(doctor.getName());
            newDoctor.setAddress(doctor.getAddress());
            newDoctor.setDateOfBirth(doctor.getDateOfBirth());
            newDoctor.setPhone(doctor.getPhone());
            newDoctor.setEmail(doctor.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    //Patient
    @Override
    public List<Patient> selectAllPatient() {
        List<Patient> patients = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Patient> criteriaQuery = builder.createQuery(Patient.class);
            Root<Patient> root = criteriaQuery.from(Patient.class);
            patients = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public boolean updatePatient(Patient patient) throws SQLException {
        boolean rowUpdated;
            rowUpdated = false;
                try {
                    Query q = session.createSQLQuery(UPDATE_PATIENT);
                    q.setParameter("doctorId", patient.getDoctor().getId());
                    q.setParameter("name", patient.getName());
                    q.setParameter("address", patient.getAddress());
                    q.setParameter("dateOfBirth", patient.getDateOfBirth());
                    q.setParameter("email", patient.getEmail());
                    q.setParameter("familyName", patient.getFamilyName());
                    q.setParameter("gender", patient.getGender());
                    q.setParameter("joiningDate", patient.getJoiningDate());
                    q.setParameter("phone", patient.getPhone());
                    q.setParameter("patientId", patient.getId());
                    Patient newPatient = (Patient) q.getSingleResult();
                    System.out.println(newPatient);
                    System.out.println(patient.getName());
                    newPatient.setDoctor(patient.getDoctor());
                    newPatient.setName(patient.getName());
                    newPatient.setAddress(patient.getAddress());
                    newPatient.setDateOfBirth(patient.getDateOfBirth());
                    newPatient.setEmail(patient.getEmail());
                    newPatient.setFamilyName(patient.getFamilyName());
                    newPatient.setGender(patient.getGender());
                    newPatient.setJoiningDate(patient.getJoiningDate());
                    newPatient.setPhone(patient.getPhone());
                    session.update(newPatient);
                    rowUpdated = q.executeUpdate() > 0;
                    transaction.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }

        return rowUpdated;
    }

    @Override
    public void insertPatient(Patient patient) throws SQLException {
        System.out.println(INSERT_PATIENT);
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.save(patient);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletePatient(int id) throws SQLException {
        boolean rowDeleted;
        rowDeleted = false;
        try {
            List<Patient> patients = session.createSQLQuery(DELETE_PATIENT_BY_ID)
                    .setParameter("patientId", id)
                    .list();
            for (Patient patient : patients) {
                session.delete(patient);
            }
            session.delete(id);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public Patient selectPatient(int patientId) {
        Patient patient = null;
        // Step 1: Establishing a Connection
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Patient> criteriaQuery = builder.createQuery(Patient.class);
            Root<Patient> rootSelectPatient = criteriaQuery.from(Patient.class);
            criteriaQuery.select(rootSelectPatient).where(builder.equal(rootSelectPatient.get("id"), patientId));
            patient = session.createQuery(criteriaQuery).getSingleResult();
            Patient newPatient = new Patient();
            newPatient.setDoctor(patient.getDoctor());
            newPatient.setName(patient.getName());
            newPatient.setAddress(patient.getAddress());
            newPatient.setDateOfBirth(patient.getDateOfBirth());
            newPatient.setEmail(patient.getEmail());
            newPatient.setFamilyName(patient.getFamilyName());
            newPatient.setGender(patient.getGender());
            newPatient.setJoiningDate(patient.getJoiningDate());
            newPatient.setPhone(patient.getPhone());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }


}
