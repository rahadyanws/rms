package com.mitrais.rms.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mitrais.rms.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

	@Mock
    private DataSource ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;
    
    private User u;
    
    @Before
    public void setUp() throws Exception {
		assertNotNull(ds);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(ds.getConnection()).thenReturn(c);
		
		u = new User();
		u.setId(1L);
		u.setUserName("Dragon");
		u.setPassword("12345");
		u.setName("Dragon Night");
		u.setEmail("dragon@email.com");
		u.setGender("Male");
		u.setType("Reguler");
		u.setStatus("Active");
		u.setAddress("Indonesia");
		
		when(rs.first()).thenReturn(true);
		when(rs.getLong(1)).thenReturn(u.getId());
		when(rs.getString(2)).thenReturn(u.getUserName());
		when(rs.getString(3)).thenReturn(u.getPassword());
		when(rs.getString(4)).thenReturn(u.getName());
		when(rs.getString(5)).thenReturn(u.getEmail());
		when(rs.getString(6)).thenReturn(u.getGender());
		when(rs.getString(7)).thenReturn(u.getType());
		when(rs.getString(8)).thenReturn(u.getStatus());
		when(rs.getString(9)).thenReturn(u.getAddress());
		
		when(stmt.executeQuery()).thenReturn(rs);
	}
    
    @Test
    public void createAndRetrieveUser() throws Exception {
      
    }

}
