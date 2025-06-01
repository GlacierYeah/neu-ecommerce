package com.neuedu.controller;

import com.neuedu.pojo.Functions;
import com.neuedu.pojo.Result;
import com.neuedu.service.FunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class FunctionsController {

    @Autowired
    private FunctionsService functionsService;

    @GetMapping("/show")
    public Result<List<Functions>> show() {
        List<Functions> list =functionsService.show();
        return Result.success(list);
    }
}
