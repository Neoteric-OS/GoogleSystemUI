package com.android.systemui.dreams.homecontrols.domain.interactor;

import com.android.systemui.controls.panels.SelectedComponentRepository$SelectedComponent;
import com.android.systemui.dreams.homecontrols.domain.interactor.HomeControlsComponentInteractor;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class HomeControlsComponentInteractor$panelComponent$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        HomeControlsComponentInteractor$panelComponent$1 homeControlsComponentInteractor$panelComponent$1 = new HomeControlsComponentInteractor$panelComponent$1(3, (Continuation) obj3);
        homeControlsComponentInteractor$panelComponent$1.L$0 = (List) obj;
        homeControlsComponentInteractor$panelComponent$1.L$1 = (SelectedComponentRepository$SelectedComponent) obj2;
        return homeControlsComponentInteractor$panelComponent$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        SelectedComponentRepository$SelectedComponent selectedComponentRepository$SelectedComponent = (SelectedComponentRepository$SelectedComponent) this.L$1;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.areEqual(((HomeControlsComponentInteractor.PanelComponent) obj2).componentName, selectedComponentRepository$SelectedComponent != null ? selectedComponentRepository$SelectedComponent.componentName : null)) {
                break;
            }
        }
        HomeControlsComponentInteractor.PanelComponent panelComponent = (HomeControlsComponentInteractor.PanelComponent) obj2;
        if (panelComponent == null) {
            panelComponent = (HomeControlsComponentInteractor.PanelComponent) CollectionsKt.firstOrNull(list);
        }
        if (panelComponent != null) {
            return panelComponent.panelActivity;
        }
        return null;
    }
}
