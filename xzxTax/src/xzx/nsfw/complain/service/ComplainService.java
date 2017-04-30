package xzx.nsfw.complain.service;

import java.util.List;

import xzx.core.service.BaseService;
import xzx.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
       
	void autoDeal();

	List getAnnualStatisticData(int year);
}
