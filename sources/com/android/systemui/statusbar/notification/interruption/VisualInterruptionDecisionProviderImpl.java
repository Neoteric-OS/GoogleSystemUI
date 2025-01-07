package com.android.systemui.statusbar.notification.interruption;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Trace;
import android.util.EventLog;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.notifications.domain.interactor.NotificationSettingsInteractor;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.FullScreenIntentDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionSuppressor;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.EventLogImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VisualInterruptionDecisionProviderImpl implements VisualInterruptionDecisionProvider {
    public final AmbientDisplayConfiguration ambientDisplayConfiguration;
    public final BatteryController batteryController;
    public final Optional bubbles;
    public final EventLogImpl eventLog;
    public final FullScreenIntentDecisionProvider fullScreenIntentDecisionProvider;
    public final GlobalSettings globalSettings;
    public final HeadsUpManager headsUpManager;
    public final KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProvider;
    public final VisualInterruptionDecisionLogger logger;
    public final Handler mainHandler;
    public final PowerManager powerManager;
    public boolean started;
    public final StatusBarStateController statusBarStateController;
    public final SystemClock systemClock;
    public final UiEventLogger uiEventLogger;
    public final UserTracker userTracker;
    public final Set legacySuppressors = new LinkedHashSet();
    public final List conditions = new ArrayList();
    public final List filters = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DecisionImpl implements VisualInterruptionDecisionProvider.Decision {
        public final String logReason;
        public final boolean shouldInterrupt;

        public DecisionImpl(String str, boolean z) {
            this.shouldInterrupt = z;
            this.logReason = str;
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
        public final String getLogReason() {
            return this.logReason;
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
        public final boolean getShouldInterrupt() {
            return this.shouldInterrupt;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FullScreenIntentDecisionImpl implements VisualInterruptionDecisionProvider.FullScreenIntentDecision, Loggable {
        public final NotificationEntry entry;
        public final FullScreenIntentDecisionProvider.Decision fsiDecision;
        public boolean hasBeenLogged;

        public FullScreenIntentDecisionImpl(NotificationEntry notificationEntry, FullScreenIntentDecisionProvider.Decision decision) {
            this.entry = notificationEntry;
            this.fsiDecision = decision;
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl.Loggable
        public final VisualInterruptionSuppressor.EventLogData getEventLogData() {
            return ((FullScreenIntentDecisionProvider.DecisionImpl) this.fsiDecision).getEventLogData();
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
        public final String getLogReason() {
            return ((FullScreenIntentDecisionProvider.DecisionImpl) this.fsiDecision).getLogReason();
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
        public final boolean getShouldInterrupt() {
            return ((FullScreenIntentDecisionProvider.DecisionImpl) this.fsiDecision).getShouldFsi();
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl.Loggable
        public final UiEventLogger.UiEventEnum getUiEventId() {
            return ((FullScreenIntentDecisionProvider.DecisionImpl) this.fsiDecision).getUiEventId();
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.FullScreenIntentDecision
        public final boolean getWouldInterruptWithoutDnd() {
            return ((FullScreenIntentDecisionProvider.DecisionImpl) this.fsiDecision).getWouldFsiWithoutDnd();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Loggable {
        VisualInterruptionSuppressor.EventLogData getEventLogData();

        UiEventLogger.UiEventEnum getUiEventId();
    }

    public VisualInterruptionDecisionProviderImpl(AmbientDisplayConfiguration ambientDisplayConfiguration, BatteryController batteryController, DeviceProvisionedController deviceProvisionedController, EventLogImpl eventLogImpl, GlobalSettings globalSettings, HeadsUpManager headsUpManager, KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl, KeyguardStateController keyguardStateController, VisualInterruptionDecisionLogger visualInterruptionDecisionLogger, Handler handler, PowerManager powerManager, StatusBarStateController statusBarStateController, SystemClock systemClock, UiEventLogger uiEventLogger, UserTracker userTracker, AvalancheProvider avalancheProvider, SystemSettingsImpl systemSettingsImpl, PackageManager packageManager, Optional optional, Context context, NotificationManager notificationManager, NotificationSettingsInteractor notificationSettingsInteractor) {
        this.ambientDisplayConfiguration = ambientDisplayConfiguration;
        this.batteryController = batteryController;
        this.eventLog = eventLogImpl;
        this.globalSettings = globalSettings;
        this.headsUpManager = headsUpManager;
        this.keyguardNotificationVisibilityProvider = keyguardNotificationVisibilityProviderImpl;
        this.logger = visualInterruptionDecisionLogger;
        this.mainHandler = handler;
        this.powerManager = powerManager;
        this.statusBarStateController = statusBarStateController;
        this.systemClock = systemClock;
        this.uiEventLogger = uiEventLogger;
        this.userTracker = userTracker;
        this.bubbles = optional;
        this.fullScreenIntentDecisionProvider = new FullScreenIntentDecisionProvider(deviceProvisionedController, keyguardStateController, powerManager, statusBarStateController);
    }

    public final void addCondition(VisualInterruptionCondition visualInterruptionCondition) {
        this.conditions.add(visualInterruptionCondition);
        visualInterruptionCondition.start();
    }

    public final void addFilter(VisualInterruptionFilter visualInterruptionFilter) {
        this.filters.add(visualInterruptionFilter);
    }

    public final LoggableDecision checkConditions(VisualInterruptionType visualInterruptionType) {
        Object obj;
        Iterator it = this.conditions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            VisualInterruptionCondition visualInterruptionCondition = (VisualInterruptionCondition) obj;
            if (visualInterruptionCondition.types.contains(visualInterruptionType) && visualInterruptionCondition.shouldSuppress()) {
                break;
            }
        }
        VisualInterruptionCondition visualInterruptionCondition2 = (VisualInterruptionCondition) obj;
        if (visualInterruptionCondition2 != null) {
            return new LoggableDecision(new DecisionImpl(visualInterruptionCondition2.reason, false), null, null, false);
        }
        return null;
    }

    public final LoggableDecision checkFilters(VisualInterruptionType visualInterruptionType, NotificationEntry notificationEntry) {
        Object obj;
        Iterator it = this.filters.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            VisualInterruptionFilter visualInterruptionFilter = (VisualInterruptionFilter) obj;
            if (visualInterruptionFilter.types.contains(visualInterruptionType) && visualInterruptionFilter.shouldSuppress(notificationEntry)) {
                break;
            }
        }
        VisualInterruptionFilter visualInterruptionFilter2 = (VisualInterruptionFilter) obj;
        if (visualInterruptionFilter2 != null) {
            return new LoggableDecision(new DecisionImpl(visualInterruptionFilter2.reason, false), visualInterruptionFilter2.uiEventId, null, visualInterruptionFilter2.isSpammy);
        }
        return null;
    }

    public final LoggableDecision checkSuppressAwakeInterruptions(NotificationEntry notificationEntry) {
        Object obj;
        Iterator it = this.legacySuppressors.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((NotificationInterruptSuppressor) obj).suppressAwakeInterruptions()) {
                break;
            }
        }
        NotificationInterruptSuppressor notificationInterruptSuppressor = (NotificationInterruptSuppressor) obj;
        if (notificationInterruptSuppressor != null) {
            return LoggableDecision.Companion.suppressed(notificationInterruptSuppressor, "suppressAwakeInterruptions");
        }
        return null;
    }

    public final LoggableDecision checkSuppressInterruptions(NotificationEntry notificationEntry) {
        Object obj;
        Iterator it = this.legacySuppressors.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((NotificationInterruptSuppressor) obj).suppressInterruptions()) {
                break;
            }
        }
        NotificationInterruptSuppressor notificationInterruptSuppressor = (NotificationInterruptSuppressor) obj;
        if (notificationInterruptSuppressor != null) {
            return LoggableDecision.Companion.suppressed(notificationInterruptSuppressor, "suppressInterruptions");
        }
        return null;
    }

    public final void logDecision(VisualInterruptionType visualInterruptionType, NotificationEntry notificationEntry, LoggableDecision loggableDecision) {
        VisualInterruptionDecisionLogger visualInterruptionDecisionLogger = this.logger;
        if (!loggableDecision.isSpammy) {
            String name = visualInterruptionType.name();
            LogLevel logLevel = LogLevel.DEBUG;
            VisualInterruptionDecisionLogger$logDecision$2 visualInterruptionDecisionLogger$logDecision$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger$logDecision$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str = logMessage.getBool1() ? "allowed" : "suppressed";
                    return logMessage.getStr1() + " " + str + ": " + logMessage.getStr2() + " (key=" + logMessage.getStr3() + ")";
                }
            };
            LogBuffer logBuffer = visualInterruptionDecisionLogger.buffer;
            LogMessage obtain = logBuffer.obtain("VisualInterruptionDecisionProvider", logLevel, visualInterruptionDecisionLogger$logDecision$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = name;
            DecisionImpl decisionImpl = loggableDecision.decision;
            logMessageImpl.bool1 = decisionImpl.shouldInterrupt;
            logMessageImpl.str2 = decisionImpl.logReason;
            logMessageImpl.str3 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
        logEvents(notificationEntry, loggableDecision);
    }

    public final void logEvents(NotificationEntry notificationEntry, Loggable loggable) {
        UiEventLogger.UiEventEnum uiEventId = loggable.getUiEventId();
        if (uiEventId != null) {
            this.uiEventLogger.log(uiEventId, notificationEntry.mSbn.getUid(), notificationEntry.mSbn.getPackageName());
        }
        VisualInterruptionSuppressor.EventLogData eventLogData = loggable.getEventLogData();
        if (eventLogData != null) {
            Object[] objArr = {eventLogData.number, Integer.valueOf(notificationEntry.mSbn.getUid()), eventLogData.description};
            this.eventLog.getClass();
            EventLog.writeEvent(1397638484, Arrays.copyOf(objArr, 3));
        }
    }

    public final void logFullScreenIntentDecision(VisualInterruptionDecisionProvider.FullScreenIntentDecision fullScreenIntentDecision) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#logFullScreenIntentDecision");
        }
        try {
            if (!this.started) {
                throw new IllegalStateException("Check failed.");
            }
            if (!(fullScreenIntentDecision instanceof FullScreenIntentDecisionImpl)) {
                Log.wtf("VisualInterruptionDecisionProviderImpl", "FSI decision " + fullScreenIntentDecision + " was not created by this class");
                if (isEnabled) {
                    return;
                } else {
                    return;
                }
            }
            if (((FullScreenIntentDecisionImpl) fullScreenIntentDecision).hasBeenLogged) {
                Log.wtf("VisualInterruptionDecisionProviderImpl", "FSI decision " + fullScreenIntentDecision + " has already been logged");
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                    return;
                }
                return;
            }
            ((FullScreenIntentDecisionImpl) fullScreenIntentDecision).hasBeenLogged = true;
            if (!((FullScreenIntentDecisionProvider.DecisionImpl) ((FullScreenIntentDecisionImpl) fullScreenIntentDecision).fsiDecision).getShouldLog()) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            } else {
                this.logger.logFullScreenIntentDecision(((FullScreenIntentDecisionImpl) fullScreenIntentDecision).entry, fullScreenIntentDecision, ((FullScreenIntentDecisionProvider.DecisionImpl) ((FullScreenIntentDecisionImpl) fullScreenIntentDecision).fsiDecision).isWarning());
                logEvents(((FullScreenIntentDecisionImpl) fullScreenIntentDecision).entry, (Loggable) fullScreenIntentDecision);
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public final VisualInterruptionDecisionProvider.Decision makeAndLogHeadsUpDecision(NotificationEntry notificationEntry) {
        LoggableDecision makeLoggablePeekDecision;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeAndLogHeadsUpDecision");
        }
        try {
            if (!this.started) {
                throw new IllegalStateException("Check failed.");
            }
            if (this.statusBarStateController.isDozing()) {
                VisualInterruptionType visualInterruptionType = VisualInterruptionType.PULSE;
                makeLoggablePeekDecision = checkConditions(visualInterruptionType);
                if (makeLoggablePeekDecision == null && (makeLoggablePeekDecision = checkFilters(visualInterruptionType, notificationEntry)) == null && (makeLoggablePeekDecision = checkSuppressInterruptions(notificationEntry)) == null) {
                    makeLoggablePeekDecision = LoggableDecision.unsuppressed;
                }
                logDecision(visualInterruptionType, notificationEntry, makeLoggablePeekDecision);
            } else {
                makeLoggablePeekDecision = makeLoggablePeekDecision(notificationEntry);
                logDecision(VisualInterruptionType.PEEK, notificationEntry, makeLoggablePeekDecision);
            }
            DecisionImpl decisionImpl = makeLoggablePeekDecision.decision;
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            return decisionImpl;
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final LoggableDecision makeLoggablePeekDecision(NotificationEntry notificationEntry) {
        Object obj;
        VisualInterruptionType visualInterruptionType = VisualInterruptionType.PEEK;
        LoggableDecision checkConditions = checkConditions(visualInterruptionType);
        if (checkConditions != null) {
            return checkConditions;
        }
        LoggableDecision checkFilters = checkFilters(visualInterruptionType, notificationEntry);
        if (checkFilters != null) {
            return checkFilters;
        }
        LoggableDecision checkSuppressInterruptions = checkSuppressInterruptions(notificationEntry);
        if (checkSuppressInterruptions != null) {
            return checkSuppressInterruptions;
        }
        LoggableDecision checkSuppressAwakeInterruptions = checkSuppressAwakeInterruptions(notificationEntry);
        if (checkSuppressAwakeInterruptions != null) {
            return checkSuppressAwakeInterruptions;
        }
        Iterator it = this.legacySuppressors.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((NotificationInterruptSuppressor) obj).suppressAwakeHeadsUp(notificationEntry)) {
                break;
            }
        }
        NotificationInterruptSuppressor notificationInterruptSuppressor = (NotificationInterruptSuppressor) obj;
        LoggableDecision suppressed = notificationInterruptSuppressor != null ? LoggableDecision.Companion.suppressed(notificationInterruptSuppressor, "suppressAwakeHeadsUp") : null;
        return suppressed == null ? LoggableDecision.unsuppressed : suppressed;
    }

    public final VisualInterruptionDecisionProvider.FullScreenIntentDecision makeUnloggedFullScreenIntentDecision(NotificationEntry notificationEntry) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeUnloggedFullScreenIntentDecision");
        }
        try {
            if (!this.started) {
                throw new IllegalStateException("Check failed.");
            }
            return new FullScreenIntentDecisionImpl(notificationEntry, this.fullScreenIntentDecisionProvider.makeFullScreenIntentDecision(notificationEntry, ((DecisionImpl) makeUnloggedHeadsUpDecision(notificationEntry)).shouldInterrupt));
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public final VisualInterruptionDecisionProvider.Decision makeUnloggedHeadsUpDecision(NotificationEntry notificationEntry) {
        LoggableDecision makeLoggablePeekDecision;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeUnloggedHeadsUpDecision");
        }
        try {
            if (!this.started) {
                throw new IllegalStateException("Check failed.");
            }
            if (this.statusBarStateController.isDozing()) {
                VisualInterruptionType visualInterruptionType = VisualInterruptionType.PULSE;
                makeLoggablePeekDecision = checkConditions(visualInterruptionType);
                if (makeLoggablePeekDecision == null && (makeLoggablePeekDecision = checkFilters(visualInterruptionType, notificationEntry)) == null && (makeLoggablePeekDecision = checkSuppressInterruptions(notificationEntry)) == null) {
                    makeLoggablePeekDecision = LoggableDecision.unsuppressed;
                }
            } else {
                makeLoggablePeekDecision = makeLoggablePeekDecision(notificationEntry);
            }
            return makeLoggablePeekDecision.decision;
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider
    public void removeCondition(VisualInterruptionCondition visualInterruptionCondition) {
        this.conditions.remove(visualInterruptionCondition);
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider
    public void removeFilter(VisualInterruptionFilter visualInterruptionFilter) {
        this.filters.remove(visualInterruptionFilter);
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider
    public final void removeLegacySuppressor(NotificationInterruptSuppressor notificationInterruptSuppressor) {
        this.legacySuppressors.remove(notificationInterruptSuppressor);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.started) {
            throw new IllegalStateException("Check failed.");
        }
        VisualInterruptionDecisionLogger visualInterruptionDecisionLogger = this.logger;
        GlobalSettings globalSettings = this.globalSettings;
        HeadsUpManager headsUpManager = this.headsUpManager;
        addCondition(new PeekDisabledSuppressor(globalSettings, headsUpManager, visualInterruptionDecisionLogger, this.mainHandler));
        addCondition(new PulseDisabledSuppressor(this.ambientDisplayConfiguration, this.userTracker));
        addCondition(new PulseBatterySaverSuppressor(this.batteryController));
        addFilter(new PeekPackageSnoozedSuppressor(headsUpManager));
        Optional optional = this.bubbles;
        StatusBarStateController statusBarStateController = this.statusBarStateController;
        addFilter(new PeekAlreadyBubbledSuppressor(statusBarStateController, optional));
        VisualInterruptionType visualInterruptionType = VisualInterruptionType.PEEK;
        addFilter(new PeekDndSuppressor("suppressed by DND", Collections.singleton(visualInterruptionType)));
        addFilter(new PeekNotImportantSuppressor("importance < HIGH", Collections.singleton(visualInterruptionType)));
        addCondition(new PeekDeviceNotInUseSuppressor(this.powerManager, statusBarStateController));
        addFilter(new PeekOldWhenSuppressor(this.systemClock));
        VisualInterruptionType visualInterruptionType2 = VisualInterruptionType.PULSE;
        addFilter(new PulseEffectSuppressor("suppressed by DND", Collections.singleton(visualInterruptionType2)));
        addFilter(new PulseLockscreenVisibilityPrivateSuppressor("hidden by lockscreen visibility override", Collections.singleton(visualInterruptionType2)));
        addFilter(new PulseLowImportanceSuppressor("importance < DEFAULT", Collections.singleton(visualInterruptionType2)));
        VisualInterruptionType visualInterruptionType3 = VisualInterruptionType.BUBBLE;
        addFilter(new BubbleNotAllowedSuppressor(Collections.singleton(visualInterruptionType3), "cannot bubble", null, 12));
        addFilter(new BubbleNoMetadataSuppressor("has no or invalid bubble metadata", Collections.singleton(visualInterruptionType3)));
        addFilter(new HunGroupAlertBehaviorSuppressor("suppressive group alert behavior", SetsKt.setOf(visualInterruptionType, visualInterruptionType2)));
        addFilter(new HunSilentNotificationSuppressor("notification isSilent", SetsKt.setOf(visualInterruptionType, visualInterruptionType2)));
        addFilter(new HunJustLaunchedFsiSuppressor("just launched FSI", SetsKt.setOf(visualInterruptionType, visualInterruptionType2)));
        addFilter(new AlertAppSuspendedSuppressor("app is suspended", SetsKt.setOf(visualInterruptionType, visualInterruptionType2, visualInterruptionType3)));
        addFilter(new AlertKeyguardVisibilitySuppressor(this.keyguardNotificationVisibilityProvider));
        this.started = true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LoggableDecision implements Loggable {
        public static final LoggableDecision unsuppressed = new LoggableDecision(new DecisionImpl("not suppressed", true));
        public final DecisionImpl decision;
        public final VisualInterruptionSuppressor.EventLogData eventLogData;
        public final boolean isSpammy;
        public final UiEventLogger.UiEventEnum uiEventId;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Companion {
            public static LoggableDecision suppressed(NotificationInterruptSuppressor notificationInterruptSuppressor, String str) {
                return new LoggableDecision(new DecisionImpl(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(notificationInterruptSuppressor.getName(), ".", str), false));
            }
        }

        public LoggableDecision(DecisionImpl decisionImpl, UiEventLogger.UiEventEnum uiEventEnum, VisualInterruptionSuppressor.EventLogData eventLogData, boolean z) {
            this.decision = decisionImpl;
            this.uiEventId = uiEventEnum;
            this.eventLogData = eventLogData;
            this.isSpammy = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LoggableDecision)) {
                return false;
            }
            LoggableDecision loggableDecision = (LoggableDecision) obj;
            return Intrinsics.areEqual(this.decision, loggableDecision.decision) && Intrinsics.areEqual(this.uiEventId, loggableDecision.uiEventId) && Intrinsics.areEqual(this.eventLogData, loggableDecision.eventLogData) && this.isSpammy == loggableDecision.isSpammy;
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl.Loggable
        public final VisualInterruptionSuppressor.EventLogData getEventLogData() {
            return this.eventLogData;
        }

        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl.Loggable
        public final UiEventLogger.UiEventEnum getUiEventId() {
            return this.uiEventId;
        }

        public final int hashCode() {
            int hashCode = this.decision.hashCode() * 31;
            UiEventLogger.UiEventEnum uiEventEnum = this.uiEventId;
            int hashCode2 = (hashCode + (uiEventEnum == null ? 0 : uiEventEnum.hashCode())) * 31;
            VisualInterruptionSuppressor.EventLogData eventLogData = this.eventLogData;
            return Boolean.hashCode(this.isSpammy) + ((hashCode2 + (eventLogData != null ? eventLogData.hashCode() : 0)) * 31);
        }

        public final String toString() {
            return "LoggableDecision(decision=" + this.decision + ", uiEventId=" + this.uiEventId + ", eventLogData=" + this.eventLogData + ", isSpammy=" + this.isSpammy + ")";
        }

        public /* synthetic */ LoggableDecision(DecisionImpl decisionImpl) {
            this(decisionImpl, null, null, false);
        }
    }
}
