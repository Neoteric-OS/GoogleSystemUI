package com.android.wm.shell.common.split;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.view.IWindow;
import android.view.InsetsState;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.window.InputTransferToken;
import com.android.wm.shell.R;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitWindowManager extends WindowlessWindowManager {
    public Context mContext;
    public DividerView mDividerView;
    public boolean mLastDividerHandleHidden;
    public boolean mLastDividerInteractive;
    public SurfaceControl mLeash;
    public final StageCoordinator.AnonymousClass1 mParentContainerCallbacks;
    public SurfaceControl.Transaction mSyncTransaction;
    public SurfaceControlViewHost mViewHost;
    public final String mWindowName;

    public SplitWindowManager(Context context, Configuration configuration, StageCoordinator.AnonymousClass1 anonymousClass1) {
        super(configuration, (SurfaceControl) null, (InputTransferToken) null);
        this.mSyncTransaction = null;
        this.mLastDividerInteractive = true;
        this.mContext = context.createConfigurationContext(configuration);
        this.mParentContainerCallbacks = anonymousClass1;
        this.mWindowName = "StageCoordinatorSplitDivider";
    }

    public final SurfaceControl getParentSurface(IWindow iWindow, WindowManager.LayoutParams layoutParams) {
        SurfaceControl.Builder callsite = new SurfaceControl.Builder().setContainerLayer().setName("SplitWindowManager").setHidden(true).setCallsite("SplitWindowManager#attachToParentSurface");
        callsite.setParent(StageCoordinator.this.mRootTaskLeash);
        this.mLeash = callsite.build();
        StageCoordinator.AnonymousClass1 anonymousClass1 = this.mParentContainerCallbacks;
        StageCoordinator stageCoordinator = StageCoordinator.this;
        if (stageCoordinator.mDividerVisible) {
            stageCoordinator.mSyncQueue.runInSync(new StageCoordinator$$ExternalSyntheticLambda3(4, anonymousClass1));
        }
        return this.mLeash;
    }

    public final SurfaceControl getSurfaceControl(IWindow iWindow) {
        return super.getSurfaceControl(iWindow);
    }

    public final void init(SplitLayout splitLayout, InsetsState insetsState, boolean z) {
        if (this.mDividerView != null || this.mViewHost != null) {
            throw new UnsupportedOperationException("Try to inflate divider view again without release first");
        }
        Context context = this.mContext;
        this.mViewHost = new SurfaceControlViewHost(context, context.getDisplay(), this, "SplitWindowManager");
        this.mDividerView = (DividerView) LayoutInflater.from(this.mContext).inflate(R.layout.split_divider, (ViewGroup) null);
        splitLayout.getClass();
        Rect rect = new Rect(splitLayout.mDividerBounds);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(rect.width(), rect.height(), 2034, 545521704, -3);
        layoutParams.token = new Binder();
        layoutParams.setTitle(this.mWindowName);
        layoutParams.privateFlags |= 536870976;
        layoutParams.accessibilityTitle = this.mContext.getResources().getString(R.string.accessibility_divider);
        this.mViewHost.setView(this.mDividerView, layoutParams);
        this.mDividerView.setup(splitLayout, this, this.mViewHost, insetsState);
        if (z) {
            this.mDividerView.setInteractive("restore_setup", this.mLastDividerInteractive, this.mLastDividerHandleHidden);
        }
    }

    public final void release(SurfaceControl.Transaction transaction) {
        DividerView dividerView = this.mDividerView;
        if (dividerView != null) {
            this.mLastDividerInteractive = dividerView.mInteractive;
            this.mLastDividerHandleHidden = dividerView.mHideHandle;
            this.mDividerView = null;
        }
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost != null) {
            this.mSyncTransaction = transaction;
            surfaceControlViewHost.release();
            this.mSyncTransaction = null;
            this.mViewHost = null;
        }
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl != null) {
            if (transaction == null) {
                new SurfaceControl.Transaction().remove(this.mLeash).apply();
            } else {
                transaction.remove(surfaceControl);
            }
            this.mLeash = null;
        }
    }

    public final void removeSurface(SurfaceControl surfaceControl) {
        SurfaceControl.Transaction transaction = this.mSyncTransaction;
        if (transaction != null) {
            transaction.remove(surfaceControl);
        } else {
            super.removeSurface(surfaceControl);
        }
    }

    public final void setConfiguration(Configuration configuration) {
        super.setConfiguration(configuration);
        this.mContext = this.mContext.createConfigurationContext(configuration);
    }

    public final void setTouchRegion(Rect rect) {
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost != null) {
            setTouchRegion(surfaceControlViewHost.getWindowToken().asBinder(), new Region(rect));
        }
    }
}
