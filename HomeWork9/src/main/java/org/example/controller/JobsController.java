package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.JobsDAO;
import org.example.dto.JobsDto;
import org.example.dto.JobsFilterDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.JobsMapper;
import org.example.models.jobs;
import java.net.URI;

import java.sql.SQLException;

import java.util.ArrayList;

@Path("/jobs")
public class JobsController {

    JobsDAO dao = new JobsDAO();
    @Context
    UriInfo uriInfo;
    @Context HttpHeaders headers;


    @Inject
    JobsMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response SELECT_ALL_jobs(
//             @QueryParam("min_salary") Double min_salary ,
//             @QueryParam("limit")  Integer limit ,
//             @QueryParam("offset") int offset
            @BeanParam JobsFilterDto filter
    ) {
//          System.out.println(min_salary);
        try {
            GenericEntity<ArrayList<jobs>> job = new GenericEntity<ArrayList<jobs>>(dao.SELECT_ALL_jobs(filter)) {};
//               return jo.SELECT_ALL_jobs(min_salary,limit,offset);
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(job)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(job)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(job, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GET
    @Path("{Job_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response SELECT_ONE_id_job(@PathParam("Job_id") int Job_id) {
        try {
            jobs jj =dao.SELECT_ONE_id_job(Job_id);
//                jo.SELECT_ONE_id_job(Job_id);

//                jobsDto dto = new jobsDto();
//                dto.setJob_id(jj.getJob_id());
//                dto.setJob_title(jj.getJob_title());
//                dto.setMin_salary(jj.getMin_salary());
//                dto.setMax_salary(jj.getMax_salary());

            JobsDto d =mapper.toJobsDto(jj);

            addLinks(d);



            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(d)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(d)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(d, MediaType.APPLICATION_JSON)
                    .build();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DELETE
    @Path("{Job_id}")
    public Response DELETE_jobs(@PathParam("Job_id") int Job_id) {

        try {
            dao.DELETE_jobs(Job_id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @POST
    public Response INSERT_jobs(JobsDto dto) {
        try {
            jobs jj = mapper.toModel(dto);
            dao.INSERT_jobs(jj);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PUT
    @Path("{Job_id}")
    public Response UPDATE_jobs(@PathParam("Job_id") int Job_id, jobs job) {

        try {
            job.setJob_id(Job_id);
            dao.UPDATE_jobs(job);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void addLinks(JobsDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder()
                .path(JobsController.class)
                .build();
        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(), "employees");
    }


}