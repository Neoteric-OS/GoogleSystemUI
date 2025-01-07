package com.android.systemui.brightness.data.repository;

import android.content.pm.UserInfo;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.systemui.utils.PolicyRestriction;
import com.android.systemui.utils.UserRestrictionChecker;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BrightnessPolicyRepositoryImpl$restrictionPolicy$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BrightnessPolicyRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPolicyRepositoryImpl$restrictionPolicy$1(BrightnessPolicyRepositoryImpl brightnessPolicyRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = brightnessPolicyRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BrightnessPolicyRepositoryImpl$restrictionPolicy$1 brightnessPolicyRepositoryImpl$restrictionPolicy$1 = new BrightnessPolicyRepositoryImpl$restrictionPolicy$1(this.this$0, continuation);
        brightnessPolicyRepositoryImpl$restrictionPolicy$1.L$0 = obj;
        return brightnessPolicyRepositoryImpl$restrictionPolicy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BrightnessPolicyRepositoryImpl$restrictionPolicy$1) create((UserInfo) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserInfo userInfo = (UserInfo) this.L$0;
        BrightnessPolicyRepositoryImpl brightnessPolicyRepositoryImpl = this.this$0;
        UserRestrictionChecker userRestrictionChecker = brightnessPolicyRepositoryImpl.userRestrictionChecker;
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(brightnessPolicyRepositoryImpl.applicationContext, "no_config_brightness", userInfo.id);
        if (checkIfRestrictionEnforced != null) {
            return new PolicyRestriction.Restricted(checkIfRestrictionEnforced);
        }
        BrightnessPolicyRepositoryImpl brightnessPolicyRepositoryImpl2 = this.this$0;
        UserRestrictionChecker userRestrictionChecker2 = brightnessPolicyRepositoryImpl2.userRestrictionChecker;
        return RestrictedLockUtilsInternal.hasBaseUserRestriction(brightnessPolicyRepositoryImpl2.applicationContext, "no_config_brightness", userInfo.id) ? new PolicyRestriction.Restricted(new RestrictedLockUtils.EnforcedAdmin()) : PolicyRestriction.NoRestriction.INSTANCE;
    }
}
