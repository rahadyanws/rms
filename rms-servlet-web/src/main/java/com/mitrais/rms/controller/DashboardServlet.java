package com.mitrais.rms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends AbstractController {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath());
		RequestDispatcher requestDispatcher =  req.getRequestDispatcher(path);
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			requestDispatcher.forward(req, resp);
		} else {
			path = "/login";
			req.setAttribute("message", "Please Login First");
			req.setAttribute("isFail", true);
			requestDispatcher = req.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
		}
	}
}