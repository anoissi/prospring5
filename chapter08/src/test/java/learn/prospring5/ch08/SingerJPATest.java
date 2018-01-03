package learn.prospring5.ch08;

import learn.prospring5.ch08.entities.Album;
import learn.prospring5.ch08.entities.Instrument;
import learn.prospring5.ch08.entities.Singer;
import learn.prospring5.ch08.services.SingerService;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
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
        logger.info(" ----  Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }
    private static void listSingersWithAlbum(List<Singer> singers) {
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
    }

    @Test
    public void findById(){
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        Album  album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAbum(album);
        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAbum(album);
        Singer s2 = singerService.findById(1L);
        s2.setLastName("Noissi");
        singerService.save(singer);
        singerService.save(s2);
        assertNotNull(singer.getId());
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testDelete(){
        Singer singer = singerService.findById(2l);
        //making sure such singer exists
        assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}