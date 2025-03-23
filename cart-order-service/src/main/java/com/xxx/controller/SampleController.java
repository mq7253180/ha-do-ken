package com.xxx.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quincy.sdk.annotation.auth.LoginRequired;
import com.xxx.o.TransactionDto;
import com.xxx.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {
	@Autowired
	private SampleService sampleService;
	/*
	 * 按状态查询(例如在7号分片中插入了7条数据，shardingKey传模后为7的数例如23就可以查出数据，传其他就查不出来)：http://localhost:8080/sample/find/status/23/0
	 * 更新id为4的status为1：http://localhost:8080/sample/update/23/4/1
	 * 再查少一条：http://localhost:8080/sample/find/status/23/0
	 * 按id查询单条：http://localhost:8080/sample/find/one/39/2
	 * 加了@LoginRequired注解要求必须登录的：http://localhost:8080/sample/find/one/login/39/2
	 */
	@GetMapping("/find/days/{shardingKey}/{days}")
	@ResponseBody
	public List<TransactionDto> find1(@PathVariable(required = true, name = "shardingKey") long shardingKey, 
			@PathVariable(required = true, name = "days") int days) {
		/*
		 * 查最近{days}天的
		 * 实际开发过程中shardingKey从session信息中获取，此处仅作演示
		 */
		return sampleService.findTransactions(shardingKey, new Date(System.currentTimeMillis()-(3600*24*days)));
	}

	@GetMapping("/find/status/{shardingKey}/{status}")
	@ResponseBody
	public List<TransactionDto> find2(@PathVariable(required = true, name = "shardingKey")long shardingKey, 
			@PathVariable(required = true, name = "status")int status) {
		return sampleService.findTransactions(shardingKey, status);
	}

	@GetMapping("/find/one/{shardingKey}/{id}")
	@ResponseBody
	public TransactionDto findOne(@PathVariable(required = true, name = "shardingKey")long shardingKey, 
			@PathVariable(required = true, name = "id")Long id) {
		return sampleService.findTransaction(shardingKey, id);
	}

	@LoginRequired//必须登录
	@GetMapping("/find/one/login/{shardingKey}/{id}")
	@ResponseBody
	public TransactionDto findOneByLogin(@PathVariable(required = true, name = "shardingKey")long shardingKey, 
			@PathVariable(required = true, name = "id")Long id) {
		return sampleService.findTransaction(shardingKey, id);
	}

	@GetMapping("/update/{shardingKey}/{id}/{status}")
	@ResponseBody
	public int update(@PathVariable(required = true, name = "shardingKey")long shardingKey, 
			@PathVariable(required = true, name = "id")Long id, 
			@PathVariable(required = true, name = "status")int status) {
		return sampleService.upateStatus(shardingKey, id, status);
	}

	@GetMapping("/redis/a/{key}/{val}")
	@ResponseBody
	public String testRedis(@PathVariable(required = true, name = "key")String key, 
			@PathVariable(required = true, name = "val")String val) {
		return sampleService.setAndReadRedis(null, key, val, 30);
	}

	@GetMapping("/redis/b/{key}/{val}")
	@ResponseBody
	public String testRedis2(@PathVariable(required = true, name = "key")String key, 
			@PathVariable(required = true, name = "val")String val) {
		return sampleService.setAndReadRedis(key, val, 30);
	}
}
