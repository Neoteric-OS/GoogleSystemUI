package com.android.settingslib.media;

import android.media.RouteListingPreference;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class InfoMediaManager$Api34Impl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ Map f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        RouteListingPreference.Item item = (RouteListingPreference.Item) obj;
        this.f$0.put(item.getRouteId(), item);
    }
}
