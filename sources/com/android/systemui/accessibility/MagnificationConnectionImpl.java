package com.android.systemui.accessibility;

import android.R;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.IMagnificationConnection;
import android.view.accessibility.IMagnificationConnectionCallback;
import android.view.accessibility.IRemoteMagnificationAnimationCallback;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.accessibility.WindowMagnificationAnimationController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MagnificationConnectionImpl extends IMagnificationConnection.Stub {
    public IMagnificationConnectionCallback mConnectionCallback;
    public final MagnificationImpl.AnonymousClass1 mHandler;
    public final MagnificationImpl mMagnification;

    public MagnificationConnectionImpl(MagnificationImpl magnificationImpl, MagnificationImpl.AnonymousClass1 anonymousClass1) {
        this.mMagnification = magnificationImpl;
        this.mHandler = anonymousClass1;
    }

    public final void disableWindowMagnification(final int i, final IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback2 = iRemoteMagnificationAnimationCallback;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationConnectionImpl.mMagnification.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController != null) {
                    WindowMagnificationAnimationController windowMagnificationAnimationController = windowMagnificationController.mAnimationController;
                    if (windowMagnificationAnimationController.mController == null) {
                        return;
                    }
                    windowMagnificationAnimationController.sendAnimationCallback(false);
                    if (iRemoteMagnificationAnimationCallback2 == null) {
                        int i3 = windowMagnificationAnimationController.mState;
                        if (i3 == 3 || i3 == 2) {
                            windowMagnificationAnimationController.mValueAnimator.cancel();
                        }
                        windowMagnificationAnimationController.mController.deleteWindowMagnification$1();
                        windowMagnificationAnimationController.updateState();
                        return;
                    }
                    windowMagnificationAnimationController.mAnimationCallback = iRemoteMagnificationAnimationCallback2;
                    int i4 = windowMagnificationAnimationController.mState;
                    if (i4 == 0 || i4 == 2) {
                        if (i4 == 0) {
                            windowMagnificationAnimationController.sendAnimationCallback(true);
                        }
                    } else {
                        windowMagnificationAnimationController.mStartSpec.set(1.0f, Float.NaN, Float.NaN);
                        WindowMagnificationAnimationController.AnimationSpec animationSpec = windowMagnificationAnimationController.mEndSpec;
                        WindowMagnificationController windowMagnificationController2 = windowMagnificationAnimationController.mController;
                        animationSpec.set(windowMagnificationController2.isActivated() ? windowMagnificationController2.mScale : Float.NaN, Float.NaN, Float.NaN);
                        windowMagnificationAnimationController.mValueAnimator.reverse();
                        windowMagnificationAnimationController.setState(2);
                    }
                }
            }
        });
    }

    public final void enableWindowMagnification(final int i, final float f, final float f2, final float f3, final float f4, final float f5, final IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                float f6 = f;
                float f7 = f2;
                float f8 = f3;
                float f9 = f4;
                float f10 = f5;
                IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback2 = iRemoteMagnificationAnimationCallback;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationConnectionImpl.mMagnification.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController != null) {
                    windowMagnificationController.mAnimationController.enableWindowMagnification(f6, f7, f8, f9, f10, iRemoteMagnificationAnimationCallback2);
                }
            }
        });
    }

    public final void moveWindowMagnifier(final int i, final float f, final float f2) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                float f3 = f;
                float f4 = f2;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationConnectionImpl.mMagnification.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController != null) {
                    windowMagnificationController.moveWindowMagnifier(f3, f4);
                }
            }
        });
    }

    public final void moveWindowMagnifierToPosition(final int i, final float f, final float f2, final IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                float f3 = f;
                float f4 = f2;
                IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback2 = iRemoteMagnificationAnimationCallback;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationConnectionImpl.mMagnification.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController == null || windowMagnificationController.mMirrorSurfaceView == null) {
                    return;
                }
                WindowMagnificationAnimationController windowMagnificationAnimationController = windowMagnificationController.mAnimationController;
                int i3 = windowMagnificationAnimationController.mState;
                if (i3 == 1) {
                    windowMagnificationAnimationController.mValueAnimator.setDuration(windowMagnificationAnimationController.mContext.getResources().getInteger(R.integer.config_shortAnimTime));
                    windowMagnificationAnimationController.enableWindowMagnification(Float.NaN, f3, f4, Float.NaN, Float.NaN, iRemoteMagnificationAnimationCallback2);
                } else if (i3 == 3) {
                    windowMagnificationAnimationController.sendAnimationCallback(false);
                    windowMagnificationAnimationController.mAnimationCallback = iRemoteMagnificationAnimationCallback2;
                    windowMagnificationAnimationController.mValueAnimator.setDuration(windowMagnificationAnimationController.mContext.getResources().getInteger(R.integer.config_shortAnimTime));
                    windowMagnificationAnimationController.setupEnableAnimationSpecs(Float.NaN, f3, f4);
                }
            }
        });
    }

    public final void onFullscreenMagnificationActivationChanged(final int i, final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                boolean z2 = z;
                final FullscreenMagnificationController fullscreenMagnificationController = (FullscreenMagnificationController) magnificationConnectionImpl.mMagnification.mFullscreenMagnificationControllerSupplier.get(i2);
                if (fullscreenMagnificationController == null || fullscreenMagnificationController.mFullscreenMagnificationActivated == z2) {
                    return;
                }
                fullscreenMagnificationController.mFullscreenMagnificationActivated = z2;
                if (!z2) {
                    if (fullscreenMagnificationController.mHandler.hasCallbacks(fullscreenMagnificationController.mShowBorderRunnable)) {
                        fullscreenMagnificationController.mHandler.removeCallbacks(fullscreenMagnificationController.mShowBorderRunnable);
                    }
                    fullscreenMagnificationController.mContext.unregisterComponentCallbacks(fullscreenMagnificationController);
                    fullscreenMagnificationController.mShowHideBorderAnimator.reverse();
                    return;
                }
                fullscreenMagnificationController.onConfigurationChanged(fullscreenMagnificationController.mContext.getResources().getConfiguration());
                fullscreenMagnificationController.mContext.registerComponentCallbacks(fullscreenMagnificationController);
                if (fullscreenMagnificationController.mSurfaceControlViewHost == null) {
                    View inflate = LayoutInflater.from(fullscreenMagnificationController.mContext).inflate(com.android.wm.shell.R.layout.fullscreen_magnification_border, (ViewGroup) null);
                    fullscreenMagnificationController.mFullscreenBorder = inflate;
                    inflate.setAlpha(0.0f);
                    fullscreenMagnificationController.mShowHideBorderAnimator.setTarget(fullscreenMagnificationController.mFullscreenBorder);
                    SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) fullscreenMagnificationController.mScvhSupplier.get();
                    fullscreenMagnificationController.mSurfaceControlViewHost = surfaceControlViewHost;
                    View view = fullscreenMagnificationController.mFullscreenBorder;
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams((fullscreenMagnificationController.mBorderOffset * 2) + fullscreenMagnificationController.mWindowBounds.width(), (fullscreenMagnificationController.mBorderOffset * 2) + fullscreenMagnificationController.mWindowBounds.height(), 2032, 40, -2);
                    layoutParams.setTrustedOverlay();
                    surfaceControlViewHost.setView(view, layoutParams);
                    fullscreenMagnificationController.mBorderSurfaceControl = fullscreenMagnificationController.mSurfaceControlViewHost.getSurfacePackage().getSurfaceControl();
                    try {
                        fullscreenMagnificationController.mIWindowManager.watchRotation(fullscreenMagnificationController.mRotationWatcher, 0);
                    } catch (Exception e) {
                        Log.w("FullscreenMagnificationController", "Failed to register rotation watcher", e);
                    }
                }
                SurfaceControl.Transaction addTransactionCommittedListener = fullscreenMagnificationController.mTransaction.addTransactionCommittedListener(fullscreenMagnificationController.mExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.systemui.accessibility.FullscreenMagnificationController$$ExternalSyntheticLambda2
                    @Override // android.view.SurfaceControl.TransactionCommittedListener
                    public final void onTransactionCommitted() {
                        FullscreenMagnificationController fullscreenMagnificationController2 = FullscreenMagnificationController.this;
                        if (fullscreenMagnificationController2.mShowHideBorderAnimator.isRunning()) {
                            fullscreenMagnificationController2.mShowHideBorderAnimator.reverse();
                        } else {
                            fullscreenMagnificationController2.mShowHideBorderAnimator.start();
                        }
                    }
                });
                SurfaceControl surfaceControl = fullscreenMagnificationController.mBorderSurfaceControl;
                float f = -fullscreenMagnificationController.mBorderOffset;
                addTransactionCommittedListener.setPosition(surfaceControl, f, f).setLayer(fullscreenMagnificationController.mBorderSurfaceControl, Integer.MAX_VALUE).show(fullscreenMagnificationController.mBorderSurfaceControl).apply();
                fullscreenMagnificationController.mAccessibilityManager.attachAccessibilityOverlayToDisplay(fullscreenMagnificationController.mDisplayId, fullscreenMagnificationController.mBorderSurfaceControl);
                if (fullscreenMagnificationController.mFullscreenBorder == null) {
                    return;
                }
                fullscreenMagnificationController.mSurfaceControlViewHost.getRootSurfaceControl().setTouchableRegion(FullscreenMagnificationController.sEmptyRegion);
            }
        });
    }

    public final void onUserMagnificationScaleChanged(final int i, final int i2, final float f) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i3 = i;
                int i4 = i2;
                float f2 = f;
                MagnificationImpl magnificationImpl = magnificationConnectionImpl.mMagnification;
                SparseArray sparseArray = (SparseArray) magnificationImpl.mUsersScales.get(i3);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    magnificationImpl.mUsersScales.put(i3, sparseArray);
                }
                if (sparseArray.contains(i4) && ((Float) sparseArray.get(i4)).floatValue() == f2) {
                    return;
                }
                sparseArray.put(i4, Float.valueOf(f2));
                WindowMagnificationSettings windowMagnificationSettings = ((MagnificationSettingsController) magnificationImpl.mMagnificationSettingsSupplier.get(i4)).mWindowMagnificationSettings;
                windowMagnificationSettings.mScale = f2;
                if (windowMagnificationSettings.mIsVisible) {
                    windowMagnificationSettings.setScaleSeekbar(f2);
                }
            }
        });
    }

    public final void removeMagnificationButton(int i) {
        this.mHandler.post(new MagnificationConnectionImpl$$ExternalSyntheticLambda1(this, i, 1));
    }

    public final void removeMagnificationSettingsPanel(int i) {
        this.mHandler.post(new MagnificationConnectionImpl$$ExternalSyntheticLambda1(this, i, 0));
    }

    public final void setConnectionCallback(IMagnificationConnectionCallback iMagnificationConnectionCallback) {
        this.mConnectionCallback = iMagnificationConnectionCallback;
    }

    public final void setScaleForWindowMagnification(final int i, final float f) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i2 = i;
                float f2 = f;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationConnectionImpl.mMagnification.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController == null || windowMagnificationController.mAnimationController.mValueAnimator.isRunning() || !windowMagnificationController.isActivated() || windowMagnificationController.mScale == f2) {
                    return;
                }
                windowMagnificationController.updateWindowMagnificationInternal(f2);
                windowMagnificationController.mHandler.removeCallbacks(windowMagnificationController.mUpdateStateDescriptionRunnable);
                windowMagnificationController.mHandler.postDelayed(windowMagnificationController.mUpdateStateDescriptionRunnable, 100L);
            }
        });
    }

    public final void showMagnificationButton(final int i, final int i2) {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.MagnificationConnectionImpl$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationConnectionImpl.this;
                int i3 = i;
                int i4 = i2;
                MagnificationImpl.AnonymousClass1 anonymousClass1 = magnificationConnectionImpl.mMagnification.mHandler;
                if (anonymousClass1.hasMessages(1)) {
                    return;
                }
                anonymousClass1.sendMessageDelayed(anonymousClass1.obtainMessage(1, i3, i4), 300L);
            }
        });
    }
}
