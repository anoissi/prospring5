import learn.prospring5.ch06.spring.springJDBCOperations.config.AppConfig;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.def.SingerDao;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Album;
import learn.prospring5.ch06.spring.springJDBCOperations.dao.model.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class MappingSqlQueryJdbcSingerDaoTest {
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
        List<Singer> singers = singerDao.findAll();
        assertTrue(singers.size() == 3);
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " +  album);
                }
            }
        });
        ctx.close();
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}