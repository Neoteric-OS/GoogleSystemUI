package com.android.systemui.deviceentry.domain.interactor;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.wm.shell.R;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OccludingAppDeviceEntryInteractor {
    public final Context context;
    public final OccludingAppDeviceEntryInteractor$special$$inlined$map$2 fingerprintLockoutEvents;
    public final OccludingAppDeviceEntryInteractor$special$$inlined$map$2 fingerprintUnlockSuccessEvents;
    public final Flow keyguardOccludedByApp;
    public final ChannelFlowTransformLatest message;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ KeyguardInteractor $keyguardInteractor;
        final /* synthetic */ PowerInteractor $powerInteractor;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$1$2, reason: invalid class name */
        final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function3 {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            public AnonymousClass2() {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PowerInteractor powerInteractor, KeyguardInteractor keyguardInteractor, Continuation continuation) {
            super(2, continuation);
            this.$powerInteractor = powerInteractor;
            this.$keyguardInteractor = keyguardInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return OccludingAppDeviceEntryInteractor.this.new AnonymousClass1(this.$powerInteractor, this.$keyguardInteractor, continuation);
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
                SafeFlow sample = FlowKt.sample(OccludingAppDeviceEntryInteractor.this.fingerprintUnlockSuccessEvents, new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.$powerInteractor.isInteractive, this.$keyguardInteractor.isDreaming, AnonymousClass2.INSTANCE));
                final OccludingAppDeviceEntryInteractor occludingAppDeviceEntryInteractor = OccludingAppDeviceEntryInteractor.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor.1.3
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Pair pair = (Pair) obj2;
                        boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
                        boolean booleanValue2 = ((Boolean) pair.component2()).booleanValue();
                        if (booleanValue && !booleanValue2) {
                            Context context = OccludingAppDeviceEntryInteractor.this.context;
                            Intent intent = new Intent("android.intent.action.MAIN");
                            intent.addCategory("android.intent.category.HOME");
                            intent.setFlags(268435456);
                            context.startActivity(intent);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (sample.collect(flowCollector, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ ActivityStarter $activityStarter;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ActivityStarter activityStarter, Continuation continuation) {
            super(2, continuation);
            this.$activityStarter = activityStarter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return OccludingAppDeviceEntryInteractor.this.new AnonymousClass2(this.$activityStarter, continuation);
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
                final OccludingAppDeviceEntryInteractor occludingAppDeviceEntryInteractor = OccludingAppDeviceEntryInteractor.this;
                OccludingAppDeviceEntryInteractor$special$$inlined$map$2 occludingAppDeviceEntryInteractor$special$$inlined$map$2 = occludingAppDeviceEntryInteractor.fingerprintLockoutEvents;
                final ActivityStarter activityStarter = this.$activityStarter;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        final OccludingAppDeviceEntryInteractor occludingAppDeviceEntryInteractor2 = occludingAppDeviceEntryInteractor;
                        ActivityStarter.this.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor.2.1.1
                            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                            public final boolean onDismiss() {
                                Context context = OccludingAppDeviceEntryInteractor.this.context;
                                Intent intent = new Intent("android.intent.action.MAIN");
                                intent.addCategory("android.intent.category.HOME");
                                intent.setFlags(268435456);
                                context.startActivity(intent);
                                return false;
                            }

                            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                            public final boolean willRunAnimationOnKeyguard() {
                                return false;
                            }
                        }, null, false);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (occludingAppDeviceEntryInteractor$special$$inlined$map$2.collect(flowCollector, this) == coroutineSingletons) {
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

    public OccludingAppDeviceEntryInteractor(BiometricMessageInteractor biometricMessageInteractor, DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository, KeyguardInteractor keyguardInteractor, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor, CoroutineScope coroutineScope, Context context, ActivityStarter activityStarter, PowerInteractor powerInteractor, CommunalSceneInteractor communalSceneInteractor) {
        int i = 0;
        int i2 = 1;
        this.context = context;
        MutableStateFlow mutableStateFlow = keyguardInteractor.isKeyguardOccluded;
        ReadonlyStateFlow readonlyStateFlow = primaryBouncerInteractor.isShowing;
        ReadonlyStateFlow readonlyStateFlow2 = communalSceneInteractor.isIdleOnCommunal;
        Flow distinctUntilChanged = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new OccludingAppDeviceEntryInteractor$special$$inlined$map$2(i2, new Flow[]{mutableStateFlow, keyguardInteractor.isKeyguardShowing, readonlyStateFlow, alternateBouncerInteractor.isVisible, keyguardInteractor.isDozing, readonlyStateFlow2}));
        DeviceEntryFingerprintAuthRepositoryImpl deviceEntryFingerprintAuthRepositoryImpl = (DeviceEntryFingerprintAuthRepositoryImpl) deviceEntryFingerprintAuthRepository;
        Flow authenticationStatus = deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus();
        EmptyFlow emptyFlow = EmptyFlow.INSTANCE;
        this.fingerprintUnlockSuccessEvents = new OccludingAppDeviceEntryInteractor$special$$inlined$map$2(i, new OccludingAppDeviceEntryInteractor$special$$inlined$filter$1(kotlinx.coroutines.flow.FlowKt.transformLatest(distinctUntilChanged, new OccludingAppDeviceEntryInteractor$ifKeyguardOccludedByApp$$inlined$flatMapLatest$1(null, authenticationStatus, emptyFlow)), i));
        this.fingerprintLockoutEvents = new OccludingAppDeviceEntryInteractor$special$$inlined$map$2(3, new OccludingAppDeviceEntryInteractor$special$$inlined$filter$1(kotlinx.coroutines.flow.FlowKt.transformLatest(distinctUntilChanged, new OccludingAppDeviceEntryInteractor$ifKeyguardOccludedByApp$$inlined$flatMapLatest$1(null, deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus(), emptyFlow)), i2));
        this.message = kotlinx.coroutines.flow.FlowKt.transformLatest(distinctUntilChanged, new OccludingAppDeviceEntryInteractor$ifKeyguardOccludedByApp$$inlined$flatMapLatest$1(null, new OccludingAppDeviceEntryInteractor$special$$inlined$map$2(2, biometricMessageInteractor.fingerprintMessage), new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null)));
        if (context.getResources().getBoolean(R.bool.config_goToHomeFromOccludedApps)) {
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(powerInteractor, keyguardInteractor, null), 3);
        }
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(activityStarter, null), 3);
    }
}
