package org.apache.micro.good.domain;


import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by HuanagYueWen on 2016-11-26.
 */
@Table(name="GOOD_PROPERTY_VALUE")
public class GoodPropertyValue {

    @Id
    private String id ;

    private String goodId ;

    private String property ;

    //value 并不是只有String 类型
    private String value ;

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
