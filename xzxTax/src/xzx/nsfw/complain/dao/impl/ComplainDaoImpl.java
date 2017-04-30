package xzx.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import xzx.core.dao.impl.BaseDaoImpl;
import xzx.nsfw.complain.dao.ComplainDao;
import xzx.nsfw.complain.entity.Complain;


public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	@Override
	public List<Object[]> getAnnualStatisticData(int year) {
		String sql = "SELECT imonth,c2 FROM tmonth LEFT JOIN(SELECT MONTH(comp_time) c1,COUNT(comp_id) c2 FROM complain WHERE YEAR(comp_time)=? GROUP BY MONTH(comp_time)) t ON imonth = c1 ORDER BY imonth;";
	    SQLQuery query = getSession().createSQLQuery(sql);
	    query.setParameter(0, year);
	    return query.list();
	}

}
