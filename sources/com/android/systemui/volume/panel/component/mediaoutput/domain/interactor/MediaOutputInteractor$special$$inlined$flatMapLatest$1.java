package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import android.media.session.MediaController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MediaOutputInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaOutputInteractor$special$$inlined$flatMapLatest$1(MediaOutputInteractor mediaOutputInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mediaOutputInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MediaOutputInteractor$special$$inlined$flatMapLatest$1 mediaOutputInteractor$special$$inlined$flatMapLatest$1 = new MediaOutputInteractor$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        mediaOutputInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        mediaOutputInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return mediaOutputInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            List<MediaController> list = (List) this.L$1;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (MediaController mediaController : list) {
                MediaOutputInteractor mediaOutputInteractor = this.this$0;
                if (mediaController == null) {
                    mediaOutputInteractor.getClass();
                    flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
                } else {
                    MediaControllerInteractorImpl mediaControllerInteractorImpl = mediaOutputInteractor.mediaControllerInteractor;
                    mediaControllerInteractorImpl.getClass();
                    flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new MediaOutputInteractor$stateChanges$2(mediaController, null), new MediaOutputInteractor$special$$inlined$map$1(FlowConflatedKt.conflatedCallbackFlow(new MediaControllerInteractorImpl$stateChanges$1(mediaController, mediaControllerInteractorImpl, null)), mediaController, 2));
                }
                arrayList.add(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1);
            }
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new MediaOutputInteractor$activeMediaControllers$1$3(list, null), new MediaOutputInteractor$special$$inlined$map$1(FlowKt.merge(arrayList), list, 1));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
