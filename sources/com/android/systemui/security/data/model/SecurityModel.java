package com.android.systemui.security.data.model;

import android.app.admin.DeviceAdminInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.internal.net.VpnConfig;
import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SecurityModel {
    public static final Companion Companion = new Companion();
    public final Drawable deviceAdminIcon;
    public final String deviceOwnerOrganizationName;
    public final boolean hasCACertInCurrentUser;
    public final boolean hasCACertInWorkProfile;
    public final boolean hasWorkProfile;
    public final boolean isDeviceManaged;
    public final boolean isNetworkLoggingEnabled;
    public final boolean isParentalControlsEnabled;
    public final boolean isProfileOwnerOfOrganizationOwnedDevice;
    public final boolean isVpnBranded;
    public final boolean isWorkProfileOn;
    public final String primaryVpnName;
    public final String workProfileOrganizationName;
    public final String workProfileVpnName;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static Object create(SecurityController securityController, CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
            return BuildersKt.withContext(coroutineDispatcher, new SecurityModel$Companion$create$2(securityController, null), continuation);
        }

        public final SecurityModel create(SecurityController securityController) {
            VpnConfig vpnConfig;
            SecurityControllerImpl securityControllerImpl = (SecurityControllerImpl) securityController;
            securityControllerImpl.getClass();
            DeviceAdminInfo deviceAdminInfo = securityControllerImpl.mDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(new UserHandle(securityControllerImpl.mCurrentUserId)) != null ? securityControllerImpl.getDeviceAdminInfo() : null;
            boolean isDeviceManaged = securityControllerImpl.mDevicePolicyManager.isDeviceManaged();
            boolean z = securityControllerImpl.getWorkProfileUserId(securityControllerImpl.mCurrentUserId) != -10000;
            UserHandle of = UserHandle.of(securityControllerImpl.getWorkProfileUserId(securityControllerImpl.mCurrentUserId));
            boolean z2 = (of == null || securityControllerImpl.mUserManager.isQuietModeEnabled(of)) ? false : true;
            boolean isOrganizationOwnedDeviceWithManagedProfile = securityControllerImpl.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
            CharSequence deviceOwnerOrganizationName = securityControllerImpl.mDevicePolicyManager.getDeviceOwnerOrganizationName();
            String obj = deviceOwnerOrganizationName != null ? deviceOwnerOrganizationName.toString() : null;
            int workProfileUserId = securityControllerImpl.getWorkProfileUserId(securityControllerImpl.mCurrentUserId);
            CharSequence organizationNameForUser = workProfileUserId == -10000 ? null : securityControllerImpl.mDevicePolicyManager.getOrganizationNameForUser(workProfileUserId);
            String obj2 = organizationNameForUser != null ? organizationNameForUser.toString() : null;
            boolean isNetworkLoggingEnabled = securityControllerImpl.mDevicePolicyManager.isNetworkLoggingEnabled(null);
            boolean isVpnBranded = securityControllerImpl.isVpnBranded();
            VpnConfig vpnConfig2 = (VpnConfig) securityControllerImpl.mCurrentVpns.get(securityControllerImpl.mVpnUserId);
            String nameForVpnConfig = vpnConfig2 != null ? securityControllerImpl.getNameForVpnConfig(vpnConfig2, new UserHandle(securityControllerImpl.mVpnUserId)) : null;
            int workProfileUserId2 = securityControllerImpl.getWorkProfileUserId(securityControllerImpl.mVpnUserId);
            String nameForVpnConfig2 = (workProfileUserId2 == -10000 || (vpnConfig = (VpnConfig) securityControllerImpl.mCurrentVpns.get(workProfileUserId2)) == null) ? null : securityControllerImpl.getNameForVpnConfig(vpnConfig, UserHandle.of(workProfileUserId2));
            Boolean bool = (Boolean) securityControllerImpl.mHasCACerts.get(Integer.valueOf(securityControllerImpl.mCurrentUserId));
            return new SecurityModel(isDeviceManaged, z, z2, isOrganizationOwnedDeviceWithManagedProfile, obj, obj2, isNetworkLoggingEnabled, isVpnBranded, nameForVpnConfig, nameForVpnConfig2, bool != null && bool.booleanValue(), securityControllerImpl.hasCACertInWorkProfile(), securityControllerImpl.mDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(new UserHandle(securityControllerImpl.mCurrentUserId)) != null, deviceAdminInfo != null ? deviceAdminInfo.loadIcon(securityControllerImpl.mPackageManager) : null);
        }
    }

    public SecurityModel(boolean z, boolean z2, boolean z3, boolean z4, String str, String str2, boolean z5, boolean z6, String str3, String str4, boolean z7, boolean z8, boolean z9, Drawable drawable) {
        this.isDeviceManaged = z;
        this.hasWorkProfile = z2;
        this.isWorkProfileOn = z3;
        this.isProfileOwnerOfOrganizationOwnedDevice = z4;
        this.deviceOwnerOrganizationName = str;
        this.workProfileOrganizationName = str2;
        this.isNetworkLoggingEnabled = z5;
        this.isVpnBranded = z6;
        this.primaryVpnName = str3;
        this.workProfileVpnName = str4;
        this.hasCACertInCurrentUser = z7;
        this.hasCACertInWorkProfile = z8;
        this.isParentalControlsEnabled = z9;
        this.deviceAdminIcon = drawable;
    }

    public static final SecurityModel create(SecurityController securityController) {
        return Companion.create(securityController);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecurityModel)) {
            return false;
        }
        SecurityModel securityModel = (SecurityModel) obj;
        return this.isDeviceManaged == securityModel.isDeviceManaged && this.hasWorkProfile == securityModel.hasWorkProfile && this.isWorkProfileOn == securityModel.isWorkProfileOn && this.isProfileOwnerOfOrganizationOwnedDevice == securityModel.isProfileOwnerOfOrganizationOwnedDevice && Intrinsics.areEqual(this.deviceOwnerOrganizationName, securityModel.deviceOwnerOrganizationName) && Intrinsics.areEqual(this.workProfileOrganizationName, securityModel.workProfileOrganizationName) && this.isNetworkLoggingEnabled == securityModel.isNetworkLoggingEnabled && this.isVpnBranded == securityModel.isVpnBranded && Intrinsics.areEqual(this.primaryVpnName, securityModel.primaryVpnName) && Intrinsics.areEqual(this.workProfileVpnName, securityModel.workProfileVpnName) && this.hasCACertInCurrentUser == securityModel.hasCACertInCurrentUser && this.hasCACertInWorkProfile == securityModel.hasCACertInWorkProfile && this.isParentalControlsEnabled == securityModel.isParentalControlsEnabled && Intrinsics.areEqual(this.deviceAdminIcon, securityModel.deviceAdminIcon);
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isDeviceManaged) * 31, 31, this.hasWorkProfile), 31, this.isWorkProfileOn), 31, this.isProfileOwnerOfOrganizationOwnedDevice);
        String str = this.deviceOwnerOrganizationName;
        int hashCode = (m + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.workProfileOrganizationName;
        int m2 = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((hashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.isNetworkLoggingEnabled), 31, this.isVpnBranded);
        String str3 = this.primaryVpnName;
        int hashCode2 = (m2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.workProfileVpnName;
        int m3 = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((hashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31, 31, this.hasCACertInCurrentUser), 31, this.hasCACertInWorkProfile), 31, this.isParentalControlsEnabled);
        Drawable drawable = this.deviceAdminIcon;
        return m3 + (drawable != null ? drawable.hashCode() : 0);
    }

    public final String toString() {
        return "SecurityModel(isDeviceManaged=" + this.isDeviceManaged + ", hasWorkProfile=" + this.hasWorkProfile + ", isWorkProfileOn=" + this.isWorkProfileOn + ", isProfileOwnerOfOrganizationOwnedDevice=" + this.isProfileOwnerOfOrganizationOwnedDevice + ", deviceOwnerOrganizationName=" + this.deviceOwnerOrganizationName + ", workProfileOrganizationName=" + this.workProfileOrganizationName + ", isNetworkLoggingEnabled=" + this.isNetworkLoggingEnabled + ", isVpnBranded=" + this.isVpnBranded + ", primaryVpnName=" + this.primaryVpnName + ", workProfileVpnName=" + this.workProfileVpnName + ", hasCACertInCurrentUser=" + this.hasCACertInCurrentUser + ", hasCACertInWorkProfile=" + this.hasCACertInWorkProfile + ", isParentalControlsEnabled=" + this.isParentalControlsEnabled + ", deviceAdminIcon=" + this.deviceAdminIcon + ")";
    }
}
