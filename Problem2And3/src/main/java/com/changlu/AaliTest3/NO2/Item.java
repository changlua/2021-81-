package com.changlu.AaliTest3.NO2;


import com.changlu.AaliTest3.NO2.stragy.IStrategy;

/**
 * @ClassName Item
 * @Author ChangLu
 * @Date 2021/10/28 0:46
 * @Description TODO
 */
public class Item {

    public String name;

    public int sellIn;

    public int value;

    public IStrategy strategy;

    // Please don't modify the signature of this method.
    public Item(String name, int sellIn, int value) {
        this.name = name;
        this.sellIn = sellIn;
        this.value = value;
    }

    // Please don't modify the signature of this method.
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.value;
    }
}
