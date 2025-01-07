package com.android.systemui.unfold;

import android.R;
import android.content.Context;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.unfold.domain.interactor.UnfoldTransitionInteractor;
import com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl;
import com.android.systemui.util.time.SystemClock;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplaySwitchLatencyTracker implements CoreStartable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long SCREEN_EVENT_TIMEOUT = Duration.ofMillis(15000).toMillis();
    public final AnimationStatusRepositoryImpl animationStatusRepository;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Context context;
    public final DeviceStateRepositoryImpl deviceStateRepository;
    public final DisplaySwitchLatencyLogger displaySwitchLatencyLogger;
    public final KeyguardInteractor keyguardInteractor;
    public final PowerInteractor powerInteractor;
    public final SystemClock systemClock;
    public final UnfoldTransitionInteractor unfoldTransitionInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplaySwitchLatencyEvent {
        public final int fromFoldableDeviceState;
        public final Set fromVisibleAppsUid;
        public final int latencyMs;
        public final int toFoldableDeviceState;
        public final int toState;
        public final Set toVisibleAppsUid;

        public DisplaySwitchLatencyEvent(int i, int i2, Set set, int i3, int i4, Set set2) {
            this.latencyMs = i;
            this.fromFoldableDeviceState = i2;
            this.fromVisibleAppsUid = set;
            this.toFoldableDeviceState = i3;
            this.toState = i4;
            this.toVisibleAppsUid = set2;
        }

        public static DisplaySwitchLatencyEvent copy$default(DisplaySwitchLatencyEvent displaySwitchLatencyEvent, int i, int i2, int i3, int i4, int i5) {
            if ((i5 & 1) != 0) {
                i = displaySwitchLatencyEvent.latencyMs;
            }
            int i6 = i;
            if ((i5 & 2) != 0) {
                i2 = displaySwitchLatencyEvent.fromFoldableDeviceState;
            }
            int i7 = i2;
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            Set set = displaySwitchLatencyEvent.fromVisibleAppsUid;
            displaySwitchLatencyEvent.getClass();
            if ((i5 & 128) != 0) {
                i3 = displaySwitchLatencyEvent.toFoldableDeviceState;
            }
            int i8 = i3;
            if ((i5 & 256) != 0) {
                i4 = displaySwitchLatencyEvent.toState;
            }
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            Set set2 = displaySwitchLatencyEvent.toVisibleAppsUid;
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            displaySwitchLatencyEvent.getClass();
            return new DisplaySwitchLatencyEvent(i6, i7, set, i8, i4, set2);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DisplaySwitchLatencyEvent)) {
                return false;
            }
            DisplaySwitchLatencyEvent displaySwitchLatencyEvent = (DisplaySwitchLatencyEvent) obj;
            return this.latencyMs == displaySwitchLatencyEvent.latencyMs && this.fromFoldableDeviceState == displaySwitchLatencyEvent.fromFoldableDeviceState && Intrinsics.areEqual(this.fromVisibleAppsUid, displaySwitchLatencyEvent.fromVisibleAppsUid) && this.toFoldableDeviceState == displaySwitchLatencyEvent.toFoldableDeviceState && this.toState == displaySwitchLatencyEvent.toState && Intrinsics.areEqual(this.toVisibleAppsUid, displaySwitchLatencyEvent.toVisibleAppsUid);
        }

        public final int hashCode() {
            return Integer.hashCode(-1) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, (this.toVisibleAppsUid.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.toState, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.toFoldableDeviceState, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, (this.fromVisibleAppsUid.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(-1, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.fromFoldableDeviceState, Integer.hashCode(this.latencyMs) * 31, 31), 31), 31), 31)) * 31, 31), 31), 31), 31), 31)) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
        }

        public final String toString() {
            return "DisplaySwitchLatencyEvent(latencyMs=" + this.latencyMs + ", fromFoldableDeviceState=" + this.fromFoldableDeviceState + ", fromState=0, fromFocusedAppUid=-1, fromPipAppUid=-1, fromVisibleAppsUid=" + this.fromVisibleAppsUid + ", fromDensityDpi=-1, toFoldableDeviceState=" + this.toFoldableDeviceState + ", toState=" + this.toState + ", toFocusedAppUid=-1, toPipAppUid=-1, toVisibleAppsUid=" + this.toVisibleAppsUid + ", toDensityDpi=-1, notificationCount=-1, externalDisplayCount=-1, throttlingLevel=0, vskinTemperatureC=-1, hallSensorToFirstHingeAngleChangeMs=-1, hallSensorToDeviceStateChangeMs=-1, onScreenTurningOnToOnDrawnMs=-1, onDrawnToOnScreenTurnedOnMs=-1)";
        }
    }

    public DisplaySwitchLatencyTracker(Context context, DeviceStateRepositoryImpl deviceStateRepositoryImpl, PowerInteractor powerInteractor, UnfoldTransitionInteractor unfoldTransitionInteractor, AnimationStatusRepositoryImpl animationStatusRepositoryImpl, KeyguardInteractor keyguardInteractor, Executor executor, CoroutineScope coroutineScope, DisplaySwitchLatencyLogger displaySwitchLatencyLogger, SystemClock systemClock) {
        this.context = context;
        this.deviceStateRepository = deviceStateRepositoryImpl;
        this.powerInteractor = powerInteractor;
        this.unfoldTransitionInteractor = unfoldTransitionInteractor;
        this.animationStatusRepository = animationStatusRepositoryImpl;
        this.keyguardInteractor = keyguardInteractor;
        this.applicationScope = coroutineScope;
        this.displaySwitchLatencyLogger = displaySwitchLatencyLogger;
        this.systemClock = systemClock;
        this.backgroundDispatcher = ExecutorsKt.from(executor);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0088 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForDisplaySwitch(com.android.systemui.unfold.DisplaySwitchLatencyTracker r10, int r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.DisplaySwitchLatencyTracker.access$waitForDisplaySwitch(com.android.systemui.unfold.DisplaySwitchLatencyTracker, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForGoToSleepWithScreenOff(final com.android.systemui.unfold.DisplaySwitchLatencyTracker r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9.getClass()
            boolean r0 = r10 instanceof com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$1
            if (r0 == 0) goto L16
            r0 = r10
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$1 r0 = (com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$1 r0 = new com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$1
            r0.<init>(r9, r10)
        L1b:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 4096(0x1000, double:2.0237E-320)
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            int r9 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L32
            goto L68
        L32:
            r10 = move-exception
            goto L75
        L34:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.concurrent.ThreadLocalRandom r10 = java.util.concurrent.ThreadLocalRandom.current()
            int r10 = r10.nextInt()
            java.lang.String r2 = "DisplaySwitchLatency"
            java.lang.String r6 = "waitForGoToSleepWithScreenOff()"
            android.os.Trace.asyncTraceForTrackBegin(r4, r2, r6, r10)
            com.android.systemui.power.domain.interactor.PowerInteractor r6 = r9.powerInteractor     // Catch: java.lang.Throwable -> L70
            kotlinx.coroutines.flow.ReadonlyStateFlow r6 = r6.detailedWakefulness     // Catch: java.lang.Throwable -> L70
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$lambda$4$$inlined$filter$1 r7 = new com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForGoToSleepWithScreenOff$lambda$4$$inlined$filter$1     // Catch: java.lang.Throwable -> L70
            r7.<init>()     // Catch: java.lang.Throwable -> L70
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L70
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L70
            r0.label = r3     // Catch: java.lang.Throwable -> L70
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r7, r0)     // Catch: java.lang.Throwable -> L70
            if (r9 != r1) goto L64
            goto L6f
        L64:
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L68:
            com.android.systemui.power.shared.model.WakefulnessModel r10 = (com.android.systemui.power.shared.model.WakefulnessModel) r10     // Catch: java.lang.Throwable -> L32
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L6f:
            return r1
        L70:
            r9 = move-exception
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L75:
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.DisplaySwitchLatencyTracker.access$waitForGoToSleepWithScreenOff(com.android.systemui.unfold.DisplaySwitchLatencyTracker, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForScreenTurnedOn(com.android.systemui.unfold.DisplaySwitchLatencyTracker r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9.getClass()
            boolean r0 = r10 instanceof com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForScreenTurnedOn$1
            if (r0 == 0) goto L16
            r0 = r10
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForScreenTurnedOn$1 r0 = (com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForScreenTurnedOn$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForScreenTurnedOn$1 r0 = new com.android.systemui.unfold.DisplaySwitchLatencyTracker$waitForScreenTurnedOn$1
            r0.<init>(r9, r10)
        L1b:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 4096(0x1000, double:2.0237E-320)
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            int r9 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L32
            goto L69
        L32:
            r10 = move-exception
            goto L76
        L34:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.concurrent.ThreadLocalRandom r10 = java.util.concurrent.ThreadLocalRandom.current()
            int r10 = r10.nextInt()
            java.lang.String r2 = "DisplaySwitchLatency"
            java.lang.String r6 = "waitForScreenTurnedOn()"
            android.os.Trace.asyncTraceForTrackBegin(r4, r2, r6, r10)
            com.android.systemui.power.domain.interactor.PowerInteractor r9 = r9.powerInteractor     // Catch: java.lang.Throwable -> L71
            kotlinx.coroutines.flow.ReadonlyStateFlow r9 = r9.screenPowerState     // Catch: java.lang.Throwable -> L71
            com.android.systemui.unfold.DisplaySwitchLatencyTracker$start$1$invokeSuspend$$inlined$filter$1 r6 = new com.android.systemui.unfold.DisplaySwitchLatencyTracker$start$1$invokeSuspend$$inlined$filter$1     // Catch: java.lang.Throwable -> L71
            r7 = 1
            r6.<init>(r9, r7)     // Catch: java.lang.Throwable -> L71
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L71
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L71
            r0.label = r3     // Catch: java.lang.Throwable -> L71
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r6, r0)     // Catch: java.lang.Throwable -> L71
            if (r9 != r1) goto L65
            goto L70
        L65:
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L69:
            com.android.systemui.power.shared.model.ScreenPowerState r10 = (com.android.systemui.power.shared.model.ScreenPowerState) r10     // Catch: java.lang.Throwable -> L32
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L70:
            return r1
        L71:
            r9 = move-exception
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L76:
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.DisplaySwitchLatencyTracker.access$waitForScreenTurnedOn(com.android.systemui.unfold.DisplaySwitchLatencyTracker, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final boolean isAsleepDueToFold() {
        WakefulnessModel wakefulnessModel = (WakefulnessModel) ((StateFlowImpl) this.powerInteractor.detailedWakefulness.$$delegate_0).getValue();
        if (wakefulnessModel.isAsleep()) {
            if (wakefulnessModel.lastSleepReason == WakeSleepReason.FOLD) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.context.getResources().getIntArray(R.array.config_fontManagerServiceCerts).length != 0) {
            BuildersKt.launch$default(this.applicationScope, this.backgroundDispatcher, null, new DisplaySwitchLatencyTracker$start$1(this, null), 2);
        }
    }
}
