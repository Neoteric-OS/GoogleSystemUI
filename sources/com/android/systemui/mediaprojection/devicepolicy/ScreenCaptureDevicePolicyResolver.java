package com.android.systemui.mediaprojection.devicepolicy;

import android.app.admin.DevicePolicyManager;
import android.os.UserHandle;
import android.os.UserManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenCaptureDevicePolicyResolver {
    public final DevicePolicyManager devicePolicyManager;
    public final UserHandle personalProfileUserHandle;
    public final UserManager userManager;
    public final UserHandle workProfileUserHandle;
    public final Lazy personalProfileScreenCaptureDisabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver$personalProfileScreenCaptureDisabled$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ScreenCaptureDevicePolicyResolver screenCaptureDevicePolicyResolver = ScreenCaptureDevicePolicyResolver.this;
            return Boolean.valueOf(screenCaptureDevicePolicyResolver.devicePolicyManager.getScreenCaptureDisabled(null, screenCaptureDevicePolicyResolver.personalProfileUserHandle.getIdentifier()));
        }
    });
    public final Lazy workProfileScreenCaptureDisabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver$workProfileScreenCaptureDisabled$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ScreenCaptureDevicePolicyResolver screenCaptureDevicePolicyResolver = ScreenCaptureDevicePolicyResolver.this;
            UserHandle userHandle = screenCaptureDevicePolicyResolver.workProfileUserHandle;
            return Boolean.valueOf(userHandle != null ? screenCaptureDevicePolicyResolver.devicePolicyManager.getScreenCaptureDisabled(null, userHandle.getIdentifier()) : false);
        }
    });
    public final Lazy disallowSharingIntoManagedProfile$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver$disallowSharingIntoManagedProfile$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ScreenCaptureDevicePolicyResolver screenCaptureDevicePolicyResolver = ScreenCaptureDevicePolicyResolver.this;
            UserHandle userHandle = screenCaptureDevicePolicyResolver.workProfileUserHandle;
            return Boolean.valueOf(userHandle != null ? screenCaptureDevicePolicyResolver.userManager.hasUserRestrictionForUser("no_sharing_into_profile", userHandle) : false);
        }
    });

    public ScreenCaptureDevicePolicyResolver(DevicePolicyManager devicePolicyManager, UserManager userManager, UserHandle userHandle, UserHandle userHandle2) {
        this.devicePolicyManager = devicePolicyManager;
        this.userManager = userManager;
        this.personalProfileUserHandle = userHandle;
        this.workProfileUserHandle = userHandle2;
    }

    public final boolean isScreenCaptureAllowed(UserHandle userHandle, UserHandle userHandle2) {
        boolean areEqual = Intrinsics.areEqual(userHandle2, this.workProfileUserHandle);
        Lazy lazy = this.workProfileScreenCaptureDisabled$delegate;
        if (areEqual && ((Boolean) lazy.getValue()).booleanValue()) {
            return false;
        }
        Lazy lazy2 = this.personalProfileScreenCaptureDisabled$delegate;
        if (((Boolean) lazy2.getValue()).booleanValue()) {
            return false;
        }
        return Intrinsics.areEqual(userHandle, this.workProfileUserHandle) ? !((Boolean) lazy.getValue()).booleanValue() : ((Intrinsics.areEqual(userHandle2, this.workProfileUserHandle) && ((Boolean) this.disallowSharingIntoManagedProfile$delegate.getValue()).booleanValue()) || ((Boolean) lazy2.getValue()).booleanValue()) ? false : true;
    }

    public final boolean isScreenCaptureCompletelyDisabled(UserHandle userHandle) {
        UserHandle userHandle2 = this.workProfileUserHandle;
        return (userHandle2 == null || !isScreenCaptureAllowed(userHandle2, userHandle)) && !isScreenCaptureAllowed(this.personalProfileUserHandle, userHandle);
    }
}
