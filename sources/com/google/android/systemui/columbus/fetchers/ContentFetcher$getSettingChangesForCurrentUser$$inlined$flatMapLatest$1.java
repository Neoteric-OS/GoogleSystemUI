package com.google.android.systemui.columbus.fetchers;

import android.net.Uri;
import android.os.UserHandle;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $settingUri$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1(Continuation continuation, ContentFetcher contentFetcher, Uri uri) {
        super(3, continuation);
        this.this$0 = contentFetcher;
        this.$settingUri$inlined = uri;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 = new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$settingUri$inlined);
        contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.L$1 = obj2;
        return contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            UserHandle userHandle = (UserHandle) this.L$1;
            ContentFetcher contentFetcher = this.this$0;
            Uri uri = this.$settingUri$inlined;
            int identifier = userHandle.getIdentifier();
            contentFetcher.getClass();
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ContentFetcher$getSettingChangesForCurrentUser$1$1(userHandle, null), FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new ContentFetcher$getSettingChangesForUser$1(contentFetcher, uri, identifier, null)), contentFetcher.bgDispatcher));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
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
