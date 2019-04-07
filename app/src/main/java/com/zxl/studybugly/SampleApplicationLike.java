package com.zxl.studybugly;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.tinker.entry.DefaultApplicationLike;


public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.SampleApplicationLike";

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        //Bugly.init(getApplication(), "cd0aeb3e92", false);
        initTxBeta();
    }

    //自定义自己的更新apk属性，如果要设置自定义布局，一定要在Bugly.init（）之前设置，否则设置不生效
    void initTxBeta(){

        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;

        Beta.largeIconId = R.mipmap.girl;
        //设置通知的小图标（默认无）
        Beta.smallIconId = R.mipmap.girl;
        //设置banner（默认无）
        Beta.defaultBannerId = R.mipmap.girl;

        //是否自动初始化，默认为true
        Beta.autoInit = true;
        //是否检查是否有版本更新，默认为true，如果设置为false，需要手动调用 Beta.checkUpgrade();检查是否有版本更新
        Beta.autoCheckUpgrade = true;
        //设置升级检查的周期（默认为0L）
        Beta.upgradeCheckPeriod = 60 * 1000;
        //设置延时启动初始化sdk，避免app启动过慢
        Beta.initDelay = 1 * 1000;
        //设置通知的大图标（默认无）
        Beta.largeIconId = R.mipmap.girl;
        //设置通知的小图标（默认无）
        Beta.smallIconId = R.mipmap.girl;
        //设置banner（默认无）
        Beta.defaultBannerId = R.mipmap.girl;
        //设置开启显示打断策略（默认true）
        Beta.showInterruptedStrategy = true;
        //添加可显示弹窗的Activity,默认为空，即所有的activity都可以弹出升级提示框，这个如果不是强制更新，最好设置为主界面
        Beta.canShowUpgradeActs.add(MainActivity.class);

        //设置是否显示通知栏的下载进度条
        Beta.enableNotification = true;
        //设置wifi下自动下载
        Beta.autoDownloadOnWifi = false;

        //设置是否开启热更新的能力
        Beta.enableHotfix = true;
        //设置是否显示apk版本信息（更新内容，更新说明）
        Beta.canShowApkInfo = false;
        //初始化的统一接口
        //Bugly.init(this, "your APP_ID", false);

        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;

        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
        Beta.tipsDialogLayoutId = R.layout.tips_dialog;


        /**
         *  如果想监听升级对话框的生命周期事件，可以通过设置OnUILifecycleListener接口
         *  回调参数解释：
         *  context - 当前弹窗上下文对象
         *  view - 升级对话框的根布局视图，可通过这个对象查找指定view控件
         *  upgradeInfo - 升级信息
         */
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:
                //upgrade_dialog.xml 是通过这种方式自定义弹出框的布局文件
                // 通过id方式获取控件，并更改imageview图片
//                ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
//                imageView.setImageResource(R.mipmap.girl);
//                TextView tvTitle = (TextView) view.findViewWithTag("beta_title");
//                tvTitle.setText("beta_title");
//
//                TextView tv = (TextView) view.findViewWithTag("beta_upgrade_info");
//                tv.setVisibility(View.GONE);
//
//                TextView tv1 = (TextView) view.findViewWithTag("beta_upgrade_feature");
//                tv1.setText("beta_title");
                //tv1.setVisibility(View.GONE);



                // 通过tag方式获取控件，并更改布局内容
//                TextView textView = (TextView) view.findViewWithTag("textview");
//                textView.setText("my custom text");

                // 更多的操作：比如设置控件的点击事件
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                });
                System.out.println("-------->"+"onCreate");
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                System.out.println("-------->"+"onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                System.out.println("-------->"+"onResume");

                //想要在代码中修改弹出框的样式，字体，显示内容，必须要在onresume生命周期中进行修改
                TextView tvTitle = (TextView) view.findViewWithTag("beta_title");
                tvTitle.setText("beta_title");

                TextView tv = (TextView) view.findViewWithTag("beta_upgrade_info");
                tv.setVisibility(View.GONE);

                TextView tv1 = (TextView) view.findViewWithTag("beta_upgrade_feature");
                tv1.setText("beta_title");
//                LinearLayout tv11 = (LinearLayout) view.findViewWithTag("test");
//                tv11.setBackgroundResource(R.drawable.update);

            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {

            }
        };


        Bugly.init(getApplication(), "cd0aeb3e92", true);

    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}
