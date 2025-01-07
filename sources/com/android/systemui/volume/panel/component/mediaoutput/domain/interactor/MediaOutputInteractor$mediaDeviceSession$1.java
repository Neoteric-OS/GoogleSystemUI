package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MediaOutputInteractor$mediaDeviceSession$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaOutputInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaOutputInteractor$mediaDeviceSession$1(MediaOutputInteractor mediaOutputInteractor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = mediaOutputInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MediaOutputInteractor.access$mediaDeviceSession(this.this$0, null, this);
    }
}
