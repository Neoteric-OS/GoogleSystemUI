package com.android.systemui.user.domain.interactor;

import android.R;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.user.data.model.UserSwitcherSettingsModel;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.shared.model.UserActionModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherInteractor$actions$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ UserSwitcherInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherInteractor$actions$1(UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
        super(5, continuation);
        this.this$0 = userSwitcherInteractor;
    }

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj4).booleanValue();
        UserSwitcherInteractor$actions$1 userSwitcherInteractor$actions$1 = new UserSwitcherInteractor$actions$1(this.this$0, (Continuation) obj5);
        userSwitcherInteractor$actions$1.L$0 = (List) obj2;
        userSwitcherInteractor$actions$1.L$1 = (UserSwitcherSettingsModel) obj3;
        userSwitcherInteractor$actions$1.Z$0 = booleanValue;
        return userSwitcherInteractor$actions$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        UserSwitcherSettingsModel userSwitcherSettingsModel = (UserSwitcherSettingsModel) this.L$1;
        boolean z = this.Z$0;
        UserSwitcherInteractor userSwitcherInteractor = this.this$0;
        ListBuilder listBuilder = new ListBuilder();
        boolean z2 = !z || userSwitcherSettingsModel.isAddUsersFromLockscreen;
        if (z2) {
            List listOf = ((FeatureFlagsClassicRelease) userSwitcherInteractor.featureFlags).isEnabled(Flags.FULL_SCREEN_USER_SWITCHER) ? CollectionsKt__CollectionsKt.listOf(UserActionModel.ADD_USER, UserActionModel.ADD_SUPERVISED_USER, UserActionModel.ENTER_GUEST_MODE) : CollectionsKt__CollectionsKt.listOf(UserActionModel.ENTER_GUEST_MODE, UserActionModel.ADD_USER, UserActionModel.ADD_SUPERVISED_USER);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
            Iterator it = listOf.iterator();
            while (it.hasNext()) {
                int ordinal = ((UserActionModel) it.next()).ordinal();
                UserRepositoryImpl userRepositoryImpl = userSwitcherInteractor.repository;
                if (ordinal == 0) {
                    if (list == null || !list.isEmpty()) {
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            if (((UserInfo) it2.next()).isGuest()) {
                                break;
                            }
                        }
                    }
                    if (!userSwitcherInteractor.guestUserInteractor.isGuestUserAutoCreated) {
                        UserManager userManager = userSwitcherInteractor.manager;
                        if (userSwitcherSettingsModel.isUserSwitcherEnabled && z2) {
                            UserInfo selectedUserInfo = userRepositoryImpl.getSelectedUserInfo();
                            if (!selectedUserInfo.isAdmin()) {
                                if (selectedUserInfo.id != 0) {
                                }
                            }
                            if (!userManager.hasUserRestrictionForUser("no_add_user", UserHandle.of(selectedUserInfo.id))) {
                                if (!userManager.hasUserRestrictionForUser("no_add_user", UserHandle.SYSTEM)) {
                                    if (!userManager.canAddMoreUsers("android.os.usertype.full.GUEST")) {
                                    }
                                }
                            }
                        }
                    }
                    listBuilder.add(UserActionModel.ENTER_GUEST_MODE);
                } else if (ordinal == 1) {
                    UserManager userManager2 = userSwitcherInteractor.manager;
                    if (userSwitcherSettingsModel.isUserSwitcherEnabled && z2) {
                        UserInfo selectedUserInfo2 = userRepositoryImpl.getSelectedUserInfo();
                        if ((selectedUserInfo2.isAdmin() || selectedUserInfo2.id == 0) && !userManager2.hasUserRestrictionForUser("no_add_user", UserHandle.of(selectedUserInfo2.id)) && !userManager2.hasUserRestrictionForUser("no_add_user", UserHandle.SYSTEM) && userManager2.canAddMoreUsers("android.os.usertype.full.SECONDARY")) {
                            listBuilder.add(UserActionModel.ADD_USER);
                        }
                    }
                } else if (ordinal == 2) {
                    UserManager userManager3 = userSwitcherInteractor.manager;
                    boolean z3 = userSwitcherSettingsModel.isUserSwitcherEnabled;
                    String string = userSwitcherInteractor.applicationContext.getString(R.string.config_usbPermissionActivity);
                    if (string != null && string.length() != 0 && z3 && z2) {
                        UserInfo selectedUserInfo3 = userRepositoryImpl.getSelectedUserInfo();
                        if ((selectedUserInfo3.isAdmin() || selectedUserInfo3.id == 0) && !userManager3.hasUserRestrictionForUser("no_add_user", UserHandle.of(selectedUserInfo3.id)) && !userManager3.hasUserRestrictionForUser("no_add_user", UserHandle.SYSTEM) && userManager3.canAddMoreUsers("android.os.usertype.full.SECONDARY")) {
                            listBuilder.add(UserActionModel.ADD_SUPERVISED_USER);
                        }
                    }
                }
                arrayList.add(Unit.INSTANCE);
            }
        }
        UserRepositoryImpl userRepositoryImpl2 = userSwitcherInteractor.repository;
        if (userSwitcherSettingsModel.isUserSwitcherEnabled && userRepositoryImpl2.getSelectedUserInfo().isAdmin()) {
            listBuilder.add(UserActionModel.NAVIGATE_TO_USER_MANAGEMENT);
        }
        return listBuilder.build();
    }
}
