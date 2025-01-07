package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CellularIconViewModel$showNetworkTypeIcon$1 extends SuspendLambda implements Function6 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    /* synthetic */ boolean Z$4;
    int label;

    public CellularIconViewModel$showNetworkTypeIcon$1(Continuation continuation) {
        super(6, continuation);
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        boolean booleanValue4 = ((Boolean) obj4).booleanValue();
        boolean booleanValue5 = ((Boolean) obj5).booleanValue();
        CellularIconViewModel$showNetworkTypeIcon$1 cellularIconViewModel$showNetworkTypeIcon$1 = new CellularIconViewModel$showNetworkTypeIcon$1((Continuation) obj6);
        cellularIconViewModel$showNetworkTypeIcon$1.Z$0 = booleanValue;
        cellularIconViewModel$showNetworkTypeIcon$1.Z$1 = booleanValue2;
        cellularIconViewModel$showNetworkTypeIcon$1.Z$2 = booleanValue3;
        cellularIconViewModel$showNetworkTypeIcon$1.Z$3 = booleanValue4;
        cellularIconViewModel$showNetworkTypeIcon$1.Z$4 = booleanValue5;
        return cellularIconViewModel$showNetworkTypeIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        return Boolean.valueOf(this.Z$2 || (!this.Z$4 && z2 && z && this.Z$3));
    }
}
