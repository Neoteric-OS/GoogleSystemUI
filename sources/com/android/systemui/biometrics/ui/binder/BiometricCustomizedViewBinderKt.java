package com.android.systemui.biometrics.ui.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.biometrics.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricCustomizedViewBinderKt {
    public static final LinearLayout inflateContentView(LayoutInflater layoutInflater, int i, String str) {
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(i, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.requireViewById(R.id.customized_view_description);
        if (str == null || str.length() == 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(Utils.ellipsize(225, str));
        }
        return linearLayout;
    }
}
