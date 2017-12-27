package learn.prospring5.ch06.spring.dao.impl;


import learn.prospring5.ch06.spring.dao.def.SingerDao;
import learn.prospring5.ch06.spring.dao.model.Singer;
import learn.prospring5.ch06.spring.utils.SingerMapper;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowMapperJdbcSingerDao implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)  {
        this.namedParameterJdbcTemplate =  namedParameterJdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT first_name ||' '|| last_name FROM singer WHERE id = :singerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql,
                namedParameters, String.class);
    }

/*    public List<Singer> findAll() {
        String sql = "select id, first_name, last_name, birth_date from singer";
        return namedParameterJdbcTemplate.query(sql, new SingerMapper());
    }*/

    public List<Singer> findAll() {
        String sql = "select id, first_name, last_name, birth_date from singer";
        return namedParameterJdbcTemplate.query(sql, (rs, rowNum)  -> {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (namedParameterJdbcTemplate ==  null) {
            throw new BeanCreationException(
                    "Null NamedParameterJdbcTemplate on  SingerDao");
        }
    }
}