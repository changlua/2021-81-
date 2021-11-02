package com.changlu.AaliTest3.NO3;

import com.changlu.AaliTest3.NO3.factory.IFactory;
import com.changlu.AaliTest3.NO3.stragy.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Store
 * @Author ChangLu
 * @Date 2021/10/28 0:47
 * @Description TODO
 */
public class Store implements IFactory{
    Item[] items;
    private Map<String, IStrategy> strategyMap = new HashMap<>();

    // Please don't modify the signature of this method.
    public Store(Item[] items) {
        this.items = items;
        for (Item item : this.items) {
            item.strategy = makeStrategy(item.name);
        }
    }

    // Please don't modify the signature of this method.
    public void updateValue() {
        for (Item item : this.items) {
            item.strategy.updateValue(item);
        }
    }

    //圈复杂度10->7
    @Override
    public IStrategy makeStrategy(String name) {
        if(name == null){
            name = "common";
        }
        IStrategy iStrategy = strategyMap.get(name);
        if(iStrategy == null){
            switch (name){
                case "Aged Wine":
                    iStrategy = new AgedWineStrategy();
                    break;
                case "Show Ticket":
                    iStrategy = new ShowTicketStrategy();
                    break;
                case "Sulfuras":
                    iStrategy = new SulfurasStrategy();
                    break;
                case "Cure":
                    iStrategy = new CureStrategy();
                    break;
                default :
                    iStrategy = strategyMap.get("Common");
                    if(iStrategy != null){
                        return iStrategy;
                    }
                    iStrategy = new CommonStrategy();
                    name = "Common";
            }
            strategyMap.put(name,iStrategy);
        }
        return iStrategy;
    }
}