package com.android.wm.shell.common.split;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.view.IWindow;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.window.InputTransferToken;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.R;
import com.android.wm.shell.common.SurfaceUtils;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitDecorManager extends WindowlessWindowManager {
    public static final /* synthetic */ int $r8$clinit = 0;
    public SurfaceControl mBackgroundLeash;
    public ValueAnimator mFadeAnimator;
    public SurfaceControl mGapBackgroundLeash;
    public SurfaceControl mHostLeash;
    public Drawable mIcon;
    public SurfaceControl mIconLeash;
    public final IconProvider mIconProvider;
    public int mIconSize;
    public final Rect mInstantaneousBounds;
    public boolean mIsCurrentlyChanging;
    public int mOffsetX;
    public int mOffsetY;
    public final Rect mOldMainBounds;
    public final Rect mOldSideBounds;
    public int mRunningAnimationCount;
    public SurfaceControl mScreenshot;
    public ValueAnimator mScreenshotAnimator;
    public boolean mShown;
    public ImageView mVeilIconView;
    public SurfaceControlViewHost mViewHost;

    public SplitDecorManager(Configuration configuration, IconProvider iconProvider) {
        super(configuration, (SurfaceControl) null, (InputTransferToken) null);
        this.mOldMainBounds = new Rect();
        this.mOldSideBounds = new Rect();
        this.mInstantaneousBounds = new Rect();
        new Rect();
        this.mRunningAnimationCount = 0;
        this.mIconProvider = iconProvider;
    }

    public static float[] getResizingBackgroundColor(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int backgroundColor = runningTaskInfo.taskDescription.getBackgroundColor();
        if (backgroundColor == -1) {
            backgroundColor = -1;
        }
        return Color.valueOf(backgroundColor).getComponents();
    }

    public final void cancelRunningAnimations() {
        ValueAnimator valueAnimator = this.mFadeAnimator;
        if (valueAnimator != null) {
            if (valueAnimator.isRunning()) {
                this.mFadeAnimator.cancel();
            }
            this.mFadeAnimator = null;
        }
        ValueAnimator valueAnimator2 = this.mScreenshotAnimator;
        if (valueAnimator2 != null) {
            if (valueAnimator2.isRunning()) {
                this.mScreenshotAnimator.cancel();
            }
            this.mScreenshotAnimator = null;
        }
    }

    public final void drawNextVeilFrameForSwapAnimation(ActivityManager.RunningTaskInfo runningTaskInfo, Rect rect, SurfaceControl.Transaction transaction, boolean z, SurfaceControl surfaceControl, float f, float f2) {
        ActivityInfo activityInfo;
        ValueAnimator valueAnimator;
        if (this.mVeilIconView == null) {
            return;
        }
        if (!this.mIsCurrentlyChanging) {
            this.mIsCurrentlyChanging = true;
        }
        this.mInstantaneousBounds.set(rect);
        this.mOffsetX = (int) f;
        this.mOffsetY = (int) f2;
        transaction.setLayer(surfaceControl, z ? -10 : 20);
        if (!this.mShown && (valueAnimator = this.mFadeAnimator) != null && valueAnimator.isRunning()) {
            this.mFadeAnimator.cancel();
        }
        if (this.mBackgroundLeash == null) {
            SurfaceControl makeColorLayer = SurfaceUtils.makeColorLayer(this.mHostLeash, "ResizingBackground");
            this.mBackgroundLeash = makeColorLayer;
            transaction.setColor(makeColorLayer, getResizingBackgroundColor(runningTaskInfo)).setLayer(this.mBackgroundLeash, 2147483646);
        }
        if (this.mIcon == null && (activityInfo = runningTaskInfo.topActivityInfo) != null) {
            Drawable icon = this.mIconProvider.getIcon(activityInfo);
            this.mIcon = icon;
            this.mVeilIconView.setImageDrawable(icon);
            this.mVeilIconView.setVisibility(0);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mViewHost.getView().getLayoutParams();
            int i = this.mIconSize;
            layoutParams.width = i;
            layoutParams.height = i;
            this.mViewHost.relayout(layoutParams);
            transaction.setLayer(this.mIconLeash, Integer.MAX_VALUE);
        }
        transaction.setPosition(this.mIconLeash, ((rect.width() / 2) - (this.mIconSize / 2)) - this.mOffsetX, ((rect.height() / 2) - (this.mIconSize / 2)) - this.mOffsetY);
        if (this.mShown) {
            return;
        }
        startFadeAnimation(true, false, null, false);
        this.mShown = true;
    }

    public final void fadeOutDecor(Runnable runnable, boolean z) {
        if (!this.mShown) {
            runnable.run();
            return;
        }
        ValueAnimator valueAnimator = this.mFadeAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mFadeAnimator.cancel();
        }
        startFadeAnimation(false, true, runnable, z);
        this.mShown = false;
    }

    public final void fadeOutVeilAndCleanUp(SurfaceControl.Transaction transaction) {
        if (this.mVeilIconView == null) {
            return;
        }
        transaction.setPosition(this.mIconLeash, (this.mInstantaneousBounds.width() / 2.0f) - (this.mIconSize / 2.0f), (this.mInstantaneousBounds.height() / 2.0f) - (this.mIconSize / 2.0f));
        this.mIsCurrentlyChanging = false;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mInstantaneousBounds.setEmpty();
        fadeOutDecor(new SplitDecorManager$$ExternalSyntheticLambda0(), true);
    }

    public final SurfaceControl getParentSurface(IWindow iWindow, WindowManager.LayoutParams layoutParams) {
        SurfaceControl build = new SurfaceControl.Builder().setContainerLayer().setName("SplitDecorManager").setHidden(true).setParent(this.mHostLeash).setCallsite("SplitDecorManager#attachToParentSurface").build();
        this.mIconLeash = build;
        return build;
    }

    public final void inflate(Context context, SurfaceControl surfaceControl) {
        if (this.mIconLeash == null || this.mViewHost == null) {
            Context createWindowContext = context.createWindowContext(context.getDisplay(), 2038, null);
            this.mHostLeash = surfaceControl;
            this.mViewHost = new SurfaceControlViewHost(createWindowContext, createWindowContext.getDisplay(), this, "SplitDecorManager");
            this.mIconSize = createWindowContext.getResources().getDimensionPixelSize(R.dimen.split_icon_size);
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(createWindowContext).inflate(R.layout.split_decor, (ViewGroup) null);
            this.mVeilIconView = (ImageView) frameLayout.findViewById(R.id.split_resizing_icon);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, 2038, 24, -3);
            int i = this.mIconSize;
            layoutParams.width = i;
            layoutParams.height = i;
            layoutParams.token = new Binder();
            layoutParams.setTitle("SplitDecorManager");
            layoutParams.privateFlags |= 536870976;
            layoutParams.inputFeatures |= 1;
            this.mViewHost.setView(frameLayout, layoutParams);
        }
    }

    public final void onResized(SurfaceControl.Transaction transaction, final Consumer consumer) {
        final int i = 0;
        ValueAnimator valueAnimator = this.mScreenshotAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mScreenshotAnimator.cancel();
        }
        SurfaceControl surfaceControl = this.mScreenshot;
        if (surfaceControl != null) {
            transaction.setPosition(surfaceControl, this.mOffsetX, this.mOffsetY);
            final SurfaceControl.Transaction transaction2 = new SurfaceControl.Transaction();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            this.mScreenshotAnimator = ofFloat;
            ofFloat.setDuration(133L);
            this.mScreenshotAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.common.split.SplitDecorManager$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    SplitDecorManager splitDecorManager = SplitDecorManager.this;
                    SurfaceControl.Transaction transaction3 = transaction2;
                    splitDecorManager.getClass();
                    transaction3.setAlpha(splitDecorManager.mScreenshot, ((Float) valueAnimator2.getAnimatedValue()).floatValue());
                    transaction3.apply();
                }
            });
            this.mScreenshotAnimator.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.wm.shell.common.split.SplitDecorManager.1
                public final /* synthetic */ SplitDecorManager this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    Consumer consumer2;
                    Consumer consumer3;
                    switch (i) {
                        case 0:
                            r2.mRunningAnimationCount--;
                            transaction2.remove(this.this$0.mScreenshot);
                            transaction2.apply();
                            transaction2.close();
                            SplitDecorManager splitDecorManager = this.this$0;
                            splitDecorManager.mScreenshot = null;
                            if (splitDecorManager.mRunningAnimationCount == 0 && (consumer2 = consumer) != null) {
                                consumer2.accept(Boolean.TRUE);
                                break;
                            }
                            break;
                        default:
                            this.this$0.releaseDecor(transaction2);
                            transaction2.apply();
                            transaction2.close();
                            if (this.this$0.mRunningAnimationCount == 0 && (consumer3 = consumer) != null) {
                                consumer3.accept(Boolean.TRUE);
                                break;
                            }
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i) {
                        case 0:
                            this.this$0.mRunningAnimationCount++;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            });
            this.mScreenshotAnimator.start();
        }
        if (this.mVeilIconView == null) {
            if (this.mRunningAnimationCount != 0 || consumer == null) {
                return;
            }
            consumer.accept(Boolean.FALSE);
            return;
        }
        this.mIsCurrentlyChanging = false;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mOldMainBounds.setEmpty();
        this.mOldSideBounds.setEmpty();
        this.mInstantaneousBounds.setEmpty();
        ValueAnimator valueAnimator2 = this.mFadeAnimator;
        if (valueAnimator2 != null && valueAnimator2.isRunning() && !this.mShown) {
            final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
            final int i2 = 1;
            this.mFadeAnimator.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.wm.shell.common.split.SplitDecorManager.1
                public final /* synthetic */ SplitDecorManager this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    Consumer consumer2;
                    Consumer consumer3;
                    switch (i2) {
                        case 0:
                            r2.mRunningAnimationCount--;
                            transaction3.remove(this.this$0.mScreenshot);
                            transaction3.apply();
                            transaction3.close();
                            SplitDecorManager splitDecorManager = this.this$0;
                            splitDecorManager.mScreenshot = null;
                            if (splitDecorManager.mRunningAnimationCount == 0 && (consumer2 = consumer) != null) {
                                consumer2.accept(Boolean.TRUE);
                                break;
                            }
                            break;
                        default:
                            this.this$0.releaseDecor(transaction3);
                            transaction3.apply();
                            transaction3.close();
                            if (this.this$0.mRunningAnimationCount == 0 && (consumer3 = consumer) != null) {
                                consumer3.accept(Boolean.TRUE);
                                break;
                            }
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    switch (i2) {
                        case 0:
                            this.this$0.mRunningAnimationCount++;
                            break;
                        default:
                            super.onAnimationStart(animator);
                            break;
                    }
                }
            });
        } else {
            if (this.mShown) {
                fadeOutDecor(new Runnable() { // from class: com.android.wm.shell.common.split.SplitDecorManager$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        SplitDecorManager splitDecorManager = SplitDecorManager.this;
                        Consumer consumer2 = consumer;
                        if (splitDecorManager.mRunningAnimationCount != 0 || consumer2 == null) {
                            return;
                        }
                        consumer2.accept(Boolean.TRUE);
                    }
                }, false);
                return;
            }
            releaseDecor(transaction);
            if (this.mRunningAnimationCount != 0 || consumer == null) {
                return;
            }
            consumer.accept(Boolean.FALSE);
        }
    }

    public final void release(SurfaceControl.Transaction transaction) {
        cancelRunningAnimations();
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost != null) {
            surfaceControlViewHost.release();
            this.mViewHost = null;
        }
        SurfaceControl surfaceControl = this.mIconLeash;
        if (surfaceControl != null) {
            transaction.remove(surfaceControl);
            this.mIconLeash = null;
        }
        SurfaceControl surfaceControl2 = this.mBackgroundLeash;
        if (surfaceControl2 != null) {
            transaction.remove(surfaceControl2);
            this.mBackgroundLeash = null;
        }
        SurfaceControl surfaceControl3 = this.mGapBackgroundLeash;
        if (surfaceControl3 != null) {
            transaction.remove(surfaceControl3);
            this.mGapBackgroundLeash = null;
        }
        SurfaceControl surfaceControl4 = this.mScreenshot;
        if (surfaceControl4 != null) {
            transaction.remove(surfaceControl4);
            this.mScreenshot = null;
        }
        this.mHostLeash = null;
        this.mIcon = null;
        this.mVeilIconView = null;
        this.mIsCurrentlyChanging = false;
        this.mShown = false;
        this.mOldMainBounds.setEmpty();
        this.mOldSideBounds.setEmpty();
        this.mInstantaneousBounds.setEmpty();
    }

    public final void releaseDecor(SurfaceControl.Transaction transaction) {
        SurfaceControl surfaceControl = this.mBackgroundLeash;
        if (surfaceControl != null) {
            transaction.remove(surfaceControl);
            this.mBackgroundLeash = null;
        }
        SurfaceControl surfaceControl2 = this.mGapBackgroundLeash;
        if (surfaceControl2 != null) {
            transaction.remove(surfaceControl2);
            this.mGapBackgroundLeash = null;
        }
        if (this.mIcon != null) {
            this.mVeilIconView.setVisibility(8);
            this.mVeilIconView.setImageDrawable(null);
            transaction.hide(this.mIconLeash);
            this.mIcon = null;
        }
    }

    public final void startFadeAnimation(final boolean z, final boolean z2, final Runnable runnable, boolean z3) {
        final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mFadeAnimator = ofFloat;
        if (z3) {
            ofFloat.setStartDelay(300L);
        }
        this.mFadeAnimator.setDuration(133L);
        this.mFadeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.common.split.SplitDecorManager$$ExternalSyntheticLambda3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SplitDecorManager splitDecorManager = SplitDecorManager.this;
                SurfaceControl.Transaction transaction2 = transaction;
                boolean z4 = z;
                splitDecorManager.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SurfaceControl surfaceControl = splitDecorManager.mBackgroundLeash;
                if (surfaceControl != null) {
                    transaction2.setAlpha(surfaceControl, z4 ? floatValue : 1.0f - floatValue);
                }
                SurfaceControl surfaceControl2 = splitDecorManager.mIconLeash;
                if (surfaceControl2 != null) {
                    if (!z4) {
                        floatValue = 1.0f - floatValue;
                    }
                    transaction2.setAlpha(surfaceControl2, floatValue);
                }
                transaction2.apply();
            }
        });
        this.mFadeAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.SplitDecorManager.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Runnable runnable2;
                SplitDecorManager splitDecorManager = SplitDecorManager.this;
                splitDecorManager.mRunningAnimationCount--;
                if (!z) {
                    SurfaceControl surfaceControl = splitDecorManager.mBackgroundLeash;
                    if (surfaceControl != null) {
                        transaction.hide(surfaceControl);
                    }
                    SurfaceControl surfaceControl2 = SplitDecorManager.this.mIconLeash;
                    if (surfaceControl2 != null) {
                        transaction.hide(surfaceControl2);
                    }
                }
                if (z2) {
                    SplitDecorManager.this.releaseDecor(transaction);
                }
                transaction.apply();
                transaction.close();
                if (SplitDecorManager.this.mRunningAnimationCount != 0 || (runnable2 = runnable) == null) {
                    return;
                }
                runnable2.run();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                SplitDecorManager splitDecorManager = SplitDecorManager.this;
                splitDecorManager.mRunningAnimationCount++;
                if (z) {
                    transaction.show(splitDecorManager.mBackgroundLeash).show(SplitDecorManager.this.mIconLeash);
                }
                SurfaceControl surfaceControl = SplitDecorManager.this.mGapBackgroundLeash;
                if (surfaceControl != null) {
                    transaction.setVisibility(surfaceControl, z);
                }
                transaction.apply();
            }
        });
        this.mFadeAnimator.start();
    }
}
