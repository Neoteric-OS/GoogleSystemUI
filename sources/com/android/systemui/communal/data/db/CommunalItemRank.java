package com.android.systemui.communal.data.db;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalItemRank {
    public final int rank;
    public final long uid;

    public CommunalItemRank(long j, int i) {
        this.uid = j;
        this.rank = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommunalItemRank)) {
            return false;
        }
        CommunalItemRank communalItemRank = (CommunalItemRank) obj;
        return this.uid == communalItemRank.uid && this.rank == communalItemRank.rank;
    }

    public final int hashCode() {
        return Integer.hashCode(this.rank) + (Long.hashCode(this.uid) * 31);
    }

    public final String toString() {
        return "CommunalItemRank(uid=" + this.uid + ", rank=" + this.rank + ")";
    }
}
