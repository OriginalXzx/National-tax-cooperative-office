package xzx.nsfw.info.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import xzx.nsfw.info.service.InfoService;
import xzx.core.dao.BaseDao;
import xzx.core.service.impl.BaseServiceImpl;
import xzx.nsfw.info.dao.InfoDao;
import xzx.nsfw.info.entity.Info;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	
	private InfoDao infoDao;
	@Resource
	public void setInfoDao(InfoDao infoDao){
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
		
	}
	
}

