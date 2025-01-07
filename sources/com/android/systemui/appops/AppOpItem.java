package com.android.systemui.appops;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppOpItem {
    public final int mCode;
    public boolean mIsDisabled;
    public final String mPackageName;
    public final StringBuilder mState;
    public final long mTimeStartedElapsed;
    public final int mUid;

    public AppOpItem(int i, int i2, long j, String str) {
        this.mCode = i;
        this.mUid = i2;
        this.mPackageName = str;
        this.mTimeStartedElapsed = j;
        StringBuilder sb = new StringBuilder();
        sb.append("AppOpItem(Op code=");
        sb.append(i);
        sb.append(", UID=");
        sb.append(i2);
        sb.append(", Package name=");
        sb.append(str);
        sb.append(", Paused=");
        this.mState = sb;
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(this.mState, this.mIsDisabled, ")");
    }
}
