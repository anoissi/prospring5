package learn.prospring5.ch06.spring.utils;

import learn.prospring5.ch06.spring.dao.model.Singer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SingerMapper implements RowMapper<Singer> {

    @Override
    public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    }
}