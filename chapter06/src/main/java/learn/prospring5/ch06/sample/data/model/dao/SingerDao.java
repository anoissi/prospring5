package learn.prospring5.ch06.sample.data.model.dao;

import learn.prospring5.ch06.sample.data.model.entities.Singer;

import java.util.List;
public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);
    List<Singer> findAllWithDetail();
    void insertWithDetail(Singer  singer);

    List<Singer> findAllWithAlbums();

    void insertWithAlbum(Singer singer);

    String findNameById(Long id);
}