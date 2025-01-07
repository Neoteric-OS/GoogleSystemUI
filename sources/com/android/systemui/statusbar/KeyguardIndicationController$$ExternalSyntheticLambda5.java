package com.android.systemui.statusbar;

import android.content.res.Resources;
import com.android.wm.shell.R;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyguardIndicationController$$ExternalSyntheticLambda5 implements Supplier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ KeyguardIndicationController$$ExternalSyntheticLambda5(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                KeyguardIndicationController keyguardIndicationController = (KeyguardIndicationController) obj;
                return Boolean.valueOf(keyguardIndicationController.mDevicePolicyManager.isDeviceManaged() || keyguardIndicationController.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile());
            default:
                return ((Resources) obj).getString(R.string.do_disclosure_generic);
        }
    }
}
