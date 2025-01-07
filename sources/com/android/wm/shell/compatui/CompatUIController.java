package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.util.ArraySet;
import android.util.SparseArray;
import android.view.Display;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.accessibility.AccessibilityManager;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.ShellTaskOrganizer$$ExternalSyntheticLambda0;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.DockStateReader;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.api.CompatUIHandler;
import com.android.wm.shell.sysui.KeyguardChangeListener;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CompatUIController implements DisplayController.OnDisplaysChangedListener, DisplayImeController.ImePositionProcessor, KeyguardChangeListener, CompatUIHandler {
    public LetterboxEduWindowManager mActiveLetterboxEduLayout;
    public ReachabilityEduWindowManager mActiveReachabilityEduLayout;
    public ShellTaskOrganizer$$ExternalSyntheticLambda0 mCallback;
    public final CompatUIConfiguration mCompatUIConfiguration;
    public final CompatUIShellCommandHandler mCompatUIShellCommandHandler;
    public final CompatUIStatusManager mCompatUIStatusManager;
    public final Context mContext;
    public final CompatUIController$$ExternalSyntheticLambda7 mDisappearTimeSupplier;
    public final DisplayController mDisplayController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final DockStateReader mDockStateReader;
    public final DisplayImeController mImeController;
    public boolean mIsFirstReachabilityEducationRunning;
    public boolean mKeyguardShowing;
    public final ShellExecutor mMainExecutor;
    public final ShellController mShellController;
    public final SyncTransactionQueue mSyncQueue;
    public int mTopActivityTaskId;
    public final Lazy mTransitionsLazy;
    public UserAspectRatioSettingsWindowManager mUserAspectRatioSettingsLayout;
    public final Set mDisplaysWithIme = new ArraySet(1);
    public final SparseArray mOnInsetsChangedListeners = new SparseArray(0);
    public final SparseArray mActiveCompatLayouts = new SparseArray(0);
    public final SparseArray mTaskIdToRestartDialogWindowManagerMap = new SparseArray(0);
    public final Set mSetOfTaskIdsShowingRestartDialog = new HashSet();
    public final SparseArray mDisplayContextCache = new SparseArray(0);
    public boolean mHasShownUserAspectRatioSettingsButton = false;
    public final CompatUIHintsState mCompatUIHintsState = new CompatUIHintsState();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CompatUIHintsState {
        public boolean mHasShownSizeCompatHint;
        public boolean mHasShownUserAspectRatioSettingsButtonHint;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PerDisplayOnInsetsChangedListener implements DisplayInsetsController.OnInsetsChangedListener {
        public final int mDisplayId;
        public final InsetsState mInsetsState = new InsetsState();

        public PerDisplayOnInsetsChangedListener(int i) {
            this.mDisplayId = i;
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsChanged(InsetsState insetsState) {
            if (this.mInsetsState.equals(insetsState)) {
                return;
            }
            this.mInsetsState.set(insetsState);
            CompatUIController compatUIController = CompatUIController.this;
            DisplayController displayController = compatUIController.mDisplayController;
            int i = this.mDisplayId;
            compatUIController.forAllLayouts(new CompatUIController$$ExternalSyntheticLambda13(i), new CompatUIController$$ExternalSyntheticLambda14(1, displayController.getDisplayLayout(i)));
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
            insetsChanged(insetsState);
        }
    }

    public CompatUIController(Context context, ShellInit shellInit, ShellController shellController, DisplayController displayController, DisplayInsetsController displayInsetsController, DisplayImeController displayImeController, SyncTransactionQueue syncTransactionQueue, ShellExecutor shellExecutor, Lazy lazy, DockStateReader dockStateReader, CompatUIConfiguration compatUIConfiguration, CompatUIShellCommandHandler compatUIShellCommandHandler, AccessibilityManager accessibilityManager, CompatUIStatusManager compatUIStatusManager, IntPredicate intPredicate) {
        this.mContext = context;
        this.mShellController = shellController;
        this.mDisplayController = displayController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mImeController = displayImeController;
        this.mSyncQueue = syncTransactionQueue;
        this.mMainExecutor = shellExecutor;
        this.mTransitionsLazy = lazy;
        this.mDockStateReader = dockStateReader;
        this.mCompatUIConfiguration = compatUIConfiguration;
        this.mCompatUIShellCommandHandler = compatUIShellCommandHandler;
        this.mDisappearTimeSupplier = new CompatUIController$$ExternalSyntheticLambda7(accessibilityManager);
        this.mCompatUIStatusManager = compatUIStatusManager;
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.compatui.CompatUIController$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                CompatUIController compatUIController = CompatUIController.this;
                compatUIController.mShellController.addKeyguardChangeListener(compatUIController);
                compatUIController.mDisplayController.addDisplayWindowListener(compatUIController);
                compatUIController.mImeController.addPositionProcessor(compatUIController);
                CompatUIShellCommandHandler compatUIShellCommandHandler2 = compatUIController.mCompatUIShellCommandHandler;
                compatUIShellCommandHandler2.mShellCommandHandler.addCommandCallback("compatui", compatUIShellCommandHandler2, compatUIShellCommandHandler2);
            }
        }, this);
    }

    public CompatUIWindowManager createCompatUiWindowManager(Context context, TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        ShellTaskOrganizer$$ExternalSyntheticLambda0 shellTaskOrganizer$$ExternalSyntheticLambda0 = this.mCallback;
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(taskInfo.displayId);
        CompatUIController$$ExternalSyntheticLambda2 compatUIController$$ExternalSyntheticLambda2 = new CompatUIController$$ExternalSyntheticLambda2(this, 1);
        return new CompatUIWindowManager(context, taskInfo, this.mSyncQueue, shellTaskOrganizer$$ExternalSyntheticLambda0, taskListener, displayLayout, this.mCompatUIHintsState, this.mCompatUIConfiguration, compatUIController$$ExternalSyntheticLambda2);
    }

    public LetterboxEduWindowManager createLetterboxEduWindowManager(Context context, TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        return new LetterboxEduWindowManager(context, taskInfo, this.mSyncQueue, taskListener, this.mDisplayController.getDisplayLayout(taskInfo.displayId), (Transitions) this.mTransitionsLazy.get(), new CompatUIController$$ExternalSyntheticLambda2(this, 4), new DialogAnimationController(context, "LetterboxEduWindowManager"), this.mDockStateReader, this.mCompatUIConfiguration, this.mCompatUIStatusManager);
    }

    public final void createOrUpdateReachabilityEduLayout(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        ReachabilityEduWindowManager reachabilityEduWindowManager = this.mActiveReachabilityEduLayout;
        if (reachabilityEduWindowManager != null) {
            if (!reachabilityEduWindowManager.needsToBeRecreated(taskInfo, taskListener)) {
                ReachabilityEduWindowManager reachabilityEduWindowManager2 = this.mActiveReachabilityEduLayout;
                if (reachabilityEduWindowManager2.updateCompatInfo(taskInfo, taskListener, showOnDisplay(reachabilityEduWindowManager2.mDisplayId))) {
                    return;
                }
                this.mActiveReachabilityEduLayout.release();
                this.mActiveReachabilityEduLayout = null;
                return;
            }
            this.mActiveReachabilityEduLayout.release();
            this.mActiveReachabilityEduLayout = null;
        }
        Context orCreateDisplayContext = getOrCreateDisplayContext(taskInfo.displayId);
        if (orCreateDisplayContext == null) {
            return;
        }
        ReachabilityEduWindowManager createReachabilityEduWindowManager = createReachabilityEduWindowManager(orCreateDisplayContext, taskInfo, taskListener);
        if (createReachabilityEduWindowManager.createLayout(showOnDisplay(taskInfo.displayId))) {
            ReachabilityEduWindowManager reachabilityEduWindowManager3 = this.mActiveReachabilityEduLayout;
            if (reachabilityEduWindowManager3 != null) {
                reachabilityEduWindowManager3.release();
            }
            this.mActiveReachabilityEduLayout = createReachabilityEduWindowManager;
        }
    }

    public final void createOrUpdateUserAspectRatioSettingsLayout(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager = this.mUserAspectRatioSettingsLayout;
        if (userAspectRatioSettingsWindowManager != null) {
            if (!userAspectRatioSettingsWindowManager.needsToBeRecreated(taskInfo, taskListener)) {
                UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager2 = this.mUserAspectRatioSettingsLayout;
                if (userAspectRatioSettingsWindowManager2.updateCompatInfo(taskInfo, taskListener, showOnDisplay(userAspectRatioSettingsWindowManager2.mDisplayId))) {
                    return;
                }
                this.mUserAspectRatioSettingsLayout.release();
                this.mUserAspectRatioSettingsLayout = null;
                return;
            }
            this.mUserAspectRatioSettingsLayout.release();
            this.mUserAspectRatioSettingsLayout = null;
        }
        Context orCreateDisplayContext = getOrCreateDisplayContext(taskInfo.displayId);
        if (orCreateDisplayContext == null) {
            return;
        }
        UserAspectRatioSettingsWindowManager createUserAspectRatioSettingsWindowManager = createUserAspectRatioSettingsWindowManager(orCreateDisplayContext, taskInfo, taskListener);
        if (createUserAspectRatioSettingsWindowManager.createLayout(showOnDisplay(taskInfo.displayId))) {
            this.mUserAspectRatioSettingsLayout = createUserAspectRatioSettingsWindowManager;
        }
    }

    public ReachabilityEduWindowManager createReachabilityEduWindowManager(Context context, TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(taskInfo.displayId);
        CompatUIController$$ExternalSyntheticLambda0 compatUIController$$ExternalSyntheticLambda0 = new CompatUIController$$ExternalSyntheticLambda0(this, 1);
        return new ReachabilityEduWindowManager(context, taskInfo, this.mSyncQueue, taskListener, displayLayout, this.mCompatUIConfiguration, this.mMainExecutor, compatUIController$$ExternalSyntheticLambda0, this.mDisappearTimeSupplier);
    }

    public RestartDialogWindowManager createRestartDialogWindowManager(Context context, TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        return new RestartDialogWindowManager(context, taskInfo, this.mSyncQueue, taskListener, this.mDisplayController.getDisplayLayout(taskInfo.displayId), (Transitions) this.mTransitionsLazy.get(), new CompatUIController$$ExternalSyntheticLambda2(this, 2), new CompatUIController$$ExternalSyntheticLambda2(this, 3), new DialogAnimationController(context, "RestartDialogWindowManager"), this.mCompatUIConfiguration);
    }

    public UserAspectRatioSettingsWindowManager createUserAspectRatioSettingsWindowManager(Context context, TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(taskInfo.displayId);
        int i = 0;
        CompatUIController$$ExternalSyntheticLambda0 compatUIController$$ExternalSyntheticLambda0 = new CompatUIController$$ExternalSyntheticLambda0(this, i);
        CompatUIController$$ExternalSyntheticLambda1 compatUIController$$ExternalSyntheticLambda1 = new CompatUIController$$ExternalSyntheticLambda1(this);
        CompatUIController$$ExternalSyntheticLambda2 compatUIController$$ExternalSyntheticLambda2 = new CompatUIController$$ExternalSyntheticLambda2(this, i);
        return new UserAspectRatioSettingsWindowManager(context, taskInfo, this.mSyncQueue, taskListener, displayLayout, this.mCompatUIHintsState, compatUIController$$ExternalSyntheticLambda0, this.mMainExecutor, this.mDisappearTimeSupplier, compatUIController$$ExternalSyntheticLambda1, compatUIController$$ExternalSyntheticLambda2);
    }

    public final void forAllLayouts(Predicate predicate, Consumer consumer) {
        for (int i = 0; i < this.mActiveCompatLayouts.size(); i++) {
            CompatUIWindowManager compatUIWindowManager = (CompatUIWindowManager) this.mActiveCompatLayouts.get(this.mActiveCompatLayouts.keyAt(i));
            if (compatUIWindowManager != null && predicate.test(compatUIWindowManager)) {
                consumer.accept(compatUIWindowManager);
            }
        }
        LetterboxEduWindowManager letterboxEduWindowManager = this.mActiveLetterboxEduLayout;
        if (letterboxEduWindowManager != null && predicate.test(letterboxEduWindowManager)) {
            consumer.accept(this.mActiveLetterboxEduLayout);
        }
        for (int i2 = 0; i2 < this.mTaskIdToRestartDialogWindowManagerMap.size(); i2++) {
            RestartDialogWindowManager restartDialogWindowManager = (RestartDialogWindowManager) this.mTaskIdToRestartDialogWindowManagerMap.get(this.mTaskIdToRestartDialogWindowManagerMap.keyAt(i2));
            if (restartDialogWindowManager != null && predicate.test(restartDialogWindowManager)) {
                consumer.accept(restartDialogWindowManager);
            }
        }
        ReachabilityEduWindowManager reachabilityEduWindowManager = this.mActiveReachabilityEduLayout;
        if (reachabilityEduWindowManager != null && predicate.test(reachabilityEduWindowManager)) {
            consumer.accept(this.mActiveReachabilityEduLayout);
        }
        UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager = this.mUserAspectRatioSettingsLayout;
        if (userAspectRatioSettingsWindowManager == null || !predicate.test(userAspectRatioSettingsWindowManager)) {
            return;
        }
        consumer.accept(this.mUserAspectRatioSettingsLayout);
    }

    public final Context getOrCreateDisplayContext(int i) {
        if (i == 0) {
            return this.mContext;
        }
        WeakReference weakReference = (WeakReference) this.mDisplayContextCache.get(i);
        Context context = weakReference != null ? (Context) weakReference.get() : null;
        if (context != null) {
            return context;
        }
        Display display = ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).getDisplay(i);
        if (display == null) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Cannot get context for display ", "CompatUIController", i);
            return context;
        }
        Context createDisplayContext = this.mContext.createDisplayContext(display);
        this.mDisplayContextCache.put(i, new WeakReference(createDisplayContext));
        return createDisplayContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCompatInfoChanged(com.android.wm.shell.compatui.api.CompatUIInfo r11) {
        /*
            Method dump skipped, instructions count: 537
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.compatui.CompatUIController.onCompatInfoChanged(com.android.wm.shell.compatui.api.CompatUIInfo):void");
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        PerDisplayOnInsetsChangedListener perDisplayOnInsetsChangedListener = new PerDisplayOnInsetsChangedListener(i);
        this.mDisplayInsetsController.addInsetsChangedListener(perDisplayOnInsetsChangedListener.mDisplayId, perDisplayOnInsetsChangedListener);
        this.mOnInsetsChangedListeners.put(i, perDisplayOnInsetsChangedListener);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
        forAllLayouts(new CompatUIController$$ExternalSyntheticLambda13(i), new CompatUIController$$ExternalSyntheticLambda14(1, this.mDisplayController.getDisplayLayout(i)));
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayRemoved(int i) {
        this.mDisplayContextCache.remove(i);
        PerDisplayOnInsetsChangedListener perDisplayOnInsetsChangedListener = (PerDisplayOnInsetsChangedListener) this.mOnInsetsChangedListeners.get(i);
        if (perDisplayOnInsetsChangedListener != null) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) CompatUIController.this.mDisplayInsetsController.mListeners.get(perDisplayOnInsetsChangedListener.mDisplayId);
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove(perDisplayOnInsetsChangedListener);
            }
            this.mOnInsetsChangedListeners.remove(i);
        }
        ArrayList arrayList = new ArrayList();
        forAllLayouts(new CompatUIController$$ExternalSyntheticLambda13(i), new CompatUIController$$ExternalSyntheticLambda14(0, arrayList));
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            removeLayouts(((Integer) arrayList.get(size)).intValue());
        }
    }

    @Override // com.android.wm.shell.common.DisplayImeController.ImePositionProcessor
    public final void onImeVisibilityChanged(final int i, boolean z) {
        if (z) {
            ((ArraySet) this.mDisplaysWithIme).add(Integer.valueOf(i));
        } else {
            ((ArraySet) this.mDisplaysWithIme).remove(Integer.valueOf(i));
        }
        forAllLayouts(new CompatUIController$$ExternalSyntheticLambda13(i), new Consumer() { // from class: com.android.wm.shell.compatui.CompatUIController$$ExternalSyntheticLambda11
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CompatUIWindowManagerAbstract) obj).updateVisibility(CompatUIController.this.showOnDisplay(i));
            }
        });
    }

    @Override // com.android.wm.shell.sysui.KeyguardChangeListener
    public final void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
        this.mKeyguardShowing = z;
        forAllLayouts(new CompatUIController$$ExternalSyntheticLambda12(), new CompatUIController$$ExternalSyntheticLambda2(this, 5));
    }

    public void removeLayouts(int i) {
        CompatUIWindowManager compatUIWindowManager = (CompatUIWindowManager) this.mActiveCompatLayouts.get(i);
        if (compatUIWindowManager != null) {
            compatUIWindowManager.release();
            this.mActiveCompatLayouts.remove(i);
        }
        LetterboxEduWindowManager letterboxEduWindowManager = this.mActiveLetterboxEduLayout;
        if (letterboxEduWindowManager != null && letterboxEduWindowManager.mTaskId == i) {
            letterboxEduWindowManager.release();
            this.mActiveLetterboxEduLayout = null;
        }
        RestartDialogWindowManager restartDialogWindowManager = (RestartDialogWindowManager) this.mTaskIdToRestartDialogWindowManagerMap.get(i);
        if (restartDialogWindowManager != null) {
            restartDialogWindowManager.release();
            this.mTaskIdToRestartDialogWindowManagerMap.remove(i);
            this.mSetOfTaskIdsShowingRestartDialog.remove(Integer.valueOf(i));
        }
        ReachabilityEduWindowManager reachabilityEduWindowManager = this.mActiveReachabilityEduLayout;
        if (reachabilityEduWindowManager != null && reachabilityEduWindowManager.mTaskId == i) {
            reachabilityEduWindowManager.release();
            this.mActiveReachabilityEduLayout = null;
        }
        UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager = this.mUserAspectRatioSettingsLayout;
        if (userAspectRatioSettingsWindowManager == null || userAspectRatioSettingsWindowManager.mTaskId != i) {
            return;
        }
        userAspectRatioSettingsWindowManager.release();
        this.mUserAspectRatioSettingsLayout = null;
    }

    public final boolean showOnDisplay(int i) {
        if (!this.mKeyguardShowing) {
            if (!((ArraySet) this.mDisplaysWithIme).contains(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }
}
