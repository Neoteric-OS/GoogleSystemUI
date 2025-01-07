package com.android.systemui.communal.ui.compose;

import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.statusbar.phone.ManagedProfileControllerImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class CommunalHubKt$CommunalHub$6$5 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        CommunalViewModel communalViewModel = (CommunalViewModel) this.receiver;
        ((ManagedProfileControllerImpl) communalViewModel.communalInteractor.managedProfileController).setWorkModeEnabled(true);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = communalViewModel._isEnableWorkProfileDialogShowing;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        return Unit.INSTANCE;
    }
}
