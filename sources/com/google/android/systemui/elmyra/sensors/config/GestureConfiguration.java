package com.google.android.systemui.elmyra.sensors.config;

import android.content.Context;
import android.provider.Settings;
import android.util.Range;
import com.android.systemui.DejankUtils;
import com.google.android.systemui.elmyra.UserContentObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GestureConfiguration {
    public static final Range SENSITIVITY_RANGE = Range.create(Float.valueOf(0.0f), Float.valueOf(1.0f));
    public final GestureConfiguration$$ExternalSyntheticLambda0 mAdjustmentCallback = new GestureConfiguration$$ExternalSyntheticLambda0(this, 0);
    public final List mAdjustments;
    public final Context mContext;
    public Listener mListener;
    public float mSensitivity;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration);
    }

    public GestureConfiguration(Context context, Set set) {
        this.mContext = context;
        ArrayList arrayList = new ArrayList(set);
        this.mAdjustments = arrayList;
        arrayList.forEach(new GestureConfiguration$$ExternalSyntheticLambda0(this, 1));
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_sensitivity"), new GestureConfiguration$$ExternalSyntheticLambda0(this, 2), true);
        Float f = (Float) DejankUtils.whitelistIpcs(new GestureConfiguration$$ExternalSyntheticLambda3(this));
        this.mSensitivity = SENSITIVITY_RANGE.contains((Range) f) ? f.floatValue() : 0.5f;
    }

    public final float getSensitivity() {
        float f = this.mSensitivity;
        for (int i = 0; i < ((ArrayList) this.mAdjustments).size(); i++) {
            Range range = SENSITIVITY_RANGE;
            ScreenStateAdjustment screenStateAdjustment = (ScreenStateAdjustment) ((ArrayList) this.mAdjustments).get(i);
            if (!screenStateAdjustment.mPowerManager.isInteractive()) {
                f += screenStateAdjustment.mScreenOffAdjustment;
            }
            f = ((Float) range.clamp(Float.valueOf(f))).floatValue();
        }
        return f;
    }
}
