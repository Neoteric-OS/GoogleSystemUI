package com.android.systemui.user.ui.viewmodel;

import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.ui.drawable.CircularDrawable;
import com.android.systemui.user.domain.interactor.GuestUserInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$2;
import com.android.systemui.user.shared.model.UserModel;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherViewModel {
    public final StateFlowImpl _isMenuVisible;
    public final GuestUserInteractor guestUserInteractor;
    public final StateFlowImpl hasCancelButtonBeenClicked;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isFinishRequested;
    public final StateFlowImpl isFinishRequiredDueToExecutedAction;
    public final StateFlowImpl isMenuVisible;
    public final UserSwitcherViewModel$special$$inlined$map$5 isOpenMenuButtonVisible;
    public final UserSwitcherViewModel$special$$inlined$map$1 maximumUserColumns;
    public final UserSwitcherViewModel$special$$inlined$map$1 menu;
    public final UserSwitcherViewModel$special$$inlined$map$1 selectedUser;
    public final StateFlowImpl userSwitched;
    public final UserSwitcherInteractor userSwitcherInteractor;
    public final UserSwitcherViewModel$special$$inlined$map$1 users;

    public UserSwitcherViewModel(UserSwitcherInteractor userSwitcherInteractor, GuestUserInteractor guestUserInteractor) {
        this.userSwitcherInteractor = userSwitcherInteractor;
        this.guestUserInteractor = guestUserInteractor;
        new UserSwitcherViewModel$special$$inlined$map$1(new UserSwitcherInteractor$special$$inlined$map$2(userSwitcherInteractor.repository.selectedUserInfo, userSwitcherInteractor, 1), this, 0);
        UserSwitcherViewModel$special$$inlined$map$1 userSwitcherViewModel$special$$inlined$map$1 = new UserSwitcherViewModel$special$$inlined$map$1(userSwitcherInteractor.getUsers(), this, 1);
        this.users = userSwitcherViewModel$special$$inlined$map$1;
        this.maximumUserColumns = new UserSwitcherViewModel$special$$inlined$map$1(userSwitcherViewModel$special$$inlined$map$1, this, 2);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isMenuVisible = MutableStateFlow;
        this.isMenuVisible = MutableStateFlow;
        UserSwitcherViewModel$special$$inlined$map$1 userSwitcherViewModel$special$$inlined$map$12 = new UserSwitcherViewModel$special$$inlined$map$1(userSwitcherInteractor.getActions(), this, 3);
        this.menu = userSwitcherViewModel$special$$inlined$map$12;
        this.isOpenMenuButtonVisible = new UserSwitcherViewModel$special$$inlined$map$5(userSwitcherViewModel$special$$inlined$map$12);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this.hasCancelButtonBeenClicked = MutableStateFlow2;
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this.isFinishRequiredDueToExecutedAction = MutableStateFlow3;
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this.userSwitched = MutableStateFlow4;
        this.isFinishRequested = FlowKt.combine(MutableStateFlow2, MutableStateFlow3, MutableStateFlow4, new UserSwitcherViewModel$createFinishRequestedFlow$1(4, null));
    }

    public static final UserViewModel access$toViewModel(final UserSwitcherViewModel userSwitcherViewModel, final UserModel userModel) {
        userSwitcherViewModel.getClass();
        int i = userModel.id;
        Text resource = (userModel.isGuest && userModel.isSelected) ? new Text.Resource(R.string.guest_exit_quick_settings_button) : userModel.name;
        CircularDrawable circularDrawable = new CircularDrawable(userModel.image);
        boolean z = userModel.isSelectable;
        return new UserViewModel(i, resource, circularDrawable, userModel.isSelected, z ? 1.0f : 0.38f, !z ? null : new Function0() { // from class: com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel$createOnSelectedCallback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                UserSwitcherViewModel.this.userSwitcherInteractor.selectUser(userModel.id, null);
                StateFlowImpl stateFlowImpl = UserSwitcherViewModel.this.userSwitched;
                Boolean bool = Boolean.TRUE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                return Unit.INSTANCE;
            }
        });
    }
}
