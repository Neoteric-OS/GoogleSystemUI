package com.android.systemui.statusbar.phone;

import android.content.res.Configuration;
import android.view.Window;
import com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog;
import com.android.systemui.statusbar.policy.ConfigurationControllerExtKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemUIBottomSheetDialog$onStart$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIBottomSheetDialog this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$onStart$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SystemUIBottomSheetDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SystemUIBottomSheetDialog systemUIBottomSheetDialog, Continuation continuation) {
            super(2, continuation);
            this.this$0 = systemUIBottomSheetDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((SystemUIBottomSheetDialog.WindowLayout.Layout) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SystemUIBottomSheetDialog.WindowLayout.Layout layout = (SystemUIBottomSheetDialog.WindowLayout.Layout) this.L$0;
            Window window = this.this$0.getWindow();
            if (window != null) {
                window.setLayout(layout.width, -2);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$onStart$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SystemUIBottomSheetDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SystemUIBottomSheetDialog systemUIBottomSheetDialog, Continuation continuation) {
            super(2, continuation);
            this.this$0 = systemUIBottomSheetDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((Configuration) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass2.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Configuration configuration = (Configuration) this.L$0;
            SystemUIBottomSheetDialog systemUIBottomSheetDialog = this.this$0;
            systemUIBottomSheetDialog.delegate.onConfigurationChanged(systemUIBottomSheetDialog, configuration);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIBottomSheetDialog$onStart$1(SystemUIBottomSheetDialog systemUIBottomSheetDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIBottomSheetDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIBottomSheetDialog$onStart$1 systemUIBottomSheetDialog$onStart$1 = new SystemUIBottomSheetDialog$onStart$1(this.this$0, continuation);
        systemUIBottomSheetDialog$onStart$1.L$0 = obj;
        return systemUIBottomSheetDialog$onStart$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIBottomSheetDialog$onStart$1 systemUIBottomSheetDialog$onStart$1 = (SystemUIBottomSheetDialog$onStart$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIBottomSheetDialog$onStart$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = 0;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        final SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge limitedEdgeToEdge = (SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge) this.this$0.windowLayout;
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1(limitedEdgeToEdge, null), ConfigurationControllerExtKt.getOnConfigChanged(limitedEdgeToEdge.configurationController));
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge limitedEdgeToEdge) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = limitedEdgeToEdge;
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L58
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.content.res.Configuration r5 = (android.content.res.Configuration) r5
                        com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge r5 = r4.this$0
                        android.content.Context r5 = r5.context
                        android.content.res.Resources r5 = r5.getResources()
                        r6 = 2131034133(0x7f050015, float:1.7678775E38)
                        boolean r5 = r5.getBoolean(r6)
                        if (r5 == 0) goto L47
                        r5 = -1
                        goto L48
                    L47:
                        r5 = -2
                    L48:
                        com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$Layout r6 = new com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$Layout
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L58
                        return r1
                    L58:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, limitedEdgeToEdge), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new AnonymousClass1(this.this$0, null), i), coroutineScope);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(ConfigurationControllerExtKt.getOnConfigChanged(this.this$0.configurationController), new AnonymousClass2(this.this$0, null), i), coroutineScope);
        return Unit.INSTANCE;
    }
}
