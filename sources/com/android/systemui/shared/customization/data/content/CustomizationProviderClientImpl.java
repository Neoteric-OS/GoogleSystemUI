package com.android.systemui.shared.customization.data.content;

import android.content.Context;
import com.android.systemui.shared.customization.data.content.CustomizationProviderContract;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomizationProviderClientImpl implements CustomizationProviderClient {
    public final CoroutineDispatcher backgroundDispatcher;
    public final Context context;

    public CustomizationProviderClientImpl(Context context, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public final Object deleteAllSelections(String str, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundDispatcher, new CustomizationProviderClientImpl$deleteAllSelections$2(this, str, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public final Object insertSelection(String str, String str2, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundDispatcher, new CustomizationProviderClientImpl$insertSelection$2(this, str, str2, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public final CustomizationProviderClientImpl$observeSelections$$inlined$map$1 observeSelections() {
        return new CustomizationProviderClientImpl$observeSelections$$inlined$map$1(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CustomizationProviderClientImpl$observeUri$2(2, null), FlowKt.callbackFlow(new CustomizationProviderClientImpl$observeUri$1(this, CustomizationProviderContract.LockScreenQuickAffordances.SelectionTable.URI, null))), this.backgroundDispatcher), this);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object querySelections(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$1 r0 = (com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$1 r0 = new com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$1
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
            com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$2 r5 = new com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$querySelections$2
            r2 = 0
            r5.<init>(r4, r2)
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r4 = r4.backgroundDispatcher
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            java.util.List r5 = (java.util.List) r5
            if (r5 != 0) goto L49
            kotlin.collections.EmptyList r5 = kotlin.collections.EmptyList.INSTANCE
        L49:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl.querySelections(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
