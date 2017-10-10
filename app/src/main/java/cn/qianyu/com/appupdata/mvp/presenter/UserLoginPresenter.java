package cn.qianyu.com.appupdata.mvp.presenter;

import android.os.Handler;

import cn.qianyu.com.appupdata.mvp.bean.User;
import cn.qianyu.com.appupdata.mvp.blz.IUserBiz;
import cn.qianyu.com.appupdata.mvp.blz.OnLoginListener;
import cn.qianyu.com.appupdata.mvp.blz.UserBiz;
import cn.qianyu.com.appupdata.mvp.view.IUserLoginView;

/**
 * create to by : CatLoveEatFish .
 * 2017/10/9 Mr:Chen
 */

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login()
    {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener()
        {
            @Override
            public void loginSuccess(final User user)
            {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed()
            {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
