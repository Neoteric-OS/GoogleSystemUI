package com.android.systemui.user.ui.viewmodel;

import com.android.systemui.animation.Expandable;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$2;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarUserChipViewModel {
    public final boolean chipEnabled;
    public final Flow isChipVisible;
    public final Function1 onClick;
    public final ChannelFlowTransformLatest userAvatar;
    public final ChannelFlowTransformLatest userName;

    public StatusBarUserChipViewModel(final UserSwitcherInteractor userSwitcherInteractor) {
        Flow mapLatest;
        boolean z = userSwitcherInteractor.isStatusBarUserChipEnabled;
        this.chipEnabled = z;
        if (z) {
            mapLatest = FlowKt.mapLatest(new StatusBarUserChipViewModel$isChipVisible$1(2, null), userSwitcherInteractor.getUsers());
        } else {
            mapLatest = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
        }
        this.isChipVisible = mapLatest;
        UserRepositoryImpl userRepositoryImpl = userSwitcherInteractor.repository;
        this.userName = FlowKt.mapLatest(new StatusBarUserChipViewModel$userName$1(2, null), new UserSwitcherInteractor$special$$inlined$map$2(userRepositoryImpl.selectedUserInfo, userSwitcherInteractor, 1));
        this.userAvatar = FlowKt.mapLatest(new StatusBarUserChipViewModel$userAvatar$1(2, null), new UserSwitcherInteractor$special$$inlined$map$2(userRepositoryImpl.selectedUserInfo, userSwitcherInteractor, 1));
        this.onClick = new Function1() { // from class: com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel$onClick$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                UserSwitcherInteractor.this.showUserSwitcher((Expandable) obj);
                return Unit.INSTANCE;
            }
        };
    }
}
