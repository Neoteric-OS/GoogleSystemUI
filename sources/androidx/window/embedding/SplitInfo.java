package androidx.window.embedding;

import android.os.IBinder;
import androidx.window.extensions.embedding.SplitInfo;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitInfo {
    public final IBinder binder;
    public final ActivityStack primaryActivityStack;
    public final ActivityStack secondaryActivityStack;
    public final SplitAttributes splitAttributes;
    public final SplitInfo.Token token;

    public SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes, IBinder iBinder, SplitInfo.Token token) {
        this.primaryActivityStack = activityStack;
        this.secondaryActivityStack = activityStack2;
        this.splitAttributes = splitAttributes;
        this.binder = iBinder;
        this.token = token;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitInfo)) {
            return false;
        }
        SplitInfo splitInfo = (SplitInfo) obj;
        return Intrinsics.areEqual(this.primaryActivityStack, splitInfo.primaryActivityStack) && Intrinsics.areEqual(this.secondaryActivityStack, splitInfo.secondaryActivityStack) && Intrinsics.areEqual(this.splitAttributes, splitInfo.splitAttributes) && Intrinsics.areEqual(this.token, splitInfo.token) && Intrinsics.areEqual(this.binder, splitInfo.binder);
    }

    public final int hashCode() {
        int hashCode = (this.splitAttributes.hashCode() + ((this.secondaryActivityStack.hashCode() + (this.primaryActivityStack.hashCode() * 31)) * 31)) * 31;
        SplitInfo.Token token = this.token;
        int hashCode2 = (hashCode + (token != null ? token.hashCode() : 0)) * 31;
        IBinder iBinder = this.binder;
        return hashCode2 + (iBinder != null ? iBinder.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SplitInfo:{");
        sb.append("primaryActivityStack=" + this.primaryActivityStack + ", ");
        sb.append("secondaryActivityStack=" + this.secondaryActivityStack + ", ");
        sb.append("splitAttributes=" + this.splitAttributes + ", ");
        if (this.token != null) {
            sb.append("token=" + this.token);
        }
        if (this.binder != null) {
            sb.append("binder=" + this.binder);
        }
        sb.append("}");
        return sb.toString();
    }

    public SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes) {
        this(activityStack, activityStack2, splitAttributes, null, null);
    }
}
