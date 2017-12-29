package learn.prospring5.ch07.utils;



import learn.prospring5.ch07.dao.def.SingerDao;
import learn.prospring5.ch07.dao.entities.Album;
import learn.prospring5.ch07.dao.entities.Instrument;
import learn.prospring5.ch07.dao.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DBInitializer {
    private Logger logger =
            LoggerFactory.getLogger(DBInitializer.class);
    @Autowired
    SingerDao singerDao;
    @PostConstruct
    public void initDB(){
        logger.info("Starting database initialization...");
        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()));

        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2017, 0, 20)).getTime().getTime()));
        singer.addAbum(album1);
        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2009, 10, 17)).getTime().getTime()));
        singer.addAbum(album2);
        singerDao.save(singer);
        logger.info("Database initialization finished.");
    }
}