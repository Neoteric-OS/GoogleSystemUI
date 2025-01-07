package com.android.compose.animation.scene;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SwipeToSceneNode$multiPointerDraggableNode$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SwipeToSceneNode swipeToSceneNode = (SwipeToSceneNode) this.receiver;
        DragControllerImpl dragControllerImpl = swipeToSceneNode._draggableHandler.dragController;
        boolean z = true;
        if ((dragControllerImpl == null || !dragControllerImpl.isDrivingTransition()) && !SwipeToSceneNode.shouldEnableSwipes(swipeToSceneNode._draggableHandler.layoutImpl.contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(), swipeToSceneNode.multiPointerDraggableNode.orientation)) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
