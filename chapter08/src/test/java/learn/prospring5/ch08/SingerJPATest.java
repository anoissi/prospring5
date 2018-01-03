package learn.prospring5.ch08;

import learn.prospring5.ch08.entities.Album;
import learn.prospring5.ch08.entities.Instrument;
import learn.prospring5.ch08.entities.Singer;
import learn.prospring5.ch08.services.SingerService;
import learn.prospring5.ch08.utils.LogUtils;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SingerJPATest {
    private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp(){
        ctx = new GenericXmlApplicationContext("app-context-annotation.xml");
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }
    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(LogUtils.BEFORE);
        logger.info(" ----  Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
        logger.info(LogUtils.AFTER);
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }
    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(LogUtils.BEFORE);
        logger.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for  (Album album :
                        singer.getAlbums()) {
                    logger.info("\t" +  album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\tInstrument: " +  instrument.getInstrumentId());
                }
            }
        }
        logger.info(LogUtils.AFTER);
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}