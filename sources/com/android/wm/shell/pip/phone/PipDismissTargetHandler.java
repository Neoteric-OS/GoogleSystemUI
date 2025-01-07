package com.android.wm.shell.pip.phone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.android.wm.shell.R;
import com.android.wm.shell.animation.FloatProperties;
import com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1;
import com.android.wm.shell.bubbles.DismissViewUtils;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.pip.phone.PipMotionHelper;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$MagneticTarget$updateLocationOnScreen$1;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipDismissTargetHandler implements ViewTreeObserver.OnPreDrawListener {
    public final Context mContext;
    public int mDismissAreaHeight;
    public boolean mEnableDismissDragToEdge;
    public float mMagneticFieldRadiusPercent = 1.0f;
    public MagnetizedObject.MagneticTarget mMagneticTarget;
    public PipMotionHelper.AnonymousClass2 mMagnetizedPip;
    public final ShellExecutor mMainExecutor;
    public final PipMotionHelper mMotionHelper;
    public final PipUiEventLogger mPipUiEventLogger;
    public int mTargetSize;
    public DismissCircleView mTargetView;
    public DismissView mTargetViewContainer;
    public SurfaceControl mTaskLeash;
    public WindowInsets mWindowInsets;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.phone.PipDismissTargetHandler$1, reason: invalid class name */
    public final class AnonymousClass1 implements MagnetizedObject.MagnetListener {
        public AnonymousClass1() {
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onReleasedInTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            PipDismissTargetHandler pipDismissTargetHandler = PipDismissTargetHandler.this;
            if (pipDismissTargetHandler.mEnableDismissDragToEdge) {
                ((HandlerExecutor) pipDismissTargetHandler.mMainExecutor).executeDelayed(new Runnable() { // from class: com.android.wm.shell.pip.phone.PipDismissTargetHandler$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PipDismissTargetHandler pipDismissTargetHandler2 = PipDismissTargetHandler.this;
                        PipMotionHelper pipMotionHelper = pipDismissTargetHandler2.mMotionHelper;
                        pipMotionHelper.mDismissalPending = true;
                        PhysicsAnimator physicsAnimator = pipMotionHelper.mTemporaryBoundsPhysicsAnimator;
                        FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_Y;
                        PipBoundsState pipBoundsState = pipMotionHelper.mPipBoundsState;
                        physicsAnimator.spring(floatProperties$Companion$RECT_X$1, (pipBoundsState.getBounds().height() * 2) + pipBoundsState.mMovementBounds.bottom, 0.0f, pipMotionHelper.mSpringConfig);
                        physicsAnimator.withEndActions(new PipMotionHelper$$ExternalSyntheticLambda0(pipMotionHelper, 0));
                        pipMotionHelper.startBoundsAnimator$1(pipBoundsState.getBounds().left, pipBoundsState.getBounds().height() + pipBoundsState.getBounds().bottom, null);
                        pipMotionHelper.mDismissalPending = false;
                        if (pipDismissTargetHandler2.mEnableDismissDragToEdge) {
                            pipDismissTargetHandler2.mTargetViewContainer.hide();
                        }
                        pipDismissTargetHandler2.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_DRAG_TO_REMOVE);
                    }
                }, 0L);
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onStuckToTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            PipDismissTargetHandler pipDismissTargetHandler = PipDismissTargetHandler.this;
            if (pipDismissTargetHandler.mEnableDismissDragToEdge) {
                pipDismissTargetHandler.showDismissTargetMaybe();
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onUnstuckFromTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject, float f, float f2, boolean z) {
            PipDismissTargetHandler pipDismissTargetHandler = PipDismissTargetHandler.this;
            if (!z) {
                pipDismissTargetHandler.mMotionHelper.mSpringingToTouch = true;
                return;
            }
            pipDismissTargetHandler.mMotionHelper.movetoTarget$1(f, f2, null, false);
            if (pipDismissTargetHandler.mEnableDismissDragToEdge) {
                pipDismissTargetHandler.mTargetViewContainer.hide();
            }
        }
    }

    public PipDismissTargetHandler(Context context, PipUiEventLogger pipUiEventLogger, PipMotionHelper pipMotionHelper, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mPipUiEventLogger = pipUiEventLogger;
        this.mMotionHelper = pipMotionHelper;
        this.mMainExecutor = shellExecutor;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    public final void createOrUpdateDismissTarget() {
        if (this.mTargetViewContainer.getParent() != null) {
            this.mWindowManager.updateViewLayout(this.mTargetViewContainer, getDismissTargetLayoutParams());
            return;
        }
        this.mTargetViewContainer.animator.cancel();
        this.mTargetViewContainer.setVisibility(4);
        this.mTargetViewContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        this.mWindowManager.addView(this.mTargetViewContainer, getDismissTargetLayoutParams());
    }

    public final WindowManager.LayoutParams getDismissTargetLayoutParams() {
        Point point = new Point();
        this.mWindowManager.getDefaultDisplay().getRealSize(point);
        int min = Math.min(point.y, this.mDismissAreaHeight);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, min, 0, point.y - min, 2024, 280, -3);
        layoutParams.setTitle("pip-dismiss-overlay");
        layoutParams.privateFlags |= 16;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        return layoutParams;
    }

    public final void init() {
        Resources resources = this.mContext.getResources();
        this.mEnableDismissDragToEdge = resources.getBoolean(R.bool.config_pipEnableDismissDragToEdge);
        this.mDismissAreaHeight = resources.getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height);
        DismissView dismissView = this.mTargetViewContainer;
        if (dismissView != null && dismissView.getParent() != null) {
            this.mWindowManager.removeViewImmediate(this.mTargetViewContainer);
        }
        DismissView dismissView2 = new DismissView(this.mContext);
        this.mTargetViewContainer = dismissView2;
        DismissViewUtils.setup(dismissView2);
        DismissView dismissView3 = this.mTargetViewContainer;
        this.mTargetView = dismissView3.circle;
        dismissView3.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.wm.shell.pip.phone.PipDismissTargetHandler$$ExternalSyntheticLambda0
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                PipDismissTargetHandler pipDismissTargetHandler = PipDismissTargetHandler.this;
                if (!windowInsets.equals(pipDismissTargetHandler.mWindowInsets)) {
                    pipDismissTargetHandler.mWindowInsets = windowInsets;
                    pipDismissTargetHandler.updateMagneticTargetSize();
                }
                return windowInsets;
            }
        });
        PipMotionHelper pipMotionHelper = this.mMotionHelper;
        if (pipMotionHelper.mMagnetizedPip == null) {
            PipMotionHelper.AnonymousClass2 anonymousClass2 = new PipMotionHelper.AnonymousClass2(pipMotionHelper.mContext, pipMotionHelper.mPipBoundsState.mMotionBoundsState.mBoundsInMotion, FloatProperties.RECT_X, FloatProperties.RECT_Y);
            pipMotionHelper.mMagnetizedPip = anonymousClass2;
            anonymousClass2.flingToTargetEnabled = false;
        }
        PipMotionHelper.AnonymousClass2 anonymousClass22 = pipMotionHelper.mMagnetizedPip;
        this.mMagnetizedPip = anonymousClass22;
        anonymousClass22.associatedTargets.clear();
        PipMotionHelper.AnonymousClass2 anonymousClass23 = this.mMagnetizedPip;
        DismissCircleView dismissCircleView = this.mTargetView;
        anonymousClass23.getClass();
        MagnetizedObject.MagneticTarget magneticTarget = new MagnetizedObject.MagneticTarget(dismissCircleView, 0);
        anonymousClass23.associatedTargets.add(magneticTarget);
        dismissCircleView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
        this.mMagneticTarget = magneticTarget;
        updateMagneticTargetSize();
        PipMotionHelper.AnonymousClass2 anonymousClass24 = this.mMagnetizedPip;
        anonymousClass24.animateStuckToTarget = new Function5() { // from class: com.android.wm.shell.pip.phone.PipDismissTargetHandler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                MagnetizedObject.MagneticTarget magneticTarget2 = (MagnetizedObject.MagneticTarget) obj;
                Float f = (Float) obj2;
                Float f2 = (Float) obj3;
                Boolean bool = (Boolean) obj4;
                Function0 function0 = (Function0) obj5;
                PipDismissTargetHandler pipDismissTargetHandler = PipDismissTargetHandler.this;
                if (pipDismissTargetHandler.mEnableDismissDragToEdge) {
                    PipMotionHelper pipMotionHelper2 = pipDismissTargetHandler.mMotionHelper;
                    float floatValue = f.floatValue();
                    float floatValue2 = f2.floatValue();
                    bool.getClass();
                    pipMotionHelper2.getClass();
                    PointF pointF = magneticTarget2.centerOnScreen;
                    float dimensionPixelSize = pipMotionHelper2.mContext.getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
                    PipBoundsState pipBoundsState = pipMotionHelper2.mPipBoundsState;
                    float f3 = dimensionPixelSize * 0.85f;
                    float width = f3 / (pipBoundsState.getBounds().width() / pipBoundsState.getBounds().height());
                    float f4 = pointF.x - (f3 / 2.0f);
                    float f5 = pointF.y - (width / 2.0f);
                    PipBoundsState.MotionBoundsState motionBoundsState = pipBoundsState.mMotionBoundsState;
                    if (!motionBoundsState.isInMotion()) {
                        motionBoundsState.setBoundsInMotion(pipBoundsState.getBounds());
                    }
                    PhysicsAnimator physicsAnimator = pipMotionHelper2.mTemporaryBoundsPhysicsAnimator;
                    FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_X;
                    PhysicsAnimator.SpringConfig springConfig = pipMotionHelper2.mAnimateToDismissSpringConfig;
                    physicsAnimator.spring(floatProperties$Companion$RECT_X$1, f4, floatValue, springConfig);
                    physicsAnimator.spring(FloatProperties.RECT_Y, f5, floatValue2, springConfig);
                    physicsAnimator.spring(FloatProperties.RECT_WIDTH, f3, 0.0f, springConfig);
                    physicsAnimator.spring(FloatProperties.RECT_HEIGHT, width, 0.0f, springConfig);
                    physicsAnimator.withEndActions(function0);
                    pipMotionHelper2.startBoundsAnimator$1(f4, f5, null);
                }
                return Unit.INSTANCE;
            }
        };
        anonymousClass24.magnetListener = new AnonymousClass1();
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        this.mTargetViewContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        if (this.mTaskLeash == null) {
            return true;
        }
        SurfaceControl surfaceControl = this.mTargetViewContainer.getViewRootImpl().getSurfaceControl();
        if (!surfaceControl.isValid()) {
            return true;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        transaction.setRelativeLayer(surfaceControl, this.mTaskLeash, -1);
        transaction.apply();
        return true;
    }

    public final void showDismissTargetMaybe() {
        if (this.mEnableDismissDragToEdge) {
            createOrUpdateDismissTarget();
            if (this.mTargetViewContainer.getVisibility() != 0) {
                this.mTargetViewContainer.getViewTreeObserver().addOnPreDrawListener(this);
            }
            this.mTargetViewContainer.show();
        }
    }

    public final void updateMagneticTargetSize() {
        if (this.mTargetView == null) {
            return;
        }
        DismissView dismissView = this.mTargetViewContainer;
        if (dismissView != null) {
            dismissView.updateResources();
        }
        Resources resources = this.mContext.getResources();
        this.mTargetSize = resources.getDimensionPixelSize(R.dimen.dismiss_circle_size);
        this.mDismissAreaHeight = resources.getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height);
        float f = this.mMagneticFieldRadiusPercent;
        this.mMagneticFieldRadiusPercent = f;
        this.mMagneticTarget.magneticFieldRadiusPx = (int) (f * this.mTargetSize * 1.25f);
    }
}
