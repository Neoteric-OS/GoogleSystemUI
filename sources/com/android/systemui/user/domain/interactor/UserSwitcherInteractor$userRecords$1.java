package com.android.systemui.user.domain.interactor;

import android.content.pm.UserInfo;
import com.android.systemui.user.data.model.UserSwitcherSettingsModel;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherInteractor$userRecords$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ UserSwitcherInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherInteractor$userRecords$1(UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
        super(5, continuation);
        this.this$0 = userSwitcherInteractor;
    }

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        UserSwitcherInteractor$userRecords$1 userSwitcherInteractor$userRecords$1 = new UserSwitcherInteractor$userRecords$1(this.this$0, (Continuation) obj5);
        userSwitcherInteractor$userRecords$1.L$0 = (List) obj;
        userSwitcherInteractor$userRecords$1.L$1 = (UserInfo) obj2;
        userSwitcherInteractor$userRecords$1.L$2 = (List) obj3;
        userSwitcherInteractor$userRecords$1.L$3 = (UserSwitcherSettingsModel) obj4;
        return userSwitcherInteractor$userRecords$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00c8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00f6 -> B:6:0x00f7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00a3 -> B:27:0x00a4). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$userRecords$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
