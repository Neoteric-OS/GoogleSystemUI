package com.android.systemui.plugins.clocks;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockSettings {
    private final List axes;
    private final String clockId;
    private JSONObject metadata;
    private final Integer seedColor;
    public static final Companion Companion = new Companion(null);
    private static final String KEY_CLOCK_ID = "clockId";
    private static final String KEY_SEED_COLOR = "seedColor";
    private static final String KEY_METADATA = "metadata";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClockSettings deserialize(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            ClockSettings clockSettings = new ClockSettings(!jSONObject.isNull(ClockSettings.KEY_CLOCK_ID) ? jSONObject.getString(ClockSettings.KEY_CLOCK_ID) : null, jSONObject.isNull(ClockSettings.KEY_SEED_COLOR) ? null : Integer.valueOf(jSONObject.getInt(ClockSettings.KEY_SEED_COLOR)), null, 4, null);
            if (!jSONObject.isNull(ClockSettings.KEY_METADATA)) {
                clockSettings.setMetadata(jSONObject.getJSONObject(ClockSettings.KEY_METADATA));
            }
            return clockSettings;
        }

        public final String serialize(ClockSettings clockSettings) {
            return clockSettings == null ? "" : new JSONObject().put(ClockSettings.KEY_CLOCK_ID, clockSettings.getClockId()).put(ClockSettings.KEY_SEED_COLOR, clockSettings.getSeedColor()).put(ClockSettings.KEY_METADATA, clockSettings.getMetadata()).toString();
        }

        private Companion() {
        }
    }

    public ClockSettings() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ ClockSettings copy$default(ClockSettings clockSettings, String str, Integer num, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockSettings.clockId;
        }
        if ((i & 2) != 0) {
            num = clockSettings.seedColor;
        }
        if ((i & 4) != 0) {
            list = clockSettings.axes;
        }
        return clockSettings.copy(str, num, list);
    }

    public final String component1() {
        return this.clockId;
    }

    public final Integer component2() {
        return this.seedColor;
    }

    public final List component3() {
        return this.axes;
    }

    public final ClockSettings copy(String str, Integer num, List list) {
        return new ClockSettings(str, num, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClockSettings)) {
            return false;
        }
        ClockSettings clockSettings = (ClockSettings) obj;
        return Intrinsics.areEqual(this.clockId, clockSettings.clockId) && Intrinsics.areEqual(this.seedColor, clockSettings.seedColor) && Intrinsics.areEqual(this.axes, clockSettings.axes);
    }

    public final List getAxes() {
        return this.axes;
    }

    public final String getClockId() {
        return this.clockId;
    }

    public final JSONObject getMetadata() {
        return this.metadata;
    }

    public final Integer getSeedColor() {
        return this.seedColor;
    }

    public int hashCode() {
        String str = this.clockId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.seedColor;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        List list = this.axes;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    public final void setMetadata(JSONObject jSONObject) {
        this.metadata = jSONObject;
    }

    public String toString() {
        return "ClockSettings(clockId=" + this.clockId + ", seedColor=" + this.seedColor + ", axes=" + this.axes + ")";
    }

    public ClockSettings(String str, Integer num, List list) {
        this.clockId = str;
        this.seedColor = num;
        this.axes = list;
        this.metadata = new JSONObject();
    }

    public /* synthetic */ ClockSettings(String str, Integer num, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : list);
    }
}
