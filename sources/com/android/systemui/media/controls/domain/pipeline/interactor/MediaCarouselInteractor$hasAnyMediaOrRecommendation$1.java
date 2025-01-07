package com.android.systemui.media.controls.domain.pipeline.interactor;

import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaCarouselInteractor$hasAnyMediaOrRecommendation$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MediaCarouselInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaCarouselInteractor$hasAnyMediaOrRecommendation$1(MediaCarouselInteractor mediaCarouselInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mediaCarouselInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MediaCarouselInteractor$hasAnyMediaOrRecommendation$1 mediaCarouselInteractor$hasAnyMediaOrRecommendation$1 = new MediaCarouselInteractor$hasAnyMediaOrRecommendation$1(this.this$0, (Continuation) obj3);
        mediaCarouselInteractor$hasAnyMediaOrRecommendation$1.L$0 = (Map) obj;
        mediaCarouselInteractor$hasAnyMediaOrRecommendation$1.L$1 = (SmartspaceMediaData) obj2;
        return mediaCarouselInteractor$hasAnyMediaOrRecommendation$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Map map = (Map) this.L$0;
        SmartspaceMediaData smartspaceMediaData = (SmartspaceMediaData) this.L$1;
        if (map.isEmpty()) {
            this.this$0.mediaFlags.isPersistentSsCardEnabled();
            if (!smartspaceMediaData.isActive || !smartspaceMediaData.isValid()) {
                z = false;
                return Boolean.valueOf(z);
            }
        }
        z = true;
        return Boolean.valueOf(z);
    }
}
