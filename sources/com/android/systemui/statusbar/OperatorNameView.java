package com.android.systemui.statusbar;

import android.content.Context;
import android.telephony.ServiceState;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.statusbar.OperatorNameViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class OperatorNameView extends TextView {
    public OperatorNameView(Context context) {
        this(context, null);
    }

    public final void updateText(OperatorNameViewController.SubInfo subInfo) {
        ServiceState serviceState;
        CharSequence charSequence = subInfo.mCarrierName;
        if (TextUtils.isEmpty(charSequence) || subInfo.mSimState != 5 || (serviceState = subInfo.mServiceState) == null || serviceState.getState() != 0) {
            charSequence = null;
        }
        setText(charSequence);
    }

    public OperatorNameView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OperatorNameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
