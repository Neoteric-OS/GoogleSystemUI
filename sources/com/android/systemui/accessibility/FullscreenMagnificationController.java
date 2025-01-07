package com.android.systemui.accessibility;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import android.util.Property;
import android.view.IRotationWatcher;
import android.view.IWindowManager;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.util.leak.RotationUtils;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class FullscreenMagnificationController implements ComponentCallbacks {
    public static final Region sEmptyRegion = new Region();
    public final AccessibilityManager mAccessibilityManager;
    public int mBorderOffset;
    public int mBorderStoke;
    public final Configuration mConfiguration;
    public final Context mContext;
    public String mCurrentDisplayUniqueId;
    public final int mDisplayId;
    public final AnonymousClass3 mDisplayListener;
    public final Executor mExecutor;
    public final Handler mHandler;
    public final IWindowManager mIWindowManager;
    public final long mLongAnimationTimeMs;
    public int mRotation;
    public final Supplier mScvhSupplier;
    public final ValueAnimator mShowHideBorderAnimator;
    public final SurfaceControl.Transaction mTransaction;
    public final Rect mWindowBounds;
    public final WindowManager mWindowManager;
    public SurfaceControlViewHost mSurfaceControlViewHost = null;
    public SurfaceControl mBorderSurfaceControl = null;
    public View mFullscreenBorder = null;
    public boolean mFullscreenMagnificationActivated = false;
    public final FullscreenMagnificationController$$ExternalSyntheticLambda0 mShowBorderRunnable = new FullscreenMagnificationController$$ExternalSyntheticLambda0(this, 1);
    public final AnonymousClass1 mRotationWatcher = new IRotationWatcher.Stub() { // from class: com.android.systemui.accessibility.FullscreenMagnificationController.1
        public final void onRotationChanged(int i) {
            FullscreenMagnificationController fullscreenMagnificationController = FullscreenMagnificationController.this;
            Region region = FullscreenMagnificationController.sEmptyRegion;
            fullscreenMagnificationController.handleScreenRotation();
        }
    };

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.accessibility.FullscreenMagnificationController$1] */
    public FullscreenMagnificationController(Context context, Handler handler, Executor executor, DisplayManager displayManager, AccessibilityManager accessibilityManager, WindowManager windowManager, IWindowManager iWindowManager, Supplier supplier, SurfaceControl.Transaction transaction, ValueAnimator valueAnimator) {
        this.mContext = context;
        this.mHandler = handler;
        this.mExecutor = executor;
        this.mAccessibilityManager = accessibilityManager;
        this.mWindowManager = windowManager;
        this.mIWindowManager = iWindowManager;
        this.mWindowBounds = windowManager.getCurrentWindowMetrics().getBounds();
        this.mTransaction = transaction;
        this.mScvhSupplier = supplier;
        updateDimensions();
        this.mDisplayId = context.getDisplayId();
        this.mConfiguration = new Configuration(context.getResources().getConfiguration());
        long integer = context.getResources().getInteger(R.integer.config_longAnimTime);
        this.mLongAnimationTimeMs = integer;
        if (valueAnimator == null) {
            valueAnimator = ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) View.ALPHA, 0.0f, 1.0f);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.setDuration(integer);
        }
        this.mShowHideBorderAnimator = valueAnimator;
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.accessibility.FullscreenMagnificationController.2
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator, boolean z) {
                if (z) {
                    FullscreenMagnificationController fullscreenMagnificationController = FullscreenMagnificationController.this;
                    SurfaceControlViewHost surfaceControlViewHost = fullscreenMagnificationController.mSurfaceControlViewHost;
                    if (surfaceControlViewHost != null) {
                        surfaceControlViewHost.release();
                        fullscreenMagnificationController.mSurfaceControlViewHost = null;
                    }
                    if (fullscreenMagnificationController.mFullscreenBorder != null) {
                        fullscreenMagnificationController.mFullscreenBorder = null;
                        try {
                            fullscreenMagnificationController.mIWindowManager.removeRotationWatcher(fullscreenMagnificationController.mRotationWatcher);
                        } catch (Exception e) {
                            Log.w("FullscreenMagnificationController", "Failed to remove rotation watcher", e);
                        }
                    }
                }
            }
        });
        this.mCurrentDisplayUniqueId = context.getDisplayNoVerify().getUniqueId();
        new DisplayManager.DisplayListener() { // from class: com.android.systemui.accessibility.FullscreenMagnificationController.3
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayChanged(int i) {
                String uniqueId = FullscreenMagnificationController.this.mContext.getDisplayNoVerify().getUniqueId();
                if (uniqueId.equals(FullscreenMagnificationController.this.mCurrentDisplayUniqueId)) {
                    return;
                }
                FullscreenMagnificationController fullscreenMagnificationController = FullscreenMagnificationController.this;
                fullscreenMagnificationController.mCurrentDisplayUniqueId = uniqueId;
                View view = fullscreenMagnificationController.mFullscreenBorder;
                if (view == null || !(view.getBackground() instanceof GradientDrawable)) {
                    return;
                }
                float windowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(fullscreenMagnificationController.mContext);
                GradientDrawable gradientDrawable = (GradientDrawable) fullscreenMagnificationController.mFullscreenBorder.getBackground();
                gradientDrawable.setStroke(fullscreenMagnificationController.mBorderStoke, fullscreenMagnificationController.mContext.getResources().getColor(com.android.wm.shell.R.color.magnification_border_color, fullscreenMagnificationController.mContext.getTheme()));
                gradientDrawable.setCornerRadius(windowCornerRadius);
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayAdded(int i) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayRemoved(int i) {
            }
        };
    }

    public final void handleScreenRotation() {
        if (this.mFullscreenBorder != null) {
            if (this.mHandler.hasCallbacks(this.mShowBorderRunnable)) {
                this.mHandler.removeCallbacks(this.mShowBorderRunnable);
            }
            this.mHandler.postAtFrontOfQueue(new FullscreenMagnificationController$$ExternalSyntheticLambda0(this, 0));
            this.mHandler.postDelayed(this.mShowBorderRunnable, this.mLongAnimationTimeMs);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        onConfigurationChanged(diff);
    }

    public final void updateDimensions() {
        this.mBorderOffset = this.mContext.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.magnifier_border_width_fullscreen_with_offset) - this.mContext.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.magnifier_border_width_fullscreen);
        this.mBorderStoke = this.mContext.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.magnifier_border_width_fullscreen_with_offset);
    }

    public void onConfigurationChanged(int i) {
        boolean z;
        if ((i & 4096) == 0 && (i & 1024) == 0 && (i & 128) == 0) {
            z = false;
        } else {
            updateDimensions();
            this.mWindowBounds.set(this.mWindowManager.getCurrentWindowMetrics().getBounds());
            z = true;
        }
        if (this.mFullscreenBorder == null) {
            return;
        }
        if (z) {
            this.mSurfaceControlViewHost.relayout((this.mBorderOffset * 2) + this.mWindowBounds.width(), (this.mBorderOffset * 2) + this.mWindowBounds.height());
        }
        int rotation = RotationUtils.getRotation(this.mContext);
        if (rotation != this.mRotation) {
            this.mRotation = rotation;
            handleScreenRotation();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
