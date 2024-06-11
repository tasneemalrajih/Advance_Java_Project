package org.example.dao;

import org.example.dto.EmployeeFilterDto;

import org.example.models.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\SDAIA-Java-Course\\JavaBasics\\HomeWork\\hr.db";
    private static final String insertEmployee = "insert into employees values (?,?,?,?,?,?,?,?,?,?)";
    private static final String selectEmployee = "select * from employees where employee_id = ?";
    private static final String selectAllEmployees = " select * from employees " ;
    private static final String updateEmployee = "update employees set first_name = ?, last_name = ? where employee_id  = ?";
    private static final String deleteEmployee = "delete from employees where employee_id = ?";
    private static final String select_Emp_hire_date = "select * from employees where hire_date LIKE ? || '%'";
    private static final String select_Emp_job_id = "select * from employees where job_id = ?";
    private static final String select_Emp_last_n = "select * from employees where Last_name = ?";
    private static final String SELECT_ONE_EMP_JOIN_JOBS = "select * from employees join jobs on employees.job_id = jobs.job_id where employee_id = ?";





    public void insertEmployee(Employee e) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(insertEmployee);
        st.setInt(1,e.getEmployee_id());
        st.setString(2,e.getFirst_name());
        st.setString(3,e.getLast_name());
        st.setString(4,e.getEmail());
        st.setString(5,e.getPhone_number());
        st.setString(6,e.getHire_date());
        st.setInt(7,e.getJob_id());
        st.setDouble(8,e.getSalary());
        st.setInt(9,e.getManager_id());
        st.setInt(10,e.getDepartment_id());
        st.executeUpdate();
        conn.close();
    }

    public Employee selectEmployee(int idEmp) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMP_JOIN_JOBS);
        st.setInt(1, idEmp);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employee(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employee> selectAllEmployees( EmployeeFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        if (filter.getJob_id() != null) {
            st = conn.prepareStatement(select_Emp_job_id);
            st.setInt(1, filter.getJob_id());
        } else if (filter.getHireYear() != null) {
            st = conn.prepareStatement(select_Emp_hire_date);
            st.setString(1, filter.getHireYear());
        } else {
            st = conn.prepareStatement(selectAllEmployees);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Employee> employee = new ArrayList<>();
        while (rs.next()) {
            employee.add(new Employee(rs));
        }
        return employee;
    }






    public void updateEmployee(Employee e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(updateEmployee);
        st.setInt(3,e.getEmployee_id());
        st.setString(1,e.getFirst_name());
        st.setString(2,e.getLast_name());
        st.executeUpdate();
        conn.close();

    }


    public void deleteEmployee(int IdEmp) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(deleteEmployee);
        st.setInt(1, IdEmp);
        st.executeUpdate();
        conn.close();
    }

//    public employees select_Emp_hire_date(String hire_date) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(select_Emp_hire_date);
//        st.setString(1, hire_date);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            return new employees(rs);
//        } else {
//            return null;
//        }
//    }

//    public employees select_Emp_job_id(int job_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(select_Emp_job_id);
//        st.setInt(1, job_id);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            return new employees(rs);
//        } else {
//            return null;
//        }
//    }

    public Employee select_Emp_last_n(String Last_name) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(select_Emp_last_n);
        st.setString(1, Last_name);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employee(rs);
        }
        else {
            return null;
        }
    }


}
