package org.example.dao;
import  java.sql.*;

import java.util.ArrayList;

import org.example.dto.EmployeeFilterDto;
import org.example.models.Employee;

public class EmployeeDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\SDAIA-Java-Course\\JavaBasics\\HomeWork\\hr.db";
    private static final String SELECT_ALL_EMP = "select * from employees";
    private static final String SELECT_ONE_EMP = "select * from employees where employee_id = ?";
    private static final String INSERT_EMP = "insert into employees values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMP = "update employees set first_name = ?, last_name=?, email=?, hire_date=?,job_id=?, salary=?, department_id=? , manager_id=?, phone_number=? where employee_id = ?";
    private static final String DELETE_EMP = "delete from employees where employee_id = ?";
    private static final String SELECT_EHD_EMP = "select * from employees where hire_date LIKE ? || '%'";

    private static final String SELECT_EJID_EMP = "select * from employees where job_id = ?";

    public void insertEMP(Employee EMP) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMP);
        st.setInt(1, EMP.getEmployeeId());
        st.setString(2, EMP.getFirstName());
        st.setString(3, EMP.getLastName());
        st.setString(4, EMP.getEmail());
        st.setString(5, EMP.getHireDate());
        st.setInt(6, EMP.getJobId());
        st.setDouble(7, EMP.getSalary());
        st.setInt(8, EMP.getDepartmentId());
        st.setInt(9,EMP.getManager_id());
        st.setString(10,EMP.getPhone_number());
        st.executeUpdate();
        conn.close();

    }


    public void updateEMP(Employee EMP) throws SQLException, ClassNotFoundException {
        System.out.println(EMP.getSalary());
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMP);
        st.setInt(10, EMP.getEmployeeId());
        st.setString(1, EMP.getFirstName());
        st.setString(2, EMP.getLastName());
        st.setString(3, EMP.getEmail());
        st.setString(4, EMP.getHireDate());
        st.setInt(5, EMP.getJobId());
        st.setDouble(6, EMP.getSalary());
        st.setInt(7, EMP.getDepartmentId());
        st.setInt(8,EMP.getManager_id());
        st.setString(9,EMP.getPhone_number());
        st.executeUpdate();
        conn.close();
    }

    public void deleteEMP(int employee_id)  throws SQLException, ClassNotFoundException  {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMP);
        st.setInt(1, employee_id);
        st.executeUpdate();
        conn.close();

    }
//    public Employee SELECT_EHD_EMP(String hire_date )  throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_EHD_EMP);
//        st.setString(1, hire_date);
//        ResultSet rs = st.executeQuery();
//        if(rs.next()) {
//            return new Employee(rs);
//        }
//        else {
//            return null;
//        }
//    }
//    public Employee SELECT_EJID_EMP(int job_id)  throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_EJID_EMP);
//        st.setInt(1, job_id);
//        ResultSet rs = st.executeQuery();
//        if(rs.next()) {
//            return new Employee(rs);
//        }
//        else {
//            return null;
//        }
//    }
    public Employee selectEMP(int employee_id)  throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMP);
        st.setInt(1, employee_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employee(rs);
        }
        else {
            return null;
        }
    }

//    public ArrayList<Employee> selectAllEMP() throws SQLException {
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_ALL_EMP);
//        ResultSet rs = st.executeQuery();
//        ArrayList<Employee> EMP = new ArrayList<>();
//        while (rs.next()) {
//            EMP.add(new Employee(rs));
//        }
//
//        return EMP;
//    }

//    public ArrayList<Employee> selectAllEMP(Integer employee_id ) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st;
//
//         if(employee_id != null) {
//            st = conn.prepareStatement(SELECT_ONE_EMP);
//
//            st.setInt(1, employee_id);
//        } else {
//            st = conn.prepareStatement(SELECT_ALL_EMP);
//        }
//        ResultSet rs = st.executeQuery();
//        ArrayList<Employee> EMP = new ArrayList<>();
//        while (rs.next()) {
//            EMP.add(new Employee(rs));
//
//        }
//
//        return EMP;
//    }

    public ArrayList<Employee> selectAllEMP(EmployeeFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;

        if(filter.get_job_id() != null) {
            st = conn.prepareStatement(SELECT_EJID_EMP);

            st.setInt(1, filter.get_job_id());
        } else if (filter.get_hire_date() != null){
            st = conn.prepareStatement(SELECT_EHD_EMP);
            st.setString(1 , filter.get_hire_date());
        }else {
            st = conn.prepareStatement(SELECT_ALL_EMP);
        }

        ResultSet rs = st.executeQuery();
        ArrayList<Employee> EMP = new ArrayList<>();
        while (rs.next()) {
            EMP.add(new Employee(rs));

        }

        return EMP;
    }

}

