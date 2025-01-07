package com.android.systemui.statusbar.pipeline.mobile.ui;

import android.view.View;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileViewLogger implements Dumpable {
    public final LogBuffer buffer;
    public final Map collectionStatuses;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static String getIdForLogging(Object obj) {
            return Integer.toHexString(System.identityHashCode(obj));
        }
    }

    public MobileViewLogger(LogBuffer logBuffer, DumpManager dumpManager) {
        this.buffer = logBuffer;
        dumpManager.registerNormalDumpable(this);
        this.collectionStatuses = new LinkedHashMap();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(final PrintWriter printWriter, String[] strArr) {
        printWriter.println("Collection statuses per view:---");
        this.collectionStatuses.forEach(new BiConsumer() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger$dump$1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                printWriter.println("viewId=" + ((String) obj) + ", isCollecting=" + ((Boolean) obj2));
            }
        });
    }

    public final void logCollectionStarted(View view, LocationBasedMobileViewModel locationBasedMobileViewModel) {
        this.collectionStatuses.put(Companion.getIdForLogging(view), Boolean.TRUE);
        LogLevel logLevel = LogLevel.INFO;
        MobileViewLogger$logCollectionStarted$2 mobileViewLogger$logCollectionStarted$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger$logCollectionStarted$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Collection started. viewId=", str1, ", viewModelId=", str2, ", viewModelLocation=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileViewLogger", logLevel, mobileViewLogger$logCollectionStarted$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = Companion.getIdForLogging(view);
        logMessageImpl.str2 = Companion.getIdForLogging(locationBasedMobileViewModel);
        logMessageImpl.str3 = locationBasedMobileViewModel.location.name();
        logBuffer.commit(obtain);
    }

    public final void logCollectionStopped(View view, LocationBasedMobileViewModel locationBasedMobileViewModel) {
        this.collectionStatuses.put(Companion.getIdForLogging(view), Boolean.FALSE);
        LogLevel logLevel = LogLevel.INFO;
        MobileViewLogger$logCollectionStopped$2 mobileViewLogger$logCollectionStopped$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger$logCollectionStopped$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Collection stopped. viewId=", str1, ", viewModelId=", str2, ", viewModelLocation=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileViewLogger", logLevel, mobileViewLogger$logCollectionStopped$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = Companion.getIdForLogging(view);
        logMessageImpl.str2 = Companion.getIdForLogging(locationBasedMobileViewModel);
        logMessageImpl.str3 = locationBasedMobileViewModel.location.name();
        logBuffer.commit(obtain);
    }

    public final void logNewViewBinding(View view, LocationBasedMobileViewModel locationBasedMobileViewModel) {
        LogLevel logLevel = LogLevel.INFO;
        MobileViewLogger$logNewViewBinding$2 mobileViewLogger$logNewViewBinding$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger$logNewViewBinding$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("New view binding. viewId=", str1, ", viewModelId=", str2, ", viewModelLocation=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileViewLogger", logLevel, mobileViewLogger$logNewViewBinding$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = Companion.getIdForLogging(view);
        logMessageImpl.str2 = Companion.getIdForLogging(locationBasedMobileViewModel);
        logMessageImpl.str3 = locationBasedMobileViewModel.location.name();
        logBuffer.commit(obtain);
    }
}
