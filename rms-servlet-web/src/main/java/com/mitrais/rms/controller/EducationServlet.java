package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.dao.EducationDao;
import com.mitrais.rms.dao.impl.EducationDaoImpl;
import com.mitrais.rms.model.Education;
import com.mitrais.rms.model.User;

/**
 * Servlet implementation class EducationServlet
 */
@WebServlet("/education/*")
public class EducationServlet extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath() + req.getPathInfo());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		EducationDao educationDao = EducationDaoImpl.getInstance();
		if ("/list".equalsIgnoreCase(req.getPathInfo()) && session != null) {
			List<Education> educations = educationDao.findByUserId(user.getId());
			req.setAttribute("educations", educations);
			requestDispatcher.forward(req, resp);
		} else if ("/edit".equalsIgnoreCase(req.getPathInfo())) {
			if (session == null || session.getAttribute("user") == null) {
				resp.sendRedirect("/rms-servlet-web/");
			} else {
				Long educationId = Long.parseLong(req.getParameter("educationId"));
				Optional<Education> education = educationDao.find(educationId);
				req.setAttribute("education", education.get());
				requestDispatcher.forward(req, resp);
			}
		} else if ("/delete".equalsIgnoreCase(req.getPathInfo())) {
			if (session == null || session.getAttribute("user") == null) {
				resp.sendRedirect("/rms-servlet-web/");
			} else {
				req.setAttribute("educationId", req.getParameter("educationId"));
				requestDispatcher.forward(req, resp);
			}
		} else if ("/form".equalsIgnoreCase(req.getPathInfo()) && session != null) {
			requestDispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath() + req.getPathInfo());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		EducationDao educationDao = EducationDaoImpl.getInstance();
		if ("/form".equalsIgnoreCase(req.getPathInfo())) {
			String period = req.getParameter("period");
			String qualification = req.getParameter("qualification");
			String school = req.getParameter("school");
			Education education = new Education(null, user.getId(), period, qualification, school);
			boolean wasSuccessful = educationDao.save(education);
			if (wasSuccessful) {
				req.setAttribute("message", "Add Education was Successful.");
			} else {
				req.setAttribute("message", "Add Education Failed.");
			}
			requestDispatcher.forward(req, resp);
		} else if ("/edit".equalsIgnoreCase(req.getPathInfo())) {
			boolean isEditSuccess = false;
			Long educationId = Long.parseLong(req.getParameter("educationId"));
			String period = req.getParameter("period");
			String qualification = req.getParameter("qualification");
			String school = req.getParameter("school");
			
			Education education = new Education(educationId, user.getId(), period, qualification, school);
			isEditSuccess = educationDao.update(education);
			if (isEditSuccess) {
				req.setAttribute("message", "Edit education was Successful");
			} else {
				req.setAttribute("message", "Edit education was Failed");
			}
			requestDispatcher.forward(req, resp);
		} else if ("/delete".equalsIgnoreCase(req.getPathInfo())) {
			boolean isDeleteSuccess = false;
			String userpass = req.getParameter("userpass");
			String message = null;
			if (session != null && session.getAttribute("user") != null) {
				if (userpass.equals(user.getPassword())) {
					Long educationId = Long.parseLong(req.getParameter("educationId"));
					Optional<Education> education = educationDao.find(educationId);
					isDeleteSuccess = educationDao.delete(education.get());				
				} else {
					message = "Wrong Password";
				}
				
				if (isDeleteSuccess) {
					resp.sendRedirect("/rms-servlet-web/education/list");
				} else {
					req.setAttribute("message", message);
					requestDispatcher = req.getRequestDispatcher(path);
					requestDispatcher.forward(req, resp);
				}
			}
		}
	}

}
