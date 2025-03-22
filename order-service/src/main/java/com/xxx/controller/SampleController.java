package com.xxx.controller;


import java.util.Date;
import java.util.List;

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
	private SampleService sampleService;

	@GetMapping("/find/{shardingKey}/{days}")
	@ResponseBody
	public List<TransactionDto> find1(@PathVariable(required = true, name = "shardingKey") long shardingKey, 
			@PathVariable(required = true, name = "days") int days) {
		/*
		 * 查{days}天前的
		 * 实际开发过程中shardingKey从session信息中获取，此处仅作演示
		 */
		return sampleService.findTransactions(shardingKey, new Date(System.currentTimeMillis()-(3600*24*days)));
	}

	@GetMapping("/find/{shardingKey}/{status}")
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
}
