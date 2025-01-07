package com.android.systemui.user.ui.binder;

import android.view.View;
import com.android.systemui.user.ui.viewmodel.UserActionViewModel;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherViewBinder$bind$2 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $viewModel;

    public /* synthetic */ UserSwitcherViewBinder$bind$2(int i, Object obj) {
        this.$r8$classId = i;
        this.$viewModel = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                StateFlowImpl stateFlowImpl = ((UserSwitcherViewModel) this.$viewModel)._isMenuVisible;
                Boolean bool = Boolean.TRUE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                break;
            case 1:
                StateFlowImpl stateFlowImpl2 = ((UserSwitcherViewModel) this.$viewModel).hasCancelButtonBeenClicked;
                Boolean bool2 = Boolean.TRUE;
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, bool2);
                break;
            default:
                ((UserActionViewModel) this.$viewModel).onClicked.invoke();
                break;
        }
    }
}
