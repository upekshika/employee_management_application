package com.xadmin.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.employeemanagement.bean.Employee;

public class EmployeeDao {
	
	 private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
		private String jdbcEmployeename = "root";
		private String jdbcPassword = "rootpasswordgiven";
		private String jdbcDriver = "com.mysql.jdbc.Driver";
		
		private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employees" + "  (name, nic, department_name, designation, joined_date) VALUES "
				+ " (?, ?, ?, ?, ?);";

		private static final String SELECT_EMPLOYEE_BY_ID = "select id,name,nic,designation,joined_date from employees where id =?";
		private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
		private static final String DELETE_EMPLOYEES_SQL = "delete from employees where id = ?;";
		private static final String UPDATE_EMPLOYEES_SQL = "update employees set name = ?, nic = ?, department_name = ?, joined_date = ? where id = ?;";
		
		
		public EmployeeDao() {
			
		}
		
		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(jdbcURL, jdbcEmployeename, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;

	}
		
		public void insertEmployee(Employee employee) throws SQLException {
			System.out.println(INSERT_EMPLOYEES_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
				preparedStatement.setString(1, employee.getName());
				preparedStatement.setString(2, employee.getNic());
				preparedStatement.setString(3, employee.getDepartment_name());
				preparedStatement.setString(4, employee.getDesignation());
				preparedStatement.setString(5, employee.getJoined_date());		
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public Employee selectEmployee(int id) {
			Employee employee = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String name = rs.getString("name");
					String nic = rs.getString("nic");
					String department_name = rs.getString("department_name");
					String designation = rs.getString("designation");
					String joined_date = rs.getString("joined_date");
					employee = new Employee(id, name, nic, department_name, designation, joined_date);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return employee;
		}

		public List<Employee> selectAllEmployees() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Employee> employees = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String nic = rs.getString("nic");
					String department_name = rs.getString("department_name");
					String designation = rs.getString("designation");
					String joined_date = rs.getString("joined_date");
					Employee.add(new Employee(id, name, nic, department_name, designation, joined_date));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			
			
			return employees;
		}

		public boolean deleteEmployee(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}

		public boolean updateEmployee(Employee employee) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL);) {
				System.out.println("updated Employee:"+statement);
				statement.setString(1, employee.getName());
				statement.setString(2, employee.getNic());
				statement.setString(3, employee.getDepartment_name());
				statement.setString(4, employee.getDesignation());
				statement.setString(5, employee.getJoined_date());
				statement.setInt(6, employee.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}

		
		
		
		
		
		
		
		

