package com.android.systemui.qs;

import android.app.Dialog;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.net.VpnConfig;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.android.wm.shell.R;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSSecurityFooterUtils implements DialogInterface.OnClickListener {
    public final ActivityStarter mActivityStarter;
    public final Handler mBgHandler;
    public final Context mContext;
    public SystemUIDialog mDialog;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final DevicePolicyManager mDpm;
    public final Handler mMainHandler;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementDialogCaCertStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementDialogNetworkStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementDialogStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementMessageSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementMonitoringStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementMultipleVpnStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mManagementTitleSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mMonitoringSubtitleCaCertStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mMonitoringSubtitleNetworkStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mMonitoringSubtitleVpnStringSupplier;
    public final SecurityController mSecurityController;
    public final AtomicBoolean mShouldUseSettingsButton = new AtomicBoolean(false);
    public final UserTracker mUserTracker;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mViewPoliciesButtonStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mWorkProfileDialogCaCertStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mWorkProfileDialogNetworkStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mWorkProfileMonitoringStringSupplier;
    public final QSSecurityFooterUtils$$ExternalSyntheticLambda3 mWorkProfileNetworkStringSupplier;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VpnSpan extends ClickableSpan {
        public VpnSpan() {
        }

        public final boolean equals(Object obj) {
            return obj instanceof VpnSpan;
        }

        public final int hashCode() {
            return 314159257;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            Intent intent = new Intent("android.settings.VPN_SETTINGS");
            QSSecurityFooterUtils.this.mDialog.dismiss();
            QSSecurityFooterUtils.this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3] */
    public QSSecurityFooterUtils(Context context, DevicePolicyManager devicePolicyManager, UserTracker userTracker, Handler handler, ActivityStarter activityStarter, SecurityController securityController, Looper looper, DialogTransitionAnimator dialogTransitionAnimator) {
        final int i = 0;
        this.mManagementTitleSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i2 = i;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i2) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i2 = 14;
        this.mManagementMessageSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i2;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i3 = 1;
        this.mManagementMonitoringStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i3;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i4 = 2;
        this.mManagementMultipleVpnStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i4;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i5 = 3;
        this.mWorkProfileMonitoringStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i5;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i6 = 4;
        this.mWorkProfileNetworkStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i6;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i7 = 5;
        this.mMonitoringSubtitleCaCertStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i7;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i8 = 6;
        this.mMonitoringSubtitleNetworkStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i8;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i9 = 7;
        this.mMonitoringSubtitleVpnStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i9;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i10 = 8;
        this.mViewPoliciesButtonStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i10;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i11 = 9;
        this.mManagementDialogStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i11;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i12 = 10;
        this.mManagementDialogCaCertStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i12;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i13 = 11;
        this.mWorkProfileDialogCaCertStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i13;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i14 = 12;
        this.mManagementDialogNetworkStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i14;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        final int i15 = 13;
        this.mWorkProfileDialogNetworkStringSupplier = new Supplier(this) { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda3
            public final /* synthetic */ QSSecurityFooterUtils f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                int i22 = i15;
                QSSecurityFooterUtils qSSecurityFooterUtils = this.f$0;
                switch (i22) {
                    case 0:
                        Context context2 = qSSecurityFooterUtils.mContext;
                        if (context2 == null) {
                            return null;
                        }
                        return context2.getString(R.string.monitoring_title_device_owned);
                    case 1:
                        Context context3 = qSSecurityFooterUtils.mContext;
                        if (context3 == null) {
                            return null;
                        }
                        return context3.getString(R.string.quick_settings_disclosure_management_monitoring);
                    case 2:
                        Context context4 = qSSecurityFooterUtils.mContext;
                        if (context4 == null) {
                            return null;
                        }
                        return context4.getString(R.string.quick_settings_disclosure_management_vpns);
                    case 3:
                        Context context5 = qSSecurityFooterUtils.mContext;
                        if (context5 == null) {
                            return null;
                        }
                        return context5.getString(R.string.quick_settings_disclosure_managed_profile_monitoring);
                    case 4:
                        Context context6 = qSSecurityFooterUtils.mContext;
                        if (context6 == null) {
                            return null;
                        }
                        return context6.getString(R.string.quick_settings_disclosure_managed_profile_network_activity);
                    case 5:
                        Context context7 = qSSecurityFooterUtils.mContext;
                        if (context7 == null) {
                            return null;
                        }
                        return context7.getString(R.string.monitoring_subtitle_ca_certificate);
                    case 6:
                        Context context8 = qSSecurityFooterUtils.mContext;
                        if (context8 == null) {
                            return null;
                        }
                        return context8.getString(R.string.monitoring_subtitle_network_logging);
                    case 7:
                        Context context9 = qSSecurityFooterUtils.mContext;
                        if (context9 == null) {
                            return null;
                        }
                        return context9.getString(R.string.monitoring_subtitle_vpn);
                    case 8:
                        Context context10 = qSSecurityFooterUtils.mContext;
                        if (context10 == null) {
                            return null;
                        }
                        return context10.getString(R.string.monitoring_button_view_policies);
                    case 9:
                        Context context11 = qSSecurityFooterUtils.mContext;
                        if (context11 == null) {
                            return null;
                        }
                        return context11.getString(R.string.monitoring_description_management);
                    case 10:
                        Context context12 = qSSecurityFooterUtils.mContext;
                        if (context12 == null) {
                            return null;
                        }
                        return context12.getString(R.string.monitoring_description_management_ca_certificate);
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Context context13 = qSSecurityFooterUtils.mContext;
                        if (context13 == null) {
                            return null;
                        }
                        return context13.getString(R.string.monitoring_description_managed_profile_ca_certificate);
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Context context14 = qSSecurityFooterUtils.mContext;
                        if (context14 == null) {
                            return null;
                        }
                        return context14.getString(R.string.monitoring_description_management_network_logging);
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        Context context15 = qSSecurityFooterUtils.mContext;
                        if (context15 == null) {
                            return null;
                        }
                        return context15.getString(R.string.monitoring_description_managed_profile_network_logging);
                    default:
                        Context context16 = qSSecurityFooterUtils.mContext;
                        if (context16 == null) {
                            return null;
                        }
                        return context16.getString(R.string.quick_settings_disclosure_management);
                }
            }
        };
        this.mContext = context;
        this.mDpm = devicePolicyManager;
        this.mUserTracker = userTracker;
        this.mMainHandler = handler;
        this.mActivityStarter = activityStarter;
        this.mSecurityController = securityController;
        this.mBgHandler = new Handler(looper);
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
    }

    public View createDialogView(Context context) {
        VpnConfig vpnConfig;
        SpannableStringBuilder spannableStringBuilder;
        Drawable loadIcon;
        SecurityControllerImpl securityControllerImpl = (SecurityControllerImpl) this.mSecurityController;
        securityControllerImpl.getClass();
        CharSequence charSequence = null;
        if (securityControllerImpl.mDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(new UserHandle(securityControllerImpl.mCurrentUserId)) != null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.quick_settings_footer_dialog_parental_controls, (ViewGroup) null, false);
            DeviceAdminInfo deviceAdminInfo = ((SecurityControllerImpl) this.mSecurityController).getDeviceAdminInfo();
            SecurityControllerImpl securityControllerImpl2 = (SecurityControllerImpl) this.mSecurityController;
            if (deviceAdminInfo == null) {
                securityControllerImpl2.getClass();
                loadIcon = null;
            } else {
                loadIcon = deviceAdminInfo.loadIcon(securityControllerImpl2.mPackageManager);
            }
            if (loadIcon != null) {
                ((ImageView) inflate.findViewById(R.id.parental_controls_icon)).setImageDrawable(loadIcon);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.parental_controls_title);
            SecurityControllerImpl securityControllerImpl3 = (SecurityControllerImpl) this.mSecurityController;
            if (deviceAdminInfo == null) {
                securityControllerImpl3.getClass();
            } else {
                charSequence = deviceAdminInfo.loadLabel(securityControllerImpl3.mPackageManager);
            }
            textView.setText(charSequence);
            return inflate;
        }
        boolean isDeviceManaged = ((SecurityControllerImpl) this.mSecurityController).mDevicePolicyManager.isDeviceManaged();
        SecurityControllerImpl securityControllerImpl4 = (SecurityControllerImpl) this.mSecurityController;
        boolean z = securityControllerImpl4.getWorkProfileUserId(securityControllerImpl4.mCurrentUserId) != -10000;
        CharSequence deviceOwnerOrganizationName = ((SecurityControllerImpl) this.mSecurityController).mDevicePolicyManager.getDeviceOwnerOrganizationName();
        SecurityControllerImpl securityControllerImpl5 = (SecurityControllerImpl) this.mSecurityController;
        Boolean bool = (Boolean) securityControllerImpl5.mHasCACerts.get(Integer.valueOf(securityControllerImpl5.mCurrentUserId));
        boolean z2 = bool != null && bool.booleanValue();
        boolean hasCACertInWorkProfile = ((SecurityControllerImpl) this.mSecurityController).hasCACertInWorkProfile();
        boolean isNetworkLoggingEnabled = ((SecurityControllerImpl) this.mSecurityController).mDevicePolicyManager.isNetworkLoggingEnabled(null);
        SecurityControllerImpl securityControllerImpl6 = (SecurityControllerImpl) this.mSecurityController;
        VpnConfig vpnConfig2 = (VpnConfig) securityControllerImpl6.mCurrentVpns.get(securityControllerImpl6.mVpnUserId);
        String nameForVpnConfig = vpnConfig2 != null ? securityControllerImpl6.getNameForVpnConfig(vpnConfig2, new UserHandle(securityControllerImpl6.mVpnUserId)) : null;
        SecurityControllerImpl securityControllerImpl7 = (SecurityControllerImpl) this.mSecurityController;
        int workProfileUserId = securityControllerImpl7.getWorkProfileUserId(securityControllerImpl7.mVpnUserId);
        String nameForVpnConfig2 = (workProfileUserId == -10000 || (vpnConfig = (VpnConfig) securityControllerImpl7.mCurrentVpns.get(workProfileUserId)) == null) ? null : securityControllerImpl7.getNameForVpnConfig(vpnConfig, UserHandle.of(workProfileUserId));
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.quick_settings_footer_dialog, (ViewGroup) null, false);
        ((TextView) inflate2.findViewById(R.id.device_management_subtitle)).setText(getManagementTitle(deviceOwnerOrganizationName));
        String string = !isDeviceManaged ? null : deviceOwnerOrganizationName != null ? isFinancedDevice() ? this.mContext.getString(R.string.monitoring_financed_description_named_management, deviceOwnerOrganizationName, deviceOwnerOrganizationName) : this.mDpm.getResources().getString("SystemUi.QS_DIALOG_NAMED_MANAGEMENT", new QSSecurityFooterUtils$$ExternalSyntheticLambda0(this, deviceOwnerOrganizationName, 4), deviceOwnerOrganizationName) : this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT", this.mManagementDialogStringSupplier);
        if (string == null) {
            inflate2.findViewById(R.id.device_management_disclosures).setVisibility(8);
        } else {
            inflate2.findViewById(R.id.device_management_disclosures).setVisibility(0);
            ((TextView) inflate2.findViewById(R.id.device_management_warning)).setText(string);
            this.mShouldUseSettingsButton.set(true);
        }
        String string2 = (z2 || hasCACertInWorkProfile) ? isDeviceManaged ? this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_CA_CERT", this.mManagementDialogCaCertStringSupplier) : hasCACertInWorkProfile ? this.mDpm.getResources().getString("SystemUi.QS_DIALOG_WORK_PROFILE_CA_CERT", this.mWorkProfileDialogCaCertStringSupplier) : this.mContext.getString(R.string.monitoring_description_ca_certificate) : null;
        if (string2 == null) {
            inflate2.findViewById(R.id.ca_certs_disclosures).setVisibility(8);
        } else {
            inflate2.findViewById(R.id.ca_certs_disclosures).setVisibility(0);
            TextView textView2 = (TextView) inflate2.findViewById(R.id.ca_certs_warning);
            textView2.setText(string2);
            textView2.setMovementMethod(new LinkMovementMethod());
            ((TextView) inflate2.findViewById(R.id.ca_certs_subtitle)).setText(this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MONITORING_CA_CERT_SUBTITLE", this.mMonitoringSubtitleCaCertStringSupplier));
        }
        String string3 = !isNetworkLoggingEnabled ? null : isDeviceManaged ? this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_NETWORK", this.mManagementDialogNetworkStringSupplier) : this.mDpm.getResources().getString("SystemUi.QS_DIALOG_WORK_PROFILE_NETWORK", this.mWorkProfileDialogNetworkStringSupplier);
        if (string3 == null) {
            inflate2.findViewById(R.id.network_logging_disclosures).setVisibility(8);
        } else {
            inflate2.findViewById(R.id.network_logging_disclosures).setVisibility(0);
            ((TextView) inflate2.findViewById(R.id.network_logging_warning)).setText(string3);
            ((TextView) inflate2.findViewById(R.id.network_logging_subtitle)).setText(this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MONITORING_NETWORK_SUBTITLE", this.mMonitoringSubtitleNetworkStringSupplier));
        }
        if (nameForVpnConfig == null && nameForVpnConfig2 == null) {
            spannableStringBuilder = null;
        } else {
            spannableStringBuilder = new SpannableStringBuilder();
            if (isDeviceManaged) {
                if (nameForVpnConfig == null || nameForVpnConfig2 == null) {
                    if (nameForVpnConfig == null) {
                        nameForVpnConfig = nameForVpnConfig2;
                    }
                    spannableStringBuilder.append((CharSequence) this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_NAMED_VPN", new QSSecurityFooterUtils$$ExternalSyntheticLambda1(this, nameForVpnConfig, 1), nameForVpnConfig));
                } else {
                    spannableStringBuilder.append((CharSequence) this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_TWO_NAMED_VPN", new QSSecurityFooterUtils$$ExternalSyntheticLambda2(this, nameForVpnConfig, nameForVpnConfig2, 1), nameForVpnConfig, nameForVpnConfig2));
                }
            } else if (nameForVpnConfig != null && nameForVpnConfig2 != null) {
                spannableStringBuilder.append((CharSequence) this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_TWO_NAMED_VPN", new QSSecurityFooterUtils$$ExternalSyntheticLambda2(this, nameForVpnConfig, nameForVpnConfig2, 2), nameForVpnConfig, nameForVpnConfig2));
            } else if (nameForVpnConfig2 != null) {
                spannableStringBuilder.append((CharSequence) this.mDpm.getResources().getString("SystemUi.QS_DIALOG_WORK_PROFILE_NAMED_VPN", new QSSecurityFooterUtils$$ExternalSyntheticLambda1(this, nameForVpnConfig2, 2), nameForVpnConfig2));
            } else if (z) {
                spannableStringBuilder.append((CharSequence) this.mDpm.getResources().getString("SystemUi.QS_DIALOG_PERSONAL_PROFILE_NAMED_VPN", new QSSecurityFooterUtils$$ExternalSyntheticLambda1(this, nameForVpnConfig, 3), nameForVpnConfig));
            } else {
                spannableStringBuilder.append((CharSequence) this.mContext.getString(R.string.monitoring_description_named_vpn, nameForVpnConfig));
            }
            spannableStringBuilder.append((CharSequence) this.mContext.getString(R.string.monitoring_description_vpn_settings_separator));
            spannableStringBuilder.append(this.mContext.getString(R.string.monitoring_description_vpn_settings), new VpnSpan(), 0);
        }
        if (spannableStringBuilder == null) {
            inflate2.findViewById(R.id.vpn_disclosures).setVisibility(8);
        } else {
            inflate2.findViewById(R.id.vpn_disclosures).setVisibility(0);
            TextView textView3 = (TextView) inflate2.findViewById(R.id.vpn_warning);
            textView3.setText(spannableStringBuilder);
            textView3.setMovementMethod(new LinkMovementMethod());
            ((TextView) inflate2.findViewById(R.id.vpn_subtitle)).setText(this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MONITORING_VPN_SUBTITLE", this.mMonitoringSubtitleVpnStringSupplier));
        }
        boolean z3 = string != null;
        int i = string2 != null ? 1 : 0;
        boolean z4 = string3 != null;
        boolean z5 = spannableStringBuilder != null;
        if (!z3) {
            int i2 = z4 ? i + 1 : i;
            if (z5) {
                i2++;
            }
            if (i2 == 1) {
                if (i != 0) {
                    inflate2.findViewById(R.id.ca_certs_subtitle).setVisibility(8);
                }
                if (z4) {
                    inflate2.findViewById(R.id.network_logging_subtitle).setVisibility(8);
                }
                if (z5) {
                    inflate2.findViewById(R.id.vpn_subtitle).setVisibility(8);
                }
            }
        }
        return inflate2;
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public CharSequence getManagementTitle(CharSequence charSequence) {
        return (charSequence == null || !isFinancedDevice()) ? this.mDpm.getResources().getString("SystemUi.QS_DIALOG_MANAGEMENT_TITLE", this.mManagementTitleSupplier) : this.mContext.getString(R.string.monitoring_title_financed_device, charSequence);
    }

    public final String getMangedDeviceGeneralText(CharSequence charSequence) {
        return charSequence == null ? this.mDpm.getResources().getString("SystemUi.QS_MSG_MANAGEMENT", this.mManagementMessageSupplier) : isFinancedDevice() ? this.mContext.getString(R.string.quick_settings_financed_disclosure_named_management, charSequence) : this.mDpm.getResources().getString("SystemUi.QS_MSG_NAMED_MANAGEMENT", new QSSecurityFooterUtils$$ExternalSyntheticLambda0(this, charSequence, 1), charSequence);
    }

    public String getSettingsButton() {
        return this.mDpm.getResources().getString("SystemUi.QS_DIALOG_VIEW_POLICIES", this.mViewPoliciesButtonStringSupplier);
    }

    public final boolean isFinancedDevice() {
        if (DeviceConfig.getBoolean("device_policy_manager", "add-isfinanced-device", true)) {
            return ((SecurityControllerImpl) this.mSecurityController).mDevicePolicyManager.isFinancedDevice();
        }
        if (((SecurityControllerImpl) this.mSecurityController).mDevicePolicyManager.isDeviceManaged()) {
            SecurityController securityController = this.mSecurityController;
            if (((SecurityControllerImpl) securityController).mDevicePolicyManager.getDeviceOwnerType(((SecurityControllerImpl) securityController).mDevicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            Intent intent = new Intent("android.settings.ENTERPRISE_PRIVACY_SETTINGS");
            dialogInterface.dismiss();
            this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
        }
    }
}
