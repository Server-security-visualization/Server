package com.dca.spring.src.weblog;

import com.dca.spring.src.malware.model.MalList;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WebLogDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource); }

    /** WebLog List 조회 **/
    public List<WebLogList> WebLogListDao(){
        String getWebLogListQuery = "" +
                "select wd.idx as webLogIdx, w.http_method as httpMethod, w.http_query as httpQuery,\n" +
                "       w.http_url as httpUrl, w.http_status as httpStatus, wd.detection as risk,\n" +
                "       if(wd.detection >= 0.5, 1, 0) as level,\n" +
                "       date_format(w.timestamp, '%Y/%m/%d %h:%i') as time\n" +
                "from web_log_table as w\n" +
                "left join web_log_detection_table as wd on w.idx = wd.web_log_idx\n" +
                "order by w.timestamp desc;";

        List<WebLogList> webLogList = this.jdbcTemplate.query(getWebLogListQuery, // 리스트면 query, 리스트가 아니면 queryForObject
                (rs,rowNum) -> new WebLogList(
                        rs.getInt("webLogIdx"),
                        rs.getString("httpMethod"),
                        rs.getString("httpQuery"),
                        rs.getString("httpUrl"),
                        rs.getInt("httpStatus"),
                        rs.getDouble("risk"),
                        rs.getInt("level"),
                        rs.getString("time")
                ));

        return webLogList;
    }
}