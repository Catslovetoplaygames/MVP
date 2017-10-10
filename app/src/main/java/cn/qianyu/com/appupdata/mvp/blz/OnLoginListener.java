package cn.qianyu.com.appupdata.mvp.blz;

import cn.qianyu.com.appupdata.mvp.bean.User;

/**
 * create to by : CatLoveEatFish .
 * 2017/10/9 Mr:Chen
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
