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
final class ClearCallingRepositoryImpl$isClearCallingAvailable$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ClearCallingRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClearCallingRepositoryImpl$isClearCallingAvailable$2(ClearCallingRepositoryImpl clearCallingRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clearCallingRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClearCallingRepositoryImpl$isClearCallingAvailable$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClearCallingRepositoryImpl$isClearCallingAvailable$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DcServiceClientImpl dcServiceClientImpl = this.this$0.dcServiceClient;
        Log.i("DcServiceClientImpl", "Query Clear Calling availability through DC-Service");
        Bundle bundle = new Bundle();
        try {
            ContentProviderClient acquireUnstableContentProviderClient = dcServiceClientImpl.contentResolver.acquireUnstableContentProviderClient(DcServiceClientImpl.PROXY_AUTHORITY);
            Intrinsics.checkNotNull(acquireUnstableContentProviderClient);
            try {
                Bundle call = acquireUnstableContentProviderClient.call("method_is_clear_calling_available", null, bundle);
                Intrinsics.checkNotNull(call);
                AutoCloseableKt.closeFinally(acquireUnstableContentProviderClient, null);
                z = call.getBoolean("clear_calling_available");
            } finally {
            }
        } catch (Exception unused) {
            Log.w("DcServiceClientImpl", "isClearCallingAvailable: error happens when calling DcService.");
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
