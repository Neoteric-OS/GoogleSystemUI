package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SwipeToSceneNode$multiPointerDraggableNode$2 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Orientation orientation;
        long j = ((Offset) obj).packedValue;
        SwipeToSceneNode swipeToSceneNode = (SwipeToSceneNode) this.receiver;
        int ordinal = swipeToSceneNode._draggableHandler.orientation.ordinal();
        if (ordinal == 0) {
            orientation = Orientation.Horizontal;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            orientation = Orientation.Vertical;
        }
        return Boolean.valueOf(!SwipeToSceneNode.shouldEnableSwipes(swipeToSceneNode._draggableHandler.layoutImpl.contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(), orientation) && swipeToSceneNode._draggableHandler.m728xd2531d7e(new Offset(j)));
    }
}
