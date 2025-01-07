package com.android.systemui.common.ui.binder;

import android.widget.TextView;
import com.android.systemui.common.shared.model.Text;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextViewBinder {
    public static void bind(TextView textView, Text text) {
        String str;
        if (text instanceof Text.Resource) {
            str = textView.getContext().getString(((Text.Resource) text).res);
        } else {
            if (!(text instanceof Text.Loaded)) {
                throw new NoWhenBranchMatchedException();
            }
            str = ((Text.Loaded) text).text;
        }
        textView.setText(str);
    }
}
