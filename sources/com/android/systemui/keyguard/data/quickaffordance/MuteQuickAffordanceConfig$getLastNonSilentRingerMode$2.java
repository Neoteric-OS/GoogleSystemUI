package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MuteQuickAffordanceConfig$getLastNonSilentRingerMode$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MuteQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MuteQuickAffordanceConfig$getLastNonSilentRingerMode$2(MuteQuickAffordanceConfig muteQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = muteQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MuteQuickAffordanceConfig$getLastNonSilentRingerMode$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MuteQuickAffordanceConfig$getLastNonSilentRingerMode$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MuteQuickAffordanceConfig muteQuickAffordanceConfig = this.this$0;
        return new Integer(((UserFileManagerImpl) muteQuickAffordanceConfig.userFileManager).getSharedPreferences$1(((UserTrackerImpl) muteQuickAffordanceConfig.userTracker).getUserId(), "quick_affordance_mute_ringer_mode_cache").getInt("key_last_non_silent_ringer_mode", this.this$0.ringerModeTracker.ringerModeInternal.getValue().intValue()));
    }
}
