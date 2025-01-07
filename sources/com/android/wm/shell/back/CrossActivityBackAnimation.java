package com.android.wm.shell.back;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
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
import android.view.animation.Transformation;
import android.window.BackEvent;
import android.window.BackMotionEvent;
import android.window.BackProgressAnimator;
import android.window.IOnBackInvokedCallback;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.policy.SystemBarUtils;
import com.android.internal.protolog.ProtoLog;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda6;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda7;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.concurrent.Executor;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CrossActivityBackAnimation extends ShellBackAnimation {
    public final BackAnimationRunner backAnimationRunner;
    public final BackAnimationBackground background;
    public RemoteAnimationTarget closingTarget;
    public final Context context;
    public float cornerRadius;
    public int customizedBackgroundColor;
    public final float displayBoundsMargin;
    public boolean enteringHasSameLetterbox;
    public RemoteAnimationTarget enteringTarget;
    public IRemoteAnimationFinishedCallback finishCallback;
    public float gestureProgress;
    public boolean isLetterboxed;
    public SurfaceControl leftLetterboxLayer;
    public int letterboxColor;
    public float maxScrimAlpha;
    public SurfaceControl rightLetterboxLayer;
    public final RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer;
    public SurfaceControl scrimLayer;
    public int statusbarHeight;
    public final SurfaceControl.Transaction transaction;
    public boolean triggerBack;
    public final RectF startClosingRect = new RectF();
    public final RectF targetClosingRect = new RectF();
    public final RectF currentClosingRect = new RectF();
    public final RectF startEnteringRect = new RectF();
    public final RectF targetEnteringRect = new RectF();
    public final RectF currentEnteringRect = new RectF();
    public final Rect backAnimRect = new Rect();
    public final Rect cropRect = new Rect();
    public final RectF tempRectF = new RectF();
    public final PointF initialTouchPos = new PointF();
    public final Matrix transformMatrix = new Matrix();
    public final float[] tmpFloat9 = new float[9];
    public final BackProgressAnimator progressAnimator = new BackProgressAnimator();
    public final Interpolator gestureInterpolator = Interpolators.BACK_GESTURE;
    public final Interpolator verticalMoveInterpolator = new DecelerateInterpolator();
    public final FloatValueHolder postCommitFlingScale = new FloatValueHolder(100.0f);
    public float lastPostCommitFlingScale = 100.0f;
    public final SpringForce postCommitFlingSpring = new SpringForce(100.0f).setStiffness(200.0f).setDampingRatio(0.75f);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Callback extends IOnBackInvokedCallback.Default {
        public Callback() {
        }

        public final void onBackCancelled() {
            final CrossActivityBackAnimation crossActivityBackAnimation = CrossActivityBackAnimation.this;
            crossActivityBackAnimation.triggerBack = false;
            crossActivityBackAnimation.progressAnimator.onBackCancelled(new Runnable() { // from class: com.android.wm.shell.back.CrossActivityBackAnimation$Callback$onBackCancelled$1
                @Override // java.lang.Runnable
                public final void run() {
                    CrossActivityBackAnimation.this.finishAnimation();
                }
            });
        }

        public final void onBackInvoked() {
            CrossActivityBackAnimation crossActivityBackAnimation = CrossActivityBackAnimation.this;
            crossActivityBackAnimation.triggerBack = true;
            crossActivityBackAnimation.progressAnimator.reset();
            CrossActivityBackAnimation crossActivityBackAnimation2 = CrossActivityBackAnimation.this;
            crossActivityBackAnimation2.onGestureCommitted(crossActivityBackAnimation2.progressAnimator.getVelocity());
        }

        public final void onBackProgressed(BackMotionEvent backMotionEvent) {
            CrossActivityBackAnimation.this.triggerBack = backMotionEvent.getTriggerBack();
            CrossActivityBackAnimation.this.progressAnimator.onBackProgressed(backMotionEvent);
        }

        public final void onBackStarted(BackMotionEvent backMotionEvent) {
            CrossActivityBackAnimation.this.progressAnimator.removeOnBackCancelledFinishCallback();
            CrossActivityBackAnimation.this.startBackAnimation(backMotionEvent);
            final CrossActivityBackAnimation crossActivityBackAnimation = CrossActivityBackAnimation.this;
            crossActivityBackAnimation.progressAnimator.onBackStarted(backMotionEvent, new BackProgressAnimator.ProgressCallback() { // from class: com.android.wm.shell.back.CrossActivityBackAnimation$Callback$onBackStarted$1
                public final void onProgressUpdate(BackEvent backEvent) {
                    CrossActivityBackAnimation crossActivityBackAnimation2 = CrossActivityBackAnimation.this;
                    float interpolation = crossActivityBackAnimation2.gestureInterpolator.getInterpolation(backEvent.getProgress());
                    crossActivityBackAnimation2.gestureProgress = interpolation;
                    CrossActivityBackAnimationKt.setInterpolatedRectF(crossActivityBackAnimation2.currentClosingRect, crossActivityBackAnimation2.startClosingRect, crossActivityBackAnimation2.targetClosingRect, interpolation);
                    RectF rectF = crossActivityBackAnimation2.currentClosingRect;
                    float touchY = backEvent.getTouchY();
                    int height = crossActivityBackAnimation2.backAnimRect.height();
                    float f = touchY - crossActivityBackAnimation2.initialTouchPos.y;
                    float f2 = height;
                    float f3 = f2 / 2.0f;
                    float max = (f < 0.0f ? -1 : 1) * Math.max(0.0f, ((f2 - rectF.height()) / 2.0f) - crossActivityBackAnimation2.displayBoundsMargin) * ((DecelerateInterpolator) crossActivityBackAnimation2.verticalMoveInterpolator).getInterpolation(Math.min(f3, Math.abs(f)) / f3);
                    crossActivityBackAnimation2.currentClosingRect.offset(0.0f, max);
                    RemoteAnimationTarget remoteAnimationTarget = crossActivityBackAnimation2.closingTarget;
                    CrossActivityBackAnimation.applyTransform$default(crossActivityBackAnimation2, remoteAnimationTarget != null ? remoteAnimationTarget.leash : null, crossActivityBackAnimation2.currentClosingRect, 1.0f, null, 24);
                    CrossActivityBackAnimationKt.setInterpolatedRectF(crossActivityBackAnimation2.currentEnteringRect, crossActivityBackAnimation2.startEnteringRect, crossActivityBackAnimation2.targetEnteringRect, interpolation);
                    if (crossActivityBackAnimation2.getAllowEnteringYShift()) {
                        crossActivityBackAnimation2.currentEnteringRect.offset(0.0f, max);
                    }
                    Transformation preCommitEnteringBaseTransformation = crossActivityBackAnimation2.getPreCommitEnteringBaseTransformation(interpolation);
                    RemoteAnimationTarget remoteAnimationTarget2 = crossActivityBackAnimation2.enteringTarget;
                    CrossActivityBackAnimation.applyTransform$default(crossActivityBackAnimation2, remoteAnimationTarget2 != null ? remoteAnimationTarget2.leash : null, crossActivityBackAnimation2.currentEnteringRect, preCommitEnteringBaseTransformation != null ? preCommitEnteringBaseTransformation.getAlpha() : 1.0f, preCommitEnteringBaseTransformation, 16);
                    crossActivityBackAnimation2.applyTransaction();
                    crossActivityBackAnimation2.background.customizeStatusBarAppearance((int) crossActivityBackAnimation2.currentClosingRect.top);
                }
            });
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlingMode {
        public static final /* synthetic */ FlingMode[] $VALUES;
        public static final FlingMode FLING_BOUNCE;
        public static final FlingMode FLING_SHRINK;
        public static final FlingMode NO_FLING;

        static {
            FlingMode flingMode = new FlingMode("NO_FLING", 0);
            NO_FLING = flingMode;
            FlingMode flingMode2 = new FlingMode("FLING_SHRINK", 1);
            FLING_SHRINK = flingMode2;
            FlingMode flingMode3 = new FlingMode("FLING_BOUNCE", 2);
            FLING_BOUNCE = flingMode3;
            FlingMode[] flingModeArr = {flingMode, flingMode2, flingMode3};
            $VALUES = flingModeArr;
            EnumEntriesKt.enumEntries(flingModeArr);
        }

        public static FlingMode valueOf(String str) {
            return (FlingMode) Enum.valueOf(FlingMode.class, str);
        }

        public static FlingMode[] values() {
            return (FlingMode[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Runner extends IRemoteAnimationRunner.Default {
        public Runner() {
        }

        public final void onAnimationCancelled() {
            CrossActivityBackAnimation.this.finishAnimation();
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            ProtoLog.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, "Start back to activity animation.", new Object[0]);
            for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
                int i2 = remoteAnimationTarget.mode;
                if (i2 == 0) {
                    CrossActivityBackAnimation.this.enteringTarget = remoteAnimationTarget;
                } else if (i2 == 1) {
                    CrossActivityBackAnimation.this.closingTarget = remoteAnimationTarget;
                }
            }
            CrossActivityBackAnimation.this.finishCallback = iRemoteAnimationFinishedCallback;
        }
    }

    public CrossActivityBackAnimation(Context context, BackAnimationBackground backAnimationBackground, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, SurfaceControl.Transaction transaction, Handler handler) {
        this.context = context;
        this.background = backAnimationBackground;
        this.rootTaskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
        this.transaction = transaction;
        this.cornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(context);
        this.statusbarHeight = SystemBarUtils.getStatusBarHeight(context);
        this.backAnimationRunner = new BackAnimationRunner(new Callback(), new Runner(), context, 84, handler);
        this.displayBoundsMargin = context.getResources().getDimension(R.dimen.cross_task_back_vertical_margin);
    }

    public static /* synthetic */ void applyTransform$default(CrossActivityBackAnimation crossActivityBackAnimation, SurfaceControl surfaceControl, RectF rectF, float f, Transformation transformation, int i) {
        FlingMode flingMode = FlingMode.FLING_BOUNCE;
        if ((i & 8) != 0) {
            transformation = null;
        }
        Transformation transformation2 = transformation;
        if ((i & 16) != 0) {
            flingMode = FlingMode.NO_FLING;
        }
        crossActivityBackAnimation.applyTransform(surfaceControl, rectF, f, transformation2, flingMode);
    }

    public final void applyTransaction() {
        this.transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        this.transaction.apply();
    }

    public final void applyTransform(SurfaceControl surfaceControl, RectF rectF, float f, Transformation transformation, FlingMode flingMode) {
        float f2;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        this.tempRectF.set(rectF);
        if (flingMode != FlingMode.NO_FLING) {
            float min = Math.min(this.postCommitFlingScale.getValue() / 100.0f, flingMode == FlingMode.FLING_BOUNCE ? 1.0f : this.lastPostCommitFlingScale);
            this.lastPostCommitFlingScale = min;
            CrossActivityBackAnimationKt.scaleCentered$default(this.tempRectF, min);
        }
        float width = this.tempRectF.width() / this.backAnimRect.width();
        Matrix matrix = transformation != null ? transformation.getMatrix() : null;
        if (matrix == null) {
            matrix = this.transformMatrix;
            matrix.reset();
        }
        if (this.isLetterboxed && this.enteringHasSameLetterbox) {
            RemoteAnimationTarget remoteAnimationTarget = this.closingTarget;
            Intrinsics.checkNotNull(remoteAnimationTarget);
            f2 = remoteAnimationTarget.localBounds.left;
        } else {
            f2 = 0.0f;
        }
        matrix.postScale(width, width, f2, 0.0f);
        RectF rectF2 = this.tempRectF;
        matrix.postTranslate(rectF2.left, rectF2.top);
        this.transaction.setAlpha(surfaceControl, f).setMatrix(surfaceControl, matrix, this.tmpFloat9).setCrop(surfaceControl, this.cropRect).setCornerRadius(surfaceControl, this.cornerRadius);
    }

    public final SurfaceControl ensureLetterbox(Rect rect) {
        SurfaceControl.Builder hidden = new SurfaceControl.Builder().setName("Cross-Activity back animation letterbox").setCallsite("CrossActivityBackAnimation").setColorLayer().setOpaque(true).setHidden(false);
        this.rootTaskDisplayAreaOrganizer.attachToDisplayArea(0, hidden);
        SurfaceControl build = hidden.build();
        SurfaceControl.Transaction crop = this.transaction.setColor(build, new float[]{Color.red(this.letterboxColor) / 255.0f, Color.green(this.letterboxColor) / 255.0f, Color.blue(this.letterboxColor) / 255.0f}).setCrop(build, rect);
        RemoteAnimationTarget remoteAnimationTarget = this.closingTarget;
        Intrinsics.checkNotNull(remoteAnimationTarget);
        crop.setRelativeLayer(build, remoteAnimationTarget.leash, 1).show(build);
        return build;
    }

    public void finishAnimation() {
        SurfaceControl surfaceControl;
        RemoteAnimationTarget remoteAnimationTarget = this.enteringTarget;
        if (remoteAnimationTarget != null) {
            SurfaceControl surfaceControl2 = remoteAnimationTarget.leash;
            if (surfaceControl2 != null && surfaceControl2.isValid()) {
                this.transaction.setCornerRadius(remoteAnimationTarget.leash, 0.0f);
                if (!this.triggerBack) {
                    this.transaction.setAlpha(remoteAnimationTarget.leash, 0.0f);
                }
                remoteAnimationTarget.leash.release();
            }
            this.enteringTarget = null;
        }
        RemoteAnimationTarget remoteAnimationTarget2 = this.closingTarget;
        if (remoteAnimationTarget2 != null && (surfaceControl = remoteAnimationTarget2.leash) != null) {
            surfaceControl.release();
        }
        this.closingTarget = null;
        SurfaceControl.Transaction transaction = this.transaction;
        BackAnimationBackground backAnimationBackground = this.background;
        SurfaceControl surfaceControl3 = backAnimationBackground.mBackgroundSurface;
        if (surfaceControl3 != null) {
            if (surfaceControl3.isValid()) {
                transaction.remove(backAnimationBackground.mBackgroundSurface);
            }
            backAnimationBackground.mBackgroundSurface = null;
            backAnimationBackground.mIsRequestingStatusBarAppearance = false;
        }
        applyTransaction();
        this.transformMatrix.reset();
        this.initialTouchPos.set(0.0f, 0.0f);
        try {
            IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = this.finishCallback;
            if (iRemoteAnimationFinishedCallback != null) {
                iRemoteAnimationFinishedCallback.onAnimationFinished();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.finishCallback = null;
        SurfaceControl surfaceControl4 = this.scrimLayer;
        if (surfaceControl4 != null && surfaceControl4.isValid()) {
            this.transaction.remove(surfaceControl4);
            applyTransaction();
        }
        this.scrimLayer = null;
        SurfaceControl surfaceControl5 = this.leftLetterboxLayer;
        if ((surfaceControl5 != null && surfaceControl5.isValid()) || ((surfaceControl5 = this.rightLetterboxLayer) != null && surfaceControl5.isValid())) {
            this.transaction.remove(surfaceControl5);
            applyTransaction();
        }
        this.leftLetterboxLayer = null;
        this.rightLetterboxLayer = null;
        this.isLetterboxed = false;
        this.enteringHasSameLetterbox = false;
        this.lastPostCommitFlingScale = 100.0f;
        this.gestureProgress = 0.0f;
        this.triggerBack = false;
    }

    public abstract boolean getAllowEnteringYShift();

    public abstract long getPostCommitAnimationDuration();

    public Transformation getPreCommitEnteringBaseTransformation(float f) {
        return null;
    }

    public final void onConfigurationChanged() {
        this.cornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(this.context);
        this.statusbarHeight = SystemBarUtils.getStatusBarHeight(this.context);
    }

    public void onGestureCommitted(float f) {
        RemoteAnimationTarget remoteAnimationTarget = this.closingTarget;
        if ((remoteAnimationTarget != null ? remoteAnimationTarget.leash : null) != null) {
            RemoteAnimationTarget remoteAnimationTarget2 = this.enteringTarget;
            if ((remoteAnimationTarget2 != null ? remoteAnimationTarget2.leash : null) != null) {
                Intrinsics.checkNotNull(remoteAnimationTarget2);
                if (remoteAnimationTarget2.leash.isValid()) {
                    RemoteAnimationTarget remoteAnimationTarget3 = this.closingTarget;
                    Intrinsics.checkNotNull(remoteAnimationTarget3);
                    if (remoteAnimationTarget3.leash.isValid()) {
                        SpringAnimation spring = new SpringAnimation(this.postCommitFlingScale, 100.0f).setStartVelocity(RangesKt.coerceIn(this.gestureProgress < 0.1f ? -120.0f : (-f) * 100.0f, -1000.0f, 0.0f)).setStartValue(100.0f).setSpring(this.postCommitFlingSpring);
                        spring.start();
                        spring.doAnimationFrame(Choreographer.getInstance().getLastFrameTimeNanos() / 1000000);
                        ValueAnimator duration = ValueAnimator.ofFloat(1.0f, 0.0f).setDuration(getPostCommitAnimationDuration());
                        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.back.CrossActivityBackAnimation$onGestureCommitted$1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                float animatedFraction = valueAnimator.getAnimatedFraction();
                                CrossActivityBackAnimation.this.onPostCommitProgress(animatedFraction);
                                if (animatedFraction > 0.8f) {
                                    EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = CrossActivityBackAnimation.this.background.mCustomizer;
                                    Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                                    EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                                    edgeBackGestureHandler.getClass();
                                    executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, null, 1));
                                }
                            }
                        });
                        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.back.CrossActivityBackAnimation$onGestureCommitted$2
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = CrossActivityBackAnimation.this.background.mCustomizer;
                                Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                                EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                                edgeBackGestureHandler.getClass();
                                executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, null, 1));
                                CrossActivityBackAnimation.this.finishAnimation();
                            }
                        });
                        duration.start();
                        return;
                    }
                }
            }
        }
        finishAnimation();
    }

    public abstract void onPostCommitProgress(float f);

    public abstract void preparePreCommitClosingRectMovement(int i);

    public abstract void preparePreCommitEnteringRectMovement();

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void startBackAnimation(android.window.BackMotionEvent r11) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.back.CrossActivityBackAnimation.startBackAnimation(android.window.BackMotionEvent):void");
    }
}
