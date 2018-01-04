package learn.prospring5.ch08.services;

import learn.prospring5.ch08.entities.Singer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
    List<Singer> findAllByNativeQuery();
    List<Singer> findByCriteriaQuery(String firstName, String lastName);

    @Transactional(readOnly=true)
    List<Singer> findByFirstName(String firstName);

    @Transactional(readOnly=true)
    List<Singer> findByFirstNameAndLastName(
            String firstName, String lastName);
}
