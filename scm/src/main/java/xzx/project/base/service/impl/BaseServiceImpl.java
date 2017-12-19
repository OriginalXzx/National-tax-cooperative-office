package xzx.project.base.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;


import org.springframework.beans.factory.annotation.Autowired;

import xzx.project.base.dao.BaseMapper;
import xzx.project.base.service.BaseService;
import xzx.project.dao.AccountMapper;
import xzx.project.dao.AccountRecordsMapper;
import xzx.project.dao.BuyOrderDetailMapper;
import xzx.project.dao.BuyOrderMapper;
import xzx.project.dao.GoodsMapper;
import xzx.project.dao.ReturnOrderDetailMapper;
import xzx.project.dao.ReturnOrderMapper;
import xzx.project.dao.StoreHouseMapper;
import xzx.project.dao.SupplierMapper;
import xzx.project.dao.SysParamMapper;
import xzx.project.entity.Page;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	protected SupplierMapper supplierMapper;
	
	@Autowired
	protected  AccountMapper accountMapper;
	
	@Autowired
	protected  GoodsMapper goodsMapper;
	
	@Autowired
	protected  StoreHouseMapper storeHouseMapper;
	
	@Autowired
	protected BuyOrderMapper buyOrderMapper;
	
	@Autowired
	protected ReturnOrderMapper returnOrderMapper;
	
	@Autowired
	protected  BuyOrderDetailMapper buyOrderDetailMapper;
	
	@Autowired
	protected  ReturnOrderDetailMapper returnOrderDetailMapper;
	
	@Autowired
	protected  AccountRecordsMapper accountRecordsMapper;
	
	@Autowired
	protected SysParamMapper sysParamMapper;
	
	protected BaseMapper<T> baseMapper;
	
	@PostConstruct//在构造方法后，初化前执行
	private void initBaseMapper() throws Exception{
		//完成以下逻辑，需要对研发本身进行命名与使用规范
				//this关键字指对象本身，这里指的是调用此方法的实现类（子类）
				System.out.println("=======this :"+this);
				System.out.println("=======父类基本信息："+this.getClass().getSuperclass());
				System.out.println("=======父类和泛型的信息："+this.getClass().getGenericSuperclass());
				
				ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
				//获取第一个参数的class
				Class clazz = (Class)type.getActualTypeArguments()[0];
				System.out.println("=======class:"+clazz);
				//转化为属性名（相关的Mapper子类的引用名）Supplier  supplierMapper
				String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
				System.out.println("=======localField:"+localField);
				//getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
				Field field=this.getClass().getSuperclass().getDeclaredField(localField);
				System.out.println("=======field:"+field);
				System.out.println("=======field对应的对象:"+field.get(this));
				Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");
				
				System.out.println("=======baseField:"+baseField);
				System.out.println("=======baseField对应的对象:"+baseField.get(this));	
				//field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中		
				baseField.set(this, field.get(this));		
				System.out.println("========baseField对应的对象:"+baseMapper);

	}	

	public int insert(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.insert(entity);
	}

	public T selectByPk(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.selectByPk(entity);
	}

	public int deleteByPk(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.deleteByPk(entity);
	}

	public int updateByPk(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.updateByPk(entity);
	}

	public List<T> selectUseDyc(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.selectUseDyc(entity);
	}

	public Page<T> selectPageUseDyc(Page<T> page) {
		page.setLists(baseMapper.selectPageListUseDyc(page));
		page.setTotal(baseMapper.selectPageCountUseDyc(page));
		return page;
	}

	public int deleteByPks(String[] pks) {
		// TODO Auto-generated method stub
		return baseMapper.deleteByPks(pks);
	}
	
}