package com.android.systemui.statusbar.notification.interruption;

import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import java.util.Collections;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekDisabledSuppressor extends VisualInterruptionCondition {
    public final GlobalSettings globalSettings;
    public final HeadsUpManager headsUpManager;
    public boolean isEnabled;
    public final VisualInterruptionDecisionLogger logger;
    public final Handler mainHandler;

    public PeekDisabledSuppressor(GlobalSettings globalSettings, HeadsUpManager headsUpManager, VisualInterruptionDecisionLogger visualInterruptionDecisionLogger, Handler handler) {
        super("peek disabled by global setting", Collections.singleton(VisualInterruptionType.PEEK));
        this.globalSettings = globalSettings;
        this.headsUpManager = headsUpManager;
        this.logger = visualInterruptionDecisionLogger;
        this.mainHandler = handler;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
    public final boolean shouldSuppress() {
        return !this.isEnabled;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionSuppressor
    public final void start() {
        final Handler handler = this.mainHandler;
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.android.systemui.statusbar.notification.interruption.PeekDisabledSuppressor$start$observer$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                PeekDisabledSuppressor peekDisabledSuppressor = PeekDisabledSuppressor.this;
                boolean z2 = peekDisabledSuppressor.isEnabled;
                peekDisabledSuppressor.isEnabled = peekDisabledSuppressor.globalSettings.getInt(0, "heads_up_notifications_enabled") != 0;
                PeekDisabledSuppressor peekDisabledSuppressor2 = PeekDisabledSuppressor.this;
                VisualInterruptionDecisionLogger visualInterruptionDecisionLogger = peekDisabledSuppressor2.logger;
                boolean z3 = peekDisabledSuppressor2.isEnabled;
                visualInterruptionDecisionLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                VisualInterruptionDecisionLogger$logHeadsUpFeatureChanged$2 visualInterruptionDecisionLogger$logHeadsUpFeatureChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger$logHeadsUpFeatureChanged$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return "HUN feature is now ".concat(((LogMessage) obj).getBool1() ? "enabled" : "disabled");
                    }
                };
                LogBuffer logBuffer = visualInterruptionDecisionLogger.buffer;
                LogMessage obtain = logBuffer.obtain("VisualInterruptionDecisionProvider", logLevel, visualInterruptionDecisionLogger$logHeadsUpFeatureChanged$2, null);
                ((LogMessageImpl) obtain).bool1 = z3;
                logBuffer.commit(obtain);
                if (z2) {
                    PeekDisabledSuppressor peekDisabledSuppressor3 = PeekDisabledSuppressor.this;
                    if (peekDisabledSuppressor3.isEnabled) {
                        return;
                    }
                    VisualInterruptionDecisionLogger visualInterruptionDecisionLogger2 = peekDisabledSuppressor3.logger;
                    visualInterruptionDecisionLogger2.getClass();
                    VisualInterruptionDecisionLogger$logWillDismissAll$2 visualInterruptionDecisionLogger$logWillDismissAll$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger$logWillDismissAll$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "dismissing all HUNs since feature was disabled";
                        }
                    };
                    LogBuffer logBuffer2 = visualInterruptionDecisionLogger2.buffer;
                    logBuffer2.commit(logBuffer2.obtain("VisualInterruptionDecisionProvider", logLevel, visualInterruptionDecisionLogger$logWillDismissAll$2, null));
                    ((BaseHeadsUpManager) PeekDisabledSuppressor.this.headsUpManager).releaseAllImmediately();
                }
            }
        };
        GlobalSettings globalSettings = this.globalSettings;
        ((GlobalSettingsImpl) globalSettings).getClass();
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("heads_up_notifications_enabled"), true, contentObserver);
        contentObserver.onChange(true);
    }
}
