package com.android.systemui.keyguard.ui.binder;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.kotlin.WithPrev;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardBlueprintViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardClockViewModel $clockViewModel;
    final /* synthetic */ ConstraintLayout $constraintLayout;
    final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
    final /* synthetic */ KeyguardBlueprintViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ KeyguardClockViewModel $clockViewModel;
        final /* synthetic */ ConstraintLayout $constraintLayout;
        final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
        final /* synthetic */ KeyguardBlueprintViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01081 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardClockViewModel $clockViewModel;
            final /* synthetic */ ConstraintLayout $constraintLayout;
            final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
            final /* synthetic */ KeyguardBlueprintViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01091 implements FlowCollector {
                public final /* synthetic */ KeyguardClockViewModel $clockViewModel;
                public final /* synthetic */ ConstraintLayout $constraintLayout;
                public final /* synthetic */ int $r8$classId = 1;
                public final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
                public final /* synthetic */ KeyguardBlueprintViewModel $viewModel;

                public C01091(KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConstraintLayout constraintLayout, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel) {
                    this.$viewModel = keyguardBlueprintViewModel;
                    this.$constraintLayout = constraintLayout;
                    this.$clockViewModel = keyguardClockViewModel;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            WithPrev withPrev = (WithPrev) obj;
                            final KeyguardBlueprint keyguardBlueprint = (KeyguardBlueprint) withPrev.previousValue;
                            final KeyguardBlueprint keyguardBlueprint2 = (KeyguardBlueprint) withPrev.newValue;
                            final IntraBlueprintTransition.Config config = IntraBlueprintTransition.Config.DEFAULT;
                            IntraBlueprintTransition intraBlueprintTransition = new IntraBlueprintTransition(config, this.$clockViewModel);
                            final ConstraintLayout constraintLayout = this.$constraintLayout;
                            final KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
                            this.$viewModel.runTransition(constraintLayout, intraBlueprintTransition, config, new Function0() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder.bind.1.1.1.1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    List list;
                                    KeyguardBlueprint keyguardBlueprint3 = KeyguardBlueprint.this;
                                    ConstraintLayout constraintLayout2 = constraintLayout;
                                    KeyguardBlueprint keyguardBlueprint4 = keyguardBlueprint;
                                    List list2 = config.rebuildSections;
                                    keyguardBlueprint3.getClass();
                                    Iterator it = list2.iterator();
                                    while (it.hasNext()) {
                                        ((KeyguardSection) it.next()).onRebuildBegin();
                                    }
                                    if (keyguardBlueprint4 == null || (list = keyguardBlueprint4.getSections()) == null) {
                                        list = EmptyList.INSTANCE;
                                    }
                                    Set subtract = CollectionsKt.subtract(CollectionsKt.intersect(keyguardBlueprint3.getSections(), list), list2);
                                    Iterator it2 = CollectionsKt.subtract(list, subtract).iterator();
                                    while (it2.hasNext()) {
                                        ((KeyguardSection) it2.next()).removeViews(constraintLayout2);
                                    }
                                    for (KeyguardSection keyguardSection : CollectionsKt.subtract(keyguardBlueprint3.getSections(), subtract)) {
                                        keyguardSection.addViews(constraintLayout2);
                                        keyguardSection.bindData(constraintLayout2);
                                    }
                                    Iterator it3 = list2.iterator();
                                    while (it3.hasNext()) {
                                        ((KeyguardSection) it3.next()).onRebuildEnd();
                                    }
                                    ConstraintSet constraintSet = new ConstraintSet();
                                    ConstraintLayout constraintLayout3 = constraintLayout;
                                    KeyguardBlueprint keyguardBlueprint5 = KeyguardBlueprint.this;
                                    constraintSet.clone(constraintLayout3);
                                    ConstraintSet.Layout layout = new ConstraintSet.Layout();
                                    for (int i : constraintSet.getKnownIds()) {
                                        constraintSet.getConstraint(i).layout.copyFrom(layout);
                                    }
                                    Iterator it4 = keyguardBlueprint5.getSections().iterator();
                                    while (it4.hasNext()) {
                                        ((KeyguardSection) it4.next()).applyConstraints(constraintSet);
                                    }
                                    constraintSet.applyTo(constraintLayout);
                                    return Unit.INSTANCE;
                                }
                            });
                            break;
                        default:
                            final IntraBlueprintTransition.Config config2 = (IntraBlueprintTransition.Config) obj;
                            KeyguardBlueprintViewModel keyguardBlueprintViewModel = this.$viewModel;
                            final KeyguardBlueprint keyguardBlueprint3 = (KeyguardBlueprint) keyguardBlueprintViewModel.blueprint.getValue();
                            final KeyguardClockViewModel keyguardClockViewModel2 = this.$clockViewModel;
                            IntraBlueprintTransition intraBlueprintTransition2 = new IntraBlueprintTransition(config2, keyguardClockViewModel2);
                            final ConstraintLayout constraintLayout2 = this.$constraintLayout;
                            keyguardBlueprintViewModel.runTransition(constraintLayout2, intraBlueprintTransition2, config2, new Function0() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder$bind$1$1$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    KeyguardBlueprint keyguardBlueprint4 = KeyguardBlueprint.this;
                                    ConstraintLayout constraintLayout3 = constraintLayout2;
                                    List<KeyguardSection> list = config2.rebuildSections;
                                    keyguardBlueprint4.getClass();
                                    if (!list.isEmpty()) {
                                        Iterator it = list.iterator();
                                        while (it.hasNext()) {
                                            ((KeyguardSection) it.next()).onRebuildBegin();
                                        }
                                        Iterator it2 = list.iterator();
                                        while (it2.hasNext()) {
                                            ((KeyguardSection) it2.next()).removeViews(constraintLayout3);
                                        }
                                        for (KeyguardSection keyguardSection : list) {
                                            keyguardSection.addViews(constraintLayout3);
                                            keyguardSection.bindData(constraintLayout3);
                                        }
                                        Iterator it3 = list.iterator();
                                        while (it3.hasNext()) {
                                            ((KeyguardSection) it3.next()).onRebuildEnd();
                                        }
                                    }
                                    ConstraintSet constraintSet = new ConstraintSet();
                                    ConstraintLayout constraintLayout4 = constraintLayout2;
                                    KeyguardBlueprint keyguardBlueprint5 = KeyguardBlueprint.this;
                                    constraintSet.clone(constraintLayout4);
                                    Iterator it4 = keyguardBlueprint5.getSections().iterator();
                                    while (it4.hasNext()) {
                                        ((KeyguardSection) it4.next()).applyConstraints(constraintSet);
                                    }
                                    constraintSet.applyTo(constraintLayout2);
                                    return Unit.INSTANCE;
                                }
                            });
                            break;
                    }
                    return Unit.INSTANCE;
                }

                public C01091(KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConstraintLayout constraintLayout) {
                    this.$clockViewModel = keyguardClockViewModel;
                    this.$viewModel = keyguardBlueprintViewModel;
                    this.$constraintLayout = constraintLayout;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01081(ConstraintLayout constraintLayout, KeyguardBlueprintViewModel keyguardBlueprintViewModel, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBlueprintViewModel;
                this.$clockViewModel = keyguardClockViewModel;
                this.$smartspaceViewModel = keyguardSmartspaceViewModel;
                this.$constraintLayout = constraintLayout;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01081(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01081) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow pairwise = FlowKt.pairwise(this.$viewModel.blueprint, null);
                    C01091 c01091 = new C01091(this.$clockViewModel, this.$smartspaceViewModel, this.$viewModel, this.$constraintLayout);
                    this.label = 1;
                    if (pairwise.collect(c01091, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardClockViewModel $clockViewModel;
            final /* synthetic */ ConstraintLayout $constraintLayout;
            final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
            final /* synthetic */ KeyguardBlueprintViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ConstraintLayout constraintLayout, KeyguardBlueprintViewModel keyguardBlueprintViewModel, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBlueprintViewModel;
                this.$constraintLayout = constraintLayout;
                this.$clockViewModel = keyguardClockViewModel;
                this.$smartspaceViewModel = keyguardSmartspaceViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                KeyguardBlueprintViewModel keyguardBlueprintViewModel = this.$viewModel;
                SharedFlowImpl sharedFlowImpl = keyguardBlueprintViewModel.refreshTransition;
                C01081.C01091 c01091 = new C01081.C01091(keyguardBlueprintViewModel, this.$constraintLayout, this.$clockViewModel, this.$smartspaceViewModel);
                this.label = 1;
                sharedFlowImpl.collect(c01091, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConstraintLayout constraintLayout, KeyguardBlueprintViewModel keyguardBlueprintViewModel, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardBlueprintViewModel;
            this.$clockViewModel = keyguardClockViewModel;
            this.$smartspaceViewModel = keyguardSmartspaceViewModel;
            this.$constraintLayout = constraintLayout;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01081(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardBlueprintViewBinder$bind$1(ConstraintLayout constraintLayout, KeyguardBlueprintViewModel keyguardBlueprintViewModel, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardBlueprintViewModel;
        this.$clockViewModel = keyguardClockViewModel;
        this.$smartspaceViewModel = keyguardSmartspaceViewModel;
        this.$constraintLayout = constraintLayout;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardBlueprintViewModel keyguardBlueprintViewModel = this.$viewModel;
        KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.$smartspaceViewModel;
        KeyguardBlueprintViewBinder$bind$1 keyguardBlueprintViewBinder$bind$1 = new KeyguardBlueprintViewBinder$bind$1(this.$constraintLayout, keyguardBlueprintViewModel, keyguardClockViewModel, keyguardSmartspaceViewModel, (Continuation) obj3);
        keyguardBlueprintViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return keyguardBlueprintViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$constraintLayout, this.$viewModel, this.$clockViewModel, this.$smartspaceViewModel, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
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
