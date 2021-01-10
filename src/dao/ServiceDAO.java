package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import entity.Service;

public class ServiceDAO {
	private Connection myConn;

	public ServiceDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB Service connection success");
	}

	public List<Service> readAll() throws Exception {
		List<Service> list = new ArrayList<Service>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM service");
			while (myRs.next()) {
				Service tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Service> search(String name) throws Exception {
		List<Service> list = new ArrayList<Service>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM Service WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Service tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Service entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into service" + " (name)" + " values (?)");
			myStmt.setString(1, entity.getName());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Service> read(Long id) throws Exception {
		List<Service> list = new ArrayList<Service>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM service WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Service tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(String nameNew, Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE service SET name=? WHERE id=?");
			myStmt.setString(1, nameNew);
			myStmt.setLong(2, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM service WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Service convertRowToEntity(ResultSet myRs) throws SQLException {
		String fio = myRs.getString("name");
		Long id = myRs.getLong("id");
		Service temp = new Service(fio);
		temp.setId(id);
		return temp;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}
}
