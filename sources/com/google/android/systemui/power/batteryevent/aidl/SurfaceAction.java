package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SurfaceAction implements Parcelable {
    public static final /* synthetic */ SurfaceAction[] $VALUES;
    public static final CREATOR CREATOR;
    public static final SurfaceAction UNKNOWN;
    private final String actionName;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            SurfaceAction surfaceAction = null;
            String readString = parcel != null ? parcel.readString() : null;
            if (readString == null) {
                Log.w("SurfaceAction", "null parameter for createFromParcel");
                return SurfaceAction.UNKNOWN;
            }
            SurfaceAction[] values = SurfaceAction.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SurfaceAction surfaceAction2 = values[i];
                if (Intrinsics.areEqual(surfaceAction2.getActionName(), readString)) {
                    surfaceAction = surfaceAction2;
                    break;
                }
                i++;
            }
            return surfaceAction == null ? SurfaceAction.UNKNOWN : surfaceAction;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new SurfaceAction[i];
        }
    }

    static {
        SurfaceAction surfaceAction = new SurfaceAction("UNKNOWN", "unknown", 0);
        UNKNOWN = surfaceAction;
        SurfaceAction[] surfaceActionArr = {surfaceAction, new SurfaceAction("POSITIVE", "positive", 1), new SurfaceAction("NEGATIVE", "negative", 2), new SurfaceAction("NEUTRAL", "neutral", 3)};
        $VALUES = surfaceActionArr;
        EnumEntriesKt.enumEntries(surfaceActionArr);
        CREATOR = new CREATOR();
    }

    public SurfaceAction(String str, String str2, int i) {
        this.actionName = str2;
    }

    public static SurfaceAction valueOf(String str) {
        return (SurfaceAction) Enum.valueOf(SurfaceAction.class, str);
    }

    public static SurfaceAction[] values() {
        return (SurfaceAction[]) $VALUES.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String getActionName() {
        return this.actionName;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionName);
    }
}
