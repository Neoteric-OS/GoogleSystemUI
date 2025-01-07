package com.android.wm.shell.back;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.MathUtils;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.window.BackMotionEvent;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.back.CrossActivityBackAnimation;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomCrossActivityBackAnimation extends CrossActivityBackAnimation {
    public Animation closeAnimation;
    public final CustomAnimationLoader customAnimationLoader;
    public Animation enterAnimation;
    public final Transformation transformation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationLoadResult {
        public int backgroundColor;
        public Animation closeAnimation;
        public Animation enterAnimation;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CustomCrossActivityBackAnimation(android.content.Context r8, com.android.wm.shell.back.BackAnimationBackground r9, com.android.wm.shell.RootTaskDisplayAreaOrganizer r10, android.os.Handler r11) {
        /*
            r7 = this;
            android.view.SurfaceControl$Transaction r4 = new android.view.SurfaceControl$Transaction
            r4.<init>()
            com.android.wm.shell.back.CustomAnimationLoader r6 = new com.android.wm.shell.back.CustomAnimationLoader
            com.android.internal.policy.TransitionAnimation r0 = new com.android.internal.policy.TransitionAnimation
            r1 = 0
            java.lang.String r2 = "CustomCrossActivityBackAnimation"
            r0.<init>(r8, r1, r2)
            r6.<init>(r0)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r7.customAnimationLoader = r6
            android.view.animation.Transformation r8 = new android.view.animation.Transformation
            r8.<init>()
            r7.transformation = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.back.CustomCrossActivityBackAnimation.<init>(android.content.Context, com.android.wm.shell.back.BackAnimationBackground, com.android.wm.shell.RootTaskDisplayAreaOrganizer, android.os.Handler):void");
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void finishAnimation() {
        Animation animation = this.closeAnimation;
        if (animation != null) {
            animation.reset();
        }
        this.closeAnimation = null;
        Animation animation2 = this.enterAnimation;
        if (animation2 != null) {
            animation2.reset();
        }
        this.enterAnimation = null;
        this.transformation.clear();
        super.finishAnimation();
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final boolean getAllowEnteringYShift() {
        return false;
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final long getPostCommitAnimationDuration() {
        Animation animation = this.closeAnimation;
        Intrinsics.checkNotNull(animation);
        long duration = animation.getDuration();
        Animation animation2 = this.enterAnimation;
        Intrinsics.checkNotNull(animation2);
        return Math.min(2000L, Math.max(duration, animation2.getDuration()));
    }

    public final float getPostCommitProgress(Animation animation, float f) {
        if (animation.getDuration() == 0) {
            return 1.0f;
        }
        return Math.min(1.0f, (getPostCommitAnimationDuration() / Math.min(2000L, animation.getDuration())) * f);
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final Transformation getPreCommitEnteringBaseTransformation(float f) {
        this.transformation.clear();
        Animation animation = this.enterAnimation;
        Intrinsics.checkNotNull(animation);
        animation.getTransformationAt(f * 0.2f, this.transformation);
        return this.transformation;
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void onPostCommitProgress(float f) {
        SurfaceControl surfaceControl = this.scrimLayer;
        if (surfaceControl != null) {
            this.transaction.setAlpha(surfaceControl, (1.0f - f) * this.maxScrimAlpha);
        }
        if (this.closingTarget == null || this.enteringTarget == null) {
            return;
        }
        Animation animation = this.closeAnimation;
        Intrinsics.checkNotNull(animation);
        float postCommitProgress = getPostCommitProgress(animation, f);
        RemoteAnimationTarget remoteAnimationTarget = this.closingTarget;
        Intrinsics.checkNotNull(remoteAnimationTarget);
        SurfaceControl surfaceControl2 = remoteAnimationTarget.leash;
        RectF rectF = this.currentClosingRect;
        Animation animation2 = this.closeAnimation;
        Intrinsics.checkNotNull(animation2);
        CrossActivityBackAnimation.FlingMode flingMode = CrossActivityBackAnimation.FlingMode.FLING_SHRINK;
        this.transformation.clear();
        animation2.getTransformationAt(postCommitProgress, this.transformation);
        applyTransform(surfaceControl2, rectF, this.transformation.getAlpha(), this.transformation, flingMode);
        float f2 = this.gestureProgress * 0.2f;
        Animation animation3 = this.enterAnimation;
        Intrinsics.checkNotNull(animation3);
        float lerp = MathUtils.lerp(f2, 1.0f, getPostCommitProgress(animation3, f));
        RemoteAnimationTarget remoteAnimationTarget2 = this.enteringTarget;
        Intrinsics.checkNotNull(remoteAnimationTarget2);
        SurfaceControl surfaceControl3 = remoteAnimationTarget2.leash;
        RectF rectF2 = this.currentEnteringRect;
        Animation animation4 = this.enterAnimation;
        Intrinsics.checkNotNull(animation4);
        CrossActivityBackAnimation.FlingMode flingMode2 = CrossActivityBackAnimation.FlingMode.NO_FLING;
        this.transformation.clear();
        animation4.getTransformationAt(lerp, this.transformation);
        applyTransform(surfaceControl3, rectF2, this.transformation.getAlpha(), this.transformation, flingMode2);
        applyTransaction();
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void preparePreCommitClosingRectMovement(int i) {
        this.startClosingRect.set(this.backAnimRect);
        this.targetClosingRect.set(this.startClosingRect);
        CrossActivityBackAnimationKt.scaleCentered$default(this.targetClosingRect, 0.9f);
        float f = this.displayBoundsMargin;
        this.targetClosingRect.offset(i != 1 ? (this.startClosingRect.right - this.targetClosingRect.right) - f : (-this.targetClosingRect.left) + f, 0.0f);
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void preparePreCommitEnteringRectMovement() {
        this.startEnteringRect.set(this.startClosingRect);
        this.targetEnteringRect.set(this.startClosingRect);
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void startBackAnimation(BackMotionEvent backMotionEvent) {
        RemoteAnimationTarget remoteAnimationTarget;
        super.startBackAnimation(backMotionEvent);
        Animation animation = this.closeAnimation;
        if (animation == null || this.enterAnimation == null || (remoteAnimationTarget = this.closingTarget) == null || this.enteringTarget == null) {
            ProtoLog.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, "Enter animation or close animation is null.", new Object[0]);
            return;
        }
        Intrinsics.checkNotNull(remoteAnimationTarget);
        Rect rect = remoteAnimationTarget.localBounds;
        int width = rect.width();
        int height = rect.height();
        animation.initialize(width, height, width, height);
        Animation animation2 = this.enterAnimation;
        Intrinsics.checkNotNull(animation2);
        RemoteAnimationTarget remoteAnimationTarget2 = this.enteringTarget;
        Intrinsics.checkNotNull(remoteAnimationTarget2);
        Rect rect2 = remoteAnimationTarget2.localBounds;
        int width2 = rect2.width();
        int height2 = rect2.height();
        animation2.initialize(width2, height2, width2, height2);
    }
}
