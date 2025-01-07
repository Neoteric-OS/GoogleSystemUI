package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewModel$isInteractive$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        DeviceEntryIconViewModel$isInteractive$1 deviceEntryIconViewModel$isInteractive$1 = new DeviceEntryIconViewModel$isInteractive$1(3, (Continuation) obj3);
        deviceEntryIconViewModel$isInteractive$1.L$0 = (DeviceEntryIconView.IconType) obj;
        deviceEntryIconViewModel$isInteractive$1.Z$0 = booleanValue;
        return deviceEntryIconViewModel$isInteractive$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        if (r2 != false) goto L14;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) {
        /*
            r2 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r2.label
            if (r0 != 0) goto L30
            kotlin.ResultKt.throwOnFailure(r3)
            java.lang.Object r3 = r2.L$0
            com.android.systemui.keyguard.ui.view.DeviceEntryIconView$IconType r3 = (com.android.systemui.keyguard.ui.view.DeviceEntryIconView.IconType) r3
            boolean r2 = r2.Z$0
            int r3 = r3.ordinal()
            r0 = 0
            r1 = 1
            if (r3 == 0) goto L28
            if (r3 == r1) goto L26
            r2 = 2
            if (r3 == r2) goto L2b
            r2 = 3
            if (r3 != r2) goto L20
            goto L2b
        L20:
            kotlin.NoWhenBranchMatchedException r2 = new kotlin.NoWhenBranchMatchedException
            r2.<init>()
            throw r2
        L26:
            r0 = r1
            goto L2b
        L28:
            if (r2 == 0) goto L2b
            goto L26
        L2b:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            return r2
        L30:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "call to 'resume' before 'invoke' with coroutine"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$isInteractive$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
