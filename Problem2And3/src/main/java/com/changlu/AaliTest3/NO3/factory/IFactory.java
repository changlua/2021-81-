package com.changlu.AaliTest3.NO3.factory;

import com.changlu.AaliTest3.NO3.stragy.IStrategy;

/**
 * @ClassName IFactory
 * @Author ChangLu
 * @Date 2021/10/29 17:59
 * @Description TODO
 */
public interface IFactory {

    /**
     * 根据策略名称创建策略
     * @param name 策略名称
     * @return 策略实例
     */
    IStrategy makeStrategy(String name);

}