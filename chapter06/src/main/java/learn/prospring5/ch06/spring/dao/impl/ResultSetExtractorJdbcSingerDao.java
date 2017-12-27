package learn.prospring5.ch06.spring.dao.impl;

import learn.prospring5.ch06.spring.dao.def.SingerDao;
import learn.prospring5.ch06.spring.dao.model.Singer;
import learn.prospring5.ch06.spring.utils.SingerWithDetailExtractor;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class ResultSetExtractorJdbcSingerDao extends NamedParameterJdbcSingerDao implements SingerDao, InitializingBean {

    @Override
    public List<Singer> findAllWithAlbums() {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date" +
                ", a.id as singer_tel_id, a.title, a.release_date from singer s " +
                "left join album a on s.id = a.singer_id";
        return namedParameterJdbcTemplate.query(sql, new SingerWithDetailExtractor());
    }

}
