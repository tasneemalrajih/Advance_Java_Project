package org.example.mappers;

import org.example.dto.EmployeeDto;
import org.example.dto.JobsDto;
import org.example.models.Employee;
import org.example.models.jobs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {JobsMapper.class}, componentModel = "cdi")
public interface EmployeeMapper {

//EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
//
//
//    @Mapping(source = "e.employee_id", target = "employee_id")
//    @Mapping(source = "e.Job_id",target = "Job_id")
//    EmployeeDto toEmpDto(Employee e,jobs j);
//
//
//    Employee toModel(EmployeeDto dto);
//    employeesMapper INSTANCE = Mappers.getMapper(employeesMapper.class);
//    DepartmentDto toDeptDto(Department d, Location l);
@Mapping(source = "e.job_id",target = "job_id")
EmployeeDto toEmpDto (Employee e, jobs j );

    @Mapping(source = "e.job_id",target = "job_id")
    EmployeeDto toEmpDto (Employee e );

}
