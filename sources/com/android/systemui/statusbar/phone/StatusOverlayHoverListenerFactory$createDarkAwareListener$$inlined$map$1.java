package com.android.systemui.statusbar.phone;

import android.view.View;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1 implements Flow {
    public final /* synthetic */ StateFlow $this_unsafeTransform$inlined;
    public final /* synthetic */ View $view$inlined;
    public final /* synthetic */ StatusOverlayHoverListenerFactory this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ View $view$inlined;
        public final /* synthetic */ StatusOverlayHoverListenerFactory this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory, View view) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = statusOverlayHoverListenerFactory;
            this.$view$inlined = view;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L56
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                com.android.systemui.statusbar.phone.SysuiDarkIconDispatcher$DarkChange r5 = (com.android.systemui.statusbar.phone.SysuiDarkIconDispatcher$DarkChange) r5
                android.view.View r6 = r4.$view$inlined
                com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory r2 = r4.this$0
                r2.getClass()
                java.util.Collection r2 = r5.areas
                int r5 = r5.tint
                int r5 = com.android.systemui.plugins.DarkIconDispatcher.getTint(r2, r6, r5)
                r6 = -1
                if (r5 != r6) goto L49
                com.android.systemui.statusbar.phone.HoverTheme r5 = com.android.systemui.statusbar.phone.HoverTheme.LIGHT
                goto L4b
            L49:
                com.android.systemui.statusbar.phone.HoverTheme r5 = com.android.systemui.statusbar.phone.HoverTheme.DARK
            L4b:
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                java.lang.Object r4 = r4.emit(r5, r0)
                if (r4 != r1) goto L56
                return r1
            L56:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1(StateFlow stateFlow, StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory, View view) {
        this.$this_unsafeTransform$inlined = stateFlow;
        this.this$0 = statusOverlayHoverListenerFactory;
        this.$view$inlined = view;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0, this.$view$inlined), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
