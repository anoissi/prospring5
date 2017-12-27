package learn.prospring5.ch06.spring.dao.def;

import learn.prospring5.ch06.spring.dao.model.Singer;

import java.util.List;

public interface SingerDao {
    String findNameById(Long id);

    List<Singer> findAll();
}