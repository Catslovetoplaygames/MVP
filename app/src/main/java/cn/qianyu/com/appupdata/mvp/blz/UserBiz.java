package cn.qianyu.com.appupdata.mvp.blz;

import cn.qianyu.com.appupdata.mvp.bean.User;

/**
 * create to by : CatLoveEatFish .
 * 2017/10/9 Mr:Chen
 */

public class UserBiz implements IUserBiz{
    public void login(final String username,final String password,final OnLoginListener loginListener){
        // 模拟子线程操作
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟登录成功
                if ("cn".equals(username)&&"123".equals(password)){
                    User user = new User();
                    user.setPassword(password);
                    user.setUsername(username);
                    loginListener.loginSuccess(user);
                }
                super.run();
            }
        }.start();
    }
}
