package com.android.wm.shell.shared;

import android.app.ActivityManager;
import android.app.WindowConfiguration;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.wm.shell.shared.split.SplitBounds;
import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupedRecentTaskInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public final int[] mMinimizedTaskIds;
    public final SplitBounds mSplitBounds;
    public final ActivityManager.RecentTaskInfo[] mTasks;
    public final int mType;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.shared.GroupedRecentTaskInfo$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new GroupedRecentTaskInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GroupedRecentTaskInfo[i];
        }
    }

    public GroupedRecentTaskInfo(final ActivityManager.RecentTaskInfo[] recentTaskInfoArr, SplitBounds splitBounds, int i, int[] iArr) {
        this.mTasks = recentTaskInfoArr;
        this.mSplitBounds = splitBounds;
        this.mType = i;
        this.mMinimizedTaskIds = iArr;
        if (iArr != null && !Arrays.stream(iArr).allMatch(new IntPredicate() { // from class: com.android.wm.shell.shared.GroupedRecentTaskInfo$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(final int i2) {
                return Arrays.stream(recentTaskInfoArr).anyMatch(new Predicate() { // from class: com.android.wm.shell.shared.GroupedRecentTaskInfo$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((ActivityManager.RecentTaskInfo) obj).taskId == i2;
                    }
                });
            }
        })) {
            throw new IllegalArgumentException("Minimized task IDs contain non-existent Task ID.");
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final ActivityManager.RecentTaskInfo getTaskInfo2() {
        ActivityManager.RecentTaskInfo[] recentTaskInfoArr = this.mTasks;
        if (recentTaskInfoArr.length > 1) {
            return recentTaskInfoArr[1];
        }
        return null;
    }

    public final String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (i < this.mTasks.length) {
            if (i == 0) {
                sb2.append("Task");
            } else {
                sb2.append(", Task");
            }
            int i2 = i + 1;
            sb2.append(i2);
            sb2.append(": ");
            ActivityManager.RecentTaskInfo recentTaskInfo = this.mTasks[i];
            if (recentTaskInfo == null) {
                sb = null;
            } else {
                StringBuilder sb3 = new StringBuilder("id=");
                sb3.append(recentTaskInfo.taskId);
                sb3.append(" baseIntent=");
                Intent intent = recentTaskInfo.baseIntent;
                sb3.append(intent != null ? intent.getComponent() : "null");
                sb3.append(" winMode=");
                sb3.append(WindowConfiguration.windowingModeToString(recentTaskInfo.getWindowingMode()));
                sb = sb3.toString();
            }
            sb2.append(sb);
            i = i2;
        }
        if (this.mSplitBounds != null) {
            sb2.append(", SplitBounds: ");
            sb2.append(this.mSplitBounds);
        }
        sb2.append(", Type=");
        int i3 = this.mType;
        if (i3 == 1) {
            sb2.append("TYPE_SINGLE");
        } else if (i3 == 2) {
            sb2.append("TYPE_SPLIT");
        } else if (i3 == 3) {
            sb2.append("TYPE_FREEFORM");
        }
        sb2.append(", Minimized Task IDs: ");
        sb2.append(Arrays.toString(this.mMinimizedTaskIds));
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mTasks, i);
        parcel.writeTypedObject(this.mSplitBounds, i);
        parcel.writeInt(this.mType);
        parcel.writeIntArray(this.mMinimizedTaskIds);
    }

    public GroupedRecentTaskInfo(Parcel parcel) {
        this.mTasks = (ActivityManager.RecentTaskInfo[]) parcel.createTypedArray(ActivityManager.RecentTaskInfo.CREATOR);
        this.mSplitBounds = (SplitBounds) parcel.readTypedObject(SplitBounds.CREATOR);
        this.mType = parcel.readInt();
        this.mMinimizedTaskIds = parcel.createIntArray();
    }
}
