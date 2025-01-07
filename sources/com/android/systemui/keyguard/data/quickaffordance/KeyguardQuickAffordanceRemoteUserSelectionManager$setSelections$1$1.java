package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.shared.customization.data.content.CustomizationProviderClient;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ List $affordanceIds;
    final /* synthetic */ CustomizationProviderClient $client;
    final /* synthetic */ String $slotId;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1(CustomizationProviderClient customizationProviderClient, String str, List list, Continuation continuation) {
        super(2, continuation);
        this.$client = customizationProviderClient;
        this.$slotId = str;
        this.$affordanceIds = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1(this.$client, this.$slotId, this.$affordanceIds, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v9, types: [com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L29
            if (r1 == r3) goto L25
            if (r1 != r2) goto L1d
            java.lang.Object r1 = r6.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r6.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.L$0
            com.android.systemui.shared.customization.data.content.CustomizationProviderClient r4 = (com.android.systemui.shared.customization.data.content.CustomizationProviderClient) r4
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r4
            goto L48
        L1d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L25:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3b
        L29:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.shared.customization.data.content.CustomizationProviderClient r7 = r6.$client
            java.lang.String r1 = r6.$slotId
            r6.label = r3
            com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl r7 = (com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl) r7
            java.lang.Object r7 = r7.deleteAllSelections(r1, r6)
            if (r7 != r0) goto L3b
            return r0
        L3b:
            java.util.List r7 = r6.$affordanceIds
            com.android.systemui.shared.customization.data.content.CustomizationProviderClient r1 = r6.$client
            java.lang.String r3 = r6.$slotId
            java.util.Iterator r7 = r7.iterator()
            r5 = r1
            r1 = r7
            r7 = r5
        L48:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L65
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            r6.L$0 = r7
            r6.L$1 = r3
            r6.L$2 = r1
            r6.label = r2
            com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl r7 = (com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl) r7
            java.lang.Object r4 = r7.insertSelection(r3, r4, r6)
            if (r4 != r0) goto L48
            return r0
        L65:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
