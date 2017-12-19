package xzx.project.dao;

import java.util.List;

import xzx.project.base.dao.BaseMapper;
import xzx.project.entity.Account;

public interface AccountMapper extends BaseMapper<Account> {

	List<Account> login(Account account);
      
}