package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class EmployeeDto {

   private String first_name;
   private String last_name;
   private String email;
    private String hireYear;
    private int job_id;
   private double salary;
    private int department_id;
    private int  manager_id;
    private String phone_number;
    private int employee_id;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public EmployeeDto() {

    }

    public EmployeeDto(String first_name, String last_name, String email, String hireYear, int job_id, double salary, int department_id, int manager_id, String phone_number, int employee_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.hireYear = hireYear;
        this.job_id = job_id;
        this.salary = salary;
        this.department_id = department_id;
        this.manager_id = manager_id;
        this.phone_number = phone_number;
        this.employee_id = employee_id;

    }

    public EmployeeDto(ResultSet rs) throws SQLException {
        first_name = rs.getString("first_name");
        last_name = rs.getString("last_name");
        email = rs.getString("email");
        hireYear = rs.getString("hireYear");
        job_id = rs.getInt("job_id");
        salary = rs.getDouble("salary");
        department_id = rs.getInt("department_id");
        manager_id = rs.getInt("manager_id");
        phone_number = rs.getString("phone_number");
        employee_id = rs.getInt("employee_id");

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireYear() {
        return hireYear;
    }

    public void setHireYear(String hireYear) {
        this.hireYear = hireYear;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", hireYear='" + hireYear + '\'' +
                ", job_id=" + job_id +
                ", salary=" + salary +
                ", department_id=" + department_id +
                ", manager_id=" + manager_id +
                ", phone_number='" + phone_number + '\'' +
                ", employee_id='" + employee_id + '\'' +
                '}';
    }
}
