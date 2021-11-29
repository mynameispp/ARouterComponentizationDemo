package com.test.star_for_result;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.test.baselibs.constans.MyConstans;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = MyConstans.Arout_Path_Activity_Star_For_Result)
public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.textView)
    TextView textView;
    @BindView(R2.id.button)
    Button button;

    @Autowired
    String input_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_for_resilt_main);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

        textView.setText(input_value);
    }

    @OnClick(R2.id.button)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra(MyConstans.KEY_DATA, "666666");
        setResult(100, intent);
        finish();
    }
}
