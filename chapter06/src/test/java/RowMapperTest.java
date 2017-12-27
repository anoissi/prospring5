import learn.prospring5.ch06.spring.dao.def.SingerDao;
import learn.prospring5.ch06.spring.dao.model.Album;
import learn.prospring5.ch06.spring.dao.model.Singer;
import learn.prospring5.ch06.spring.embeddedDatabase.EmbeddedJdbcConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class RowMapperTest {
    @Test
    public void testRowMapper() {
        System.out.println("Im here");
        GenericApplicationContext ctx  =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertTrue(singers.size() ==  3);
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album   :
                        singer.getAlbums()) {
                    System.out.println("---" +  album);
                }
            }
        });
        ctx.close();
    }
}