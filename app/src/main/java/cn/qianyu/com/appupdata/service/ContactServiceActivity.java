package cn.qianyu.com.appupdata.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.qianyu.com.appupdata.R;
import cn.qianyu.com.appupdata.updata.MainActivity;

/**
 * create to by : CatLoveEatFish .
 * 2017/9/26 Mr:Chen
 */

public class ContactServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ContactServiceActivity";
    private Button startService;

    private Button stopService;
    private Button bindService;
    private Button notices;
    private Button unbindService;
    private NotificationManager notificationManager;
    private ContactService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (ContactService.MyBinder) service;
            myBinder.startDownload();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        notices = (Button) findViewById(R.id.notices);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        notices.setOnClickListener(this);
        notificationManager
                = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, ContactService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, ContactService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, ContactService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.notices:
                Notification notification;

                Notification.Builder builder;

                //Api大于等于HONEYCOMB,使用Notification.Builder创建notification
                builder = new Notification.Builder(ContactServiceActivity.this);
                builder.setTicker("滚动提示");  //N滚动提示
                builder.setContentTitle("应该是标题");   //标题
                builder.setContentText("这个是内容");    //内容
                builder.setSmallIcon(android.R.drawable.sym_action_chat);  //Icon 如果不设置Icon,Notification不会跳出来

                builder.setAutoCancel(true);    //点击以后是否自动清除,true-点击后自动清除,false-点击以后不会自动清除
//                builder.setContentInfo("右侧提示");
                //铃声,震动,提示灯默认设置.
                // DEFAULT_ALL会忽略已经设置的震动,声音,提示等设置
//                除此还有三个参数 DEFAULT_SOUND - 默认声音, DEFAULT_VIBRATE - 默认震动, DEFAULT_LIGHTS - 默认提示灯
                builder.setDefaults(Notification.DEFAULT_ALL);
                //但设置了LargeIcon,小图标依然会在状态栏中显示.当时下拉状态的时候大图会显示在左边,小图片给显示在右下角
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.sym_action_call));
                //在右边显示一个数量,等价于setContentInfo函数.如果有设置setContentInfo函数,那么本函数会给覆盖
//                builder.setNumber(12);
                //是否常驻状态栏
                //builder.setOngoing(true);
                //setOnlyAlertOnce 是否提示一次.true - 如果Notification已经存在状态栏即使在调用notify函数也不会更新.
                builder.setOnlyAlertOnce(true);
                //滚动条
                //setProgress (int max, int progress, boolean indeterminate)
                //indeterminate true - 不确定的,不会显示进度,false - 根据max和progress情况显示进度条
//                builder.setProgress(100, 50, false);
                //显示一个计数器
//                builder.setUsesChronometer(true);

                //点击事件
                // PendingIntent ClickPending = PendingIntent.getActivity(MainActivity.this, 0, new Intent().setClass(MainActivity.this, NotificationActivity.class), 0);
                //builder.setContentIntent(ClickPending);
                //清除事件
                PendingIntent DelPending = PendingIntent
                        .getActivity(this, 0, new Intent()
                                .setClass(this, MainActivity.class), 0);
                builder.setDeleteIntent(DelPending);

                notification = builder.build();
                notificationManager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
