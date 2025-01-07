package com.android.systemui.clipboardoverlay;

import android.content.Context;
import android.widget.Toast;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardToast extends Toast.Callback {
    public final Context mContext;
    public Toast mCopiedToast;

    public ClipboardToast(Context context) {
        this.mContext = context;
    }

    @Override // android.widget.Toast.Callback
    public final void onToastHidden() {
        super.onToastHidden();
        this.mCopiedToast = null;
    }
}
