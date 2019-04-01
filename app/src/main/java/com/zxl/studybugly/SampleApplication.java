package com.zxl.studybugly;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class SampleApplication extends TinkerApplication {

    private static final String TAG = "OnUILifecycleListener";

    public SampleApplication() {
        /**
         * 参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，
         * default: TINKER_ENABLE_ALL,支持全部类型
         * 参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
         * 参数3：loaderClassName Tinker的加载器，使用默认即可
         * 参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
         */
        super(ShareConstants.TINKER_ENABLE_ALL, "com.zxl.studybugly.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //不能再这里设置自己的自定义更新格式，要在SampleApplicationLike中的oncreate中
        //initTxBeta();
    }

    /**
     * 自定义初始化bugly的方法参数，如果你需要改变bugly更新的设置，你可以按照你的业务需求进行改变配置
     *
     * 建议:参照，天猫，优酷，淘宝等多款大型app的更新对话框，本人觉得还是使用bug默认的弹框较好，这样更加主流和尽量原生
     *
     *
     */

//    void initTxBeta(){
//        //是否自动初始化，默认为true
//        Beta.autoInit = true;
//        //是否检查是否有版本更新，默认为true，如果设置为false，需要手动调用 Beta.checkUpgrade();检查是否有版本更新
//        Beta.autoCheckUpgrade = true;
//        //设置升级检查的周期（默认为0L）
//        Beta.upgradeCheckPeriod = 60 * 1000;
//        //设置延时启动初始化sdk，避免app启动过慢
//        Beta.initDelay = 1 * 1000;
//        //设置通知的大图标（默认无）
//        Beta.largeIconId = R.mipmap.girl;
//        //设置通知的小图标（默认无）
//        Beta.smallIconId = R.mipmap.girl;
//        //设置banner（默认无）
//        Beta.defaultBannerId = R.mipmap.girl;
//        //设置开启显示打断策略（默认true）
//        Beta.showInterruptedStrategy = true;
//        //添加可显示弹窗的Activity,默认为空，即所有的activity都可以弹出升级提示框
//        //Beta.canShowUpgradeActs.add(MainActivity.class);
//        //设置自定义布局，因为要保持风格一致，我们需要对不同的控件id设置和系统相同的tag（自定义布局，默认为空）
//        //Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
//        //设置是否显示通知栏的下载进度条
//        Beta.enableNotification = true;
//        //设置wifi下自动下载
//        Beta.autoDownloadOnWifi = false;
//
//        //设置是否开启热更新的能力
//        Beta.enableHotfix = true;
//        //初始化的统一接口
//        //Bugly.init(this, "your APP_ID", false);
//
//        /**
//         *  设置自定义升级对话框UI布局
//         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
//         *  标题：beta_title，如：android:tag="beta_title"
//         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
//         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
//         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
//         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
//         *  详见layout/upgrade_dialog.xml
//         */
//        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
//
//        /**
//         * 设置自定义tip弹窗UI布局
//         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
//         *  标题：beta_title，如：android:tag="beta_title"
//         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
//         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
//         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
//         *  详见layout/tips_dialog.xml
//         */
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;
//
//        //设置是否显示升级对话框中的apk信息
//        Beta.canShowApkInfo = false;
//        Beta.upgradeDialogLifecycleListener  = new UILifecycleListener<UpgradeInfo>() {
//            @Override
//            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
//
////                ImageView imageView = view.findViewById();
//
//                TextView textView = view.findViewWithTag("beta_upgrade_info");
//                textView.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
//
//            }
//
//            @Override
//            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
//
//            }
//
//            @Override
//            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
//
//            }
//
//            @Override
//            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
//
//            }
//
//            @Override
//            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
//
//            }
//        };
//         Bugly.init(getApplicationContext(), "cd0aeb3e92", true);
//
//    }

}