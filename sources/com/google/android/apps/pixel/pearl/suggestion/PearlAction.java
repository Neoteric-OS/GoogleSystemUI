package com.google.android.apps.pixel.pearl.suggestion;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PearlAction implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    public final PendingIntent action;
    public final String description;
    public final Bitmap icon;
    public final String label;
    public final boolean tint;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            Object readParcelable = parcel.readParcelable(Bitmap.class.getClassLoader(), Bitmap.class);
            if (readParcelable == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Bitmap bitmap = (Bitmap) readParcelable;
            String readString = parcel.readString();
            String str = readString == null ? "" : readString;
            String readString2 = parcel.readString();
            String str2 = readString2 != null ? readString2 : "";
            Object readParcelable2 = parcel.readParcelable(PendingIntent.class.getClassLoader(), PendingIntent.class);
            if (readParcelable2 != null) {
                return new PearlAction(bitmap, str, str2, (PendingIntent) readParcelable2, parcel.dataAvail() <= 0 || parcel.readInt() != -1);
            }
            throw new IllegalArgumentException("Required value was null.");
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new PearlAction[i];
        }
    }

    public PearlAction(Bitmap bitmap, String str, String str2, PendingIntent pendingIntent, boolean z) {
        this.icon = bitmap;
        this.label = str;
        this.description = str2;
        this.action = pendingIntent;
        this.tint = z;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PearlAction)) {
            return false;
        }
        PearlAction pearlAction = (PearlAction) obj;
        return Intrinsics.areEqual(this.icon, pearlAction.icon) && Intrinsics.areEqual(this.label, pearlAction.label) && Intrinsics.areEqual(this.description, pearlAction.description) && Intrinsics.areEqual(this.action, pearlAction.action) && this.tint == pearlAction.tint;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.tint) + ((this.action.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.description, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.label, this.icon.hashCode() * 31, 31), 31)) * 31);
    }

    public final String toString() {
        Bitmap bitmap = this.icon;
        String str = this.label;
        String str2 = this.description;
        PendingIntent pendingIntent = this.action;
        boolean z = this.tint;
        StringBuilder sb = new StringBuilder("PearlAction(icon=");
        sb.append(bitmap);
        sb.append(", label=");
        sb.append(str);
        sb.append(", description=");
        sb.append(str2);
        sb.append(", action=");
        sb.append(pendingIntent);
        sb.append(", tint=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, z, ")");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.icon, i);
        parcel.writeString(this.label);
        parcel.writeString(this.description);
        parcel.writeParcelable(this.action, i);
        parcel.writeInt(this.tint ? 1 : -1);
    }
}
