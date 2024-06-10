package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {
    private @QueryParam("hire_date") String hire_date;
    private @QueryParam("job_id") Integer job_id;


    public String get_hire_date() {
        return hire_date;
    }

    public void set_hire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public Integer get_job_id() {
        return job_id;
    }

    public void set_job_id(int job_id) {
        this.job_id = job_id;
    }

}
