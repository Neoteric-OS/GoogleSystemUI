package com.android.systemui.qs.ui.composable;

import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class QuickSettingsShadeOverlayKt$QuickSettingsLayout$1$1$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        EditModeViewModel editModeViewModel = (EditModeViewModel) this.receiver;
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = editModeViewModel._isEditing;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        return Unit.INSTANCE;
    }
}
