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
final class MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $lastRingerMode;
    int label;
    final /* synthetic */ MuteQuickAffordanceCoreStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1(int i, MuteQuickAffordanceCoreStartable muteQuickAffordanceCoreStartable, Continuation continuation) {
        super(2, continuation);
        this.$lastRingerMode = i;
        this.this$0 = muteQuickAffordanceCoreStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1(this.$lastRingerMode, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1 muteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1 = (MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        muteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.$lastRingerMode != 0) {
            MuteQuickAffordanceCoreStartable muteQuickAffordanceCoreStartable = this.this$0;
            ((UserFileManagerImpl) muteQuickAffordanceCoreStartable.userFileManager).getSharedPreferences$1(((UserTrackerImpl) muteQuickAffordanceCoreStartable.userTracker).getUserId(), "quick_affordance_mute_ringer_mode_cache").edit().putInt("key_last_non_silent_ringer_mode", this.$lastRingerMode).apply();
        }
        return Unit.INSTANCE;
    }
}
