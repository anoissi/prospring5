import learn.prospring5.ch06.spring.cfs.SingerJdbcResultSetExtractorConfig;
import learn.prospring5.ch06.spring.dao.def.SingerDao;
import learn.prospring5.ch06.spring.dao.model.Album;
import learn.prospring5.ch06.spring.dao.model.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ResultSetExtractorTest {
    @Test
    public void testResultSetExtractor() {
        GenericApplicationContext ctx  =
                new AnnotationConfigApplicationContext(SingerJdbcResultSetExtractorConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAllWithAlbums();
        assertTrue(singers.size() ==  3);
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                singer.getAlbums().forEach(album -> System.out.println("\t--> " +  album));
            }
        });
        ctx.close();
    }
}