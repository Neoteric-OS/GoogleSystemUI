package com.google.android.systemui.elmyra.sensors.config;

import android.util.Range;
import com.android.systemui.DejankUtils;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GestureConfiguration f$0;

    public /* synthetic */ GestureConfiguration$$ExternalSyntheticLambda0(GestureConfiguration gestureConfiguration, int i) {
        this.$r8$classId = i;
        this.f$0 = gestureConfiguration;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        GestureConfiguration gestureConfiguration = this.f$0;
        switch (i) {
            case 0:
                gestureConfiguration.getClass();
                Float f = (Float) DejankUtils.whitelistIpcs(new GestureConfiguration$$ExternalSyntheticLambda3(gestureConfiguration));
                float floatValue = f.floatValue();
                if (!GestureConfiguration.SENSITIVITY_RANGE.contains((Range) f)) {
                    floatValue = 0.5f;
                }
                gestureConfiguration.mSensitivity = floatValue;
                GestureConfiguration.Listener listener = gestureConfiguration.mListener;
                if (listener != null) {
                    listener.onGestureConfigurationChanged(gestureConfiguration);
                    break;
                }
                break;
            case 1:
                ((ScreenStateAdjustment) obj).mCallback = gestureConfiguration.mAdjustmentCallback;
                break;
            default:
                gestureConfiguration.getClass();
                Float f2 = (Float) DejankUtils.whitelistIpcs(new GestureConfiguration$$ExternalSyntheticLambda3(gestureConfiguration));
                float floatValue2 = f2.floatValue();
                if (!GestureConfiguration.SENSITIVITY_RANGE.contains((Range) f2)) {
                    floatValue2 = 0.5f;
                }
                gestureConfiguration.mSensitivity = floatValue2;
                GestureConfiguration.Listener listener2 = gestureConfiguration.mListener;
                if (listener2 != null) {
                    listener2.onGestureConfigurationChanged(gestureConfiguration);
                    break;
                }
                break;
        }
    }
}
