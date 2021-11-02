package com.changlu.AaliTest3.NO3.stragy;

import com.changlu.AaliTest3.NO3.Constant;
import com.changlu.AaliTest3.NO3.Item;

/**
 * @ClassName AgendWine
 * @Author ChangLu
 * @Date 2021/10/29 17:55
 * @Description TODO
 */
public class AgedWineStrategy extends AbstractStrategy {
    @Override
    public void updateValue(Item item) {
        //剩余时间内
        if (item.sellIn > 0) {
            item.value = addValue(item);
        } else {
            //过了剩余范围
            for (int i = 0; i < Constant.ADD_TWO; i++) {
                item.value = item.value < 50 ? item.value + 1 : 50;
            }
        }
        item.sellIn--;
    }
}