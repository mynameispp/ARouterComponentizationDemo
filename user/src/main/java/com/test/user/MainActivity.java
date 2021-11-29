package com.test.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.test.baselibs.application.GlideApp;
import com.test.baselibs.bean.UserInfoBean;
import com.test.baselibs.constans.MyConstans;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = MyConstans.Arout_Path_Activity_User)
public class MainActivity extends AppCompatActivity {
    //默认取值
    @Autowired
    UserInfoBean userInfo;
    //别名取值
    @Autowired(name = "userId")
    String userInfoId;

    @BindView(R2.id.user_name)
    TextView userName;
    @BindView(R2.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

        GlideApp.with(imageView)
                .load("https://img1.baidu.com/it/u=1709267998,1556915933&fm=26&fmt=auto")
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .transform(new CircleCrop())
                .into(imageView);


        if (null != getIntent().getExtras()) {
            //Arouter with取值
            StringBuilder userInfoSB = new StringBuilder();
            userInfoSB.append("withString 用户id:")
                    .append("\n")
                    .append(userInfoId)
                    .append("\n\n");
            if (userInfo != null) {
                userInfoSB.append("withSerializable 用户信息：")
                        .append("\n")
                        .append(userInfo.getUserName() + "=id=" + userInfo.getUserId())
                        .append("\n\n");
            }
            //从Bundle取值
            Bundle bundle = getIntent().getBundleExtra("bundle");
            if (bundle != null) {
                UserInfoBean uu = (UserInfoBean) bundle.getSerializable(MyConstans.KEY_DATA);
                if (uu != null) {
                    String title = bundle.getString(MyConstans.Key_Title_Name);
                    userInfoSB.append("withBundle 信息：")
                            .append("\n")
                            .append("title = " + title)
                            .append("\n")
                            .append(uu.getUserName() + "=id=" + uu.getUserId());
                }
            }
            userName.setText(userInfoSB.toString());
        }
    }

    @OnClick(R2.id.button)
    public void onViewClicked() {
        finish();
    }
}
