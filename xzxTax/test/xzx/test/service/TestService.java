package xzx.test.service;

import java.io.Serializable;

import xzx.test.entity.Person;

public interface TestService {
    public void say();
    void save(Person person);
	
	Person findById(Serializable id);
}
