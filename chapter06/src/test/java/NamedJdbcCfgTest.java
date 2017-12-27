import learn.prospring5.ch06.spring.dao.def.SingerDao;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class NamedJdbcCfgTest {
    @Test
    public void testH2DB() {
        GenericXmlApplicationContext  ctx  =
                new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();
        testDao(ctx.getBean(SingerDao.class));
        ctx.close();
    }
    private void testDao(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1l);
        assertTrue("John Mayer".equals(singerName));
    }
}