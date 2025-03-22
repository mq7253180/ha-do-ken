package com.xxx.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="xxx", url="https://ducati.jep8566.com")
public interface TestFeign {

}
