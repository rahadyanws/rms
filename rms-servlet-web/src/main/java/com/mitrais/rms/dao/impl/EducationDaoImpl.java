package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.EducationDao;
import com.mitrais.rms.model.Education;

public class EducationDaoImpl implements EducationDao {

	@Override
	public Optional<Education> find(Long id) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM education WHERE education_id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Education education = new Education(rs.getLong("education_id"), rs.getLong("user_id"), rs.getString("period"), rs.getString("qualification"), rs.getString("school"));
				return Optional.of(education);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<Education> findAll() {
		List<Education> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM education");
			while (rs.next()) {
				Education education = new Education(rs.getLong("education_id"), rs.getLong("user_id"), rs.getString("period"), rs.getString("qualification"), rs.getString("school"));
				result.add(education);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean save(Education o) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO education VALUES (NULL, ?, ?, ?, ?)");
			stmt.setLong(1, o.getUserId());
			stmt.setString(2, o.getPeriod());
			stmt.setString(3, o.getQualification());
			stmt.setString(4, o.getSchool());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Education o) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("UPDATE education SET period=?, qualification=?, school=? WHERE education_id=?");
			stmt.setString(1, o.getPeriod());
			stmt.setString(2, o.getQualification());
			stmt.setString(3, o.getSchool());
			stmt.setLong(4, o.getEducationId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Education o) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM education WHERE education_id=?");
			stmt.setLong(1, o.getEducationId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	private static class SingletonHelper {
		private static final EducationDaoImpl INSTANCE = new EducationDaoImpl();
	}

	public static EducationDao getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public List<Education> findByUserId(Long id) {
		List<Education> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM education WHERE user_id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Education education = new Education(rs.getLong("education_id"), rs.getLong("user_id"), rs.getString("period"), rs.getString("qualification"), rs.getString("school"));
				result.add(education);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
