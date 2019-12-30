package com.bawei.week.view.activituy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.week.Model.Bean.EvenBarBean;
import com.bawei.week.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.s_text)
    TextView sText;
    @BindView(R.id.s_image)
    ImageView sImage;
    @BindView(R.id.string_btn)
    Button stringBtn;
    @BindView(R.id.Json_btn)
    Button JsonBtn;
    @BindView(R.id.phone_btn2)
    Button phoneBtn2;
    @BindView(R.id.phone_bt3)
    Button phoneBt3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        CodeUtils.init(this);
        sImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(sImage, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "生成二维码成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "生成二维码失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });


    }


    @OnClick({R.id.string_btn, R.id.Json_btn, R.id.s_text, R.id.phone_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.s_text:
                CharSequence text = sText.getText();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                Bitmap image = CodeUtils.createImage(text.toString(), 400, 400, bitmap);
                sImage.setImageBitmap(image);
                break;
            case R.id.string_btn:
                EventBus.getDefault().post("我是字符串");
                break;
            case R.id.Json_btn:
                EventBus.getDefault().post(new EvenBarBean("肖笑", 5));
                break;
            case R.id.phone_btn2:
                CodeUtils.analyzeByPhotos(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEven(String s) {
        Toast.makeText(this, "成功" + s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEven(EvenBarBean evenBarBean) {
        Toast.makeText(this, "成功" + evenBarBean.getName() + evenBarBean.getAge(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(SecondActivity.this, "成功" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
