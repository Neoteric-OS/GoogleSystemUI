package com.android.systemui.util.kotlin;

import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ManagedProfileControllerExtKt {
    public static final Flow getHasActiveWorkProfile(ManagedProfileController managedProfileController) {
        return FlowConflatedKt.conflatedCallbackFlow(new ManagedProfileControllerExtKt$hasActiveWorkProfile$1(managedProfileController, null));
    }
}
