package com.android.wm.shell.shared.bubbles;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public String mAppName;
    public int mFlags;
    public Icon mIcon;
    public boolean mIsImportantConversation;
    public String mKey;
    public String mPackageName;
    public String mShortcutId;
    public boolean mShowAppBadge;
    public String mTitle;
    public int mUserId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.shared.bubbles.BubbleInfo$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            BubbleInfo bubbleInfo = new BubbleInfo();
            bubbleInfo.mKey = parcel.readString();
            bubbleInfo.mFlags = parcel.readInt();
            bubbleInfo.mShortcutId = parcel.readString();
            bubbleInfo.mIcon = (Icon) parcel.readTypedObject(Icon.CREATOR);
            bubbleInfo.mUserId = parcel.readInt();
            bubbleInfo.mPackageName = parcel.readString();
            bubbleInfo.mTitle = parcel.readString();
            bubbleInfo.mAppName = parcel.readString();
            bubbleInfo.mIsImportantConversation = parcel.readBoolean();
            bubbleInfo.mShowAppBadge = parcel.readBoolean();
            return bubbleInfo;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new BubbleInfo[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BubbleInfo) {
            return Objects.equals(this.mKey, ((BubbleInfo) obj).mKey);
        }
        return false;
    }

    public final int hashCode() {
        return this.mKey.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mKey);
        parcel.writeInt(this.mFlags);
        parcel.writeString(this.mShortcutId);
        parcel.writeTypedObject(this.mIcon, i);
        parcel.writeInt(this.mUserId);
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mAppName);
        parcel.writeBoolean(this.mIsImportantConversation);
        parcel.writeBoolean(this.mShowAppBadge);
    }
}
