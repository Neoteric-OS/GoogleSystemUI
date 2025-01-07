package com.android.systemui.dreams;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.complication.ComplicationLayoutParams;
import com.android.systemui.dreams.ui.viewmodel.DreamViewModel$special$$inlined$filter$1;
import java.util.Iterator;
import java.util.function.Consumer;
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
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DreamOverlayAnimationsController$init$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DreamOverlayAnimationsController this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DreamOverlayAnimationsController this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00721 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ DreamOverlayAnimationsController this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C00731 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ DreamOverlayAnimationsController this$0;

                public /* synthetic */ C00731(DreamOverlayAnimationsController dreamOverlayAnimationsController, int i) {
                    this.$r8$classId = i;
                    this.this$0 = dreamOverlayAnimationsController;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            final float floatValue = ((Number) obj).floatValue();
                            final DreamOverlayAnimationsController dreamOverlayAnimationsController = this.this$0;
                            final int i = 0;
                            ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController.init.1.1.1.1.1
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj2) {
                                    switch (i) {
                                        case 0:
                                            DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController, floatValue, ((Number) obj2).intValue());
                                            break;
                                        case 1:
                                            int intValue = ((Number) obj2).intValue();
                                            DreamOverlayAnimationsController dreamOverlayAnimationsController2 = dreamOverlayAnimationsController;
                                            float f = floatValue;
                                            Iterator it = dreamOverlayAnimationsController2.mComplicationHostViewController.getViewsAtPosition(intValue).iterator();
                                            while (it.hasNext()) {
                                                ((View) it.next()).setTranslationX(f);
                                            }
                                            break;
                                        default:
                                            DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController, floatValue, ((Number) obj2).intValue(), true);
                                            break;
                                    }
                                }
                            });
                            break;
                        case 1:
                            final float floatValue2 = ((Number) obj).floatValue();
                            final DreamOverlayAnimationsController dreamOverlayAnimationsController2 = this.this$0;
                            final int i2 = 1;
                            ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController.init.1.1.1.1.1
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj2) {
                                    switch (i2) {
                                        case 0:
                                            DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController2, floatValue2, ((Number) obj2).intValue());
                                            break;
                                        case 1:
                                            int intValue = ((Number) obj2).intValue();
                                            DreamOverlayAnimationsController dreamOverlayAnimationsController22 = dreamOverlayAnimationsController2;
                                            float f = floatValue2;
                                            Iterator it = dreamOverlayAnimationsController22.mComplicationHostViewController.getViewsAtPosition(intValue).iterator();
                                            while (it.hasNext()) {
                                                ((View) it.next()).setTranslationX(f);
                                            }
                                            break;
                                        default:
                                            DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController2, floatValue2, ((Number) obj2).intValue(), true);
                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:
                            final float floatValue3 = ((Number) obj).floatValue();
                            final DreamOverlayAnimationsController dreamOverlayAnimationsController3 = this.this$0;
                            final int i3 = 2;
                            ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController.init.1.1.1.1.1
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj2) {
                                    switch (i3) {
                                        case 0:
                                            DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController3, floatValue3, ((Number) obj2).intValue());
                                            break;
                                        case 1:
                                            int intValue = ((Number) obj2).intValue();
                                            DreamOverlayAnimationsController dreamOverlayAnimationsController22 = dreamOverlayAnimationsController3;
                                            float f = floatValue3;
                                            Iterator it = dreamOverlayAnimationsController22.mComplicationHostViewController.getViewsAtPosition(intValue).iterator();
                                            while (it.hasNext()) {
                                                ((View) it.next()).setTranslationX(f);
                                            }
                                            break;
                                        default:
                                            DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController3, floatValue3, ((Number) obj2).intValue(), true);
                                            break;
                                    }
                                }
                            });
                            break;
                        default:
                            DreamOverlayStateController dreamOverlayStateController = this.this$0.mOverlayStateController;
                            dreamOverlayStateController.getClass();
                            dreamOverlayStateController.modifyState(1, 8);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00721(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = dreamOverlayAnimationsController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00721(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00721) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DreamOverlayAnimationsController dreamOverlayAnimationsController = this.this$0;
                    ChannelFlowTransformLatest channelFlowTransformLatest = dreamOverlayAnimationsController.dreamViewModel.dreamOverlayTranslationY;
                    C00731 c00731 = new C00731(dreamOverlayAnimationsController, 0);
                    this.label = 1;
                    if (channelFlowTransformLatest.collect(c00731, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ DreamOverlayAnimationsController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = dreamOverlayAnimationsController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
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
                    DreamOverlayAnimationsController dreamOverlayAnimationsController = this.this$0;
                    Flow flow = dreamOverlayAnimationsController.dreamViewModel.dreamOverlayTranslationX;
                    C00721.C00731 c00731 = new C00721.C00731(dreamOverlayAnimationsController, 1);
                    this.label = 1;
                    if (flow.collect(c00731, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ DreamOverlayAnimationsController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = dreamOverlayAnimationsController;
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
                    DreamOverlayAnimationsController dreamOverlayAnimationsController = this.this$0;
                    Flow flow = dreamOverlayAnimationsController.dreamViewModel.dreamOverlayAlpha;
                    C00721.C00731 c00731 = new C00721.C00731(dreamOverlayAnimationsController, 2);
                    this.label = 1;
                    if (flow.collect(c00731, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.dreams.DreamOverlayAnimationsController$init$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ DreamOverlayAnimationsController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = dreamOverlayAnimationsController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DreamOverlayAnimationsController dreamOverlayAnimationsController = this.this$0;
                    DreamViewModel$special$$inlined$filter$1 dreamViewModel$special$$inlined$filter$1 = dreamOverlayAnimationsController.dreamViewModel.transitionEnded;
                    C00721.C00731 c00731 = new C00721.C00731(dreamOverlayAnimationsController, 3);
                    this.label = 1;
                    if (dreamViewModel$special$$inlined$filter$1.collect(c00731, this) == coroutineSingletons) {
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
        public AnonymousClass1(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
            super(2, continuation);
            this.this$0 = dreamOverlayAnimationsController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
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
            BuildersKt.launch$default(coroutineScope, null, null, new C00721(this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DreamOverlayAnimationsController$init$1(DreamOverlayAnimationsController dreamOverlayAnimationsController, Continuation continuation) {
        super(3, continuation);
        this.this$0 = dreamOverlayAnimationsController;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DreamOverlayAnimationsController$init$1 dreamOverlayAnimationsController$init$1 = new DreamOverlayAnimationsController$init$1(this.this$0, (Continuation) obj3);
        dreamOverlayAnimationsController$init$1.L$0 = (LifecycleOwner) obj;
        return dreamOverlayAnimationsController$init$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
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
