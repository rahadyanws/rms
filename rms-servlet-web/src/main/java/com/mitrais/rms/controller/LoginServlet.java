package com.mitrais.rms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		HttpSession session = req.getSession();
		if(session!=null && session.getAttribute("user") != null) {
			resp.sendRedirect("/rms-servlet-web/dashboard");
		} else {
			session.invalidate();
			requestDispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = null;
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);

		String username = req.getParameter("username");
		String userpass = req.getParameter("userpass");
		UserDao userDao = UserDaoImpl.getInstance();
		Optional<User> user = userDao.findByUserName(username);
		
		if (user.isPresent()) {
			if (userpass.equals(user.get().getPassword())) {
				path = "/rms-servlet-web/dashboard";
				HttpSession session = req.getSession();
				session.setAttribute("user", user.get());
				resp.sendRedirect(path);
			} else {
				path = getTemplatePath(req.getServletPath());
				req.setAttribute("message", "Wrong Password");
				req.setAttribute("isFail", true);
				requestDispatcher.forward(req, resp);
			}
		} else {
			path = getTemplatePath(req.getServletPath());
			req.setAttribute("message", "Username not found");
			req.setAttribute("isFail", true);
			requestDispatcher.forward(req, resp);
		}
	}

}