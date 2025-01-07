package com.android.wm.shell.transition;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.RotationUtils;
import android.view.Choreographer;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.policy.TransitionAnimation;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.splitscreen.SplitScreenTransitions$$ExternalSyntheticLambda0;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultTransitionHandler implements Transitions.TransitionHandler {
    public final ShellExecutor mAnimExecutor;
    public final Context mContext;
    public final DevicePolicyManager mDevicePolicyManager;
    public final DisplayController mDisplayController;
    public Drawable mEnterpriseThumbnailDrawable;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final RootTaskDisplayAreaOrganizer mRootTDAOrganizer;
    public final TransactionPool mTransactionPool;
    public final TransitionAnimation mTransitionAnimation;
    public final ArrayMap mAnimations = new ArrayMap();
    public final CounterRotatorHelper mRotator = new CounterRotatorHelper();
    public final Rect mInsets = new Rect(0, 0, 0, 0);
    public float mTransitionAnimationScaleSetting = 1.0f;
    public final AnonymousClass1 mEnterpriseResourceUpdatedReceiver = new BroadcastReceiver() { // from class: com.android.wm.shell.transition.DefaultTransitionHandler.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("android.app.extra.RESOURCE_TYPE", -1) != 1) {
                return;
            }
            DefaultTransitionHandler defaultTransitionHandler = DefaultTransitionHandler.this;
            defaultTransitionHandler.mEnterpriseThumbnailDrawable = defaultTransitionHandler.mDevicePolicyManager.getResources().getDrawable("WORK_PROFILE_ICON", "OUTLINE", "PROFILE_SWITCH_ANIMATION", new DefaultTransitionHandler$$ExternalSyntheticLambda2(defaultTransitionHandler));
        }
    };

    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.wm.shell.transition.DefaultTransitionHandler$1] */
    public DefaultTransitionHandler(Context context, ShellInit shellInit, DisplayController displayController, TransactionPool transactionPool, ShellExecutor shellExecutor, Handler handler, ShellExecutor shellExecutor2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer) {
        this.mDisplayController = displayController;
        this.mTransactionPool = transactionPool;
        this.mContext = context;
        this.mMainHandler = handler;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mTransitionAnimation = new TransitionAnimation(context, false, "ShellTransitions");
        UserHandle.myUserId();
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        shellInit.addInitCallback(new DefaultTransitionHandler$$ExternalSyntheticLambda1(0, this), this);
        this.mRootTDAOrganizer = rootTaskDisplayAreaOrganizer;
    }

    public static void applyTransformation(long j, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Animation animation, Transformation transformation, float[] fArr, Point point, float f, Rect rect) {
        transformation.clear();
        animation.getTransformation(j, transformation);
        if (point != null) {
            transformation.getMatrix().postTranslate(point.x, point.y);
        }
        transaction.setMatrix(surfaceControl, transformation.getMatrix(), fArr);
        transaction.setAlpha(surfaceControl, transformation.getAlpha());
        Rect rect2 = rect == null ? null : new Rect(rect);
        Insets insets = transformation.getInsets();
        Insets insets2 = Insets.NONE;
        Insets min = Insets.min(insets, insets2);
        if (!min.equals(insets2) && rect2 != null && !rect2.isEmpty()) {
            rect2.inset(min);
            transaction.setCrop(surfaceControl, rect2);
        }
        if (animation.hasRoundedCorners() && f > 0.0f && rect2 != null) {
            transaction.setCrop(surfaceControl, rect2);
            transaction.setCornerRadius(surfaceControl, f);
        }
        transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        transaction.apply();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [android.animation.ValueAnimator$AnimatorUpdateListener, com.android.wm.shell.transition.DefaultTransitionHandler$$ExternalSyntheticLambda8] */
    public static void buildSurfaceAnimation(ArrayList arrayList, final Animation animation, final SurfaceControl surfaceControl, Runnable runnable, TransactionPool transactionPool, ShellExecutor shellExecutor, final Point point, final float f, final Rect rect, final boolean z) {
        final SurfaceControl.Transaction acquire = transactionPool.acquire();
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final Transformation transformation = new Transformation();
        final float[] fArr = new float[9];
        ofFloat.overrideDurationScale(1.0f);
        ofFloat.setDuration(animation.computeDurationHint());
        final ?? r14 = new ValueAnimator.AnimatorUpdateListener(ofFloat, acquire, surfaceControl, animation, transformation, fArr, point, f, rect, z) { // from class: com.android.wm.shell.transition.DefaultTransitionHandler$$ExternalSyntheticLambda8
            public final /* synthetic */ ValueAnimator f$0;
            public final /* synthetic */ SurfaceControl.Transaction f$1;
            public final /* synthetic */ SurfaceControl f$2;
            public final /* synthetic */ Animation f$3;
            public final /* synthetic */ Transformation f$4;
            public final /* synthetic */ float[] f$5;
            public final /* synthetic */ Point f$6;
            public final /* synthetic */ float f$7;
            public final /* synthetic */ Rect f$8;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator valueAnimator2 = this.f$0;
                DefaultTransitionHandler.applyTransformation(Math.min(valueAnimator2.getDuration(), valueAnimator2.getCurrentPlayTime()), this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
            }
        };
        ofFloat.addUpdateListener(r14);
        final DefaultTransitionHandler$$ExternalSyntheticLambda9 defaultTransitionHandler$$ExternalSyntheticLambda9 = new DefaultTransitionHandler$$ExternalSyntheticLambda9(ofFloat, acquire, surfaceControl, animation, transformation, fArr, point, f, rect, z, transactionPool, shellExecutor, arrayList, runnable);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.transition.DefaultTransitionHandler.2
            public boolean mFinished = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                if (this.mFinished) {
                    return;
                }
                this.mFinished = true;
                DefaultTransitionHandler$$ExternalSyntheticLambda9.this.run();
                ofFloat.removeUpdateListener(r14);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (this.mFinished) {
                    return;
                }
                this.mFinished = true;
                DefaultTransitionHandler$$ExternalSyntheticLambda9.this.run();
                ofFloat.removeUpdateListener(r14);
            }
        });
        arrayList.add(ofFloat);
    }

    public static int getRotationAnimationHint(TransitionInfo.Change change, TransitionInfo transitionInfo, DisplayController displayController) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -7541648108389319031L, 0, null);
        }
        if (change.getRotationAnimation() == 3) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -318194249311505831L, 0, null);
            }
            return 3;
        }
        int size = transitionInfo.getChanges().size();
        ActivityManager.RunningTaskInfo runningTaskInfo = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change2.getMode() == 6 && change2.getEndRotation() != change2.getStartRotation()) {
                if ((change2.getFlags() & 32) != 0) {
                    if ((change2.getFlags() & 128) != 0) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7261468683300714502L, 0, null);
                        }
                        z2 = true;
                    }
                } else if ((2 & change2.getFlags()) != 0) {
                    if (change2.getRotationAnimation() != 3) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5449828478871288392L, 0, null);
                        }
                        z2 = true;
                    }
                } else if (change2.getTaskInfo() != null) {
                    int rotationAnimation = change2.getRotationAnimation();
                    ActivityManager.RunningTaskInfo taskInfo = change2.getTaskInfo();
                    boolean z3 = runningTaskInfo == null;
                    if (z3) {
                        if (rotationAnimation != -1 && rotationAnimation != 3) {
                            i2 = rotationAnimation;
                        }
                        runningTaskInfo = taskInfo;
                    }
                    if (rotationAnimation != 3) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 6117639397286231122L, 0, String.valueOf(taskInfo.taskId));
                        }
                        z = false;
                    } else if (z3) {
                        z = true;
                    }
                }
            }
            i++;
        }
        if (z && !z2) {
            DisplayLayout displayLayout = displayController.getDisplayLayout(runningTaskInfo.displayId);
            if (displayLayout.mAllowSeamlessRotationDespiteNavBarMoving) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 6706234276054184697L, 0, null);
                }
                return 3;
            }
            boolean z4 = displayLayout.mWidth > displayLayout.mHeight;
            if (displayLayout.mRotation % 2 != 0) {
                z4 = !z4;
            }
            int i3 = z4 ? displayLayout.mReverseDefaultRotation ? 3 : 1 : 2;
            if (change.getStartRotation() != i3 && change.getEndRotation() != i3) {
                if (displayLayout.mNavigationBarCanMove && displayLayout.mWidth != displayLayout.mHeight) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -2751141420127473180L, 0, null);
                    }
                    return 3;
                }
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 2597835766514531195L, 0, null);
                }
                return i2;
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 8225236208583391558L, 0, null);
            }
        }
        return i2;
    }

    public final void attachThumbnailAnimation(ArrayList arrayList, DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda7, TransitionInfo.Change change, TransitionInfo.AnimationOptions animationOptions, float f) {
        SurfaceControl.Transaction acquire = this.mTransactionPool.acquire();
        WindowThumbnail createAndAttach = WindowThumbnail.createAndAttach(change.getLeash(), animationOptions.getThumbnail(), acquire);
        Animation createThumbnailAspectScaleAnimationLocked = this.mTransitionAnimation.createThumbnailAspectScaleAnimationLocked(change.getEndAbsBounds(), this.mInsets, animationOptions.getThumbnail(), this.mContext.getResources().getConfiguration().orientation, (Rect) null, animationOptions.getTransitionBounds(), animationOptions.getType() == 3);
        DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda72 = new DefaultTransitionHandler$$ExternalSyntheticLambda7(this, createAndAttach, acquire, defaultTransitionHandler$$ExternalSyntheticLambda7, 0);
        createThumbnailAspectScaleAnimationLocked.restrictDuration(3000L);
        createThumbnailAspectScaleAnimationLocked.scaleCurrentDuration(this.mTransitionAnimationScaleSetting);
        buildSurfaceAnimation(arrayList, createThumbnailAspectScaleAnimationLocked, createAndAttach.mSurfaceControl, defaultTransitionHandler$$ExternalSyntheticLambda72, this.mTransactionPool, this.mMainExecutor, change.getEndRelOffset(), f, change.getEndAbsBounds(), change.getActivityComponent() != null);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        ArrayList arrayList = (ArrayList) this.mAnimations.get(iBinder2);
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Animator animator = (Animator) arrayList.get(size);
            Objects.requireNonNull(animator);
            ((HandlerExecutor) this.mAnimExecutor).execute(new SplitScreenTransitions$$ExternalSyntheticLambda0(3, animator));
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void setAnimScaleSetting(float f) {
        this.mTransitionAnimationScaleSetting = f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:138:0x031f, code lost:
    
        if (r5.hasFlags(1024) != false) goto L169;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0623  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0695  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0715 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x06b5  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0367  */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r41, android.window.TransitionInfo r42, android.view.SurfaceControl.Transaction r43, android.view.SurfaceControl.Transaction r44, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r45) {
        /*
            Method dump skipped, instructions count: 2456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.DefaultTransitionHandler.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    public final void startRotationAnimation(SurfaceControl.Transaction transaction, TransitionInfo.Change change, TransitionInfo transitionInfo, int i, final ArrayList arrayList, final DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda7) {
        ArrayList arrayList2;
        int rootIndexFor = TransitionUtil.rootIndexFor(change, transitionInfo);
        Context context = this.mContext;
        SurfaceControl leash = transitionInfo.getRoot(rootIndexFor).getLeash();
        TransactionPool transactionPool = this.mTransactionPool;
        final ScreenRotationAnimation screenRotationAnimation = new ScreenRotationAnimation(context, transactionPool, transaction, change, leash, i);
        final ArrayList arrayList3 = new ArrayList(3);
        final ArrayList arrayList4 = new ArrayList(3);
        Runnable runnable = new Runnable() { // from class: com.android.wm.shell.transition.DefaultTransitionHandler$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                ArrayList arrayList5 = arrayList3;
                ScreenRotationAnimation screenRotationAnimation2 = screenRotationAnimation;
                ArrayList arrayList6 = arrayList;
                ArrayList arrayList7 = arrayList4;
                DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda72 = defaultTransitionHandler$$ExternalSyntheticLambda7;
                if (arrayList5.isEmpty()) {
                    TransactionPool transactionPool2 = screenRotationAnimation2.mTransactionPool;
                    SurfaceControl.Transaction acquire = transactionPool2.acquire();
                    if (screenRotationAnimation2.mAnimLeash.isValid()) {
                        acquire.remove(screenRotationAnimation2.mAnimLeash);
                    }
                    SurfaceControl surfaceControl = screenRotationAnimation2.mScreenshotLayer;
                    if (surfaceControl != null && surfaceControl.isValid()) {
                        acquire.remove(screenRotationAnimation2.mScreenshotLayer);
                    }
                    SurfaceControl surfaceControl2 = screenRotationAnimation2.mBackColorSurface;
                    if (surfaceControl2 != null && surfaceControl2.isValid()) {
                        acquire.remove(screenRotationAnimation2.mBackColorSurface);
                    }
                    acquire.apply();
                    transactionPool2.release(acquire);
                    arrayList6.removeAll(arrayList7);
                    defaultTransitionHandler$$ExternalSyntheticLambda72.run();
                }
            }
        };
        float f = this.mTransitionAnimationScaleSetting;
        if (screenRotationAnimation.mScreenshotLayer == null) {
            arrayList2 = arrayList4;
        } else {
            boolean z = i == 1 || i == 2;
            if (z) {
                screenRotationAnimation.mRotateExitAnimation = AnimationUtils.loadAnimation(context, i == 2 ? R.anim.screen_rotate_alpha : R.anim.screen_rotate_finish_enter);
                screenRotationAnimation.mRotateEnterAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_180_frame);
                screenRotationAnimation.mRotateAlphaAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_start_exit);
            } else {
                int deltaRotation = RotationUtils.deltaRotation(screenRotationAnimation.mEndRotation, screenRotationAnimation.mStartRotation);
                if (deltaRotation == 0) {
                    screenRotationAnimation.mRotateExitAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_minus_90_exit);
                    screenRotationAnimation.mRotateEnterAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_180_frame);
                } else if (deltaRotation == 1) {
                    screenRotationAnimation.mRotateExitAnimation = AnimationUtils.loadAnimation(context, R.anim.seekbar_thumb_unpressed_to_pressed_thumb_0_animation);
                    screenRotationAnimation.mRotateEnterAnimation = AnimationUtils.loadAnimation(context, R.anim.seekbar_thumb_pressed_to_unpressed_thumb_animation);
                } else if (deltaRotation == 2) {
                    screenRotationAnimation.mRotateExitAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_plus_90_exit);
                    screenRotationAnimation.mRotateEnterAnimation = AnimationUtils.loadAnimation(context, R.anim.screen_rotate_plus_90_enter);
                } else if (deltaRotation == 3) {
                    screenRotationAnimation.mRotateExitAnimation = AnimationUtils.loadAnimation(context, R.anim.search_bar_exit);
                    screenRotationAnimation.mRotateEnterAnimation = AnimationUtils.loadAnimation(context, R.anim.search_bar_enter);
                }
            }
            Animation animation = screenRotationAnimation.mRotateExitAnimation;
            int i2 = screenRotationAnimation.mEndWidth;
            int i3 = screenRotationAnimation.mEndHeight;
            int i4 = screenRotationAnimation.mStartWidth;
            int i5 = screenRotationAnimation.mStartHeight;
            animation.initialize(i2, i3, i4, i5);
            screenRotationAnimation.mRotateExitAnimation.restrictDuration(10000L);
            screenRotationAnimation.mRotateExitAnimation.scaleCurrentDuration(f);
            screenRotationAnimation.mRotateEnterAnimation.initialize(i2, i3, i4, i5);
            screenRotationAnimation.mRotateEnterAnimation.restrictDuration(10000L);
            screenRotationAnimation.mRotateEnterAnimation.scaleCurrentDuration(f);
            ShellExecutor shellExecutor = this.mMainExecutor;
            if (z) {
                screenRotationAnimation.mRotateAlphaAnimation.initialize(i2, i3, i4, i5);
                screenRotationAnimation.mRotateAlphaAnimation.restrictDuration(10000L);
                screenRotationAnimation.mRotateAlphaAnimation.scaleCurrentDuration(f);
                arrayList2 = arrayList4;
                buildSurfaceAnimation(arrayList3, screenRotationAnimation.mRotateAlphaAnimation, screenRotationAnimation.mAnimLeash, runnable, transactionPool, shellExecutor, null, 0.0f, null, false);
                buildSurfaceAnimation(arrayList3, screenRotationAnimation.mRotateEnterAnimation, screenRotationAnimation.mSurfaceControl, runnable, transactionPool, shellExecutor, null, 0.0f, null, false);
            } else {
                arrayList2 = arrayList4;
                buildSurfaceAnimation(arrayList3, screenRotationAnimation.mRotateEnterAnimation, screenRotationAnimation.mSurfaceControl, runnable, transactionPool, shellExecutor, null, 0.0f, null, false);
                buildSurfaceAnimation(arrayList3, screenRotationAnimation.mRotateExitAnimation, screenRotationAnimation.mAnimLeash, runnable, transactionPool, shellExecutor, null, 0.0f, null, false);
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            Animator animator = (Animator) arrayList3.get(size);
            arrayList2.add(animator);
            arrayList.add(animator);
        }
    }
}
