package com.android.systemui.inputdevice.tutorial.data.repository;

import androidx.datastore.preferences.core.MutablePreferences;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TutorialSchedulerRepository$clearDataStore$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TutorialSchedulerRepository$clearDataStore$2 tutorialSchedulerRepository$clearDataStore$2 = new TutorialSchedulerRepository$clearDataStore$2(2, continuation);
        tutorialSchedulerRepository$clearDataStore$2.L$0 = obj;
        return tutorialSchedulerRepository$clearDataStore$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TutorialSchedulerRepository$clearDataStore$2 tutorialSchedulerRepository$clearDataStore$2 = (TutorialSchedulerRepository$clearDataStore$2) create((MutablePreferences) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        tutorialSchedulerRepository$clearDataStore$2.invokeSuspend(unit);
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
        mutablePreferences.checkNotFrozen$datastore_preferences_core();
        mutablePreferences.preferencesMap.clear();
        return Unit.INSTANCE;
    }
}
