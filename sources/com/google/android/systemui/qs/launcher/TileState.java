package com.google.android.systemui.qs.launcher;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public CharSequence mContentDescription;
    public Icon mIcon;
    public boolean mIsTransient;
    public CharSequence mLabel;
    public boolean mShowChevron;
    public int mState = 1;
    public CharSequence mStateDescription;
    public CharSequence mSubtitle;
    public boolean mSupportClick;
    public boolean mSupportLongClick;
    public CharSequence mTileSpec;
    public boolean mUnSupported;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.qs.launcher.TileState$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            TileState tileState = new TileState();
            tileState.mState = 1;
            tileState.mIcon = (Icon) parcel.readTypedObject(Icon.CREATOR);
            tileState.mState = parcel.readInt();
            tileState.mIsTransient = parcel.readBoolean();
            tileState.mSupportClick = parcel.readBoolean();
            tileState.mSupportLongClick = parcel.readBoolean();
            tileState.mShowChevron = parcel.readBoolean();
            tileState.mUnSupported = parcel.readBoolean();
            Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
            tileState.mLabel = (CharSequence) creator.createFromParcel(parcel);
            tileState.mSubtitle = (CharSequence) creator.createFromParcel(parcel);
            tileState.mContentDescription = (CharSequence) creator.createFromParcel(parcel);
            tileState.mStateDescription = (CharSequence) creator.createFromParcel(parcel);
            tileState.mTileSpec = (CharSequence) creator.createFromParcel(parcel);
            return tileState;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new TileState[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mIcon, i);
        parcel.writeInt(this.mState);
        parcel.writeBoolean(this.mIsTransient);
        parcel.writeBoolean(this.mSupportClick);
        parcel.writeBoolean(this.mSupportLongClick);
        parcel.writeBoolean(this.mShowChevron);
        parcel.writeBoolean(this.mUnSupported);
        TextUtils.writeToParcel(this.mLabel, parcel, i);
        TextUtils.writeToParcel(this.mSubtitle, parcel, i);
        TextUtils.writeToParcel(this.mContentDescription, parcel, i);
        TextUtils.writeToParcel(this.mStateDescription, parcel, i);
        TextUtils.writeToParcel(this.mTileSpec, parcel, i);
    }
}
