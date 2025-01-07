package com.android.systemui.util.kotlin;

import android.content.SharedPreferences;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SharedPreferencesExt {
    public static Flow observe(SharedPreferences sharedPreferences) {
        return FlowConflatedKt.conflatedCallbackFlow(new SharedPreferencesExt$observe$1(sharedPreferences, null));
    }
}
