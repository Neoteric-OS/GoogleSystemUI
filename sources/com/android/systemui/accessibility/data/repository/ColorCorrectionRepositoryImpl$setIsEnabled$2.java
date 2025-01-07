package com.android.systemui.accessibility.data.repository;

import android.os.UserHandle;
import com.android.systemui.util.settings.SecureSettings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ColorCorrectionRepositoryImpl$setIsEnabled$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isEnabled;
    final /* synthetic */ UserHandle $userHandle;
    int label;
    final /* synthetic */ ColorCorrectionRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorCorrectionRepositoryImpl$setIsEnabled$2(ColorCorrectionRepositoryImpl colorCorrectionRepositoryImpl, boolean z, UserHandle userHandle, Continuation continuation) {
        super(2, continuation);
        this.this$0 = colorCorrectionRepositoryImpl;
        this.$isEnabled = z;
        this.$userHandle = userHandle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ColorCorrectionRepositoryImpl$setIsEnabled$2(this.this$0, this.$isEnabled, this.$userHandle, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ColorCorrectionRepositoryImpl$setIsEnabled$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SecureSettings secureSettings = this.this$0.secureSettings;
        boolean z = this.$isEnabled;
        return Boolean.valueOf(secureSettings.putIntForUser("accessibility_display_daltonizer_enabled", z ? 1 : 0, this.$userHandle.getIdentifier()));
    }
}
