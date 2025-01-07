package com.google.android.systemui.volume.panel.component.clearcalling.data.repository;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.util.Log;
import com.google.android.settingslib.dcservice.DcServiceClientImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClearCallingRepositoryImpl {
    public final CoroutineContext backgroundCoroutineContext;
    public final ContextScope coroutineScope;
    public final DcServiceClientImpl dcServiceClient;
    public final StateFlowImpl mutableClearCallingStateUpdate = StateFlowKt.MutableStateFlow(null);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepositoryImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ClearCallingRepositoryImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ClearCallingRepositoryImpl clearCallingRepositoryImpl = ClearCallingRepositoryImpl.this;
            StateFlowImpl stateFlowImpl = clearCallingRepositoryImpl.mutableClearCallingStateUpdate;
            DcServiceClientImpl dcServiceClientImpl = clearCallingRepositoryImpl.dcServiceClient;
            Log.i("DcServiceClientImpl", "Query Clear Calling state through DC-Service");
            Bundle bundle = new Bundle();
            Boolean bool = null;
            try {
                ContentProviderClient acquireUnstableContentProviderClient = dcServiceClientImpl.contentResolver.acquireUnstableContentProviderClient(DcServiceClientImpl.PROXY_AUTHORITY);
                Intrinsics.checkNotNull(acquireUnstableContentProviderClient);
                try {
                    Bundle call = acquireUnstableContentProviderClient.call("method_get_clear_calling_state", null, bundle);
                    Intrinsics.checkNotNull(call);
                    AutoCloseableKt.closeFinally(acquireUnstableContentProviderClient, null);
                    bool = Boolean.valueOf(call.getBoolean("clear_calling_enable_state"));
                } finally {
                }
            } catch (Exception unused) {
                Log.w("DcServiceClientImpl", "getClearCallingEnableState: error happens when calling DcService.");
            }
            stateFlowImpl.setValue(bool);
            return Unit.INSTANCE;
        }
    }

    public ClearCallingRepositoryImpl(DcServiceClientImpl dcServiceClientImpl, ContextScope contextScope, CoroutineContext coroutineContext) {
        this.dcServiceClient = dcServiceClientImpl;
        this.backgroundCoroutineContext = coroutineContext;
        BuildersKt.launch$default(contextScope, coroutineContext, null, new AnonymousClass1(null), 2);
    }

    public final Object isClearCallingAvailable(Continuation continuation) {
        return BuildersKt.withContext(this.backgroundCoroutineContext, new ClearCallingRepositoryImpl$isClearCallingAvailable$2(this, null), continuation);
    }

    public final Object setClearCallingEnabled(boolean z, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundCoroutineContext, new ClearCallingRepositoryImpl$setClearCallingEnabled$2(this, z, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
