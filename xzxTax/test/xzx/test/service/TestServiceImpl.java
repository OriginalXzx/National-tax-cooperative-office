package xzx.test.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xzx.test.dao.TestDao;
import xzx.test.entity.Person;

@Service("testService")
public class TestServiceImpl implements TestService {
    
	@Resource
	private TestDao testDao;
	
	public void say() {
          System.out.println("service say hello!");
	}

	@Override
	public void save(Person person) {
		testDao.save(person);
		
	}

	@Override
	public Person findById(Serializable id) {
		return testDao.findById(id);
		
	}

}
