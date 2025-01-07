package com.android.systemui.biometrics.domain.interactor;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.UserManager;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.util.time.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CredentialInteractorImpl {
    public final Context applicationContext;
    public final DevicePolicyManager devicePolicyManager;
    public final LockPatternUtils lockPatternUtils;
    public final SystemClock systemClock;
    public final UserManager userManager;

    public CredentialInteractorImpl(Context context, LockPatternUtils lockPatternUtils, UserManager userManager, DevicePolicyManager devicePolicyManager, SystemClock systemClock) {
        this.applicationContext = context;
        this.lockPatternUtils = lockPatternUtils;
        this.userManager = userManager;
        this.devicePolicyManager = devicePolicyManager;
        this.systemClock = systemClock;
    }
}
