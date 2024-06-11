package org.example.mappers;

import org.example.dto.JobsDto;
import org.example.models.jobs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "cdi")
public interface  JobsMapper {
//    JobsMapper INSTANCE = Mappers.getMapper(JobsMapper.class);
//    @Mapping(source = "j.job_id", target = "job_id")
//    @Mapping(source = "j.job_title", target = "job_title")
//    @Mapping(source = "j.min_salary", target = "min_salary")
//    @Mapping(source = "j.max_salary", target = "max_salary")
    JobsDto toJobsDto(jobs j);

//    @Mapping(target = "job_id", source = "job_id")
//    @Mapping(target = "job_title", source = "job_title")
//    @Mapping(target = "min_salary", source = "min_salary")
//    @Mapping(source = "max_salary", target = "max_salary")
    jobs toModel(JobsDto dto);


}
