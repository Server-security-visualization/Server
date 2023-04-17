package com.dca.spring.src.user;

import com.dca.spring.src.user.model.*;
import com.dca.spring.src.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /** 로그인 - password 값 확인 **/
    public boolean checkPwd(String pwd){
        String checkPwdQuery = "select exists(select uuid from auth_table where uuid = ?)";
        int result = this.jdbcTemplate.queryForObject(checkPwdQuery, int.class, pwd);
        if(result > 0){ // 입력받은 pwd(uuid)값이 존재하면
            return true;
        }
        else
            return false;
    }
}
