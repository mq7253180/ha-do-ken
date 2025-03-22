package com.xxx.service;

import java.util.Date;
import java.util.List;

import com.xxx.o.TransactionDto;

public interface SampleService {
	public List<TransactionDto> findTransactions(long shardingKey, Date createFrom);
	public List<TransactionDto> findTransactions(long shardingKey, int status);
	public TransactionDto findTransaction(long shardingKey, Long id);
	public int upateStatus(long shardingKey, Long id, int statusTo);
}