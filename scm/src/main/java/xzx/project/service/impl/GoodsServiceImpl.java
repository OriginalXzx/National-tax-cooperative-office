package xzx.project.service.impl;


import org.springframework.stereotype.Service;

import xzx.project.base.service.impl.BaseServiceImpl;
import xzx.project.entity.Goods;
@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements xzx.project.service.GoodsService {

	/*@Autowired
	private GoodsMapper goodsMapper;
	
	
	public int insert(Goods entity) {
		return goodsMapper.insert(entity);
	}

	public Goods selectByPk(Goods entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteByPk(Goods entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPk(Goods entity) {
		return goodsMapper.updateByPk(entity);
	}

	public List<Goods> selectUseDyc(Goods entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Goods> selectPageUseDyc(Page<Goods> page) {
		page.setLists(goodsMapper.selectPageListUseDyc(page));
		page.setTotal(goodsMapper.selectPageCountUseDyc(page));
		return page;
	}

	public int deleteByPks(String[] pks) {
		
		return goodsMapper.deleteByPks(pks);
	}*/

}
