package com.android.systemui.user.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.UserManager;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherRepositoryImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final Handler bgHandler;
    public final Flow currentUserInfo;
    public final Flow currentUserName;
    public final GlobalSettings globalSetting;
    public final Flow isEnabled;
    public final boolean showUserSwitcherForSingleUser;
    public final UserInfoControllerImpl userInfoController;
    public final UserManager userManager;
    public final UserRepositoryImpl userRepository;
    public final UserSwitcherController userSwitcherController;
    public final Flow userSwitcherStatus;

    public UserSwitcherRepositoryImpl(Context context, Handler handler, CoroutineDispatcher coroutineDispatcher, UserManager userManager, UserSwitcherController userSwitcherController, UserInfoControllerImpl userInfoControllerImpl, GlobalSettings globalSettings, UserRepositoryImpl userRepositoryImpl) {
        this.bgHandler = handler;
        this.bgDispatcher = coroutineDispatcher;
        this.userManager = userManager;
        this.userSwitcherController = userSwitcherController;
        this.userInfoController = userInfoControllerImpl;
        this.globalSetting = globalSettings;
        this.userRepository = userRepositoryImpl;
        this.showUserSwitcherForSingleUser = context.getResources().getBoolean(R.bool.qs_show_user_switcher_for_single_user);
        Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new UserSwitcherRepositoryImpl$isEnabled$1(this, null));
        this.isEnabled = conflatedCallbackFlow;
        this.currentUserName = FlowConflatedKt.conflatedCallbackFlow(new UserSwitcherRepositoryImpl$currentUserName$1(this, null));
        this.currentUserInfo = FlowConflatedKt.conflatedCallbackFlow(new UserSwitcherRepositoryImpl$currentUserInfo$1(this, null));
        this.userSwitcherStatus = FlowKt.distinctUntilChanged(FlowKt.transformLatest(conflatedCallbackFlow, new UserSwitcherRepositoryImpl$special$$inlined$flatMapLatest$1(this, null)));
    }
}
