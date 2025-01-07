package com.android.systemui.shared.clocks;

import com.android.systemui.plugins.clocks.ClockMetadata;
import java.util.Collections;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ClockRegistryKt {
    public static final Map KNOWN_PLUGINS = MapsKt.mapOf(new Pair("com.android.systemui.clocks.bignum", Collections.singletonList(new ClockMetadata("ANALOG_CLOCK_BIGNUM"))), new Pair("com.android.systemui.clocks.calligraphy", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_CALLIGRAPHY"))), new Pair("com.android.systemui.clocks.flex", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_FLEX"))), new Pair("com.android.systemui.clocks.growth", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_GROWTH"))), new Pair("com.android.systemui.clocks.handwritten", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_HANDWRITTEN"))), new Pair("com.android.systemui.clocks.inflate", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_INFLATE"))), new Pair("com.android.systemui.clocks.metro", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_METRO"))), new Pair("com.android.systemui.clocks.numoverlap", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_NUMBEROVERLAP"))), new Pair("com.android.systemui.clocks.weather", Collections.singletonList(new ClockMetadata("DIGITAL_CLOCK_WEATHER"))));
}
