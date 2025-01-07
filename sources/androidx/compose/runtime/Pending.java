package androidx.compose.runtime;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class Pending {
    public int groupIndex;
    public final MutableIntObjectMap groupInfos;
    public final List keyInfos;
    public final Lazy keyMap$delegate;
    public final int startIndex;
    public final List usedKeys;

    public Pending(int i, List list) {
        this.keyInfos = list;
        this.startIndex = i;
        if (i < 0) {
            PreconditionsKt.throwIllegalArgumentException("Invalid start index");
        }
        this.usedKeys = new ArrayList();
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap();
        int size = ((ArrayList) list).size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            KeyInfo keyInfo = (KeyInfo) ((ArrayList) this.keyInfos).get(i3);
            int i4 = keyInfo.location;
            int i5 = keyInfo.nodes;
            mutableIntObjectMap.set(i4, new GroupInfo(i3, i2, i5));
            i2 += i5;
        }
        this.groupInfos = mutableIntObjectMap;
        this.keyMap$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.compose.runtime.Pending$keyMap$2
            {
                super(0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.KeyInfo, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r5v4 */
            /* JADX WARN: Type inference failed for: r5v6, types: [java.util.List] */
            /* JADX WARN: Type inference failed for: r5v7 */
            /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
            /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object[]] */
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                int size2 = ((ArrayList) Pending.this.keyInfos).size();
                OpaqueKey opaqueKey = ComposerKt.invocation;
                MutableScatterMap mutableScatterMap = new MutableScatterMap(size2);
                Pending pending = Pending.this;
                int size3 = ((ArrayList) pending.keyInfos).size();
                for (int i6 = 0; i6 < size3; i6++) {
                    ?? r5 = (KeyInfo) ((ArrayList) pending.keyInfos).get(i6);
                    Object obj = r5.objectKey;
                    int i7 = r5.key;
                    Object joinedKey = obj != null ? new JoinedKey(Integer.valueOf(i7), r5.objectKey) : Integer.valueOf(i7);
                    int findInsertIndex = mutableScatterMap.findInsertIndex(joinedKey);
                    boolean z = findInsertIndex < 0;
                    Object obj2 = z ? null : mutableScatterMap.values[findInsertIndex];
                    if (obj2 != null) {
                        if (!(obj2 instanceof List) || ((obj2 instanceof KMappedMarker) && !(obj2 instanceof KMutableList))) {
                            r5 = CollectionsKt__CollectionsKt.mutableListOf(obj2, r5);
                        } else {
                            List asMutableList = TypeIntrinsics.asMutableList(obj2);
                            asMutableList.add(r5);
                            r5 = asMutableList;
                        }
                    }
                    if (z) {
                        int i8 = ~findInsertIndex;
                        mutableScatterMap.keys[i8] = joinedKey;
                        mutableScatterMap.values[i8] = r5;
                    } else {
                        mutableScatterMap.values[findInsertIndex] = r5;
                    }
                }
                return new MutableScatterMultiMap(mutableScatterMap);
            }
        });
    }

    public final boolean updateNodeCount(int i, int i2) {
        int i3;
        MutableIntObjectMap mutableIntObjectMap = this.groupInfos;
        GroupInfo groupInfo = (GroupInfo) mutableIntObjectMap.get(i);
        if (groupInfo == null) {
            return false;
        }
        int i4 = groupInfo.nodeIndex;
        int i5 = i2 - groupInfo.nodeCount;
        groupInfo.nodeCount = i2;
        if (i5 == 0) {
            return true;
        }
        Object[] objArr = mutableIntObjectMap.values;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i6 = 0;
        while (true) {
            long j = jArr[i6];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8 - ((~(i6 - length)) >>> 31);
                for (int i8 = 0; i8 < i7; i8++) {
                    if ((255 & j) < 128) {
                        GroupInfo groupInfo2 = (GroupInfo) objArr[(i6 << 3) + i8];
                        if (groupInfo2.nodeIndex >= i4 && !groupInfo2.equals(groupInfo) && (i3 = groupInfo2.nodeIndex + i5) >= 0) {
                            groupInfo2.nodeIndex = i3;
                        }
                    }
                    j >>= 8;
                }
                if (i7 != 8) {
                    return true;
                }
            }
            if (i6 == length) {
                return true;
            }
            i6++;
        }
    }
}
