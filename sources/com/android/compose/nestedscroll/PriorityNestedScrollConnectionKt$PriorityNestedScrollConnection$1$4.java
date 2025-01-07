package com.android.compose.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import com.android.compose.ui.util.SpaceVectorConverter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$4 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $onStart;
    final /* synthetic */ SpaceVectorConverter $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$4(SpaceVectorConverter spaceVectorConverter, Function1 function1) {
        super(1);
        this.$onStart = function1;
        this.$this_with = spaceVectorConverter;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.$onStart.invoke(Float.valueOf(this.$this_with.mo735toFloatk4lQ0M(((Offset) obj).packedValue)));
        return Unit.INSTANCE;
    }
}
