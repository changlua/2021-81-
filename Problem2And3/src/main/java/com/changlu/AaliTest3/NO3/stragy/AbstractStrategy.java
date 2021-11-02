package com.changlu.AaliTest3.NO3.stragy;

import com.changlu.AaliTest3.NO3.Item;

/**
 * @ClassName AbstractStrategy
 * @Author ChangLu
 * @Date 2021/10/30 13:54
 * @Description TODO
 */
public abstract class AbstractStrategy implements IStrategy {

    public int addValue(Item item){
        return item.value < 50 ? item.value + 1 : 50;
    }

    public int minusValue(Item item){
        return item.value > 0 ? item.value - 1 : 0;
    }
}