package xzx.test.dao;

import java.io.Serializable;

import xzx.test.entity.Person;

public interface TestDao {
     
	void save(Person person);
	
	Person findById(Serializable id);
}
