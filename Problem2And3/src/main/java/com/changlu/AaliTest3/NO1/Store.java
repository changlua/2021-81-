package com.changlu.AaliTest3.NO1;

/**
 * @ClassName Store
 * @Author ChangLu
 * @Date 2021/10/28 0:47
 * @Description TODO
 */
public class Store {
    Item[] items;

    // Please don't modify the signature of this method.
    public Store(Item[] items) {
        this.items = items;
    }

    // Please don't modify the signature of this method.
    public void updateValue() {
        for (int i = 0; i < items.length; i++) {
            if (!"Aged Wine".equals(items[i].name)  && !"Show Ticket".equals(items[i].name)) {
                if (items[i].value > 0) {
                    if (!"Sulfuras".equals(items[i].name)) {
                        //Cure
//                        items[i].value = items[i].value - 1;  //NO2
                        items[i].value = Math.max(items[i].value - 2, 0);
                    }
                }
            } else {
                //Aged Wine、Show Ticket
                if (items[i].value < 50) {
                    items[i].value = items[i].value + 1;
                    if ("Show Ticket".equals(items[i].name)) {
                        //-Show Ticket-
                        if (items[i].sellIn < 11) {
                            if (items[i].value < 50) {
                                items[i].value = items[i].value + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].value < 50) {
                                items[i].value = items[i].value + 1;
                            }
                        }
                    }
                }
            }

            //处理过了演出时间情况
            if (items[i].sellIn < 0) {
                if (!"Aged Wine".equals(items[i].name)) {
                    if (!"Show Ticket".equals(items[i].name)) {
                        //-Cure、Sulfuras-   //注意Sulfuras价值不变！
//                        if (items[i].value > 0) {  //NO1
//                        -Cure-
                        if (items[i].value > 0 && !"Sulfuras".equals(items[i].name)) {
                            items[i].value = Math.max(items[i].value - 2, 0);
                        }
                    } else {
                        //-Show Ticket-
                        items[i].value = 0;
                    }
                } else {
                    //-Aged Wine-
//                    items[i].value = items[i].value + 1; //NO3
                    if(items[i].value < 50){
                        items[i].value = items[i].value + 1;
                    }
                }
            }

            //-Aged Wine、Show Ticket、Cure-
            if ( !"Sulfuras".equals(items[i].name) ) {
                items[i].sellIn = items[i].sellIn - 1;
            }

        }
    }
}