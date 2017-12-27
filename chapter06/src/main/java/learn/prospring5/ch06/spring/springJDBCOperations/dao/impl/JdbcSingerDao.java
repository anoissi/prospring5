package learn.prospring5.ch06.spring.springJDBCOperations.dao.impl;

import learn.prospring5.ch06.spring.springJDBCOperations.dao.def.SingerDao;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.impl.queries.SelectAllSingers;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.impl.queries.SqlUpdateUpdateSinger;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mappingSqlQueryJdbcSingerDao")
public class JdbcSingerDao implements SingerDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SqlUpdateUpdateSinger sqlUpdateUpdateSinger;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.sqlUpdateUpdateSinger = new SqlUpdateUpdateSinger(dataSource);
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
        return null;
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public void insert(Singer singer) {

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

    }
}