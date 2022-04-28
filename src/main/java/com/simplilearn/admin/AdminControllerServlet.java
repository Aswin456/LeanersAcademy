package com.simplilearn.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.simplilearn.models.Student;
import com.simplilearn.models.Subject;
import com.simplilearn.models.Teacher;
import com.simplilearn.models.Class;

/**
 * Servlet implementation class AdminControllerServlet
 */
@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DbRetrieve dbRetrieve;

	@Resource(name = "jdbc_database")
	private DataSource datasource;

	@Override
	public void init() throws ServletException {

		super.init();

		// create instance of db util, to pass in conn pool object
		try {
			dbRetrieve = new DbRetrieve();

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			// read the "command" parameter
			String command = request.getParameter("command");

			if (command == null) {
				command = "CLASSES";
			}
			
			

			else {

				// if there is no command, how to handle

				// route the data to the appropriate method
				switch (command) {

				case "STUDENTS":
					reterieveStudents(request, response);
					break;

				case "TEACHERS":
					reterieveTeachers(request, response);
					break;

				case "SUBJECTS":
					reterieveSubjects(request, response);
					break;

				case "CLASSES":
					reterieveClasses(request, response);
					break;

				case "ST_LIST":
					classStudentsList(request, response);
					break;

				case "LOGIN":
					login(request, response);
					break;

				default:
					reterieveClasses(request, response);

				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void reterieveStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students = dbRetrieve.getStudents();

		request.setAttribute("STUDENT_LIST", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}

	private void reterieveTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Teacher> teachers = dbRetrieve.getTeachers();

		request.setAttribute("TEACHERS_LIST", teachers);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers-list.jsp");
		dispatcher.forward(request, response);

	}

	private void reterieveSubjects(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Subject> subjects = dbRetrieve.getSubjects();

		request.setAttribute("SUBJECTS_LIST", subjects);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects-list.jsp");
		dispatcher.forward(request, response);

	}

	private void reterieveClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Class> classes = dbRetrieve.getClasses();

		request.setAttribute("CLASSES_LIST", classes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {

			Cookie cookie = new Cookie(username, password);

			cookie.setMaxAge(86400); 

			response.addCookie(cookie);
			reterieveClasses(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int classId = Integer.parseInt(request.getParameter("classId"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");

		List<Student> students = dbRetrieve.loadClassStudents(classId);

		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/class-students.jsp");
		dispatcher.forward(request, response);

	}
	

	

}
