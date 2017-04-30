package xzx.test.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xzx.test.entity.Person;
/**
 * hibernateDaosupport自动实现增删改查的一个类，但是要注入sessionFactory参数
 * @author xzx
 *
 */
public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public void save(Person person) {
      getHibernateTemplate().save(person);
	}

	@Override
	public Person findById(Serializable id) {
		return getHibernateTemplate().get(Person.class, id);
	}

}
