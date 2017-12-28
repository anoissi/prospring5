package learn.prospring5.ch07.dao.def;

import learn.prospring5.ch07.dao.entities.Singer;

import java.util.List;

public interface SingerDao {
   List<Singer> findAll();
  /*   List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer contact);
    void delete(Singer contact);*/
}
