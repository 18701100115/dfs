package cn.demo.dfs.mode.prototype;

import java.util.Date;

/**
 * 浅拷贝
 */
public class User implements Cloneable {
    private String id;
    private String name;
    private UserDetail userDetail;
    private UserOrder userOrder;

    public User(String id, String name, UserDetail userDetail, UserOrder userOrder) {
        this.id = id;
        this.name = name;
        this.userDetail = userDetail;
        this.userOrder = userOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        UserDetail userDetail = new UserDetail("1","111@163.com","admin123","安居里");
        UserOrder userOrder = new UserOrder("1","N20200202",3,1223d,new Date());
        User user =new User("1","2",userDetail,userOrder);
        User user1 = null;
        try {
            user1 = (User)user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(user1==user);
        System.out.println(user1.getUserOrder()==user.getUserOrder());
        System.out.println(user1.getUserDetail()==user.getUserDetail());
    }
}
