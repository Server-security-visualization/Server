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
                "select b.idx as blockIdx, date_format(b.time, '%Y/%m/%d %h:%i') as time,\n" +
                "       w.idx as webLogIdx, w.ip, w.http_method as httpMethod, w.http_query as httpQuery,\n" +
                "       w.http_url as httpUrl, w.http_status as httpStatus, wd.detection as risk\n" +
                "from block_user_table as b\n" +
                "left join web_log_table as w on b.info_idx = w.idx\n" +
                "left join web_log_detection_table as wd on w.idx = wd.web_log_idx\n" +
                "where b.type=2\n" +
                "order by b.time desc;";

        List<BlockWebLogList> blockWebList = this.jdbcTemplate.query(getBlockWebListQuery, // 리스트면 query, 리스트가 아니면 queryForObject
                (rs,rowNum) -> new BlockWebLogList(
                        rs.getInt("blockIdx"),
                        rs.getString("time"),
                        rs.getInt("webLogIdx"),
                        rs.getString("ip"),
                        rs.getString("httpMethod"),
                        rs.getString("httpQuery"),
                        rs.getString("httpUrl"),
                        rs.getInt("httpStatus"),
                        rs.getDouble("risk")
                ));

        // Malware Blacklist
        String getBlockMalListQuery = "" +
                "select b.idx as blockIdx,date_format(b.time, '%Y/%m/%d %h:%i') as time,\n" +
                "       fd.file_idx as fileIdx, f.ip, f.file as fileName, fd.detection as risk,\n" +
                "       if(fd.detection = 0, '정상', fd.type) as malwareType\n" +
                "from block_user_table as b\n" +
                "left join file_table as f on f.idx = b.info_idx\n" +
                "left join file_detection_table as fd on f.idx = fd.file_idx\n" +
                "where b.type=1\n" +
                "order by b.time desc;";

        List<BlockMalwareList> blockMalList = this.jdbcTemplate.query(getBlockMalListQuery, // 리스트면 query, 리스트가 아니면 queryForObject
                (rs,rowNum) -> new BlockMalwareList(
                        rs.getInt("blockIdx"),
                        rs.getString("time"),
                        rs.getInt("fileIdx"),
                        rs.getString("ip"),
                        rs.getString("fileName"),
                        rs.getDouble("risk"),
                        rs.getString("malwareType")
                ));

        return new BlackListRes(blockWebList, blockMalList);
    }
}
