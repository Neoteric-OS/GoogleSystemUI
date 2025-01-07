package com.android.systemui.mediaprojection;

import android.app.ActivityOptions;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionCaptureTarget implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    public final ActivityOptions.LaunchCookie launchCookie;
    public final int taskId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new MediaProjectionCaptureTarget(ActivityOptions.LaunchCookie.readFromParcel(parcel), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new MediaProjectionCaptureTarget[i];
        }
    }

    public MediaProjectionCaptureTarget(ActivityOptions.LaunchCookie launchCookie, int i) {
        this.launchCookie = launchCookie;
        this.taskId = i;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaProjectionCaptureTarget)) {
            return false;
        }
        MediaProjectionCaptureTarget mediaProjectionCaptureTarget = (MediaProjectionCaptureTarget) obj;
        return Intrinsics.areEqual(this.launchCookie, mediaProjectionCaptureTarget.launchCookie) && this.taskId == mediaProjectionCaptureTarget.taskId;
    }

    public final int hashCode() {
        ActivityOptions.LaunchCookie launchCookie = this.launchCookie;
        return Integer.hashCode(this.taskId) + ((launchCookie == null ? 0 : launchCookie.hashCode()) * 31);
    }

    public final String toString() {
        return "MediaProjectionCaptureTarget(launchCookie=" + this.launchCookie + ", taskId=" + this.taskId + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ActivityOptions.LaunchCookie.writeToParcel(this.launchCookie, parcel);
        parcel.writeInt(this.taskId);
    }
}
