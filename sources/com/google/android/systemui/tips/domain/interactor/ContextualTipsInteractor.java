package com.google.android.systemui.tips.domain.interactor;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.assist.domain.interactor.AssistInteractor;
import com.android.systemui.globalactions.domain.interactor.GlobalActionsInteractor;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.statusbar.gesture.TapGestureDetector;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.volume.domain.interactor.VolumeDialogInteractor;
import com.google.android.systemui.tips.data.model.HistoricalTipsModel;
import com.google.android.systemui.tips.data.model.TipType;
import com.google.android.systemui.tips.data.repository.ContextualTipsRepository;
import com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Triple;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextualTipsInteractor implements CoreStartable {
    public final StateFlowImpl _eligibleForMuteVolumeTip;
    public final StateFlowImpl _eligibleForPowerOffTip;
    public final StateFlowImpl _eligibleForScreenshotTip;
    public final StateFlowImpl _isOver30Days;
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final AssistInteractor assistInteractor;
    public final AudioManager audioManager;
    public final ReadonlyStateFlow eligibleForMuteVolumeTip;
    public final ReadonlyStateFlow eligibleForPowerOffTip;
    public final ReadonlyStateFlow eligibleForScreenshotTip;
    public final GlobalActionsInteractor globalActionsInteractor;
    public boolean isListeningTaskStack;
    public final ReadonlyStateFlow isOver30Days;
    public String lastTopActivity;
    public String lastTopPackage;
    public final UiEventLogger logger;
    public final CoroutineDispatcher mainDispatcher;
    public StandaloneCoroutine removeTapGestureCallbackJob;
    public StandaloneCoroutine removeTaskStackListenerJob;
    public final ContextualTipsRepository repository;
    public StandaloneCoroutine resetAssistantDismissCountJob;
    public StandaloneCoroutine resetAssistantStartCountJob;
    public StandaloneCoroutine resetPowerMenuDismissCountJob;
    public final SetupWizardRepositoryImpl setupWizardRepository;
    public final SystemClock systemClock;
    public final TapGestureDetector tapGestureDetector;
    public final ContextualTipsInteractor$taskListener$1 taskListener;
    public final VolumeDialogInteractor volumeDialogInteractor;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LogEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ LogEvent[] $VALUES;
        public static final LogEvent CONTEXTUAL_MUTE_VOLUME_TIP_ELIGIBLE;
        public static final LogEvent CONTEXTUAL_MUTE_VOLUME_TIP_SENT;
        public static final LogEvent CONTEXTUAL_MUTE_VOLUME_TIP_TRIGGERED;
        public static final LogEvent CONTEXTUAL_POWER_OFF_TIP_ELIGIBLE;
        public static final LogEvent CONTEXTUAL_POWER_OFF_TIP_SENT;
        public static final LogEvent CONTEXTUAL_POWER_OFF_TIP_TRIGGERED;
        public static final LogEvent CONTEXTUAL_SCREENSHOT_TIP_ELIGIBLE;
        public static final LogEvent CONTEXTUAL_SCREENSHOT_TIP_SENT;
        public static final LogEvent CONTEXTUAL_SCREENSHOT_TIP_TRIGGERED;
        public static final LogEvent CONTEXTUAL_TIPS_INITED;
        public static final LogEvent CONTEXTUAL_TIPS_OVER_30_DAYS;
        private final int _id;

        static {
            LogEvent logEvent = new LogEvent("CONTEXTUAL_TIPS_INITED", 0, 1705);
            CONTEXTUAL_TIPS_INITED = logEvent;
            LogEvent logEvent2 = new LogEvent("CONTEXTUAL_TIPS_OVER_30_DAYS", 1, 1711);
            CONTEXTUAL_TIPS_OVER_30_DAYS = logEvent2;
            LogEvent logEvent3 = new LogEvent("CONTEXTUAL_SCREENSHOT_TIP_ELIGIBLE", 2, 1687);
            CONTEXTUAL_SCREENSHOT_TIP_ELIGIBLE = logEvent3;
            LogEvent logEvent4 = new LogEvent("CONTEXTUAL_SCREENSHOT_TIP_TRIGGERED", 3, 1685);
            CONTEXTUAL_SCREENSHOT_TIP_TRIGGERED = logEvent4;
            LogEvent logEvent5 = new LogEvent("CONTEXTUAL_SCREENSHOT_TIP_SENT", 4, 1678);
            CONTEXTUAL_SCREENSHOT_TIP_SENT = logEvent5;
            LogEvent logEvent6 = new LogEvent("CONTEXTUAL_POWER_OFF_TIP_ELIGIBLE", 5, 1688);
            CONTEXTUAL_POWER_OFF_TIP_ELIGIBLE = logEvent6;
            LogEvent logEvent7 = new LogEvent("CONTEXTUAL_POWER_OFF_TIP_TRIGGERED", 6, 1686);
            CONTEXTUAL_POWER_OFF_TIP_TRIGGERED = logEvent7;
            LogEvent logEvent8 = new LogEvent("CONTEXTUAL_POWER_OFF_TIP_SENT", 7, 1679);
            CONTEXTUAL_POWER_OFF_TIP_SENT = logEvent8;
            LogEvent logEvent9 = new LogEvent("CONTEXTUAL_MUTE_VOLUME_TIP_ELIGIBLE", 8, 1795);
            CONTEXTUAL_MUTE_VOLUME_TIP_ELIGIBLE = logEvent9;
            LogEvent logEvent10 = new LogEvent("CONTEXTUAL_MUTE_VOLUME_TIP_TRIGGERED", 9, 1796);
            CONTEXTUAL_MUTE_VOLUME_TIP_TRIGGERED = logEvent10;
            LogEvent logEvent11 = new LogEvent("CONTEXTUAL_MUTE_VOLUME_TIP_SENT", 10, 1797);
            CONTEXTUAL_MUTE_VOLUME_TIP_SENT = logEvent11;
            LogEvent[] logEventArr = {logEvent, logEvent2, logEvent3, logEvent4, logEvent5, logEvent6, logEvent7, logEvent8, logEvent9, logEvent10, logEvent11};
            $VALUES = logEventArr;
            EnumEntriesKt.enumEntries(logEventArr);
        }

        public LogEvent(String str, int i, int i2) {
            this._id = i2;
        }

        public static LogEvent valueOf(String str) {
            return (LogEvent) Enum.valueOf(LogEvent.class, str);
        }

        public static LogEvent[] values() {
            return (LogEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this._id;
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$taskListener$1] */
    public ContextualTipsInteractor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ContextualTipsRepository contextualTipsRepository, SetupWizardRepositoryImpl setupWizardRepositoryImpl, GlobalActionsInteractor globalActionsInteractor, VolumeDialogInteractor volumeDialogInteractor, AssistInteractor assistInteractor, TapGestureDetector tapGestureDetector, SystemClock systemClock, AudioManager audioManager, UiEventLogger uiEventLogger) {
        this.applicationContext = context;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.repository = contextualTipsRepository;
        this.setupWizardRepository = setupWizardRepositoryImpl;
        this.globalActionsInteractor = globalActionsInteractor;
        this.volumeDialogInteractor = volumeDialogInteractor;
        this.assistInteractor = assistInteractor;
        this.tapGestureDetector = tapGestureDetector;
        this.systemClock = systemClock;
        this.audioManager = audioManager;
        this.logger = uiEventLogger;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._eligibleForPowerOffTip = MutableStateFlow;
        this.eligibleForPowerOffTip = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._eligibleForScreenshotTip = MutableStateFlow2;
        this.eligibleForScreenshotTip = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._eligibleForMuteVolumeTip = MutableStateFlow3;
        this.eligibleForMuteVolumeTip = new ReadonlyStateFlow(MutableStateFlow3);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._isOver30Days = MutableStateFlow4;
        this.isOver30Days = new ReadonlyStateFlow(MutableStateFlow4);
        this.lastTopPackage = "unknown";
        this.lastTopActivity = "unknown";
        this.taskListener = new TaskStackChangeListener() { // from class: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$taskListener$1
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                String str;
                String className;
                ComponentName componentName = runningTaskInfo.topActivity;
                String str2 = "unknown";
                if (componentName == null || (str = componentName.getPackageName()) == null) {
                    str = "unknown";
                }
                ComponentName componentName2 = runningTaskInfo.topActivity;
                if (componentName2 != null && (className = componentName2.getClassName()) != null) {
                    str2 = className;
                }
                runningTaskInfo.toString();
                ContextualTipsInteractor contextualTipsInteractor = ContextualTipsInteractor.this;
                if (Intrinsics.areEqual(contextualTipsInteractor.lastTopPackage, "com.google.android.googlequicksearchbox") && !str.equals("com.google.android.googlequicksearchbox") && contextualTipsInteractor.lastTopActivity.startsWith("com.google.android.apps.search.assistant.") && !str2.startsWith("com.google.android.apps.search.assistant.")) {
                    StateFlowImpl stateFlowImpl = contextualTipsInteractor.repository._assistantDismissals;
                    stateFlowImpl.updateState(null, Integer.valueOf(((Number) stateFlowImpl.getValue()).intValue() + 1));
                }
                contextualTipsInteractor.lastTopPackage = str;
                contextualTipsInteractor.lastTopActivity = str2;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$refreshPreconditions(com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor r13, kotlin.coroutines.Continuation r14) {
        /*
            r13.getClass()
            boolean r0 = r14 instanceof com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$refreshPreconditions$1
            if (r0 == 0) goto L16
            r0 = r14
            com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$refreshPreconditions$1 r0 = (com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$refreshPreconditions$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$refreshPreconditions$1 r0 = new com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$refreshPreconditions$1
            r0.<init>(r13, r14)
        L1b:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "precondition_check_timestamp"
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 != r4) goto L3a
            java.lang.Object r13 = r0.L$2
            kotlinx.coroutines.flow.MutableStateFlow r13 = (kotlinx.coroutines.flow.MutableStateFlow) r13
            java.lang.Object r1 = r0.L$1
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            java.lang.Object r0 = r0.L$0
            com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor r0 = (com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor) r0
            kotlin.ResultKt.throwOnFailure(r14)
            r5 = r13
            r13 = r0
            goto La1
        L3a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L42:
            kotlin.ResultKt.throwOnFailure(r14)
            com.google.android.systemui.tips.data.repository.ContextualTipsRepository r14 = r13.repository
            android.content.SharedPreferences r2 = r14.preferences
            r5 = 0
            long r7 = r2.getLong(r3, r5)
            com.android.systemui.util.time.SystemClock r2 = r13.systemClock
            com.android.systemui.util.time.SystemClockImpl r2 = (com.android.systemui.util.time.SystemClockImpl) r2
            r2.getClass()
            long r9 = java.lang.System.currentTimeMillis()
            r11 = 86400000(0x5265c00, double:4.2687272E-316)
            long r9 = r9 - r11
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 >= 0) goto Lc1
            android.content.SharedPreferences r14 = r14.preferences
            java.lang.String r2 = "initialization_timestamp"
            long r7 = r14.getLong(r2, r5)
            int r14 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r14 <= 0) goto L7e
            long r5 = java.lang.System.currentTimeMillis()
            r9 = 2592000000(0x9a7ec800, double:1.280618154E-314)
            long r5 = r5 - r9
            int r14 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r14 >= 0) goto L7e
            r14 = r4
            goto L7f
        L7e:
            r14 = 0
        L7f:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r14)
            kotlinx.coroutines.flow.StateFlowImpl r5 = r13._isOver30Days
            if (r14 == 0) goto L8f
            com.android.internal.logging.UiEventLogger r14 = r13.logger
            com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$LogEvent r0 = com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor.LogEvent.CONTEXTUAL_TIPS_OVER_30_DAYS
            r14.log(r0)
            goto La2
        L8f:
            r0.L$0 = r13
            r0.L$1 = r2
            r0.L$2 = r5
            r0.label = r4
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl r14 = r13.setupWizardRepository
            java.lang.Object r14 = r14.refresh(r0)
            if (r14 != r1) goto La0
            goto Lc3
        La0:
            r1 = r2
        La1:
            r2 = r1
        La2:
            kotlinx.coroutines.flow.StateFlowImpl r5 = (kotlinx.coroutines.flow.StateFlowImpl) r5
            r5.setValue(r2)
            com.google.android.systemui.tips.data.repository.ContextualTipsRepository r14 = r13.repository
            com.android.systemui.util.time.SystemClock r13 = r13.systemClock
            com.android.systemui.util.time.SystemClockImpl r13 = (com.android.systemui.util.time.SystemClockImpl) r13
            r13.getClass()
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences r13 = r14.preferences
            android.content.SharedPreferences$Editor r13 = r13.edit()
            android.content.SharedPreferences$Editor r13 = r13.putLong(r3, r0)
            r13.apply()
        Lc1:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        Lc3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor.access$refreshPreconditions(com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void access$showTip(ContextualTipsInteractor contextualTipsInteractor, TipType tipType) {
        Triple triple;
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        ((SystemClockImpl) contextualTipsInteractor.systemClock).getClass();
        long currentTimeMillis = System.currentTimeMillis();
        int ordinal = tipType.ordinal();
        ContextualTipsRepository contextualTipsRepository = contextualTipsInteractor.repository;
        if (ordinal == 1) {
            contextualTipsInteractor.logger.log(LogEvent.CONTEXTUAL_SCREENSHOT_TIP_TRIGGERED);
            HistoricalTipsModel historicalTipsModel = (HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.screenshotTipHistory.$$delegate_0).getValue();
            LogEvent logEvent = LogEvent.CONTEXTUAL_SCREENSHOT_TIP_SENT;
            if (historicalTipsModel.lastTimestamp < currentTimeMillis - 86400000) {
                if (historicalTipsModel.penultimateTimestamp < currentTimeMillis - 604800000 && historicalTipsModel.count <= 2) {
                    z = true;
                    triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_ON_SCREEN_TAKE_SCREENSHOT", logEvent, Boolean.valueOf(z));
                }
            }
            z = false;
            triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_ON_SCREEN_TAKE_SCREENSHOT", logEvent, Boolean.valueOf(z));
        } else if (ordinal == 2) {
            contextualTipsInteractor.logger.log(LogEvent.CONTEXTUAL_POWER_OFF_TIP_TRIGGERED);
            HistoricalTipsModel historicalTipsModel2 = (HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.powerOffTipHistory.$$delegate_0).getValue();
            LogEvent logEvent2 = LogEvent.CONTEXTUAL_POWER_OFF_TIP_SENT;
            if (historicalTipsModel2.lastTimestamp < currentTimeMillis - 86400000) {
                if (historicalTipsModel2.penultimateTimestamp < currentTimeMillis - 604800000 && historicalTipsModel2.count <= 2) {
                    z2 = true;
                    triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_ON_SCREEN_POWER_OFF", logEvent2, Boolean.valueOf(z2));
                }
            }
            z2 = false;
            triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_ON_SCREEN_POWER_OFF", logEvent2, Boolean.valueOf(z2));
        } else {
            if (ordinal != 3) {
                return;
            }
            contextualTipsInteractor.logger.log(LogEvent.CONTEXTUAL_MUTE_VOLUME_TIP_TRIGGERED);
            HistoricalTipsModel historicalTipsModel3 = (HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.muteVolumeTipHistory.$$delegate_0).getValue();
            LogEvent logEvent3 = LogEvent.CONTEXTUAL_MUTE_VOLUME_TIP_SENT;
            if (historicalTipsModel3.lastTimestamp < currentTimeMillis - 86400000) {
                if (historicalTipsModel3.penultimateTimestamp < currentTimeMillis - 604800000 && historicalTipsModel3.count <= 2) {
                    z3 = true;
                    triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_BOTTOM_SHEET_MUTE_VOLUME", logEvent3, Boolean.valueOf(z3));
                }
            }
            z3 = false;
            triple = new Triple("com.google.android.apps.tips.contextual.triggering.LAUNCH_BOTTOM_SHEET_MUTE_VOLUME", logEvent3, Boolean.valueOf(z3));
        }
        String str = (String) triple.component1();
        LogEvent logEvent4 = (LogEvent) triple.component2();
        boolean booleanValue = ((Boolean) triple.component3()).booleanValue();
        List listOf = CollectionsKt__CollectionsKt.listOf(Long.valueOf(((HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.screenshotTipHistory.$$delegate_0).getValue()).lastTimestamp), Long.valueOf(((HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.powerOffTipHistory.$$delegate_0).getValue()).lastTimestamp), Long.valueOf(((HistoricalTipsModel) ((StateFlowImpl) contextualTipsRepository.muteVolumeTipHistory.$$delegate_0).getValue()).lastTimestamp));
        if (listOf == null || !listOf.isEmpty()) {
            Iterator it = listOf.iterator();
            i = 0;
            while (it.hasNext()) {
                long longValue = ((Number) it.next()).longValue();
                ((SystemClockImpl) contextualTipsRepository.systemClock).getClass();
                Iterator it2 = it;
                if (longValue > System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
                it = it2;
            }
        } else {
            i = 0;
        }
        boolean z4 = i >= 2;
        if (!booleanValue || z4) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setPackage("com.google.android.apps.tips");
        intent.setFlags(268533760);
        try {
            contextualTipsInteractor.applicationContext.startActivity(intent);
            ContextualTipsRepository.updateTriggeringHistory$default(contextualTipsRepository, tipType);
            contextualTipsInteractor.logger.log(logEvent4);
        } catch (Exception e) {
            e.toString();
        }
    }

    public final boolean isAssistantDismiss(float f, float f2, int i, int i2) {
        if (i2 > i) {
            return ((double) f2) <= ((double) i2) * 0.5d;
        }
        if (f2 > i2 * 0.15d) {
            double d = f;
            double d2 = i;
            if (d >= 0.2d * d2 && d <= d2 * 0.8d) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        PackageManager packageManager = this.applicationContext.getPackageManager();
        boolean z = false;
        if (packageManager != null ? packageManager.hasSystemFeature("com.google.android.feature.PIXEL_2024_EXPERIENCE") : false) {
            ContextualTipsRepository contextualTipsRepository = this.repository;
            long j = contextualTipsRepository.preferences.getLong("initialization_timestamp", 0L);
            SystemClock systemClock = this.systemClock;
            if (j > 0) {
                ((SystemClockImpl) systemClock).getClass();
                if (j < System.currentTimeMillis() - 2592000000L) {
                    z = true;
                }
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (z) {
                this.logger.log(LogEvent.CONTEXTUAL_TIPS_OVER_30_DAYS);
            }
            StateFlowImpl stateFlowImpl = this._isOver30Days;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
            if (j == 0) {
                ((SystemClockImpl) systemClock).getClass();
                contextualTipsRepository.preferences.edit().putLong("initialization_timestamp", System.currentTimeMillis()).apply();
                this.logger.log(LogEvent.CONTEXTUAL_TIPS_INITED);
            }
            contextualTipsRepository.preferences.edit().putLong("precondition_check_timestamp", 0L).apply();
            ContextualTipsInteractor$start$2 contextualTipsInteractor$start$2 = new ContextualTipsInteractor$start$2(this, null);
            CoroutineScope coroutineScope = this.applicationScope;
            BuildersKt.launch$default(coroutineScope, null, null, contextualTipsInteractor$start$2, 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$3(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$4(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$5(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$6(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$7(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$8(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$9(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$10(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new ContextualTipsInteractor$start$11(this, null), 3);
        }
    }
}
