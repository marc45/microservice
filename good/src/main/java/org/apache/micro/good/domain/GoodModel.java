package org.apache.micro.good.domain;

import java.util.List;

/**
 * Created by HuangYueWen on 2016-11-24.
 */
public class GoodModel {

    private Good good ;

    private List<GoodProperty> goodProperties ;

    private List<GoodPicture> goodPictures ;

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public List<GoodProperty> getGoodProperties() {
        return goodProperties;
    }

    public void setGoodProperties(List<GoodProperty> goodProperties) {
        this.goodProperties = goodProperties;
    }

    public List<GoodPicture> getGoodPictures() {
        return goodPictures;
    }

    public void setGoodPictures(List<GoodPicture> goodPictures) {
        this.goodPictures = goodPictures;
    }
}
