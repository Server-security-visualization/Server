package com.dca.spring.src.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.sql.DataSource;

@Repository
public class TestDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /** db 테스트 - uuid 값 조회 **/
    public String getUuid(){
        String getUuidQuery = "select uuid from auth_table";
        return this.jdbcTemplate.queryForObject(getUuidQuery, String.class);
    }
}
