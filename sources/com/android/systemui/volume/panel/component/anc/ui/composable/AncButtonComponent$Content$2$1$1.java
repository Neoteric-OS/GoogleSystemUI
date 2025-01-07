package com.android.systemui.volume.panel.component.anc.ui.composable;

import com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class AncButtonComponent$Content$2$1$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int intValue = ((Number) obj).intValue();
        StateFlowImpl stateFlowImpl = ((AncViewModel) this.receiver).interactor.buttonSliceWidth;
        Integer valueOf = Integer.valueOf(intValue);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        return Unit.INSTANCE;
    }
}
