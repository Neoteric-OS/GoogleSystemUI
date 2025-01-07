package com.android.systemui.screenshot.appclips;

import android.graphics.ParcelableColorSpace;
import android.hardware.HardwareBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import android.window.ScreenCapture;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotHardwareBufferInternal implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public final HardwareBuffer mHardwareBuffer;
    public final ParcelableColorSpace mParcelableColorSpace;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.appclips.ScreenshotHardwareBufferInternal$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new ScreenshotHardwareBufferInternal(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ScreenshotHardwareBufferInternal[i];
        }
    }

    public ScreenshotHardwareBufferInternal(ScreenCapture.ScreenshotHardwareBuffer screenshotHardwareBuffer) {
        this.mHardwareBuffer = screenshotHardwareBuffer.getHardwareBuffer();
        this.mParcelableColorSpace = new ParcelableColorSpace(screenshotHardwareBuffer.getColorSpace());
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ScreenshotHardwareBufferInternal)) {
            return false;
        }
        ScreenshotHardwareBufferInternal screenshotHardwareBufferInternal = (ScreenshotHardwareBufferInternal) obj;
        return this.mHardwareBuffer.equals(screenshotHardwareBufferInternal.mHardwareBuffer) && this.mParcelableColorSpace.equals(screenshotHardwareBufferInternal.mParcelableColorSpace);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mHardwareBuffer, i);
        parcel.writeParcelable(this.mParcelableColorSpace, i);
    }

    public ScreenshotHardwareBufferInternal(Parcel parcel) {
        this.mHardwareBuffer = (HardwareBuffer) parcel.readParcelable(HardwareBuffer.class.getClassLoader(), HardwareBuffer.class);
        this.mParcelableColorSpace = (ParcelableColorSpace) parcel.readParcelable(ParcelableColorSpace.class.getClassLoader(), ParcelableColorSpace.class);
    }
}
