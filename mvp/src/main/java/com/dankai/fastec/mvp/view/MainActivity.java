package com.dankai.fastec.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dankai.fastec.mvp.R;
import com.dankai.fastec.mvp.presenter.CidianPresenter;

public class MainActivity extends AppCompatActivity implements IFanyiView {

    private EditText mEditText = null;
    private TextView mTextView = null;
    //声明了Presenter类
    private CidianPresenter mCidianPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCidianPresenter.inputToModel(mEditText.getText().toString(), MainActivity.this);
            }
        });
    }

    @Override
    public void init() {
        mCidianPresenter = new CidianPresenter(this);
        mEditText = findViewById(R.id.et_input);
        mTextView = findViewById(R.id.tv_show);
    }

    @Override
    public void setInfo(String str) {
        mTextView.setText(str);
    }

    @Override
    public void setError() {
        mTextView.setText("查询不成功，请检查网络");
    }
}
