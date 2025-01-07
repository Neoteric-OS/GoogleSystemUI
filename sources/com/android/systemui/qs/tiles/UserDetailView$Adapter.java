package com.android.systemui.qs.tiles;

import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.QSUserSwitcherEvent;
import com.android.systemui.statusbar.policy.BaseUserSwitcherAdapter;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.user.ui.dialog.DialogShowerImpl;
import com.android.wm.shell.R;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserDetailView$Adapter extends BaseUserSwitcherAdapter implements View.OnClickListener {
    public final Context mContext;
    public final UserSwitcherController mController;
    public UserDetailItemView mCurrentUserView;
    public DialogShowerImpl mDialogShower;
    public final FalsingManager mFalsingManager;
    public final UiEventLogger mUiEventLogger;

    public UserDetailView$Adapter(Context context, UserSwitcherController userSwitcherController, UiEventLogger uiEventLogger, FalsingManager falsingManager) {
        super(userSwitcherController);
        this.mContext = context;
        this.mController = userSwitcherController;
        this.mUiEventLogger = uiEventLogger;
        this.mFalsingManager = falsingManager;
    }

    @Override // com.android.systemui.statusbar.policy.BaseUserSwitcherAdapter
    public final List getUsers() {
        return (List) super.getUsers().stream().filter(new UserDetailView$Adapter$$ExternalSyntheticLambda0()).collect(Collectors.toList());
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        UserInfo userInfo;
        UserRecord item = getItem(i);
        Context context = viewGroup.getContext();
        int i2 = UserDetailItemView.$r8$clinit;
        if (!(view instanceof UserDetailItemView)) {
            view = LayoutInflater.from(context).inflate(R.layout.qs_user_detail_item, viewGroup, false);
        }
        UserDetailItemView userDetailItemView = (UserDetailItemView) view;
        boolean z = item.isCurrent;
        boolean z2 = item.isGuest;
        if (!z || z2) {
            userDetailItemView.setOnClickListener(this);
        } else {
            userDetailItemView.setOnClickListener(null);
            userDetailItemView.setClickable(false);
        }
        String name = getName(this.mContext, item);
        Bitmap bitmap = item.picture;
        boolean z3 = item.isSwitchToEnabled;
        boolean z4 = item.isCurrent;
        if (bitmap == null) {
            Context context2 = this.mContext;
            Drawable iconDrawable = BaseUserSwitcherAdapter.getIconDrawable(context2, item);
            iconDrawable.setTint(context2.getResources().getColor(z4 ? R.color.qs_user_switcher_selected_avatar_icon_color : !z3 ? R.color.GM2_grey_600 : R.color.qs_user_switcher_avatar_icon_color, context2.getTheme()));
            Drawable mutate = new LayerDrawable(new Drawable[]{context2.getDrawable(z4 ? R.drawable.bg_avatar_selected : R.drawable.qs_bg_avatar), iconDrawable}).mutate();
            int i3 = (z2 || (userInfo = item.info) == null) ? -10000 : userInfo.id;
            userDetailItemView.mName.setText(name);
            userDetailItemView.mAvatar.setDrawableWithBadge(i3, mutate);
        } else {
            CircleFramedDrawable circleFramedDrawable = new CircleFramedDrawable(item.picture, (int) this.mContext.getResources().getDimension(R.dimen.qs_framed_avatar_size));
            circleFramedDrawable.setColorFilter(z3 ? null : (ColorFilter) BaseUserSwitcherAdapter.disabledUserAvatarColorFilter$delegate.getValue());
            int i4 = item.info.id;
            userDetailItemView.mName.setText(name);
            userDetailItemView.mAvatar.setDrawableWithBadge(i4, circleFramedDrawable);
        }
        userDetailItemView.setActivated(z4);
        boolean z5 = item.enforcedAdmin != null;
        View view2 = userDetailItemView.mRestrictedPadlock;
        if (view2 != null) {
            view2.setVisibility(z5 ? 0 : 8);
        }
        userDetailItemView.setEnabled(!z5);
        userDetailItemView.setEnabled(z3);
        userDetailItemView.setAlpha(userDetailItemView.isEnabled() ? 1.0f : 0.38f);
        if (z4) {
            this.mCurrentUserView = userDetailItemView;
        }
        userDetailItemView.setTag(item);
        return userDetailItemView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.mFalsingManager.isFalseTap(1)) {
            return;
        }
        Trace.beginSection("UserDetailView.Adapter#onClick");
        UserRecord userRecord = (UserRecord) view.getTag();
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = userRecord.enforcedAdmin;
        if (enforcedAdmin != null) {
            this.mController.activityStarter.startActivity(RestrictedLockUtils.getShowAdminSupportDetailsIntent(enforcedAdmin), true);
        } else if (userRecord.isSwitchToEnabled) {
            MetricsLogger.action(this.mContext, 156);
            this.mUiEventLogger.log(QSUserSwitcherEvent.QS_USER_SWITCH);
            if (!userRecord.isAddUser && !userRecord.isRestricted && userRecord.enforcedAdmin == null) {
                UserDetailItemView userDetailItemView = this.mCurrentUserView;
                if (userDetailItemView != null) {
                    userDetailItemView.setActivated(false);
                }
                view.setActivated(true);
            }
            onUserListItemClicked(userRecord, this.mDialogShower);
        }
        Trace.endSection();
    }
}
