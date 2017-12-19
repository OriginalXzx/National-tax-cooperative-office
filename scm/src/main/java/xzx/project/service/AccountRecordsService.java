package xzx.project.service;

import java.util.List;
import java.util.Map;

import xzx.project.base.service.BaseService;
import xzx.project.entity.AccountRecords;

public interface AccountRecordsService extends BaseService<AccountRecords> {
	List<Map<String, Object>> selectSupplier(Map<String,String> paramMap);
}
