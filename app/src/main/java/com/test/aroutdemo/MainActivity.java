package com.test.aroutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.test.baselibs.application.GlideApp;
import com.test.baselibs.bean.UserInfoBean;
import com.test.baselibs.constans.MyConstans;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.button)
    Button button;
    @BindView(R2.id.button2)
    Button button2;
    @BindView(R2.id.txt)
    TextView txt;
    @BindView(R2.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GlideApp.with(imageView)
                .load("https://t7.baidu.com/it/u=2084624597,235761712&fm=193&f=GIF")
                .into(imageView);
    }

    @OnClick({R2.id.button, R2.id.button2, R2.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                //普通跳转
                ARouter.getInstance()
                        .build(MyConstans.Arout_Path_Activity_User)
                        .navigation();
                break;
            case R.id.button3:
                //传参数

                UserInfoBean userInfoBean = new UserInfoBean();
                userInfoBean.setUserId("6666666");
                userInfoBean.setUserName("用户1");
                UserInfoBean userInfoBean2 = new UserInfoBean();
                userInfoBean2.setUserId("888888");
                userInfoBean2.setUserName("用户2");
                Bundle bundle = new Bundle();
                bundle.putString(MyConstans.Key_Title_Name, "用户信息");
                bundle.putSerializable(MyConstans.KEY_DATA, userInfoBean2);
                ARouter.getInstance()
                        .build(MyConstans.Arout_Path_Activity_User)
                        .withString("userId", "123456")//传入普通类型
                        .withSerializable("userInfo", userInfoBean)//传入自定义对象
                        .withBundle("bundle", bundle)//传入Bundle
                        .navigation();
                break;
            case R.id.button2:
                ARouter.getInstance()
                        .build(MyConstans.Arout_Path_Activity_Star_For_Result)
                        .withString("input_value", "传入88888")
                        .navigation(this, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            txt.setText("返回值：" + data.getStringExtra(MyConstans.KEY_DATA));
        }
    }
}
