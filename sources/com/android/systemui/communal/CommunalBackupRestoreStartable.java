package com.android.systemui.communal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalBackupRestoreStartable extends BroadcastReceiver implements CoreStartable {
    public final BroadcastDispatcher broadcastDispatcher;
    public final CommunalInteractor communalInteractor;
    public final Logger logger;
    public Map oldToNewWidgetIdMap = MapsKt.emptyMap();
    public final SecureSettings secureSettings;
    public boolean userSetupComplete;
    public final CommunalBackupRestoreStartable$userSetupObserver$1 userSetupObserver;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.communal.CommunalBackupRestoreStartable$userSetupObserver$1] */
    public CommunalBackupRestoreStartable(BroadcastDispatcher broadcastDispatcher, CommunalInteractor communalInteractor, LogBuffer logBuffer, SecureSettings secureSettings, final Handler handler) {
        this.broadcastDispatcher = broadcastDispatcher;
        this.communalInteractor = communalInteractor;
        this.secureSettings = secureSettings;
        this.logger = new Logger(logBuffer, "CommunalBackupRestoreStartable");
        this.userSetupObserver = new ContentObserver(handler) { // from class: com.android.systemui.communal.CommunalBackupRestoreStartable$userSetupObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.maybeRestoreWidgets();
                CommunalBackupRestoreStartable communalBackupRestoreStartable = this;
                if (communalBackupRestoreStartable.userSetupComplete) {
                    communalBackupRestoreStartable.secureSettings.unregisterContentObserverSync(communalBackupRestoreStartable.userSetupObserver);
                }
            }
        };
    }

    public final void maybeRestoreWidgets() {
        SecureSettings secureSettings = this.secureSettings;
        String stringForUser = ((SecureSettingsImpl) secureSettings).getStringForUser(secureSettings.getUserId(), "user_setup_complete");
        if (stringForUser == null) {
            throw new Settings.SettingNotFoundException("user_setup_complete");
        }
        try {
            boolean z = Integer.parseInt(stringForUser) > 0;
            if (this.userSetupComplete != z) {
                this.userSetupComplete = z;
                Logger logger = this.logger;
                LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, new Function1() { // from class: com.android.systemui.communal.CommunalBackupRestoreStartable$maybeRestoreWidgets$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("User setup complete: ", ((LogMessage) obj).getBool1());
                    }
                }, null);
                obtain.setBool1(this.userSetupComplete);
                logger.getBuffer().commit(obtain);
            }
            if (!this.userSetupComplete || this.oldToNewWidgetIdMap.isEmpty()) {
                return;
            }
            Logger.i$default(this.logger, "Starting to restore widgets", null, 2, null);
            this.communalInteractor.widgetRepository.restoreWidgets(MapsKt.toMap(this.oldToNewWidgetIdMap));
            this.oldToNewWidgetIdMap = MapsKt.emptyMap();
        } catch (NumberFormatException unused) {
            throw new Settings.SettingNotFoundException("user_setup_complete");
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Logger.w$default(this.logger, "On app widget host restored, but intent is null", null, 2, null);
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), "android.appwidget.action.APPWIDGET_HOST_RESTORED")) {
            if (intent.getIntExtra("hostId", 0) != 116) {
                return;
            }
            int[] intArrayExtra = intent.getIntArrayExtra("appWidgetOldIds");
            int[] intArrayExtra2 = intent.getIntArrayExtra("appWidgetIds");
            if (intArrayExtra == null || intArrayExtra2 == null || intArrayExtra.length != intArrayExtra2.length) {
                Logger.w$default(this.logger, "On app widget host restored, but old to new ids mapping is invalid", null, 2, null);
                this.communalInteractor.widgetRepository.abortRestoreWidgets();
                return;
            }
            int min = Math.min(intArrayExtra.length, intArrayExtra2.length);
            ArrayList arrayList = new ArrayList(min);
            for (int i = 0; i < min; i++) {
                arrayList.add(new Pair(Integer.valueOf(intArrayExtra[i]), Integer.valueOf(intArrayExtra2[i])));
            }
            this.oldToNewWidgetIdMap = MapsKt.toMap(arrayList);
            Logger logger = this.logger;
            CommunalBackupRestoreStartable$onReceive$1 communalBackupRestoreStartable$onReceive$1 = new Function1() { // from class: com.android.systemui.communal.CommunalBackupRestoreStartable$onReceive$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("On old to new widget ids mapping updated: ", ((LogMessage) obj).getStr1());
                }
            };
            LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, communalBackupRestoreStartable$onReceive$1, null);
            obtain.setStr1(this.oldToNewWidgetIdMap.toString());
            logger.getBuffer().commit(obtain);
            maybeRestoreWidgets();
            if (this.userSetupComplete) {
                return;
            }
            this.secureSettings.registerContentObserverSync("user_setup_complete", this.userSetupObserver);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this, new IntentFilter("android.appwidget.action.APPWIDGET_HOST_RESTORED"), null, null, 0, 60);
    }
}
