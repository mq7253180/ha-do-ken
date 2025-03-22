package com.xxx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quincy.sdk.annotation.auth.LoginRequired;

@Controller
@RequestMapping("/sample")
public class SampleController {
	@LoginRequired
	@GetMapping("/test/a")
	@ResponseBody
	public String test1() {
		return "Success";
	}

	@GetMapping("/test/b")
	@ResponseBody
	public String test2() {
		return "Success";
	}
}
