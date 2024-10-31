package com.spzx.channel.service;

import com.spzx.product.domain.vo.ItemVO;

public interface ItemService {

    ItemVO selectItemBySkuId(Long skuId);

    void testLock();
}
