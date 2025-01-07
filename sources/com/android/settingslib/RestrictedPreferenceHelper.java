package com.android.settingslib;

import android.R;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.RestrictedLockUtils;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RestrictedPreferenceHelper {
    public final String mAttrUserRestriction;
    public final Context mContext;
    public boolean mDisabledByAdmin;
    public boolean mDisabledByEcm;
    public boolean mDisabledSummary;
    RestrictedLockUtils.EnforcedAdmin mEnforcedAdmin;
    public final Preference mPreference;

    public RestrictedPreferenceHelper(Context context, Preference preference, AttributeSet attributeSet, int i) {
        CharSequence charSequence;
        this.mAttrUserRestriction = null;
        this.mDisabledSummary = false;
        this.mContext = context;
        this.mPreference = preference;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RestrictedPreference);
            TypedValue peekValue = obtainStyledAttributes.peekValue(1);
            if (peekValue == null || peekValue.type != 3) {
                charSequence = null;
            } else {
                int i2 = peekValue.resourceId;
                charSequence = i2 != 0 ? context.getText(i2) : peekValue.string;
            }
            String charSequence2 = charSequence == null ? null : charSequence.toString();
            this.mAttrUserRestriction = charSequence2;
            if (RestrictedLockUtilsInternal.hasBaseUserRestriction(context, charSequence2, UserHandle.myUserId())) {
                this.mAttrUserRestriction = null;
                return;
            }
            TypedValue peekValue2 = obtainStyledAttributes.peekValue(0);
            if (peekValue2 != null) {
                this.mDisabledSummary = peekValue2.type == 18 && peekValue2.data != 0;
            }
        }
    }

    public final void onAttachedToHierarchy() {
        String str = this.mAttrUserRestriction;
        if (str != null) {
            setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, str, UserHandle.myUserId()));
        }
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        TextView textView;
        if (this.mDisabledByAdmin || this.mDisabledByEcm) {
            preferenceViewHolder.itemView.setEnabled(true);
        }
        if (!this.mDisabledSummary || (textView = (TextView) preferenceViewHolder.findViewById(R.id.summary)) == null) {
            return;
        }
        String string = ((DevicePolicyManager) this.mContext.getSystemService(DevicePolicyManager.class)).getResources().getString("Settings.CONTROLLED_BY_ADMIN_SUMMARY", new Supplier() { // from class: com.android.settingslib.RestrictedPreferenceHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return RestrictedPreferenceHelper.this.mContext.getString(com.android.wm.shell.R.string.disabled_by_admin_summary_text);
            }
        });
        if (this.mDisabledByAdmin) {
            textView.setText(string);
        } else if (this.mDisabledByEcm) {
            textView.setText(com.android.wm.shell.R.string.disabled_by_app_ops_text);
        } else if (TextUtils.equals(string, textView.getText())) {
            textView.setText((CharSequence) null);
        }
    }

    public final boolean performClick() {
        if (this.mDisabledByAdmin) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mContext, this.mEnforcedAdmin);
            return true;
        }
        if (!this.mDisabledByEcm) {
            return false;
        }
        this.mContext.startActivity(null);
        return true;
    }

    public final void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        boolean z;
        this.mEnforcedAdmin = null;
        if (enforcedAdmin != null) {
            RestrictedLockUtils.EnforcedAdmin enforcedAdmin2 = new RestrictedLockUtils.EnforcedAdmin();
            enforcedAdmin2.component = null;
            enforcedAdmin2.enforcedRestriction = null;
            enforcedAdmin2.user = null;
            enforcedAdmin2.component = enforcedAdmin.component;
            enforcedAdmin2.enforcedRestriction = enforcedAdmin.enforcedRestriction;
            enforcedAdmin2.user = enforcedAdmin.user;
            this.mEnforcedAdmin = enforcedAdmin2;
            z = true;
        } else {
            z = false;
        }
        if (this.mDisabledByAdmin != z) {
            this.mDisabledByAdmin = z;
            updateDisabledState();
        }
    }

    public final void setDisabledByEcm() {
        if (this.mDisabledByEcm) {
            this.mDisabledByEcm = false;
            updateDisabledState();
        }
    }

    public final void updateDisabledState() {
        boolean z = (this.mDisabledByAdmin || this.mDisabledByEcm) ? false : true;
        Preference preference = this.mPreference;
        if (!(preference instanceof RestrictedTopLevelPreference)) {
            preference.setEnabled(z);
        }
        if (preference instanceof PrimarySwitchPreference) {
            PrimarySwitchPreference primarySwitchPreference = (PrimarySwitchPreference) preference;
            primarySwitchPreference.mEnableSwitch = z;
            CompoundButton compoundButton = primarySwitchPreference.mSwitch;
            if (compoundButton != null) {
                compoundButton.setEnabled(z);
            }
        }
        if (z || !this.mDisabledByEcm) {
            return;
        }
        preference.setSummary(com.android.wm.shell.R.string.disabled_by_app_ops_text);
    }
}
