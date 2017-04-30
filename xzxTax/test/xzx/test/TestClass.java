package xzx.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.annotations.Before;

import xzx.test.entity.Person;
import xzx.test.service.TestService;

/**
 * 测试Spring与Struts2整合是否成功
 * @author xzx
 *
 */
public class TestClass {
      ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
     @Test
     public void testSpring(){
    	TestService testService= (TestService) context.getBean("testService");
    	testService.say();
     }
     
     @Test
     public void testHibernate(){
    	SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
    	Session session =  sf.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.save(new Person("test1"));
    	transaction.commit();
    	session.close();
     }
     
     @Test
     public void testDaoAndService(){
    	TestService ts  =  (TestService) context.getBean("testService");
    	ts.save(new Person("test2"));
    	System.out.println(ts.findById("402881e85b0e2f05015b0e2f06820000").getName());
     }
     
}
