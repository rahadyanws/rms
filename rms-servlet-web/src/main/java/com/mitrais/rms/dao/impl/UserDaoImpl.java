package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class UserDaoImpl implements UserDao {
	@Override
	public Optional<User> find(Long i) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE id=?");
			stmt.setLong(1, i);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("password"),
						rs.getString("name"), rs.getString("gender"), rs.getString("email"), rs.getString("type"), rs.getString("status"), rs.getString("address"));
				return Optional.of(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		List<User> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			while (rs.next()) {
				User user = new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("password"),
						rs.getString("name"), rs.getString("gender"), rs.getString("email"), rs.getString("type"), rs.getString("status"), rs.getString("address"));
				result.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean save(User user) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getGender());
			stmt.setString(6, user.getType());
			stmt.setString(7, user.getStatus());
			stmt.setString(8, user.getAddress());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("UPDATE user SET user_name=?, password=? , name=?, gender=?, email=?, type=?, status=?, address=? WHERE id=?");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getType());
			stmt.setString(7, user.getStatus());
			stmt.setString(8, user.getAddress());
			stmt.setLong(9, user.getId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(User user) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id=?");
			stmt.setLong(1, user.getId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE user_name=?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("password"),
						rs.getString("name"), rs.getString("email"), rs.getString("gender"), 
						rs.getString("type"), rs.getString("status"),  rs.getString("address"));
				return Optional.of(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	private static class SingletonHelper {
		private static final UserDaoImpl INSTANCE = new UserDaoImpl();
	}

	public static UserDao getInstance() {
		return SingletonHelper.INSTANCE;
	}
}