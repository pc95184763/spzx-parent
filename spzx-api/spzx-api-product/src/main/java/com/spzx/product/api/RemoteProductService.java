package com.spzx.product.api;
import com.spzx.common.core.constant.SecurityConstants;
import com.spzx.common.core.constant.ServiceNameConstants;
import com.spzx.common.core.domain.R;
import com.spzx.common.core.web.page.TableDataInfo;
import com.spzx.product.domain.*;
import com.spzx.product.domain.vo.ItemVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@FeignClient(contextId = "remoteProductService",
        value = ServiceNameConstants.PRODUCT_SERVICE,
        fallbackFactory = RemoteProductFallbackFactory.class)
public interface RemoteProductService {

    @GetMapping("/product/getTopSale")
    public R<List<ProductSku>> getTopSale( @RequestHeader(SecurityConstants.FROM_SOURCE) String source );

    @GetMapping("/product/skuList/{pageNum}/{pageSize}")
    public R<TableDataInfo> skuList( @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @SpringQueryMap SkuQuery skuQuery, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    @Operation(summary = "内部调用接口: 根据skuId查询商品详情页数据")
    @GetMapping("/product/getItemVOBySkuId/{skuId}")
    public R<ItemVO> getItemVOBySkuId( @PathVariable("skuId") Long skuId  ) ;


}

