package com.spzx.product.domain.vo;

import com.spzx.product.domain.Product;
import com.spzx.product.domain.ProductSku;
import com.spzx.product.domain.SkuPrice;
import com.spzx.product.domain.SkuStockVo;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class ItemVO {
    ProductSku productSku;

    SkuPrice skuPrice;

    Product product;

    List<String> sliderUrlList;

    List<String> detailsimagesUrlList;

    SkuStockVo skuStockVo;

    Map<String, String> skuSpecValueMap;
}

