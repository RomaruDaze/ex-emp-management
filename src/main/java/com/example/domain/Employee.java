package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {
    private Integer id;
    private String name;
    private String image;
    private String gender;
    private java.util.Date hireDate;
    private String mailAddress;
    private String zipCode;
    private String address;
    private String telephone;
    private String salary;
    private String characteristics;
    private Integer dependentsCount;

}
