package xzx.project.service.impl;


import org.springframework.stereotype.Service;

import xzx.project.base.service.impl.BaseServiceImpl;
import xzx.project.entity.StoreHouse;
import xzx.project.service.StoreHouseService;
@Service
public class StoreHouseServiceImpl extends BaseServiceImpl<StoreHouse> implements StoreHouseService {
    
	/*@Autowired
	private StoreHouseMapper storeHouseMapper;
	
	public int insert(StoreHouse entity) {
		return storeHouseMapper.insert(entity);
	}

	public StoreHouse selectByPk(StoreHouse entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteByPk(StoreHouse entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPk(StoreHouse entity) {
		return storeHouseMapper.updateByPk(entity);
	}

	public List<StoreHouse> selectUseDyc(StoreHouse entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<StoreHouse> selectPageUseDyc(Page<StoreHouse> page) {
		page.setLists(storeHouseMapper.selectPageListUseDyc(page));
		page.setTotal(storeHouseMapper.selectPageCountUseDyc(page));
		return page;
	}

	public int deleteByPks(String[] pks) {
		
		return storeHouseMapper.deleteByPks(pks);
	}*/

}
