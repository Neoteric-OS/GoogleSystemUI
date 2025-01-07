package com.android.systemui.communal.shared.log;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSceneLogger {
    public final LogBuffer logBuffer;

    public CommunalSceneLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void logSceneChangeCommitted(SceneKey sceneKey, SceneKey sceneKey2) {
        LogLevel logLevel = LogLevel.INFO;
        CommunalSceneLogger$logSceneChangeCommitted$2 communalSceneLogger$logSceneChangeCommitted$2 = new Function1() { // from class: com.android.systemui.communal.shared.log.CommunalSceneLogger$logSceneChangeCommitted$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Scene change committed: ", logMessage.getStr1(), " → ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("CommunalSceneLogger", logLevel, communalSceneLogger$logSceneChangeCommitted$2, null);
        ((LogMessageImpl) obtain).str1 = sceneKey.toString();
        ((LogMessageImpl) obtain).str2 = sceneKey2.toString();
        logBuffer.commit(obtain);
    }

    public final void logSceneChangeRequested(SceneKey sceneKey, SceneKey sceneKey2, String str, final boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        Function1 function1 = new Function1() { // from class: com.android.systemui.communal.shared.log.CommunalSceneLogger$logSceneChangeRequested$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean z2 = z;
                StringBuilder sb = new StringBuilder();
                sb.append("Scene change requested: " + logMessage.getStr1() + " → " + logMessage.getStr2());
                if (z2) {
                    sb.append(" (instant)");
                }
                sb.append(", reason: " + logMessage.getStr3());
                return sb.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("CommunalSceneLogger", logLevel, function1, null);
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
            LogMessage obtain = logBuffer.obtain("CommunalSceneLogger", LogLevel.INFO, new Function1() { // from class: com.android.systemui.communal.shared.log.CommunalSceneLogger$logSceneTransition$2
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
            LogMessage obtain2 = logBuffer.obtain("CommunalSceneLogger", LogLevel.INFO, new Function1() { // from class: com.android.systemui.communal.shared.log.CommunalSceneLogger$logSceneTransition$4
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
