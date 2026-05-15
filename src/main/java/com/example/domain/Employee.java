package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class Employee {
    private Integer id;
    private String name;
    private String image;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date hireDate;
    private String mailAddress;
    private String zipCode;
    private String address;
    private String telephone;
    private Integer salary;
    private String characteristics;
    private Integer dependentsCount;

}
