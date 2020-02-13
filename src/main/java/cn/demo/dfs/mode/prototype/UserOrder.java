package cn.demo.dfs.mode.prototype;

import java.io.Serializable;
import java.util.Date;

public class UserOrder  implements Cloneable,Serializable {
    private static final long serialVersionUID = -3487218732843612152L;
    private String userId;
    private String orderNo;
    private Integer num;
    private double price;
    private Date createDate;

    public UserOrder(String userId, String orderNo, Integer num, double price, Date createDate) {
        this.userId = userId;
        this.orderNo = orderNo;
        this.num = num;
        this.price = price;
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
