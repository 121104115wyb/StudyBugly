package com.zxl.studybugly;


import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

public class MainActivity extends AppCompatActivity {
    private Button testBug, update, test;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBug = findViewById(R.id.testBug);

        update = findViewById(R.id.update);
        test = findViewById(R.id.test);
        textView = findViewById(R.id.textView);
        testBug.setOnClickListener(new MyClickListener());
        try {
            testBug.setText("测试bug"+getPackageManager().getPackageInfo(this.getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        update.setOnClickListener(new MyClickListener());
        test.setOnClickListener(new MyClickListener());

    }


    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.testBug:
                    Toast.makeText(MainActivity.this, BugClass.init.getBugMes(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.update:
                    Beta.checkUpgrade();

                    break;
                case R.id.test:
                    test();

                    break;
                default:
                    break;
            }

        }
    }

    void test(){

        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (upgradeInfo == null) {
            textView.setText("无升级信息");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType).append("\n");
        info.append("图片地址：").append(upgradeInfo.imageUrl);
        textView.setText(info);
    }



}
