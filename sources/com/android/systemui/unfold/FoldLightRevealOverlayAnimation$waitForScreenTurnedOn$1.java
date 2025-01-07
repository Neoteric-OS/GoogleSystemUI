package com.android.systemui.unfold;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = foldLightRevealOverlayAnimation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FoldLightRevealOverlayAnimation.access$waitForScreenTurnedOn(this.this$0, this);
    }
}
