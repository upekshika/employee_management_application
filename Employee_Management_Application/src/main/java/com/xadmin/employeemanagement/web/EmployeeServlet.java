package com.xadmin.employeemanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.employeemanagement.dao.EmployeeDao;
import com.xadmin.employeemanagement.bean.Employee;
/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
       
  

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() {
		employeeDao = new EmployeeDao();
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
        try {
	             switch(action){
	             case "/new":
		             showNewForm(request, response);
		             break;
	
	             case "/insert":
	            	 insertemployee(request, response);
	            	 break;
	            	 
	             case "/delete":
	            	 deleteemployee(request, response);
	            	 break;
	            	 
	             case "/edit":
	            	 showEditForm(request, response);
	            	 break;
	            	 
	             case "/update":
	            	 updateemployee(request, response);
	            	 break;
	            	 
	             default:
			         deleteemployee(request, response);
			         break;
			         
	             }
			         
        }catch (SQLException ex) {
				throw new ServletException(ex);
			}
			break;
	
	
	}
	private void ListEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
			
			try {
				
				List<Employee> listEmployee = employeeDao.selectAllEmployees();
				request.setAttribute("listEmployee, listEmployee);", 
				RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list-jsp");
				request.setAttribute("employee, exsistingEmployee", dispatcher);
				dispatcher.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
		dispatcher.forward(request, response);	
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Employee existingEmployee = employeeDao.selectEmployee(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form-jsp");
			request.setAttribute("employee, exsistingEmployee"); 
			dispatcher.forward(request, response);
			
			}
	
	private void insertemployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
	String name = request.getParameter("name");
	String nic = request.getParameter("nic");
	String department_name = request.getParameter("department_name");
	String designation = request.getParameter("designation");
	String joined_date = request.getParameter("joined_date");
	Employee newEmployee = new Employee (name, nic, department_name, designation, joined_date);
	employeeDao.insertEmployee(newEmployee);
	response.sendRedirect("List");
	
	}
	private void updateemployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	String nic = request.getParameter("nic");
	String department_name = request.getParameter("department_name");
	String designation = request.getParameter("designation");
	String joined_date = request.getParameter("joined_date");
	
	Employee book = new Employee (id, name, nic, department_name, designation, joined_date);
	employeeDao.updateEmployee(book);
	response.sendRedirect("List");
	}
	
	private void deleteemployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		employeeDao.deleteEmployee(id);
		response.sendRedirect("List");
		
	}
}