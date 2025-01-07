package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.user.data.model.UserSwitcherSettingsModel;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.legacyhelper.ui.LegacyUserUiHelper;
import com.android.systemui.user.shared.model.UserActionModel;
import com.android.systemui.user.ui.dialog.DialogShowerImpl;
import com.android.systemui.user.utils.MultiUserActionsEvent;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BaseUserSwitcherAdapter extends BaseAdapter {
    public static final Lazy disabledUserAvatarColorFilter$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.policy.BaseUserSwitcherAdapter$Companion$disabledUserAvatarColorFilter$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            return new ColorMatrixColorFilter(colorMatrix);
        }
    });
    public final UserSwitcherController controller;

    public BaseUserSwitcherAdapter(UserSwitcherController userSwitcherController) {
        this.controller = userSwitcherController;
        final WeakReference weakReference = new WeakReference(this);
        userSwitcherController.getMUserSwitcherInteractor().addCallback(new UserSwitcherInteractor.UserCallback() { // from class: com.android.systemui.statusbar.policy.UserSwitcherController$addAdapter$1
            @Override // com.android.systemui.user.domain.interactor.UserSwitcherInteractor.UserCallback
            public final boolean isEvictable() {
                return weakReference.get() == null;
            }

            @Override // com.android.systemui.user.domain.interactor.UserSwitcherInteractor.UserCallback
            public final void onUserStateChanged() {
                BaseUserSwitcherAdapter baseUserSwitcherAdapter = (BaseUserSwitcherAdapter) weakReference.get();
                if (baseUserSwitcherAdapter != null) {
                    baseUserSwitcherAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public static final Drawable getIconDrawable(Context context, UserRecord userRecord) {
        Drawable drawable = context.getDrawable(userRecord.isAddUser ? R.drawable.ic_add : userRecord.isGuest ? R.drawable.ic_account_circle : userRecord.isAddSupervisedUser ? R.drawable.ic_add_supervised_user : userRecord.isManageUsers ? R.drawable.ic_manage_users : R.drawable.ic_avatar_user);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return getUsers().size();
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    public final String getName(Context context, UserRecord userRecord) {
        return LegacyUserUiHelper.getUserRecordName(context, userRecord, this.controller.getMUserSwitcherInteractor().isGuestUserAutoCreated, this.controller.getMUserSwitcherInteractor().isGuestUserResetting);
    }

    public List getUsers() {
        ArrayList arrayList = (ArrayList) ((StateFlowImpl) this.controller.getMUserSwitcherInteractor().userRecords.$$delegate_0).getValue();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            UserRecord userRecord = (UserRecord) obj;
            if (!((KeyguardInteractor) this.controller.keyguardInteractor$delegate.getValue()).isKeyguardShowing() || !userRecord.isRestricted) {
                if (((UserSwitcherSettingsModel) ((StateFlowImpl) this.controller.getMUserSwitcherInteractor().repository._userSwitcherSettings.$$delegate_0).getValue()).isUserSwitcherEnabled || userRecord.isCurrent) {
                    arrayList2.add(obj);
                }
            }
        }
        return arrayList2;
    }

    public final void onUserListItemClicked(UserRecord userRecord, DialogShowerImpl dialogShowerImpl) {
        UserActionModel userActionModel;
        UserSwitcherInteractor mUserSwitcherInteractor = this.controller.getMUserSwitcherInteractor();
        UserInfo userInfo = userRecord.info;
        if (userInfo != null) {
            mUserSwitcherInteractor.uiEventLogger.log(userInfo.isGuest() ? MultiUserActionsEvent.SWITCH_TO_GUEST_FROM_USER_SWITCHER : userInfo.isRestricted() ? MultiUserActionsEvent.SWITCH_TO_RESTRICTED_USER_FROM_USER_SWITCHER : MultiUserActionsEvent.SWITCH_TO_USER_FROM_USER_SWITCHER);
            UserInfo userInfo2 = userRecord.info;
            if (userInfo2 == null) {
                throw new IllegalStateException("Required value was null.");
            }
            mUserSwitcherInteractor.selectUser(userInfo2.id, dialogShowerImpl);
            return;
        }
        if (userInfo != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (userRecord.isAddUser) {
            userActionModel = UserActionModel.ADD_USER;
        } else if (userRecord.isAddSupervisedUser) {
            userActionModel = UserActionModel.ADD_SUPERVISED_USER;
        } else if (userRecord.isGuest) {
            userActionModel = UserActionModel.ENTER_GUEST_MODE;
        } else {
            if (!userRecord.isManageUsers) {
                throw new IllegalStateException(("Not a known action: " + userRecord).toString());
            }
            userActionModel = UserActionModel.NAVIGATE_TO_USER_MANAGEMENT;
        }
        mUserSwitcherInteractor.executeAction(userActionModel, dialogShowerImpl);
    }

    @Override // android.widget.Adapter
    public final UserRecord getItem(int i) {
        return (UserRecord) getUsers().get(i);
    }
}
