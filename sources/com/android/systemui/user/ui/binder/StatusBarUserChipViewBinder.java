package com.android.systemui.user.ui.binder;

import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.phone.userswitcher.StatusBarUserSwitcherContainer;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarUserChipViewBinder {
    public static final void bind(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel) {
        RepeatWhenAttachedKt.repeatWhenAttached(statusBarUserSwitcherContainer, EmptyCoroutineContext.INSTANCE, new StatusBarUserChipViewBinder$bind$1(statusBarUserSwitcherContainer, statusBarUserChipViewModel, null));
    }
}
