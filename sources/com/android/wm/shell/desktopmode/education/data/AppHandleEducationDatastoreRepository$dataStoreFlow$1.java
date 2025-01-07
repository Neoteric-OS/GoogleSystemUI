package com.android.wm.shell.desktopmode.education.data;

import android.util.Log;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AppHandleEducationDatastoreRepository$dataStoreFlow$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AppHandleEducationDatastoreRepository$dataStoreFlow$1 appHandleEducationDatastoreRepository$dataStoreFlow$1 = new AppHandleEducationDatastoreRepository$dataStoreFlow$1(3, (Continuation) obj3);
        appHandleEducationDatastoreRepository$dataStoreFlow$1.L$0 = (Throwable) obj2;
        Unit unit = Unit.INSTANCE;
        appHandleEducationDatastoreRepository$dataStoreFlow$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Throwable th = (Throwable) this.L$0;
        if (!(th instanceof IOException)) {
            throw th;
        }
        Log.e("AppHandleEducationDatastoreRepository", "Error in reading app handle education related data from datastore, data is stored in a file named app_handle_education.pb", th);
        return Unit.INSTANCE;
    }
}
