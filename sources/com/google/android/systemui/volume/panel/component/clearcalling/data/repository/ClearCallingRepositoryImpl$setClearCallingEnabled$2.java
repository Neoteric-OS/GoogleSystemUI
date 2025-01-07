package com.google.android.systemui.volume.panel.component.clearcalling.data.repository;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.util.Log;
import com.google.android.settingslib.dcservice.DcServiceClientImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ClearCallingRepositoryImpl$setClearCallingEnabled$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $state;
    int label;
    final /* synthetic */ ClearCallingRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClearCallingRepositoryImpl$setClearCallingEnabled$2(ClearCallingRepositoryImpl clearCallingRepositoryImpl, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clearCallingRepositoryImpl;
        this.$state = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClearCallingRepositoryImpl$setClearCallingEnabled$2(this.this$0, this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ClearCallingRepositoryImpl$setClearCallingEnabled$2 clearCallingRepositoryImpl$setClearCallingEnabled$2 = (ClearCallingRepositoryImpl$setClearCallingEnabled$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        clearCallingRepositoryImpl$setClearCallingEnabled$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DcServiceClientImpl dcServiceClientImpl = this.this$0.dcServiceClient;
        boolean z = this.$state;
        Log.i("DcServiceClientImpl", "Set Clear Calling state to " + z + " through DC-Service");
        Bundle bundle = new Bundle();
        bundle.putBoolean("clear_calling_enable_state", z);
        Boolean bool = null;
        try {
            ContentProviderClient acquireUnstableContentProviderClient = dcServiceClientImpl.contentResolver.acquireUnstableContentProviderClient(DcServiceClientImpl.PROXY_AUTHORITY);
            Intrinsics.checkNotNull(acquireUnstableContentProviderClient);
            try {
                Bundle call = acquireUnstableContentProviderClient.call("method_set_clear_calling_state", null, bundle);
                Intrinsics.checkNotNull(call);
                AutoCloseableKt.closeFinally(acquireUnstableContentProviderClient, null);
                bool = Boolean.valueOf(call.getBoolean("clear_calling_enable_state"));
            } finally {
            }
        } catch (Exception unused) {
            Log.w("DcServiceClientImpl", "setClearCallingEnableState: error happens when calling DcService.");
        }
        this.this$0.mutableClearCallingStateUpdate.setValue(bool);
        return Unit.INSTANCE;
    }
}
