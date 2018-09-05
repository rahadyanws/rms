package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/*")
public class UserServlet extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath() + req.getPathInfo());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		HttpSession session = req.getSession(false);
		if ("/list".equalsIgnoreCase(req.getPathInfo())) {
			UserDao userDao = UserDaoImpl.getInstance();
			List<User> users = userDao.findAll();
			req.setAttribute("users", users);
			requestDispatcher.forward(req, resp);
		} else if ("/edit".equalsIgnoreCase(req.getPathInfo())) {
			if (session == null || session.getAttribute("user") == null) {
				resp.sendRedirect("/rms-servlet-web/");
			} else {
				requestDispatcher.forward(req, resp);
			}
		} else if ("/delete".equalsIgnoreCase(req.getPathInfo())) {
			if (session == null || session.getAttribute("user") == null) {
				resp.sendRedirect("/rms-servlet-web/");
			} else {
				requestDispatcher.forward(req, resp);
			}
		} else if ("/form".equalsIgnoreCase(req.getPathInfo())) {
			requestDispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath() + req.getPathInfo());
		HttpSession session = req.getSession();
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		UserDao userDao = UserDaoImpl.getInstance();
		if ("/form".equalsIgnoreCase(req.getPathInfo())) {
			String username = req.getParameter("username");
			String password = req.getParameter("userpass");
			String confPassword = req.getParameter("conf-userpass");
			String name = req.getParameter("name");
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String type = req.getParameter("type");
			String status = req.getParameter("status");
			String address = req.getParameter("address");
			Optional<User> user = userDao.findByUserName(username);
			if (user.isPresent()) {
				req.setAttribute("message", "Failed. Username already exist.");
			} else if (!password.equals(confPassword)) {
				req.setAttribute("message", "Failed. Your confirm password was different.");
			} else {
				User userSave = new User(null, username, password, name, email, gender, type, status, address);
				boolean wasSuccessful = userDao.save(userSave);
				if (wasSuccessful) {
					req.setAttribute("message", "Registration was Successful.");
				} else {
					req.setAttribute("message", "Registration Failed.");
				}
			}
			requestDispatcher.forward(req, resp);
		} else if ("/edit".equalsIgnoreCase(req.getPathInfo())) {
			User userSession = (User) session.getAttribute("user");
			String name = req.getParameter("name");
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String type = req.getParameter("type");
			String status = req.getParameter("status");
			String address = req.getParameter("address");
			
			User user = new User(userSession.getId(), userSession.getUserName(), userSession.getPassword(), name, email, gender, type, status, address);
			boolean wasSuccessful = userDao.update(user);
			if (wasSuccessful) {
				session.setAttribute("user", user);
				req.setAttribute("message", "Edit profile was Successful");
			} else {
				req.setAttribute("message", "Edit profile was Failed");
			}
			path = getTemplatePath("/dashboard");
			requestDispatcher = req.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
		} else if ("/delete".equalsIgnoreCase(req.getPathInfo())) {
			boolean isDeleteSuccess = false;
			String userpass = req.getParameter("userpass");
			String message = null;
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if (userpass.equals(user.getPassword())) {
					isDeleteSuccess = userDao.delete(user);
				} else {
					message = "Wrong Password";
				}

				if (isDeleteSuccess) {
					session.invalidate();
					path = getTemplatePath("/");
					resp.sendRedirect(path);
				} else {
					req.setAttribute("message", message);
					requestDispatcher = req.getRequestDispatcher(path);
					requestDispatcher.forward(req, resp);
				}
			}
		}	
	}
}