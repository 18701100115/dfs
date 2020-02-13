package cn.demo.dfs.mode.prototype;

import java.io.Serializable;

public class UserDetail  implements Cloneable,Serializable {
    private static final long serialVersionUID = -9215728420074686690L;
    private String userId;
    private String email;
    private String password;
    private String address;

    public UserDetail(String userId, String email, String password, String address) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

