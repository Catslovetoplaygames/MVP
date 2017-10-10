package cn.qianyu.com.appupdata.mvp.blz;

/**
 * create to by : CatLoveEatFish .
 * 2017/10/9 Mr:Chen
 */

public interface IUserBiz {
    void login(String username,String password,OnLoginListener loginListener);
}
