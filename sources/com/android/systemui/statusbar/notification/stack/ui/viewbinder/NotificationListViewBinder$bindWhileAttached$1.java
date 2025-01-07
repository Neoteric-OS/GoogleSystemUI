package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.shelf.ui.viewbinder.NotificationShelfViewBinder;
import com.android.systemui.statusbar.notification.stack.DisplaySwitchNotificationsHiderTracker;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationListViewBinder$bindWhileAttached$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ NotificationShelf $shelf;
    final /* synthetic */ NotificationStackScrollLayout $view;
    final /* synthetic */ NotificationStackScrollLayoutController $viewController;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NotificationListViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationShelf $shelf;
        final /* synthetic */ NotificationStackScrollLayout $view;
        final /* synthetic */ NotificationStackScrollLayoutController $viewController;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ NotificationListViewBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationShelf $shelf;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(NotificationListViewBinder notificationListViewBinder, NotificationShelf notificationShelf, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$shelf = notificationShelf;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, this.$shelf, continuation);
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
                    NotificationListViewBinder notificationListViewBinder = this.this$0;
                    NotificationShelf notificationShelf = this.$shelf;
                    this.label = 1;
                    NotificationListViewModel notificationListViewModel = notificationListViewBinder.viewModel;
                    Object bind = NotificationShelfViewBinder.bind(notificationListViewBinder.falsingManager, notificationShelf, notificationListViewBinder.nicBinder, notificationListViewModel.shelf, this);
                    if (bind != coroutineSingletons) {
                        bind = unit;
                    }
                    if (bind == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ StateFlow $hasNonClearableSilentNotifications;
            final /* synthetic */ NotificationStackScrollLayout $view;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, StateFlow stateFlow, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$view = notificationStackScrollLayout;
                this.$hasNonClearableSilentNotifications = stateFlow;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.this$0, this.$view, this.$hasNonClearableSilentNotifications, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0067, code lost:
            
                if (r10 == r0) goto L14;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                /*
                    r10 = this;
                    kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r1 = r10.label
                    kotlin.Unit r2 = kotlin.Unit.INSTANCE
                    r3 = 1
                    if (r1 == 0) goto L17
                    if (r1 != r3) goto Lf
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L6e
                Lf:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L17:
                    kotlin.ResultKt.throwOnFailure(r11)
                    com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder r5 = r10.this$0
                    com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r4 = r10.$view
                    kotlinx.coroutines.flow.StateFlow r7 = r10.$hasNonClearableSilentNotifications
                    r10.label = r3
                    com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel r11 = r5.viewModel
                    java.util.Optional r11 = r11.footer
                    r1 = 0
                    java.lang.Object r11 = r11.orElse(r1)
                    r6 = r11
                    com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel r6 = (com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel) r6
                    if (r6 == 0) goto L6a
                    com.android.systemui.common.ui.ConfigurationState r11 = r5.configuration
                    com.android.systemui.common.ui.ConfigurationStateImpl r11 = (com.android.systemui.common.ui.ConfigurationStateImpl) r11
                    com.android.systemui.statusbar.policy.ConfigurationController r3 = r11.configurationController
                    kotlinx.coroutines.flow.Flow r8 = com.android.systemui.statusbar.policy.ConfigurationControllerExtKt.getOnThemeChanged(r3)
                    kotlinx.coroutines.flow.Flow r3 = com.android.systemui.statusbar.policy.ConfigurationControllerExtKt.getOnDensityOrFontScaleChanged(r3)
                    kotlinx.coroutines.flow.Flow[] r3 = new kotlinx.coroutines.flow.Flow[]{r8, r3}
                    kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge r3 = kotlinx.coroutines.flow.FlowKt.merge(r3)
                    com.android.systemui.util.kotlin.FlowKt$emitOnStart$1 r8 = new com.android.systemui.util.kotlin.FlowKt$emitOnStart$1
                    r9 = 2
                    r8.<init>(r9, r1)
                    kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 r1 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1
                    r1.<init>(r8, r3)
                    com.android.systemui.common.ui.ConfigurationStateImpl$inflateLayout$$inlined$map$1 r3 = new com.android.systemui.common.ui.ConfigurationStateImpl$inflateLayout$$inlined$map$1
                    r3.<init>()
                    kotlinx.coroutines.CoroutineDispatcher r11 = r5.backgroundDispatcher
                    kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.flowOn(r3, r11)
                    com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$reinflateAndBindFooter$2$1 r1 = new com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$reinflateAndBindFooter$2$1
                    r8 = 0
                    r3 = r1
                    r3.<init>(r4, r5, r6, r7, r8)
                    java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt.collectLatest(r11, r1, r10)
                    if (r10 != r0) goto L6a
                    goto L6b
                L6a:
                    r10 = r2
                L6b:
                    if (r10 != r0) goto L6e
                    return r0
                L6e:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1.AnonymousClass1.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationStackScrollLayout $view;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$view = notificationStackScrollLayout;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.this$0, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NotificationListViewBinder notificationListViewBinder = this.this$0;
                    NotificationStackScrollLayout notificationStackScrollLayout = this.$view;
                    this.label = 1;
                    NotificationListViewModel notificationListViewModel = notificationListViewBinder.viewModel;
                    Object collect = FlowKt.combine((Flow) notificationListViewModel.shouldShowEmptyShadeView$delegate.getValue(), (Flow) notificationListViewModel.areNotificationsHiddenInShade$delegate.getValue(), (Flow) notificationListViewModel.hasFilteredOutSeenNotifications$delegate.getValue(), NotificationListViewBinder$bindEmptyShade$3.INSTANCE).collect(new NotificationListViewBinder$bindEmptyShade$4(notificationStackScrollLayout, 0), this);
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

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ StateFlow $hasNonClearableSilentNotifications;
            final /* synthetic */ NotificationStackScrollLayout $view;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, StateFlow stateFlow, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$view = notificationStackScrollLayout;
                this.$hasNonClearableSilentNotifications = stateFlow;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.this$0, this.$view, this.$hasNonClearableSilentNotifications, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NotificationListViewBinder notificationListViewBinder = this.this$0;
                    NotificationStackScrollLayout notificationStackScrollLayout = this.$view;
                    StateFlow stateFlow = this.$hasNonClearableSilentNotifications;
                    this.label = 1;
                    notificationListViewBinder.getClass();
                    Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new NotificationListViewBinder$bindSilentHeaderClickListener$2(notificationListViewBinder, notificationStackScrollLayout, stateFlow, null));
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationStackScrollLayout $view;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$view = notificationStackScrollLayout;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.this$0, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = (Flow) this.this$0.viewModel.isImportantForAccessibility$delegate.getValue();
                    NotificationListViewBinder$bindEmptyShade$4 notificationListViewBinder$bindEmptyShade$4 = new NotificationListViewBinder$bindEmptyShade$4(this.$view, 1);
                    this.label = 1;
                    if (flow.collect(notificationListViewBinder$bindEmptyShade$4, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindWhileAttached$1$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationStackScrollLayout $view;
            int label;
            final /* synthetic */ NotificationListViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationListViewBinder;
                this.$view = notificationStackScrollLayout;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.this$0, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NotificationListViewBinder notificationListViewBinder = this.this$0;
                    this.label = 1;
                    NotificationListViewBinder.access$bindLogger(notificationListViewBinder, this);
                    if (unit == coroutineSingletons) {
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
        public AnonymousClass1(NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, NotificationShelf notificationShelf, Continuation continuation) {
            super(2, continuation);
            this.$viewController = notificationStackScrollLayoutController;
            this.this$0 = notificationListViewBinder;
            this.$view = notificationStackScrollLayout;
            this.$shelf = notificationShelf;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewController, this.this$0, this.$view, this.$shelf, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                BuildersKt.launch$default(coroutineScope2, null, null, new AnonymousClass2(this.this$0, this.$shelf, null), 3);
                final NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.$viewController;
                NotificationListViewBinder notificationListViewBinder = this.this$0;
                NotificationListViewModel notificationListViewModel = notificationListViewBinder.viewModel;
                DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker = notificationListViewBinder.hiderTracker;
                final NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
                if (notificationStackScrollLayout.isAttachedToWindow()) {
                    notificationStackScrollLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.HideNotificationsBinder$bindHideList$$inlined$doOnDetach$1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public final void onViewDetachedFromWindow(View view) {
                            notificationStackScrollLayout.removeOnAttachStateChangeListener(this);
                            HideNotificationsBinder.access$bindHideState(notificationStackScrollLayoutController, false);
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public final void onViewAttachedToWindow(View view) {
                        }
                    });
                } else {
                    HideNotificationsBinder.access$bindHideState(notificationStackScrollLayoutController, false);
                }
                notificationListViewModel.hideListViewModel.getClass();
                ReadonlySharedFlow shareIn = FlowKt.shareIn(EmptyFlow.INSTANCE, coroutineScope2, SharingStarted.Companion.Lazily, 0);
                BuildersKt.launch$default(coroutineScope2, null, null, new HideNotificationsBinder$bindHideList$2(shareIn, notificationStackScrollLayoutController, null), 3);
                BuildersKt.launch$default(coroutineScope2, null, null, new HideNotificationsBinder$bindHideList$3(displaySwitchNotificationsHiderTracker, shareIn, null), 3);
                BuildersKt.launch$default(coroutineScope2, null, null, new HideNotificationsBinder$bindHideList$4(displaySwitchNotificationsHiderTracker, shareIn, null), 3);
                Flow flow = (Flow) this.this$0.viewModel.hasNonClearableSilentNotifications$delegate.getValue();
                this.L$0 = coroutineScope2;
                this.label = 1;
                Object stateIn = FlowKt.stateIn(flow, coroutineScope2, this);
                if (stateIn == coroutineSingletons) {
                    return coroutineSingletons;
                }
                coroutineScope = coroutineScope2;
                obj = stateIn;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            StateFlow stateFlow = (StateFlow) obj;
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, this.$view, stateFlow, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.this$0, this.$view, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.this$0, this.$view, stateFlow, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.this$0, this.$view, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.this$0, this.$view, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationListViewBinder$bindWhileAttached$1(NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, NotificationShelf notificationShelf, Continuation continuation) {
        super(3, continuation);
        this.$viewController = notificationStackScrollLayoutController;
        this.this$0 = notificationListViewBinder;
        this.$view = notificationStackScrollLayout;
        this.$shelf = notificationShelf;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        NotificationListViewBinder$bindWhileAttached$1 notificationListViewBinder$bindWhileAttached$1 = new NotificationListViewBinder$bindWhileAttached$1(this.$viewController, this.this$0, this.$view, this.$shelf, (Continuation) obj3);
        notificationListViewBinder$bindWhileAttached$1.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        notificationListViewBinder$bindWhileAttached$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) this.L$0), null, null, new AnonymousClass1(this.$viewController, this.this$0, this.$view, this.$shelf, null), 3);
        return Unit.INSTANCE;
    }
}
