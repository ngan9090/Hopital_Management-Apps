package net.javaguides.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.javaguides.todoapp.DaoHibernate.DashboardH;
import net.javaguides.todoapp.DaoHibernate.Dashboardlmpl;
import net.javaguides.todoapp.DaoHibernate.TodoHibernate;
import net.javaguides.todoapp.model.Doctor;
import net.javaguides.todoapp.model.Todo;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the todo.
 * 
 * @email Ramesh Fadatare
 */

@WebServlet("/m")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoHibernate todoHibernate;

	private DashboardH dashboardH ;
	public void init() {
		//todoHibernate = new TodoHibernatelmpl();
		dashboardH = new Dashboardlmpl();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTodo(request, response);
				break;
			case "/delete":
				deleteTodo(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTodo(request, response);
				break;
			case "/list":
				listTodo(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Doctor> listTodo = dashboardH.selectAllDoctor();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoHibernate.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);

	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");

		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/

//		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
//		Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
//		todoHibernate.insertTodo(newTodo);
//		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		
		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
//		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
//
//		todoHibernate.updateTodo(updateTodo);
		
		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		todoHibernate.deleteTodo(id);
		response.sendRedirect("list");
	}
}
