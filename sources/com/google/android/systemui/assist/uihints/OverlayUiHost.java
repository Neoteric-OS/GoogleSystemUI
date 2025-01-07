package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OverlayUiHost {
    public boolean mAttached = false;
    public WindowManager.LayoutParams mLayoutParams;
    public final AssistUIView mRoot;
    public final ViewCaptureAwareWindowManager mWindowManager;

    public OverlayUiHost(Context context, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mRoot = (AssistUIView) LayoutInflater.from(context).inflate(R.layout.assist_ui, (ViewGroup) null, false);
        this.mWindowManager = viewCaptureAwareWindowManager;
    }
}
