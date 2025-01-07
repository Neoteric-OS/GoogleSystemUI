package com.android.systemui.education.data.repository;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences$Key;
import com.android.systemui.education.data.model.EduDeviceConnectionTime;
import java.time.Instant;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UserContextualEducationRepository$updateEduDeviceConnectionTime$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $transform;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UserContextualEducationRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserContextualEducationRepository$updateEduDeviceConnectionTime$2(UserContextualEducationRepository userContextualEducationRepository, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userContextualEducationRepository;
        this.$transform = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        UserContextualEducationRepository$updateEduDeviceConnectionTime$2 userContextualEducationRepository$updateEduDeviceConnectionTime$2 = new UserContextualEducationRepository$updateEduDeviceConnectionTime$2(this.this$0, this.$transform, continuation);
        userContextualEducationRepository$updateEduDeviceConnectionTime$2.L$0 = obj;
        return userContextualEducationRepository$updateEduDeviceConnectionTime$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UserContextualEducationRepository$updateEduDeviceConnectionTime$2 userContextualEducationRepository$updateEduDeviceConnectionTime$2 = (UserContextualEducationRepository$updateEduDeviceConnectionTime$2) create((MutablePreferences) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        userContextualEducationRepository$updateEduDeviceConnectionTime$2.invokeSuspend(unit);
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
        EduDeviceConnectionTime eduDeviceConnectionTime = (EduDeviceConnectionTime) this.$transform.invoke(UserContextualEducationRepository.access$getEduDeviceConnectionTime(this.this$0, mutablePreferences));
        UserContextualEducationRepository userContextualEducationRepository = this.this$0;
        Instant instant = eduDeviceConnectionTime.keyboardFirstConnectionTime;
        userContextualEducationRepository.getClass();
        UserContextualEducationRepository.access$setInstant(userContextualEducationRepository, mutablePreferences, instant, new Preferences$Key("KEYBOARD_FIRST_CONNECTION_TIME"));
        UserContextualEducationRepository userContextualEducationRepository2 = this.this$0;
        Instant instant2 = eduDeviceConnectionTime.touchpadFirstConnectionTime;
        userContextualEducationRepository2.getClass();
        UserContextualEducationRepository.access$setInstant(userContextualEducationRepository2, mutablePreferences, instant2, new Preferences$Key("TOUCHPAD_FIRST_CONNECTION_TIME"));
        return Unit.INSTANCE;
    }
}
