package com.android.systemui.qs.pipeline.shared.logging;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPipelineLogger {
    public final LogBuffer restoreLogBuffer;
    public final LogBuffer tileAutoAddLogBuffer;
    public final LogBuffer tileListLogBuffer;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RestorePreprocessorStep {
        public static final /* synthetic */ RestorePreprocessorStep[] $VALUES;
        public static final RestorePreprocessorStep POSTPROCESSING;
        public static final RestorePreprocessorStep PREPROCESSING;

        static {
            RestorePreprocessorStep restorePreprocessorStep = new RestorePreprocessorStep("PREPROCESSING", 0);
            PREPROCESSING = restorePreprocessorStep;
            RestorePreprocessorStep restorePreprocessorStep2 = new RestorePreprocessorStep("POSTPROCESSING", 1);
            POSTPROCESSING = restorePreprocessorStep2;
            RestorePreprocessorStep[] restorePreprocessorStepArr = {restorePreprocessorStep, restorePreprocessorStep2};
            $VALUES = restorePreprocessorStepArr;
            EnumEntriesKt.enumEntries(restorePreprocessorStepArr);
        }

        public static RestorePreprocessorStep valueOf(String str) {
            return (RestorePreprocessorStep) Enum.valueOf(RestorePreprocessorStep.class, str);
        }

        public static RestorePreprocessorStep[] values() {
            return (RestorePreprocessorStep[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileDestroyedReason {
        public static final /* synthetic */ TileDestroyedReason[] $VALUES;
        public static final TileDestroyedReason CUSTOM_TILE_USER_CHANGED;
        public static final TileDestroyedReason EXISTING_TILE_NOT_AVAILABLE;
        public static final TileDestroyedReason NEW_TILE_NOT_AVAILABLE;
        public static final TileDestroyedReason TILE_NOT_PRESENT_IN_NEW_USER;
        public static final TileDestroyedReason TILE_REMOVED;
        private final String readable;

        static {
            TileDestroyedReason tileDestroyedReason = new TileDestroyedReason("TILE_REMOVED", "Tile removed from  current set", 0);
            TILE_REMOVED = tileDestroyedReason;
            TileDestroyedReason tileDestroyedReason2 = new TileDestroyedReason("CUSTOM_TILE_USER_CHANGED", "User changed for custom tile", 1);
            CUSTOM_TILE_USER_CHANGED = tileDestroyedReason2;
            TileDestroyedReason tileDestroyedReason3 = new TileDestroyedReason("NEW_TILE_NOT_AVAILABLE", "New tile not available", 2);
            NEW_TILE_NOT_AVAILABLE = tileDestroyedReason3;
            TileDestroyedReason tileDestroyedReason4 = new TileDestroyedReason("EXISTING_TILE_NOT_AVAILABLE", "Existing tile not available", 3);
            EXISTING_TILE_NOT_AVAILABLE = tileDestroyedReason4;
            TileDestroyedReason tileDestroyedReason5 = new TileDestroyedReason("TILE_NOT_PRESENT_IN_NEW_USER", "Tile not present in new user", 4);
            TILE_NOT_PRESENT_IN_NEW_USER = tileDestroyedReason5;
            TileDestroyedReason[] tileDestroyedReasonArr = {tileDestroyedReason, tileDestroyedReason2, tileDestroyedReason3, tileDestroyedReason4, tileDestroyedReason5};
            $VALUES = tileDestroyedReasonArr;
            EnumEntriesKt.enumEntries(tileDestroyedReasonArr);
        }

        public TileDestroyedReason(String str, String str2, int i) {
            this.readable = str2;
        }

        public static TileDestroyedReason valueOf(String str) {
            return (TileDestroyedReason) Enum.valueOf(TileDestroyedReason.class, str);
        }

        public static TileDestroyedReason[] values() {
            return (TileDestroyedReason[]) $VALUES.clone();
        }

        public final String getReadable() {
            return this.readable;
        }
    }

    public QSPipelineLogger(LogBuffer logBuffer, LogBuffer logBuffer2, LogBuffer logBuffer3) {
        this.tileListLogBuffer = logBuffer;
        this.tileAutoAddLogBuffer = logBuffer2;
        this.restoreLogBuffer = logBuffer3;
    }

    public final void logAutoAddTilesParsed(int i, Set set) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logAutoAddTilesParsed$2 qSPipelineLogger$logAutoAddTilesParsed$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logAutoAddTilesParsed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Auto add tiles parsed for user " + logMessage.getInt1() + ": " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.tileAutoAddLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSAutoAddableLog", logLevel, qSPipelineLogger$logAutoAddTilesParsed$2, null);
        ((LogMessageImpl) obtain).str1 = set.toString();
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logAutoAddTilesRestoredReconciled(int i, Set set) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logAutoAddTilesRestoredReconciled$2 qSPipelineLogger$logAutoAddTilesRestoredReconciled$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logAutoAddTilesRestoredReconciled$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Auto-add tiles reconciled for user " + logMessage.getInt1() + ": " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.tileAutoAddLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSAutoAddableLog", logLevel, qSPipelineLogger$logAutoAddTilesRestoredReconciled$2, null);
        ((LogMessageImpl) obtain).str1 = set.toString();
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logParsedTiles(int i, List list, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logParsedTiles$2 qSPipelineLogger$logParsedTiles$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logParsedTiles$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Parsed tiles (default=" + logMessage.getBool1() + ", user=" + logMessage.getInt1() + "): " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logParsedTiles$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = list.toString();
        logMessageImpl.bool1 = z;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logProcessTileChange(UserTileSpecRepository.ChangeAction changeAction, List list, int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logProcessTileChange$2 qSPipelineLogger$logProcessTileChange$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logProcessTileChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                String str2 = logMessage.getStr2();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("Processing ", str1, " for user ", int1, "\nNew list: ");
                m.append(str2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logProcessTileChange$2, null);
        ((LogMessageImpl) obtain).str1 = changeAction.toString();
        String obj = list.toString();
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = obj;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logRestoreProcessorApplied(String str, RestorePreprocessorStep restorePreprocessorStep) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logRestoreProcessorApplied$2 qSPipelineLogger$logRestoreProcessorApplied$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logRestoreProcessorApplied$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Restore ", logMessage.getStr2(), " processed by ", logMessage.getStr1());
            }
        };
        LogBuffer logBuffer = this.restoreLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSRestoreLog", logLevel, qSPipelineLogger$logRestoreProcessorApplied$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = restorePreprocessorStep.name();
        logBuffer.commit(obtain);
    }

    public final void logSettingsRestored(RestoreData restoreData) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logSettingsRestored$2 qSPipelineLogger$logSettingsRestored$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logSettingsRestored$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Restored settings data for user " + logMessage.getInt1() + "\n\tRestored tiles: " + logMessage.getStr1() + "\n\tRestored auto added tiles: " + logMessage.getStr2();
            }
        };
        LogBuffer logBuffer = this.restoreLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSRestoreLog", logLevel, qSPipelineLogger$logSettingsRestored$2, null);
        ((LogMessageImpl) obtain).int1 = restoreData.userId;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = restoreData.restoredTiles.toString();
        logMessageImpl.str2 = restoreData.restoredAutoAddedTiles.toString();
        logBuffer.commit(obtain);
    }

    public final void logSettingsRestoredOnUserSetupComplete(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logSettingsRestoredOnUserSetupComplete$2 qSPipelineLogger$logSettingsRestoredOnUserSetupComplete$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logSettingsRestoredOnUserSetupComplete$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Restored from single intent after user setup complete for user ");
            }
        };
        LogBuffer logBuffer = this.restoreLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSRestoreLog", logLevel, qSPipelineLogger$logSettingsRestoredOnUserSetupComplete$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logTileAutoAdded(int i, TileSpec tileSpec, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTileAutoAdded$2 qSPipelineLogger$logTileAutoAdded$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileAutoAdded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("Tile ", str1, " auto added for user ", int1, " at position ");
                m.append(int2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.tileAutoAddLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSAutoAddableLog", logLevel, qSPipelineLogger$logTileAutoAdded$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int2 = i2;
        logMessageImpl.str1 = tileSpec.toString();
        logBuffer.commit(obtain);
    }

    public final void logTileAutoRemoved(int i, TileSpec tileSpec) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTileAutoRemoved$2 qSPipelineLogger$logTileAutoRemoved$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileAutoRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Tile ", logMessage.getStr1(), " auto removed for user ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.tileAutoAddLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSAutoAddableLog", logLevel, qSPipelineLogger$logTileAutoRemoved$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).str1 = tileSpec.toString();
        logBuffer.commit(obtain);
    }

    public final void logTileCreated(TileSpec tileSpec) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTileCreated$2 qSPipelineLogger$logTileCreated$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileCreated$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Tile ", ((LogMessage) obj).getStr1(), " created");
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTileCreated$2, null);
        ((LogMessageImpl) obtain).str1 = tileSpec.toString();
        logBuffer.commit(obtain);
    }

    public final void logTileDestroyed(TileSpec tileSpec, TileDestroyedReason tileDestroyedReason) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTileDestroyed$2 qSPipelineLogger$logTileDestroyed$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileDestroyed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Tile ", logMessage.getStr1(), " destroyed. Reason: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTileDestroyed$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = tileSpec.toString();
        logMessageImpl.str2 = tileDestroyedReason.getReadable();
        logBuffer.commit(obtain);
    }

    public final void logTileNotFoundInFactory(TileSpec tileSpec) {
        LogLevel logLevel = LogLevel.VERBOSE;
        QSPipelineLogger$logTileNotFoundInFactory$2 qSPipelineLogger$logTileNotFoundInFactory$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileNotFoundInFactory$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Tile ", ((LogMessage) obj).getStr1(), " not found in factory");
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTileNotFoundInFactory$2, null);
        ((LogMessageImpl) obtain).str1 = tileSpec.toString();
        logBuffer.commit(obtain);
    }

    public final void logTileUnmarked(int i, TileSpec tileSpec) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTileUnmarked$2 qSPipelineLogger$logTileUnmarked$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileUnmarked$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Tile ", logMessage.getStr1(), " unmarked as auto-added for user ", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.tileAutoAddLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSAutoAddableLog", logLevel, qSPipelineLogger$logTileUnmarked$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).str1 = tileSpec.toString();
        logBuffer.commit(obtain);
    }

    public final void logTileUserChanged(int i, TileSpec tileSpec) {
        LogLevel logLevel = LogLevel.VERBOSE;
        QSPipelineLogger$logTileUserChanged$2 qSPipelineLogger$logTileUserChanged$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTileUserChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "User changed to " + logMessage.getInt1() + " for tile " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTileUserChanged$2, null);
        ((LogMessageImpl) obtain).str1 = tileSpec.toString();
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logTilesNotInstalled(int i, Collection collection) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTilesNotInstalled$2 qSPipelineLogger$logTilesNotInstalled$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTilesNotInstalled$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Tiles kept for not installed packages for user " + logMessage.getInt1() + ": " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTilesNotInstalled$2, null);
        ((LogMessageImpl) obtain).str1 = collection.toString();
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logTilesRestoredAndReconciled(int i, List list, List list2) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logTilesRestoredAndReconciled$2 qSPipelineLogger$logTilesRestoredAndReconciled$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logTilesRestoredAndReconciled$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Tiles restored and reconciled for user: " + logMessage.getInt1() + "\nWas: " + logMessage.getStr1() + "\nSet to: " + logMessage.getStr2();
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        LogMessage obtain = logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logTilesRestoredAndReconciled$2, null);
        ((LogMessageImpl) obtain).str1 = list.toString();
        String obj = list2.toString();
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = obj;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logUsingRetailTiles() {
        LogLevel logLevel = LogLevel.DEBUG;
        QSPipelineLogger$logUsingRetailTiles$2 qSPipelineLogger$logUsingRetailTiles$2 = new Function1() { // from class: com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$logUsingRetailTiles$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Using retail tiles";
            }
        };
        LogBuffer logBuffer = this.tileListLogBuffer;
        logBuffer.commit(logBuffer.obtain("QSTileListLog", logLevel, qSPipelineLogger$logUsingRetailTiles$2, null));
    }
}
