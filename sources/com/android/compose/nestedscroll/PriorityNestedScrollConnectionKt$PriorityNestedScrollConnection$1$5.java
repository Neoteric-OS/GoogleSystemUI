package com.android.compose.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import com.android.compose.ui.util.SpaceVectorConverter;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$5 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $onScroll;
    final /* synthetic */ SpaceVectorConverter $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$5(SpaceVectorConverter spaceVectorConverter, Function1 function1) {
        super(1);
        this.$this_with = spaceVectorConverter;
        this.$onScroll = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        long j = ((Offset) obj).packedValue;
        SpaceVectorConverter spaceVectorConverter = this.$this_with;
        return new Offset(spaceVectorConverter.mo736toOffsettuRUvjQ(((Number) this.$onScroll.invoke(Float.valueOf(spaceVectorConverter.mo735toFloatk4lQ0M(j)))).floatValue()));
    }
}
