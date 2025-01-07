package com.android.wm.shell.freeform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FreeformTaskTransitionHandler implements Transitions.TransitionHandler {
    public final ShellExecutor mAnimExecutor;
    public final Context mContext;
    public final DesktopModeTaskRepository mDesktopModeTaskRepository;
    public final DisplayController mDisplayController;
    public final Handler mHandler;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final ShellExecutor mMainExecutor;
    public final Transitions mTransitions;
    public final WindowDecorViewModel mWindowDecorViewModel;
    public final List mPendingTransitionTokens = new ArrayList();
    public final ArrayMap mAnimations = new ArrayMap();

    public FreeformTaskTransitionHandler(ShellInit shellInit, Transitions transitions, Context context, WindowDecorViewModel windowDecorViewModel, DisplayController displayController, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, DesktopModeTaskRepository desktopModeTaskRepository, InteractionJankMonitor interactionJankMonitor, Handler handler) {
        this.mTransitions = transitions;
        this.mContext = context;
        this.mWindowDecorViewModel = windowDecorViewModel;
        this.mDesktopModeTaskRepository = desktopModeTaskRepository;
        this.mDisplayController = displayController;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mHandler = handler;
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            shellInit.addInitCallback(new FreeformTaskTransitionHandler$$ExternalSyntheticLambda1(2, this), this);
        }
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
        ((HandlerExecutor) this.mAnimExecutor).execute(new FreeformTaskTransitionHandler$$ExternalSyntheticLambda1(0, arrayList));
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        int i;
        boolean z;
        boolean z2;
        boolean z3;
        int i2 = 2;
        int i3 = 1;
        final ArrayList arrayList = new ArrayList();
        final FreeformTaskTransitionHandler$$ExternalSyntheticLambda2 freeformTaskTransitionHandler$$ExternalSyntheticLambda2 = new FreeformTaskTransitionHandler$$ExternalSyntheticLambda2(this, arrayList, iBinder, transitionFinishCallback);
        boolean z4 = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if ((change.getFlags() & i2) == 0 && (taskInfo = change.getTaskInfo()) != null && taskInfo.taskId != -1) {
                int mode = change.getMode();
                if (mode != i2) {
                    if (mode == 4) {
                        int type = transitionInfo.getType();
                        if (this.mPendingTransitionTokens.contains(iBinder)) {
                            change.getTaskInfo();
                            if (type == 1020) {
                                z2 = true;
                                z4 |= z2;
                            }
                        }
                        z2 = false;
                        z4 |= z2;
                    } else if (mode == 6) {
                        int type2 = transitionInfo.getType();
                        if (this.mPendingTransitionTokens.contains(iBinder)) {
                            ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
                            z3 = type2 == 1008 && taskInfo2.getWindowingMode() == 1;
                            if (type2 == 1009 && taskInfo2.getWindowingMode() == 5) {
                                z3 = true;
                            }
                        } else {
                            z3 = false;
                        }
                        z4 |= z3;
                    }
                } else if (change.getTaskInfo().getWindowingMode() == 5) {
                    if (this.mPendingTransitionTokens.contains(iBinder)) {
                        final int i4 = this.mDisplayController.getDisplayLayout(change.getTaskInfo().displayId).mHeight;
                        final ValueAnimator valueAnimator = new ValueAnimator();
                        float[] fArr = new float[i2];
                        // fill-array-data instruction
                        fArr[0] = 0.0f;
                        fArr[1] = 1.0f;
                        valueAnimator.setDuration(400L).setFloatValues(fArr);
                        final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
                        final SurfaceControl leash = change.getLeash();
                        transaction2.hide(leash);
                        final Rect rect = new Rect(change.getTaskInfo().configuration.windowConfiguration.getBounds());
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.freeform.FreeformTaskTransitionHandler$$ExternalSyntheticLambda4
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                                SurfaceControl.Transaction transaction4 = transaction3;
                                SurfaceControl surfaceControl = leash;
                                Rect rect2 = rect;
                                transaction4.setPosition(surfaceControl, rect2.left, (valueAnimator2.getAnimatedFraction() * i4) + rect2.top);
                                transaction4.apply();
                            }
                        });
                        int i5 = change.getTaskInfo().displayId;
                        DesktopModeTaskRepository desktopModeTaskRepository = this.mDesktopModeTaskRepository;
                        desktopModeTaskRepository.getClass();
                        DesktopModeTaskRepository.DesktopTaskData desktopTaskData = (DesktopModeTaskRepository.DesktopTaskData) desktopModeTaskRepository.desktopTaskDataByDisplayId.get(i5);
                        ArraySet arraySet = new ArraySet(desktopTaskData != null ? desktopTaskData.activeTasks : null);
                        if (arraySet.isEmpty()) {
                            i = 0;
                        } else {
                            Iterator it = arraySet.iterator();
                            i = 0;
                            while (it.hasNext()) {
                                Integer num = (Integer) it.next();
                                Intrinsics.checkNotNull(num);
                                if (!desktopModeTaskRepository.isMinimizedTask(num.intValue()) && (i = i + 1) < 0) {
                                    CollectionsKt__CollectionsKt.throwCountOverflow();
                                    throw null;
                                }
                            }
                        }
                        if (i == 1) {
                            this.mInteractionJankMonitor.begin(leash, this.mContext, this.mHandler, 117);
                        }
                        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.freeform.FreeformTaskTransitionHandler.1
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                arrayList.remove(valueAnimator);
                                freeformTaskTransitionHandler$$ExternalSyntheticLambda2.run();
                                FreeformTaskTransitionHandler.this.mInteractionJankMonitor.end(117);
                            }
                        });
                        arrayList.add(valueAnimator);
                        z = true;
                    } else {
                        z = false;
                    }
                    z4 = z | z4;
                    i2 = 2;
                }
            }
            i2 = 2;
        }
        if (!z4) {
            return false;
        }
        this.mAnimations.put(iBinder, arrayList);
        transaction.apply();
        ((HandlerExecutor) this.mAnimExecutor).execute(new FreeformTaskTransitionHandler$$ExternalSyntheticLambda1(i3, arrayList));
        freeformTaskTransitionHandler$$ExternalSyntheticLambda2.run();
        this.mPendingTransitionTokens.remove(iBinder);
        return true;
    }
}
