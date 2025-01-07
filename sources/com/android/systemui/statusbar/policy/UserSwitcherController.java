package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.domain.interactor.GuestUserInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import dagger.Lazy;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherController {
    public final ActivityStarter activityStarter;
    public final Context applicationContext;
    public final Lazy guestUserInteractorLazy;
    public final Lazy keyguardInteractorLazy;
    public final Lazy userSwitcherInteractorLazy;
    public final kotlin.Lazy mUserSwitcherInteractor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.policy.UserSwitcherController$mUserSwitcherInteractor$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (UserSwitcherInteractor) UserSwitcherController.this.userSwitcherInteractorLazy.get();
        }
    });
    public final kotlin.Lazy guestUserInteractor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.policy.UserSwitcherController$guestUserInteractor$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (GuestUserInteractor) UserSwitcherController.this.guestUserInteractorLazy.get();
        }
    });
    public final kotlin.Lazy keyguardInteractor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.policy.UserSwitcherController$keyguardInteractor$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (KeyguardInteractor) UserSwitcherController.this.keyguardInteractorLazy.get();
        }
    });
    public final Map callbackCompatMap = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface UserSwitchCallback {
        void onUserSwitched();
    }

    public UserSwitcherController(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, ActivityStarter activityStarter) {
        this.applicationContext = context;
        this.userSwitcherInteractorLazy = lazy;
        this.guestUserInteractorLazy = lazy2;
        this.keyguardInteractorLazy = lazy3;
        this.activityStarter = activityStarter;
    }

    public final void addUserSwitchCallback(final UserSwitchCallback userSwitchCallback) {
        UserSwitcherInteractor.UserCallback userCallback = new UserSwitcherInteractor.UserCallback() { // from class: com.android.systemui.statusbar.policy.UserSwitcherController$addUserSwitchCallback$interactorCallback$1
            @Override // com.android.systemui.user.domain.interactor.UserSwitcherInteractor.UserCallback
            public final void onUserStateChanged() {
                UserSwitcherController.UserSwitchCallback.this.onUserSwitched();
            }
        };
        this.callbackCompatMap.put(userSwitchCallback, userCallback);
        getMUserSwitcherInteractor().addCallback(userCallback);
    }

    public final UserSwitcherInteractor getMUserSwitcherInteractor() {
        return (UserSwitcherInteractor) this.mUserSwitcherInteractor$delegate.getValue();
    }
}
