package com.android.compose.nestedscroll;

import androidx.compose.ui.unit.Velocity;
import com.android.compose.ui.util.SpaceVectorConverter;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$6 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $onStop;
    final /* synthetic */ SpaceVectorConverter $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$6(SpaceVectorConverter spaceVectorConverter, Function1 function1) {
        super(1);
        this.$this_with = spaceVectorConverter;
        this.$onStop = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        long j = ((Velocity) obj).packedValue;
        SpaceVectorConverter spaceVectorConverter = this.$this_with;
        return new Velocity(spaceVectorConverter.mo737toVelocityadjELrA(((Number) this.$onStop.invoke(Float.valueOf(spaceVectorConverter.mo734toFloatTH1AsA0(j)))).floatValue()));
    }
}
