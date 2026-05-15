package com.example.repository;

import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ROW_MAPPER = (rs, i) -> {
        Administrator admin = new Administrator();
        admin.setId(rs.getInt("id"));
        admin.setName(rs.getString("name"));
        admin.setMailAddress(rs.getString("mail_address"));
        admin.setPassword(rs.getString("password"));
        return admin;
    };

    public Administrator save(Administrator admin) {
        System.out.println("RepositorySave");
        SqlParameterSource param = new BeanPropertySqlParameterSource(admin);

        if (admin.getId() == null) {
            String insertSql = "INSERT INTO administrators (name,mail_address,password)" +
                    "VALUES (:name,:mailAddress,:password)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            String[] keyColumnsNames = {"id"};
            template.update(insertSql, param, keyHolder, keyColumnsNames);
            admin.setId(keyHolder.getKey().intValue());
            System.out.println(keyHolder.getKey() + "が割り当てられました。");
        } else {
            String updateSql = "UPDATE administrators " +
                    "SET name=:name,mail_address=:maidAddress,password=:password " +
                    "WHERE id = :id";

            template.update(updateSql, param);
        }
        return null;
    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        System.out.println("RepositoryFindBymailAddressAndPassword");

        String sql = "SELECT * FROM administrators WHERE mail_address = :mailAddress AND password = :password";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);

        return template.query(sql, param, ROW_MAPPER).stream()
                .findFirst()
                .orElse(null);
    }

    public List<Administrator> findAll() {
        System.out.println("RepositoryFindAll");
        String sql = "SELECT id,name,mail_address,password FROM administrators ORDER BY id ASC";
        return template.query(sql, ROW_MAPPER);
    }
}
