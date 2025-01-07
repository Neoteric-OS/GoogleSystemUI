package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CellularIconViewModel$networkTypeIcon$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        CellularIconViewModel$networkTypeIcon$1 cellularIconViewModel$networkTypeIcon$1 = new CellularIconViewModel$networkTypeIcon$1(3, (Continuation) obj3);
        cellularIconViewModel$networkTypeIcon$1.L$0 = (NetworkTypeIconModel) obj;
        cellularIconViewModel$networkTypeIcon$1.Z$0 = booleanValue;
        return cellularIconViewModel$networkTypeIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        NetworkTypeIconModel networkTypeIconModel = (NetworkTypeIconModel) this.L$0;
        boolean z = this.Z$0;
        Icon.Resource resource = networkTypeIconModel.getIconId() != 0 ? new Icon.Resource(networkTypeIconModel.getIconId(), networkTypeIconModel.getContentDescription() != 0 ? new ContentDescription.Resource(networkTypeIconModel.getContentDescription()) : null) : null;
        if (z) {
            return resource;
        }
        return null;
    }
}
