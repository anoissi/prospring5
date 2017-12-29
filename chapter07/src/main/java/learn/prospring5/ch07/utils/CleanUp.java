package learn.prospring5.ch07.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 4/15/17.
 */

@Component
public class CleanUp {
    private static Logger logger = LoggerFactory.getLogger(CleanUp.class);

    private   JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private   DataSource dataSource;


    public void destroy() {
        logger.info(" ... Deleting database files.");
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}