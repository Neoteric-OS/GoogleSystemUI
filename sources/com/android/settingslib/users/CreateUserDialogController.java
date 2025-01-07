package com.android.settingslib.users;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.utils.CustomDialogHelper;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda0;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda2;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CreateUserDialogController {
    public CreateUserActivity mActivity;
    public CreateUserActivity$$ExternalSyntheticLambda0 mActivityStarter;
    public String mCachedDrawablePath;
    public CreateUserActivity$$ExternalSyntheticLambda2 mCancelCallback;
    public int mCurrentState;
    public CustomDialogHelper mCustomDialogHelper;
    public View mEditUserInfoView;
    public EditUserPhotoController mEditUserPhotoController;
    public View mGrantAdminView;
    public Boolean mIsAdmin;
    public Drawable mNewUserIcon;
    public CircleFramedDrawable mSavedDrawable;
    public String mSavedName;
    public Bitmap mSavedPhoto;
    public CreateUserActivity$$ExternalSyntheticLambda0 mSuccessCallback;
    public Dialog mUserCreationDialog;
    public String mUserName;
    public EditText mUserNameView;
    public boolean mWaitingForActivityResult;

    public EditUserPhotoController createEditUserPhotoController(ImageView imageView) {
        return new EditUserPhotoController(this.mActivity, this.mActivityStarter, imageView, this.mSavedPhoto, this.mSavedDrawable);
    }

    public RestrictedLockUtils.EnforcedAdmin getChangePhotoAdminRestriction(Context context) {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, "no_set_user_icon", UserHandle.myUserId());
    }

    public boolean isChangePhotoRestrictedByBase(Context context) {
        return RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_set_user_icon", UserHandle.myUserId());
    }

    public final void updateLayout() {
        CircleFramedDrawable circleFramedDrawable;
        int i = this.mCurrentState;
        if (i == -1) {
            this.mCustomDialogHelper.mDialog.dismiss();
            return;
        }
        if (i == 0) {
            this.mEditUserInfoView.setVisibility(8);
            this.mGrantAdminView.setVisibility(8);
            SharedPreferences preferences = this.mActivity.getPreferences(0);
            boolean z = preferences.getBoolean("key_add_user_long_message_displayed", false);
            int i2 = z ? R.string.user_add_user_message_short : R.string.user_add_user_message_long;
            if (!z) {
                preferences.edit().putBoolean("key_add_user_long_message_displayed", true).apply();
            }
            Drawable drawable = this.mActivity.getDrawable(R.drawable.ic_person_add);
            CustomDialogHelper customDialogHelper = this.mCustomDialogHelper;
            customDialogHelper.setVisibility(0, true);
            customDialogHelper.setVisibility(2, true);
            customDialogHelper.mDialogIcon.setImageDrawable(drawable);
            customDialogHelper.mPositiveButton.setEnabled(true);
            customDialogHelper.mDialogTitle.setText(R.string.user_add_user_title);
            customDialogHelper.mDialogMessage.setText(i2);
            customDialogHelper.mNegativeButton.setText(R.string.cancel);
            customDialogHelper.mPositiveButton.setText(R.string.next);
            CustomDialogHelper customDialogHelper2 = this.mCustomDialogHelper;
            customDialogHelper2.mDialogTitle.requestFocus();
            customDialogHelper2.mDialogTitle.sendAccessibilityEvent(8);
            return;
        }
        if (i == 1) {
            this.mEditUserInfoView.setVisibility(8);
            this.mGrantAdminView.setVisibility(0);
            CustomDialogHelper customDialogHelper3 = this.mCustomDialogHelper;
            customDialogHelper3.setVisibility(0, true);
            customDialogHelper3.setVisibility(2, true);
            customDialogHelper3.mDialogIcon.setImageDrawable(this.mActivity.getDrawable(R.drawable.ic_admin_panel_settings));
            customDialogHelper3.mDialogTitle.setText(R.string.user_grant_admin_title);
            customDialogHelper3.mDialogMessage.setText(R.string.user_grant_admin_message);
            customDialogHelper3.mNegativeButton.setText(R.string.back);
            customDialogHelper3.mPositiveButton.setText(R.string.next);
            CustomDialogHelper customDialogHelper4 = this.mCustomDialogHelper;
            customDialogHelper4.mDialogTitle.requestFocus();
            customDialogHelper4.mDialogTitle.sendAccessibilityEvent(8);
            if (this.mIsAdmin == null) {
                this.mCustomDialogHelper.mPositiveButton.setEnabled(false);
                return;
            }
            return;
        }
        if (i == 2) {
            CustomDialogHelper customDialogHelper5 = this.mCustomDialogHelper;
            customDialogHelper5.setVisibility(0, false);
            customDialogHelper5.setVisibility(2, false);
            customDialogHelper5.mDialogTitle.setText(R.string.user_info_settings_title);
            customDialogHelper5.mNegativeButton.setText(R.string.back);
            customDialogHelper5.mPositiveButton.setText(R.string.done);
            CustomDialogHelper customDialogHelper6 = this.mCustomDialogHelper;
            customDialogHelper6.mDialogTitle.requestFocus();
            customDialogHelper6.mDialogTitle.sendAccessibilityEvent(8);
            this.mEditUserInfoView.setVisibility(0);
            this.mGrantAdminView.setVisibility(8);
            return;
        }
        if (i != 3) {
            if (i < -1) {
                this.mCurrentState = -1;
                updateLayout();
                return;
            } else {
                this.mCurrentState = 3;
                updateLayout();
                return;
            }
        }
        EditUserPhotoController editUserPhotoController = this.mEditUserPhotoController;
        if (editUserPhotoController == null || (circleFramedDrawable = editUserPhotoController.mNewUserPhotoDrawable) == null) {
            circleFramedDrawable = this.mSavedDrawable;
        }
        this.mNewUserIcon = circleFramedDrawable;
        String trim = this.mUserNameView.getText().toString().trim();
        String string = this.mActivity.getString(R.string.user_new_user_name);
        if (trim.isEmpty()) {
            trim = string;
        }
        this.mUserName = trim;
        this.mCustomDialogHelper.mDialog.dismiss();
    }
}
