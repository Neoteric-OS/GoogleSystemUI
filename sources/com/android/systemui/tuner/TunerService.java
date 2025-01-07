package com.android.systemui.tuner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.Dependency;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TunerService {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class ClearReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("com.android.systemui.action.CLEAR_TUNER".equals(intent.getAction())) {
                TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class));
                tunerServiceImpl.clearAllFromUser(tunerServiceImpl.mCurrentUser);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Tunable {
        void onTuningChanged(String str, String str2);
    }

    public static boolean parseIntegerSwitch(String str, boolean z) {
        if (str == null) {
            return z;
        }
        try {
            return Integer.parseInt(str) != 0;
        } catch (NumberFormatException unused) {
            return z;
        }
    }

    public abstract void addTunable(Tunable tunable, String... strArr);

    public abstract void removeTunable(Tunable tunable);
}
