package com.android.systemui.shared.system.smartspace;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceState implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    public Rect boundsOnScreen;
    public int selectedPage;
    public boolean visibleOnScreen;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            SmartspaceState smartspaceState = new SmartspaceState();
            smartspaceState.boundsOnScreen = new Rect();
            Rect rect = (Rect) parcel.readParcelable(new PropertyReference1Impl() { // from class: com.android.systemui.shared.system.smartspace.SmartspaceState.1
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public final Object get(Object obj) {
                    return obj.getClass();
                }
            }.getClass().getClassLoader());
            if (rect == null) {
                rect = new Rect();
            }
            smartspaceState.boundsOnScreen = rect;
            smartspaceState.selectedPage = parcel.readInt();
            smartspaceState.visibleOnScreen = parcel.readBoolean();
            return smartspaceState;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new SmartspaceState[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "boundsOnScreen: " + this.boundsOnScreen + ", selectedPage: " + this.selectedPage + ", visibleOnScreen: " + this.visibleOnScreen;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.boundsOnScreen, 0);
        parcel.writeInt(this.selectedPage);
        parcel.writeBoolean(this.visibleOnScreen);
    }
}
