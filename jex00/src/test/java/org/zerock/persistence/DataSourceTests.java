package org.zerock.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import jdk.internal.org.jline.utils.Log;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root_context.xml")
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class DataSourceTests {
	@Setter(onMethod_  = { @Autowired })
	private DataSource dataSource;
	
	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection connection =session.getConnection(); ){
			log.info(session);
			log.info(connection);
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection connection = dataSource.getConnection()){
			log.info(connection);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
