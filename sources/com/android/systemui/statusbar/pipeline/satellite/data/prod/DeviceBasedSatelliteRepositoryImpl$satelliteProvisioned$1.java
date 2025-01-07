package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.satellite.SatelliteManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SatelliteManager $sm;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1(SatelliteManager satelliteManager, DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceBasedSatelliteRepositoryImpl;
        this.$sm = satelliteManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1 deviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1 = new DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1(this.$sm, this.this$0, continuation);
        deviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.L$0 = obj;
        return deviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(1:(1:(3:5|6|7)(2:9|10))(1:11))(2:21|(1:23)(1:24))|12|13|14|15|(1:17)|6|7) */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0081, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
    
        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.Companion.access$e(r11.this$0.logBuffer, "error registering for provisioning state callback", r3);
     */
    /* JADX WARN: Type inference failed for: r12v6, types: [android.telephony.satellite.SatelliteProvisionStateCallback, com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r11.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L26
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            kotlin.ResultKt.throwOnFailure(r12)
            goto L9f
        L12:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L1a:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            java.lang.Object r5 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L4a
        L26:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            r1 = r12
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r12 = r11.this$0
            android.telephony.satellite.SatelliteManager r5 = r11.$sm
            r11.L$0 = r1
            r11.L$1 = r1
            r11.label = r3
            r12.getClass()
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2 r6 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2
            r6.<init>(r5, r12, r4)
            kotlinx.coroutines.CoroutineDispatcher r12 = r12.bgDispatcher
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r6, r11)
            if (r12 != r0) goto L49
            return r0
        L49:
            r5 = r1
        L4a:
            kotlinx.coroutines.channels.ProducerCoroutine r1 = (kotlinx.coroutines.channels.ProducerCoroutine) r1
            r1.mo1790trySendJP2dKIU(r12)
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$callback$1 r12 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$callback$1
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r1 = r11.this$0
            r12.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r1 = new kotlin.jvm.internal.Ref$BooleanRef
            r1.<init>()
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r6 = r11.this$0     // Catch: java.lang.Exception -> L81
            com.android.systemui.log.LogBuffer r6 = r6.logBuffer     // Catch: java.lang.Exception -> L81
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$1 r7 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.1
                static {
                    /*
                        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$1 r0 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$1)
 com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.1.INSTANCE com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.AnonymousClass1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.AnonymousClass1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.log.core.LogMessage r1 = (com.android.systemui.log.core.LogMessage) r1
                        java.lang.String r0 = "registerForProvisionStateChanged"
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.AnonymousClass1.invoke(java.lang.Object):java.lang.Object");
                }
            }     // Catch: java.lang.Exception -> L81
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$Companion$i$1 r8 = com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE     // Catch: java.lang.Exception -> L81
            com.android.systemui.log.core.LogLevel r9 = com.android.systemui.log.core.LogLevel.INFO     // Catch: java.lang.Exception -> L81
            java.lang.String r10 = "DeviceBasedSatelliteRepo"
            com.android.systemui.log.core.LogMessage r7 = r6.obtain(r10, r9, r7, r4)     // Catch: java.lang.Exception -> L81
            r8.getClass()     // Catch: java.lang.Exception -> L81
            r6.commit(r7)     // Catch: java.lang.Exception -> L81
            android.telephony.satellite.SatelliteManager r6 = r11.$sm     // Catch: java.lang.Exception -> L81
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r7 = r11.this$0     // Catch: java.lang.Exception -> L81
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.bgDispatcher     // Catch: java.lang.Exception -> L81
            java.util.concurrent.Executor r7 = kotlinx.coroutines.ExecutorsKt.asExecutor(r7)     // Catch: java.lang.Exception -> L81
            r6.registerForProvisionStateChanged(r7, r12)     // Catch: java.lang.Exception -> L81
            r1.element = r3     // Catch: java.lang.Exception -> L81
            goto L8b
        L81:
            r3 = move-exception
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r6 = r11.this$0
            com.android.systemui.log.LogBuffer r6 = r6.logBuffer
            java.lang.String r7 = "error registering for provisioning state callback"
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.Companion.access$e(r6, r7, r3)
        L8b:
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$2 r3 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1$2
            android.telephony.satellite.SatelliteManager r6 = r11.$sm
            r3.<init>()
            r11.L$0 = r4
            r11.L$1 = r4
            r11.label = r2
            java.lang.Object r11 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r5, r3, r11)
            if (r11 != r0) goto L9f
            return r0
        L9f:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteProvisioned$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
