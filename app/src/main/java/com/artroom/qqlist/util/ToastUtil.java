package com.artroom.qqlist.util;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.artroom.qqlist.R;


/**
 * Created by gaopeng on 2018/3/20.
 */

public class ToastUtil {
    private static Toast toast;
    private static View toastRoot;
    private static TextView tv_toast;
    public static void showToast(Activity activity, String str) {
        if (activity==null)
            return;
        if (toastRoot == null) {
            toastRoot = activity.getLayoutInflater().inflate(R.layout.layout_toast, null);
            tv_toast = (TextView) toastRoot.findViewById(R.id.tv_toast);
            toast = new Toast(activity);
            toast.setView(toastRoot);
        }
        tv_toast.setText(str);
        toast.show();
    }

}
