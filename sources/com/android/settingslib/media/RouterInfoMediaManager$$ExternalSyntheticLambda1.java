package com.android.settingslib.media;

import android.media.MediaRouter2;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class RouterInfoMediaManager$$ExternalSyntheticLambda1 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((MediaRouter2.RoutingController) obj).getRoutingSessionInfo();
    }
}
