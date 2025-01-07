package com.android.systemui.common.domain.interactor;

import android.os.UserHandle;
import com.android.systemui.common.data.repository.PackageChangeRepositoryImpl;
import com.android.systemui.common.data.repository.PackageChangeRepositoryImpl$packageChanged$$inlined$filter$1;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor$packageChangedInternal$$inlined$filter$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ String $packageName$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PackageChangeInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1(Continuation continuation, PackageChangeInteractor packageChangeInteractor, String str) {
        super(3, continuation);
        this.this$0 = packageChangeInteractor;
        this.$packageName$inlined = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1 packageChangeInteractor$packageChanged$$inlined$flatMapLatest$1 = new PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$packageName$inlined);
        packageChangeInteractor$packageChanged$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        packageChangeInteractor$packageChanged$$inlined$flatMapLatest$1.L$1 = obj2;
        return packageChangeInteractor$packageChanged$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int intValue = ((Number) this.L$1).intValue();
            PackageChangeInteractor packageChangeInteractor = this.this$0;
            UserHandle of = UserHandle.of(intValue);
            String str = this.$packageName$inlined;
            PackageChangeRepositoryImpl$packageChanged$$inlined$filter$1 packageChanged = ((PackageChangeRepositoryImpl) packageChangeInteractor.packageChangeRepository).packageChanged(of);
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = packageChanged.collect(new PackageChangeInteractor$packageChangedInternal$$inlined$filter$1.AnonymousClass2(str, flowCollector), this);
            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                collect = unit;
            }
            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
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
