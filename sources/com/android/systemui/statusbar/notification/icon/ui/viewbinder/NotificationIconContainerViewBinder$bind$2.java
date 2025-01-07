package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import java.util.Collection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bind$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConfigurationState $configuration;
    final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
    final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ConfigurationState $configuration;
        final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
        final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function3 {
            final /* synthetic */ ContrastColorUtil $contrastColorUtil;
            final /* synthetic */ StateFlow $iconColors;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(StateFlow stateFlow, ContrastColorUtil contrastColorUtil, Continuation continuation) {
                super(3, continuation);
                this.$iconColors = stateFlow;
                this.$contrastColorUtil = contrastColorUtil;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$iconColors, this.$contrastColorUtil, (Continuation) obj3);
                anonymousClass2.L$0 = (StatusBarIconView) obj2;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final StatusBarIconView statusBarIconView = (StatusBarIconView) this.L$0;
                    StateFlow stateFlow = this.$iconColors;
                    final ContrastColorUtil contrastColorUtil = this.$contrastColorUtil;
                    this.label = 1;
                    Object collect = stateFlow.collect(new FlowCollector() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBinder$bindIconColors$$inlined$collectTracingEach$1
                        /* JADX WARN: Removed duplicated region for block: B:14:0x007e  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                            /*
                                r6 = this;
                                com.android.systemui.statusbar.StatusBarIconView r8 = com.android.systemui.statusbar.StatusBarIconView.this
                                boolean r0 = android.os.Trace.isEnabled()
                                if (r0 == 0) goto Ld
                                java.lang.String r1 = "SBIV#bindIconColors"
                                com.android.app.tracing.TraceUtilsKt.beginSlice(r1)
                            Ld:
                                com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel$IconColorsImpl r7 = (com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel.IconColorsImpl) r7     // Catch: java.lang.Throwable -> L41
                                java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L41
                                r2 = 2131362736(0x7f0a03b0, float:1.834526E38)
                                java.lang.Object r2 = r8.getTag(r2)     // Catch: java.lang.Throwable -> L41
                                boolean r2 = r1.equals(r2)     // Catch: java.lang.Throwable -> L41
                                r3 = 0
                                if (r2 == 0) goto L43
                                com.android.internal.util.ContrastColorUtil r6 = r2     // Catch: java.lang.Throwable -> L41
                                r2 = 2131362735(0x7f0a03af, float:1.8345259E38)
                                java.lang.Object r4 = r8.getTag(r2)     // Catch: java.lang.Throwable -> L41
                                if (r4 == 0) goto L2f
                                boolean r6 = r1.equals(r4)     // Catch: java.lang.Throwable -> L41
                                goto L3e
                            L2f:
                                android.graphics.drawable.Drawable r1 = r8.getDrawable()     // Catch: java.lang.Throwable -> L41
                                boolean r6 = r6.isGrayscaleIcon(r1)     // Catch: java.lang.Throwable -> L41
                                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L41
                                r8.setTag(r2, r1)     // Catch: java.lang.Throwable -> L41
                            L3e:
                                if (r6 == 0) goto L74
                                goto L43
                            L41:
                                r6 = move-exception
                                goto L84
                            L43:
                                int[] r6 = new int[]{r3, r3}     // Catch: java.lang.Throwable -> L41
                                r8.getLocationOnScreen(r6)     // Catch: java.lang.Throwable -> L41
                                android.graphics.Rect r1 = new android.graphics.Rect     // Catch: java.lang.Throwable -> L41
                                r2 = r6[r3]     // Catch: java.lang.Throwable -> L41
                                r3 = 1
                                r6 = r6[r3]     // Catch: java.lang.Throwable -> L41
                                int r3 = r8.getLeft()     // Catch: java.lang.Throwable -> L41
                                int r4 = r8.getWidth()     // Catch: java.lang.Throwable -> L41
                                int r4 = r4 + r3
                                int r3 = r8.getTop()     // Catch: java.lang.Throwable -> L41
                                int r5 = r8.getHeight()     // Catch: java.lang.Throwable -> L41
                                int r5 = r5 + r3
                                r1.<init>(r2, r6, r4, r5)     // Catch: java.lang.Throwable -> L41
                                java.util.Collection r6 = r7.areas     // Catch: java.lang.Throwable -> L41
                                boolean r6 = com.android.systemui.plugins.DarkIconDispatcher.isInAreas(r6, r1)     // Catch: java.lang.Throwable -> L41
                                if (r6 == 0) goto L72
                                int r6 = r7.tint     // Catch: java.lang.Throwable -> L41
                            L70:
                                r3 = r6
                                goto L74
                            L72:
                                r6 = -1
                                goto L70
                            L74:
                                r8.setStaticDrawableColor(r3)     // Catch: java.lang.Throwable -> L41
                                int r6 = r7.tint     // Catch: java.lang.Throwable -> L41
                                r8.setDecorColor(r6)     // Catch: java.lang.Throwable -> L41
                                if (r0 == 0) goto L81
                                com.android.app.tracing.TraceUtilsKt.endSlice()
                            L81:
                                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                                return r6
                            L84:
                                if (r0 == 0) goto L89
                                com.android.app.tracing.TraceUtilsKt.endSlice()
                            L89:
                                throw r6
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBinder$bindIconColors$$inlined$collectTracingEach$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }, this);
                    if (collect != coroutineSingletons) {
                        collect = unit;
                    }
                    if (collect == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return unit;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, Continuation continuation) {
            super(2, continuation);
            this.$view = notificationIconContainer;
            this.$viewModel = notificationIconContainerStatusBarViewModel;
            this.$configuration = configurationState;
            this.$systemBarUtilsState = systemBarUtilsState;
            this.$viewStore = iconViewStore;
            this.$failureTracker = statusBarIconViewBindingFailureTracker;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, this.$configuration, this.$systemBarUtilsState, this.$viewStore, this.$failureTracker, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ContrastColorUtil contrastColorUtil;
            Object stateIn;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                contrastColorUtil = ContrastColorUtil.getInstance(this.$view.getContext());
                final Flow flow = this.$viewModel.iconColors;
                final NotificationIconContainer notificationIconContainer = this.$view;
                Flow flow2 = new Flow() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ NotificationIconContainer $view$inlined;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, NotificationIconContainer notificationIconContainer) {
                            this.$this_unsafeFlow = flowCollector;
                            this.$view$inlined = notificationIconContainer;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                            /*
                                r8 = this;
                                boolean r0 = r10 instanceof com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r10
                                com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1$2$1
                                r0.<init>(r10)
                            L18:
                                java.lang.Object r10 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r10)
                                goto L79
                            L27:
                                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                                r8.<init>(r9)
                                throw r8
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r10)
                                com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel$iconColors$1$1 r9 = (com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel$iconColors$1$1) r9
                                r10 = 0
                                int[] r2 = new int[]{r10, r10}
                                com.android.systemui.statusbar.phone.NotificationIconContainer r4 = r8.$view$inlined
                                r4.getLocationOnScreen(r2)
                                android.graphics.Rect r5 = new android.graphics.Rect
                                r10 = r2[r10]
                                r2 = r2[r3]
                                int r6 = r4.getLeft()
                                int r7 = r4.getWidth()
                                int r7 = r7 + r6
                                int r6 = r4.getTop()
                                int r4 = r4.getHeight()
                                int r4 = r4 + r6
                                r5.<init>(r10, r2, r7, r4)
                                java.util.Collection r10 = r9.$areas
                                boolean r10 = com.android.systemui.plugins.DarkIconDispatcher.isInAreas(r10, r5)
                                if (r10 == 0) goto L6b
                                com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel$IconColorsImpl r10 = new com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel$IconColorsImpl
                                java.util.Collection r2 = r9.$areas
                                int r9 = r9.$tint
                                r10.<init>(r9, r2)
                                goto L6c
                            L6b:
                                r10 = 0
                            L6c:
                                if (r10 == 0) goto L79
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                                java.lang.Object r8 = r8.emit(r10, r0)
                                if (r8 != r1) goto L79
                                return r1
                            L79:
                                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                                return r8
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, notificationIconContainer), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                };
                this.L$0 = contrastColorUtil;
                this.label = 1;
                stateIn = FlowKt.stateIn(flow2, coroutineScope, this);
                if (stateIn == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ContrastColorUtil contrastColorUtil2 = (ContrastColorUtil) this.L$0;
                ResultKt.throwOnFailure(obj);
                contrastColorUtil = contrastColorUtil2;
                stateIn = obj;
            }
            Flow flow3 = this.$viewModel.icons;
            NotificationIconContainer notificationIconContainer2 = this.$view;
            ConfigurationState configurationState = this.$configuration;
            SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
            final StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = this.$failureTracker;
            Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder.bind.2.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    StatusBarIconViewBindingFailureTracker.this.statusBarFailures = (Collection) obj2;
                    return Unit.INSTANCE;
                }
            };
            NotificationIconContainerViewBinder.IconViewStore iconViewStore = this.$viewStore;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2((StateFlow) stateIn, contrastColorUtil, null);
            this.L$0 = null;
            this.label = 2;
            Object coroutineScope2 = CoroutineScopeKt.coroutineScope(this, new NotificationIconContainerViewBinder$bindIcons$3(configurationState, systemBarUtilsState, flow3, "statusbar", notificationIconContainer2, function1, iconViewStore, anonymousClass2, null));
            if (coroutineScope2 != coroutineSingletons) {
                coroutineScope2 = unit;
            }
            return coroutineScope2 == coroutineSingletons ? coroutineSingletons : unit;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = notificationIconContainerStatusBarViewModel;
            this.$view = notificationIconContainer;
            this.$viewStore = iconViewStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$viewModel, this.$view, this.$viewStore, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = this.$viewModel;
                NotificationIconContainer notificationIconContainer = this.$view;
                NotificationIconContainerViewBinder.IconViewStore iconViewStore = this.$viewStore;
                this.label = 1;
                Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new NotificationIconContainerViewBinder$bindIsolatedIcon$2(notificationIconContainerStatusBarViewModel, notificationIconContainer, iconViewStore, null));
                if (coroutineScope != coroutineSingletons) {
                    coroutineScope = unit;
                }
                if (coroutineScope == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$2$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = notificationIconContainerStatusBarViewModel;
            this.$view = notificationIconContainer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.$viewModel, this.$view, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.$viewModel.animationsEnabled;
                NotificationIconContainer notificationIconContainer = this.$view;
                this.label = 1;
                Object collect = flow.collect(new NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1(notificationIconContainer, 0), this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconContainerViewBinder$bind$2(NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, Continuation continuation) {
        super(2, continuation);
        this.$view = notificationIconContainer;
        this.$viewModel = notificationIconContainerStatusBarViewModel;
        this.$configuration = configurationState;
        this.$systemBarUtilsState = systemBarUtilsState;
        this.$viewStore = iconViewStore;
        this.$failureTracker = statusBarIconViewBindingFailureTracker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainerViewBinder$bind$2 notificationIconContainerViewBinder$bind$2 = new NotificationIconContainerViewBinder$bind$2(this.$view, this.$viewModel, this.$configuration, this.$systemBarUtilsState, this.$viewStore, this.$failureTracker, continuation);
        notificationIconContainerViewBinder$bind$2.L$0 = obj;
        return notificationIconContainerViewBinder$bind$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        NotificationIconContainerViewBinder$bind$2 notificationIconContainerViewBinder$bind$2 = (NotificationIconContainerViewBinder$bind$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        notificationIconContainerViewBinder$bind$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$view, this.$viewModel, this.$configuration, this.$systemBarUtilsState, this.$viewStore, this.$failureTracker, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$view, this.$viewStore, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$view, null), 3);
        return Unit.INSTANCE;
    }
}
