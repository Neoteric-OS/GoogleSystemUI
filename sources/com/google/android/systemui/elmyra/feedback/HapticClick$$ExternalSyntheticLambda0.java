package com.google.android.systemui.elmyra.feedback;

import android.os.Vibrator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HapticClick$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HapticClick f$0;

    public /* synthetic */ HapticClick$$ExternalSyntheticLambda0(HapticClick hapticClick, int i) {
        this.$r8$classId = i;
        this.f$0 = hapticClick;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        HapticClick hapticClick = this.f$0;
        Vibrator vibrator = (Vibrator) obj;
        switch (i) {
            case 0:
                vibrator.vibrate(hapticClick.mProgressVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                break;
            default:
                vibrator.vibrate(hapticClick.mResolveVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                break;
        }
    }
}
