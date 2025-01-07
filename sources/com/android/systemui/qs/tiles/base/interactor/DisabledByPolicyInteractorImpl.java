package com.android.systemui.qs.tiles.base.interactor;

import android.os.UserHandle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisabledByPolicyInteractorImpl {
    public final ActivityStarter activityStarter;
    public final CoroutineDispatcher backgroundDispatcher;
    public final RestrictedLockProxy restrictedLockProxy;

    public DisabledByPolicyInteractorImpl(ActivityStarter activityStarter, RestrictedLockProxy restrictedLockProxy, CoroutineDispatcher coroutineDispatcher) {
        this.activityStarter = activityStarter;
        this.restrictedLockProxy = restrictedLockProxy;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public final Object isDisabled(UserHandle userHandle, String str, QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1.AnonymousClass2.AnonymousClass1 anonymousClass1) {
        return BuildersKt.withContext(this.backgroundDispatcher, new DisabledByPolicyInteractorImpl$isDisabled$2(this, userHandle, str, null), anonymousClass1);
    }
}
