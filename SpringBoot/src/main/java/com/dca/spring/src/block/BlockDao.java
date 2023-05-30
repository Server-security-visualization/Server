package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

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

    /** blacklist 조회 **/
    public BlackListRes BlackListDao(){
        // Weblog Blacklist
        String getBlockWebListQuery = "" +
                "select idx as blockIdx, date_format(b.time, '%Y/%m/%d %h:%i') as time, ip\n" +
                "from block_user_table as b\n" +
                "where b.type=2\n" +
                "order by b.time desc;\n";

        List<BlockList> blockWebList = this.jdbcTemplate.query(getBlockWebListQuery, // 리스트면 query, 리스트가 아니면 queryForObject
                (rs,rowNum) -> new BlockList(
                        rs.getInt("blockIdx"),
                        rs.getString("time"),
                        rs.getString("ip")
                ));

        // Malware Blacklist
        String getBlockMalListQuery = "" +
                "select idx as blockIdx,date_format(b.time, '%Y/%m/%d %h:%i') as time, ip\n" +
                "from block_user_table as b\n" +
                "where b.type=1\n" +
                "order by b.time desc;";

        List<BlockList> blockMalList = this.jdbcTemplate.query(getBlockMalListQuery, // 리스트면 query, 리스트가 아니면 queryForObject
                (rs,rowNum) -> new BlockList(
                        rs.getInt("blockIdx"),
                        rs.getString("time"),
                        rs.getString("ip")
                ));

        return new BlackListRes(blockWebList, blockMalList);
    }
}
