package com.android.systemui.touch;

import android.graphics.Region;
import android.view.AttachedSurfaceControl;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TouchInsetManager$$ExternalSyntheticLambda1 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        ((AttachedSurfaceControl) entry.getKey()).setTouchableRegion((Region) entry.getValue());
    }
}
