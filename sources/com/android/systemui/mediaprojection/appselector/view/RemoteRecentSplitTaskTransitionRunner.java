package com.android.systemui.mediaprojection.appselector.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.animation.DecelerateInterpolator;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.RemoteTransitionStub;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCapture;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RemoteRecentSplitTaskTransitionRunner extends RemoteTransitionStub {
    public final int firstTaskId;
    public final Function0 handleResult;
    public final Rect screenBounds;
    public final int secondTaskId;
    public final int[] viewPosition;

    public RemoteRecentSplitTaskTransitionRunner(int i, int i2, int[] iArr, Rect rect, Function0 function0) {
        this.firstTaskId = i;
        this.secondTaskId = i2;
        this.viewPosition = iArr;
        this.screenBounds = rect;
        this.handleResult = function0;
    }

    public final void onTransitionConsumed(IBinder iBinder, boolean z) {
        Log.w("MediaProjectionAppSelectorActivity", "unexpected consumption of app selector transition: aborted=" + z);
    }

    public final void startAnimation(IBinder iBinder, TransitionInfo transitionInfo, final SurfaceControl.Transaction transaction, final IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) {
        Object obj;
        ActivityManager.RunningTaskInfo taskInfo;
        final AnimatorSet animatorSet = new AnimatorSet();
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        Intrinsics.checkNotNull(transitionInfo);
        Iterator it = transitionInfo.getChanges().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            TransitionInfo.Change change = (TransitionInfo.Change) obj;
            ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
            if ((taskInfo2 != null && taskInfo2.taskId == this.firstTaskId) || ((taskInfo = change.getTaskInfo()) != null && taskInfo.taskId == this.secondTaskId)) {
                break;
            }
        }
        ref$ObjectRef.element = obj;
        if (obj == null) {
            throw new IllegalStateException("Could not find a split root candidate");
        }
        WindowContainerToken parent = ((TransitionInfo.Change) obj).getParent();
        while (parent != null) {
            TransitionInfo.Change change2 = transitionInfo.getChange(parent);
            if (change2 == null) {
                break;
            }
            ref$ObjectRef.element = change2;
            parent = change2.getParent();
        }
        if (ref$ObjectRef.element == null) {
            throw new IllegalStateException("Failed to find a root leash");
        }
        int[] iArr = this.viewPosition;
        final int i = iArr[0];
        final int i2 = iArr[1];
        Rect rect = this.screenBounds;
        final int i3 = rect.left;
        final int i4 = rect.top;
        ViewCapture.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.android.systemui.mediaprojection.appselector.view.RemoteRecentSplitTaskTransitionRunner$startAnimation$3
            @Override // java.lang.Runnable
            public final void run() {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                final int i5 = i;
                final int i6 = i3;
                final int i7 = i2;
                final int i8 = i4;
                final SurfaceControl.Transaction transaction2 = transaction;
                final Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                final RemoteRecentSplitTaskTransitionRunner remoteRecentSplitTaskTransitionRunner = this;
                final IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback2 = iRemoteTransitionFinishedCallback;
                ofFloat.setInterpolator(new DecelerateInterpolator(1.5f));
                ofFloat.setDuration(336L);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.mediaprojection.appselector.view.RemoteRecentSplitTaskTransitionRunner$startAnimation$3$1$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        float f = ((i6 - r0) * animatedFraction) + i5;
                        float f2 = ((i8 - r1) * animatedFraction) + i7;
                        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1, 0.25f, animatedFraction, 0.25f);
                        SurfaceControl.Transaction transaction3 = transaction2;
                        Intrinsics.checkNotNull(transaction3);
                        transaction3.setPosition(((TransitionInfo.Change) ref$ObjectRef2.element).getLeash(), f, f2).setScale(((TransitionInfo.Change) ref$ObjectRef2.element).getLeash(), m, m).setAlpha(((TransitionInfo.Change) ref$ObjectRef2.element).getLeash(), animatedFraction).apply();
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.mediaprojection.appselector.view.RemoteRecentSplitTaskTransitionRunner$startAnimation$3$1$2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        try {
                            RemoteRecentSplitTaskTransitionRunner.this.handleResult.invoke();
                            iRemoteTransitionFinishedCallback2.onTransitionFinished((WindowContainerTransaction) null, (SurfaceControl.Transaction) null);
                        } catch (RemoteException e) {
                            Log.e("MediaProjectionAppSelectorActivity", "Failed to call transition finished callback", e);
                        }
                    }
                });
                animatorSet.play(ofFloat);
                animatorSet.start();
            }
        });
    }
}
