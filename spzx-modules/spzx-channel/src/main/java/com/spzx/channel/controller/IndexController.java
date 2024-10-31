package com.spzx.channel.controller;

import com.spzx.channel.service.IIndexService;
import com.spzx.common.core.web.controller.BaseController;
import com.spzx.common.core.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "首页管理")
@RestController
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

    @Resource
    private IIndexService indexService;

    //首页数据
    @Operation(summary = "获取首页数据")
    @GetMapping
    public AjaxResult index() {
        return success(indexService.getIndexData() );
    }

}
