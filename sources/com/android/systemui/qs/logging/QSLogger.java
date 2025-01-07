package com.android.systemui.qs.logging;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.ConstantStringsLoggerImpl;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.statusbar.StatusBarState;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSLogger {
    public final /* synthetic */ ConstantStringsLoggerImpl $$delegate_0;
    public final LogBuffer buffer;
    public final LogBuffer configChangedBuffer;

    public QSLogger(LogBuffer logBuffer, LogBuffer logBuffer2) {
        this.buffer = logBuffer;
        this.configChangedBuffer = logBuffer2;
        this.$$delegate_0 = new ConstantStringsLoggerImpl(logBuffer, "QSLog");
    }

    public static String toStateString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "wrong state" : "active" : "inactive" : "unavailable";
    }

    public final void d(Object obj, final String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        Function1 function1 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$d$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(str, ": ", ((LogMessage) obj2).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, function1, null);
        ((LogMessageImpl) obtain).str1 = obj.toString();
        logBuffer.commit(obtain);
    }

    public final void logAllTilesChangeListening(String str, String str2, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logAllTilesChangeListening$2 qSLogger$logAllTilesChangeListening$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logAllTilesChangeListening$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Tiles listening=" + logMessage.getBool1() + " in " + logMessage.getStr1() + ". " + logMessage.getStr2();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logAllTilesChangeListening$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logException(Exception exc, final String str) {
        LogLevel logLevel = LogLevel.ERROR;
        Function1 function1 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logException$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return str;
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("QSLog", logLevel, function1, exc));
    }

    public final void logHandleClick(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logHandleClick$2 qSLogger$logHandleClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logHandleClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "[" + logMessage.getStr1() + "][" + logMessage.getInt1() + "] Tile handling click.";
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logHandleClick$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logHandleLongClick(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logHandleLongClick$2 qSLogger$logHandleLongClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logHandleLongClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "[" + logMessage.getStr1() + "][" + logMessage.getInt1() + "] Tile handling long click.";
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logHandleLongClick$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logHandleSecondaryClick(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logHandleSecondaryClick$2 qSLogger$logHandleSecondaryClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logHandleSecondaryClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "[" + logMessage.getStr1() + "][" + logMessage.getInt1() + "] Tile handling secondary click.";
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logHandleSecondaryClick$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logInternetTileUpdate(String str, String str2, int i) {
        LogLevel logLevel = LogLevel.VERBOSE;
        QSLogger$logInternetTileUpdate$2 qSLogger$logInternetTileUpdate$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logInternetTileUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(GenericDocument$$ExternalSyntheticOutline0.m("[", str1, "] mLastTileState=", int1, ", Callback="), logMessage.getStr2(), ".");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logInternetTileUpdate$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logOnConfigurationChanged(int i, int i2, boolean z, boolean z2, int i3, int i4, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logOnConfigurationChanged$2 qSLogger$logOnConfigurationChanged$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logOnConfigurationChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int2 = logMessage.getInt2();
                String str2 = "undefined";
                String str3 = int2 != 1 ? int2 != 2 ? "undefined" : "land" : "port";
                int int1 = logMessage.getInt1();
                String str4 = int1 != 1 ? int1 != 2 ? "undefined" : "land" : "port";
                int long1 = (int) logMessage.getLong1();
                String str5 = long1 != 16 ? long1 != 32 ? "undefined" : "long" : "notlong";
                int long2 = (int) logMessage.getLong2();
                if (long2 == 16) {
                    str2 = "notlong";
                } else if (long2 == 32) {
                    str2 = "long";
                }
                boolean bool2 = logMessage.getBool2();
                boolean bool1 = logMessage.getBool1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("config change: ", str1, " orientation=", str3, " (was ");
                PlatformSliderColors$$ExternalSyntheticOutline0.m(m, str4, "), screen layout=", str5, " (was ");
                m.append(str2);
                m.append("), splitShade=");
                m.append(bool2);
                m.append(" (was ");
                return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(m, bool1, ")");
            }
        };
        LogBuffer logBuffer = this.configChangedBuffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logOnConfigurationChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = i2;
        logMessageImpl.long1 = i3;
        logMessageImpl.long2 = i4;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
    }

    public final void logOnViewAttached(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logOnViewAttached$2 qSLogger$logOnViewAttached$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logOnViewAttached$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("onViewAttached: ", logMessage.getStr1(), " orientation ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logOnViewAttached$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logOnViewDetached(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logOnViewDetached$2 qSLogger$logOnViewDetached$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logOnViewDetached$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("onViewDetached: ", logMessage.getStr1(), " orientation ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logOnViewDetached$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logPanelExpanded(String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logPanelExpanded$2 qSLogger$logPanelExpanded$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logPanelExpanded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return logMessage.getStr1() + " expanded=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logPanelExpanded$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logSwitchTileLayout(String str, boolean z, boolean z2, boolean z3) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logSwitchTileLayout$2 qSLogger$logSwitchTileLayout$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logSwitchTileLayout$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("change tile layout: ", str1, " horizontal=", bool1, " (was ");
                m.append(bool2);
                m.append("), force? ");
                m.append(bool3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logSwitchTileLayout$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logMessageImpl.bool3 = z3;
        logBuffer.commit(obtain);
    }

    public final void logTileBackgroundColorUpdateIfInternetTile(int i, int i2, String str, boolean z) {
        if (str.equals("internet")) {
            LogLevel logLevel = LogLevel.VERBOSE;
            QSLogger$logTileBackgroundColorUpdateIfInternetTile$2 qSLogger$logTileBackgroundColorUpdateIfInternetTile$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileBackgroundColorUpdateIfInternetTile$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    int int1 = logMessage.getInt1();
                    boolean bool1 = logMessage.getBool1();
                    int int2 = logMessage.getInt2();
                    StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("[", str1, "] state=", int1, ", disabledByPolicy=");
                    m.append(bool1);
                    m.append(", color=");
                    m.append(int2);
                    m.append(".");
                    return m.toString();
                }
            };
            LogBuffer logBuffer = this.buffer;
            LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileBackgroundColorUpdateIfInternetTile$2, null);
            ((LogMessageImpl) obtain).str1 = str;
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.int1 = i;
            logMessageImpl.bool1 = z;
            logMessageImpl.int2 = i2;
            logBuffer.commit(obtain);
        }
    }

    public final void logTileChangeListening(String str, boolean z) {
        LogLevel logLevel = LogLevel.VERBOSE;
        QSLogger$logTileChangeListening$2 qSLogger$logTileChangeListening$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileChangeListening$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "[" + logMessage.getStr1() + "] Tile listening=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileChangeListening$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logTileClick(String str, int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileClick$2 qSLogger$logTileClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("[", str1, "][", int1, "] Tile clicked. StatusBarState=");
                m.append(str2);
                m.append(". TileState=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileClick$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i3;
        logMessageImpl.str2 = StatusBarState.toString(i);
        logMessageImpl.str3 = toStateString(i2);
        logBuffer.commit(obtain);
    }

    public final void logTileDestroyed(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileDestroyed$2 qSLogger$logTileDestroyed$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileDestroyed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("[", logMessage.getStr1(), "] Tile destroyed. Reason: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileDestroyed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = "Handle destroy";
        logBuffer.commit(obtain);
    }

    public final void logTileDistributed(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileDistributed$2 qSLogger$logTileDistributed$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileDistributed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Adding ", logMessage.getStr1(), " to page number ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileDistributed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logTileDistributionInProgress(int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileDistributionInProgress$2 qSLogger$logTileDistributionInProgress$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileDistributionInProgress$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MutableVectorKt$$ExternalSyntheticOutline0.m(logMessage.getInt1(), logMessage.getInt2(), "Distributing tiles: [tilesPerPageCount=", "] [totalTilesCount=", "]");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileDistributionInProgress$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).int2 = i2;
        logBuffer.commit(obtain);
    }

    public final void logTileLongClick(String str, int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileLongClick$2 qSLogger$logTileLongClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileLongClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("[", str1, "][", int1, "] Tile long clicked. StatusBarState=");
                m.append(str2);
                m.append(". TileState=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileLongClick$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i3;
        logMessageImpl.str2 = StatusBarState.toString(i);
        logMessageImpl.str3 = toStateString(i2);
        logBuffer.commit(obtain);
    }

    public final void logTileSecondaryClick(String str, int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logTileSecondaryClick$2 qSLogger$logTileSecondaryClick$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileSecondaryClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("[", str1, "][", int1, "] Tile secondary clicked. StatusBarState=");
                m.append(str2);
                m.append(". TileState=");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileSecondaryClick$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i3;
        logMessageImpl.str2 = StatusBarState.toString(i);
        logMessageImpl.str3 = toStateString(i2);
        logBuffer.commit(obtain);
    }

    public final void logTileUpdated(QSTile.State state, String str) {
        LogLevel logLevel = LogLevel.VERBOSE;
        QSLogger$logTileUpdated$2 qSLogger$logTileUpdated$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logTileUpdated$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                int int1 = logMessage.getInt1();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("[", str1, "] Tile updated. Label=", str2, ". State=");
                m.append(int1);
                m.append(". Icon=");
                m.append(str3);
                m.append(".");
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logTileUpdated$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        CharSequence charSequence = state.label;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = charSequence != null ? charSequence.toString() : null;
        QSTile.Icon icon = state.icon;
        logMessageImpl.str3 = icon != null ? icon.toString() : null;
        logMessageImpl.int1 = state.state;
        logBuffer.commit(obtain);
    }

    public final void logVisibility(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLogger$logVisibility$2 qSLogger$logVisibility$2 = new Function1() { // from class: com.android.systemui.qs.logging.QSLogger$logVisibility$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(logMessage.getStr1(), " visibility: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("QSLog", logLevel, qSLogger$logVisibility$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = "QS fragment";
        logMessageImpl.str2 = i != 0 ? i != 4 ? i != 8 ? "undefined" : "GONE" : "INVISIBLE" : "VISIBLE";
        logBuffer.commit(obtain);
    }
}
