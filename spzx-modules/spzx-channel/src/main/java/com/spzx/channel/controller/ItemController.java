package com.spzx.channel.controller;

import com.spzx.channel.service.ItemService;
import com.spzx.common.core.web.controller.BaseController;
import com.spzx.common.core.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="商品详情模块")
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Resource
    ItemService itemService;



    @GetMapping("/testLock")
    public AjaxResult testLock() {
        itemService.testLock();
        return success();
    }


    @Operation(summary = "根据id 查询商品详情")
    @GetMapping("{skuId}")
    public AjaxResult getItemBySkuId(@PathVariable("skuId") Long skuId ) {
        return success( itemService.selectItemBySkuId(skuId) );
    }


}
