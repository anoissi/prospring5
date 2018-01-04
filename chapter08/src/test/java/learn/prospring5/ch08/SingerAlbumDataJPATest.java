package learn.prospring5.ch08;


import learn.prospring5.ch08.entities.Album;
import learn.prospring5.ch08.services.AlbumService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SingerAlbumDataJPATest {
    private static Logger logger =
            LoggerFactory.getLogger(SingerDataJPATest.class);
    private GenericApplicationContext ctx;
    private AlbumService albumService;
    @Before
    public void setUp(){
        ctx =  new GenericXmlApplicationContext("app-context-springjpa.xml");
        albumService =  ctx.getBean(AlbumService.class);
        assertNotNull(albumService);
    }
    @Test
    public void testFindByTitle(){
        List<Album> albums =  albumService.findByTitle("The");
        assertTrue(albums.size() >  0);
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString() +  ", Singer: "
                + a.getSinger().getFirstName() +  " "
                + a.getSinger().getLastName()));
    }
    @After
    public void tearDown() {
        ctx.close();
    }
}
