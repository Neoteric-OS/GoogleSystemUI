package com.android.systemui.communal.ui.compose;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.android.systemui.communal.ui.compose.extensions.PointerInputScopeExtKt;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.util.animation.UniqueObjectHostView;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalHubKt$Umo$1 implements PointerInputEventHandler {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BaseCommunalViewModel $viewModel;

    public /* synthetic */ CommunalHubKt$Umo$1(BaseCommunalViewModel baseCommunalViewModel, int i) {
        this.$r8$classId = i;
        this.$viewModel = baseCommunalViewModel;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        Object detectHorizontalDragGestures;
        switch (this.$r8$classId) {
            case 0:
                final BaseCommunalViewModel baseCommunalViewModel = this.$viewModel;
                detectHorizontalDragGestures = DragGestureDetectorKt.detectHorizontalDragGestures(pointerInputScope, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* synthetic */ Object invoke(Object obj) {
                        long j = ((Offset) obj).packedValue;
                        return Unit.INSTANCE;
                    }
                }, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$3
                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Object invoke() {
                        return Unit.INSTANCE;
                    }
                }, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$4
                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Object invoke() {
                        return Unit.INSTANCE;
                    }
                }, new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Umo$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        PointerInputChange pointerInputChange = (PointerInputChange) obj;
                        ((Number) obj2).floatValue();
                        pointerInputChange.consume();
                        long uptimeMillis = SystemClock.uptimeMillis();
                        long j = pointerInputChange.position;
                        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 2, Float.intBitsToFloat((int) (j >> 32)), Float.intBitsToFloat((int) (j & 4294967295L)), 0);
                        UniqueObjectHostView uniqueObjectHostView = BaseCommunalViewModel.this.mediaHost.hostView;
                        if (uniqueObjectHostView == null) {
                            uniqueObjectHostView = null;
                        }
                        uniqueObjectHostView.dispatchTouchEvent(obtain);
                        obtain.recycle();
                        return Unit.INSTANCE;
                    }
                }, continuation);
                if (detectHorizontalDragGestures != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 1:
                Object awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(continuation, new CommunalHubKt$CommunalHub$4$1$1(this.$viewModel, null));
                if (awaitPointerEventScope != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                final BaseCommunalViewModel baseCommunalViewModel2 = this.$viewModel;
                Object observeTaps$default = PointerInputScopeExtKt.observeTaps$default(pointerInputScope, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$3$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        long j = ((Offset) obj).packedValue;
                        BaseCommunalViewModel.this.onOpenEnableWorkProfileDialog();
                        return Unit.INSTANCE;
                    }
                }, continuation, 1);
                if (observeTaps$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
