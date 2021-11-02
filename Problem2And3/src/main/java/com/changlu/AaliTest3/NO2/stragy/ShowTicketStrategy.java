package com.changlu.AaliTest3.NO2.stragy;

import com.changlu.AaliTest3.NO2.Item;

/**
 * @ClassName ShowTicketStrategy
 * @Author ChangLu
 * @Date 2021/10/29 17:57
 * @Description TODO
 */
public class ShowTicketStrategy extends AbstractStrategy{
    @Override
    public void updateValue(Item item) {
        //剩余时间范围
        if (item.sellIn > 0) {
            item.value = addValue(item);
            if (item.sellIn < 11) {
                item.value = addValue(item);
            }
            if (item.sellIn < 6) {
                item.value = addValue(item);
            }
        } else {  //过期
            item.value = 0;
        }
        item.sellIn--;
    }
}