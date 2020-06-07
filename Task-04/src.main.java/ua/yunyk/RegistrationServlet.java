package ua.yunyk;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(asyncSupported = true, name = "registration", urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sql = "INSERT INTO users (`firstName`,`lastName`,`email`,`password`) VALUES(?,?,?,?)";
	private String sql1 = "SELECT * FROM users WHERE email=?";
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Registration.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(firstName == null || firstName == "") {
			request.setAttribute("errorMessage", "<br> First name entered wrong");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		
		if(lastName == null || lastName == "") {
			request.setAttribute("errorMessage", "<br> Last name entered wrong");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		
		if(email == null || email == "") {
			request.setAttribute("errorMessage", "<br> Email entered wrong");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		
		if(password == null || password.length() < 6) {
			request.setAttribute("errorMessage", "<br> Password must have 6 or more characters");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/internetstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1,email);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				request.setAttribute("errorMessage", "<br>There is user with this email address <br> <a href=\"login.jsp\">Log in</a>");
				request.setAttribute("firstName", firstName);
				request.setAttribute("lastName", lastName);
				request.setAttribute("email", email);
				request.getRequestDispatcher("Registration.jsp").forward(request, response);
				return;
			}
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, firstName);
			prepareStatement.setString(2, lastName);
			prepareStatement.setString(3, email);
			prepareStatement.setString(4, password);
			int flag = prepareStatement.executeUpdate();
			
			if(flag == 1) {
				request.setAttribute("firstName", firstName);
				request.getRequestDispatcher("cabinet.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("Registration.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
