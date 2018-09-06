package com.mitrais.rms.controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Test;

import com.mitrais.rms.model.User;

public class EducationServletTest {

	@Test
	public void testServlet() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getServletPath()).thenReturn("/education");
		when(req.getPathInfo()).thenReturn("/list");
		
		User user = new User(12L, "userdemo", "12345", "User Demo", "demo@email.com", "Male", "Reguler", "Active", "Indonesia");

		
	}
}
