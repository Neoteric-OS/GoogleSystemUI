package com.android.wm.shell.shared.bubbles;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarUpdate implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public BubbleInfo addedBubble;
    public BubbleBarLocation bubbleBarLocation;
    public final List bubbleKeysInOrder;
    public final List currentBubbleList;
    public boolean expanded;
    public boolean expandedChanged;
    public Point expandedViewDropTargetSize;
    public final boolean initialState;
    public final List removedBubbles;
    public String selectedBubbleKey;
    public boolean shouldShowEducation;
    public boolean showOverflow;
    public boolean showOverflowChanged;
    public String suppressedBubbleKey;
    public String unsupressedBubbleKey;
    public BubbleInfo updatedBubble;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.shared.bubbles.BubbleBarUpdate$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new BubbleBarUpdate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new BubbleBarUpdate[i];
        }
    }

    public BubbleBarUpdate(boolean z) {
        this.removedBubbles = new ArrayList();
        this.bubbleKeysInOrder = new ArrayList();
        this.currentBubbleList = new ArrayList();
        this.initialState = z;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BubbleBarUpdate{ initialState=");
        sb.append(this.initialState);
        sb.append(" expandedChanged=");
        sb.append(this.expandedChanged);
        sb.append(" expanded=");
        sb.append(this.expanded);
        sb.append(" selectedBubbleKey=");
        sb.append(this.selectedBubbleKey);
        sb.append(" shouldShowEducation=");
        sb.append(this.shouldShowEducation);
        sb.append(" addedBubble=");
        sb.append(this.addedBubble);
        sb.append(" updatedBubble=");
        sb.append(this.updatedBubble);
        sb.append(" suppressedBubbleKey=");
        sb.append(this.suppressedBubbleKey);
        sb.append(" unsuppressedBubbleKey=");
        sb.append(this.unsupressedBubbleKey);
        sb.append(" removedBubbles=");
        sb.append(this.removedBubbles);
        sb.append(" bubbles=");
        sb.append(this.bubbleKeysInOrder);
        sb.append(" currentBubbleList=");
        sb.append(this.currentBubbleList);
        sb.append(" bubbleBarLocation=");
        sb.append(this.bubbleBarLocation);
        sb.append(" expandedViewDropTargetSize=");
        sb.append(this.expandedViewDropTargetSize);
        sb.append(" showOverflowChanged=");
        sb.append(this.showOverflowChanged);
        sb.append(" showOverflow=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.showOverflow, " }");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.initialState);
        parcel.writeBoolean(this.expandedChanged);
        parcel.writeBoolean(this.expanded);
        parcel.writeBoolean(this.shouldShowEducation);
        parcel.writeString(this.selectedBubbleKey);
        parcel.writeParcelable(this.addedBubble, i);
        parcel.writeParcelable(this.updatedBubble, i);
        parcel.writeString(this.suppressedBubbleKey);
        parcel.writeString(this.unsupressedBubbleKey);
        parcel.writeParcelableList(this.removedBubbles, i);
        parcel.writeStringList(this.bubbleKeysInOrder);
        parcel.writeParcelableList(this.currentBubbleList, i);
        parcel.writeParcelable(this.bubbleBarLocation, i);
        parcel.writeParcelable(this.expandedViewDropTargetSize, i);
        parcel.writeBoolean(this.showOverflowChanged);
        parcel.writeBoolean(this.showOverflow);
    }

    public BubbleBarUpdate(Parcel parcel) {
        this.removedBubbles = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.bubbleKeysInOrder = arrayList;
        this.currentBubbleList = new ArrayList();
        this.initialState = parcel.readBoolean();
        this.expandedChanged = parcel.readBoolean();
        this.expanded = parcel.readBoolean();
        this.shouldShowEducation = parcel.readBoolean();
        this.selectedBubbleKey = parcel.readString();
        this.addedBubble = (BubbleInfo) parcel.readParcelable(BubbleInfo.class.getClassLoader(), BubbleInfo.class);
        this.updatedBubble = (BubbleInfo) parcel.readParcelable(BubbleInfo.class.getClassLoader(), BubbleInfo.class);
        this.suppressedBubbleKey = parcel.readString();
        this.unsupressedBubbleKey = parcel.readString();
        this.removedBubbles = parcel.readParcelableList(new ArrayList(), RemovedBubble.class.getClassLoader(), RemovedBubble.class);
        parcel.readStringList(arrayList);
        this.currentBubbleList = parcel.readParcelableList(new ArrayList(), BubbleInfo.class.getClassLoader(), BubbleInfo.class);
        this.bubbleBarLocation = (BubbleBarLocation) parcel.readParcelable(BubbleBarLocation.class.getClassLoader(), BubbleBarLocation.class);
        this.expandedViewDropTargetSize = (Point) parcel.readParcelable(Point.class.getClassLoader(), Point.class);
        this.showOverflowChanged = parcel.readBoolean();
        this.showOverflow = parcel.readBoolean();
    }
}
