package learn.prospring5.ch08.services;

import learn.prospring5.ch08.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.google.common.collect.Lists;

@Service("newJpaSingerService")
@Repository
@Transactional
public class NewSingerServiceImpl implements SingerService{

    @Autowired
    private SingerRepository singerRepository;

    @Transactional(readOnly=true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        throw new NotImplementedException("findAllWithAlbum");
    }

    @Override
    public Singer findById(Long id) {
        throw new NotImplementedException("findById");
    }

    @Override
    public Singer save(Singer singer) {
        throw new NotImplementedException("save");
    }

    @Override
    public void delete(Singer singer) {
        throw new NotImplementedException("delete");
    }

    @Override
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException("findAllByNativeQuery");
    }

    @Override
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        throw new NotImplementedException("findByCriteriaQuery");    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findByFirstNameAndLastName(
            String firstName, String lastName) {
        return singerRepository.findByFirstNameAndLastName(
                firstName,  lastName);
    }

}

