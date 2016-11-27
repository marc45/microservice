package org.apache.micro.good.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by HuanagYueWen on 2016-11-15.
 */
@Table(name="GOOD_PICTURE")
public class GoodPicture {

    @Id
    private String id ;

    private String goodId ;

    private String name ;

    private String url ;

    private boolean isMain ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
