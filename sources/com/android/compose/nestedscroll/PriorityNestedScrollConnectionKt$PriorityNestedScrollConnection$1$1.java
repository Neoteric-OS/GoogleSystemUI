package com.android.compose.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import com.android.compose.ui.util.SpaceVectorConverter;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$1 extends Lambda implements Function2 {
    final /* synthetic */ Function2 $canStartPreScroll;
    final /* synthetic */ SpaceVectorConverter $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$1(Function2 function2, SpaceVectorConverter spaceVectorConverter) {
        super(2);
        this.$canStartPreScroll = function2;
        this.$this_with = spaceVectorConverter;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return (Boolean) this.$canStartPreScroll.invoke(Float.valueOf(this.$this_with.mo735toFloatk4lQ0M(((Offset) obj).packedValue)), Float.valueOf(this.$this_with.mo735toFloatk4lQ0M(((Offset) obj2).packedValue)));
    }
}
