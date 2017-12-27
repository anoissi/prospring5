package learn.prospring5.ch06.spring.springJDBCOperations.dao.def;

import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findNameById(Long id);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    List<Singer> findAllWithAlbums();

    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);
    void insertWithAlbum(Singer singer);

    void cleanUpDatabaseForTest();
}

