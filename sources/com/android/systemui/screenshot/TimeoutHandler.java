package com.android.systemui.screenshot;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.accessibility.AccessibilityManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TimeoutHandler extends Handler {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Context mContext;
    public int mDefaultTimeout;
    public Runnable mOnTimeout;

    public TimeoutHandler(Context context) {
        super(Looper.getMainLooper());
        this.mDefaultTimeout = 6000;
        this.mContext = context;
        this.mOnTimeout = new TimeoutHandler$$ExternalSyntheticLambda0();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what != 2) {
            return;
        }
        this.mOnTimeout.run();
    }

    public final void resetTimeout() {
        removeMessages(2);
        sendMessageDelayed(obtainMessage(2), ((AccessibilityManager) this.mContext.getSystemService("accessibility")).getRecommendedTimeoutMillis(this.mDefaultTimeout, 4));
    }
}
