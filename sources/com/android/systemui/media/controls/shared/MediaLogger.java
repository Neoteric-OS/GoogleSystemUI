package com.android.systemui.media.controls.shared;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaLogger {
    public final LogBuffer buffer;

    public MediaLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logDuplicateMediaNotification(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logDuplicateMediaNotification$2 mediaLogger$logDuplicateMediaNotification$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logDuplicateMediaNotification$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("duplicate media notification ", ((LogMessage) obj).getStr1(), " posted");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logDuplicateMediaNotification$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logMediaCardAdded(InstanceId instanceId) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaCardAdded$2 mediaLogger$logMediaCardAdded$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaCardAdded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("adding media card ", ((LogMessage) obj).getStr1(), " to carousel");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaCardAdded$2, null);
        ((LogMessageImpl) obtain).str1 = instanceId.toString();
        logBuffer.commit(obtain);
    }

    public final void logMediaCardRemoved(InstanceId instanceId) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaCardRemoved$2 mediaLogger$logMediaCardRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaCardRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("removing media card ", ((LogMessage) obj).getStr1(), " from carousel");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaCardRemoved$2, null);
        ((LogMessageImpl) obtain).str1 = instanceId.toString();
        logBuffer.commit(obtain);
    }

    public final void logMediaLoaded(InstanceId instanceId, boolean z, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaLoaded$2 mediaLogger$logMediaLoaded$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaLoaded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                String str2 = logMessage.getStr2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("add media ", str1, ", active: ", bool1, ", reason: ");
                m.append(str2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaLoaded$2, null);
        ((LogMessageImpl) obtain).str1 = instanceId.toString();
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = z;
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logMediaRecommendationCardAdded(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaRecommendationCardAdded$2 mediaLogger$logMediaRecommendationCardAdded$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaRecommendationCardAdded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("adding recommendation card ", ((LogMessage) obj).getStr1(), " to carousel");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaRecommendationCardAdded$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logMediaRecommendationCardRemoved(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaRecommendationCardRemoved$2 mediaLogger$logMediaRecommendationCardRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaRecommendationCardRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("removing recommendation card ", ((LogMessage) obj).getStr1(), " from carousel");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaRecommendationCardRemoved$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logMediaRemoved(InstanceId instanceId, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logMediaRemoved$2 mediaLogger$logMediaRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logMediaRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("removing media ", logMessage.getStr1(), ", reason: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logMediaRemoved$2, null);
        ((LogMessageImpl) obtain).str1 = instanceId.toString();
        ((LogMessageImpl) obtain).str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logRecommendationLoaded(String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logRecommendationLoaded$2 mediaLogger$logRecommendationLoaded$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logRecommendationLoaded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                String str2 = logMessage.getStr2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("add recommendation ", str1, ", active ", bool1, ", reason: ");
                m.append(str2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logRecommendationLoaded$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.bool1 = z;
        logMessageImpl.str2 = "loading recommendations";
        logBuffer.commit(obtain);
    }

    public final void logRecommendationRemoved(String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaLogger$logRecommendationRemoved$2 mediaLogger$logRecommendationRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.shared.MediaLogger$logRecommendationRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                String str2 = logMessage.getStr2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("removing recommendation ", str1, ", immediate=", bool1, ", reason: ");
                m.append(str2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaLog", logLevel, mediaLogger$logRecommendationRemoved$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.bool1 = z;
        logMessageImpl.str2 = "removing recommendations card";
        logBuffer.commit(obtain);
    }
}
