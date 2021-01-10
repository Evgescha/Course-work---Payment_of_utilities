package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.ApplicationController;
import entity.Payment;
import entity.Service;
import entity.Tenant;

public class PaymentDAO {
	private Connection myConn;

	public PaymentDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB Payment connection success");
	}

	public List<Payment> readAll() throws Exception {
		List<Payment> list = new ArrayList<Payment>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM payment");
			while (myRs.next()) {
				Payment tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Payment> search(String serviceName) throws Exception {
		List<Payment> list = new ArrayList<Payment>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			serviceName = "%" + serviceName + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM payment WHERE service in (SELECT id from service where name LIKE ?)");
			myStmt.setString(1, serviceName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Payment tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Payment entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into payment"
					+ " (dates,tenant,service,summ,fine)" + " values (?,?,?,?,?)");
			myStmt.setDate(1, entity.getDates());
			myStmt.setLong(2, entity.getTenant().getId());
			myStmt.setLong(3, entity.getService().getId());
			myStmt.setFloat(4, entity.getSumm());
			myStmt.setFloat(5, entity.getFine());
			myStmt.executeUpdate();

			
		} finally {
			close(myStmt);
		}
	}

	public List<Payment> read(Long id) throws Exception {
		List<Payment> list = new ArrayList<Payment>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM payment WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Payment tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Payment entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE payment SET dates=?, tenant=?, service=?, summ=?, fine=?  WHERE id=?");
			myStmt.setDate(1, entity.getDates());
			myStmt.setLong(2, entity.getTenant().getId());
			myStmt.setLong(3, entity.getService().getId());
			myStmt.setFloat(4, entity.getSumm());
			myStmt.setFloat(5, entity.getFine());
			myStmt.setLong(6, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {

			myStmt = myConn.prepareStatement("DELETE FROM payment WHERE id=?");

			myStmt.setLong(1, id);
			myStmt.executeUpdate();			
		} finally {
			close(myStmt);
		}
	}

	private Payment convertRowToEntity(ResultSet myRs) throws SQLException {
		Tenant tenant = null;
		Service service = null;
		
		Payment temp = null;
		try {
			Long id = myRs.getLong("id");
			Date dates= myRs.getDate("dates");
			Long id_tenant = myRs.getLong("tenant");
			Long id_service = myRs.getLong("service");
			Float summ = myRs.getFloat("summ");
			Float fine= myRs.getFloat("fine");

			tenant = ApplicationController.tenantController.getDAO().read(id_tenant).get(0);
			service = ApplicationController.serviceController.getDAO().read(id_service).get(0);

			temp = new Payment(dates,tenant,service,summ,fine);
			temp.setId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
