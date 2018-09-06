package com.mitrais.rms.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.EducationDao;
import com.mitrais.rms.model.Education;

public class EducationDaoImplTest {
	@Mock
    private DataSourceFactory dsf;
	@Mock
    private Connection c;
	@Mock
    private PreparedStatement stmt;
	
	@Test
	public void testSave() throws SQLException {
		EducationDao educationDao = mock(EducationDao.class);
		Education education = new Education(1L, 1L, "2009 - 2013", "Information System", "STMIK AKAKOM");
		when(educationDao.save(education)).thenReturn(true);	
		
		assertEquals(true, educationDao.save(education));
	}

	

}
