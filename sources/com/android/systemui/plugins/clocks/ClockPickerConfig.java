package com.android.systemui.plugins.clocks;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockPickerConfig {
    private final List axes;
    private final String description;
    private final String id;
    private final boolean isReactiveToTone;
    private final boolean isReactiveToTouch;
    private final String name;
    private final Drawable thumbnail;

    public ClockPickerConfig(String str, String str2, String str3, Drawable drawable) {
        this(str, str2, str3, drawable, false, false, null, 112, null);
    }

    public static /* synthetic */ ClockPickerConfig copy$default(ClockPickerConfig clockPickerConfig, String str, String str2, String str3, Drawable drawable, boolean z, boolean z2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockPickerConfig.id;
        }
        if ((i & 2) != 0) {
            str2 = clockPickerConfig.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = clockPickerConfig.description;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            drawable = clockPickerConfig.thumbnail;
        }
        Drawable drawable2 = drawable;
        if ((i & 16) != 0) {
            z = clockPickerConfig.isReactiveToTone;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = clockPickerConfig.isReactiveToTouch;
        }
        boolean z4 = z2;
        if ((i & 64) != 0) {
            list = clockPickerConfig.axes;
        }
        return clockPickerConfig.copy(str, str4, str5, drawable2, z3, z4, list);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.description;
    }

    public final Drawable component4() {
        return this.thumbnail;
    }

    public final boolean component5() {
        return this.isReactiveToTone;
    }

    public final boolean component6() {
        return this.isReactiveToTouch;
    }

    public final List component7() {
        return this.axes;
    }

    public final ClockPickerConfig copy(String str, String str2, String str3, Drawable drawable, boolean z, boolean z2, List list) {
        return new ClockPickerConfig(str, str2, str3, drawable, z, z2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClockPickerConfig)) {
            return false;
        }
        ClockPickerConfig clockPickerConfig = (ClockPickerConfig) obj;
        return Intrinsics.areEqual(this.id, clockPickerConfig.id) && Intrinsics.areEqual(this.name, clockPickerConfig.name) && Intrinsics.areEqual(this.description, clockPickerConfig.description) && Intrinsics.areEqual(this.thumbnail, clockPickerConfig.thumbnail) && this.isReactiveToTone == clockPickerConfig.isReactiveToTone && this.isReactiveToTouch == clockPickerConfig.isReactiveToTouch && Intrinsics.areEqual(this.axes, clockPickerConfig.axes);
    }

    public final List getAxes() {
        return this.axes;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final Drawable getThumbnail() {
        return this.thumbnail;
    }

    public int hashCode() {
        return this.axes.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.thumbnail.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.description, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, this.id.hashCode() * 31, 31), 31)) * 31, 31, this.isReactiveToTone), 31, this.isReactiveToTouch);
    }

    public final boolean isReactiveToTone() {
        return this.isReactiveToTone;
    }

    public final boolean isReactiveToTouch() {
        return this.isReactiveToTouch;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        Drawable drawable = this.thumbnail;
        boolean z = this.isReactiveToTone;
        boolean z2 = this.isReactiveToTouch;
        List list = this.axes;
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("ClockPickerConfig(id=", str, ", name=", str2, ", description=");
        m.append(str3);
        m.append(", thumbnail=");
        m.append(drawable);
        m.append(", isReactiveToTone=");
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, z, ", isReactiveToTouch=", z2, ", axes=");
        m.append(list);
        m.append(")");
        return m.toString();
    }

    public ClockPickerConfig(String str, String str2, String str3, Drawable drawable, boolean z) {
        this(str, str2, str3, drawable, z, false, null, 96, null);
    }

    public ClockPickerConfig(String str, String str2, String str3, Drawable drawable, boolean z, boolean z2) {
        this(str, str2, str3, drawable, z, z2, null, 64, null);
    }

    public ClockPickerConfig(String str, String str2, String str3, Drawable drawable, boolean z, boolean z2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, drawable, (i & 16) != 0 ? true : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? EmptyList.INSTANCE : list);
    }

    public ClockPickerConfig(String str, String str2, String str3, Drawable drawable, boolean z, boolean z2, List list) {
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.thumbnail = drawable;
        this.isReactiveToTone = z;
        this.isReactiveToTouch = z2;
        this.axes = list;
    }
}
