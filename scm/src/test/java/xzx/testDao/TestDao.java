package xzx.testDao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xzx.project.dao.AccountMapper;
import xzx.project.entity.Account;

public class TestDao {
    
	@Test
	public void testSpringMybatis(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-mybatis.xml"});
		AccountMapper accountDao = (AccountMapper) ac.getBean("accountDaoID");
		accountDao.insert(new Account(3,"admin1","admin1","admin1"));
	}

}
