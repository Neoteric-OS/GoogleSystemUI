package com.android.systemui.biometrics.ui.binder;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.domain.interactor.BiometricStatusInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor;
import com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor$special$$inlined$map$2;
import com.android.systemui.biometrics.shared.model.AuthenticationReason;
import com.android.systemui.biometrics.shared.model.LottieCallback;
import com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor;
import com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel;
import com.android.systemui.util.kotlin.FlowKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewBinder$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SideFpsOverlayViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$start$1$1$2, reason: invalid class name */
        final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function4 {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            public AnonymousClass2() {
                super(4, Triple.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", 4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                Boolean bool2 = (Boolean) obj3;
                bool2.booleanValue();
                return new Triple((AuthenticationReason) obj, bool, bool2);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$start$1$1$4, reason: invalid class name */
        final /* synthetic */ class AnonymousClass4 extends AdaptedFunctionReference implements Function3 {
            public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

            public AnonymousClass4() {
                super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                return new Pair((Triple) obj, bool);
            }
        }

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    Unit unit = Unit.INSTANCE;
                    if (booleanValue) {
                        SideFpsOverlayViewBinder sideFpsOverlayViewBinder = (SideFpsOverlayViewBinder) this.this$0;
                        Object collect = FlowKt.sample(kotlinx.coroutines.flow.FlowKt.combine(((BiometricStatusInteractorImpl) sideFpsOverlayViewBinder.biometricStatusInteractor.get()).sfpsAuthenticationReason, ((DeviceEntrySideFpsOverlayInteractor) sideFpsOverlayViewBinder.deviceEntrySideFpsOverlayInteractor.get()).showIndicatorForDeviceEntry, ((SideFpsProgressBarViewModel) sideFpsOverlayViewBinder.sideFpsProgressBarViewModel.get()).isVisible, AnonymousClass2.INSTANCE), ((DisplayStateInteractorImpl) ((DisplayStateInteractor) sideFpsOverlayViewBinder.displayStateInteractor.get())).isInRearDisplayMode, AnonymousClass4.INSTANCE).collect(new AnonymousClass1(1, sideFpsOverlayViewBinder), continuation);
                        if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                    }
                    break;
                case 1:
                    Pair pair = (Pair) obj;
                    Triple triple = (Triple) pair.component1();
                    boolean booleanValue2 = ((Boolean) pair.component2()).booleanValue();
                    AuthenticationReason authenticationReason = (AuthenticationReason) triple.component1();
                    boolean booleanValue3 = ((Boolean) triple.component2()).booleanValue();
                    boolean booleanValue4 = ((Boolean) triple.component3()).booleanValue();
                    StringBuilder sb = new StringBuilder("systemServerAuthReason = ");
                    sb.append(authenticationReason);
                    sb.append(", showIndicatorForDeviceEntry = ");
                    sb.append(booleanValue3);
                    sb.append(", progressBarIsVisible = ");
                    CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, booleanValue4, "SideFpsOverlayViewBinder");
                    if (!booleanValue2) {
                        SideFpsOverlayViewBinder sideFpsOverlayViewBinder2 = (SideFpsOverlayViewBinder) this.this$0;
                        if (booleanValue4) {
                            SideFpsOverlayViewBinder.access$hide(sideFpsOverlayViewBinder2);
                        } else if (!Intrinsics.areEqual(authenticationReason, AuthenticationReason.NotRunning.INSTANCE)) {
                            SideFpsOverlayViewBinder.access$show(sideFpsOverlayViewBinder2);
                        } else if (booleanValue3) {
                            SideFpsOverlayViewBinder.access$show(sideFpsOverlayViewBinder2);
                        } else {
                            SideFpsOverlayViewBinder.access$hide(sideFpsOverlayViewBinder2);
                        }
                    }
                    break;
                default:
                    final List list = (List) obj;
                    final LottieAnimationView lottieAnimationView = (LottieAnimationView) this.this$0;
                    lottieAnimationView.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() { // from class: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinderKt$addOverlayDynamicColor$1
                        @Override // com.airbnb.lottie.LottieOnCompositionLoadedListener
                        public final void onCompositionLoaded(LottieComposition lottieComposition) {
                            Iterator it = list.iterator();
                            while (true) {
                                boolean hasNext = it.hasNext();
                                LottieAnimationView lottieAnimationView2 = lottieAnimationView;
                                if (!hasNext) {
                                    lottieAnimationView2.resumeAnimation();
                                    return;
                                } else {
                                    final LottieCallback lottieCallback = (LottieCallback) it.next();
                                    lottieAnimationView2.addValueCallback(lottieCallback.keypath, LottieProperty.COLOR_FILTER, new SimpleLottieValueCallback() { // from class: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinderKt$addOverlayDynamicColor$1.1
                                        @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                                        public final Object getValue() {
                                            LottieCallback lottieCallback2 = LottieCallback.this;
                                            return new PorterDuffColorFilter(lottieCallback2.color, PorterDuff.Mode.SRC_ATOP);
                                        }
                                    });
                                }
                            }
                        }
                    });
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideFpsOverlayViewBinder$start$1(SideFpsOverlayViewBinder sideFpsOverlayViewBinder, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sideFpsOverlayViewBinder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SideFpsOverlayViewBinder$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SideFpsOverlayViewBinder$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SideFpsSensorInteractor$special$$inlined$map$2 sideFpsSensorInteractor$special$$inlined$map$2 = ((SideFpsSensorInteractor) this.this$0.sfpsSensorInteractor.get()).isAvailable;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0, this.this$0);
            this.label = 1;
            if (sideFpsSensorInteractor$special$$inlined$map$2.collect(anonymousClass1, this) == coroutineSingletons) {
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
