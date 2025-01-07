package com.android.wm.shell.shared.bubbles;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemovedBubble implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public final String mKey;
    public final int mRemovalReason;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.shared.bubbles.RemovedBubble$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new RemovedBubble(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new RemovedBubble[i];
        }
    }

    public RemovedBubble(String str, int i) {
        this.mKey = str;
        this.mRemovalReason = i;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mKey);
        parcel.writeInt(this.mRemovalReason);
    }

    public RemovedBubble(Parcel parcel) {
        this.mKey = parcel.readString();
        this.mRemovalReason = parcel.readInt();
    }
}
