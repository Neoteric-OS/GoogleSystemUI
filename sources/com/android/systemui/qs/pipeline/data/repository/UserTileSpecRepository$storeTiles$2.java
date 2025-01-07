package com.android.systemui.qs.pipeline.data.repository;

import android.provider.Settings;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserTileSpecRepository$storeTiles$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $forUser;
    final /* synthetic */ String $toStore;
    int label;
    final /* synthetic */ UserTileSpecRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserTileSpecRepository$storeTiles$2(UserTileSpecRepository userTileSpecRepository, String str, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userTileSpecRepository;
        this.$toStore = str;
        this.$forUser = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserTileSpecRepository$storeTiles$2(this.this$0, this.$toStore, this.$forUser, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserTileSpecRepository$storeTiles$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SecureSettings secureSettings = this.this$0.secureSettings;
        SecureSettingsImpl secureSettingsImpl = (SecureSettingsImpl) secureSettings;
        return Boolean.valueOf(Settings.Secure.putStringForUser(secureSettingsImpl.mContentResolver, "sysui_qs_tiles", this.$toStore, null, false, secureSettingsImpl.getRealUserHandle(this.$forUser), true));
    }
}
