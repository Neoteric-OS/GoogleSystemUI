package com.android.compose.nestedscroll;

import androidx.compose.ui.unit.Velocity;
import com.android.compose.ui.util.SpaceVectorConverter;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$3 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $canStartPostFling;
    final /* synthetic */ SpaceVectorConverter $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$3(SpaceVectorConverter spaceVectorConverter, Function1 function1) {
        super(1);
        this.$canStartPostFling = function1;
        this.$this_with = spaceVectorConverter;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return (Boolean) this.$canStartPostFling.invoke(Float.valueOf(this.$this_with.mo734toFloatTH1AsA0(((Velocity) obj).packedValue)));
    }
}
