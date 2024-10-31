package com.spzx.channel.service.impl;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.spzx.channel.service.ItemService;
import com.spzx.common.core.constant.SecurityConstants;
import com.spzx.common.core.domain.R;
import com.spzx.common.core.exception.ServiceException;
import com.spzx.product.api.RemoteProductService;
import com.spzx.product.domain.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    RemoteProductService remoteProductService;

    @Resource
    RedisTemplate redisTemplate;


    public ItemVO selectItemBySkuId(Long skuId) {
        // 1、查询是否存在缓存  如果存在直接返回
        String key = "spzx:channel:item:" + skuId;
//        redisTemplate.
        Object cache = redisTemplate.opsForValue().get(key);
        if (!ObjectUtils.isEmpty(cache) ) {
            return (ItemVO) cache;
        }

        //2.查询缓存失败：远程调用查询数据库
        R<ItemVO> itemVO = remoteProductService.getItemVOBySkuId(skuId );

        if ( itemVO.getCode() != 200) {
            throw new ServiceException( itemVO.getMsg() );
        }

        //3.缓存数据
        redisTemplate.opsForValue().set(key, itemVO.getData() );

        return itemVO.getData();
    }
    //
    public synchronized  void testLock() {
        //1` 获取redis中的number
        Object obj = redisTemplate.opsForValue().get("number");
        if ( obj == null) {
            redisTemplate.opsForValue().set("number", 0);
            obj = 0;
        }
        // 2 执行number + 1
        Integer number = Integer.parseInt( obj.toString() );
        number = number + 1;
        // 3
        redisTemplate.opsForValue().set("number", number);

    }


}

