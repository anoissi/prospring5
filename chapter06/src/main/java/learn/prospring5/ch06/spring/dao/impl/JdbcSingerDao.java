package learn.prospring5.ch06.spring.dao.impl;

import learn.prospring5.ch06.spring.dao.def.SingerDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("select first_name || ' ' || last_name from singer where id = ?", new Object[]{id}, String.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}