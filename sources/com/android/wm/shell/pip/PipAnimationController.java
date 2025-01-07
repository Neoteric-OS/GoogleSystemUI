package com.android.wm.shell.pip;

import android.animation.AnimationHandler;
import android.animation.Animator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.TaskInfo;
import android.graphics.Rect;
import android.util.RotationUtils;
import android.view.SurfaceControl;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PipSurfaceTransactionHelper;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.pip.PipContentOverlay;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipAnimationController {
    public PipTransitionAnimator mCurrentAnimator;
    public long mLastOneShotAlphaAnimationTime;
    public final PipSurfaceTransactionHelper mSurfaceTransactionHelper;
    public final ThreadLocal mSfAnimationHandlerThreadLocal = ThreadLocal.withInitial(new PipAnimationController$$ExternalSyntheticLambda0());
    public int mOneShotAnimationType = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PipAnimationCallback {
        public abstract void onPipAnimationCancel(PipTransitionAnimator pipTransitionAnimator);

        public abstract void onPipAnimationEnd(TaskInfo taskInfo, SurfaceControl.Transaction transaction, PipTransitionAnimator pipTransitionAnimator);

        public abstract void onPipAnimationStart(PipTransitionAnimator pipTransitionAnimator);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PipTransactionHandler {
        public abstract boolean handlePipTransaction(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, Rect rect, float f);
    }

    public PipAnimationController(PipSurfaceTransactionHelper pipSurfaceTransactionHelper) {
        this.mSurfaceTransactionHelper = pipSurfaceTransactionHelper;
    }

    public static boolean isInPipDirection(int i) {
        return i == 2;
    }

    public static boolean isOutPipDirection(int i) {
        return i == 3 || i == 4;
    }

    public PipTransitionAnimator getAnimator(TaskInfo taskInfo, SurfaceControl surfaceControl, final Rect rect, float f, float f2) {
        PipTransitionAnimator pipTransitionAnimator = this.mCurrentAnimator;
        if (pipTransitionAnimator == null) {
            int i = PipTransitionAnimator.$r8$clinit;
            PipTransitionAnimator pipTransitionAnimator2 = new PipTransitionAnimator(taskInfo, surfaceControl, rect, Float.valueOf(f), Float.valueOf(f), Float.valueOf(f2)) { // from class: com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator.1
                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void applySurfaceControlTransaction(SurfaceControl surfaceControl2, SurfaceControl.Transaction transaction, float f3) {
                    float floatValue = (((Float) getEndValue()).floatValue() * f3) + ((1.0f - f3) * ((Float) this.mStartValue).floatValue());
                    this.mCurrentValue = Float.valueOf(floatValue);
                    PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
                    pipSurfaceTransactionHelper.getClass();
                    transaction.setAlpha(surfaceControl2, floatValue);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl2, true);
                    pipSurfaceTransactionHelper.shadow(transaction, surfaceControl2, shouldApplyShadowRadius());
                    Rect rect2 = rect;
                    PipTransactionHandler pipTransactionHandler = this.mPipTransactionHandler;
                    if (pipTransactionHandler != null ? pipTransactionHandler.handlePipTransaction(surfaceControl2, transaction, rect2, floatValue) : false) {
                        return;
                    }
                    transaction.apply();
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void onStartTransaction(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl2) {
                    if (getTransitionDirection() == 5) {
                        return;
                    }
                    PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
                    pipSurfaceTransactionHelper.resetScale(this.mDestinationBounds, transaction, surfaceControl2);
                    pipSurfaceTransactionHelper.crop(this.mDestinationBounds, transaction, surfaceControl2);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl2, true);
                    pipSurfaceTransactionHelper.shadow(transaction, surfaceControl2, shouldApplyShadowRadius());
                    transaction.show(surfaceControl2);
                    transaction.apply();
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void updateEndValue(Object obj) {
                    this.mEndValue = (Float) obj;
                    this.mStartValue = this.mCurrentValue;
                }
            };
            setupPipTransitionAnimator(pipTransitionAnimator2);
            this.mCurrentAnimator = pipTransitionAnimator2;
        } else if (pipTransitionAnimator.getAnimationType() == 1 && Objects.equals(rect, this.mCurrentAnimator.mDestinationBounds) && this.mCurrentAnimator.isRunning()) {
            this.mCurrentAnimator.updateEndValue(Float.valueOf(f2));
        } else {
            this.mCurrentAnimator.cancel();
            PipTransitionAnimator pipTransitionAnimator3 = new PipTransitionAnimator(taskInfo, surfaceControl, rect, Float.valueOf(f), Float.valueOf(f), Float.valueOf(f2)) { // from class: com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator.1
                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void applySurfaceControlTransaction(SurfaceControl surfaceControl2, SurfaceControl.Transaction transaction, float f3) {
                    float floatValue = (((Float) getEndValue()).floatValue() * f3) + ((1.0f - f3) * ((Float) this.mStartValue).floatValue());
                    this.mCurrentValue = Float.valueOf(floatValue);
                    PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
                    pipSurfaceTransactionHelper.getClass();
                    transaction.setAlpha(surfaceControl2, floatValue);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl2, true);
                    pipSurfaceTransactionHelper.shadow(transaction, surfaceControl2, shouldApplyShadowRadius());
                    Rect rect2 = rect;
                    PipTransactionHandler pipTransactionHandler = this.mPipTransactionHandler;
                    if (pipTransactionHandler != null ? pipTransactionHandler.handlePipTransaction(surfaceControl2, transaction, rect2, floatValue) : false) {
                        return;
                    }
                    transaction.apply();
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void onStartTransaction(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl2) {
                    if (getTransitionDirection() == 5) {
                        return;
                    }
                    PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
                    pipSurfaceTransactionHelper.resetScale(this.mDestinationBounds, transaction, surfaceControl2);
                    pipSurfaceTransactionHelper.crop(this.mDestinationBounds, transaction, surfaceControl2);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl2, true);
                    pipSurfaceTransactionHelper.shadow(transaction, surfaceControl2, shouldApplyShadowRadius());
                    transaction.show(surfaceControl2);
                    transaction.apply();
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void updateEndValue(Object obj) {
                    this.mEndValue = (Float) obj;
                    this.mStartValue = this.mCurrentValue;
                }
            };
            setupPipTransitionAnimator(pipTransitionAnimator3);
            this.mCurrentAnimator = pipTransitionAnimator3;
        }
        return this.mCurrentAnimator;
    }

    public final void setupPipTransitionAnimator(PipTransitionAnimator pipTransitionAnimator) {
        pipTransitionAnimator.mSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipTransitionAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        pipTransitionAnimator.setFloatValues(0.0f, 1.0f);
        pipTransitionAnimator.setAnimationHandler((AnimationHandler) this.mSfAnimationHandlerThreadLocal.get());
    }

    public PipTransitionAnimator getAnimator(TaskInfo taskInfo, SurfaceControl surfaceControl, Rect rect, Rect rect2, Rect rect3, Rect rect4, int i, float f, int i2) {
        PipTransitionAnimator pipTransitionAnimator = this.mCurrentAnimator;
        if (pipTransitionAnimator == null) {
            PipTransitionAnimator.AnonymousClass2 ofBounds = PipTransitionAnimator.ofBounds(taskInfo, surfaceControl, rect2, rect2, rect3, rect4, i, 0.0f, i2);
            setupPipTransitionAnimator(ofBounds);
            this.mCurrentAnimator = ofBounds;
        } else if (pipTransitionAnimator.getAnimationType() == 1 && this.mCurrentAnimator.isRunning()) {
            this.mCurrentAnimator.setDestinationBounds(rect3);
        } else if (this.mCurrentAnimator.getAnimationType() == 0 && this.mCurrentAnimator.isRunning()) {
            this.mCurrentAnimator.setDestinationBounds(rect3);
            this.mCurrentAnimator.updateEndValue(new Rect(rect3));
        } else {
            this.mCurrentAnimator.cancel();
            PipTransitionAnimator.AnonymousClass2 ofBounds2 = PipTransitionAnimator.ofBounds(taskInfo, surfaceControl, rect, rect2, rect3, rect4, i, f, i2);
            setupPipTransitionAnimator(ofBounds2);
            this.mCurrentAnimator = ofBounds2;
        }
        return this.mCurrentAnimator;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PipTransitionAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
        public static final /* synthetic */ int $r8$clinit = 0;
        public final int mAnimationType;
        public final Object mBaseValue;
        public PipContentOverlay mContentOverlay;
        public Object mCurrentValue;
        public final Rect mDestinationBounds;
        public Object mEndValue;
        public boolean mHasRequestedEnd;
        public final SurfaceControl mLeash;
        public PipAnimationCallback mPipAnimationCallback;
        public PipTransactionHandler mPipTransactionHandler;
        public Object mStartValue;
        public PipSurfaceTransactionHelper.SurfaceControlTransactionFactory mSurfaceControlTransactionFactory;
        public PipSurfaceTransactionHelper mSurfaceTransactionHelper;
        public final TaskInfo mTaskInfo;
        public int mTransitionDirection;

        public PipTransitionAnimator(TaskInfo taskInfo, SurfaceControl surfaceControl, int i, Rect rect, Object obj, Object obj2, Object obj3) {
            Rect rect2 = new Rect();
            this.mDestinationBounds = rect2;
            this.mTaskInfo = taskInfo;
            this.mLeash = surfaceControl;
            this.mAnimationType = i;
            rect2.set(rect);
            this.mBaseValue = obj;
            this.mStartValue = obj2;
            this.mEndValue = obj3;
            addListener(this);
            addUpdateListener(this);
            this.mSurfaceControlTransactionFactory = new PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory();
            this.mTransitionDirection = 0;
        }

        /* JADX WARN: Type inference failed for: r21v0, types: [com.android.wm.shell.pip.PipAnimationController$PipTransitionAnimator$2] */
        public static AnonymousClass2 ofBounds(TaskInfo taskInfo, SurfaceControl surfaceControl, Rect rect, Rect rect2, final Rect rect3, Rect rect4, int i, final float f, final int i2) {
            Rect rect5;
            final Rect rect6;
            Rect rect7;
            final boolean isOutPipDirection = PipAnimationController.isOutPipDirection(i);
            final boolean isInPipDirection = PipAnimationController.isInPipDirection(i);
            final Rect rect8 = isOutPipDirection ? new Rect(rect3) : new Rect(rect);
            if (i2 == 1 || i2 == 3) {
                Rect rect9 = new Rect(rect3);
                Rect rect10 = new Rect(rect3);
                RotationUtils.rotateBounds(rect10, rect8, i2);
                rect5 = rect9;
                rect6 = rect10;
                rect7 = isOutPipDirection ? rect10 : rect8;
            } else {
                rect6 = null;
                rect5 = null;
                rect7 = rect8;
            }
            final Rect rect11 = new Rect();
            if (rect4 != null && !rect4.isEmpty()) {
                rect11.set(rect4);
                if (PipAnimationController.isInPipDirection(i) && i2 == 0) {
                    Rect rect12 = taskInfo.displayCutoutInsets;
                    if (rect12 != null) {
                        rect11.offset(rect12.left, rect12.top);
                    }
                }
            } else if (PipAnimationController.isInPipDirection(i)) {
                rect11.set(PipUtils.getEnterPipWithOverlaySrcRectHint(rect2, rect3.width() / rect3.height()));
            }
            final Rect rect13 = new Rect();
            if (!rect11.isEmpty()) {
                rect13.set(rect11.left - rect7.left, rect11.top - rect7.top, rect7.right - rect11.right, rect7.bottom - rect11.bottom);
            }
            final Rect rect14 = new Rect(0, 0, 0, 0);
            final Rect rect15 = rect7;
            final Rect rect16 = rect5;
            return new PipTransitionAnimator(taskInfo, surfaceControl, rect3, new Rect(rect), new Rect(rect2), new Rect(rect3)) { // from class: com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator.2
                public final RectEvaluator mRectEvaluator = new RectEvaluator(new Rect());
                public final RectEvaluator mInsetsEvaluator = new RectEvaluator(new Rect());

                /* JADX WARN: Code restructure failed: missing block: B:45:0x0146, code lost:
                
                    if (com.android.wm.shell.pip.PipAnimationController.isOutPipDirection(r2) == false) goto L49;
                 */
                /* JADX WARN: Removed duplicated region for block: B:52:0x0270  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x027a  */
                /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:57:0x0277  */
                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void applySurfaceControlTransaction(android.view.SurfaceControl r19, android.view.SurfaceControl.Transaction r20, float r21) {
                    /*
                        Method dump skipped, instructions count: 638
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator.AnonymousClass2.applySurfaceControlTransaction(android.view.SurfaceControl, android.view.SurfaceControl$Transaction, float):void");
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void onEndTransaction(int i3, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl2) {
                    Rect rect17 = this.mDestinationBounds;
                    this.mSurfaceTransactionHelper.resetScale(rect17, transaction, surfaceControl2);
                    if (PipAnimationController.isOutPipDirection(i3)) {
                        transaction.setMatrix(surfaceControl2, 1.0f, 0.0f, 0.0f, 1.0f);
                        transaction.setPosition(surfaceControl2, 0.0f, 0.0f);
                        transaction.setWindowCrop(surfaceControl2, 0, 0);
                    } else {
                        this.mSurfaceTransactionHelper.crop(rect17, transaction, surfaceControl2);
                    }
                    if (this.mContentOverlay != null) {
                        this.mContentOverlay = null;
                    }
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void onStartTransaction(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl2) {
                    PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
                    pipSurfaceTransactionHelper.getClass();
                    transaction.setAlpha(surfaceControl2, 1.0f);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl2, true);
                    pipSurfaceTransactionHelper.shadow(transaction, surfaceControl2, shouldApplyShadowRadius());
                    transaction.show(surfaceControl2);
                    transaction.apply();
                }

                @Override // com.android.wm.shell.pip.PipAnimationController.PipTransitionAnimator
                public final void updateEndValue(Object obj) {
                    Object obj2;
                    this.mEndValue = (Rect) obj;
                    Object obj3 = this.mStartValue;
                    if (obj3 == null || (obj2 = this.mCurrentValue) == null) {
                        return;
                    }
                    ((Rect) obj3).set((Rect) obj2);
                }
            };
        }

        public abstract void applySurfaceControlTransaction(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, float f);

        public int getAnimationType() {
            return this.mAnimationType;
        }

        public Object getEndValue() {
            return this.mEndValue;
        }

        public int getTransitionDirection() {
            return this.mTransitionDirection;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            PipAnimationCallback pipAnimationCallback = this.mPipAnimationCallback;
            if (pipAnimationCallback != null) {
                pipAnimationCallback.onPipAnimationCancel(this);
            }
            this.mTransitionDirection = 0;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.mHasRequestedEnd) {
                return;
            }
            this.mHasRequestedEnd = true;
            this.mCurrentValue = this.mEndValue;
            SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            onEndTransaction(this.mTransitionDirection, transaction, this.mLeash);
            PipAnimationCallback pipAnimationCallback = this.mPipAnimationCallback;
            if (pipAnimationCallback != null) {
                pipAnimationCallback.onPipAnimationEnd(this.mTaskInfo, transaction, this);
            }
            this.mTransitionDirection = 0;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.mCurrentValue = this.mStartValue;
            onStartTransaction(((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction(), this.mLeash);
            PipAnimationCallback pipAnimationCallback = this.mPipAnimationCallback;
            if (pipAnimationCallback != null) {
                pipAnimationCallback.onPipAnimationStart(this);
            }
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.mHasRequestedEnd) {
                return;
            }
            applySurfaceControlTransaction(this.mLeash, ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction(), valueAnimator.getAnimatedFraction());
        }

        public abstract void onStartTransaction(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl);

        public final void reattachContentOverlay(PipContentOverlay pipContentOverlay) {
            SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            PipContentOverlay pipContentOverlay2 = this.mContentOverlay;
            if (pipContentOverlay2 != null) {
                pipContentOverlay2.detach(transaction);
            }
            this.mContentOverlay = pipContentOverlay;
            pipContentOverlay.attach(transaction, this.mLeash);
        }

        public final void setDestinationBounds(Rect rect) {
            this.mDestinationBounds.set(rect);
            if (this.mAnimationType == 1) {
                onStartTransaction(((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction(), this.mLeash);
            }
        }

        public PipTransitionAnimator setPipAnimationCallback(PipAnimationCallback pipAnimationCallback) {
            this.mPipAnimationCallback = pipAnimationCallback;
            return this;
        }

        public void setSurfaceControlTransactionFactory(PipSurfaceTransactionHelper.SurfaceControlTransactionFactory surfaceControlTransactionFactory) {
            this.mSurfaceControlTransactionFactory = surfaceControlTransactionFactory;
        }

        public PipTransitionAnimator setTransitionDirection(int i) {
            if (i != 1) {
                this.mTransitionDirection = i;
            }
            return this;
        }

        public final boolean shouldApplyShadowRadius() {
            return !(this.mTransitionDirection == 5);
        }

        public abstract void updateEndValue(Object obj);

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        public void onEndTransaction(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        }
    }
}
