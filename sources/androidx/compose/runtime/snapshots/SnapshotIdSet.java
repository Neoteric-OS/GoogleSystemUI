package androidx.compose.runtime.snapshots;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotIdSet implements Iterable, KMappedMarker {
    public static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    public final int[] belowBound;
    public final int lowerBound;
    public final long lowerSet;
    public final long upperSet;

    public SnapshotIdSet(long j, long j2, int i, int[] iArr) {
        this.upperSet = j;
        this.lowerSet = j2;
        this.lowerBound = i;
        this.belowBound = iArr;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet snapshotIdSet) {
        SnapshotIdSet snapshotIdSet2 = EMPTY;
        if (snapshotIdSet == snapshotIdSet2) {
            return this;
        }
        if (this == snapshotIdSet2) {
            return snapshotIdSet2;
        }
        int i = snapshotIdSet.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = snapshotIdSet.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet & (~snapshotIdSet.upperSet), this.lowerSet & (~snapshotIdSet.lowerSet), i2, iArr2);
            }
        }
        int[] iArr3 = snapshotIdSet.belowBound;
        if (iArr3 != null) {
            for (int i3 : iArr3) {
                this = this.clear(i3);
            }
        }
        if (snapshotIdSet.lowerSet != 0) {
            for (int i4 = 0; i4 < 64; i4++) {
                if ((snapshotIdSet.lowerSet & (1 << i4)) != 0) {
                    this = this.clear(snapshotIdSet.lowerBound + i4);
                }
            }
        }
        if (snapshotIdSet.upperSet != 0) {
            for (int i5 = 0; i5 < 64; i5++) {
                if ((snapshotIdSet.upperSet & (1 << i5)) != 0) {
                    this = this.clear(i5 + 64 + snapshotIdSet.lowerBound);
                }
            }
        }
        return this;
    }

    public final SnapshotIdSet clear(int i) {
        int[] iArr;
        int binarySearch;
        int i2 = this.lowerBound;
        int i3 = i - i2;
        if (i3 >= 0 && i3 < 64) {
            long j = 1 << i3;
            long j2 = this.lowerSet;
            if ((j2 & j) != 0) {
                return new SnapshotIdSet(this.upperSet, j2 & (~j), i2, this.belowBound);
            }
        } else if (i3 >= 64 && i3 < 128) {
            long j3 = 1 << (i3 - 64);
            long j4 = this.upperSet;
            if ((j4 & j3) != 0) {
                return new SnapshotIdSet((~j3) & j4, this.lowerSet, i2, this.belowBound);
            }
        } else if (i3 < 0 && (iArr = this.belowBound) != null && (binarySearch = SnapshotIdSetKt.binarySearch(iArr, i)) >= 0) {
            int length = iArr.length;
            int i4 = length - 1;
            if (i4 == 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, null);
            }
            int[] iArr2 = new int[i4];
            if (binarySearch > 0) {
                ArraysKt.copyInto(0, 0, binarySearch, iArr, iArr2);
            }
            if (binarySearch < i4) {
                ArraysKt.copyInto(binarySearch, binarySearch + 1, length, iArr, iArr2);
            }
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
        }
        return this;
    }

    public final boolean get(int i) {
        int[] iArr;
        int i2 = i - this.lowerBound;
        if (i2 >= 0 && i2 < 64) {
            return (this.lowerSet & (1 << i2)) != 0;
        }
        if (i2 >= 64 && i2 < 128) {
            return (this.upperSet & (1 << (i2 - 64))) != 0;
        }
        if (i2 <= 0 && (iArr = this.belowBound) != null) {
            return SnapshotIdSetKt.binarySearch(iArr, i) >= 0;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return SequencesKt__SequenceBuilderKt.iterator(new SnapshotIdSet$iterator$1(this, null));
    }

    public final SnapshotIdSet or(SnapshotIdSet snapshotIdSet) {
        SnapshotIdSet snapshotIdSet2 = EMPTY;
        if (snapshotIdSet == snapshotIdSet2) {
            return this;
        }
        if (this == snapshotIdSet2) {
            return snapshotIdSet;
        }
        int i = snapshotIdSet.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = snapshotIdSet.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet | snapshotIdSet.upperSet, this.lowerSet | snapshotIdSet.lowerSet, i2, iArr2);
            }
        }
        int[] iArr3 = this.belowBound;
        int i3 = 0;
        if (iArr3 == null) {
            if (iArr3 != null) {
                for (int i4 : iArr3) {
                    snapshotIdSet = snapshotIdSet.set(i4);
                }
            }
            if (this.lowerSet != 0) {
                for (int i5 = 0; i5 < 64; i5++) {
                    if ((this.lowerSet & (1 << i5)) != 0) {
                        snapshotIdSet = snapshotIdSet.set(this.lowerBound + i5);
                    }
                }
            }
            if (this.upperSet != 0) {
                while (i3 < 64) {
                    if ((this.upperSet & (1 << i3)) != 0) {
                        snapshotIdSet = snapshotIdSet.set(i3 + 64 + this.lowerBound);
                    }
                    i3++;
                }
            }
            return snapshotIdSet;
        }
        int[] iArr4 = snapshotIdSet.belowBound;
        if (iArr4 != null) {
            for (int i6 : iArr4) {
                this = this.set(i6);
            }
        }
        if (snapshotIdSet.lowerSet != 0) {
            for (int i7 = 0; i7 < 64; i7++) {
                if ((snapshotIdSet.lowerSet & (1 << i7)) != 0) {
                    this = this.set(snapshotIdSet.lowerBound + i7);
                }
            }
        }
        if (snapshotIdSet.upperSet != 0) {
            while (i3 < 64) {
                if ((snapshotIdSet.upperSet & (1 << i3)) != 0) {
                    this = this.set(i3 + 64 + snapshotIdSet.lowerBound);
                }
                i3++;
            }
        }
        return this;
    }

    public final SnapshotIdSet set(int i) {
        int i2;
        int i3 = this.lowerBound;
        int i4 = i - i3;
        long j = 0;
        if (i4 >= 0 && i4 < 64) {
            long j2 = 1 << i4;
            long j3 = this.lowerSet;
            if ((j3 & j2) == 0) {
                return new SnapshotIdSet(this.upperSet, j3 | j2, i3, this.belowBound);
            }
        } else if (i4 >= 64 && i4 < 128) {
            long j4 = 1 << (i4 - 64);
            long j5 = this.upperSet;
            if ((j5 & j4) == 0) {
                return new SnapshotIdSet(j4 | j5, this.lowerSet, i3, this.belowBound);
            }
        } else if (i4 < 128) {
            int[] iArr = this.belowBound;
            if (iArr == null) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, i3, new int[]{i});
            }
            int binarySearch = SnapshotIdSetKt.binarySearch(iArr, i);
            if (binarySearch < 0) {
                int i5 = -(binarySearch + 1);
                int length = iArr.length;
                int[] iArr2 = new int[length + 1];
                ArraysKt.copyInto(0, 0, i5, iArr, iArr2);
                ArraysKt.copyInto(i5 + 1, i5, length, iArr, iArr2);
                iArr2[i5] = i;
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
            }
        } else if (!get(i)) {
            long j6 = this.upperSet;
            long j7 = this.lowerSet;
            int i6 = this.lowerBound;
            int i7 = ((i + 1) / 64) * 64;
            ArrayList arrayList = null;
            long j8 = j7;
            long j9 = j6;
            while (true) {
                if (i6 >= i7) {
                    i2 = i6;
                    break;
                }
                if (j8 != j) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        int[] iArr3 = this.belowBound;
                        if (iArr3 != null) {
                            for (int i8 : iArr3) {
                                arrayList.add(Integer.valueOf(i8));
                            }
                        }
                    }
                    for (int i9 = 0; i9 < 64; i9++) {
                        if (((1 << i9) & j8) != 0) {
                            arrayList.add(Integer.valueOf(i9 + i6));
                        }
                    }
                    j = 0;
                }
                if (j9 == j) {
                    i2 = i7;
                    j8 = j;
                    break;
                }
                i6 += 64;
                j8 = j9;
                j9 = j;
            }
            return new SnapshotIdSet(j9, j8, i2, arrayList != null ? CollectionsKt.toIntArray(arrayList) : this.belowBound).set(i);
        }
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(this, 10));
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = arrayList.get(i2);
            i++;
            if (i > 1) {
                sb2.append((CharSequence) ", ");
            }
            if (obj != null ? obj instanceof CharSequence : true) {
                sb2.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb2.append(((Character) obj).charValue());
            } else {
                sb2.append((CharSequence) String.valueOf(obj));
            }
        }
        sb2.append((CharSequence) "");
        sb.append(sb2.toString());
        sb.append(']');
        return sb.toString();
    }
}
