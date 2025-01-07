package com.android.systemui.media.controls.domain.pipeline;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaDataLoader$loadMediaDataForResumptionInBackground$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaDataLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaDataLoader$loadMediaDataForResumptionInBackground$1(MediaDataLoader mediaDataLoader, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = mediaDataLoader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MediaDataLoader.access$loadMediaDataForResumptionInBackground(this.this$0, 0, null, null, null, null, null, null, null, this);
    }
}
