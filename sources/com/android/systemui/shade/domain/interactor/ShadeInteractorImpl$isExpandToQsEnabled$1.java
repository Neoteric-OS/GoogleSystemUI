package com.android.systemui.shade.domain.interactor;

import com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ShadeInteractorImpl$isExpandToQsEnabled$1 extends SuspendLambda implements Function6 {
    final /* synthetic */ UserSwitcherInteractor $userSwitcherInteractor;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadeInteractorImpl$isExpandToQsEnabled$1(UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
        super(6, continuation);
        this.$userSwitcherInteractor = userSwitcherInteractor;
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        boolean booleanValue2 = ((Boolean) obj3).booleanValue();
        boolean booleanValue3 = ((Boolean) obj4).booleanValue();
        boolean booleanValue4 = ((Boolean) obj5).booleanValue();
        ShadeInteractorImpl$isExpandToQsEnabled$1 shadeInteractorImpl$isExpandToQsEnabled$1 = new ShadeInteractorImpl$isExpandToQsEnabled$1(this.$userSwitcherInteractor, (Continuation) obj6);
        shadeInteractorImpl$isExpandToQsEnabled$1.L$0 = (DisableFlagsModel) obj;
        shadeInteractorImpl$isExpandToQsEnabled$1.Z$0 = booleanValue;
        shadeInteractorImpl$isExpandToQsEnabled$1.Z$1 = booleanValue2;
        shadeInteractorImpl$isExpandToQsEnabled$1.Z$2 = booleanValue3;
        shadeInteractorImpl$isExpandToQsEnabled$1.Z$3 = booleanValue4;
        return shadeInteractorImpl$isExpandToQsEnabled$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        if (r1 == false) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r4.label
            if (r0 != 0) goto L3a
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel r5 = (com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel) r5
            boolean r0 = r4.Z$0
            boolean r1 = r4.Z$1
            boolean r2 = r4.Z$2
            boolean r3 = r4.Z$3
            if (r3 == 0) goto L34
            if (r2 != 0) goto L29
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r4 = r4.$userSwitcherInteractor
            com.android.systemui.user.data.repository.UserRepositoryImpl r4 = r4.repository
            kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r4._userSwitcherSettings
            java.lang.Object r4 = r4.getValue()
            com.android.systemui.user.data.model.UserSwitcherSettingsModel r4 = (com.android.systemui.user.data.model.UserSwitcherSettingsModel) r4
            boolean r4 = r4.isSimpleUserSwitcher
            if (r4 != 0) goto L34
        L29:
            if (r0 == 0) goto L34
            int r4 = r5.disable2
            r5 = 1
            r4 = r4 & r5
            if (r4 != 0) goto L34
            if (r1 != 0) goto L34
            goto L35
        L34:
            r5 = 0
        L35:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)
            return r4
        L3a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.domain.interactor.ShadeInteractorImpl$isExpandToQsEnabled$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
