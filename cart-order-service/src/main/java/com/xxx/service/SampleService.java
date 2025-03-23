package com.xxx.service;

import java.util.Date;
import java.util.List;

import com.xxx.o.TransactionDto;

import redis.clients.jedis.Jedis;

public interface SampleService {
	public void optById(Long id, String arg);
	public List<TransactionDto> findTransactions(long shardingKey, Date createFrom);
	public List<TransactionDto> findTransactions(long shardingKey, int status);
	public TransactionDto findTransaction(long shardingKey, Long id);
	public int upateStatus(long shardingKey, Long id, int statusTo);
	public String setAndReadRedis(Jedis jedis, String key, String value, long expireSeconds);
	public String setAndReadRedis(String key, String value, long expireSeconds);
}