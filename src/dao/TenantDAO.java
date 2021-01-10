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

import entity.Tenant;

public class TenantDAO {
	private Connection myConn;

	public TenantDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB Tenant connection success");
	}

	public List<Tenant> readAll() throws Exception {
		List<Tenant> list = new ArrayList<Tenant>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM tenant");
			while (myRs.next()) {
				Tenant tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Tenant> search(String name) throws Exception {
		List<Tenant> list = new ArrayList<Tenant>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM Tenant WHERE fio LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Tenant tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Tenant entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into tenant" + " (fio)" + " values (?)");
			myStmt.setString(1, entity.getFio());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Tenant> read(Long id) throws Exception {
		List<Tenant> list = new ArrayList<Tenant>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM tenant WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Tenant tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(String fioNew, Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE tenant SET fio=? WHERE id=?");
			myStmt.setString(1, fioNew);
			myStmt.setLong(2, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM tenant WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Tenant convertRowToEntity(ResultSet myRs) throws SQLException {
		String fio = myRs.getString("fio");
		Long id = myRs.getLong("id");
		Tenant temp = new Tenant(fio);
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
