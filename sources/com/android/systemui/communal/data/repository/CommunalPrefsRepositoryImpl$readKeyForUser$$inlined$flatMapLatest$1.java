package com.android.systemui.communal.data.repository;

import android.content.SharedPreferences;
import android.content.pm.UserInfo;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.util.kotlin.SharedPreferencesExt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ String $key$inlined;
    final /* synthetic */ UserInfo $user$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CommunalPrefsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1(Continuation continuation, CommunalPrefsRepositoryImpl communalPrefsRepositoryImpl, UserInfo userInfo, String str) {
        super(3, continuation);
        this.this$0 = communalPrefsRepositoryImpl;
        this.$user$inlined = userInfo;
        this.$key$inlined = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CommunalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1 communalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1 = new CommunalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$user$inlined, this.$key$inlined);
        communalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        communalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1.L$1 = obj2;
        return communalPrefsRepositoryImpl$readKeyForUser$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            CommunalPrefsRepositoryImpl communalPrefsRepositoryImpl = this.this$0;
            UserInfo userInfo = this.$user$inlined;
            communalPrefsRepositoryImpl.getClass();
            SharedPreferences sharedPreferences$1 = ((UserFileManagerImpl) communalPrefsRepositoryImpl.userFileManager).getSharedPreferences$1(userInfo.id, "communal_hub_prefs");
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), SharedPreferencesExt.observe(sharedPreferences$1));
            String str = this.$key$inlined;
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(new CommunalPrefsRepositoryImpl$readKeyForUser$lambda$1$$inlined$map$1$2(flowCollector, sharedPreferences$1, str), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
