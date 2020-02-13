package cn.demo.dfs.mode.proxy;

/**
 * 静态代理
 */
public class StaticProxyFactory implements UserDAO {
    private UserDAO userDAO;

    public StaticProxyFactory(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save() {
        System.out.println("开启事务");
        userDAO.save();
        System.out.println("结束事务");
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        StaticProxyFactory staticProxyFactory = new StaticProxyFactory(userDAO);
        staticProxyFactory.save();
    }
}
