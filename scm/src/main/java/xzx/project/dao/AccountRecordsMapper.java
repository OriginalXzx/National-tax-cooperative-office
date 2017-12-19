package xzx.project.dao;

import java.util.List;
import java.util.Map;

import xzx.project.base.dao.BaseMapper;
import xzx.project.entity.AccountRecords;

public interface AccountRecordsMapper extends BaseMapper<AccountRecords> {
	List<Map<String, Object>> selectSupplier(Map<String, String> paramMap);
}