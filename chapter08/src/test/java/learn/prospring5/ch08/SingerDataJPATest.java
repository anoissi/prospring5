package learn.prospring5.ch08;


import learn.prospring5.ch08.entities.Singer;
import learn.prospring5.ch08.services.NewSingerServiceImpl;
import learn.prospring5.ch08.services.SingerService;
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
import static org.junit.Assert.assertTrue;

public class SingerDataJPATest {
    private static Logger logger =
            LoggerFactory.getLogger(SingerDataJPATest.class);
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new GenericXmlApplicationContext("app-context-springjpa.xml");
        singerService = (SingerService) ctx.getBean("newJpaSingerService");
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertTrue(singers.size() > 0);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertTrue(singers.size() > 0);
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        assertEquals(1, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ----  Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}

