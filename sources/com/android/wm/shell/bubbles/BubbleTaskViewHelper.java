package com.android.wm.shell.bubbles;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Rect;
import android.util.Log;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleTaskViewHelper;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import com.android.wm.shell.taskview.TaskView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleTaskViewHelper {
    public Bubble mBubble;
    public final Context mContext;
    public final BubbleExpandedViewManager mExpandedViewManager;
    public final BubbleBarExpandedView mListener;
    public final BubbleBarExpandedView mParentView;
    public PendingIntent mPendingIntent;
    public int mTaskId;
    public TaskView mTaskView;
    public final AnonymousClass1 mTaskViewListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleTaskViewHelper$1, reason: invalid class name */
    public final class AnonymousClass1 implements TaskView.Listener {
        public boolean mInitialized = false;
        public boolean mDestroyed = false;

        public AnonymousClass1() {
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onBackPressedOnTaskRoot(int i) {
            BubbleBarLayerView.AnonymousClass2 anonymousClass2;
            BubbleTaskViewHelper bubbleTaskViewHelper = BubbleTaskViewHelper.this;
            if (bubbleTaskViewHelper.mTaskId == i && ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleTaskViewHelper.mExpandedViewManager).$controller.mBubbleData.mExpanded && (anonymousClass2 = bubbleTaskViewHelper.mListener.mListener) != null) {
                BubbleBarLayerView.this.hideModalOrCollapse();
            }
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onInitialized() {
            boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleTaskViewHelper bubbleTaskViewHelper = BubbleTaskViewHelper.this;
            if (z) {
                boolean z2 = this.mDestroyed;
                boolean z3 = this.mInitialized;
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 170374694659175905L, 15, Boolean.valueOf(z2), Boolean.valueOf(z3), String.valueOf(bubbleTaskViewHelper.getBubbleKey()));
            }
            if (this.mDestroyed || this.mInitialized) {
                return;
            }
            final ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(bubbleTaskViewHelper.mContext, 0, 0);
            final Rect rect = new Rect();
            bubbleTaskViewHelper.mTaskView.getBoundsOnScreen(rect);
            bubbleTaskViewHelper.mParentView.post(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleTaskViewHelper$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z4;
                    BubbleTaskViewHelper.AnonymousClass1 anonymousClass1 = BubbleTaskViewHelper.AnonymousClass1.this;
                    ActivityOptions activityOptions = makeCustomAnimation;
                    Rect rect2 = rect;
                    anonymousClass1.getClass();
                    boolean z5 = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
                    BubbleTaskViewHelper bubbleTaskViewHelper2 = BubbleTaskViewHelper.this;
                    if (z5) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 4519044740839980655L, 0, String.valueOf(bubbleTaskViewHelper2.getBubbleKey()));
                    }
                    try {
                        activityOptions.setTaskAlwaysOnTop(true);
                        activityOptions.setLaunchedFromBubble(true);
                        activityOptions.setPendingIntentBackgroundActivityStartMode(3);
                        Intent intent = new Intent();
                        intent.addFlags(524288);
                        intent.addFlags(134217728);
                        if (bubbleTaskViewHelper2.mBubble.hasMetadataShortcutId()) {
                            z4 = true;
                        } else {
                            ShortcutInfo shortcutInfo = bubbleTaskViewHelper2.mBubble.mShortcutInfo;
                            z4 = false;
                        }
                        Bubble bubble = bubbleTaskViewHelper2.mBubble;
                        if (bubble.mIsAppBubble) {
                            bubbleTaskViewHelper2.mTaskView.startActivity(PendingIntent.getActivity(bubbleTaskViewHelper2.mContext.createContextAsUser(bubble.mUser, 4), 0, bubbleTaskViewHelper2.mBubble.getAppBubbleIntent().addFlags(524288).addFlags(134217728), 201326592, null), null, activityOptions, rect2);
                        } else if (z4) {
                            activityOptions.setApplyActivityFlagsForBubbles(true);
                            bubbleTaskViewHelper2.mTaskView.startShortcutActivity(bubbleTaskViewHelper2.mBubble.mShortcutInfo, activityOptions, rect2);
                        } else {
                            bubble.mIntentActive = true;
                            bubbleTaskViewHelper2.mTaskView.startActivity(bubbleTaskViewHelper2.mPendingIntent, intent, activityOptions, rect2);
                        }
                    } catch (RuntimeException e) {
                        Log.w("BubbleTaskViewHelper", "Exception while displaying bubble: " + bubbleTaskViewHelper2.getBubbleKey() + ", " + e.getMessage() + "; removing bubble");
                        ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleTaskViewHelper2.mExpandedViewManager).$controller.removeBubble(10, bubbleTaskViewHelper2.getBubbleKey());
                    }
                    anonymousClass1.mInitialized = true;
                }
            });
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onReleased() {
            this.mDestroyed = true;
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskCreated(int i, ComponentName componentName) {
            boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleTaskViewHelper bubbleTaskViewHelper = BubbleTaskViewHelper.this;
            if (z) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8730479417503105759L, 1, Long.valueOf(i), String.valueOf(bubbleTaskViewHelper.getBubbleKey()));
            }
            bubbleTaskViewHelper.mTaskId = i;
            Bubble bubble = bubbleTaskViewHelper.mBubble;
            if (bubble != null && bubble.mIsAppBubble) {
                BubbleExpandedViewManager bubbleExpandedViewManager = bubbleTaskViewHelper.mExpandedViewManager;
                String str = bubble.mKey;
                BubbleController.BubblesImpl.CachedState cachedState = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager).$controller.mImpl.mCachedState;
                synchronized (cachedState) {
                    cachedState.mAppBubbleTaskIds.put(str, Integer.valueOf(i));
                }
            }
            bubbleTaskViewHelper.mListener.onTaskCreated();
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskRemovalStarted(int i) {
            boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleTaskViewHelper bubbleTaskViewHelper = BubbleTaskViewHelper.this;
            if (z) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -3861093027035201914L, 1, Long.valueOf(i), String.valueOf(bubbleTaskViewHelper.getBubbleKey()));
            }
            Bubble bubble = bubbleTaskViewHelper.mBubble;
            if (bubble != null) {
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleTaskViewHelper.mExpandedViewManager).$controller.removeBubble(3, bubble.mKey);
            }
            TaskView taskView = bubbleTaskViewHelper.mTaskView;
            if (taskView != null) {
                taskView.getHolder().removeCallback(taskView);
                taskView.mTaskViewTaskController.performRelease();
                bubbleTaskViewHelper.mParentView.removeView(bubbleTaskViewHelper.mTaskView);
                bubbleTaskViewHelper.mTaskView = null;
            }
            RegionSamplingHelper regionSamplingHelper = bubbleTaskViewHelper.mListener.mRegionSamplingHelper;
            if (regionSamplingHelper != null) {
                regionSamplingHelper.stopAndDestroy();
            }
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskVisibilityChanged(int i, boolean z) {
            BubbleTaskViewHelper.this.mListener.setContentVisibility(z);
        }
    }

    public BubbleTaskViewHelper(Context context, BubbleExpandedViewManager bubbleExpandedViewManager, BubbleBarExpandedView bubbleBarExpandedView, BubbleTaskView bubbleTaskView, BubbleBarExpandedView bubbleBarExpandedView2) {
        this.mTaskId = -1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mContext = context;
        this.mExpandedViewManager = bubbleExpandedViewManager;
        this.mListener = bubbleBarExpandedView;
        this.mParentView = bubbleBarExpandedView2;
        this.mTaskView = bubbleTaskView.taskView;
        bubbleTaskView.delegateListener = anonymousClass1;
        if (bubbleTaskView.isCreated) {
            this.mTaskId = bubbleTaskView.taskId;
            bubbleBarExpandedView.onTaskCreated();
        }
    }

    public final String getBubbleKey() {
        Bubble bubble = this.mBubble;
        if (bubble != null) {
            return bubble.mKey;
        }
        return null;
    }
}
