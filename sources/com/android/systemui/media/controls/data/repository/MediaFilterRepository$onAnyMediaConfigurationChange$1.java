package com.android.systemui.media.controls.data.repository;

import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaFilterRepository$onAnyMediaConfigurationChange$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaFilterRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaFilterRepository$onAnyMediaConfigurationChange$1(MediaFilterRepository mediaFilterRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaFilterRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MediaFilterRepository$onAnyMediaConfigurationChange$1 mediaFilterRepository$onAnyMediaConfigurationChange$1 = new MediaFilterRepository$onAnyMediaConfigurationChange$1(this.this$0, continuation);
        mediaFilterRepository$onAnyMediaConfigurationChange$1.L$0 = obj;
        return mediaFilterRepository$onAnyMediaConfigurationChange$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaFilterRepository$onAnyMediaConfigurationChange$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.media.controls.data.repository.MediaFilterRepository$onAnyMediaConfigurationChange$1$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final MediaFilterRepository mediaFilterRepository = this.this$0;
            final ?? r1 = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.media.controls.data.repository.MediaFilterRepository$onAnyMediaConfigurationChange$1$callback$1
                @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                public final void onDensityOrFontScaleChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }

                @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                public final void onLocaleListChanged() {
                    MediaFilterRepository mediaFilterRepository2 = mediaFilterRepository;
                    if (Intrinsics.areEqual(mediaFilterRepository2.locale, mediaFilterRepository2.applicationContext.getResources().getConfiguration().getLocales().get(0))) {
                        return;
                    }
                    mediaFilterRepository2.locale = mediaFilterRepository2.applicationContext.getResources().getConfiguration().getLocales().get(0);
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }

                @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                public final void onThemeChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }

                @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                public final void onUiModeChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }
            };
            ((ConfigurationControllerImpl) mediaFilterRepository.configurationController).addCallback(r1);
            ProducerCoroutine producerCoroutine = (ProducerCoroutine) producerScope;
            producerCoroutine.mo1790trySendJP2dKIU(unit);
            final MediaFilterRepository mediaFilterRepository2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.media.controls.data.repository.MediaFilterRepository$onAnyMediaConfigurationChange$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((ConfigurationControllerImpl) MediaFilterRepository.this.configurationController).removeCallback(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerCoroutine, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
