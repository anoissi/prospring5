package learn.prospring5.ch06.spring.dao.impl;

import learn.prospring5.ch06.spring.dao.def.SingerDao;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class NamedParameterJdbcSingerDao implements SingerDao, InitializingBean {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public String findNameById(Long id) {
        String sql = "SELECT first_name ||' '|| last_name FROM singer WHERE id = :singerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql,
                namedParameters, String.class);
    }
    public void setNamedParameterJdbcTemplate
            (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if (namedParameterJdbcTemplate ==  null) {
            throw new BeanCreationException
                    ("Null NamedParameterJdbcTemplate on  SingerDao");
        }
    }
}