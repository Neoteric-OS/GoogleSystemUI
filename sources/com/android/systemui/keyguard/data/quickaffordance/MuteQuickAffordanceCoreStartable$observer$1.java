package com.android.systemui.keyguard.data.quickaffordance;

import androidx.lifecycle.Observer;
import kotlin.Function;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MuteQuickAffordanceCoreStartable$observer$1 implements Observer, FunctionAdapter {
    public final /* synthetic */ MuteQuickAffordanceCoreStartable $tmp0;

    public MuteQuickAffordanceCoreStartable$observer$1(MuteQuickAffordanceCoreStartable muteQuickAffordanceCoreStartable) {
        this.$tmp0 = muteQuickAffordanceCoreStartable;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Observer) && (obj instanceof FunctionAdapter)) {
            return getFunctionDelegate().equals(((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    public final Function getFunctionDelegate() {
        return new FunctionReferenceImpl(1, this.$tmp0, MuteQuickAffordanceCoreStartable.class, "updateLastNonSilentRingerMode", "updateLastNonSilentRingerMode(I)V", 0);
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        int intValue = ((Number) obj).intValue();
        MuteQuickAffordanceCoreStartable muteQuickAffordanceCoreStartable = this.$tmp0;
        muteQuickAffordanceCoreStartable.getClass();
        BuildersKt.launch$default(muteQuickAffordanceCoreStartable.coroutineScope, muteQuickAffordanceCoreStartable.backgroundDispatcher, null, new MuteQuickAffordanceCoreStartable$updateLastNonSilentRingerMode$1(intValue, muteQuickAffordanceCoreStartable, null), 2);
    }
}
