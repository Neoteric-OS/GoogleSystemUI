package com.android.wm.shell.bubbles;

import android.app.ActivityManager;
import android.content.LocusId;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.content.pm.UserInfo;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManagerGlobal;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.WindowManagerShellWrapper;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController.BubblesImeListener;
import com.android.wm.shell.bubbles.BubbleController.IBubblesImpl;
import com.android.wm.shell.bubbles.animation.ExpandedAnimationController;
import com.android.wm.shell.bubbles.animation.StackAnimationController;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository$removeBubbles$1$1;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.pip.PinnedStackListenerForwarder;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import kotlin.collections.CollectionsKt__IterablesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController f$0;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda3(BubbleController bubbleController, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final BubbleController bubbleController = this.f$0;
        switch (i) {
            case 0:
                bubbleController.mBubbleViewCallback = bubbleController.isShowingAsBubbleBar() ? bubbleController.mBubbleBarViewCallback : bubbleController.mBubbleStackViewCallback;
                BubbleData bubbleData = bubbleController.mBubbleData;
                bubbleData.mListener = bubbleController.mBubbleDataListener;
                bubbleData.mBubbleMetadataFlagListener = new BubbleController$$ExternalSyntheticLambda7(bubbleController, 0);
                bubbleController.mDataRepository.bubbleMetadataFlagListener = new BubbleController$$ExternalSyntheticLambda7(bubbleController, 0);
                bubbleData.mCancelledListener = new BubbleController$$ExternalSyntheticLambda7(bubbleController, 3);
                boolean z = false;
                try {
                    WindowManagerShellWrapper windowManagerShellWrapper = bubbleController.mWindowManagerShellWrapper;
                    BubbleController.BubblesImeListener bubblesImeListener = bubbleController.new BubblesImeListener();
                    PinnedStackListenerForwarder pinnedStackListenerForwarder = windowManagerShellWrapper.mPinnedStackListenerForwarder;
                    pinnedStackListenerForwarder.mListeners.add(bubblesImeListener);
                    WindowManagerGlobal.getWindowManagerService().registerPinnedTaskListener(0, pinnedStackListenerForwarder.mListenerImpl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                bubbleController.mBubbleData.mCurrentUserId = bubbleController.mCurrentUserId;
                ShellTaskOrganizer shellTaskOrganizer = bubbleController.mTaskOrganizer;
                ShellTaskOrganizer.LocusIdListener locusIdListener = new ShellTaskOrganizer.LocusIdListener() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda9
                    @Override // com.android.wm.shell.ShellTaskOrganizer.LocusIdListener
                    public final void onVisibilityChanged(int i2, LocusId locusId, boolean z2) {
                        Bubble bubble;
                        BubbleData bubbleData2 = BubbleController.this.mBubbleData;
                        bubbleData2.getClass();
                        if (locusId == null) {
                            return;
                        }
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5004922702703375966L, 28, String.valueOf(locusId.getId()), Boolean.valueOf(z2), Long.valueOf(i2));
                        }
                        int i3 = 0;
                        while (true) {
                            if (i3 >= ((ArrayList) bubbleData2.mBubbles).size()) {
                                bubble = null;
                                break;
                            }
                            bubble = (Bubble) ((ArrayList) bubbleData2.mBubbles).get(i3);
                            if (locusId.equals(bubble.mLocusId)) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (!z2 || (bubble != null && bubble.getTaskId() == i2)) {
                            bubbleData2.mVisibleLocusIds.remove(locusId);
                        } else {
                            bubbleData2.mVisibleLocusIds.add(locusId);
                        }
                        if (bubble == null && (bubble = (Bubble) bubbleData2.mSuppressedBubbles.get(locusId)) == null) {
                            return;
                        }
                        boolean z3 = bubbleData2.mSuppressedBubbles.get(locusId) != null;
                        if (z2 && !z3 && (bubble.mFlags & 4) != 0 && i2 != bubble.getTaskId()) {
                            bubbleData2.mSuppressedBubbles.put(locusId, bubble);
                            bubbleData2.doSuppress(bubble);
                            bubbleData2.dispatchPendingChanges();
                        } else {
                            if (z2) {
                                return;
                            }
                            Bubble bubble2 = (Bubble) bubbleData2.mSuppressedBubbles.remove(locusId);
                            if (bubble2 != null) {
                                bubbleData2.doUnsuppress(bubble2);
                            }
                            bubbleData2.dispatchPendingChanges();
                        }
                    }
                };
                synchronized (shellTaskOrganizer.mLock) {
                    try {
                        shellTaskOrganizer.mLocusIdListeners.add(locusIdListener);
                        for (int i2 = 0; i2 < shellTaskOrganizer.mVisibleTasksWithLocusId.size(); i2++) {
                            locusIdListener.onVisibilityChanged(shellTaskOrganizer.mVisibleTasksWithLocusId.keyAt(i2), (LocusId) shellTaskOrganizer.mVisibleTasksWithLocusId.valueAt(i2), true);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                bubbleController.mLauncherApps.registerCallback(new LauncherApps.Callback() { // from class: com.android.wm.shell.bubbles.BubbleController.3
                    public AnonymousClass3() {
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onPackageRemoved(String str, UserHandle userHandle) {
                        BubbleData bubbleData2 = BubbleController.this.mBubbleData;
                        bubbleData2.getClass();
                        BubbleData$$ExternalSyntheticLambda1 bubbleData$$ExternalSyntheticLambda1 = new BubbleData$$ExternalSyntheticLambda1(1, str);
                        BubbleData$$ExternalSyntheticLambda7 bubbleData$$ExternalSyntheticLambda7 = new BubbleData$$ExternalSyntheticLambda7(2, bubbleData2);
                        BubbleData.performActionOnBubblesMatching(Collections.unmodifiableList(bubbleData2.mBubbles), bubbleData$$ExternalSyntheticLambda1, bubbleData$$ExternalSyntheticLambda7);
                        BubbleData.performActionOnBubblesMatching(bubbleData2.getOverflowBubbles(), bubbleData$$ExternalSyntheticLambda1, bubbleData$$ExternalSyntheticLambda7);
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z2) {
                        for (String str : strArr) {
                            BubbleData bubbleData2 = BubbleController.this.mBubbleData;
                            bubbleData2.getClass();
                            BubbleData$$ExternalSyntheticLambda1 bubbleData$$ExternalSyntheticLambda1 = new BubbleData$$ExternalSyntheticLambda1(1, str);
                            BubbleData$$ExternalSyntheticLambda7 bubbleData$$ExternalSyntheticLambda7 = new BubbleData$$ExternalSyntheticLambda7(2, bubbleData2);
                            BubbleData.performActionOnBubblesMatching(Collections.unmodifiableList(bubbleData2.mBubbles), bubbleData$$ExternalSyntheticLambda1, bubbleData$$ExternalSyntheticLambda7);
                            BubbleData.performActionOnBubblesMatching(bubbleData2.getOverflowBubbles(), bubbleData$$ExternalSyntheticLambda1, bubbleData$$ExternalSyntheticLambda7);
                        }
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onShortcutsChanged(final String str, List list, UserHandle userHandle) {
                        super.onShortcutsChanged(str, list, userHandle);
                        BubbleData bubbleData2 = BubbleController.this.mBubbleData;
                        bubbleData2.getClass();
                        final HashSet hashSet = new HashSet();
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            hashSet.add(((ShortcutInfo) it.next()).getId());
                        }
                        Predicate predicate = new Predicate() { // from class: com.android.wm.shell.bubbles.BubbleData$$ExternalSyntheticLambda10
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                ShortcutInfo shortcutInfo;
                                String str2 = str;
                                Set set = hashSet;
                                Bubble bubble = (Bubble) obj;
                                boolean equals = str2.equals(bubble.mPackageName);
                                boolean hasMetadataShortcutId = bubble.hasMetadataShortcutId();
                                if (equals && hasMetadataShortcutId) {
                                    return equals && !(bubble.hasMetadataShortcutId() && (shortcutInfo = bubble.mShortcutInfo) != null && shortcutInfo.isEnabled() && set.contains(bubble.mShortcutInfo.getId()));
                                }
                                return false;
                            }
                        };
                        BubbleData$$ExternalSyntheticLambda7 bubbleData$$ExternalSyntheticLambda7 = new BubbleData$$ExternalSyntheticLambda7(1, bubbleData2);
                        BubbleData.performActionOnBubblesMatching(Collections.unmodifiableList(bubbleData2.mBubbles), predicate, bubbleData$$ExternalSyntheticLambda7);
                        BubbleData.performActionOnBubblesMatching(bubbleData2.getOverflowBubbles(), predicate, bubbleData$$ExternalSyntheticLambda7);
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onPackageAdded(String str, UserHandle userHandle) {
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onPackageChanged(String str, UserHandle userHandle) {
                    }

                    @Override // android.content.pm.LauncherApps.Callback
                    public final void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z2) {
                    }
                }, bubbleController.mMainHandler);
                Transitions transitions = bubbleController.mTransitions;
                BubbleData bubbleData2 = bubbleController.mBubbleData;
                BubblesTransitionObserver bubblesTransitionObserver = new BubblesTransitionObserver();
                bubblesTransitionObserver.mBubbleController = bubbleController;
                bubblesTransitionObserver.mBubbleData = bubbleData2;
                transitions.registerObserver(bubblesTransitionObserver);
                bubbleController.mTaskStackListener.addListener(new TaskStackListenerCallback() { // from class: com.android.wm.shell.bubbles.BubbleController.4
                    public AnonymousClass4() {
                    }

                    @Override // com.android.wm.shell.common.TaskStackListenerCallback
                    public final void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z2) {
                        BubbleData bubbleData3;
                        Bubble bubble;
                        BubbleController bubbleController2 = BubbleController.this;
                        Iterator it = Collections.unmodifiableList(bubbleController2.mBubbleData.mBubbles).iterator();
                        do {
                            boolean hasNext = it.hasNext();
                            bubbleData3 = bubbleController2.mBubbleData;
                            if (!hasNext) {
                                for (Bubble bubble2 : bubbleData3.getOverflowBubbles()) {
                                    if (runningTaskInfo.taskId == bubble2.getTaskId()) {
                                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 7156592394091969590L, 1, Long.valueOf(runningTaskInfo.taskId), String.valueOf(bubble2.mKey));
                                        }
                                        bubbleController2.promoteBubbleFromOverflow(bubble2);
                                        bubbleData3.setExpanded(true);
                                        return;
                                    }
                                }
                                return;
                            }
                            bubble = (Bubble) it.next();
                        } while (runningTaskInfo.taskId != bubble.getTaskId());
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -6884963536522955080L, 1, Long.valueOf(runningTaskInfo.taskId), String.valueOf(bubble.mKey));
                        }
                        bubbleData3.setSelectedBubbleAndExpandStack(bubble);
                    }
                });
                bubbleController.mDisplayController.addDisplayChangingController(new DisplayChangeController.OnDisplayChangingListener() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda10
                    @Override // com.android.wm.shell.common.DisplayChangeController.OnDisplayChangingListener
                    public final void onDisplayChange$1(int i3, int i4, int i5, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
                        BubbleStackView bubbleStackView;
                        BubbleController bubbleController2 = BubbleController.this;
                        bubbleController2.getClass();
                        Rect rect = new Rect();
                        if (displayAreaInfo != null) {
                            rect = displayAreaInfo.configuration.windowConfiguration.getBounds();
                        }
                        if ((i4 == i5 && rect.equals(bubbleController2.mScreenBounds)) || (bubbleStackView = bubbleController2.mStackView) == null) {
                            return;
                        }
                        bubbleStackView.onOrientationChanged();
                    }
                });
                bubbleController.mOneHandedOptional.ifPresent(new BubbleController$$ExternalSyntheticLambda11(bubbleController, 0));
                bubbleController.mDragAndDropController.mListeners.add(new DragAndDropController.DragAndDropListener() { // from class: com.android.wm.shell.bubbles.BubbleController.5
                    public AnonymousClass5() {
                    }

                    @Override // com.android.wm.shell.draganddrop.DragAndDropController.DragAndDropListener
                    public final void onDragStarted() {
                        BubbleController.this.collapseStack();
                    }
                });
                List aliveUsers = bubbleController.mUserManager.getAliveUsers();
                BubbleDataRepository bubbleDataRepository = bubbleController.mDataRepository;
                bubbleDataRepository.getClass();
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(aliveUsers, 10));
                Iterator it = aliveUsers.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((UserInfo) it.next()).id));
                }
                BubbleVolatileRepository bubbleVolatileRepository = bubbleDataRepository.volatileRepository;
                synchronized (bubbleVolatileRepository) {
                    int size = bubbleVolatileRepository.entitiesByUser.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 < size) {
                            int keyAt = bubbleVolatileRepository.entitiesByUser.keyAt(i3);
                            if (!arrayList.contains(Integer.valueOf(keyAt))) {
                                bubbleVolatileRepository.entitiesByUser.remove(keyAt);
                                z = true;
                            } else if (bubbleVolatileRepository.entitiesByUser.get(keyAt) != null) {
                                z = ((List) bubbleVolatileRepository.entitiesByUser.get(keyAt)).removeIf(new BubbleVolatileRepository$removeBubbles$1$1(2, arrayList));
                            } else {
                                i3++;
                            }
                        }
                    }
                }
                if (z) {
                    BubbleDataRepository.persistToDisk$default(bubbleDataRepository);
                }
                SparseArray sparseArray = new SparseArray();
                for (UserInfo userInfo : bubbleController.mUserManager.getProfiles(bubbleController.mCurrentUserId)) {
                    sparseArray.put(userInfo.id, userInfo);
                }
                bubbleController.mCurrentProfiles = sparseArray;
                bubbleController.mShellController.addConfigurationChangeListener(bubbleController);
                bubbleController.mShellController.addExternalInterface("extra_shell_bubbles", new Supplier() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda12
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        BubbleController bubbleController2 = BubbleController.this;
                        bubbleController2.getClass();
                        return bubbleController2.new IBubblesImpl(bubbleController2);
                    }
                }, bubbleController);
                bubbleController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda13
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        BubbleController bubbleController2 = BubbleController.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        bubbleController2.getClass();
                        printWriter.print(str);
                        printWriter.println("BubbleController state:");
                        printWriter.print(str);
                        printWriter.println("  currentUserId= " + bubbleController2.mCurrentUserId);
                        printWriter.print(str);
                        printWriter.println("  isStatusBarShade= " + bubbleController2.mIsStatusBarShade);
                        printWriter.print(str);
                        printWriter.println("  isShowingAsBubbleBar= " + bubbleController2.isShowingAsBubbleBar());
                        printWriter.print(str);
                        printWriter.println("  isImeVisible= " + bubbleController2.mBubblePositioner.mImeVisible);
                        printWriter.println();
                        BubbleData bubbleData3 = bubbleController2.mBubbleData;
                        bubbleData3.getClass();
                        printWriter.println("BubbleData state:");
                        printWriter.print("  selected: ");
                        BubbleViewProvider bubbleViewProvider = bubbleData3.mSelectedBubble;
                        printWriter.println(bubbleViewProvider != null ? bubbleViewProvider.getKey() : null);
                        printWriter.print("  expanded: ");
                        printWriter.println(bubbleData3.mExpanded);
                        printWriter.print("Stack bubble count: ");
                        printWriter.println(((ArrayList) bubbleData3.mBubbles).size());
                        Iterator it2 = bubbleData3.mBubbles.iterator();
                        while (it2.hasNext()) {
                            ((Bubble) it2.next()).dump(printWriter);
                        }
                        printWriter.print("Overflow bubble count: ");
                        printWriter.println(((ArrayList) bubbleData3.mOverflowBubbles).size());
                        Iterator it3 = bubbleData3.mOverflowBubbles.iterator();
                        while (it3.hasNext()) {
                            ((Bubble) it3.next()).dump(printWriter);
                        }
                        printWriter.print("SummaryKeys: ");
                        printWriter.println(bubbleData3.mSuppressedGroupKeys.size());
                        Iterator it4 = bubbleData3.mSuppressedGroupKeys.keySet().iterator();
                        while (it4.hasNext()) {
                            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "     suppressing: ", (String) it4.next());
                        }
                        printWriter.println();
                        BubbleStackView bubbleStackView = bubbleController2.mStackView;
                        if (bubbleStackView != null) {
                            printWriter.println("Stack view state:");
                            ArrayList arrayList2 = new ArrayList();
                            for (int i4 = 0; i4 < bubbleStackView.getBubbleCount(); i4++) {
                                View childAt = bubbleStackView.mBubbleContainer.getChildAt(i4);
                                if (childAt instanceof BadgedImageView) {
                                    BubbleViewProvider bubbleViewProvider2 = ((BadgedImageView) childAt).mBubble;
                                    arrayList2.add(bubbleStackView.mBubbleData.getBubbleInStackWithKey(bubbleViewProvider2 != null ? bubbleViewProvider2.getKey() : null));
                                }
                            }
                            BubbleViewProvider expandedBubble = bubbleStackView.getExpandedBubble();
                            StringBuilder sb = new StringBuilder();
                            for (int i5 = 0; i5 < arrayList2.size(); i5++) {
                                Bubble bubble = (Bubble) arrayList2.get(i5);
                                if (bubble == null) {
                                    sb.append("   <null> !!!!!");
                                } else {
                                    sb.append(String.format("%s Bubble{act=%12d, showInShade=%d, key=%s}", (expandedBubble == null || "Overflow".equals(expandedBubble.getKey()) || bubble != expandedBubble) ? "  " : "=>", Long.valueOf(Math.max(bubble.mLastUpdated, bubble.mLastAccessed)), Integer.valueOf(bubble.showInShade() ? 1 : 0), bubble.mKey));
                                }
                                if (i5 != arrayList2.size() - 1) {
                                    sb.append("\n");
                                }
                            }
                            String sb2 = sb.toString();
                            printWriter.println("  bubbles on screen:       ");
                            printWriter.println(sb2);
                            printWriter.print("  gestureInProgress:       ");
                            printWriter.println(bubbleStackView.mIsGestureInProgress);
                            printWriter.print("  showingDismiss:          ");
                            printWriter.println(bubbleStackView.mDismissView.isShowing);
                            printWriter.print("  isExpansionAnimating:    ");
                            printWriter.println(bubbleStackView.mIsExpansionAnimating);
                            printWriter.print("  expandedContainerVis:    ");
                            printWriter.println(bubbleStackView.mExpandedViewContainer.getVisibility());
                            printWriter.print("  expandedContainerAlpha:  ");
                            printWriter.println(bubbleStackView.mExpandedViewContainer.getAlpha());
                            printWriter.print("  expandedContainerMatrix: ");
                            printWriter.println(bubbleStackView.mExpandedViewContainer.getAnimationMatrix());
                            printWriter.print("  stack visibility :       ");
                            printWriter.println(bubbleStackView.getVisibility());
                            printWriter.print("  temporarilyInvisible:    ");
                            printWriter.println(bubbleStackView.mTemporarilyInvisible);
                            printWriter.print("  expandedViewTemporarilyHidden: ");
                            printWriter.println(bubbleStackView.mExpandedViewTemporarilyHidden);
                            StackAnimationController stackAnimationController = bubbleStackView.mStackAnimationController;
                            stackAnimationController.getClass();
                            printWriter.println("StackAnimationController state:");
                            printWriter.print("  isActive:             ");
                            printWriter.println(stackAnimationController.isActiveController());
                            printWriter.print("  restingStackPos:      ");
                            printWriter.println(stackAnimationController.mPositioner.getRestingPosition().toString());
                            printWriter.print("  currentStackPos:      ");
                            printWriter.println(stackAnimationController.mStackPosition.toString());
                            printWriter.print("  isMovingFromFlinging: ");
                            printWriter.println(stackAnimationController.mIsMovingFromFlinging);
                            printWriter.print("  withinDismiss:        ");
                            printWriter.println(stackAnimationController.isStackStuckToTarget());
                            printWriter.print("  firstBubbleSpringing: ");
                            printWriter.println(stackAnimationController.mFirstBubbleSpringingToTouch);
                            ExpandedAnimationController expandedAnimationController = bubbleStackView.mExpandedAnimationController;
                            expandedAnimationController.getClass();
                            printWriter.println("ExpandedAnimationController state:");
                            printWriter.print("  isActive:          ");
                            printWriter.println(expandedAnimationController.isActiveController());
                            printWriter.print("  animatingExpand:   ");
                            printWriter.println(expandedAnimationController.mAnimatingExpand);
                            printWriter.print("  animatingCollapse: ");
                            printWriter.println(expandedAnimationController.mAnimatingCollapse);
                            printWriter.print("  springingBubble:   ");
                            printWriter.println(expandedAnimationController.mSpringingBubbleToTouch);
                            if (bubbleStackView.mExpandedBubble != null) {
                                StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "Expanded bubble state:", "  expandedBubbleKey: ");
                                m.append(bubbleStackView.mExpandedBubble.getKey());
                                printWriter.println(m.toString());
                                BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                                if (expandedView != null) {
                                    printWriter.println("  expandedViewVis:    " + expandedView.getVisibility());
                                    printWriter.println("  expandedViewAlpha:  " + expandedView.getAlpha());
                                    printWriter.println("  expandedViewTaskId: " + expandedView.mTaskId);
                                    TaskView taskView = expandedView.mTaskView;
                                    if (taskView != null) {
                                        printWriter.println("  activityViewVis:    " + taskView.getVisibility());
                                        printWriter.println("  activityViewAlpha:  " + taskView.getAlpha());
                                    } else {
                                        printWriter.println("  activityView is null");
                                    }
                                } else {
                                    printWriter.println("Expanded bubble view state: expanded bubble view is null");
                                }
                            } else {
                                printWriter.println("Expanded bubble state: expanded bubble is null");
                            }
                        }
                        printWriter.println();
                        BubbleController.BubblesImpl.CachedState cachedState = bubbleController2.mImpl.mCachedState;
                        synchronized (cachedState) {
                            try {
                                printWriter.println("BubbleImpl.CachedState state:");
                                printWriter.println("mIsStackExpanded: " + cachedState.mIsStackExpanded);
                                printWriter.println("mSelectedBubbleKey: " + cachedState.mSelectedBubbleKey);
                                printWriter.println("mSuppressedBubbleKeys: " + cachedState.mSuppressedBubbleKeys.size());
                                Iterator it5 = cachedState.mSuppressedBubbleKeys.iterator();
                                while (it5.hasNext()) {
                                    printWriter.println("   suppressing: " + ((String) it5.next()));
                                }
                                printWriter.print("mSuppressedGroupToNotifKeys: ");
                                printWriter.println(cachedState.mSuppressedGroupToNotifKeys.size());
                                Iterator it6 = cachedState.mSuppressedGroupToNotifKeys.keySet().iterator();
                                while (it6.hasNext()) {
                                    printWriter.println("   suppressing: " + ((String) it6.next()));
                                }
                                printWriter.println("mAppBubbleTaskIds: " + cachedState.mAppBubbleTaskIds.values());
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                }, bubbleController);
                return;
            default:
                bubbleController.getClass();
                try {
                    bubbleController.mContext.unregisterReceiver(bubbleController.mBroadcastReceiver);
                    return;
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    return;
                }
        }
    }
}
