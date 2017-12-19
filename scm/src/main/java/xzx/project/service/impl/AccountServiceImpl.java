package xzx.project.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzx.project.dao.AccountMapper;
import xzx.project.entity.Account;
import xzx.project.entity.Page;
import xzx.project.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired    
	private AccountMapper accountMapper;

	public List<Account> login(Account account) {
		
		return accountMapper.login(account);
		
	}

	public int insert(Account entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Account selectByPk(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteByPk(Account entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPk(Account entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Account> selectUseDyc(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Account> selectPageUseDyc(Page<Account> page) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteByPks(String[] pks) {
		return 0;
		// TODO Auto-generated method stub
		
	}
    
}
