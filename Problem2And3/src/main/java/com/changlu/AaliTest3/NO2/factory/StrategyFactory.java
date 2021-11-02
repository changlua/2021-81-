package com.changlu.AaliTest3.NO2.factory;

import com.changlu.AaliTest3.NO2.stragy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName StrategyFactory
 * @Author ChangLu
 * @Date 2021/10/29 18:00
 * @Description TODO
 */
public class StrategyFactory implements IFactory{
    private Map<String,IStrategy> strategyMap = new HashMap<>();

    @Override
    public IStrategy makeStrategy(String name) {
        IStrategy iStrategy = strategyMap.get(name);
        if(iStrategy == null){
            if(Objects.equals("Aged Wine",name)){
                iStrategy = new AgedWineStrategy();
                strategyMap.put(name,iStrategy);
            }else if(Objects.equals("Show Ticket",name)){
                iStrategy = new ShowTicketStrategy();
                strategyMap.put(name,iStrategy);
            }else if(Objects.equals("Sulfuras",name)){
                iStrategy = new SulfurasStrategy();
                strategyMap.put(name,iStrategy);
            }else if(Objects.equals("Cure",name)){
                iStrategy = new CureStrategy();
                strategyMap.put(name,iStrategy);
            }else{
                iStrategy = strategyMap.get("Common");
                if(iStrategy == null){
                    iStrategy = new CommonStrategy();
                    strategyMap.put("Common",iStrategy);
                }
            }
        }
        return iStrategy;
    }
}