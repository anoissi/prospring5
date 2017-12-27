package learn.prospring5.ch06.spring.dao.impl;

import learn.prospring5.ch06.spring.dao.def.SingerDao;
import learn.prospring5.ch06.spring.dao.model.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("select first_name || ' ' || last_name from singer where id = ?", new Object[]{id}, String.class);
    }

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate ==  null) {
            throw new BeanCreationException
                    ("Null JdbcTemplate on  SingerDao");
        }
    }
}