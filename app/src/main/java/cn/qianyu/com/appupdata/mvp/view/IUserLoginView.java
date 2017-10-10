package cn.qianyu.com.appupdata.mvp.view;

import cn.qianyu.com.appupdata.mvp.bean.User;

/**
 * create to by : CatLoveEatFish .
 * 2017/10/9 Mr:Chen
 */

public interface IUserLoginView {
    String getUserName();
    String getPassword();
    void clearUserName();
    void clearPassword();
    void showLoading();
    void hideLoading();
    void toMainActivity(User user);
    void showFailedError();
}
