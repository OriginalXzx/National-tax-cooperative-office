package xzx.nsfw.complain.dao;

import java.util.List;

import xzx.core.dao.BaseDao;
import xzx.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain>{

	List<Object[]> getAnnualStatisticData(int year);

}
