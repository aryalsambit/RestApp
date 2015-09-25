package uncc.sambit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uncc.sambit.exception.AppException;
import uncc.sambit.model.Employee;
import uncc.sambit.utils.DBUtil;

public class EmployeeDAO {

	public List<Employee> getAll() throws AppException {
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM employee");
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setAddress1(rs.getString("ADDRESS1"));
				emp.setAdddress2(rs.getString("ADDRESS2"));
				emp.setCity(rs.getString("CITY"));
				emp.setState(rs.getString("STATE"));
				emp.setZip(rs.getInt("ZIP"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setPhone(rs.getString("PHONE"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in retrieving from database", e.getCause());
		} finally {

			DBUtil.closeResources(con, ps, rs);
		}
		return empList;
	}

	public Employee getEmployee(int empId) throws AppException {
		Employee emp = new Employee();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM employee where id = ?");
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setAddress1(rs.getString("ADDRESS1"));
				emp.setAdddress2(rs.getString("ADDRESS2"));
				emp.setCity(rs.getString("CITY"));
				emp.setState(rs.getString("STATE"));
				emp.setZip(rs.getInt("ZIP"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setPhone(rs.getString("PHONE"));
			} else {
				throw new AppException("Employee with id " + empId + " doesnot exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in retrieving from database", e.getCause());
		} finally {

			DBUtil.closeResources(con, ps, rs);
		}
		return emp;
	}

	public Employee addEmployee(Employee emp) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("INSERT INTO employee (FIRST_NAME, LAST_NAME, EMAIL, ADDRESS1, ADDRESS2, CITY, ZIP, PHONE,STATE) VALUES (?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getAddress1());
			ps.setString(5, emp.getAdddress2());
			ps.setString(6, emp.getCity());
			ps.setInt(7, emp.getZip());
			ps.setString(8, emp.getPhone());
			ps.setString(9, emp.getState());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				emp.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in inserting from database", e.getCause());
		} finally {

			DBUtil.closeResources(con, ps, rs);
		}
		return emp;
	}

	public void deleteEmployee(int empId) throws AppException {
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("DELETE FROM employee WHERE id = ?");
			ps.setInt(1, empId);
			int returnValue = ps.executeUpdate();
			if(returnValue == 0){
				throw new AppException("Employee with id " + empId + " doesnot exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in deleting from database", e.getCause());
		} finally {
			DBUtil.closeResources(con, ps, rs);
		}
	}
}
