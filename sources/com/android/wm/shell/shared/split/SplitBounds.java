package com.android.wm.shell.shared.split;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitBounds implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public final boolean appsStackedVertically;
    public final float dividerHeightPercent;
    public final float dividerWidthPercent;
    public final float leftTaskPercent;
    public final Rect leftTopBounds;
    public final int leftTopTaskId;
    public final Rect rightBottomBounds;
    public final int rightBottomTaskId;
    public final int snapPosition;
    public final float topTaskPercent;
    public final Rect visualDividerBounds;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.shared.split.SplitBounds$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new SplitBounds(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new SplitBounds[i];
        }
    }

    public SplitBounds(int i, int i2, int i3, Rect rect, Rect rect2) {
        this.leftTopBounds = rect;
        this.rightBottomBounds = rect2;
        this.leftTopTaskId = i;
        this.rightBottomTaskId = i2;
        this.snapPosition = i3;
        if (rect2.top > rect.top) {
            this.visualDividerBounds = new Rect(rect.left, rect.bottom, rect.right, rect2.top);
            this.appsStackedVertically = true;
        } else {
            this.visualDividerBounds = new Rect(rect.right, rect.top, rect2.left, rect.bottom);
            this.appsStackedVertically = false;
        }
        float f = rect2.right - rect.left;
        float f2 = rect2.bottom - rect.top;
        this.leftTaskPercent = rect.width() / f;
        this.topTaskPercent = rect.height() / f2;
        this.dividerWidthPercent = this.visualDividerBounds.width() / f;
        this.dividerHeightPercent = this.visualDividerBounds.height() / f2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SplitBounds)) {
            return false;
        }
        SplitBounds splitBounds = (SplitBounds) obj;
        return Objects.equals(this.leftTopBounds, splitBounds.leftTopBounds) && Objects.equals(this.rightBottomBounds, splitBounds.rightBottomBounds) && this.leftTopTaskId == splitBounds.leftTopTaskId && this.rightBottomTaskId == splitBounds.rightBottomTaskId;
    }

    public final int hashCode() {
        return Objects.hash(this.leftTopBounds, this.rightBottomBounds, Integer.valueOf(this.leftTopTaskId), Integer.valueOf(this.rightBottomTaskId));
    }

    public final String toString() {
        return "LeftTop: " + this.leftTopBounds + ", taskId: " + this.leftTopTaskId + "\nRightBottom: " + this.rightBottomBounds + ", taskId: " + this.rightBottomTaskId + "\nDivider: " + this.visualDividerBounds + "\nAppsVertical? " + this.appsStackedVertically + "\nsnapPosition: " + this.snapPosition;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.leftTopBounds, i);
        parcel.writeTypedObject(this.rightBottomBounds, i);
        parcel.writeTypedObject(this.visualDividerBounds, i);
        parcel.writeFloat(this.topTaskPercent);
        parcel.writeFloat(this.leftTaskPercent);
        parcel.writeBoolean(this.appsStackedVertically);
        parcel.writeInt(this.leftTopTaskId);
        parcel.writeInt(this.rightBottomTaskId);
        parcel.writeFloat(this.dividerWidthPercent);
        parcel.writeFloat(this.dividerHeightPercent);
        parcel.writeInt(this.snapPosition);
    }

    public SplitBounds(Parcel parcel) {
        Parcelable.Creator creator = Rect.CREATOR;
        this.leftTopBounds = (Rect) parcel.readTypedObject(creator);
        this.rightBottomBounds = (Rect) parcel.readTypedObject(creator);
        this.visualDividerBounds = (Rect) parcel.readTypedObject(creator);
        this.topTaskPercent = parcel.readFloat();
        this.leftTaskPercent = parcel.readFloat();
        this.appsStackedVertically = parcel.readBoolean();
        this.leftTopTaskId = parcel.readInt();
        this.rightBottomTaskId = parcel.readInt();
        this.dividerWidthPercent = parcel.readFloat();
        this.dividerHeightPercent = parcel.readFloat();
        this.snapPosition = parcel.readInt();
    }
}
