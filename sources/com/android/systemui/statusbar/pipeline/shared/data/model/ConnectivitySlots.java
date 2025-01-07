package com.android.systemui.statusbar.pipeline.shared.data.model;

import android.R;
import android.content.Context;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectivitySlots {
    public final Map slotByName;

    public ConnectivitySlots(Context context) {
        this.slotByName = MapsKt.mapOf(new Pair(context.getString(R.string.status_bar_vpn), ConnectivitySlot.AIRPLANE), new Pair(context.getString(R.string.suspended_widget_accessibility), ConnectivitySlot.MOBILE), new Pair(context.getString(R.string.time_picker_decrement_minute_button), ConnectivitySlot.WIFI), new Pair(context.getString(R.string.storage_sd_card), ConnectivitySlot.ETHERNET));
    }
}
