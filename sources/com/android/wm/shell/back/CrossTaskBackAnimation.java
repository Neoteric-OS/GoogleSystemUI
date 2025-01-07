package com.android.wm.shell.back;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.RemoteException;
import android.view.Choreographer;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.window.BackEvent;
import android.window.BackMotionEvent;
import android.window.BackProgressAnimator;
import android.window.IOnBackInvokedCallback;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.policy.SystemBarUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda6;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda7;
import com.android.wm.shell.R;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CrossTaskBackAnimation extends ShellBackAnimation {
    public final BackAnimationRunner mBackAnimationRunner;
    public final BackAnimationBackground mBackground;
    public RemoteAnimationTarget mClosingTarget;
    public final Context mContext;
    public float mCornerRadius;
    public RemoteAnimationTarget mEnteringTarget;
    public IRemoteAnimationFinishedCallback mFinishCallback;
    public float mInterWindowMargin;
    public boolean mIsRightEdge;
    public int mStatusbarHeight;
    public float mVerticalMargin;
    public final Rect mStartTaskRect = new Rect();
    public final Rect mClosingStartRect = new Rect();
    public final RectF mClosingCurrentRect = new RectF();
    public final Rect mEnteringStartRect = new Rect();
    public final RectF mEnteringCurrentRect = new RectF();
    public final PointF mInitialTouchPos = new PointF();
    public final Interpolator mPostAnimationInterpolator = Interpolators.EMPHASIZED;
    public final Interpolator mProgressInterpolator = Interpolators.BACK_GESTURE;
    public final Interpolator mVerticalMoveInterpolator = new DecelerateInterpolator();
    public final Matrix mTransformMatrix = new Matrix();
    public final float[] mTmpFloat9 = new float[9];
    public final SurfaceControl.Transaction mTransaction = new SurfaceControl.Transaction();
    public boolean mBackInProgress = false;
    public final PointF mTouchPos = new PointF();
    public final BackProgressAnimator mProgressAnimator = new BackProgressAnimator();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Callback extends IOnBackInvokedCallback.Default {
        public Callback() {
        }

        public final void onBackCancelled() {
            final CrossTaskBackAnimation crossTaskBackAnimation = CrossTaskBackAnimation.this;
            crossTaskBackAnimation.mProgressAnimator.onBackCancelled(new Runnable() { // from class: com.android.wm.shell.back.CrossTaskBackAnimation$Callback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CrossTaskBackAnimation.this.finishAnimation$1();
                }
            });
        }

        public final void onBackInvoked() {
            CrossTaskBackAnimation.this.mProgressAnimator.reset();
            final CrossTaskBackAnimation crossTaskBackAnimation = CrossTaskBackAnimation.this;
            if (crossTaskBackAnimation.mEnteringTarget == null || crossTaskBackAnimation.mClosingTarget == null) {
                crossTaskBackAnimation.finishAnimation$1();
                return;
            }
            crossTaskBackAnimation.mEnteringCurrentRect.round(crossTaskBackAnimation.mEnteringStartRect);
            crossTaskBackAnimation.mClosingCurrentRect.round(crossTaskBackAnimation.mClosingStartRect);
            ValueAnimator duration = ValueAnimator.ofFloat(1.0f, 0.0f).setDuration(500L);
            duration.setInterpolator(crossTaskBackAnimation.mPostAnimationInterpolator);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.back.CrossTaskBackAnimation$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CrossTaskBackAnimation crossTaskBackAnimation2 = CrossTaskBackAnimation.this;
                    crossTaskBackAnimation2.getClass();
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    float mapRange = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mEnteringStartRect.left, crossTaskBackAnimation2.mStartTaskRect.left);
                    float mapRange2 = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mEnteringStartRect.top, crossTaskBackAnimation2.mStartTaskRect.top);
                    crossTaskBackAnimation2.mEnteringCurrentRect.set(mapRange, mapRange2, CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mEnteringStartRect.width(), crossTaskBackAnimation2.mStartTaskRect.width()) + mapRange, CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mEnteringStartRect.height(), crossTaskBackAnimation2.mStartTaskRect.height()) + mapRange2);
                    crossTaskBackAnimation2.applyTransform(crossTaskBackAnimation2.mEnteringTarget.leash, crossTaskBackAnimation2.mEnteringCurrentRect, crossTaskBackAnimation2.mCornerRadius);
                    Rect rect = crossTaskBackAnimation2.mStartTaskRect;
                    Rect rect2 = crossTaskBackAnimation2.mStartTaskRect;
                    float mapRange3 = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mClosingStartRect.left, ((rect.width() * 0.19999999f) / 2.0f) + rect.left);
                    float mapRange4 = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mClosingStartRect.top, ((rect2.height() * 0.19999999f) / 2.0f) + rect2.top);
                    float mapRange5 = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mClosingStartRect.width(), crossTaskBackAnimation2.mStartTaskRect.width() * 0.8f);
                    float mapRange6 = CrossTaskBackAnimation.mapRange(animatedFraction, crossTaskBackAnimation2.mClosingStartRect.height(), crossTaskBackAnimation2.mStartTaskRect.height() * 0.8f);
                    SurfaceControl surfaceControl = crossTaskBackAnimation2.mClosingTarget.leash;
                    if (surfaceControl != null && surfaceControl.isValid()) {
                        crossTaskBackAnimation2.mTransaction.setLayer(crossTaskBackAnimation2.mClosingTarget.leash, 0);
                    }
                    crossTaskBackAnimation2.mClosingCurrentRect.set(mapRange3, mapRange4, mapRange5 + mapRange3, mapRange6 + mapRange4);
                    crossTaskBackAnimation2.applyTransform(crossTaskBackAnimation2.mClosingTarget.leash, crossTaskBackAnimation2.mClosingCurrentRect, crossTaskBackAnimation2.mCornerRadius);
                    if (animatedFraction > 0.8f) {
                        EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = crossTaskBackAnimation2.mBackground.mCustomizer;
                        Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                        EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                        edgeBackGestureHandler.getClass();
                        executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, null, 1));
                    }
                    crossTaskBackAnimation2.applyTransaction$1();
                }
            });
            duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.back.CrossTaskBackAnimation.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = CrossTaskBackAnimation.this.mBackground.mCustomizer;
                    Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                    EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                    edgeBackGestureHandler.getClass();
                    executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, null, 1));
                    CrossTaskBackAnimation.this.finishAnimation$1();
                }
            });
            duration.start();
        }

        public final void onBackProgressed(BackMotionEvent backMotionEvent) {
            CrossTaskBackAnimation.this.mProgressAnimator.onBackProgressed(backMotionEvent);
        }

        public final void onBackStarted(BackMotionEvent backMotionEvent) {
            CrossTaskBackAnimation.this.mProgressAnimator.removeOnBackCancelledFinishCallback();
            CrossTaskBackAnimation.this.mIsRightEdge = backMotionEvent.getSwipeEdge() == 1;
            CrossTaskBackAnimation.this.mInitialTouchPos.set(backMotionEvent.getTouchX(), backMotionEvent.getTouchY());
            final CrossTaskBackAnimation crossTaskBackAnimation = CrossTaskBackAnimation.this;
            crossTaskBackAnimation.mProgressAnimator.onBackStarted(backMotionEvent, new BackProgressAnimator.ProgressCallback() { // from class: com.android.wm.shell.back.CrossTaskBackAnimation$Callback$$ExternalSyntheticLambda0
                public final void onProgressUpdate(BackEvent backEvent) {
                    CrossTaskBackAnimation crossTaskBackAnimation2 = CrossTaskBackAnimation.this;
                    if (!crossTaskBackAnimation2.mBackInProgress) {
                        crossTaskBackAnimation2.mBackInProgress = true;
                    }
                    float progress = backEvent.getProgress();
                    crossTaskBackAnimation2.mTouchPos.set(backEvent.getTouchX(), backEvent.getTouchY());
                    float interpolation = crossTaskBackAnimation2.mProgressInterpolator.getInterpolation(progress);
                    if (crossTaskBackAnimation2.mEnteringTarget == null || crossTaskBackAnimation2.mClosingTarget == null) {
                        return;
                    }
                    float touchY = backEvent.getTouchY();
                    int width = crossTaskBackAnimation2.mStartTaskRect.width();
                    int height = crossTaskBackAnimation2.mStartTaskRect.height();
                    float mapRange = CrossTaskBackAnimation.mapRange(interpolation, 1.0f, 0.8f);
                    float f = width;
                    float f2 = mapRange * f;
                    float f3 = height;
                    float f4 = mapRange * f3;
                    float f5 = touchY - crossTaskBackAnimation2.mInitialTouchPos.y;
                    float f6 = f3 / 2.0f;
                    float f7 = f3 - f4;
                    float max = (f7 * 0.5f) + (Math.max(0.0f, (f7 / 2.0f) - crossTaskBackAnimation2.mVerticalMargin) * ((DecelerateInterpolator) crossTaskBackAnimation2.mVerticalMoveInterpolator).getInterpolation(Math.min(f6, Math.abs(f5)) / f6) * (f5 < 0.0f ? -1.0f : 1.0f));
                    float m = crossTaskBackAnimation2.mIsRightEdge ? AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, f2, 0.5f, f2) : f - (interpolation * crossTaskBackAnimation2.mVerticalMargin);
                    float f8 = m - f2;
                    float f9 = f4 + max;
                    crossTaskBackAnimation2.mClosingCurrentRect.set(f8, max, m, f9);
                    RectF rectF = crossTaskBackAnimation2.mEnteringCurrentRect;
                    float f10 = crossTaskBackAnimation2.mInterWindowMargin;
                    rectF.set((f8 - f2) - f10, max, f8 - f10, f9);
                    crossTaskBackAnimation2.applyTransform(crossTaskBackAnimation2.mClosingTarget.leash, crossTaskBackAnimation2.mClosingCurrentRect, crossTaskBackAnimation2.mCornerRadius);
                    crossTaskBackAnimation2.applyTransform(crossTaskBackAnimation2.mEnteringTarget.leash, crossTaskBackAnimation2.mEnteringCurrentRect, crossTaskBackAnimation2.mCornerRadius);
                    crossTaskBackAnimation2.applyTransaction$1();
                    crossTaskBackAnimation2.mBackground.customizeStatusBarAppearance((int) max);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Runner extends IRemoteAnimationRunner.Default {
        public Runner() {
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            RemoteAnimationTarget remoteAnimationTarget;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -8349216159198398073L, 0, null);
            }
            for (RemoteAnimationTarget remoteAnimationTarget2 : remoteAnimationTargetArr) {
                int i2 = remoteAnimationTarget2.mode;
                if (i2 == 1) {
                    CrossTaskBackAnimation.this.mClosingTarget = remoteAnimationTarget2;
                }
                if (i2 == 0) {
                    CrossTaskBackAnimation.this.mEnteringTarget = remoteAnimationTarget2;
                }
            }
            CrossTaskBackAnimation crossTaskBackAnimation = CrossTaskBackAnimation.this;
            if (crossTaskBackAnimation.mEnteringTarget != null && (remoteAnimationTarget = crossTaskBackAnimation.mClosingTarget) != null) {
                crossTaskBackAnimation.mStartTaskRect.set(remoteAnimationTarget.windowConfiguration.getBounds());
                crossTaskBackAnimation.mStartTaskRect.offsetTo(0, 0);
                crossTaskBackAnimation.mStartTaskRect.inset(0, 0, 0, crossTaskBackAnimation.mClosingTarget.contentInsets.bottom);
                crossTaskBackAnimation.mBackground.ensureBackground(crossTaskBackAnimation.mClosingTarget.windowConfiguration.getBounds(), 4408122, crossTaskBackAnimation.mTransaction, crossTaskBackAnimation.mStatusbarHeight, null, 0.0f);
                crossTaskBackAnimation.mInterWindowMargin = crossTaskBackAnimation.mContext.getResources().getDimension(R.dimen.cross_task_back_inter_window_margin);
                crossTaskBackAnimation.mVerticalMargin = crossTaskBackAnimation.mContext.getResources().getDimension(R.dimen.cross_task_back_vertical_margin);
            } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 2843564892737714090L, 0, null);
            }
            CrossTaskBackAnimation.this.mFinishCallback = iRemoteAnimationFinishedCallback;
        }
    }

    public CrossTaskBackAnimation(Context context, BackAnimationBackground backAnimationBackground, Handler handler) {
        this.mBackAnimationRunner = new BackAnimationRunner(new Callback(), new Runner(), context, 85, handler);
        this.mBackground = backAnimationBackground;
        this.mContext = context;
        this.mCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(context);
        this.mStatusbarHeight = SystemBarUtils.getStatusBarHeight(context);
    }

    public static float mapRange(float f, float f2, float f3) {
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(f3, f2, f, f2);
    }

    public final void applyTransaction$1() {
        this.mTransaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        this.mTransaction.apply();
    }

    public final void applyTransform(SurfaceControl surfaceControl, RectF rectF, float f) {
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        float width = rectF.width() / this.mStartTaskRect.width();
        this.mTransformMatrix.reset();
        this.mTransformMatrix.setScale(width, width);
        this.mTransformMatrix.postTranslate(rectF.left, rectF.top);
        this.mTransaction.setMatrix(surfaceControl, this.mTransformMatrix, this.mTmpFloat9).setWindowCrop(surfaceControl, this.mStartTaskRect).setCornerRadius(surfaceControl, f);
    }

    public final void finishAnimation$1() {
        RemoteAnimationTarget remoteAnimationTarget = this.mEnteringTarget;
        if (remoteAnimationTarget != null) {
            remoteAnimationTarget.leash.release();
            this.mEnteringTarget = null;
        }
        RemoteAnimationTarget remoteAnimationTarget2 = this.mClosingTarget;
        if (remoteAnimationTarget2 != null) {
            remoteAnimationTarget2.leash.release();
            this.mClosingTarget = null;
        }
        BackAnimationBackground backAnimationBackground = this.mBackground;
        if (backAnimationBackground != null) {
            SurfaceControl.Transaction transaction = this.mTransaction;
            SurfaceControl surfaceControl = backAnimationBackground.mBackgroundSurface;
            if (surfaceControl != null) {
                if (surfaceControl.isValid()) {
                    transaction.remove(backAnimationBackground.mBackgroundSurface);
                }
                backAnimationBackground.mBackgroundSurface = null;
                backAnimationBackground.mIsRequestingStatusBarAppearance = false;
            }
        }
        applyTransaction$1();
        this.mBackInProgress = false;
        this.mTransformMatrix.reset();
        this.mClosingCurrentRect.setEmpty();
        this.mInitialTouchPos.set(0.0f, 0.0f);
        IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = this.mFinishCallback;
        if (iRemoteAnimationFinishedCallback != null) {
            try {
                iRemoteAnimationFinishedCallback.onAnimationFinished();
            } catch (RemoteException unused) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -4706434252374524577L, 0, null);
                }
            }
            this.mFinishCallback = null;
        }
    }
}
