package learn.prospring5.ch07.demo;



import learn.prospring5.ch07.configs.AppConfig;
import learn.prospring5.ch07.dao.def.SingerDao;
import learn.prospring5.ch07.dao.entities.Album;
import learn.prospring5.ch07.dao.entities.Instrument;
import learn.prospring5.ch07.dao.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {
    private static Logger logger =
            LoggerFactory.getLogger(SpringHibernateDemo.class);

    public static void main(String... args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
      // listSingers(singerDao.findAll());
       /// listSingersWithAlbum(singerDao.findAll());
        //listSingersWithAlbum(singerDao.findAllWithAlbum());
        Singer singer = singerDao.findById(2l);
        logger.info(singer.toString());
        ctx.close();
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info("-----------------------------------------------------------");
            logger.info(singer.toString());
            logger.info("-----------------------------------------------------------");
        }

    }
    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singer with albums and instruments:");
        for (Singer singer : singers) {
            logger.info("-----------------------------------------------------------");
            logger.info(singer.toString());
            System.out.print("\n\n");
            logger.info(" ---- Listing singer albums :");
            if (singer.getAlbums() != null) {
                for (Album album :
                        singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            System.out.print("\n\n");
            logger.info(" ---- Listing singer instruments:");
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }
            logger.info("-----------------------------------------------------------");
        }
    }
}