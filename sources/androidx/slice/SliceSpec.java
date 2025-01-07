package androidx.slice;

import androidx.versionedparcelable.VersionedParcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceSpec implements VersionedParcelable {
    public int mRevision;
    public String mType;

    public SliceSpec() {
        this.mRevision = 1;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SliceSpec)) {
            return false;
        }
        SliceSpec sliceSpec = (SliceSpec) obj;
        return this.mType.equals(sliceSpec.mType) && this.mRevision == sliceSpec.mRevision;
    }

    public final int hashCode() {
        return this.mType.hashCode() + this.mRevision;
    }

    public final String toString() {
        return String.format("SliceSpec{%s,%d}", this.mType, Integer.valueOf(this.mRevision));
    }

    public SliceSpec(String str, int i) {
        this.mType = str;
        this.mRevision = i;
    }
}
