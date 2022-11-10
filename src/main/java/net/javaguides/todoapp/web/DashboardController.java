package net.javaguides.todoapp.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import net.javaguides.todoapp.DaoHibernate.DashboardH;
import net.javaguides.todoapp.DaoHibernate.Dashboardlmpl;
import net.javaguides.todoapp.model.Doctor;
import net.javaguides.todoapp.model.Patient;
import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.model.User;

import javax.print.Doc;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class DashboardController extends HttpServlet {
    private final long serialVersionUID = 1L;
    private DashboardH dashboardH ;

    public void init() {
        dashboardH = new Dashboardlmpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showDoctorForm(request, response);
                    break;
                case "/insert":
                    insertDoctor(request, response);
                    break;
                case "/update":
                    updateDoctor(request, response);
                    break;
                case "/sum":
                    sumAll(request, response);
                    break;
                case "/doctorList":
                    doctorList(request, response);
                    break;
                case "/patientList":
                    patientList(request, response);
                    break;
                case "/delete":
                    deleteDoctor(request, response);
                    break;
                case "/edit":
                    showFormDoctor(request, response);
                    break;
                //Patient
                case "/newPatient":
                    showPatientForm(request, response);
                    break;
                case "/insertPatient":
                    insertPatient(request, response);
                    break;
                case "/updatePatient":
                    updatePatient(request, response);
                    break;
                case "/deletePatient":
                    deletePatient(request, response);
                    break;
                case "/editPatient":
                    showFormPatient(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    System.out.println("mot");
                    //response.sendRedirect("sum");
                    System.out.println("hai");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void sumAll(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        BigInteger sumDoctors =  Dashboardlmpl.countDoctors();
        request.setAttribute("sumDoctors", sumDoctors);
        BigInteger sumPatients =  Dashboardlmpl.countPatients();
        request.setAttribute("sumPatients", sumPatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard/dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void doctorList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Doctor> doctorList = dashboardH.selectAllDoctor();
        request.setAttribute("doctorList", doctorList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctorPage/doctor-list.jsp");
        dispatcher.forward(request, response);
    }

    private void patientList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Patient> patientList = dashboardH.selectAllPatient();
        request.setAttribute("patientList", patientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patientPage/patientList.jsp");
        dispatcher.forward(request, response);
    }

    private void showDoctorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctorPage/doctorForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String position = request.getParameter("position");
        String name = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("name");
        System.out.println("noi dung");
        System.out.println(position);
		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/

        //boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Doctor newDoctor = new Doctor(position, address, dateOfBirth, phone, email, name);
        System.out.println(newDoctor);
        dashboardH.insertDoctor(newDoctor);
        System.out.println("final");
        response.sendRedirect("doctorList");
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String position = request.getParameter("position");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");

        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        Doctor updateDoctor = new Doctor(id, position, address, dateOfBirth, email, phone, name);

        dashboardH.updateDoctor(updateDoctor);

        response.sendRedirect("doctorList");
    }

    private void showFormDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor existingDoctor = dashboardH.selectDoctor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctorPage/doctorForm.jsp");
        request.setAttribute("doctor", existingDoctor);
        dispatcher.forward(request, response);

    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dashboardH.deleteDoctor(id);
        response.sendRedirect("doctorList");
    }

    //Patient
    private void showPatientForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("patientPage/patientForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        Doctor doctor = dashboardH.selectDoctor(doctorId);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String familyName = request.getParameter("familyName");
        String gender = request.getParameter("gender");
        String joiningDate = request.getParameter("joiningDate");
        String phone = request.getParameter("phone");
        System.out.println("noi dung");
        System.out.println(name);
		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/

        //boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Patient newPatient = new Patient(doctor,name, address, dateOfBirth, email, familyName, gender, joiningDate, phone);
        System.out.println(newPatient);
        dashboardH.insertPatient(newPatient);
        System.out.println("final");
        response.sendRedirect("patientList");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        Doctor doctor = dashboardH.selectDoctor(doctorId);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String familyName = request.getParameter("familyName");
        String gender = request.getParameter("gender");
        String joiningDate = request.getParameter("joiningDate");
        String phone = request.getParameter("phone");

        Patient updatePatient = new Patient(id, doctor, name, address, dateOfBirth, email, familyName, gender, joiningDate, phone);

        dashboardH.updatePatient(updatePatient);

        response.sendRedirect("patientList");
    }

    private void showFormPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Patient existingPatient = dashboardH.selectPatient(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patientPage/patientForm.jsp");
        request.setAttribute("patient", existingPatient);
        dispatcher.forward(request, response);

    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dashboardH.deletePatient(id);
        response.sendRedirect("patientList");
    }
}
