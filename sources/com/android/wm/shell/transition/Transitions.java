package com.android.wm.shell.transition;

import android.R;
import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.app.IApplicationThread;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.util.Slog;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.ITransitionPlayer;
import android.window.RemoteTransition;
import android.window.TransitionFilter;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.nano.Transition;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.IHomeTransitionListener$Stub$Proxy;
import com.android.wm.shell.shared.ShellTransitions;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.transition.tracing.LegacyTransitionTracer;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Transitions implements RemoteCallable, ShellCommandHandler.ShellCommandActionHandler {
    public static final boolean ENABLE_SHELL_TRANSITIONS;
    public static final boolean SHELL_TRANSITIONS_ROTATION;
    public final ShellExecutor mAnimExecutor;
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final ArrayList mHandlers;
    public final HomeTransitionObserver mHomeTransitionObserver;
    public final ArrayMap mKnownTransitions;
    public final ShellExecutor mMainExecutor;
    public final ArrayList mObservers;
    public final ShellTaskOrganizer mOrganizer;
    public final ArrayList mPendingTransitions;
    public final TransitionPlayerImpl mPlayerImpl;
    public final ArrayList mReadyDuringSync;
    public final RemoteTransitionHandler mRemoteTransitionHandler;
    public final ArrayList mRunWhenIdleQueue;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final ArrayList mTracks;
    public float mTransitionAnimationScaleSetting;
    public final LegacyTransitionTracer mTransitionTracer;
    public final ShellTransitionImpl mImpl = new ShellTransitionImpl();
    public final SleepHandler mSleepHandler = new SleepHandler();
    public boolean mIsRegistered = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActiveTransition {
        public boolean mAborted;
        public SurfaceControl.Transaction mFinishT;
        public TransitionHandler mHandler;
        public TransitionInfo mInfo;
        public ArrayList mMerged;
        public SurfaceControl.Transaction mStartT;
        public final IBinder mToken;

        public ActiveTransition(IBinder iBinder) {
            this.mToken = iBinder;
        }

        public final int getTrack() {
            TransitionInfo transitionInfo = this.mInfo;
            if (transitionInfo != null) {
                return transitionInfo.getTrack();
            }
            return -1;
        }

        public final String toString() {
            TransitionInfo transitionInfo = this.mInfo;
            if (transitionInfo == null || transitionInfo.getDebugId() < 0) {
                return this.mToken.toString() + "@" + getTrack();
            }
            return "(#" + this.mInfo.getDebugId() + ") " + this.mToken + "@" + getTrack();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsObserver extends ContentObserver {
        public SettingsObserver() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            Transitions transitions = Transitions.this;
            transitions.mTransitionAnimationScaleSetting = WindowManager.fixScale(Settings.Global.getFloat(transitions.mContext.getContentResolver(), "transition_animation_scale", transitions.mContext.getResources().getFloat(R.dimen.config_buttonCornerRadius)));
            ((HandlerExecutor) Transitions.this.mMainExecutor).execute(new Transitions$$ExternalSyntheticLambda1(1, this));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShellTransitionImpl implements ShellTransitions {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Track {
        public final ArrayList mReadyTransitions = new ArrayList();
        public ActiveTransition mActiveTransition = null;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransitionFinishCallback {
        void onTransitionFinished(WindowContainerTransaction windowContainerTransaction);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionPlayerImpl extends ITransitionPlayer.Stub {
        public TransitionPlayerImpl() {
        }

        public final void onTransitionReady(final IBinder iBinder, final TransitionInfo transitionInfo, final SurfaceControl.Transaction transaction, final SurfaceControl.Transaction transaction2) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 6541979577719206723L, 1, Long.valueOf(transaction.getId()));
            }
            ((HandlerExecutor) Transitions.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.Transitions$TransitionPlayerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Transitions.TransitionPlayerImpl transitionPlayerImpl = Transitions.TransitionPlayerImpl.this;
                    Transitions.this.onTransitionReady(iBinder, transitionInfo, transaction, transaction2);
                }
            });
        }

        public final void requestStartTransition(final IBinder iBinder, final TransitionRequestInfo transitionRequestInfo) {
            ((HandlerExecutor) Transitions.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.Transitions$TransitionPlayerImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WindowContainerTransaction windowContainerTransaction;
                    WindowContainerTransaction windowContainerTransaction2;
                    Transitions.TransitionPlayerImpl transitionPlayerImpl = Transitions.TransitionPlayerImpl.this;
                    IBinder iBinder2 = iBinder;
                    TransitionRequestInfo transitionRequestInfo2 = transitionRequestInfo;
                    Transitions transitions = Transitions.this;
                    transitions.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 319134872409225255L, 1, Long.valueOf(transitionRequestInfo2.getDebugId()), String.valueOf(iBinder2), String.valueOf(transitionRequestInfo2));
                    }
                    if (transitions.mKnownTransitions.containsKey(iBinder2)) {
                        throw new RuntimeException("Transition already started " + iBinder2);
                    }
                    Transitions.ActiveTransition activeTransition = new Transitions.ActiveTransition(iBinder2);
                    transitions.mKnownTransitions.put(iBinder2, activeTransition);
                    if (transitionRequestInfo2.getType() == 12) {
                        transitions.mSleepHandler.handleRequest(iBinder2, transitionRequestInfo2);
                        activeTransition.mHandler = transitions.mSleepHandler;
                        windowContainerTransaction = null;
                    } else {
                        int size = transitions.mHandlers.size() - 1;
                        windowContainerTransaction = null;
                        while (true) {
                            if (size < 0) {
                                break;
                            }
                            windowContainerTransaction = ((Transitions.TransitionHandler) transitions.mHandlers.get(size)).handleRequest(iBinder2, transitionRequestInfo2);
                            if (windowContainerTransaction != null) {
                                activeTransition.mHandler = (Transitions.TransitionHandler) transitions.mHandlers.get(size);
                                break;
                            }
                            size--;
                        }
                        if (transitionRequestInfo2.getDisplayChange() != null) {
                            TransitionRequestInfo.DisplayChange displayChange = transitionRequestInfo2.getDisplayChange();
                            if (displayChange.getStartRotation() != displayChange.getEndRotation() || (displayChange.getStartAbsBounds() != null && !displayChange.getStartAbsBounds().equals(displayChange.getEndAbsBounds()))) {
                                if (windowContainerTransaction == null) {
                                    windowContainerTransaction = new WindowContainerTransaction();
                                }
                                DisplayController displayController = transitions.mDisplayController;
                                int displayId = displayChange.getDisplayId();
                                displayChange.getStartAbsBounds();
                                Rect endAbsBounds = displayChange.getEndAbsBounds();
                                int startRotation = displayChange.getStartRotation();
                                int endRotation = displayChange.getEndRotation();
                                synchronized (displayController.mDisplays) {
                                    try {
                                        DisplayController.DisplayRecord displayRecord = (DisplayController.DisplayRecord) displayController.mDisplays.get(displayId);
                                        if (displayRecord == null) {
                                            Slog.w("DisplayController", "Skipping Display rotate on non-added display.");
                                        } else {
                                            DisplayLayout displayLayout = displayRecord.mDisplayLayout;
                                            if (displayLayout != null) {
                                                if (endAbsBounds != null) {
                                                    Resources resources = displayRecord.mContext.getResources();
                                                    Size size2 = new Size(endAbsBounds.width(), endAbsBounds.height());
                                                    displayLayout.mWidth = size2.getWidth();
                                                    displayLayout.mHeight = size2.getHeight();
                                                    displayLayout.recalcInsets(resources);
                                                }
                                                if (startRotation != endRotation) {
                                                    displayRecord.mDisplayLayout.rotateTo(endRotation, displayRecord.mContext.getResources());
                                                }
                                            }
                                            displayController.mChangeController.dispatchOnDisplayChange(displayId, startRotation, endRotation, null, windowContainerTransaction);
                                        }
                                    } finally {
                                    }
                                }
                            }
                        }
                    }
                    if ((transitionRequestInfo2.getType() == 8 || (transitionRequestInfo2.getFlags() & 4096) != 0) && transitionRequestInfo2.getTriggerTask() != null && transitionRequestInfo2.getTriggerTask().getWindowingMode() == 5) {
                        if (windowContainerTransaction == null) {
                            windowContainerTransaction = new WindowContainerTransaction();
                        }
                        windowContainerTransaction.setWindowingMode(transitionRequestInfo2.getTriggerTask().token, 1);
                        windowContainerTransaction2 = null;
                        windowContainerTransaction.setBounds(transitionRequestInfo2.getTriggerTask().token, (Rect) null);
                    } else {
                        windowContainerTransaction2 = null;
                    }
                    transitions.mOrganizer.startTransition(iBinder2, (windowContainerTransaction == null || !windowContainerTransaction.isEmpty()) ? windowContainerTransaction : windowContainerTransaction2);
                    transitions.mPendingTransitions.add(0, activeTransition);
                }
            });
        }
    }

    static {
        boolean z;
        try {
        } catch (RemoteException unused) {
            Log.w("ShellTransitions", "Error getting system features");
        }
        if (AppGlobals.getPackageManager().hasSystemFeature("android.hardware.type.automotive", 0)) {
            z = SystemProperties.getBoolean("persist.wm.debug.shell_transit", true);
            ENABLE_SHELL_TRANSITIONS = z;
            SHELL_TRANSITIONS_ROTATION = !z && SystemProperties.getBoolean("persist.wm.debug.shell_transit_rotate", false);
        }
        z = true;
        ENABLE_SHELL_TRANSITIONS = z;
        SHELL_TRANSITIONS_ROTATION = !z && SystemProperties.getBoolean("persist.wm.debug.shell_transit_rotate", false);
    }

    public Transitions(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellTaskOrganizer shellTaskOrganizer, TransactionPool transactionPool, DisplayController displayController, ShellExecutor shellExecutor, Handler handler, ShellExecutor shellExecutor2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, HomeTransitionObserver homeTransitionObserver) {
        ArrayList arrayList = new ArrayList();
        this.mHandlers = arrayList;
        this.mObservers = new ArrayList();
        this.mRunWhenIdleQueue = new ArrayList();
        this.mTransitionAnimationScaleSetting = 1.0f;
        this.mKnownTransitions = new ArrayMap();
        this.mPendingTransitions = new ArrayList();
        this.mReadyDuringSync = new ArrayList();
        this.mTracks = new ArrayList();
        this.mOrganizer = shellTaskOrganizer;
        this.mContext = context;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mDisplayController = displayController;
        this.mPlayerImpl = new TransitionPlayerImpl();
        DefaultTransitionHandler defaultTransitionHandler = new DefaultTransitionHandler(context, shellInit, displayController, transactionPool, shellExecutor, handler, shellExecutor2, rootTaskDisplayAreaOrganizer);
        RemoteTransitionHandler remoteTransitionHandler = new RemoteTransitionHandler(shellExecutor);
        this.mRemoteTransitionHandler = remoteTransitionHandler;
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellController = shellController;
        arrayList.add(defaultTransitionHandler);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -6160073112438359978L, 0, null);
        }
        arrayList.add(remoteTransitionHandler);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -2436748845961170270L, 0, null);
        }
        shellInit.addInitCallback(new Transitions$$ExternalSyntheticLambda1(0, this), this);
        this.mHomeTransitionObserver = homeTransitionObserver;
        this.mTransitionTracer = new LegacyTransitionTracer();
    }

    public static int calculateAnimLayer(TransitionInfo.Change change, int i, int i2, int i3) {
        int i4 = i2 + 1;
        boolean isOpeningType = TransitionUtil.isOpeningType(i3);
        boolean isClosingType = TransitionUtil.isClosingType(i3);
        int mode = change.getMode();
        if (mode == 1 || mode == 3) {
            if (!isOpeningType && isClosingType) {
                return i4 - i;
            }
        } else if (mode == 2 || mode == 4) {
            if (isOpeningType) {
                return i4 - i;
            }
        } else if (isClosingType || TransitionUtil.isOrderOnly(change)) {
            return i4 - i;
        }
        return (i4 + i2) - i;
    }

    public static void setRunningRemoteTransitionDelegate(IApplicationThread iApplicationThread) {
        if (iApplicationThread == null) {
            return;
        }
        try {
            ActivityTaskManager.getService().setRunningRemoteTransitionDelegate(iApplicationThread);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        } catch (SecurityException unused) {
            Log.e("ShellTransitions", "Unable to boost animation process. This should only happen during unit tests");
        }
    }

    public final void addHandler(TransitionHandler transitionHandler) {
        if (this.mHandlers.isEmpty()) {
            throw new RuntimeException("Unexpected handler added prior to initialization, please use ShellInit callbacks to ensure proper ordering");
        }
        this.mHandlers.add(transitionHandler);
        transitionHandler.setAnimScaleSetting(this.mTransitionAnimationScaleSetting);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 6809365603763764620L, 0, transitionHandler.getClass().getSimpleName());
        }
    }

    public final boolean dispatchReady(ActiveTransition activeTransition) {
        char c;
        char c2;
        TransitionInfo transitionInfo = activeTransition.mInfo;
        if (transitionInfo.getType() == 12 || (activeTransition.mInfo.getFlags() & 2097152) != 0) {
            this.mReadyDuringSync.add(0, activeTransition);
            boolean z = false;
            for (int i = 0; i < this.mTracks.size(); i++) {
                Track track = (Track) this.mTracks.get(i);
                if (track.mActiveTransition != null || !track.mReadyTransitions.isEmpty()) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7942742606586919691L, 1, Long.valueOf(i));
                    }
                    finishForSync(activeTransition.mToken, i, null);
                    z = true;
                }
            }
            if (z) {
                return false;
            }
            this.mReadyDuringSync.remove(activeTransition);
        }
        int track2 = transitionInfo.getTrack();
        while (track2 >= this.mTracks.size()) {
            this.mTracks.add(new Track());
        }
        Track track3 = (Track) this.mTracks.get(track2);
        track3.mReadyTransitions.add(activeTransition);
        for (int i2 = 0; i2 < this.mObservers.size(); i2++) {
            ((TransitionObserver) this.mObservers.get(i2)).onTransitionReady(activeTransition.mToken, transitionInfo, activeTransition.mStartT, activeTransition.mFinishT);
        }
        if (transitionInfo.getRootCount() == 0 && !KeyguardTransitionHandler.handles(transitionInfo)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 3980123572601110375L, 0, String.valueOf(activeTransition));
            }
            onAbort(activeTransition);
            return true;
        }
        int size = transitionInfo.getChanges().size();
        boolean z2 = size > 0;
        int i3 = size - 1;
        boolean z3 = false;
        boolean z4 = false;
        int i4 = 0;
        while (true) {
            c = '\b';
            if (i3 < 0) {
                break;
            }
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i3);
            z3 |= change.getTaskInfo() != null;
            z4 |= change.hasFlags(8);
            if (change.hasAllFlags(278528) || change.hasAllFlags(16896)) {
                i4++;
            }
            if (!change.hasFlags(32768)) {
                z2 = false;
            } else if (change.hasAllFlags(294912)) {
                transitionInfo.getChanges().remove(i3);
            }
            i3--;
        }
        if ((!z3 && ((z4 || i4 == size) && size >= 1)) || ((transitionInfo.getType() == 4 || transitionInfo.getType() == 3) && z2)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -8508011197847096775L, 0, String.valueOf(activeTransition));
            }
            onAbort(activeTransition);
            return true;
        }
        TransitionInfo transitionInfo2 = activeTransition.mInfo;
        SurfaceControl.Transaction transaction = activeTransition.mStartT;
        SurfaceControl.Transaction transaction2 = activeTransition.mFinishT;
        boolean isOpeningType = TransitionUtil.isOpeningType(transitionInfo2.getType());
        int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo2, 1);
        while (m >= 0) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo2.getChanges().get(m);
            if (change2.hasFlags(65792) || change2.hasFlags(2)) {
                c2 = c;
            } else {
                SurfaceControl leash = change2.getLeash();
                int mode = ((TransitionInfo.Change) transitionInfo2.getChanges().get(m)).getMode();
                if (mode == 3) {
                    transaction.setPosition(leash, change2.getEndRelOffset().x, change2.getEndRelOffset().y);
                    transaction.setWindowCrop(leash, change2.getEndAbsBounds().width(), change2.getEndAbsBounds().height());
                }
                if (TransitionInfo.isIndependent(change2, transitionInfo2)) {
                    if (mode == 1 || mode == 3) {
                        transaction.show(leash);
                        transaction.setMatrix(leash, 1.0f, 0.0f, 0.0f, 1.0f);
                        if (isOpeningType) {
                            c2 = '\b';
                            if ((change2.getFlags() & 8) == 0) {
                                transaction.setAlpha(leash, 0.0f);
                            }
                        } else {
                            c2 = '\b';
                        }
                        transaction2.show(leash);
                    } else if (mode == 2 || mode == 4) {
                        transaction2.hide(leash);
                    } else if (isOpeningType && mode == 6) {
                        transaction.show(leash);
                    }
                } else if (mode == 1 || mode == 3 || mode == 6) {
                    transaction.show(leash);
                    transaction.setMatrix(leash, 1.0f, 0.0f, 0.0f, 1.0f);
                    transaction.setAlpha(leash, 1.0f);
                    transaction.setPosition(leash, change2.getEndRelOffset().x, change2.getEndRelOffset().y);
                    transaction.setWindowCrop(leash, change2.getEndAbsBounds().width(), change2.getEndAbsBounds().height());
                }
                c2 = '\b';
            }
            m--;
            c = c2;
        }
        if (track3.mReadyTransitions.size() > 1) {
            return true;
        }
        processReadyQueue(track3);
        return true;
    }

    public final Pair dispatchRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo, DefaultMixedHandler defaultMixedHandler) {
        WindowContainerTransaction handleRequest;
        for (int size = this.mHandlers.size() - 1; size >= 0; size--) {
            if (this.mHandlers.get(size) != defaultMixedHandler && (handleRequest = ((TransitionHandler) this.mHandlers.get(size)).handleRequest(iBinder, transitionRequestInfo)) != null) {
                return new Pair((TransitionHandler) this.mHandlers.get(size), handleRequest);
            }
        }
        return null;
    }

    public final TransitionHandler dispatchTransition(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, TransitionFinishCallback transitionFinishCallback, TransitionHandler transitionHandler) {
        for (int size = this.mHandlers.size() - 1; size >= 0; size--) {
            if (this.mHandlers.get(size) != transitionHandler) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 2316055606185049262L, 0, String.valueOf(this.mHandlers.get(size)));
                }
                if (((TransitionHandler) this.mHandlers.get(size)).startAnimation(iBinder, transitionInfo, transaction, transaction2, transitionFinishCallback)) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5292399587644158186L, 0, String.valueOf(this.mHandlers.get(size)));
                    }
                    this.mTransitionTracer.logDispatched(transitionInfo.getDebugId(), (TransitionHandler) this.mHandlers.get(size));
                    return (TransitionHandler) this.mHandlers.get(size);
                }
            }
        }
        throw new IllegalStateException("This shouldn't happen, maybe the default handler is broken.");
    }

    public final void finishForSync(final IBinder iBinder, final int i, ActiveTransition activeTransition) {
        if (!this.mKnownTransitions.containsKey(iBinder)) {
            Log.d("ShellTransitions", "finishForSleep: already played sync transition " + iBinder);
            return;
        }
        Track track = (Track) this.mTracks.get(i);
        if (activeTransition != null) {
            Track track2 = (Track) this.mTracks.get(activeTransition.getTrack());
            if (track2 != track) {
                Log.e("ShellTransitions", "finishForSleep: mismatched Tracks between forceFinish and logic " + activeTransition.getTrack() + " vs " + i);
            }
            if (track2.mActiveTransition == activeTransition) {
                Log.e("ShellTransitions", "Forcing transition to finish due to sync timeout: " + activeTransition);
                activeTransition.mAborted = true;
                TransitionHandler transitionHandler = activeTransition.mHandler;
                if (transitionHandler != null) {
                    transitionHandler.onTransitionConsumed(activeTransition.mToken, true, null);
                }
                onFinish(activeTransition.mToken, null);
            }
        }
        if ((track.mActiveTransition == null && track.mReadyTransitions.isEmpty()) || this.mReadyDuringSync.isEmpty()) {
            return;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        TransitionInfo transitionInfo = new TransitionInfo(12, 0);
        while (track.mActiveTransition != null && !this.mReadyDuringSync.isEmpty()) {
            final ActiveTransition activeTransition2 = track.mActiveTransition;
            ActiveTransition activeTransition3 = (ActiveTransition) this.mReadyDuringSync.get(0);
            if ((activeTransition3.mInfo.getFlags() & 2097152) == 0) {
                Log.e("ShellTransitions", "Somehow blocked on a non-sync transition? " + activeTransition3);
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -8650312174245760311L, 0, String.valueOf(activeTransition3), String.valueOf(activeTransition2));
            }
            activeTransition2.mHandler.mergeAnimation(activeTransition3.mToken, transitionInfo, transaction, activeTransition2.mToken, new Transitions$$ExternalSyntheticLambda3());
            if (track.mActiveTransition == activeTransition2) {
                ((HandlerExecutor) this.mMainExecutor).executeDelayed(new Runnable() { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        Transitions.this.finishForSync(iBinder, i, activeTransition2);
                    }
                }, 120L);
                return;
            }
        }
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public final void onAbort(ActiveTransition activeTransition) {
        Track track = (Track) this.mTracks.get(activeTransition.getTrack());
        activeTransition.mAborted = true;
        int debugId = activeTransition.mInfo.getDebugId();
        LegacyTransitionTracer legacyTransitionTracer = this.mTransitionTracer;
        legacyTransitionTracer.getClass();
        Transition transition = new Transition();
        transition.id = debugId;
        transition.abortTimeNs = SystemClock.elapsedRealtimeNanos();
        legacyTransitionTracer.mTraceBuffer.add(transition);
        TransitionHandler transitionHandler = activeTransition.mHandler;
        if (transitionHandler != null) {
            transitionHandler.onTransitionConsumed(activeTransition.mToken, true, null);
        }
        TransitionInfo transitionInfo = activeTransition.mInfo;
        if (transitionInfo != null) {
            transitionInfo.releaseAnimSurfaces();
        }
        if (track.mReadyTransitions.size() > 1) {
            return;
        }
        processReadyQueue(track);
    }

    public final void onFinish(IBinder iBinder, WindowContainerTransaction windowContainerTransaction) {
        ((HandlerExecutor) this.mMainExecutor).assertCurrentThread();
        ActiveTransition activeTransition = (ActiveTransition) this.mKnownTransitions.get(iBinder);
        if (activeTransition == null) {
            Log.e("ShellTransitions", "Trying to finish a non-existent transition: " + iBinder);
            return;
        }
        Track track = (Track) this.mTracks.get(activeTransition.getTrack());
        if (track == null || track.mActiveTransition != activeTransition) {
            Log.e("ShellTransitions", "Trying to finish a non-running transition. Either remote crashed or  a handler didn't properly deal with a merge. " + activeTransition, new RuntimeException());
            return;
        }
        track.mActiveTransition = null;
        for (int i = 0; i < this.mObservers.size(); i++) {
            ((TransitionObserver) this.mObservers.get(i)).onTransitionFinished(activeTransition.mToken, activeTransition.mAborted);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5216177164274512366L, 3, Boolean.valueOf(activeTransition.mAborted), String.valueOf(activeTransition));
        }
        SurfaceControl.Transaction transaction = activeTransition.mStartT;
        if (transaction != null) {
            transaction.clear();
        }
        SurfaceControl.Transaction transaction2 = activeTransition.mFinishT;
        if (activeTransition.mMerged != null) {
            for (int i2 = 0; i2 < activeTransition.mMerged.size(); i2++) {
                ActiveTransition activeTransition2 = (ActiveTransition) activeTransition.mMerged.get(i2);
                SurfaceControl.Transaction transaction3 = activeTransition2.mStartT;
                if (transaction3 != null) {
                    if (transaction2 == null) {
                        transaction2 = transaction3;
                    } else {
                        transaction2.merge(transaction3);
                    }
                }
                SurfaceControl.Transaction transaction4 = activeTransition2.mFinishT;
                if (transaction4 != null) {
                    if (transaction2 == null) {
                        transaction2 = transaction4;
                    } else {
                        transaction2.merge(transaction4);
                    }
                }
            }
        }
        if (transaction2 != null) {
            transaction2.apply();
        }
        TransitionInfo transitionInfo = activeTransition.mInfo;
        if (transitionInfo != null) {
            transitionInfo.releaseAnimSurfaces();
        }
        IBinder iBinder2 = activeTransition.mToken;
        ShellTaskOrganizer shellTaskOrganizer = this.mOrganizer;
        shellTaskOrganizer.finishTransition(iBinder2, windowContainerTransaction);
        if (activeTransition.mMerged != null) {
            for (int i3 = 0; i3 < activeTransition.mMerged.size(); i3++) {
                ActiveTransition activeTransition3 = (ActiveTransition) activeTransition.mMerged.get(i3);
                shellTaskOrganizer.finishTransition(activeTransition3.mToken, (WindowContainerTransaction) null);
                TransitionInfo transitionInfo2 = activeTransition3.mInfo;
                if (transitionInfo2 != null) {
                    transitionInfo2.releaseAnimSurfaces();
                }
                this.mKnownTransitions.remove(activeTransition3.mToken);
            }
            activeTransition.mMerged.clear();
        }
        this.mKnownTransitions.remove(iBinder);
        processReadyQueue(track);
    }

    public final void onMerged(IBinder iBinder, IBinder iBinder2) {
        int indexOf;
        ((HandlerExecutor) this.mMainExecutor).assertCurrentThread();
        ActiveTransition activeTransition = (ActiveTransition) this.mKnownTransitions.get(iBinder);
        if (activeTransition == null) {
            Log.e("ShellTransitions", "Merging into a non-existent transition: " + iBinder);
            return;
        }
        ActiveTransition activeTransition2 = (ActiveTransition) this.mKnownTransitions.get(iBinder2);
        if (activeTransition2 == null) {
            Log.e("ShellTransitions", "Merging a non-existent transition: " + iBinder2);
            return;
        }
        if (activeTransition.getTrack() != activeTransition2.getTrack()) {
            throw new IllegalStateException("Can't merge across tracks: " + activeTransition2 + " into " + activeTransition);
        }
        Track track = (Track) this.mTracks.get(activeTransition.getTrack());
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7347095270847821413L, 0, String.valueOf(activeTransition2), String.valueOf(activeTransition));
        }
        if (track.mReadyTransitions.isEmpty() || track.mReadyTransitions.get(0) != activeTransition2) {
            Log.e("ShellTransitions", "Merged transition out-of-order? " + activeTransition2);
            indexOf = track.mReadyTransitions.indexOf(activeTransition2);
            if (indexOf < 0) {
                Log.e("ShellTransitions", "Merged a transition that is no-longer queued? " + activeTransition2);
                return;
            }
        } else {
            indexOf = 0;
        }
        track.mReadyTransitions.remove(indexOf);
        if (activeTransition.mMerged == null) {
            activeTransition.mMerged = new ArrayList();
        }
        activeTransition.mMerged.add(activeTransition2);
        TransitionHandler transitionHandler = activeTransition2.mHandler;
        if (transitionHandler != null && !activeTransition2.mAborted) {
            transitionHandler.onTransitionConsumed(activeTransition2.mToken, false, activeTransition2.mFinishT);
        }
        for (int i = 0; i < this.mObservers.size(); i++) {
            ((TransitionObserver) this.mObservers.get(i)).onTransitionMerged(activeTransition2.mToken, activeTransition.mToken);
        }
        int debugId = activeTransition2.mInfo.getDebugId();
        int debugId2 = activeTransition.mInfo.getDebugId();
        LegacyTransitionTracer legacyTransitionTracer = this.mTransitionTracer;
        legacyTransitionTracer.getClass();
        Transition transition = new Transition();
        transition.id = debugId;
        transition.mergeTimeNs = SystemClock.elapsedRealtimeNanos();
        transition.mergeTarget = debugId2;
        legacyTransitionTracer.mTraceBuffer.add(transition);
        processReadyQueue(track);
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final boolean onShellCommand(PrintWriter printWriter, String[] strArr) {
        String str = strArr[0];
        if (str.hashCode() == -1067396926 && str.equals("tracing")) {
            this.mTransitionTracer.onShellCommand(printWriter, (String[]) Arrays.copyOfRange(strArr, 1, strArr.length));
            return true;
        }
        printWriter.println("Invalid command: " + strArr[0]);
        printShellCommandHelp(printWriter, "");
        return false;
    }

    public void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        transitionInfo.setUnreleasedWarningCallSiteForAllSurfaces("Transitions.onTransitionReady");
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 1354476626241269833L, 1, Long.valueOf(transitionInfo.getDebugId()), String.valueOf(iBinder), String.valueOf(transitionInfo));
        }
        ArrayList arrayList = this.mPendingTransitions;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                size = -1;
                break;
            } else if (((ActiveTransition) arrayList.get(size)).mToken == iBinder) {
                break;
            } else {
                size--;
            }
        }
        if (size < 0) {
            ActiveTransition activeTransition = (ActiveTransition) this.mKnownTransitions.get(iBinder);
            if (activeTransition != null) {
                Log.e("ShellTransitions", "Got duplicate transitionReady for " + iBinder);
                transaction.apply();
                SurfaceControl.Transaction transaction3 = activeTransition.mFinishT;
                if (transaction3 != null) {
                    transaction3.merge(transaction2);
                    return;
                } else {
                    activeTransition.mFinishT = transaction2;
                    return;
                }
            }
            Log.wtf("ShellTransitions", "Got transitionReady for non-pending transition " + iBinder + ". expecting one of " + Arrays.toString(this.mPendingTransitions.stream().map(new Transitions$$ExternalSyntheticLambda0()).toArray()));
            ActiveTransition activeTransition2 = new ActiveTransition(iBinder);
            this.mKnownTransitions.put(iBinder, activeTransition2);
            this.mPendingTransitions.add(activeTransition2);
            size = this.mPendingTransitions.size() + (-1);
        }
        ActiveTransition activeTransition3 = (ActiveTransition) this.mPendingTransitions.remove(size);
        activeTransition3.mInfo = transitionInfo;
        activeTransition3.mStartT = transaction;
        activeTransition3.mFinishT = transaction2;
        if (size > 0) {
            Log.i("ShellTransitions", "Transition might be ready out-of-order " + size + " for " + activeTransition3 + ". This is ok if it's on a different track.");
        }
        if (this.mReadyDuringSync.isEmpty()) {
            dispatchReady(activeTransition3);
        } else {
            this.mReadyDuringSync.add(activeTransition3);
        }
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println(str.concat("tracing"));
        this.mTransitionTracer.printShellCommandHelp(printWriter, str.concat("  "));
    }

    public final void processReadyQueue(Track track) {
        int i = 0;
        if (track.mReadyTransitions.isEmpty()) {
            if (track.mActiveTransition == null) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -1071245984949193362L, 1, Long.valueOf(this.mTracks.indexOf(track)));
                }
                for (int i2 = 0; i2 < this.mTracks.size(); i2++) {
                    Track track2 = (Track) this.mTracks.get(i2);
                    if (track2.mActiveTransition != null || !track2.mReadyTransitions.isEmpty()) {
                        return;
                    }
                }
                if (!this.mReadyDuringSync.isEmpty()) {
                    while (!this.mReadyDuringSync.isEmpty() && dispatchReady((ActiveTransition) this.mReadyDuringSync.remove(0))) {
                    }
                    return;
                } else {
                    if (this.mPendingTransitions.isEmpty()) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5665710760068723116L, 0, null);
                        }
                        this.mKnownTransitions.clear();
                        while (i < this.mRunWhenIdleQueue.size()) {
                            ((Runnable) this.mRunWhenIdleQueue.get(i)).run();
                            i++;
                        }
                        this.mRunWhenIdleQueue.clear();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        ActiveTransition activeTransition = (ActiveTransition) track.mReadyTransitions.get(0);
        ActiveTransition activeTransition2 = track.mActiveTransition;
        LegacyTransitionTracer legacyTransitionTracer = this.mTransitionTracer;
        if (activeTransition2 != null) {
            final IBinder iBinder = activeTransition2.mToken;
            final IBinder iBinder2 = activeTransition.mToken;
            if (activeTransition.mAborted) {
                onMerged(iBinder, iBinder2);
                return;
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 8857561493841984889L, 0, String.valueOf(activeTransition), String.valueOf(activeTransition2));
            }
            int debugId = activeTransition.mInfo.getDebugId();
            int debugId2 = activeTransition2.mInfo.getDebugId();
            legacyTransitionTracer.getClass();
            Transition transition = new Transition();
            transition.id = debugId;
            transition.mergeRequestTimeNs = SystemClock.elapsedRealtimeNanos();
            transition.mergeTarget = debugId2;
            legacyTransitionTracer.mTraceBuffer.add(transition);
            activeTransition2.mHandler.mergeAnimation(activeTransition.mToken, activeTransition.mInfo, activeTransition.mStartT, activeTransition2.mToken, new TransitionFinishCallback() { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda2
                @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
                public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
                    Transitions.this.onMerged(iBinder, iBinder2);
                }
            });
            return;
        }
        track.mReadyTransitions.remove(0);
        track.mActiveTransition = activeTransition;
        if (activeTransition.mAborted) {
            SurfaceControl.Transaction transaction = activeTransition.mStartT;
            if (transaction != null) {
                transaction.apply();
            }
            onFinish(activeTransition.mToken, null);
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7933815964243868580L, 0, String.valueOf(activeTransition));
        }
        final IBinder iBinder3 = activeTransition.mToken;
        for (int i3 = 0; i3 < this.mObservers.size(); i3++) {
            ((TransitionObserver) this.mObservers.get(i3)).onTransitionStarting(iBinder3);
        }
        TransitionInfo transitionInfo = activeTransition.mInfo;
        SurfaceControl.Transaction transaction2 = activeTransition.mStartT;
        int type = transitionInfo.getType();
        for (int i4 = 0; i4 < transitionInfo.getRootCount(); i4++) {
            transaction2.show(transitionInfo.getRoot(i4).getLeash());
        }
        int size = transitionInfo.getChanges().size();
        int i5 = size - 1;
        while (i5 >= 0) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i5);
            SurfaceControl leash = change.getLeash();
            if (TransitionInfo.isIndependent(change, transitionInfo)) {
                int i6 = change.getParent() != null ? 1 : i;
                TransitionInfo.Root root = transitionInfo.getRoot(TransitionUtil.rootIndexFor(change, transitionInfo));
                if (i6 == 0) {
                    transaction2.reparent(leash, root.getLeash());
                    transaction2.setPosition(leash, change.getStartAbsBounds().left - root.getOffset().x, change.getStartAbsBounds().top - root.getOffset().y);
                }
                transaction2.setLayer(leash, calculateAnimLayer(change, i5, size, type));
            }
            i5--;
            i = 0;
        }
        TransitionHandler transitionHandler = activeTransition.mHandler;
        if (transitionHandler != null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5057565356781286066L, 0, String.valueOf(transitionHandler));
            }
            final int i7 = 0;
            if (activeTransition.mHandler.startAnimation(iBinder3, activeTransition.mInfo, activeTransition.mStartT, activeTransition.mFinishT, new TransitionFinishCallback(this) { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda7
                public final /* synthetic */ Transitions f$0;

                {
                    this.f$0 = this;
                }

                @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
                public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
                    switch (i7) {
                        case 0:
                            this.f$0.onFinish(iBinder3, windowContainerTransaction);
                            break;
                        default:
                            this.f$0.onFinish(iBinder3, windowContainerTransaction);
                            break;
                    }
                }
            })) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 2542068187878801251L, 0, null);
                }
                legacyTransitionTracer.logDispatched(activeTransition.mInfo.getDebugId(), activeTransition.mHandler);
                processReadyQueue(track);
            }
        }
        final int i8 = 1;
        activeTransition.mHandler = dispatchTransition(iBinder3, activeTransition.mInfo, activeTransition.mStartT, activeTransition.mFinishT, new TransitionFinishCallback(this) { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda7
            public final /* synthetic */ Transitions f$0;

            {
                this.f$0 = this;
            }

            @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
            public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
                switch (i8) {
                    case 0:
                        this.f$0.onFinish(iBinder3, windowContainerTransaction);
                        break;
                    default:
                        this.f$0.onFinish(iBinder3, windowContainerTransaction);
                        break;
                }
            }
        }, activeTransition.mHandler);
        processReadyQueue(track);
    }

    public final void registerObserver(TransitionObserver transitionObserver) {
        this.mObservers.add(transitionObserver);
    }

    public void replaceDefaultHandlerForTest(TransitionHandler transitionHandler) {
        this.mHandlers.set(0, transitionHandler);
    }

    public final void runOnIdle(Runnable runnable) {
        int i;
        if (this.mPendingTransitions.isEmpty() && this.mReadyDuringSync.isEmpty()) {
            while (i < this.mTracks.size()) {
                Track track = (Track) this.mTracks.get(i);
                i = (track.mActiveTransition == null && track.mReadyTransitions.isEmpty()) ? i + 1 : 0;
            }
            runnable.run();
            return;
        }
        this.mRunWhenIdleQueue.add(runnable);
    }

    public final IBinder startTransition(int i, WindowContainerTransaction windowContainerTransaction, TransitionHandler transitionHandler) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 4018922785617165231L, 0, String.valueOf(WindowManager.transitTypeToString(i)), String.valueOf(windowContainerTransaction), String.valueOf(transitionHandler));
        }
        IBinder startNewTransition = this.mOrganizer.startNewTransition(i, windowContainerTransaction);
        ActiveTransition activeTransition = new ActiveTransition(startNewTransition);
        activeTransition.mHandler = transitionHandler;
        this.mKnownTransitions.put(startNewTransition, activeTransition);
        this.mPendingTransitions.add(activeTransition);
        return startNewTransition;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IShellTransitionsImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public Transitions mTransitions;

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            Transitions transitions = this.mTransitions;
            HomeTransitionObserver homeTransitionObserver = transitions.mHomeTransitionObserver;
            homeTransitionObserver.getClass();
            transitions.mObservers.remove(homeTransitionObserver);
            SingleInstanceRemoteListener singleInstanceRemoteListener = homeTransitionObserver.mListener;
            if (singleInstanceRemoteListener != null) {
                singleInstanceRemoteListener.unregister();
            }
            this.mTransitions = null;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            final IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.shared.IShellTransitions");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.shared.IShellTransitions");
                return true;
            }
            switch (i) {
                case 2:
                    final TransitionFilter transitionFilter = (TransitionFilter) parcel.readTypedObject(TransitionFilter.CREATOR);
                    final RemoteTransition remoteTransition = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    parcel.enforceNoDataAvail();
                    final int i3 = 0;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mTransitions, "registerRemote", new Consumer() { // from class: com.android.wm.shell.transition.Transitions$IShellTransitionsImpl$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            switch (i3) {
                                case 0:
                                    TransitionFilter transitionFilter2 = transitionFilter;
                                    RemoteTransition remoteTransition2 = remoteTransition;
                                    RemoteTransitionHandler remoteTransitionHandler = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler.getClass();
                                    remoteTransitionHandler.handleDeath(remoteTransition2.asBinder(), null);
                                    remoteTransitionHandler.mFilters.add(new Pair(transitionFilter2, remoteTransition2));
                                    break;
                                default:
                                    TransitionFilter transitionFilter3 = transitionFilter;
                                    RemoteTransition remoteTransition3 = remoteTransition;
                                    RemoteTransitionHandler remoteTransitionHandler2 = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler2.getClass();
                                    remoteTransitionHandler2.handleDeath(remoteTransition3.asBinder(), null);
                                    remoteTransitionHandler2.mTakeoverFilters.add(new Pair(transitionFilter3, remoteTransition3));
                                    break;
                            }
                        }
                    }, false);
                    return true;
                case 3:
                    final RemoteTransition remoteTransition2 = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    parcel.enforceNoDataAvail();
                    final int i4 = 0;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mTransitions, "unregisterRemote", new Consumer() { // from class: com.android.wm.shell.transition.Transitions$IShellTransitionsImpl$$ExternalSyntheticLambda1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i5 = i4;
                            Object obj2 = remoteTransition2;
                            switch (i5) {
                                case 0:
                                    RemoteTransition remoteTransition3 = (RemoteTransition) obj2;
                                    RemoteTransitionHandler remoteTransitionHandler = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler.getClass();
                                    boolean z = false;
                                    for (ArrayList arrayList : Arrays.asList(remoteTransitionHandler.mFilters, remoteTransitionHandler.mTakeoverFilters)) {
                                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                                            if (((RemoteTransition) ((Pair) arrayList.get(size)).second).asBinder().equals(remoteTransition3.asBinder())) {
                                                arrayList.remove(size);
                                                z = true;
                                            }
                                        }
                                    }
                                    if (z) {
                                        remoteTransitionHandler.unhandleDeath(remoteTransition3.asBinder(), null);
                                        break;
                                    }
                                    break;
                                case 1:
                                    ((SurfaceControl[]) obj2)[0] = ((Transitions) obj).mOrganizer.mHomeTaskOverlayContainer;
                                    break;
                                default:
                                    IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy2 = (IHomeTransitionListener$Stub$Proxy) obj2;
                                    final Transitions transitions = (Transitions) obj;
                                    final HomeTransitionObserver homeTransitionObserver = transitions.mHomeTransitionObserver;
                                    if (homeTransitionObserver.mListener == null) {
                                        final int i6 = 0;
                                        final int i7 = 1;
                                        homeTransitionObserver.mListener = new SingleInstanceRemoteListener(homeTransitionObserver, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i6) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        }, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i7) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        });
                                    }
                                    if (iHomeTransitionListener$Stub$Proxy2 != null) {
                                        homeTransitionObserver.mListener.register(iHomeTransitionListener$Stub$Proxy2);
                                        break;
                                    } else {
                                        homeTransitionObserver.mListener.unregister();
                                        break;
                                    }
                            }
                        }
                    }, false);
                    return true;
                case 4:
                    IBinder defaultApplyToken = SurfaceControl.Transaction.getDefaultApplyToken();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(defaultApplyToken);
                    return true;
                case 5:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        iHomeTransitionListener$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.wm.shell.shared.IHomeTransitionListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IHomeTransitionListener$Stub$Proxy)) {
                            IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy2 = new IHomeTransitionListener$Stub$Proxy();
                            iHomeTransitionListener$Stub$Proxy2.mRemote = readStrongBinder;
                            iHomeTransitionListener$Stub$Proxy = iHomeTransitionListener$Stub$Proxy2;
                        } else {
                            iHomeTransitionListener$Stub$Proxy = (IHomeTransitionListener$Stub$Proxy) queryLocalInterface;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    final int i5 = 2;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mTransitions, "setHomeTransitionListener", new Consumer() { // from class: com.android.wm.shell.transition.Transitions$IShellTransitionsImpl$$ExternalSyntheticLambda1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i52 = i5;
                            Object obj2 = iHomeTransitionListener$Stub$Proxy;
                            switch (i52) {
                                case 0:
                                    RemoteTransition remoteTransition3 = (RemoteTransition) obj2;
                                    RemoteTransitionHandler remoteTransitionHandler = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler.getClass();
                                    boolean z = false;
                                    for (ArrayList arrayList : Arrays.asList(remoteTransitionHandler.mFilters, remoteTransitionHandler.mTakeoverFilters)) {
                                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                                            if (((RemoteTransition) ((Pair) arrayList.get(size)).second).asBinder().equals(remoteTransition3.asBinder())) {
                                                arrayList.remove(size);
                                                z = true;
                                            }
                                        }
                                    }
                                    if (z) {
                                        remoteTransitionHandler.unhandleDeath(remoteTransition3.asBinder(), null);
                                        break;
                                    }
                                    break;
                                case 1:
                                    ((SurfaceControl[]) obj2)[0] = ((Transitions) obj).mOrganizer.mHomeTaskOverlayContainer;
                                    break;
                                default:
                                    IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy22 = (IHomeTransitionListener$Stub$Proxy) obj2;
                                    final Transitions transitions = (Transitions) obj;
                                    final HomeTransitionObserver homeTransitionObserver = transitions.mHomeTransitionObserver;
                                    if (homeTransitionObserver.mListener == null) {
                                        final int i6 = 0;
                                        final int i7 = 1;
                                        homeTransitionObserver.mListener = new SingleInstanceRemoteListener(homeTransitionObserver, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i6) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        }, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i7) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        });
                                    }
                                    if (iHomeTransitionListener$Stub$Proxy22 != null) {
                                        homeTransitionObserver.mListener.register(iHomeTransitionListener$Stub$Proxy22);
                                        break;
                                    } else {
                                        homeTransitionObserver.mListener.unregister();
                                        break;
                                    }
                            }
                        }
                    }, false);
                    return true;
                case 6:
                    final SurfaceControl[] surfaceControlArr = new SurfaceControl[1];
                    final int i6 = 1;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mTransitions, "getHomeTaskOverlayContainer", new Consumer() { // from class: com.android.wm.shell.transition.Transitions$IShellTransitionsImpl$$ExternalSyntheticLambda1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i52 = i6;
                            Object obj2 = surfaceControlArr;
                            switch (i52) {
                                case 0:
                                    RemoteTransition remoteTransition3 = (RemoteTransition) obj2;
                                    RemoteTransitionHandler remoteTransitionHandler = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler.getClass();
                                    boolean z = false;
                                    for (ArrayList arrayList : Arrays.asList(remoteTransitionHandler.mFilters, remoteTransitionHandler.mTakeoverFilters)) {
                                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                                            if (((RemoteTransition) ((Pair) arrayList.get(size)).second).asBinder().equals(remoteTransition3.asBinder())) {
                                                arrayList.remove(size);
                                                z = true;
                                            }
                                        }
                                    }
                                    if (z) {
                                        remoteTransitionHandler.unhandleDeath(remoteTransition3.asBinder(), null);
                                        break;
                                    }
                                    break;
                                case 1:
                                    ((SurfaceControl[]) obj2)[0] = ((Transitions) obj).mOrganizer.mHomeTaskOverlayContainer;
                                    break;
                                default:
                                    IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy22 = (IHomeTransitionListener$Stub$Proxy) obj2;
                                    final Transitions transitions = (Transitions) obj;
                                    final HomeTransitionObserver homeTransitionObserver = transitions.mHomeTransitionObserver;
                                    if (homeTransitionObserver.mListener == null) {
                                        final int i62 = 0;
                                        final int i7 = 1;
                                        homeTransitionObserver.mListener = new SingleInstanceRemoteListener(homeTransitionObserver, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i62) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        }, new Consumer() { // from class: com.android.wm.shell.transition.HomeTransitionObserver$$ExternalSyntheticLambda0
                                            @Override // java.util.function.Consumer
                                            public final void accept(Object obj3) {
                                                switch (i7) {
                                                    case 0:
                                                        HomeTransitionObserver homeTransitionObserver2 = homeTransitionObserver;
                                                        Transitions transitions2 = transitions;
                                                        homeTransitionObserver2.getClass();
                                                        transitions2.registerObserver(homeTransitionObserver2);
                                                        break;
                                                    default:
                                                        HomeTransitionObserver homeTransitionObserver3 = homeTransitionObserver;
                                                        Transitions transitions3 = transitions;
                                                        homeTransitionObserver3.getClass();
                                                        transitions3.mObservers.remove(homeTransitionObserver3);
                                                        break;
                                                }
                                            }
                                        });
                                    }
                                    if (iHomeTransitionListener$Stub$Proxy22 != null) {
                                        homeTransitionObserver.mListener.register(iHomeTransitionListener$Stub$Proxy22);
                                        break;
                                    } else {
                                        homeTransitionObserver.mListener.unregister();
                                        break;
                                    }
                            }
                        }
                    }, true);
                    SurfaceControl surfaceControl = new SurfaceControl(surfaceControlArr[0], "Transitions.HomeOverlay");
                    parcel2.writeNoException();
                    parcel2.writeTypedObject(surfaceControl, 1);
                    return true;
                case 7:
                    final TransitionFilter transitionFilter2 = (TransitionFilter) parcel.readTypedObject(TransitionFilter.CREATOR);
                    final RemoteTransition remoteTransition3 = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    parcel.enforceNoDataAvail();
                    final int i7 = 1;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mTransitions, "registerRemoteForTakeover", new Consumer() { // from class: com.android.wm.shell.transition.Transitions$IShellTransitionsImpl$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            switch (i7) {
                                case 0:
                                    TransitionFilter transitionFilter22 = transitionFilter2;
                                    RemoteTransition remoteTransition22 = remoteTransition3;
                                    RemoteTransitionHandler remoteTransitionHandler = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler.getClass();
                                    remoteTransitionHandler.handleDeath(remoteTransition22.asBinder(), null);
                                    remoteTransitionHandler.mFilters.add(new Pair(transitionFilter22, remoteTransition22));
                                    break;
                                default:
                                    TransitionFilter transitionFilter3 = transitionFilter2;
                                    RemoteTransition remoteTransition32 = remoteTransition3;
                                    RemoteTransitionHandler remoteTransitionHandler2 = ((Transitions) obj).mRemoteTransitionHandler;
                                    remoteTransitionHandler2.getClass();
                                    remoteTransitionHandler2.handleDeath(remoteTransition32.asBinder(), null);
                                    remoteTransitionHandler2.mTakeoverFilters.add(new Pair(transitionFilter3, remoteTransition32));
                                    break;
                            }
                        }
                    }, false);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransitionHandler {
        WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo);

        boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, TransitionFinishCallback transitionFinishCallback);

        default void setAnimScaleSetting(float f) {
        }

        default void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        }

        default void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, TransitionFinishCallback transitionFinishCallback) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransitionObserver {
        void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2);

        default void onTransitionStarting(IBinder iBinder) {
        }

        default void onTransitionFinished(IBinder iBinder, boolean z) {
        }

        default void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
        }
    }
}
