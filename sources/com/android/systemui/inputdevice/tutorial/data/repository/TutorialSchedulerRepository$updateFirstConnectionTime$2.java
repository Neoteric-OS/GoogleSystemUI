package com.android.systemui.inputdevice.tutorial.data.repository;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences$Key;
import java.time.Instant;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TutorialSchedulerRepository$updateFirstConnectionTime$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ DeviceType $device;
    final /* synthetic */ Instant $time;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TutorialSchedulerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TutorialSchedulerRepository$updateFirstConnectionTime$2(TutorialSchedulerRepository tutorialSchedulerRepository, DeviceType deviceType, Instant instant, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tutorialSchedulerRepository;
        this.$device = deviceType;
        this.$time = instant;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TutorialSchedulerRepository$updateFirstConnectionTime$2 tutorialSchedulerRepository$updateFirstConnectionTime$2 = new TutorialSchedulerRepository$updateFirstConnectionTime$2(this.this$0, this.$device, this.$time, continuation);
        tutorialSchedulerRepository$updateFirstConnectionTime$2.L$0 = obj;
        return tutorialSchedulerRepository$updateFirstConnectionTime$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TutorialSchedulerRepository$updateFirstConnectionTime$2 tutorialSchedulerRepository$updateFirstConnectionTime$2 = (TutorialSchedulerRepository$updateFirstConnectionTime$2) create((MutablePreferences) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        tutorialSchedulerRepository$updateFirstConnectionTime$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
        TutorialSchedulerRepository tutorialSchedulerRepository = this.this$0;
        DeviceType deviceType = this.$device;
        KProperty[] kPropertyArr = TutorialSchedulerRepository.$$delegatedProperties;
        tutorialSchedulerRepository.getClass();
        mutablePreferences.setUnchecked$datastore_preferences_core(new Preferences$Key(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(deviceType.name(), "_CONNECT_TIME")), new Long(this.$time.getEpochSecond()));
        return Unit.INSTANCE;
    }
}
