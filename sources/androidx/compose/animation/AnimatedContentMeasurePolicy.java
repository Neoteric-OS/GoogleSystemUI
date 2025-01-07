package androidx.compose.animation;

import androidx.compose.animation.AnimatedContentTransitionScopeImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnimatedContentMeasurePolicy implements MeasurePolicy {
    public final AnimatedContentTransitionScopeImpl rootScope;

    public AnimatedContentMeasurePolicy(AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl) {
        this.rootScope = animatedContentTransitionScopeImpl;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer valueOf;
        if (list.isEmpty()) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).maxIntrinsicHeight(i));
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer valueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).maxIntrinsicHeight(i));
                    if (valueOf2.compareTo(valueOf) > 0) {
                        valueOf = valueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer valueOf;
        if (list.isEmpty()) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).maxIntrinsicWidth(i));
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer valueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).maxIntrinsicWidth(i));
                    if (valueOf2.compareTo(valueOf) > 0) {
                        valueOf = valueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        Placeable placeable;
        final int i;
        int i2;
        Placeable placeable2;
        final int i3;
        MeasureResult layout$1;
        int size = list.size();
        final Placeable[] placeableArr = new Placeable[size];
        int size2 = list.size();
        long j2 = 0;
        for (int i4 = 0; i4 < size2; i4++) {
            Measurable measurable = (Measurable) list.get(i4);
            Object parentData = measurable.getParentData();
            AnimatedContentTransitionScopeImpl.ChildData childData = parentData instanceof AnimatedContentTransitionScopeImpl.ChildData ? (AnimatedContentTransitionScopeImpl.ChildData) parentData : null;
            if (childData != null && ((Boolean) ((SnapshotMutableStateImpl) childData.isTarget$delegate).getValue()).booleanValue()) {
                placeableArr[i4] = measurable.mo479measureBRTryo0(j);
                j2 = (r7.height & 4294967295L) | (r7.width << 32);
            }
        }
        int size3 = list.size();
        for (int i5 = 0; i5 < size3; i5++) {
            Measurable measurable2 = (Measurable) list.get(i5);
            if (placeableArr[i5] == null) {
                placeableArr[i5] = measurable2.mo479measureBRTryo0(j);
            }
        }
        if (measureScope.isLookingAhead()) {
            i = (int) (j2 >> 32);
        } else {
            if (size == 0) {
                placeable = null;
            } else {
                placeable = placeableArr[0];
                int i6 = size - 1;
                if (i6 != 0) {
                    int i7 = placeable != null ? placeable.width : 0;
                    IntProgressionIterator it = new IntRange(1, i6, 1).iterator();
                    while (it.hasNext) {
                        Placeable placeable3 = placeableArr[it.nextInt()];
                        int i8 = placeable3 != null ? placeable3.width : 0;
                        if (i7 < i8) {
                            placeable = placeable3;
                            i7 = i8;
                        }
                    }
                }
            }
            i = placeable != null ? placeable.width : 0;
        }
        if (measureScope.isLookingAhead()) {
            i3 = (int) (j2 & 4294967295L);
        } else {
            if (size == 0) {
                i2 = 0;
                placeable2 = null;
            } else {
                i2 = 0;
                placeable2 = placeableArr[0];
                int i9 = size - 1;
                if (i9 != 0) {
                    int i10 = placeable2 != null ? placeable2.height : 0;
                    IntProgressionIterator it2 = new IntRange(1, i9, 1).iterator();
                    while (it2.hasNext) {
                        Placeable placeable4 = placeableArr[it2.nextInt()];
                        int i11 = placeable4 != null ? placeable4.height : 0;
                        if (i10 < i11) {
                            placeable2 = placeable4;
                            i10 = i11;
                        }
                    }
                }
            }
            i3 = placeable2 != null ? placeable2.height : i2;
        }
        if (!measureScope.isLookingAhead()) {
            ((SnapshotMutableStateImpl) this.rootScope.measuredSize$delegate).setValue(new IntSize((i << 32) | (i3 & 4294967295L)));
        }
        layout$1 = measureScope.layout$1(i, i3, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.AnimatedContentMeasurePolicy$measure$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                Placeable[] placeableArr2 = placeableArr;
                AnimatedContentMeasurePolicy animatedContentMeasurePolicy = this;
                int i12 = i;
                int i13 = i3;
                for (Placeable placeable5 : placeableArr2) {
                    if (placeable5 != null) {
                        long mo274alignKFBX0sM = animatedContentMeasurePolicy.rootScope.contentAlignment.mo274alignKFBX0sM((placeable5.width << 32) | (placeable5.height & 4294967295L), (i12 << 32) | (i13 & 4294967295L), LayoutDirection.Ltr);
                        placementScope.place(placeable5, (int) (mo274alignKFBX0sM >> 32), (int) (mo274alignKFBX0sM & 4294967295L), 0.0f);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer valueOf;
        if (list.isEmpty()) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).minIntrinsicHeight(i));
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer valueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).minIntrinsicHeight(i));
                    if (valueOf2.compareTo(valueOf) > 0) {
                        valueOf = valueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer valueOf;
        if (list.isEmpty()) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).minIntrinsicWidth(i));
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer valueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).minIntrinsicWidth(i));
                    if (valueOf2.compareTo(valueOf) > 0) {
                        valueOf = valueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }
}
