package com.android.systemui.keyboard.shortcut.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperCategoriesInteractor$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ ReadonlyStateFlow $this_unsafeTransform$inlined;
    public final /* synthetic */ ShortcutHelperCategoriesInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperCategoriesInteractor$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ ShortcutHelperCategoriesInteractor this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperCategoriesInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, ShortcutHelperCategoriesInteractor shortcutHelperCategoriesInteractor) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = shortcutHelperCategoriesInteractor;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r19, kotlin.coroutines.Continuation r20) {
            /*
                Method dump skipped, instructions count: 323
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperCategoriesInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public ShortcutHelperCategoriesInteractor$special$$inlined$map$1(ReadonlyStateFlow readonlyStateFlow, ShortcutHelperCategoriesInteractor shortcutHelperCategoriesInteractor) {
        this.$this_unsafeTransform$inlined = readonlyStateFlow;
        this.this$0 = shortcutHelperCategoriesInteractor;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0), continuation);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
