package com.android.systemui.statusbar;

import android.app.admin.DevicePolicyManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyguardIndicationController$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardIndicationController f$0;

    public /* synthetic */ KeyguardIndicationController$$ExternalSyntheticLambda6(KeyguardIndicationController keyguardIndicationController, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardIndicationController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final CharSequence charSequence;
        int i;
        boolean z = false;
        int i2 = this.$r8$classId;
        KeyguardIndicationController keyguardIndicationController = this.f$0;
        switch (i2) {
            case 0:
                keyguardIndicationController.mWakeLock.setAcquired(false);
                break;
            case 1:
                String deviceOwnerInfo = keyguardIndicationController.mLockPatternUtils.getDeviceOwnerInfo();
                if (deviceOwnerInfo == null && keyguardIndicationController.mLockPatternUtils.isOwnerInfoEnabled(keyguardIndicationController.getCurrentUser())) {
                    deviceOwnerInfo = keyguardIndicationController.mLockPatternUtils.getOwnerInfo(keyguardIndicationController.getCurrentUser());
                }
                ((ExecutorImpl) keyguardIndicationController.mExecutor).execute(new KeyguardIndicationController$$ExternalSyntheticLambda10(keyguardIndicationController, deviceOwnerInfo));
                break;
            default:
                if (keyguardIndicationController.mDevicePolicyManager.isDeviceManaged()) {
                    charSequence = keyguardIndicationController.mDevicePolicyManager.getDeviceOwnerOrganizationName();
                } else {
                    if (keyguardIndicationController.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile()) {
                        Iterator it = keyguardIndicationController.mUserManager.getProfiles(UserHandle.myUserId()).iterator();
                        while (true) {
                            if (it.hasNext()) {
                                UserInfo userInfo = (UserInfo) it.next();
                                if (userInfo.isManagedProfile()) {
                                    i = userInfo.id;
                                }
                            } else {
                                i = -10000;
                            }
                        }
                        if (i != -10000) {
                            charSequence = keyguardIndicationController.mDevicePolicyManager.getOrganizationNameForUser(i);
                        }
                    }
                    charSequence = null;
                }
                final Resources resources = keyguardIndicationController.mContext.getResources();
                if (DeviceConfig.getBoolean("device_policy_manager", "add-isfinanced-device", true)) {
                    z = keyguardIndicationController.mDevicePolicyManager.isFinancedDevice();
                } else if (keyguardIndicationController.mDevicePolicyManager.isDeviceManaged()) {
                    DevicePolicyManager devicePolicyManager = keyguardIndicationController.mDevicePolicyManager;
                    if (devicePolicyManager.getDeviceOwnerType(devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == 1) {
                        z = true;
                    }
                }
                ((ExecutorImpl) keyguardIndicationController.mExecutor).execute(new KeyguardIndicationController$$ExternalSyntheticLambda10(keyguardIndicationController, (CharSequence) (charSequence == null ? keyguardIndicationController.mDevicePolicyManager.getResources().getString("SystemUi.KEYGUARD_MANAGEMENT_DISCLOSURE", new KeyguardIndicationController$$ExternalSyntheticLambda5(1, resources)) : z ? resources.getString(R.string.do_financed_disclosure_with_name, charSequence) : keyguardIndicationController.mDevicePolicyManager.getResources().getString("SystemUi.KEYGUARD_NAMED_MANAGEMENT_DISCLOSURE", new Supplier() { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda13
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return resources.getString(R.string.do_disclosure_with_name, charSequence);
                    }
                }, charSequence))));
                break;
        }
    }
}
