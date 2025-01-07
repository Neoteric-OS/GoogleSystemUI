package com.android.systemui.util.settings;

import android.database.ContentObserver;
import android.net.Uri;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSettingsProxy$registerContentObserverForUserAsync$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Runnable $registered;
    final /* synthetic */ ContentObserver $settingsObserver;
    final /* synthetic */ Uri $uri;
    final /* synthetic */ int $userHandle;
    int label;
    final /* synthetic */ UserSettingsProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSettingsProxy$registerContentObserverForUserAsync$3(UserSettingsProxy userSettingsProxy, Uri uri, ContentObserver contentObserver, int i, Runnable runnable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSettingsProxy;
        this.$uri = uri;
        this.$settingsObserver = contentObserver;
        this.$userHandle = i;
        this.$registered = runnable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSettingsProxy$registerContentObserverForUserAsync$3(this.this$0, this.$uri, this.$settingsObserver, this.$userHandle, this.$registered, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UserSettingsProxy$registerContentObserverForUserAsync$3 userSettingsProxy$registerContentObserverForUserAsync$3 = (UserSettingsProxy$registerContentObserverForUserAsync$3) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        userSettingsProxy$registerContentObserverForUserAsync$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.registerContentObserverForUserSync(this.$uri, false, this.$settingsObserver, this.$userHandle);
        this.$registered.run();
        return Unit.INSTANCE;
    }
}
