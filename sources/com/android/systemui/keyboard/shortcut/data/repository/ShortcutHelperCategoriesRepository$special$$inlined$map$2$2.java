package com.android.systemui.keyboard.shortcut.data.repository;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperCategoriesRepository$special$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ ShortcutHelperCategoriesRepository this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$2$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortcutHelperCategoriesRepository$special$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public ShortcutHelperCategoriesRepository$special$$inlined$map$2$2(FlowCollector flowCollector, ShortcutHelperCategoriesRepository shortcutHelperCategoriesRepository) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = shortcutHelperCategoriesRepository;
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x024c, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.compare(r11.getBaseCharacter(), 0) <= 0) goto L82;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0396 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r10v16, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:118:0x00c2 -> B:112:0x00c7). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r31, kotlin.coroutines.Continuation r32) {
        /*
            Method dump skipped, instructions count: 922
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
