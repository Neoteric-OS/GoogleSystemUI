package com.android.systemui.util.settings;

import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SettingsProxyExt {
    public static Flow observerFlow(UserSettingsProxy userSettingsProxy, int i, String... strArr) {
        return FlowConflatedKt.conflatedCallbackFlow(new SettingsProxyExt$observerFlow$1(strArr, userSettingsProxy, i, null));
    }

    public static Flow observerFlow(SettingsProxy settingsProxy, String... strArr) {
        return FlowConflatedKt.conflatedCallbackFlow(new SettingsProxyExt$observerFlow$2(strArr, settingsProxy, null));
    }
}
