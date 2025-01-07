package com.google.android.systemui.volume.panel.component.clearcalling.domain;

import com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ClearCallingAvailabilityCriteria$availability$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ ClearCallingInteractor $clearCallingInteractor;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClearCallingAvailabilityCriteria$availability$1(ClearCallingInteractor clearCallingInteractor, Continuation continuation) {
        super(3, continuation);
        this.$clearCallingInteractor = clearCallingInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        ClearCallingAvailabilityCriteria$availability$1 clearCallingAvailabilityCriteria$availability$1 = new ClearCallingAvailabilityCriteria$availability$1(this.$clearCallingInteractor, (Continuation) obj3);
        clearCallingAvailabilityCriteria$availability$1.Z$0 = booleanValue;
        clearCallingAvailabilityCriteria$availability$1.Z$1 = booleanValue2;
        return clearCallingAvailabilityCriteria$availability$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0033, code lost:
    
        if (((java.lang.Boolean) r4).booleanValue() != false) goto L18;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r3.label
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            kotlin.ResultKt.throwOnFailure(r4)
            goto L2d
        Ld:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L15:
            kotlin.ResultKt.throwOnFailure(r4)
            boolean r4 = r3.Z$0
            boolean r1 = r3.Z$1
            if (r4 == 0) goto L36
            if (r1 == 0) goto L36
            com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor r4 = r3.$clearCallingInteractor
            r3.label = r2
            com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepositoryImpl r4 = r4.clearCallingRepository
            java.lang.Object r4 = r4.isClearCallingAvailable(r3)
            if (r4 != r0) goto L2d
            return r0
        L2d:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            if (r3 == 0) goto L36
            goto L37
        L36:
            r2 = 0
        L37:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.clearcalling.domain.ClearCallingAvailabilityCriteria$availability$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
