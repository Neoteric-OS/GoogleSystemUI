package com.google.android.systemui.tips.data.repository;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SetupWizardRepositoryImpl {
    public final StateFlowImpl _isWipedOut;
    public final StateFlowImpl _priorDeviceType;
    public final Context applicationContext;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow isWipedOut;
    public final UiEventLogger logger;
    public final ReadonlyStateFlow priorDeviceType;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LogEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ LogEvent[] $VALUES;
        public static final LogEvent CONTEXTUAL_TIPS_SOURCE_DEVICE_ANDROID;
        public static final LogEvent CONTEXTUAL_TIPS_SOURCE_DEVICE_IOS;
        public static final LogEvent CONTEXTUAL_TIPS_SOURCE_DEVICE_UNKNOWN;
        public static final LogEvent CONTEXTUAL_TIPS_SOURCE_DEVICE_WIPED_OUT;
        private final int _id;

        static {
            LogEvent logEvent = new LogEvent("CONTEXTUAL_TIPS_SOURCE_DEVICE_UNKNOWN", 0, 1701);
            CONTEXTUAL_TIPS_SOURCE_DEVICE_UNKNOWN = logEvent;
            LogEvent logEvent2 = new LogEvent("CONTEXTUAL_TIPS_SOURCE_DEVICE_ANDROID", 1, 1702);
            CONTEXTUAL_TIPS_SOURCE_DEVICE_ANDROID = logEvent2;
            LogEvent logEvent3 = new LogEvent("CONTEXTUAL_TIPS_SOURCE_DEVICE_IOS", 2, 1703);
            CONTEXTUAL_TIPS_SOURCE_DEVICE_IOS = logEvent3;
            LogEvent logEvent4 = new LogEvent("CONTEXTUAL_TIPS_SOURCE_DEVICE_WIPED_OUT", 3, 1704);
            CONTEXTUAL_TIPS_SOURCE_DEVICE_WIPED_OUT = logEvent4;
            LogEvent[] logEventArr = {logEvent, logEvent2, logEvent3, logEvent4};
            $VALUES = logEventArr;
            EnumEntriesKt.enumEntries(logEventArr);
        }

        public LogEvent(String str, int i, int i2) {
            this._id = i2;
        }

        public static LogEvent valueOf(String str) {
            return (LogEvent) Enum.valueOf(LogEvent.class, str);
        }

        public static LogEvent[] values() {
            return (LogEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this._id;
        }
    }

    public SetupWizardRepositoryImpl(Context context, CoroutineDispatcher coroutineDispatcher, UiEventLogger uiEventLogger) {
        this.applicationContext = context;
        this.backgroundDispatcher = coroutineDispatcher;
        this.logger = uiEventLogger;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow("unknown");
        this._priorDeviceType = MutableStateFlow;
        this.priorDeviceType = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isWipedOut = MutableStateFlow2;
        this.isWipedOut = new ReadonlyStateFlow(MutableStateFlow2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSourceDeviceType(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$1
            if (r0 == 0) goto L13
            r0 = r5
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$1 r0 = (com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$1 r0 = new com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L43
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$2 r5 = new com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getSourceDeviceType$2
            r2 = 0
            r5.<init>(r4, r2)
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r4 = r4.backgroundDispatcher
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl.getSourceDeviceType(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refresh(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$refresh$1
            if (r0 == 0) goto L13
            r0 = r7
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$refresh$1 r0 = (com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$refresh$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$refresh$1 r0 = new com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$refresh$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.MutableStateFlow r6 = (kotlinx.coroutines.flow.MutableStateFlow) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L74
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.MutableStateFlow r6 = (kotlinx.coroutines.flow.MutableStateFlow) r6
            java.lang.Object r2 = r0.L$0
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl r2 = (com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L58
        L42:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            kotlinx.coroutines.flow.StateFlowImpl r7 = r6._priorDeviceType
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r2 = r6.getSourceDeviceType(r0)
            if (r2 != r1) goto L54
            return r1
        L54:
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
        L58:
            kotlinx.coroutines.flow.StateFlowImpl r6 = (kotlinx.coroutines.flow.StateFlowImpl) r6
            r6.setValue(r7)
            kotlinx.coroutines.flow.StateFlowImpl r6 = r2._isWipedOut
            r0.L$0 = r6
            r7 = 0
            r0.L$1 = r7
            r0.label = r3
            com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getWipedOut$2 r3 = new com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl$getWipedOut$2
            r3.<init>(r2, r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = r2.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r3, r0)
            if (r7 != r1) goto L74
            return r1
        L74:
            kotlinx.coroutines.flow.StateFlowImpl r6 = (kotlinx.coroutines.flow.StateFlowImpl) r6
            r6.setValue(r7)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl.refresh(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
