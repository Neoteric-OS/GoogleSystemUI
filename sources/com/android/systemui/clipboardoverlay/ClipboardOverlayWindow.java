package com.android.systemui.clipboardoverlay;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.policy.PhoneWindow;
import com.android.systemui.screenshot.FloatingWindowUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardOverlayWindow extends PhoneWindow implements ViewRootImpl.ActivityConfigCallback {
    public final Context mContext;
    public boolean mKeyboardVisible;
    public ClipboardOverlayController$$ExternalSyntheticLambda2 mOnKeyboardChangeListener;
    public ClipboardOverlayController$$ExternalSyntheticLambda1 mOnOrientationChangeListener;
    public final int mOrientation;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final WindowManager.LayoutParams mWindowLayoutParams;
    public final WindowManager mWindowManager;

    public ClipboardOverlayWindow(Context context, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, WindowManager windowManager) {
        super(context);
        View peekDecorView;
        this.mContext = context;
        this.mOrientation = context.getResources().getConfiguration().orientation;
        requestFeature(1);
        requestFeature(13);
        setBackgroundDrawableResource(R.color.transparent);
        this.mWindowManager = windowManager;
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        WindowManager.LayoutParams floatingWindowParams = FloatingWindowUtil.getFloatingWindowParams();
        this.mWindowLayoutParams = floatingWindowParams;
        floatingWindowParams.setTitle("ClipboardOverlay");
        setWindowManager(windowManager, (IBinder) null, (String) null);
        int i = floatingWindowParams.flags;
        int i2 = i | 8;
        floatingWindowParams.flags = i2;
        if (i2 == i || (peekDecorView = peekDecorView()) == null || !peekDecorView.isAttachedToWindow()) {
            return;
        }
        viewCaptureAwareWindowManager.updateViewLayout(peekDecorView, floatingWindowParams);
    }

    public final void onConfigurationChanged(Configuration configuration, int i) {
        if (this.mContext.getResources().getConfiguration().orientation != this.mOrientation) {
            this.mOnOrientationChangeListener.run();
        }
    }

    public final void withWindowAttached(final Runnable runnable) {
        final View decorView = getDecorView();
        if (decorView.isAttachedToWindow()) {
            runnable.run();
        } else {
            decorView.getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayWindow.1
                @Override // android.view.ViewTreeObserver.OnWindowAttachListener
                public final void onWindowAttached() {
                    decorView.getViewTreeObserver().removeOnWindowAttachListener(this);
                    runnable.run();
                }

                @Override // android.view.ViewTreeObserver.OnWindowAttachListener
                public final void onWindowDetached() {
                }
            });
        }
    }
}
