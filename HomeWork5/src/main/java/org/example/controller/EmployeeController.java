package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dao.EmployeeDAO;
import org.example.models.Employee;
import java.util.ArrayList;



@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();

    @GET
    @Produces( MediaType.APPLICATION_JSON)
    public ArrayList<Employee> getAllEmployees(
            @QueryParam("employee_id") Integer employee_id

    ) {try {
            return dao.selectAllEMP(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employee_id}")
    public Employee getEmployee(@PathParam("employee_id") int employee_id) {

        try {
            return dao.selectEMP(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
