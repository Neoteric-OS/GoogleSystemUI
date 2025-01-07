package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.VisibilityLocationProvider;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager;
import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ListenerSet;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VisualStabilityCoordinator implements Coordinator, Dumpable {
    protected static final long ALLOW_SECTION_CHANGE_TIMEOUT = 500;
    public final CommunalSceneInteractor mCommunalSceneInteractor;
    public final DelayableExecutor mDelayableExecutor;
    public boolean mFullyDozed;
    public final HeadsUpManager mHeadsUpManager;
    public final JavaAdapter mJavaAdapter;
    public final VisualStabilityCoordinatorLogger mLogger;
    public boolean mNotifPanelCollapsing;
    public boolean mNotifPanelLaunchingActivity;
    public boolean mPanelExpanded;
    public boolean mPipelineRunAllowed;
    public boolean mPulsing;
    public boolean mReorderingAllowed;
    public final ShadeAnimationInteractor mShadeAnimationInteractor;
    public final ShadeInteractor mShadeInteractor;
    public final StatusBarStateController mStatusBarStateController;
    public final VisibilityLocationProvider mVisibilityLocationProvider;
    public final VisualStabilityProvider mVisualStabilityProvider;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public boolean mSleepy = true;
    public boolean mCommunalShowing = false;
    public boolean mIsSuppressingPipelineRun = false;
    public boolean mIsSuppressingGroupChange = false;
    public final Set mEntriesWithSuppressedSectionChange = new HashSet();
    public boolean mIsSuppressingEntryReorder = false;
    public final Map mEntriesThatCanChangeSection = new HashMap();
    public final AnonymousClass1 mNotifStabilityManager = new AnonymousClass1();
    public final AnonymousClass2 mStatusBarStateControllerListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator.2
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozeAmountChanged(float f, float f2) {
            boolean z = f == 1.0f;
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mFullyDozed = z;
            visualStabilityCoordinator.updateAllowedStates("fullyDozed", z);
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onExpandedChanged(boolean z) {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mPanelExpanded = z;
            visualStabilityCoordinator.updateAllowedStates("panelExpanded", z);
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onPulsingChanged(boolean z) {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mPulsing = z;
            visualStabilityCoordinator.updateAllowedStates("pulsing", z);
        }
    };
    public final AnonymousClass3 mWakefulnessObserver = new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator.3
        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mSleepy = true;
            visualStabilityCoordinator.updateAllowedStates("sleepy", true);
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedWakingUp() {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mSleepy = false;
            visualStabilityCoordinator.updateAllowedStates("sleepy", false);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$1, reason: invalid class name */
    public final class AnonymousClass1 extends NotifStabilityManager {
        public AnonymousClass1() {
            super("VisualStabilityCoordinator");
        }

        public final boolean canMoveForHeadsUp(NotificationEntry notificationEntry) {
            if (notificationEntry == null) {
                return false;
            }
            if (((BaseHeadsUpManager) VisualStabilityCoordinator.this.mHeadsUpManager).isHeadsUpEntry(notificationEntry.mKey)) {
                return !r3.mVisibilityLocationProvider.isInVisibleLocation(notificationEntry);
            }
            return false;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final boolean isEntryReorderingAllowed(ListEntry listEntry) {
            return VisualStabilityCoordinator.this.mReorderingAllowed || canMoveForHeadsUp(listEntry.getRepresentativeEntry());
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final boolean isEveryChangeAllowed() {
            return VisualStabilityCoordinator.this.mReorderingAllowed;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final boolean isGroupChangeAllowed(NotificationEntry notificationEntry) {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            boolean z = visualStabilityCoordinator.mReorderingAllowed || canMoveForHeadsUp(notificationEntry);
            visualStabilityCoordinator.mIsSuppressingGroupChange |= !z;
            return z;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final boolean isGroupPruneAllowed() {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            boolean z = visualStabilityCoordinator.mReorderingAllowed;
            visualStabilityCoordinator.mIsSuppressingGroupChange |= !z;
            return z;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final boolean isSectionChangeAllowed(NotificationEntry notificationEntry) {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            boolean z = visualStabilityCoordinator.mReorderingAllowed || canMoveForHeadsUp(notificationEntry) || visualStabilityCoordinator.mEntriesThatCanChangeSection.containsKey(notificationEntry.mKey);
            if (!z) {
                visualStabilityCoordinator.mEntriesWithSuppressedSectionChange.add(notificationEntry.mKey);
            }
            return z;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final void onBeginRun$1() {
            VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
            visualStabilityCoordinator.mIsSuppressingPipelineRun = false;
            visualStabilityCoordinator.mIsSuppressingGroupChange = false;
            visualStabilityCoordinator.mEntriesWithSuppressedSectionChange.clear();
            visualStabilityCoordinator.mIsSuppressingEntryReorder = false;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager
        public final void onEntryReorderSuppressed() {
            VisualStabilityCoordinator.this.mIsSuppressingEntryReorder = true;
        }
    }

    /* JADX WARN: Type inference failed for: r6v5, types: [com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$2] */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$3] */
    public VisualStabilityCoordinator(DelayableExecutor delayableExecutor, DumpManager dumpManager, HeadsUpManager headsUpManager, ShadeAnimationInteractor shadeAnimationInteractor, JavaAdapter javaAdapter, SeenNotificationsInteractor seenNotificationsInteractor, StatusBarStateController statusBarStateController, VisibilityLocationProvider visibilityLocationProvider, VisualStabilityProvider visualStabilityProvider, WakefulnessLifecycle wakefulnessLifecycle, CommunalSceneInteractor communalSceneInteractor, ShadeInteractor shadeInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, VisualStabilityCoordinatorLogger visualStabilityCoordinatorLogger) {
        this.mHeadsUpManager = headsUpManager;
        this.mShadeAnimationInteractor = shadeAnimationInteractor;
        this.mJavaAdapter = javaAdapter;
        this.mVisibilityLocationProvider = visibilityLocationProvider;
        this.mVisualStabilityProvider = visualStabilityProvider;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mStatusBarStateController = statusBarStateController;
        this.mDelayableExecutor = delayableExecutor;
        this.mCommunalSceneInteractor = communalSceneInteractor;
        this.mShadeInteractor = shadeInteractor;
        this.mLogger = visualStabilityCoordinatorLogger;
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        WakefulnessLifecycle wakefulnessLifecycle = this.mWakefulnessLifecycle;
        wakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
        this.mSleepy = wakefulnessLifecycle.mWakefulness == 0;
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        this.mFullyDozed = statusBarStateController.getDozeAmount() == 1.0f;
        statusBarStateController.addCallback(this.mStatusBarStateControllerListener);
        this.mPulsing = statusBarStateController.isPulsing();
        ShadeAnimationInteractor shadeAnimationInteractor = this.mShadeAnimationInteractor;
        StateFlow isAnyCloseAnimationRunning = shadeAnimationInteractor.isAnyCloseAnimationRunning();
        final int i = 0;
        Consumer consumer = new Consumer(this) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$$ExternalSyntheticLambda1
            public final /* synthetic */ VisualStabilityCoordinator f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                VisualStabilityCoordinator visualStabilityCoordinator = this.f$0;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                switch (i2) {
                    case 0:
                        visualStabilityCoordinator.mNotifPanelCollapsing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelCollapsing", booleanValue);
                        break;
                    case 1:
                        visualStabilityCoordinator.mNotifPanelLaunchingActivity = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelLaunchingActivity", booleanValue);
                        break;
                    default:
                        visualStabilityCoordinator.mCommunalShowing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("communalShowing", booleanValue);
                        break;
                }
            }
        };
        JavaAdapter javaAdapter = this.mJavaAdapter;
        javaAdapter.alwaysCollectFlow(isAnyCloseAnimationRunning, consumer);
        final int i2 = 1;
        javaAdapter.alwaysCollectFlow(shadeAnimationInteractor.isLaunchingActivity, new Consumer(this) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$$ExternalSyntheticLambda1
            public final /* synthetic */ VisualStabilityCoordinator f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                VisualStabilityCoordinator visualStabilityCoordinator = this.f$0;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                switch (i22) {
                    case 0:
                        visualStabilityCoordinator.mNotifPanelCollapsing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelCollapsing", booleanValue);
                        break;
                    case 1:
                        visualStabilityCoordinator.mNotifPanelLaunchingActivity = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelLaunchingActivity", booleanValue);
                        break;
                    default:
                        visualStabilityCoordinator.mCommunalShowing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("communalShowing", booleanValue);
                        break;
                }
            }
        });
        final int i3 = 2;
        javaAdapter.alwaysCollectFlow(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{this.mCommunalSceneInteractor.isIdleOnCommunal, new BooleanFlowOperators$not$$inlined$map$1(0, ((ShadeInteractorImpl) this.mShadeInteractor).isAnyFullyExpanded)})).toArray(new Flow[0]))), new Consumer(this) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$$ExternalSyntheticLambda1
            public final /* synthetic */ VisualStabilityCoordinator f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i3;
                VisualStabilityCoordinator visualStabilityCoordinator = this.f$0;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                switch (i22) {
                    case 0:
                        visualStabilityCoordinator.mNotifPanelCollapsing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelCollapsing", booleanValue);
                        break;
                    case 1:
                        visualStabilityCoordinator.mNotifPanelLaunchingActivity = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("notifPanelLaunchingActivity", booleanValue);
                        break;
                    default:
                        visualStabilityCoordinator.mCommunalShowing = booleanValue;
                        visualStabilityCoordinator.updateAllowedStates("communalShowing", booleanValue);
                        break;
                }
            }
        });
        ShadeListBuilder shadeListBuilder = notifPipeline.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        AnonymousClass1 anonymousClass1 = shadeListBuilder.mNotifStabilityManager;
        AnonymousClass1 anonymousClass12 = this.mNotifStabilityManager;
        if (anonymousClass1 == null) {
            shadeListBuilder.mNotifStabilityManager = anonymousClass12;
            anonymousClass12.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 1);
        } else {
            throw new IllegalStateException("Attempting to set the NotifStabilityManager more than once. There should only be one visual stability manager. Manager is being set by " + shadeListBuilder.mNotifStabilityManager.mName + " and " + anonymousClass12.mName);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("pipelineRunAllowed: "), this.mPipelineRunAllowed, printWriter, "  notifPanelCollapsing: "), this.mNotifPanelCollapsing, printWriter, "  launchingNotifActivity: "), this.mNotifPanelLaunchingActivity, printWriter, "reorderingAllowed: "), this.mReorderingAllowed, printWriter, "  sleepy: "), this.mSleepy, printWriter, "  fullyDozed: "), this.mFullyDozed, printWriter, "  panelExpanded: "), this.mPanelExpanded, printWriter, "  pulsing: "), this.mPulsing, printWriter, "  communalShowing: "), this.mCommunalShowing, printWriter, "isSuppressingPipelineRun: "), this.mIsSuppressingPipelineRun, printWriter, "isSuppressingGroupChange: "), this.mIsSuppressingGroupChange, printWriter, "isSuppressingEntryReorder: "), this.mIsSuppressingEntryReorder, printWriter, "entriesWithSuppressedSectionChange: ");
        m.append(((HashSet) this.mEntriesWithSuppressedSectionChange).size());
        printWriter.println(m.toString());
        Iterator it = ((HashSet) this.mEntriesWithSuppressedSectionChange).iterator();
        while (it.hasNext()) {
            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "  ", (String) it.next());
        }
        printWriter.println("entriesThatCanChangeSection: " + this.mEntriesThatCanChangeSection.size());
        Iterator it2 = this.mEntriesThatCanChangeSection.keySet().iterator();
        while (it2.hasNext()) {
            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "  ", (String) it2.next());
        }
    }

    public final void updateAllowedStates(String str, boolean z) {
        boolean z2 = this.mPipelineRunAllowed;
        boolean z3 = this.mReorderingAllowed;
        boolean z4 = false;
        boolean z5 = !(this.mNotifPanelCollapsing || this.mNotifPanelLaunchingActivity);
        this.mPipelineRunAllowed = z5;
        boolean z6 = this.mFullyDozed && this.mSleepy;
        boolean z7 = this.mPanelExpanded;
        if ((z6 || !z7 || this.mCommunalShowing) && !this.mPulsing) {
            z4 = true;
        }
        this.mReorderingAllowed = z4;
        if (z2 != z5 || z3 != z4) {
            VisualStabilityCoordinatorLogger visualStabilityCoordinatorLogger = this.mLogger;
            visualStabilityCoordinatorLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            VisualStabilityCoordinatorLogger$logAllowancesChanged$2 visualStabilityCoordinatorLogger$logAllowancesChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinatorLogger$logAllowancesChanged$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    boolean bool1 = logMessage.getBool1();
                    boolean bool2 = logMessage.getBool2();
                    boolean bool3 = logMessage.getBool3();
                    boolean bool4 = logMessage.getBool4();
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("stability allowances changed: pipelineRunAllowed ", "->", " reorderingAllowed ", bool1, bool2);
                    BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool3, "->", bool4, " when setting ");
                    m.append(str1);
                    m.append("=");
                    m.append(str2);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = visualStabilityCoordinatorLogger.buffer;
            LogMessage obtain = logBuffer.obtain("VisualStability", logLevel, visualStabilityCoordinatorLogger$logAllowancesChanged$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool1 = z2;
            logMessageImpl.bool2 = z5;
            logMessageImpl.bool3 = z3;
            logMessageImpl.bool4 = z4;
            logMessageImpl.str1 = str;
            logMessageImpl.str2 = String.valueOf(z);
            logBuffer.commit(obtain);
        }
        boolean z8 = this.mPipelineRunAllowed;
        AnonymousClass1 anonymousClass1 = this.mNotifStabilityManager;
        if (z8 && this.mIsSuppressingPipelineRun) {
            anonymousClass1.invalidateList("pipeline run suppression ended");
        } else if (this.mReorderingAllowed && (this.mIsSuppressingGroupChange || !this.mEntriesWithSuppressedSectionChange.isEmpty() || this.mIsSuppressingEntryReorder)) {
            StringBuilder sb = new StringBuilder("reorder suppression ended for group=");
            sb.append(this.mIsSuppressingGroupChange);
            sb.append(" section=");
            sb.append(!this.mEntriesWithSuppressedSectionChange.isEmpty());
            sb.append(" sort=");
            sb.append(this.mIsSuppressingEntryReorder);
            anonymousClass1.invalidateList(sb.toString());
        }
        boolean z9 = this.mReorderingAllowed;
        VisualStabilityProvider visualStabilityProvider = this.mVisualStabilityProvider;
        if (visualStabilityProvider.isReorderingAllowed != z9) {
            visualStabilityProvider.isReorderingAllowed = z9;
            if (z9) {
                ListenerSet listenerSet = visualStabilityProvider.allListeners;
                Iterator it = listenerSet.listeners.iterator();
                while (it.hasNext()) {
                    OnReorderingAllowedListener onReorderingAllowedListener = (OnReorderingAllowedListener) it.next();
                    if (visualStabilityProvider.temporaryListeners.remove(onReorderingAllowedListener)) {
                        listenerSet.remove(onReorderingAllowedListener);
                    }
                    onReorderingAllowedListener.onReorderingAllowed();
                }
            }
        }
    }
}
