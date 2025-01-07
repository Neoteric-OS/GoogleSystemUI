package com.android.compose.nestedscroll;

import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.ui.util.SpaceVectorConverter;
import com.android.compose.ui.util.SpaceVectorConverterKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PriorityNestedScrollConnectionKt {
    public static final PriorityNestedScrollConnection PriorityNestedScrollConnection(Orientation orientation, Function2 function2, Function2 function22, Function1 function1, Function1 function12, boolean z, Function1 function13, Function1 function14, Function1 function15) {
        SpaceVectorConverter SpaceVectorConverter = SpaceVectorConverterKt.SpaceVectorConverter(orientation);
        return new PriorityNestedScrollConnection(new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$1(function2, SpaceVectorConverter), new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$2(function22, SpaceVectorConverter), new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$3(SpaceVectorConverter, function1), function12, z, new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$4(SpaceVectorConverter, function13), new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$5(SpaceVectorConverter, function14), new PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$6(SpaceVectorConverter, function15));
    }
}
