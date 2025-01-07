package com.android.wm.shell.bubbles.storage;

import java.util.List;
import java.util.function.Predicate;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleVolatileRepository$removeBubbles$1$1 implements Predicate {
    public final /* synthetic */ Object $b;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BubbleVolatileRepository$removeBubbles$1$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$b = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return Intrinsics.areEqual(((BubbleEntity) this.$b).key, ((BubbleEntity) obj).key);
            case 1:
                return Intrinsics.areEqual(((BubbleEntity) this.$b).key, ((BubbleEntity) obj).key);
            default:
                return !((List) this.$b).contains(Integer.valueOf(((BubbleEntity) obj).userId));
        }
    }
}
