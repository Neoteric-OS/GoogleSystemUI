package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BooleanFlag implements ParcelableFlag {
    public static final Parcelable.Creator CREATOR = new BooleanFlag$Companion$CREATOR$1();

    /* renamed from: default, reason: not valid java name */
    public final boolean f26default;
    public final String name;
    public final String namespace;
    public final boolean overridden;
    public final boolean teamfood;

    public BooleanFlag(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.name = str;
        this.namespace = str2;
        this.f26default = z;
        this.teamfood = z2;
        this.overridden = z3;
    }

    @Override // com.android.systemui.flags.Flag
    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public boolean getOverridden() {
        return this.overridden;
    }

    public boolean getTeamfood() {
        return this.teamfood;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(0);
        parcel.writeString(getName());
        parcel.writeString(getNamespace());
        parcel.writeBoolean(this.f26default);
        parcel.writeBoolean(getTeamfood());
        parcel.writeBoolean(getOverridden());
    }
}
