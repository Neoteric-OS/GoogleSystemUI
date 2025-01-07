package com.android.settingslib.satellite;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SatelliteDialogUtils {
    public static final SatelliteDialogUtils INSTANCE = null;

    public static final StandaloneCoroutine mayStartSatelliteWarningDialog(Context context, CoroutineScope coroutineScope, int i, Function1 function1) {
        return BuildersKt.launch$default(coroutineScope, null, null, new SatelliteDialogUtils$mayStartSatelliteWarningDialog$1(context, i, function1, null), 3);
    }
}
