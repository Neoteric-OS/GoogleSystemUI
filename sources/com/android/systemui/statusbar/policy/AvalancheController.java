package com.android.systemui.statusbar.policy;

import android.os.Handler;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AvalancheController implements Dumpable {
    public Function0 baseEntryMapStr = new Function0() { // from class: com.android.systemui.statusbar.policy.AvalancheController$baseEntryMapStr$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return "baseEntryMapStr not initialized";
        }
    };
    public final Handler bgHandler;
    public final Set debugDropSet;
    public final Map debugRunnableLabelMap;
    public final BaseHeadsUpManager.HeadsUpEntry headsUpEntryShowing;
    public final HeadsUpManagerLogger headsUpManagerLogger;
    public final List nextList;
    public final Map nextMap;
    public final UiEventLogger uiEventLogger;

    public AvalancheController(DumpManager dumpManager, UiEventLogger uiEventLogger, HeadsUpManagerLogger headsUpManagerLogger, Handler handler) {
        this.headsUpManagerLogger = headsUpManagerLogger;
        new ArrayList();
        this.nextList = new ArrayList();
        this.nextMap = new HashMap();
        new HashMap();
        this.debugDropSet = new HashSet();
        dumpManager.registerNormalDumpable("AvalancheController", this);
    }

    public static String getKey(BaseHeadsUpManager.HeadsUpEntry headsUpEntry) {
        if (headsUpEntry == null) {
            return "HeadsUpEntry null";
        }
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        return notificationEntry == null ? "HeadsUpEntry.mEntry null" : notificationEntry.mKey;
    }

    public final void addToNext(BaseHeadsUpManager.HeadsUpEntry headsUpEntry, Runnable runnable) {
        this.nextMap.put(headsUpEntry, CollectionsKt__CollectionsKt.arrayListOf(runnable));
        this.nextList.add(headsUpEntry);
    }

    public final void delete(BaseHeadsUpManager.HeadsUpEntry headsUpEntry, Runnable runnable, String str) {
        String key = getKey(headsUpEntry);
        HeadsUpManagerLogger headsUpManagerLogger = this.headsUpManagerLogger;
        runnable.run();
        String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("NOT ENABLED, run runnable. ", getStateStr());
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logAvalancheDelete$2 headsUpManagerLogger$logAvalancheDelete$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAvalancheDelete$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder sb = new StringBuilder();
                sb.append(str1);
                sb.append("\n\t=> AC[isEnabled:");
                sb.append(bool1);
                sb.append("] delete: ");
                sb.append(str2);
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, "\n\t=> ", str3);
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logAvalancheDelete$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = key;
        logMessageImpl.str3 = m;
        logMessageImpl.bool1 = false;
        logBuffer.commit(obtain);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "AvalancheController: ", getStateStr());
    }

    public final String getStateStr() {
        String key = getKey(this.headsUpEntryShowing);
        ArrayList arrayList = new ArrayList();
        Iterator it = this.nextList.iterator();
        while (it.hasNext()) {
            arrayList.add("[" + getKey((BaseHeadsUpManager.HeadsUpEntry) it.next()) + "]");
        }
        String join = String.join("\n", arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = this.nextMap.keySet().iterator();
        while (it2.hasNext()) {
            arrayList2.add("[" + getKey((BaseHeadsUpManager.HeadsUpEntry) it2.next()) + "]");
        }
        String join2 = String.join("\n", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        Iterator it3 = ((HashSet) this.debugDropSet).iterator();
        while (it3.hasNext()) {
            arrayList3.add("[" + getKey((BaseHeadsUpManager.HeadsUpEntry) it3.next()) + "]");
        }
        String join3 = String.join("\n", arrayList3);
        Object invoke = this.baseEntryMapStr.invoke();
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("\nAvalancheController:\n\tshowing: [", key, "]\n\tprevious: []\n\tnext list: ", join, "\n\tnext map: ");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, join2, "\n\tdropped: ", join3, "\nBHUM.mHeadsUpEntryMap: ");
        m.append(invoke);
        return m.toString();
    }

    public final void update(BaseHeadsUpManager.HeadsUpEntry headsUpEntry, Runnable runnable, String str) {
        String key = getKey(headsUpEntry);
        HeadsUpManagerLogger headsUpManagerLogger = this.headsUpManagerLogger;
        String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("NOT ENABLED, run runnable. ", getStateStr());
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logAvalancheUpdate$2 headsUpManagerLogger$logAvalancheUpdate$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAvalancheUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder sb = new StringBuilder();
                sb.append(str1);
                sb.append("\n\t=> AC[isEnabled:");
                sb.append(bool1);
                sb.append("] update: ");
                sb.append(str2);
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, "\n\t=> ", str3);
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logAvalancheUpdate$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = key;
        logMessageImpl.str3 = m;
        logMessageImpl.bool1 = false;
        logBuffer.commit(obtain);
        runnable.run();
    }

    public static /* synthetic */ void getDebugDropSet$annotations() {
    }

    public static /* synthetic */ void getHeadsUpEntryShowing$annotations() {
    }

    public static /* synthetic */ void getNextMap$annotations() {
    }
}
