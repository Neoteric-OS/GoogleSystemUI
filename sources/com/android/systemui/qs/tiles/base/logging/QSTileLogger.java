package com.android.systemui.qs.tiles.base.logging;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.StatusBarState;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileLogger {
    public final LogBufferFactory factory;
    public final Map logBufferCache;
    public final StatusBarStateController mStatusBarStateController;

    public QSTileLogger(Map map, LogBufferFactory logBufferFactory, StatusBarStateController statusBarStateController) {
        this.factory = logBufferFactory;
        this.mStatusBarStateController = statusBarStateController;
        this.logBufferCache = new LinkedHashMap(map);
    }

    public static String getLogTag(TileSpec tileSpec) {
        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("QSLog_tile__", tileSpec.getSpec());
    }

    public static String toLogString(QSTileState qSTileState) {
        CharSequence charSequence = qSTileState.label;
        CharSequence charSequence2 = qSTileState.secondaryLabel;
        CharSequence charSequence3 = qSTileState.contentDescription;
        CharSequence charSequence4 = qSTileState.stateDescription;
        StringBuilder sb = new StringBuilder("[label=");
        sb.append((Object) charSequence);
        sb.append(", state=");
        sb.append(qSTileState.activationState);
        sb.append(", s_label=");
        sb.append((Object) charSequence2);
        sb.append(", cd=");
        sb.append((Object) charSequence3);
        sb.append(", sd=");
        sb.append((Object) charSequence4);
        sb.append(", svi=");
        sb.append(qSTileState.sideViewIcon);
        sb.append(", enabled=");
        sb.append(QSTileState.EnabledState.ENABLED);
        sb.append(", a11y=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, qSTileState.expandedAccessibilityClassName, "]");
    }

    public final LogBuffer getLogBuffer(TileSpec tileSpec) {
        LogBuffer logBuffer;
        synchronized (this.logBufferCache) {
            try {
                Map map = this.logBufferCache;
                Object obj = map.get(tileSpec);
                if (obj == null) {
                    obj = LogBufferFactory.create$default(this.factory, getLogTag(tileSpec), 25, false, 8);
                    map.put(tileSpec, obj);
                }
                logBuffer = (LogBuffer) obj;
            } catch (Throwable th) {
                throw th;
            }
        }
        return logBuffer;
    }

    public final void logForceUpdate(TileSpec tileSpec) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        logBuffer.commit(logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logForceUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "tile data force update";
            }
        }, null));
    }

    public final void logInfo(TileSpec tileSpec) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.INFO, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logInfo$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str1 = ((LogMessage) obj).getStr1();
                Intrinsics.checkNotNull(str1);
                return str1;
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = "Enrolled in forced night display auto mode";
        logBuffer.commit(obtain);
    }

    public final void logInitialRequest(TileSpec tileSpec) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        logBuffer.commit(logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logInitialRequest$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "tile data initial update";
            }
        }, null));
    }

    public final void logStateUpdate(TileSpec tileSpec, QSTileState qSTileState, Object obj) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logStateUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("tile state update: state=", logMessage.getStr1(), ", data=", logMessage.getStr2());
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = toLogString(qSTileState);
        logMessageImpl.str2 = StringsKt.take(50, String.valueOf(obj));
        logBuffer.commit(obtain);
    }

    public final void logUserActionPipeline(TileSpec tileSpec, QSTileUserAction qSTileUserAction, QSTileState qSTileState, Object obj) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logUserActionPipeline$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                String str1 = logMessage.getStr1();
                String statusBarState = StatusBarState.toString(logMessage.getInt1());
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("tile ", str1, " pipeline: statusBarState=", statusBarState, ", state=");
                m.append(str2);
                m.append(", data=");
                m.append(str3);
                return m.toString();
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = toLogString(qSTileUserAction);
        logMessageImpl.str2 = toLogString(qSTileState);
        logMessageImpl.str3 = StringsKt.take(50, String.valueOf(obj));
        logBuffer.commit(obtain);
    }

    public final void logUserActionRejectedByFalsing(QSTileUserAction qSTileUserAction, TileSpec tileSpec) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logUserActionRejectedByFalsing$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("tile ", ((LogMessage) obj).getStr1(), ": rejected by falsing");
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = toLogString(qSTileUserAction);
        logBuffer.commit(obtain);
    }

    public final void logUserActionRejectedByPolicy(QSTileUserAction qSTileUserAction, TileSpec tileSpec, final String str) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logUserActionRejectedByPolicy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("tile ", ((LogMessage) obj).getStr1(), ": rejected by policy, restriction: ", str);
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = toLogString(qSTileUserAction);
        logBuffer.commit(obtain);
    }

    public final void logWarning(TileSpec tileSpec, String str) {
        LogBuffer logBuffer = getLogBuffer(tileSpec);
        LogMessage obtain = logBuffer.obtain(getLogTag(tileSpec), LogLevel.WARNING, new Function1() { // from class: com.android.systemui.qs.tiles.base.logging.QSTileLogger$logWarning$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str1 = ((LogMessage) obj).getStr1();
                Intrinsics.checkNotNull(str1);
                return str1;
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public static String toLogString(QSTileUserAction qSTileUserAction) {
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            return "click";
        }
        if (qSTileUserAction instanceof QSTileUserAction.ToggleClick) {
            return "toggle click";
        }
        if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            return "long click";
        }
        throw new NoWhenBranchMatchedException();
    }
}
