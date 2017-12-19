package xzx.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import xzx.project.base.service.impl.BaseServiceImpl;
import xzx.project.entity.AccountRecords;
import xzx.project.service.AccountRecordsService;

@Service("accountRecordsService")
public class AccountRecordsServiceImpl extends BaseServiceImpl<AccountRecords> implements AccountRecordsService {

	public List<Map<String, Object>> selectSupplier(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return accountRecordsMapper.selectSupplier(paramMap);
	}

}
