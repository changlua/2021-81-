package com.changlu.AaliTest3.NO2;

import com.changlu.AaliTest3.NO2.factory.IFactory;
import com.changlu.AaliTest3.NO2.factory.StrategyFactory;

/**
 * @ClassName Store
 * @Author ChangLu
 * @Date 2021/10/28 0:47
 * @Description TODO
 */
public class Store {
    Item[] items;
    private static IFactory strategyFactory = new StrategyFactory();

    // Please don't modify the signature of this method.
    public Store(Item[] items) {
        this.items = items;
        for (Item item : this.items) {
            item.strategy = strategyFactory.makeStrategy(item.name);
        }
    }

    // Please don't modify the signature of this method.
    public void updateValue() {
        for (Item item : this.items) {
            item.strategy.updateValue(item);
        }
    }

}