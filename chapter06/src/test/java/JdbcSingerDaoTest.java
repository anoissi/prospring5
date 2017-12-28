import learn.prospring5.ch06.spring.springJDBCOperations.config.AppConfig;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.def.SingerDao;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Album;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class JdbcSingerDaoTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {

        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        testSingerInsert();
        System.out.print("testFindAll()\n");
        System.out.println("\n\n\n\n\n");
        List<Singer> singers = singerDao.findAll();
        assertTrue(singers.size() == 1);
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " +  album);
                }
            }
        });
        System.out.println("\n\n\n\n\n");
    }

    @Test
    public void testSingerUpdate() {
        System.out.println("testSingerUpdate()");
        System.out.println("\n\n\n\n\n");
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
        singerDao.update(singer);
        List<Singer> singers =  singerDao.findAll();
        singers.forEach(singer1 -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " +  album);
                }
            }
        });
        System.out.println("\n\n\n\n\n");
    }

    @Test
    public void testSingerInsert(){
        System.out.print("testSingerInsert()\n");
        System.out.println("\n\n\n\n\n");
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1991, 1, 17)).getTime().getTime()));
        singerDao.insert(singer);
        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
        System.out.println("\n\n\n\n\n");
    }
    @Test
    public void testSingerInsertWithAlbum(){
        System.out.println("testSingerInsertWith");
        System.out.println("\n\n\n\n\n");
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAbum(album);
        album = new Album();
        album.setTitle("A Heart Full  of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAbum(album);
        singerDao.insertWithAlbum(singer);
        List<Singer> singers = singerDao.findAllWithAlbums();
        listSingers(singers);
        System.out.println("\n\n\n\n\n");
    }


    @Test
    public void testFindFirstNameById(){
        testSingerInsert();
        System.out.println("testFindFirstNameById()");
        System.out.println("\n\n\n\n\n");
        String  firstName =  singerDao.findFirstNameById(1L);
        assertEquals("Ed", firstName);
        System.out.println("Retrieved value: " +  firstName);
        System.out.println("\n\n\n\n\n");
    }

    private void listSingers(List<Singer> singers){
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " +  album);
                }
            }
        });
        System.out.println("\n\n\n\n\n");
    }

    @After
    public void tearDown() {
        singerDao.cleanUpDatabaseForTest();
        ctx.close();
    }
}