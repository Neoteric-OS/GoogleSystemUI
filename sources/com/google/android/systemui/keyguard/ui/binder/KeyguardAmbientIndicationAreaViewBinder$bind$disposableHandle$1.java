package com.google.android.systemui.keyguard.ui.binder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.keyguard.shared.AmbientIndicationMusic;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
    final /* synthetic */ AodAlphaViewModel $aodAlphaViewModel;
    final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
        final /* synthetic */ AodAlphaViewModel $aodAlphaViewModel;
        final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02751 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ AodAlphaViewModel $aodAlphaViewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02761 implements FlowCollector {
                public final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C02761(AmbientIndicationContainer ambientIndicationContainer, int i) {
                    this.$r8$classId = i;
                    this.$ambientIndicationArea = ambientIndicationContainer;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            float floatValue = ((Number) obj).floatValue();
                            int i = floatValue == 0.0f ? 4 : 0;
                            AmbientIndicationContainer ambientIndicationContainer = this.$ambientIndicationArea;
                            ambientIndicationContainer.setImportantForAccessibility(i);
                            ambientIndicationContainer.setAlpha(floatValue);
                            break;
                        case 1:
                            this.$ambientIndicationArea.setTranslationX(((Number) obj).floatValue());
                            break;
                        case 2:
                            this.$ambientIndicationArea.setTranslationY(((Number) obj).floatValue());
                            break;
                        case 3:
                            AmbientIndicationMusic ambientIndicationMusic = (AmbientIndicationMusic) obj;
                            if (ambientIndicationMusic == null) {
                                this.$ambientIndicationArea.setAmbientMusic(null, null, null, 0, false, null);
                            } else {
                                this.$ambientIndicationArea.setAmbientMusic(ambientIndicationMusic.text, ambientIndicationMusic.openIntent, ambientIndicationMusic.favoritingIntent, ambientIndicationMusic.iconOverride.intValue(), ambientIndicationMusic.skipUnlock.equals(Boolean.TRUE), ambientIndicationMusic.iconDescription);
                            }
                            break;
                        case 4:
                            String str = (String) obj;
                            AmbientIndicationContainer ambientIndicationContainer2 = this.$ambientIndicationArea;
                            if (!Objects.equals(ambientIndicationContainer2.mReverseChargingMessage, str) || ambientIndicationContainer2.mWirelessChargingMessage != null) {
                                ambientIndicationContainer2.mWirelessChargingMessage = null;
                                ambientIndicationContainer2.mReverseChargingMessage = str;
                                ambientIndicationContainer2.updatePill();
                            }
                            break;
                        default:
                            String str2 = (String) obj;
                            AmbientIndicationContainer ambientIndicationContainer3 = this.$ambientIndicationArea;
                            if (!Objects.equals(ambientIndicationContainer3.mWirelessChargingMessage, str2) || ambientIndicationContainer3.mReverseChargingMessage != null) {
                                ambientIndicationContainer3.mWirelessChargingMessage = str2;
                                ambientIndicationContainer3.mReverseChargingMessage = null;
                                ambientIndicationContainer3.updatePill();
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02751(AodAlphaViewModel aodAlphaViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$aodAlphaViewModel = aodAlphaViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02751(this.$aodAlphaViewModel, this.$ambientIndicationArea, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02751) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$aodAlphaViewModel.alpha;
                    C02761 c02761 = new C02761(this.$ambientIndicationArea, 0);
                    this.label = 1;
                    if (safeFlow.collect(c02761, this) == coroutineSingletons) {
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
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardAmbientIndicationViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$ambientIndicationArea, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.indicationAreaTranslationX;
                    C02751.C02761 c02761 = new C02751.C02761(this.$ambientIndicationArea, 1);
                    this.label = 1;
                    if (flow.collect(c02761, this) == coroutineSingletons) {
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
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardAmbientIndicationViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$ambientIndicationArea, continuation);
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
                    Flow flow = this.$viewModel.indicationAreaTranslationY;
                    C02751.C02761 c02761 = new C02751.C02761(this.$ambientIndicationArea, 2);
                    this.label = 1;
                    if (flow.collect(c02761, this) == coroutineSingletons) {
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
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardAmbientIndicationViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$ambientIndicationArea, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.ambientIndicationMusicState;
                C02751.C02761 c02761 = new C02751.C02761(this.$ambientIndicationArea, 3);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c02761, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardAmbientIndicationViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$viewModel, this.$ambientIndicationArea, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.reverseChargingMessage;
                C02751.C02761 c02761 = new C02751.C02761(this.$ambientIndicationArea, 4);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c02761, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.keyguard.ui.binder.KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ AmbientIndicationContainer $ambientIndicationArea;
            final /* synthetic */ KeyguardAmbientIndicationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AmbientIndicationContainer ambientIndicationContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardAmbientIndicationViewModel;
                this.$ambientIndicationArea = ambientIndicationContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$viewModel, this.$ambientIndicationArea, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.wirelessChargingMessage;
                C02751.C02761 c02761 = new C02751.C02761(this.$ambientIndicationArea, 5);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c02761, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AodAlphaViewModel aodAlphaViewModel, AmbientIndicationContainer ambientIndicationContainer, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, Continuation continuation) {
            super(2, continuation);
            this.$aodAlphaViewModel = aodAlphaViewModel;
            this.$ambientIndicationArea = ambientIndicationContainer;
            this.$viewModel = keyguardAmbientIndicationViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$aodAlphaViewModel, this.$ambientIndicationArea, this.$viewModel, continuation);
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
            BuildersKt.launch$default(coroutineScope, null, null, new C02751(this.$aodAlphaViewModel, this.$ambientIndicationArea, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$ambientIndicationArea, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$ambientIndicationArea, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$ambientIndicationArea, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$ambientIndicationArea, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$ambientIndicationArea, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1(AodAlphaViewModel aodAlphaViewModel, AmbientIndicationContainer ambientIndicationContainer, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, Continuation continuation) {
        super(3, continuation);
        this.$aodAlphaViewModel = aodAlphaViewModel;
        this.$ambientIndicationArea = ambientIndicationContainer;
        this.$viewModel = keyguardAmbientIndicationViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1 keyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1 = new KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1(this.$aodAlphaViewModel, this.$ambientIndicationArea, this.$viewModel, (Continuation) obj3);
        keyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1.L$0 = (LifecycleOwner) obj;
        return keyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$aodAlphaViewModel, this.$ambientIndicationArea, this.$viewModel, null);
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
