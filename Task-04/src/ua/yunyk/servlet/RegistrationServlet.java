package ua.yunyk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.yunyk.domain.User;
import ua.yunyk.domain.UserRole;
import ua.yunyk.service.UserService;
import ua.yunyk.service.impl.UserServiceImpl;

@WebServlet(asyncSupported = true, name = "registration", urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = UserServiceImpl.getUserService();
		User user = new User(request.getParameter("email"), request.getParameter("password"),
				request.getParameter("firstName"), request.getParameter("lastName"), UserRole.USER.toString());
		if (user.getFirstName() == null || user.getFirstName() == "") {
			request.setAttribute("errorMessage", "<br> First name entered wrong");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}

		if (user.getLastName() == null || user.getLastName() == "") {
			request.setAttribute("errorMessage", "<br> Last name entered wrong");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}

		if (user.getEmail() == null || user.getEmail() == "") {
			request.setAttribute("errorMessage", "<br> Email entered wrong");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}

		if (user.getPassword() == null || user.getPassword().length() < 6) {
			request.setAttribute("errorMessage", "<br> Password must have 6 or more characters");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}

		if (userService.getUserByEmail(user.getEmail()) != null) {
			request.setAttribute("errorMessage",
					"<br>There is user with this email address <br> <a href=\"login.jsp\">Log in</a>");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
			return;
		}
		user = userService.create(user);
		if (user.getId() != null) {
			request.setAttribute("firstName", user.getFirstName());
			request.getRequestDispatcher("cabinet.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage",
					"<br>Registration failed. Please check your network connection <br>");
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}

	}

}
