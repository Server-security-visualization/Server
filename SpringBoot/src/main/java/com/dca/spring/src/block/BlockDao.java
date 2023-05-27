package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BlockDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /** IP 차단 **/
    public int BlockIpDao(BlockReq blockReq){
        String postBlockIpQuery = "insert into block_user_table (ip, type, info_idx) VALUES (?,?,?);";
        Object[] insertBlockIpParams = new Object[]{
                blockReq.getIp(),
                blockReq.getType(),
                blockReq.getInfoIdx()
        };
        this.jdbcTemplate.update(postBlockIpQuery,insertBlockIpParams);

        // 추가된 인덱스 조회
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}
