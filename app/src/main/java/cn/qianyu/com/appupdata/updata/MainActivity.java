package cn.qianyu.com.appupdata.updata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.qianyu.com.appupdata.R;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //本地测试检测是否有新版本发布
                UpdateVersionUtil.localCheckedVersion(MainActivity.this,new UpdateVersionUtil.UpdateListener() {

                    @Override
                    public void onUpdateReturned(int updateStatus, VersionInfo versionInfo) {
                        //判断回调过来的版本检测状态
                        switch (updateStatus) {
                            case UpdateStatus.YES:
                                //弹出更新提示
                                UpdateVersionUtil.showDialog(MainActivity.this,versionInfo);
                                break;
                            case UpdateStatus.NO:
                                //没有新版本
                                ToastUtils.showToast(getApplicationContext(), "已经是最新版本了!");
                                break;
                            case UpdateStatus.NOWIFI:
                                //当前是非wifi网络
                                ToastUtils.showToast(getApplicationContext(), "只有在wifi下更新！");
//							DialogUtils.showDialog(MainActivity.this, "温馨提示","当前非wifi网络,下载会消耗手机流量!", "确定", "取消",new DialogOnClickListenner() {
//								@Override
//								public void btnConfirmClick(Dialog dialog) {
//									dialog.dismiss();
//									//点击确定之后弹出更新对话框
//									UpdateVersionUtil.showDialog(SystemActivity.this,versionInfo);
//								}
//
//								@Override
//								public void btnCancelClick(Dialog dialog) {
//									dialog.dismiss();
//								}
//							});
                                break;
                            case UpdateStatus.ERROR:
                                //检测失败
                                ToastUtils.showToast(getApplicationContext(), "检测失败，请稍后重试！");
                                break;
                            case UpdateStatus.TIMEOUT:
                                //链接超时
                                ToastUtils.showToast(getApplicationContext(), "链接超时，请检查网络设置!");
                                break;
                        }
                    }
                });


                //访问服务器 试检测是否有新版本发布
                 UpdateVersionUtil.localCheckedVersion(MainActivity.this,new UpdateVersionUtil.UpdateListener() {

                @Override
                public void onUpdateReturned(int updateStatus, VersionInfo versionInfo) {
                //判断回调过来的版本检测状态
                switch (updateStatus) {
                case UpdateStatus.YES:
                //弹出更新提示
                UpdateVersionUtil.showDialog(MainActivity.this,versionInfo);
                break;
                case UpdateStatus.NO:
                //没有新版本
                ToastUtils.showToast(getApplicationContext(), "已经是最新版本了!");
                break;
                case UpdateStatus.NOWIFI:
                //当前是非wifi网络
                ToastUtils.showToast(getApplicationContext(), "只有在wifi下更新！");
                //							DialogUtils.showDialog(MainActivity.this, "温馨提示","当前非wifi网络,下载会消耗手机流量!", "确定", "取消",new DialogOnClickListenner() {
                //								@Override
                //								public void btnConfirmClick(Dialog dialog) {
                //									dialog.dismiss();
                //									//点击确定之后弹出更新对话框
                //									UpdateVersionUtil.showDialog(SystemActivity.this,versionInfo);
                //								}
                //
                //								@Override
                //								public void btnCancelClick(Dialog dialog) {
                //									dialog.dismiss();
                //								}
                //							});
                break;
                case UpdateStatus.ERROR:
                //检测失败
                ToastUtils.showToast(getApplicationContext(), "检测失败，请稍后重试！");
                break;
                case UpdateStatus.TIMEOUT:
                //链接超时
                ToastUtils.showToast(getApplicationContext(), "链接超时，请检查网络设置!");
                break;
                }
                }
                });

            }
        });
    }
}
