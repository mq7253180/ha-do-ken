package com.xxx.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.quincy.sdk.annotation.jdbc.ReadOnly;
import com.quincy.sdk.annotation.sharding.ShardingKey;
import com.xxx.dao.TransactionDao;
import com.xxx.o.TransactionDto;
import com.xxx.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private TransactionDao transactionDao;

	@ReadOnly
	@Override
	public List<TransactionDto> findTransactions(@ShardingKey long shardingKey, Date createFrom) {
		return transactionDao.find(createFrom);
	}

	@ReadOnly
	@Override
	public List<TransactionDto> findTransactions(@ShardingKey long shardingKey, int status) {
		return transactionDao.find(status);
	}

	@ReadOnly
	@Override
	public TransactionDto findTransaction(@ShardingKey long shardingKey, Long id) {
		return transactionDao.find(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public int upateStatus(@ShardingKey long shardingKey, Long id, int statusTo) {
		return transactionDao.upateStatus(statusTo, id);
	}
}
