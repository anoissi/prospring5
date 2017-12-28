package learn.prospring5.ch06.spring.springJDBCOperations.dao.impl;

import learn.prospring5.ch06.spring.springJDBCOperations.dao.def.SingerDao;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.impl.queries.*;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Album;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mappingSqlQueryJdbcSingerDao")
public class JdbcSingerDao implements SingerDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SqlUpdateUpdateSinger sqlUpdateUpdateSinger;
    private InsertSinger insertSinger;
    private InsertSingerAlbum insertSingerAlbum;
    private StoredFunctionFirstNameById storedFunctionFirstNameById;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.sqlUpdateUpdateSinger = new SqlUpdateUpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute() ;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        List<String> result = storedFunctionFirstNameById.execute(id);
        return result.get(0);
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT s.id, s.first_name, s.last_name, s.birth_date" +
                ", a.id AS album_id, a.title, a.release_date FROM  singer s " +
                "LEFT JOIN album a ON s.id = a.singer_id";
        return jdbcTemplate.query(sql, new SingerWithAlbumExtractor());
    }
    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
    }

    @Override
    public void update(Singer singer)  {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        sqlUpdateUpdateSinger.updateByNamedParam(paramMap);
        logger.info("Existing singer updated with  id: " + singer.getId());
    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public void insertWithAlbum(Singer singer) {
        insertSingerAlbum = new InsertSingerAlbum(dataSource);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " +  singer.getId());
        List<Album>  albums =  singer.getAlbums();
        if (albums != null) {
            for (Album album : albums) {
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }
        insertSingerAlbum.flush();
    }

    @Override
    public void cleanUpDatabaseForTest(){
        String sql1 = "delete  from singer";
        String sql2 = "delete   from album" ;
        String sql3 = "ALTER SEQUENCE singer_id_seq RESTART WITH 1;";
        String sql4 = "ALTER SEQUENCE album_id_seq RESTART WITH 1;";

      try {
          JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
          jdbcTemplate.update(sql2, new Object[]{});
          jdbcTemplate.update(sql1, new Object[]{});
          jdbcTemplate.update(sql3, new Object[]{});
          jdbcTemplate.update(sql4, new Object[]{});
      } catch (Exception e){
          logger.info(" danger !  : error when trying to clean up database");
      }


    }

    private static final class SingerWithAlbumExtractor implements ResultSetExtractor<List<Singer>> {
        public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while (rs.next()) {
                Long id = rs.getLong("id");
                singer = map.get(id);
                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.getAlbums().add(album);
                }
            }
            return new ArrayList<>(map.values());
        }
    }
}