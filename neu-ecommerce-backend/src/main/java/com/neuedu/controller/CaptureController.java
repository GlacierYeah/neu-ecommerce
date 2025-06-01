package com.neuedu.controller;

import com.neuedu.utils.CaptureConfig;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {

    //用来显示验证码
    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置验证码的图片
        SpecCaptcha captcha = new SpecCaptcha(140, 40, 5);
        //设置验证码的字体
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
        //将验证码显示出来
        captcha.out(response.getOutputStream());
    }
}
