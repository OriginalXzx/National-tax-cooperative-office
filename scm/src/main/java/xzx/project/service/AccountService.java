package xzx.project.service;

import java.util.List;

import xzx.project.base.service.BaseService;
import xzx.project.entity.Account;

public interface AccountService extends BaseService<Account>{
      List<Account> login(Account account);
}
