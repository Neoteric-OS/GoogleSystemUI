package com.android.systemui.util.settings;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SettingsProxyExt$observerFlow$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $names;
    final /* synthetic */ SettingsProxy $this_observerFlow;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsProxyExt$observerFlow$2(String[] strArr, SettingsProxy settingsProxy, Continuation continuation) {
        super(2, continuation);
        this.$names = strArr;
        this.$this_observerFlow = settingsProxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SettingsProxyExt$observerFlow$2 settingsProxyExt$observerFlow$2 = new SettingsProxyExt$observerFlow$2(this.$names, this.$this_observerFlow, continuation);
        settingsProxyExt$observerFlow$2.L$0 = obj;
        return settingsProxyExt$observerFlow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SettingsProxyExt$observerFlow$2) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0074, code lost:
    
        if (r15 != r0) goto L19;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0076  */
    /* JADX WARN: Type inference failed for: r8v3 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r14.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L33
            if (r1 == r4) goto L1b
            if (r1 != r3) goto L13
            kotlin.ResultKt.throwOnFailure(r15)
            goto L8e
        L13:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L1b:
            int r1 = r14.I$1
            int r5 = r14.I$0
            java.lang.Object r6 = r14.L$3
            com.android.systemui.util.settings.SettingsProxy r6 = (com.android.systemui.util.settings.SettingsProxy) r6
            java.lang.Object r7 = r14.L$2
            java.lang.String[] r7 = (java.lang.String[]) r7
            java.lang.Object r8 = r14.L$1
            com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$observer$1 r8 = (com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$observer$1) r8
            java.lang.Object r9 = r14.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L74
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            kotlinx.coroutines.channels.ProducerScope r15 = (kotlinx.coroutines.channels.ProducerScope) r15
            com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$observer$1 r1 = new com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$observer$1
            r1.<init>()
            java.lang.String[] r5 = r14.$names
            com.android.systemui.util.settings.SettingsProxy r6 = r14.$this_observerFlow
            int r7 = r5.length
            r8 = 0
            r9 = r15
            r13 = r8
            r8 = r1
            r1 = r7
            r7 = r5
            r5 = r13
        L4b:
            r15 = 0
            if (r5 >= r1) goto L76
            r10 = r7[r5]
            r14.L$0 = r9
            r14.L$1 = r8
            r14.L$2 = r7
            r14.L$3 = r6
            r14.I$0 = r5
            r14.I$1 = r1
            r14.label = r4
            kotlinx.coroutines.CoroutineDispatcher r11 = r6.getBackgroundDispatcher()
            com.android.systemui.util.settings.SettingsProxy$registerContentObserver$2 r12 = new com.android.systemui.util.settings.SettingsProxy$registerContentObserver$2
            r12.<init>(r6, r10, r8, r15)
            java.lang.Object r15 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r14)
            kotlin.coroutines.intrinsics.CoroutineSingletons r10 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r15 != r10) goto L70
            goto L71
        L70:
            r15 = r2
        L71:
            if (r15 != r0) goto L74
            return r0
        L74:
            int r5 = r5 + r4
            goto L4b
        L76:
            com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$2 r1 = new com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2$2
            com.android.systemui.util.settings.SettingsProxy r4 = r14.$this_observerFlow
            r1.<init>()
            r14.L$0 = r15
            r14.L$1 = r15
            r14.L$2 = r15
            r14.L$3 = r15
            r14.label = r3
            java.lang.Object r14 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r9, r1, r14)
            if (r14 != r0) goto L8e
            return r0
        L8e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.settings.SettingsProxyExt$observerFlow$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
