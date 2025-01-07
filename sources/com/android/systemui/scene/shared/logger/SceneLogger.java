package com.android.systemui.scene.shared.logger;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneLogger {
    public final LogBuffer logBuffer;

    public SceneLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public static void logOverlayChangeRequested$default(SceneLogger sceneLogger, OverlayKey overlayKey, OverlayKey overlayKey2, String str, int i) {
        if ((i & 1) != 0) {
            overlayKey = null;
        }
        if ((i & 2) != 0) {
            overlayKey2 = null;
        }
        LogLevel logLevel = LogLevel.INFO;
        SceneLogger$logOverlayChangeRequested$2 sceneLogger$logOverlayChangeRequested$2 = new Function1() { // from class: com.android.systemui.scene.shared.logger.SceneLogger$logOverlayChangeRequested$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                StringBuilder sb = new StringBuilder("Overlay change requested: ");
                if (logMessage.getStr1() != null) {
                    sb.append(logMessage.getStr1());
                    sb.append(logMessage.getStr2() == null ? " (hidden)" : AppSearchSchema$Builder$$ExternalSyntheticOutline0.m(" → ", logMessage.getStr2()));
                } else {
                    sb.append(logMessage.getStr2() + " (shown)");
                }
                sb.append(", reason: " + logMessage.getStr3());
                return sb.toString();
            }
        };
        LogBuffer logBuffer = sceneLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("SceneFramework", logLevel, sceneLogger$logOverlayChangeRequested$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = overlayKey != null ? overlayKey.toString() : null;
        logMessageImpl.str2 = overlayKey2 != null ? overlayKey2.toString() : null;
        logMessageImpl.str3 = str;
        logBuffer.commit(obtain);
    }

    public final void logFrameworkEnabled(String str) {
        LogLevel logLevel = LogLevel.WARNING;
        SceneLogger$logFrameworkEnabled$2 sceneLogger$logFrameworkEnabled$2 = new Function1() { // from class: com.android.systemui.scene.shared.logger.SceneLogger$logFrameworkEnabled$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Scene framework is ", logMessage.getBool1() ? "enabled" : "disabled", logMessage.getStr1() != null ? AppSearchSchema$Builder$$ExternalSyntheticOutline0.m(" ", logMessage.getStr1()) : "");
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SceneFramework", logLevel, sceneLogger$logFrameworkEnabled$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = false;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logSceneChanged(SceneKey sceneKey, SceneKey sceneKey2, String str, final boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        Function1 function1 = new Function1() { // from class: com.android.systemui.scene.shared.logger.SceneLogger$logSceneChanged$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean z2 = z;
                StringBuilder sb = new StringBuilder();
                sb.append("Scene changed: " + logMessage.getStr1() + " → " + logMessage.getStr2());
                if (z2) {
                    sb.append(" (instant)");
                }
                sb.append(", reason: " + logMessage.getStr3());
                return sb.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SceneFramework", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = sceneKey.toString();
        logMessageImpl.str2 = sceneKey2.toString();
        logMessageImpl.str3 = str;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logSceneTransition(ObservableTransitionState observableTransitionState) {
        boolean z = observableTransitionState instanceof ObservableTransitionState.Transition;
        LogBuffer logBuffer = this.logBuffer;
        if (z) {
            LogMessage obtain = logBuffer.obtain("SceneFramework", LogLevel.INFO, new Function1() { // from class: com.android.systemui.scene.shared.logger.SceneLogger$logSceneTransition$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Scene transition started: ", logMessage.getStr1(), " → ", logMessage.getStr2());
                }
            }, null);
            ObservableTransitionState.Transition transition = (ObservableTransitionState.Transition) observableTransitionState;
            ((LogMessageImpl) obtain).str1 = transition.fromContent.toString();
            ((LogMessageImpl) obtain).str2 = transition.toContent.toString();
            logBuffer.commit(obtain);
            return;
        }
        if (observableTransitionState instanceof ObservableTransitionState.Idle) {
            LogMessage obtain2 = logBuffer.obtain("SceneFramework", LogLevel.INFO, new Function1() { // from class: com.android.systemui.scene.shared.logger.SceneLogger$logSceneTransition$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Scene transition idle on: ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain2).str1 = ((ObservableTransitionState.Idle) observableTransitionState).currentScene.toString();
            logBuffer.commit(obtain2);
        }
    }
}
