package com.android.settingslib.utils;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.user.CreateUserActivity;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CustomDialogHelper {
    public Button mBackButton;
    public CreateUserActivity mContext;
    public LinearLayout mCustomLayout;
    public Dialog mDialog;
    public ImageView mDialogIcon;
    public TextView mDialogMessage;
    public TextView mDialogTitle;
    public Button mNegativeButton;
    public Button mPositiveButton;

    public final void setButton(int i, int i2, View.OnClickListener onClickListener) {
        if (i == 4) {
            this.mBackButton.setText(i2);
            this.mBackButton.setVisibility(0);
            this.mBackButton.setOnClickListener(onClickListener);
        } else if (i == 5) {
            this.mNegativeButton.setText(i2);
            this.mNegativeButton.setVisibility(0);
            this.mNegativeButton.setOnClickListener(onClickListener);
        } else {
            if (i != 6) {
                return;
            }
            this.mPositiveButton.setText(i2);
            this.mPositiveButton.setVisibility(0);
            this.mPositiveButton.setOnClickListener(onClickListener);
        }
    }

    public final void setVisibility(int i, boolean z) {
        int i2 = z ? 0 : 8;
        if (i == 0) {
            this.mDialogIcon.setVisibility(i2);
            return;
        }
        if (i == 1) {
            this.mDialogTitle.setVisibility(i2);
            return;
        }
        if (i == 2) {
            this.mDialogMessage.setVisibility(i2);
            return;
        }
        if (i == 4) {
            this.mBackButton.setVisibility(i2);
        } else if (i == 5) {
            this.mNegativeButton.setVisibility(i2);
        } else {
            if (i != 6) {
                return;
            }
            this.mPositiveButton.setVisibility(i2);
        }
    }
}
