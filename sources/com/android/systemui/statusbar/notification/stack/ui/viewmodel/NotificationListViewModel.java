package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import android.os.Build;
import android.util.Log;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.domain.interactor.RemoteInputInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.statusbar.policy.domain.interactor.UserSetupInteractor;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.android.systemui.util.ui.AnimatableEvent;
import com.android.systemui.util.ui.AnimatedValue;
import com.android.systemui.util.ui.AnimatedValueKt;
import java.util.Optional;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptySet;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationListViewModel extends FlowDumperImpl {
    public final Lazy areNotificationsHiddenInShade$delegate;
    public final Optional footer;
    public final Lazy hasClearableAlertingNotifications$delegate;
    public final Lazy hasFilteredOutSeenNotifications$delegate;
    public final Lazy hasNonClearableSilentNotifications$delegate;
    public final Lazy hasPinnedHeadsUpRow$delegate;
    public final Lazy headsUpAnimationsEnabled$delegate;
    public final HeadsUpNotificationInteractor headsUpNotificationInteractor;
    public final HideListViewModel hideListViewModel;
    public final Lazy isImportantForAccessibility$delegate;
    public final Lazy pinnedHeadsUpRows$delegate;
    public final NotificationShelfViewModel shelf;
    public final Lazy shouldHideFooterView$delegate;
    public final Lazy shouldIncludeFooterView$delegate;
    public final Lazy shouldShowEmptyShadeView$delegate;
    public final Lazy shouldShowFooterView$delegate;
    public final Lazy topHeadsUpRow$delegate;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VisibilityChange {
        public static final /* synthetic */ VisibilityChange[] $VALUES;
        public static final VisibilityChange APPEAR_WITH_ANIMATION;
        public static final VisibilityChange DISAPPEAR_WITHOUT_ANIMATION;
        public static final VisibilityChange DISAPPEAR_WITH_ANIMATION;
        private final boolean canAnimate;
        private final boolean visible;

        static {
            VisibilityChange visibilityChange = new VisibilityChange(0, "DISAPPEAR_WITHOUT_ANIMATION", false, false);
            DISAPPEAR_WITHOUT_ANIMATION = visibilityChange;
            VisibilityChange visibilityChange2 = new VisibilityChange(1, "DISAPPEAR_WITH_ANIMATION", false, true);
            DISAPPEAR_WITH_ANIMATION = visibilityChange2;
            VisibilityChange visibilityChange3 = new VisibilityChange(2, "APPEAR_WITH_ANIMATION", true, true);
            APPEAR_WITH_ANIMATION = visibilityChange3;
            VisibilityChange[] visibilityChangeArr = {visibilityChange, visibilityChange2, visibilityChange3};
            $VALUES = visibilityChangeArr;
            EnumEntriesKt.enumEntries(visibilityChangeArr);
        }

        public VisibilityChange(int i, String str, boolean z, boolean z2) {
            this.visible = z;
            this.canAnimate = z2;
        }

        public static VisibilityChange valueOf(String str) {
            return (VisibilityChange) Enum.valueOf(VisibilityChange.class, str);
        }

        public static VisibilityChange[] values() {
            return (VisibilityChange[]) $VALUES.clone();
        }

        public final boolean getCanAnimate() {
            return this.canAnimate;
        }

        public final boolean getVisible() {
            return this.visible;
        }
    }

    public NotificationListViewModel(NotificationShelfViewModel notificationShelfViewModel, HideListViewModel hideListViewModel, Optional optional, Optional optional2, final ActiveNotificationsInteractor activeNotificationsInteractor, final NotificationStackInteractor notificationStackInteractor, HeadsUpNotificationInteractor headsUpNotificationInteractor, final RemoteInputInteractor remoteInputInteractor, final SeenNotificationsInteractor seenNotificationsInteractor, final ShadeInteractor shadeInteractor, final UserSetupInteractor userSetupInteractor, final ZenModeInteractor zenModeInteractor, final CoroutineDispatcher coroutineDispatcher, DumpManager dumpManager) {
        super(dumpManager);
        this.shelf = notificationShelfViewModel;
        this.hideListViewModel = hideListViewModel;
        this.footer = optional;
        this.isImportantForAccessibility$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$isImportantForAccessibility$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$isImportantForAccessibility$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function3 {
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                    anonymousClass1.Z$0 = booleanValue;
                    anonymousClass1.Z$1 = booleanValue2;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boolean.valueOf(this.Z$0 || !this.Z$1);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FlowKt.flowOn(NotificationListViewModel.this.dumpWhileCollecting(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(activeNotificationsInteractor.areAnyNotificationsPresent, notificationStackInteractor.isShowingOnLockscreen, new AnonymousClass1(3, null))), "isImportantForAccessibility"), coroutineDispatcher);
            }
        });
        this.shouldShowEmptyShadeView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldShowEmptyShadeView$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldShowEmptyShadeView$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function4 {
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                /* synthetic */ boolean Z$2;
                int label;

                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    boolean booleanValue3 = ((Boolean) obj3).booleanValue();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
                    anonymousClass1.Z$0 = booleanValue;
                    anonymousClass1.Z$1 = booleanValue2;
                    anonymousClass1.Z$2 = booleanValue3;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    boolean z2 = this.Z$1;
                    boolean z3 = this.Z$2;
                    boolean z4 = false;
                    if (!z && !z2 && !z3) {
                        z4 = true;
                    }
                    return Boolean.valueOf(z4);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FlowKt.flowOn(NotificationListViewModel.this.dumpWhileCollecting(FlowKt.distinctUntilChanged(FlowKt.combine(activeNotificationsInteractor.areAnyNotificationsPresent, ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isQsFullscreen(), notificationStackInteractor.isShowingOnLockscreen, new AnonymousClass1(4, null))), "shouldShowEmptyShadeView"), coroutineDispatcher);
            }
        });
        this.shouldHideFooterView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NotificationListViewModel notificationListViewModel = NotificationListViewModel.this;
                final StateFlow shadeExpansion = ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.getShadeExpansion();
                return FlowKt.flowOn(notificationListViewModel.dumpWhileCollecting(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.$this_unsafeFlow = flowCollector;
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
                                boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r6)
                                goto L4f
                            L27:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r6)
                                java.lang.Number r5 = (java.lang.Number) r5
                                float r5 = r5.floatValue()
                                r6 = 0
                                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                                if (r5 != 0) goto L3f
                                r5 = r3
                                goto L40
                            L3f:
                                r5 = 0
                            L40:
                                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L4f
                                return r1
                            L4f:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldHideFooterView$2$invoke$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }), "shouldHideFooterView"), coroutineDispatcher);
            }
        });
        this.shouldIncludeFooterView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function6 {
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                /* synthetic */ boolean Z$2;
                /* synthetic */ boolean Z$3;
                /* synthetic */ boolean Z$4;
                int label;

                public AnonymousClass1(Continuation continuation) {
                    super(6, continuation);
                }

                @Override // kotlin.jvm.functions.Function6
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    boolean booleanValue3 = ((Boolean) obj3).booleanValue();
                    boolean booleanValue4 = ((Boolean) obj4).booleanValue();
                    boolean booleanValue5 = ((Boolean) obj5).booleanValue();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1((Continuation) obj6);
                    anonymousClass1.Z$0 = booleanValue;
                    anonymousClass1.Z$1 = booleanValue2;
                    anonymousClass1.Z$2 = booleanValue3;
                    anonymousClass1.Z$3 = booleanValue4;
                    anonymousClass1.Z$4 = booleanValue5;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return !this.Z$0 ? NotificationListViewModel.VisibilityChange.DISAPPEAR_WITH_ANIMATION : !this.Z$1 ? NotificationListViewModel.VisibilityChange.DISAPPEAR_WITH_ANIMATION : this.Z$2 ? NotificationListViewModel.VisibilityChange.DISAPPEAR_WITHOUT_ANIMATION : this.Z$3 ? NotificationListViewModel.VisibilityChange.DISAPPEAR_WITH_ANIMATION : this.Z$4 ? NotificationListViewModel.VisibilityChange.DISAPPEAR_WITH_ANIMATION : NotificationListViewModel.VisibilityChange.APPEAR_WITH_ANIMATION;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2$4, reason: invalid class name */
            final /* synthetic */ class AnonymousClass4 extends AdaptedFunctionReference implements Function3 {
                public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

                public AnonymousClass4() {
                    super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    Boolean bool2 = (Boolean) obj2;
                    bool2.booleanValue();
                    return new Pair(bool, bool2);
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2$5, reason: invalid class name */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                private /* synthetic */ Object L$0;
                int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass5 anonymousClass5 = new AnonymousClass5(2, continuation);
                    anonymousClass5.L$0 = obj;
                    return anonymousClass5;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass5) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Boolean bool = Boolean.FALSE;
                        Pair pair = new Pair(bool, bool);
                        this.label = 1;
                        if (flowCollector.emit(pair, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2$6, reason: invalid class name */
            final class AnonymousClass6 extends SuspendLambda implements Function3 {
                /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    AnonymousClass6 anonymousClass6 = new AnonymousClass6(3, (Continuation) obj3);
                    anonymousClass6.L$0 = (NotificationListViewModel.VisibilityChange) obj;
                    anonymousClass6.L$1 = (Pair) obj2;
                    return anonymousClass6.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    NotificationListViewModel.VisibilityChange visibilityChange = (NotificationListViewModel.VisibilityChange) this.L$0;
                    Pair pair = (Pair) this.L$1;
                    return new AnimatableEvent(Boolean.valueOf(visibilityChange.getVisible()), ((Boolean) pair.component1()).booleanValue() && ((Boolean) pair.component2()).booleanValue() && visibilityChange.getCanAnimate());
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NotificationListViewModel notificationListViewModel = NotificationListViewModel.this;
                DistinctFlowImpl distinctUntilChanged = FlowKt.distinctUntilChanged(new Function2() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldIncludeFooterView$2.2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return Boolean.valueOf(((NotificationListViewModel.VisibilityChange) obj).getVisible() == ((NotificationListViewModel.VisibilityChange) obj2).getVisible());
                    }
                }, FlowKt.combine(activeNotificationsInteractor.areAnyNotificationsPresent, userSetupInteractor.isUserSetUp, notificationStackInteractor.isShowingOnLockscreen, ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isQsFullscreen(), remoteInputInteractor.isRemoteInputActive, new AnonymousClass1(null)));
                ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) shadeInteractor;
                return FlowKt.flowOn(notificationListViewModel.dumpWhileCollecting(AnimatedValueKt.toAnimatedValueFlow(com.android.systemui.util.kotlin.FlowKt.sample(distinctUntilChanged, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AnonymousClass5(2, null), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(shadeInteractorImpl.isShadeFullyExpanded, shadeInteractorImpl.isShadeTouchable, AnonymousClass4.INSTANCE)), new AnonymousClass6(3, null))), "shouldIncludeFooterView"), coroutineDispatcher);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$shouldShowFooterView$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new AnimatedValue.NotAnimating(Boolean.FALSE));
            }
        });
        this.areNotificationsHiddenInShade$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$areNotificationsHiddenInShade$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationListViewModel.this.dumpWhileCollecting(zenModeInteractor.areNotificationsHiddenInShade, "areNotificationsHiddenInShade");
            }
        });
        this.hasFilteredOutSeenNotifications$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$hasFilteredOutSeenNotifications$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationListViewModel.this.dumpWhileCollecting(seenNotificationsInteractor.hasFilteredOutSeenNotifications, "hasFilteredOutSeenNotifications");
            }
        });
        this.hasClearableAlertingNotifications$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$hasClearableAlertingNotifications$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationListViewModel.this.dumpWhileCollecting(activeNotificationsInteractor.hasClearableAlertingNotifications, "hasClearableAlertingNotifications");
            }
        });
        this.hasNonClearableSilentNotifications$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$hasNonClearableSilentNotifications$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationListViewModel.this.dumpWhileCollecting(activeNotificationsInteractor.hasNonClearableSilentNotifications, "hasNonClearableSilentNotifications");
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$topHeadsUpRow$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$pinnedHeadsUpRows$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(EmptySet.INSTANCE);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$headsUpAnimationsEnabled$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel$hasPinnedHeadsUpRow$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            }
        });
    }
}
