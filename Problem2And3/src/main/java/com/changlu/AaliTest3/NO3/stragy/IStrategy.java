package com.changlu.AaliTest3.NO3.stragy;

import com.changlu.AaliTest3.NO3.Item;

/**
 * @ClassName IStrategy
 * @Author ChangLu
 * @Date 2021/10/29 16:38
 * @Description TODO
 */
public interface IStrategy {
    /**
     * 更新值
     * @param item item实例
     */
    void updateValue(Item item);
}
