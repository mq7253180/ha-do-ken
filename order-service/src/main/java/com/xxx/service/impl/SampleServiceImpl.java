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
	/**
	 * 必须加@Transactional或@ReadOnly注解
	 * 必须传@ShardingKey注解的long型参数才能路由到指定分片
	 * 如果不是直接传的路由键，而是id需要加上snowFlake = true，框架会从id中通过雪花算法提取真正的shardingKey：@ShardingKey(snowFlake = true)
	 * 操作数据库必须包上这一层，否则无法正确路由
	 */
	@ReadOnly
	@Override
	public List<TransactionDto> findTransactions(@ShardingKey long shardingKey, Date createFrom) {
		/*
		 * 这里可以使用JPA或MyBatis，没有要求
		 * 如果想直接写简单SQL：也可以使用@JDBCDao、@ExecuteQuery、@ExecuteUpdate实现的框架
		 */
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
	/**
	 * 必须加@Transactional或@ReadOnly注解
	 * 必须传@ShardingKey注解的long型参数才能路由到指定分片
	 * 如果不是直接传的路由键，而是id需要加上snowFlake = true，框架会从id中通过雪花算法提取真正的shardingKey：@ShardingKey(snowFlake = true)
	 * 操作数据库必须包上这一层，否则无法正确路由
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public int upateStatus(@ShardingKey long shardingKey, Long id, int statusTo) {
		/*
		 * 这里可以使用JPA或MyBatis，没有要求
		 * 如果想直接写简单SQL：也可以使用@JDBCDao、@ExecuteQuery、@ExecuteUpdate实现的框架
		 */
		return transactionDao.upateStatus(statusTo, id);
	}
}
