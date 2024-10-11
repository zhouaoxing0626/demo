package com.asiainfotest.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfotest.demo.service.HallowService;
import com.asiainfotest.demo.utils.AESUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HallowController {

    @Autowired
    private HallowService hallowService;

    @RequestMapping(value = "/testEncrypt", method = {RequestMethod.POST})
    public String testEncrypt(@RequestBody String cond) throws Exception {
        JSONObject jsonObject = JSON.parseObject(cond);
        String key = jsonObject.getString("key");
        String data = jsonObject.getString("data");
        return AESUtil.encrypt(key, data);
    }

    @ApiOperation("2.307商品路由渠道切换接口")
    @RequestMapping(value = "/modProdChannel" , produces = "application/json; charset=utf-8", method = {RequestMethod.POST})
    public String modProdChannel(@RequestBody String cond) throws Exception {
        log.info("modProdChannel前端入参数据：------------------------{}", cond);
        JSONObject jsonObject = JSON.parseObject(cond);
        JSONObject result = hallowService.modProdChannel(jsonObject);
        log.info("modProdChannel接口接口out：{}", result.toJSONString());
        return result.toJSONString();
    }
}
