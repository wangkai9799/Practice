package com.dankai.fragmentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dankai.fragmentation.base.activity.ProxyActivity;
import com.dankai.fragmentation.base.delegate.BaseDelegate;

/**
 * 单Activity多Fragment框架
 */
public class MainActivity extends ProxyActivity {

    //再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    private DrawerLayout mDrawerLayout = null;

    @Override
    public BaseDelegate setRootDelegate() {
        return new MainDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
