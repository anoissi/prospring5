package learn.prospring5.ch06.spring.embeddedDatabase;

import learn.prospring5.ch06.spring.dao.def.SingerDao;

import learn.prospring5.ch06.spring.dao.impl.RowMapperJdbcSingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import  org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import  org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;


@Configuration
public class EmbeddedJdbcConfig {
    private static Logger logger =
            LoggerFactory.getLogger(EmbeddedJdbcConfig.class);
    @Bean
    public DataSource dataSource()  {
        try {
            EmbeddedDatabaseBuilder dbBuilder  =
                    new EmbeddedDatabaseBuilder();
            return  dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql").build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
     public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return namedParameterJdbcTemplate;
    }
    @Bean
    public SingerDao singerDao() {
        RowMapperJdbcSingerDao dao = new RowMapperJdbcSingerDao();
        dao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return dao;
    }
}