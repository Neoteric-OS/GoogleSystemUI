package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StringFlag implements ParcelableFlag {
    public static final Parcelable.Creator CREATOR = new StringFlag$Companion$CREATOR$1();

    /* renamed from: default, reason: not valid java name */
    public final String f27default;
    public final String name;
    public final String namespace;

    public StringFlag(Parcel parcel) {
        parcel.readInt();
        String readString = parcel.readString();
        readString = readString == null ? "" : readString;
        String readString2 = parcel.readString();
        readString2 = readString2 == null ? "" : readString2;
        String readString3 = parcel.readString();
        String str = readString3 != null ? readString3 : "";
        this.name = readString;
        this.namespace = readString2;
        this.f27default = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringFlag)) {
            return false;
        }
        StringFlag stringFlag = (StringFlag) obj;
        return Intrinsics.areEqual(this.name, stringFlag.name) && Intrinsics.areEqual(this.namespace, stringFlag.namespace) && Intrinsics.areEqual(this.f27default, stringFlag.f27default);
    }

    @Override // com.android.systemui.flags.Flag
    public final String getName() {
        throw null;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.f27default, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.namespace, this.name.hashCode() * 31, 31), 31), 31, false);
    }

    public final String toString() {
        String str = this.name;
        String str2 = this.namespace;
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("StringFlag(name=", str, ", namespace=", str2, ", default="), this.f27default, ", teamfood=false, overridden=false)");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(0);
        parcel.writeString(this.name);
        parcel.writeString(this.namespace);
        parcel.writeString(this.f27default);
    }
}
