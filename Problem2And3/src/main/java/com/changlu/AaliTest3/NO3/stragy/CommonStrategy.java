package com.changlu.AaliTest3.NO3.stragy;

import com.changlu.AaliTest3.NO3.Constant;
import com.changlu.AaliTest3.NO3.Item;

/**
 * @ClassName CommonStrategy
 * @Author ChangLu
 * @Date 2021/10/29 18:06
 * @Description TODO
 */
public class CommonStrategy extends AbstractStrategy {
    @Override
    public void updateValue(Item item) {
        if(item.sellIn > 0){
            item.value = minusValue(item);
        }else{
            for (int i = 0; i < Constant.ADD_TWO; i++) {
                item.value = minusValue(item);
            }
        }
        item.sellIn--;
    }
}