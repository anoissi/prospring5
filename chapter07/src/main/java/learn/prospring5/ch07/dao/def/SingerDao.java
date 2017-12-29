package learn.prospring5.ch07.dao.def;

import learn.prospring5.ch07.dao.entities.Singer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingerDao {
   List<Singer> findAll();

    @Transactional(readOnly = true)
    List<Singer> findAllWithAlbum();

    @Transactional(readOnly = true)
    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
  /*   List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer contact);
    void delete(Singer contact);*/
}
