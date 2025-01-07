package com.android.wm.shell.onehanded;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Binder;
import android.util.Slog;
import android.view.ContextThemeWrapper;
import android.view.IWindow;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.window.InputTransferToken;
import androidx.core.view.ViewCompat$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BackgroundWindowManager extends WindowlessWindowManager {
    public View mBackgroundView;
    public Context mContext;
    public int mCurrentState;
    public Rect mDisplayBounds;
    public SurfaceControl mLeash;
    public final ViewCompat$$ExternalSyntheticLambda0 mTransactionFactory;
    public SurfaceControlViewHost mViewHost;

    public BackgroundWindowManager(Context context) {
        super(context.getResources().getConfiguration(), (SurfaceControl) null, (InputTransferToken) null);
        this.mContext = context;
        this.mTransactionFactory = new ViewCompat$$ExternalSyntheticLambda0();
    }

    public final SurfaceControl getParentSurface(IWindow iWindow, WindowManager.LayoutParams layoutParams) {
        SurfaceControl build = new SurfaceControl.Builder().setColorLayer().setBufferSize(this.mDisplayBounds.width(), this.mDisplayBounds.height()).setFormat(3).setOpaque(true).setName("BackgroundWindowManager").setCallsite("BackgroundWindowManager#attachToParentSurface").build();
        this.mLeash = build;
        return build;
    }

    public final SurfaceControl getSurfaceControl(IWindow iWindow) {
        return super.getSurfaceControl(iWindow);
    }

    public final int getThemeColorForBackground() {
        return Color.argb(Color.alpha(new ContextThemeWrapper(this.mContext, R.style.Theme.DeviceDefault.DayNight).getColor(com.android.wm.shell.R.color.one_handed_tutorial_background_color)), Color.red(r3) - 10, Color.green(r3) - 10, Color.blue(r3) - 10);
    }

    public final void setConfiguration(Configuration configuration) {
        super.setConfiguration(configuration);
        this.mContext = this.mContext.createConfigurationContext(configuration);
    }

    public final void showBackgroundLayer() {
        if (this.mBackgroundView != null || this.mViewHost != null) {
            updateThemeOnly();
            return;
        }
        Context context = this.mContext;
        this.mViewHost = new SurfaceControlViewHost(context, context.getDisplay(), this, "BackgroundWindowManager");
        this.mBackgroundView = LayoutInflater.from(this.mContext).inflate(com.android.wm.shell.R.layout.background_panel, (ViewGroup) null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(this.mDisplayBounds.width(), this.mDisplayBounds.height(), 0, 537133096, -3);
        layoutParams.token = new Binder();
        layoutParams.setTitle("background-panel");
        layoutParams.privateFlags |= 536870976;
        this.mBackgroundView.setBackgroundColor(getThemeColorForBackground());
        this.mViewHost.setView(this.mBackgroundView, layoutParams);
        if (this.mLeash == null) {
            Slog.w("BackgroundWindowManager", "SurfaceControl mLeash is null, can't show One-handed mode background panel!");
        } else {
            getClass();
            new SurfaceControl.Transaction().setAlpha(this.mLeash, 1.0f).setLayer(this.mLeash, -1).show(this.mLeash).apply();
        }
    }

    public final void updateThemeOnly() {
        View view = this.mBackgroundView;
        if (view == null || this.mViewHost == null || this.mLeash == null) {
            Slog.w("BackgroundWindowManager", "Background view or SurfaceControl does not exist when trying to update theme only!");
            return;
        }
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        this.mBackgroundView.setBackgroundColor(getThemeColorForBackground());
        this.mViewHost.setView(this.mBackgroundView, layoutParams);
    }
}
