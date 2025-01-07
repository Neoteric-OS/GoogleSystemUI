package com.android.systemui.qs.ui.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.qs.QSContainerImpl;
import com.android.systemui.qs.QSImpl;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.qs.dagger.QSSceneComponent$Factory;
import com.android.systemui.qs.ui.adapter.CustomizerState;
import com.android.systemui.qs.ui.adapter.QSSceneAdapter$State;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.shade.shared.model.ShadeMode;
import com.android.systemui.util.kotlin.FlowKt;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import javax.inject.Provider;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSSceneAdapterImpl implements QSContainerController, Dumpable {
    public final StateFlowImpl _customizingState;
    public final StateFlowImpl _qsImpl;
    public final Function1 asyncLayoutInflaterFactory;
    public final SharedFlowImpl bottomNavBarSize;
    public final ConfigurationInteractor configurationInteractor;
    public final ReadonlyStateFlow customizerAnimationDuration;
    public final ReadonlyStateFlow customizerState;
    public final InterestingConfigChanges interestingChanges;
    public final ReadonlyStateFlow isCustomizerShowing;
    public final ReadonlyStateFlow isCustomizing;
    public final CoroutineDispatcher mainDispatcher;
    public final ReadonlyStateFlow qsImpl;
    public final Provider qsImplProvider;
    public final QSSceneComponent$Factory qsSceneComponentFactory;
    public final ReadonlyStateFlow qsView;
    public final StateFlowImpl state;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, AsyncLayoutInflater.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AsyncLayoutInflater((Context) obj);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ DisplayStateInteractor $displayStateInteractor;
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ QSSceneAdapterImpl this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$1$2, reason: invalid class name and collision with other inner class name */
            final /* synthetic */ class C01692 extends AdaptedFunctionReference implements Function3 {
                public static final C01692 INSTANCE = new C01692();

                public C01692() {
                    super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return new Pair((QSSceneAdapter$State) obj, (CustomizerState) obj2);
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$1$3, reason: invalid class name */
            public final class AnonymousClass3 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ QSSceneAdapterImpl this$0;

                public /* synthetic */ AnonymousClass3(QSSceneAdapterImpl qSSceneAdapterImpl, int i) {
                    this.$r8$classId = i;
                    this.this$0 = qSSceneAdapterImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    View view;
                    View view2;
                    switch (this.$r8$classId) {
                        case 0:
                            Pair pair = (Pair) obj;
                            QSSceneAdapter$State qSSceneAdapter$State = (QSSceneAdapter$State) pair.component1();
                            CustomizerState customizerState = (CustomizerState) pair.component2();
                            QSSceneAdapterImpl qSSceneAdapterImpl = this.this$0;
                            QSImpl qSImpl = (QSImpl) ((StateFlowImpl) qSSceneAdapterImpl.qsImpl.$$delegate_0).getValue();
                            if (qSImpl != null) {
                                QSSceneAdapter$State.Companion.getClass();
                                if (!Intrinsics.areEqual(qSSceneAdapter$State, QSSceneAdapter$State.Companion.QS) && customizerState.isShowing()) {
                                    qSImpl.mQSCustomizerController.hide(false);
                                }
                                QSSceneAdapterImpl.access$applyState(qSSceneAdapterImpl, qSImpl, qSSceneAdapter$State);
                            }
                            break;
                        case 1:
                            Configuration configuration = (Configuration) obj;
                            QSSceneAdapterImpl qSSceneAdapterImpl2 = this.this$0;
                            InterestingConfigChanges interestingConfigChanges = qSSceneAdapterImpl2.interestingChanges;
                            Configuration configuration2 = interestingConfigChanges.mLastConfiguration;
                            boolean z = (interestingConfigChanges.mFlags & configuration2.updateFrom(Configuration.generateDelta(configuration2, configuration))) != 0;
                            ReadonlyStateFlow readonlyStateFlow = qSSceneAdapterImpl2.qsImpl;
                            if (z) {
                                QSImpl qSImpl2 = (QSImpl) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
                                if (qSImpl2 != null && (view2 = qSImpl2.mRootView) != null) {
                                    Context context = view2.getContext();
                                    qSSceneAdapterImpl2.getClass();
                                    Object withContext = BuildersKt.withContext(qSSceneAdapterImpl2.mainDispatcher, new QSSceneAdapterImpl$inflate$2(qSSceneAdapterImpl2, context, null), continuation);
                                    if (withContext != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                        withContext = Unit.INSTANCE;
                                    }
                                    if (withContext == CoroutineSingletons.COROUTINE_SUSPENDED) {
                                    }
                                }
                            } else {
                                QSImpl qSImpl3 = (QSImpl) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
                                if (qSImpl3 != null) {
                                    qSImpl3.onConfigurationChanged(configuration);
                                }
                                QSImpl qSImpl4 = (QSImpl) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
                                if (qSImpl4 != null && (view = qSImpl4.mRootView) != null) {
                                    view.dispatchConfigurationChanged(configuration);
                                }
                            }
                            break;
                        default:
                            ShadeMode shadeMode = (ShadeMode) obj;
                            QSImpl qSImpl5 = (QSImpl) ((StateFlowImpl) this.this$0.qsImpl.$$delegate_0).getValue();
                            if (qSImpl5 != null) {
                                qSImpl5.setInSplitShade(Intrinsics.areEqual(shadeMode, ShadeMode.Split.INSTANCE));
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(QSSceneAdapterImpl qSSceneAdapterImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = qSSceneAdapterImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    QSSceneAdapterImpl qSSceneAdapterImpl = this.this$0;
                    SafeFlow sample = FlowKt.sample(qSSceneAdapterImpl.state, qSSceneAdapterImpl._customizingState, C01692.INSTANCE);
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, 0);
                    this.label = 1;
                    if (sample.collect(anonymousClass3, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$2, reason: invalid class name and collision with other inner class name */
        final class C01702 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ QSSceneAdapterImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01702(QSSceneAdapterImpl qSSceneAdapterImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = qSSceneAdapterImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01702(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01702) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    QSSceneAdapterImpl qSSceneAdapterImpl = this.this$0;
                    Flow flow = qSSceneAdapterImpl.configurationInteractor.configurationValues;
                    AnonymousClass1.AnonymousClass3 anonymousClass3 = new AnonymousClass1.AnonymousClass3(qSSceneAdapterImpl, 1);
                    this.label = 1;
                    if (flow.collect(anonymousClass3, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ QSSceneAdapterImpl this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$3$2, reason: invalid class name and collision with other inner class name */
            final /* synthetic */ class C01712 extends AdaptedFunctionReference implements Function3 {
                public static final C01712 INSTANCE = new C01712();

                public C01712() {
                    super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return new Pair(new Integer(((Number) obj).intValue()), (QSImpl) obj2);
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$3$3, reason: invalid class name and collision with other inner class name */
            public final class C01723 implements FlowCollector {
                public static final C01723 INSTANCE = new C01723(0);
                public static final C01723 INSTANCE$1 = new C01723(1);
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C01723(int i) {
                    this.$r8$classId = i;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            Pair pair = (Pair) obj;
                            QSImpl qSImpl = (QSImpl) pair.getSecond();
                            int intValue = ((Number) pair.getFirst()).intValue();
                            QSCustomizer qSCustomizer = (QSCustomizer) qSImpl.mQSCustomizerController.mView;
                            RecyclerView recyclerView = qSCustomizer.mRecyclerView;
                            recyclerView.setPadding(recyclerView.getPaddingLeft(), qSCustomizer.mRecyclerView.getPaddingTop(), qSCustomizer.mRecyclerView.getPaddingRight(), intValue);
                            break;
                        default:
                            ((QSImpl) ((Pair) obj).getSecond()).mIsSmallScreen = !((Boolean) r3.getFirst()).booleanValue();
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(QSSceneAdapterImpl qSSceneAdapterImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = qSSceneAdapterImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    QSSceneAdapterImpl qSSceneAdapterImpl = this.this$0;
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(qSSceneAdapterImpl.bottomNavBarSize, new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(qSSceneAdapterImpl.qsImpl), C01712.INSTANCE);
                    C01723 c01723 = C01723.INSTANCE;
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(c01723, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ ShadeInteractor $shadeInteractor;
            int label;
            final /* synthetic */ QSSceneAdapterImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(ShadeInteractor shadeInteractor, QSSceneAdapterImpl qSSceneAdapterImpl, Continuation continuation) {
                super(2, continuation);
                this.$shadeInteractor = shadeInteractor;
                this.this$0 = qSSceneAdapterImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$shadeInteractor, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow shadeMode = ((ShadeInteractorImpl) this.$shadeInteractor).$$delegate_1.getShadeMode();
                    AnonymousClass1.AnonymousClass3 anonymousClass3 = new AnonymousClass1.AnonymousClass3(this.this$0, 2);
                    this.label = 1;
                    if (shadeMode.collect(anonymousClass3, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ DisplayStateInteractor $displayStateInteractor;
            int label;
            final /* synthetic */ QSSceneAdapterImpl this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$2$5$2, reason: invalid class name and collision with other inner class name */
            final /* synthetic */ class C01732 extends AdaptedFunctionReference implements Function3 {
                public static final C01732 INSTANCE = new C01732();

                public C01732() {
                    super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return new Pair(bool, (QSImpl) obj2);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(DisplayStateInteractor displayStateInteractor, QSSceneAdapterImpl qSSceneAdapterImpl, Continuation continuation) {
                super(2, continuation);
                this.$displayStateInteractor = displayStateInteractor;
                this.this$0 = qSSceneAdapterImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$displayStateInteractor, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((DisplayStateInteractorImpl) this.$displayStateInteractor).isLargeScreen, new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.this$0.qsImpl), C01732.INSTANCE);
                    AnonymousClass3.C01723 c01723 = AnonymousClass3.C01723.INSTANCE$1;
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(c01723, this) == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ShadeInteractor shadeInteractor, DisplayStateInteractor displayStateInteractor, Continuation continuation) {
            super(2, continuation);
            this.$shadeInteractor = shadeInteractor;
            this.$displayStateInteractor = displayStateInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = QSSceneAdapterImpl.this.new AnonymousClass2(this.$shadeInteractor, this.$displayStateInteractor, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(QSSceneAdapterImpl.this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new C01702(QSSceneAdapterImpl.this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(QSSceneAdapterImpl.this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$shadeInteractor, QSSceneAdapterImpl.this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$displayStateInteractor, QSSceneAdapterImpl.this, null), 3);
            return Unit.INSTANCE;
        }
    }

    public QSSceneAdapterImpl(QSSceneComponent$Factory qSSceneComponent$Factory, Provider provider, ShadeInteractor shadeInteractor, DisplayStateInteractor displayStateInteractor, DumpManager dumpManager, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, ConfigurationInteractor configurationInteractor, Function1 function1) {
        this.qsSceneComponentFactory = qSSceneComponent$Factory;
        this.qsImplProvider = provider;
        this.mainDispatcher = coroutineDispatcher;
        this.configurationInteractor = configurationInteractor;
        this.asyncLayoutInflaterFactory = function1;
        this.bottomNavBarSize = SharedFlowKt.MutableSharedFlow$default(0, 1, BufferOverflow.DROP_OLDEST, 1);
        this.state = StateFlowKt.MutableStateFlow(QSSceneAdapter$State.CLOSED.INSTANCE);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(CustomizerState.Hidden.INSTANCE);
        this._customizingState = MutableStateFlow;
        final ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(MutableStateFlow);
        final int i = 0;
        kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.qs.ui.adapter.CustomizerState r5 = (com.android.systemui.qs.ui.adapter.CustomizerState) r5
                        boolean r5 = r5.isCustomizing()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        ((StateFlowImpl) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(((CustomizerState) readonlyStateFlow.getValue()).isCustomizing()));
        final int i2 = 1;
        this.isCustomizerShowing = kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.qs.ui.adapter.CustomizerState r5 = (com.android.systemui.qs.ui.adapter.CustomizerState) r5
                        boolean r5 = r5.isCustomizing()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        ((StateFlowImpl) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(((CustomizerState) readonlyStateFlow.getValue()).isShowing()));
        final int i3 = 2;
        Flow flow = new Flow() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.qs.ui.adapter.CustomizerState r5 = (com.android.systemui.qs.ui.adapter.CustomizerState) r5
                        boolean r5 = r5.isCustomizing()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        ((StateFlowImpl) readonlyStateFlow).collect(new QSSceneAdapterImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Object value = readonlyStateFlow.getValue();
        CustomizerState.Animating animating = value instanceof CustomizerState.Animating ? (CustomizerState.Animating) value : null;
        kotlinx.coroutines.flow.FlowKt.stateIn(flow, coroutineScope, WhileSubscribed$default, Integer.valueOf(animating != null ? (int) animating.getAnimationDuration() : 0));
        final StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._qsImpl = MutableStateFlow2;
        this.qsImpl = new ReadonlyStateFlow(MutableStateFlow2);
        final int i4 = 3;
        Flow flow2 = new Flow() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.qs.ui.adapter.CustomizerState r5 = (com.android.systemui.qs.ui.adapter.CustomizerState) r5
                        boolean r5 = r5.isCustomizing()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        ((ReadonlyStateFlow) MutableStateFlow2).collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        ((ReadonlyStateFlow) MutableStateFlow2).collect(new QSSceneAdapterImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        ((ReadonlyStateFlow) MutableStateFlow2).collect(new QSSceneAdapterImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        ((StateFlowImpl) MutableStateFlow2).collect(new QSSceneAdapterImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedWhileSubscribed WhileSubscribed$default2 = SharingStarted.Companion.WhileSubscribed$default(3);
        QSImpl qSImpl = (QSImpl) MutableStateFlow2.getValue();
        kotlinx.coroutines.flow.FlowKt.stateIn(flow2, coroutineScope, WhileSubscribed$default2, qSImpl != null ? qSImpl.mRootView : null);
        this.interestingChanges = new InterestingConfigChanges(-1073741820);
        dumpManager.registerDumpable(this);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(shadeInteractor, displayStateInteractor, null), 3);
    }

    public static final void access$applyState(QSSceneAdapterImpl qSSceneAdapterImpl, QSImpl qSImpl, QSSceneAdapter$State qSSceneAdapter$State) {
        qSSceneAdapterImpl.getClass();
        qSImpl.setQsVisible(qSSceneAdapter$State.isVisible());
        qSImpl.setExpanded(qSSceneAdapter$State.isVisible() && ((Number) qSSceneAdapter$State.getExpansion().invoke()).floatValue() > 0.0f);
        qSImpl.setListening(qSSceneAdapter$State.isVisible());
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        int i;
        printWriter.println("Last state: " + this.state.getValue());
        printWriter.println("CustomizerState: " + this._customizingState.getValue());
        QSImpl qSImpl = (QSImpl) ((StateFlowImpl) this.qsImpl.$$delegate_0).getValue();
        printWriter.println("QQS height: " + (qSImpl != null ? qSImpl.mContainer.mHeader.getHeight() : 0));
        QSImpl qSImpl2 = (QSImpl) ((StateFlowImpl) this.qsImpl.$$delegate_0).getValue();
        if (qSImpl2 != null) {
            QSContainerImpl qSContainerImpl = qSImpl2.mContainer;
            i = qSContainerImpl.mQSCustomizer.isCustomizing() ? qSContainerImpl.mQSCustomizer.getMeasuredHeight() : qSContainerImpl.mQSPanel.getMeasuredHeight();
        } else {
            i = 0;
        }
        printWriter.println("QS height: " + i);
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerAnimating(boolean z) {
        Object value;
        StateFlowImpl stateFlowImpl = this._customizingState;
        if (!(stateFlowImpl.getValue() instanceof CustomizerState.Animating) || z) {
            return;
        }
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, ((CustomizerState) value) instanceof CustomizerState.AnimatingIntoCustomizer ? CustomizerState.Showing.INSTANCE : CustomizerState.Hidden.INSTANCE));
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerShowing(boolean z) {
        setCustomizerShowing(z, 0L);
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerShowing(boolean z, long j) {
        StateFlowImpl stateFlowImpl;
        Object value;
        do {
            stateFlowImpl = this._customizingState;
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, z ? j > 0 ? new CustomizerState.AnimatingIntoCustomizer(j) : CustomizerState.Showing.INSTANCE : j > 0 ? new CustomizerState.AnimatingOutOfCustomizer(j) : CustomizerState.Hidden.INSTANCE));
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setDetailShowing(boolean z) {
    }

    public QSSceneAdapterImpl(DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, ShadeInteractor shadeInteractor, DisplayStateInteractor displayStateInteractor, DumpManager dumpManager, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, ConfigurationInteractor configurationInteractor) {
        this(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, switchingProvider, shadeInteractor, displayStateInteractor, dumpManager, coroutineDispatcher, coroutineScope, configurationInteractor, AnonymousClass1.INSTANCE);
    }
}
