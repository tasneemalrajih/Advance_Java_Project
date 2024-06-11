package org.example.dto;

public class EmployeeIdDto {

    private String deptCode;
    private int seq;
    private int hireYear;


    private JobsDto dto ;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    public JobsDto getDto() {
        return dto;
    }

    public void setDto(JobsDto dto) {
        this.dto = dto;
    }

    @Override
    public String toString() {
        return deptCode + seq + hireYear;
    }
}
