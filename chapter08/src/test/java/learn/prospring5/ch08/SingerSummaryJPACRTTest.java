package learn.prospring5.ch08;


import learn.prospring5.ch08.services.SingerSummaryService;
import learn.prospring5.ch08.view.SingerSummary;
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

public class SingerSummaryJPACRTTest {
    private static Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);
    private GenericApplicationContext ctx;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp() {
        ctx = new GenericXmlApplicationContext("app-context-springjpa.xml");
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        assertNotNull(singerSummaryService);
    }

    @Test
    public void testFindAll() {
        List<SingerSummary> singers = singerSummaryService.findAll();
        listSingerSummary(singers);
        assertEquals(2, singers.size());
    }

    private static void listSingerSummary(List<SingerSummary> singers) {
        logger.info(" ---- Listing singers summary:");
        for (SingerSummary singer : singers) {
            logger.info(singer.toString());
        }
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}