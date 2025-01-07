package com.google.android.systemui.tips.data.repository;

import android.database.Cursor;
import com.google.android.setupwizard.deviceorigin.DeviceOrigin;
import com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SetupWizardRepositoryImpl$getSourceDeviceType$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SetupWizardRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupWizardRepositoryImpl$getSourceDeviceType$2(SetupWizardRepositoryImpl setupWizardRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizardRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizardRepositoryImpl$getSourceDeviceType$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SetupWizardRepositoryImpl$getSourceDeviceType$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Cursor cursor;
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            try {
                cursor = DeviceOrigin.getCursorForKey(this.this$0.applicationContext);
                if (cursor != null) {
                    try {
                        str = cursor.getString(0);
                        try {
                            cursor.close();
                        } catch (Exception unused) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                } else {
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception unused3) {
                        }
                    }
                    str = "unknown";
                }
                SetupWizardRepositoryImpl setupWizardRepositoryImpl = this.this$0;
                if (Intrinsics.areEqual(str, "android")) {
                    setupWizardRepositoryImpl.logger.log(SetupWizardRepositoryImpl.LogEvent.CONTEXTUAL_TIPS_SOURCE_DEVICE_ANDROID);
                } else if (Intrinsics.areEqual(str, "ios")) {
                    setupWizardRepositoryImpl.logger.log(SetupWizardRepositoryImpl.LogEvent.CONTEXTUAL_TIPS_SOURCE_DEVICE_IOS);
                } else {
                    setupWizardRepositoryImpl.logger.log(SetupWizardRepositoryImpl.LogEvent.CONTEXTUAL_TIPS_SOURCE_DEVICE_UNKNOWN);
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } catch (Exception e) {
            e.toString();
            return "unknown";
        }
    }
}
