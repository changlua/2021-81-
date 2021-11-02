package com.changlu.AaliTest3.NO2.factory;

import com.changlu.AaliTest3.NO2.stragy.IStrategy;

/**
 * @ClassName IFactory
 * @Author ChangLu
 * @Date 2021/10/29 17:59
 * @Description TODO
 */
public interface IFactory {

    IStrategy makeStrategy(String name);

}