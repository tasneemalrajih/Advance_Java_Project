package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.EmployeeDAO;
import org.example.dto.DepartmentDto;
import org.example.dto.EmployeeDto;
import org.example.dto.EmployeeFilterDto;
import org.example.exceptions.DataNotFoundException;
import org.example.models.Department;
import org.example.models.Employee;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;



@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
    @Context UriInfo uriInfo;

//    @GET
//    @Produces( MediaType.APPLICATION_JSON)
//    public ArrayList<Employee> getAllEmployees(
//            @QueryParam("employee_id") Integer employee_id
//
//    ) {try {
//            return dao.selectAllEMP(employee_id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @GET
//    @Produces( MediaType.APPLICATION_JSON)
//    public ArrayList<Employee> getAllEmployees (
//            @BeanParam EmployeeFilterDto filter) throws SQLException {
//        try {
//        return dao.selectAllEMP(filter);
//    } catch (Exception e) {
//        throw new RuntimeException(e);
//    }
//    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(@BeanParam EmployeeFilterDto filter) throws SQLException {
        try {
            ArrayList<Employee> employees = dao.selectAllEMP(filter);

            return Response.ok(employees).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GET
    @Path("{employee_id}")
    public Response get_one_Employee(@PathParam("employee_id") int employee_id) throws SQLException {

        try {
            Employee emp = dao.selectEMP(employee_id);
            if (emp== null){
                throw new DataNotFoundException("Employee "+employee_id+" Not Found");
            }
            EmployeeDto dto=new EmployeeDto();

            dto.setFirst_name(emp.getFirstName());
            dto.setLast_name(emp.getLastName());
            dto.setEmail(emp.getEmail());
            dto.setHireYear(emp.getHireDate());
            dto.setJob_id(emp.getJobId());
            dto.setSalary(emp.getSalary());
            dto.setDepartment_id(emp.getDepartmentId());
            dto.setManager_id(emp.getManager_id());
            dto.setPhone_number(emp.getPhone_number());
            dto.setEmployee_id(emp.getEmployeeId());

            addLinks(dto);

            return Response.ok(dto).build() ;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(EmployeeDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder()
                .path(EmployeeController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(), "employees");
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void insertEmployee(Employee EMP) {

        try {
            dao.insertEMP(EMP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{employee_id}")
    public void updateEmployee(@PathParam("employee_id") int employee_id, Employee EMP) {

        try {

            EMP.setEmployeeId(employee_id);
            dao.updateEMP(EMP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{employee_id}")
    public void deleteEmployee(@PathParam("employee_id") int employee_id) {

        try {
            dao.deleteEMP(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
