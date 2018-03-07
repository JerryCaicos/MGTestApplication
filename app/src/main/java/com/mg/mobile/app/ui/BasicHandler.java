package com.mg.mobile.app.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.mg.mobile.app.model.TableConstants;

import java.lang.ref.WeakReference;

/**
 * Created by chenaxing on 2018/3/7.
 */

public abstract class BasicHandler extends Handler
{

    protected WeakReference<Activity> activityWeakReference;
    protected WeakReference<Fragment> fragmentWeakReference;

    private BasicHandler() {}//构造私有化,让调用者必须传递一个Activity 或者 Fragment的实例

    public BasicHandler(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    public BasicHandler(Fragment fragment) {
        this.fragmentWeakReference = new WeakReference<>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        if (activityWeakReference == null || activityWeakReference.get() == null || activityWeakReference.get().isFinishing()) {
            // 确认Activity是否不可用
            handleMessage(msg, TableConstants.ACTIVITY_GONE);
        } else {
            handleMessage(msg, msg.what);
        }
    }

    /**
     * 抽象方法用户实现,用来处理具体的业务逻辑
     *
     * @param msg
     * @param what
     */
    public abstract void handleMessage(Message msg, int what);

}
