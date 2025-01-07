package com.android.systemui.keyguard.data.quickaffordance;

import android.content.Context;
import android.media.AudioManager;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MuteQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AudioManager audioManager;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Context context;
    public final CoroutineScope coroutineScope;
    public final Flow lockScreenState;
    public int previousNonSilentMode = 2;
    public final RingerModeTrackerImpl ringerModeTracker;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;

    public MuteQuickAffordanceConfig(Context context, UserTracker userTracker, UserFileManager userFileManager, RingerModeTrackerImpl ringerModeTrackerImpl, AudioManager audioManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        this.context = context;
        this.userTracker = userTracker;
        this.userFileManager = userFileManager;
        this.ringerModeTracker = ringerModeTrackerImpl;
        this.audioManager = audioManager;
        this.coroutineScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher2;
        final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new MuteQuickAffordanceConfig$lockScreenState$1(this, null), FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new MuteQuickAffordanceConfig$asFlow$1(ringerModeTrackerImpl.ringerModeInternal, null)), coroutineDispatcher))), new MuteQuickAffordanceConfig$lockScreenState$2(this, null), 0);
        this.lockScreenState = FlowKt.flowOn(new Flow() { // from class: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MuteQuickAffordanceConfig this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MuteQuickAffordanceConfig muteQuickAffordanceConfig) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = muteQuickAffordanceConfig;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto La1
                    L28:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L30:
                        kotlin.ResultKt.throwOnFailure(r8)
                        java.lang.Integer r7 = (java.lang.Integer) r7
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig r8 = r6.this$0
                        android.media.AudioManager r8 = r8.audioManager
                        boolean r8 = r8.isVolumeFixed()
                        r2 = 2131954585(0x7f130b99, float:1.9545673E38)
                        if (r8 == 0) goto L4f
                        com.android.systemui.keyguard.shared.quickaffordance.ActivationState$Inactive r7 = com.android.systemui.keyguard.shared.quickaffordance.ActivationState.Inactive.INSTANCE$1
                        java.lang.Integer r8 = new java.lang.Integer
                        r8.<init>(r2)
                        kotlin.Pair r2 = new kotlin.Pair
                        r2.<init>(r7, r8)
                        goto L74
                    L4f:
                        if (r7 != 0) goto L52
                        goto L68
                    L52:
                        int r7 = r7.intValue()
                        if (r7 != 0) goto L68
                        com.android.systemui.keyguard.shared.quickaffordance.ActivationState$Active r7 = com.android.systemui.keyguard.shared.quickaffordance.ActivationState.Active.INSTANCE
                        java.lang.Integer r8 = new java.lang.Integer
                        r2 = 2131954586(0x7f130b9a, float:1.9545675E38)
                        r8.<init>(r2)
                        kotlin.Pair r2 = new kotlin.Pair
                        r2.<init>(r7, r8)
                        goto L74
                    L68:
                        com.android.systemui.keyguard.shared.quickaffordance.ActivationState$Inactive r7 = com.android.systemui.keyguard.shared.quickaffordance.ActivationState.Inactive.INSTANCE
                        java.lang.Integer r8 = new java.lang.Integer
                        r8.<init>(r2)
                        kotlin.Pair r2 = new kotlin.Pair
                        r2.<init>(r7, r8)
                    L74:
                        java.lang.Object r7 = r2.component1()
                        com.android.systemui.keyguard.shared.quickaffordance.ActivationState r7 = (com.android.systemui.keyguard.shared.quickaffordance.ActivationState) r7
                        java.lang.Object r8 = r2.component2()
                        java.lang.Number r8 = (java.lang.Number) r8
                        int r8 = r8.intValue()
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$LockScreenState$Visible r2 = new com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$LockScreenState$Visible
                        com.android.systemui.common.shared.model.Icon$Resource r4 = new com.android.systemui.common.shared.model.Icon$Resource
                        com.android.systemui.common.shared.model.ContentDescription$Resource r5 = new com.android.systemui.common.shared.model.ContentDescription$Resource
                        r5.<init>(r8)
                        r8 = 2131232833(0x7f080841, float:1.8081786E38)
                        r4.<init>(r8, r5)
                        r2.<init>(r4, r7)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r2, r0)
                        if (r6 != r1) goto La1
                        return r1
                    La1:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineDispatcher2);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return "mute";
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return this.lockScreenState;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return R.drawable.ic_notifications_silence;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Object getPickerScreenState(Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new MuteQuickAffordanceConfig$getPickerScreenState$2(this, null), continuation);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        BuildersKt.launch$default(this.coroutineScope, this.backgroundDispatcher, null, new MuteQuickAffordanceConfig$onTriggered$1(this, null), 2);
        return KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.volume_ringer_status_silent);
    }
}
