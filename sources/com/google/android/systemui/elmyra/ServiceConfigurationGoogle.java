package com.google.android.systemui.elmyra;

import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ServiceConfigurationGoogle {
    public final List mActions;
    public final List mFeedbackEffects;
    public final List mGates;
    public final GestureSensor mGestureSensor;

    public ServiceConfigurationGoogle(Set set, Set set2, Set set3, GestureSensor gestureSensor) {
        this.mActions = new ArrayList(set2);
        this.mFeedbackEffects = new ArrayList(set3);
        this.mGates = new ArrayList(set);
        this.mGestureSensor = gestureSensor;
    }
}
