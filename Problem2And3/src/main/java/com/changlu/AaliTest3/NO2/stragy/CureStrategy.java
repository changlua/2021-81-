package com.changlu.AaliTest3.NO2.stragy;

import com.changlu.AaliTest3.NO2.Item;

/**
 * @ClassName CureStrategy
 * @Author ChangLu
 * @Date 2021/10/29 17:58
 * @Description TODO
 */
public class CureStrategy extends AbstractStrategy{
    @Override
    public void updateValue(Item item) {
        if (item.sellIn > 0) {
            for (int i = 0; i < 2; i++) {
                item.value = minusValue(item);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                item.value = minusValue(item);
            }
        }
        item.sellIn--;
    }
}