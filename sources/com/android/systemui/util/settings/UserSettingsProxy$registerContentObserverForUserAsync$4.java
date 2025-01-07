package com.android.systemui.util.settings;

import android.database.ContentObserver;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSettingsProxy$registerContentObserverForUserAsync$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $name;
    final /* synthetic */ boolean $notifyForDescendants;
    final /* synthetic */ ContentObserver $settingsObserver;
    final /* synthetic */ int $userHandle;
    int label;
    final /* synthetic */ UserSettingsProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSettingsProxy$registerContentObserverForUserAsync$4(UserSettingsProxy userSettingsProxy, String str, boolean z, ContentObserver contentObserver, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSettingsProxy;
        this.$name = str;
        this.$notifyForDescendants = z;
        this.$settingsObserver = contentObserver;
        this.$userHandle = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSettingsProxy$registerContentObserverForUserAsync$4(this.this$0, this.$name, this.$notifyForDescendants, this.$settingsObserver, this.$userHandle, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UserSettingsProxy$registerContentObserverForUserAsync$4 userSettingsProxy$registerContentObserverForUserAsync$4 = (UserSettingsProxy$registerContentObserverForUserAsync$4) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        userSettingsProxy$registerContentObserverForUserAsync$4.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserSettingsProxy userSettingsProxy = this.this$0;
        userSettingsProxy.registerContentObserverForUserSync(userSettingsProxy.getUriFor(this.$name), this.$notifyForDescendants, this.$settingsObserver, this.$userHandle);
        return Unit.INSTANCE;
    }
}
