package androidx.compose.animation.graphics.vector;

import androidx.collection.MutableScatterMap;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimatorSet extends Animator {
    public final List animators;
    public final Ordering ordering;
    public final int totalDuration;

    public AnimatorSet(List list, Ordering ordering) {
        Object obj;
        this.animators = list;
        this.ordering = ordering;
        int ordinal = ordering.ordinal();
        int i = 1;
        int i2 = 0;
        if (ordinal == 0) {
            if (list.isEmpty()) {
                obj = null;
            } else {
                ArrayList arrayList = (ArrayList) list;
                Object obj2 = arrayList.get(0);
                int totalDuration = ((Animator) obj2).getTotalDuration();
                int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
                if (1 <= lastIndex) {
                    while (true) {
                        Object obj3 = arrayList.get(i);
                        int totalDuration2 = ((Animator) obj3).getTotalDuration();
                        if (totalDuration < totalDuration2) {
                            obj2 = obj3;
                            totalDuration = totalDuration2;
                        }
                        if (i == lastIndex) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                obj = obj2;
            }
            Animator animator = (Animator) obj;
            if (animator != null) {
                i2 = animator.getTotalDuration();
            }
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            ArrayList arrayList2 = (ArrayList) list;
            int size = arrayList2.size();
            int i3 = 0;
            while (i2 < size) {
                i3 += ((Animator) arrayList2.get(i2)).getTotalDuration();
                i2++;
            }
            i2 = i3;
        }
        this.totalDuration = i2;
    }

    @Override // androidx.compose.animation.graphics.vector.Animator
    public final void collectPropertyValues(MutableScatterMap mutableScatterMap, int i, int i2) {
        int ordinal = this.ordering.ordinal();
        int i3 = 0;
        if (ordinal == 0) {
            ArrayList arrayList = (ArrayList) this.animators;
            int size = arrayList.size();
            while (i3 < size) {
                ((Animator) arrayList.get(i3)).collectPropertyValues(mutableScatterMap, i, i2);
                i3++;
            }
            return;
        }
        if (ordinal != 1) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.animators;
        int size2 = arrayList2.size();
        while (i3 < size2) {
            Animator animator = (Animator) arrayList2.get(i3);
            animator.collectPropertyValues(mutableScatterMap, i, i2);
            i2 += animator.getTotalDuration();
            i3++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimatorSet)) {
            return false;
        }
        AnimatorSet animatorSet = (AnimatorSet) obj;
        return Intrinsics.areEqual(this.animators, animatorSet.animators) && this.ordering == animatorSet.ordering;
    }

    @Override // androidx.compose.animation.graphics.vector.Animator
    public final int getTotalDuration() {
        return this.totalDuration;
    }

    public final int hashCode() {
        return this.ordering.hashCode() + (this.animators.hashCode() * 31);
    }

    public final String toString() {
        return "AnimatorSet(animators=" + this.animators + ", ordering=" + this.ordering + ')';
    }
}
