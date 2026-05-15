package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    public List<Employee> findAll() {
        System.out.println("RepositoryFindAll");
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code," +
                "address,telephone,salary,characteristics,dependents_count " +
                "FROM employees ORDER BY id ASC";
        return template.query(sql, ROW_MAPPER);
    }

    public Employee findById(Integer id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null so your Service/Controller can handle it gracefully
        }
    }


    public void update(Employee employee) {
        System.out.println("RepositoryUpdate");

        String sql = "UPDATE employees " +
                "SET " +
                "id = :id, " +
                "name=:name," +
                "image=:image," +
                "gender=:gender," +
                "hire_date=:hireDate," +
                "mail_address=:mailAddress," +
                "zip_code=:zipCode," +
                "address=:address," +
                "telephone=:telephone," +
                "salary=:salary," +
                "characteristics=:characteristics," +
                "dependents_count=:dependentsCount where id = :id";

        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        System.out.println(sql);
        template.update(sql, param);
    }
}
