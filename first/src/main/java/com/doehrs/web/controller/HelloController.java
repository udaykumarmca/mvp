package com.doehrs.web.controller;

import com.doehrs.dto.CommonResponse;
import com.doehrs.service.SecondService;
import com.doehrs.dto.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/hello")
public class HelloController implements Serializable {

    @Autowired
    private SecondService secondService;

    @GetMapping("/")
    public CommonResponse hello() {
        return CommonResponse.builder().success(true)
                .data("SUCCESS").build();
    }

    @GetMapping("/{id}")
    public CommonResponse helloId(@PathVariable("id") Integer id) {
        HelloResponse helloResponse = HelloResponse.builder().id(id).message("SUCCESS").build();
        return CommonResponse.builder().success(true)
                .data(helloResponse).build();
    }

    @GetMapping("/second/hello")
    public CommonResponse helloFromSecondBy() {
        return secondService.getHelloFromSecondBy();
    }

    @GetMapping("/second/hello/{id}")
    public CommonResponse helloFromSecondById(@PathVariable("id") Integer id) {
        HelloResponse helloResponse = secondService.getHelloById(id);
        return CommonResponse.builder().success(true)
                .data(helloResponse).build();
    }


}
