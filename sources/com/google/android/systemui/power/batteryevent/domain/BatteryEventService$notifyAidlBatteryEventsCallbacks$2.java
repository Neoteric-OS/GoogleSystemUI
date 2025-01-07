package com.google.android.systemui.power.batteryevent.domain;

import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryEventService$notifyAidlBatteryEventsCallbacks$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryEvents $events;
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyAidlBatteryEventsCallbacks$2(BatteryEvents batteryEvents, BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventService;
        this.$events = batteryEvents;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$notifyAidlBatteryEventsCallbacks$2(this.$events, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryEventService$notifyAidlBatteryEventsCallbacks$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0075 A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:6:0x001d, B:8:0x0064, B:10:0x0075, B:12:0x007b, B:13:0x007f, B:16:0x0051), top: B:5:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x007b A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:6:0x001d, B:8:0x0064, B:10:0x0075, B:12:0x007b, B:13:0x007f, B:16:0x0051), top: B:5:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051 A[Catch: all -> 0x0023, TRY_ENTER, TryCatch #0 {all -> 0x0023, blocks: (B:6:0x001d, B:8:0x0064, B:10:0x0075, B:12:0x007b, B:13:0x007f, B:16:0x0051), top: B:5:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0061 -> B:8:0x0064). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = " ms"
            java.lang.String r5 = "BatteryEventService"
            if (r2 == 0) goto L2e
            if (r2 != r3) goto L26
            int r2 = r0.I$1
            int r6 = r0.I$0
            long r7 = r0.J$0
            java.lang.Object r9 = r0.L$1
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r9 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r9
            java.lang.Object r10 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r10 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r10
            kotlin.ResultKt.throwOnFailure(r19)     // Catch: java.lang.Throwable -> L23
            r11 = r19
            goto L64
        L23:
            r0 = move-exception
            goto La1
        L26:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2e:
            kotlin.ResultKt.throwOnFailure(r19)
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r2 = r0.this$0
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r6 = r0.$events
            long r7 = java.lang.System.currentTimeMillis()
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r9 = r2.aidlBatteryEventsCallbackListener
            int r9 = r9.beginBroadcast()
            java.lang.String r10 = "AIDL callback listeners count: "
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r10, r5, r9)
            r10 = 0
            r16 = r10
            r10 = r2
            r2 = r16
            r17 = r9
            r9 = r6
            r6 = r17
        L4f:
            if (r2 >= r6) goto La7
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L23
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L23
            r0.J$0 = r7     // Catch: java.lang.Throwable -> L23
            r0.I$0 = r6     // Catch: java.lang.Throwable -> L23
            r0.I$1 = r2     // Catch: java.lang.Throwable -> L23
            r0.label = r3     // Catch: java.lang.Throwable -> L23
            java.lang.Object r11 = com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$notifyAidlListenerBatteryEventUpdate(r10, r2, r9, r0)     // Catch: java.lang.Throwable -> L23
            if (r11 != r1) goto L64
            return r1
        L64:
            java.lang.Number r11 = (java.lang.Number) r11     // Catch: java.lang.Throwable -> L23
            long r11 = r11.longValue()     // Catch: java.lang.Throwable -> L23
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r13 = r10.aidlBatteryEventsCallbackListener     // Catch: java.lang.Throwable -> L23
            java.lang.Object r13 = r13.getBroadcastCookie(r2)     // Catch: java.lang.Throwable -> L23
            boolean r14 = r13 instanceof com.google.android.systemui.power.batteryevent.aidl.SurfaceType     // Catch: java.lang.Throwable -> L23
            r15 = 0
            if (r14 == 0) goto L78
            com.google.android.systemui.power.batteryevent.aidl.SurfaceType r13 = (com.google.android.systemui.power.batteryevent.aidl.SurfaceType) r13     // Catch: java.lang.Throwable -> L23
            goto L79
        L78:
            r13 = r15
        L79:
            if (r13 == 0) goto L7f
            java.lang.String r15 = r13.name()     // Catch: java.lang.Throwable -> L23
        L7f:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L23
            r13.<init>()     // Catch: java.lang.Throwable -> L23
            java.lang.String r14 = "notify AIDL callback to "
            r13.append(r14)     // Catch: java.lang.Throwable -> L23
            r13.append(r15)     // Catch: java.lang.Throwable -> L23
            java.lang.String r14 = ", cost: "
            r13.append(r14)     // Catch: java.lang.Throwable -> L23
            r13.append(r11)     // Catch: java.lang.Throwable -> L23
            r13.append(r4)     // Catch: java.lang.Throwable -> L23
            java.lang.String r11 = r13.toString()     // Catch: java.lang.Throwable -> L23
            android.util.Log.d(r5, r11)     // Catch: java.lang.Throwable -> L23
            int r2 = r2 + 1
            goto L4f
        La1:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r1 = r10.aidlBatteryEventsCallbackListener
            r1.finishBroadcast()
            throw r0
        La7:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r0 = r10.aidlBatteryEventsCallbackListener
            r0.finishBroadcast()
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 - r7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "notify all AIDL callbacks, cost: "
            r2.<init>(r3)
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = r2.toString()
            int r0 = android.util.Log.i(r5, r0)
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlBatteryEventsCallbacks$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
