package com.xxx.dao;

import java.util.Date;
import java.util.List;

import com.quincy.sdk.annotation.jdbc.ExecuteQuery;
import com.quincy.sdk.annotation.jdbc.ExecuteUpdate;
import com.quincy.sdk.annotation.jdbc.JDBCDao;

import com.xxx.o.TransactionDto;

@JDBCDao
public interface TransactionDao {
	@ExecuteQuery(sql = "SELECT id AS application_name,bean_name,method_name,status,flag_for_cron_job FROM s_transaction WHERE creation_time>?", returnItemType = TransactionDto.class)
	public List<TransactionDto> find(Date createFrom);
	@ExecuteQuery(sql = "SELECT id AS application_name,bean_name,method_name,status,flag_for_cron_job FROM s_transaction WHERE status=?", returnItemType = TransactionDto.class)
	public List<TransactionDto> find(int status);
	@ExecuteQuery(sql = "SELECT id AS application_name,bean_name,method_name,status,flag_for_cron_job FROM s_transaction WHERE id=?", returnItemType = TransactionDto.class)
	public TransactionDto find(Long id);
	@ExecuteUpdate(sql = "UPDATE s_transaction SET status=? WHERE id=?")
	public int upateStatus(int statusTo, Long id);
}
