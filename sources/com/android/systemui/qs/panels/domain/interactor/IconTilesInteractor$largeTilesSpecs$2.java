package com.android.systemui.qs.panels.domain.interactor;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class IconTilesInteractor$largeTilesSpecs$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IconTilesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IconTilesInteractor$largeTilesSpecs$2(IconTilesInteractor iconTilesInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = iconTilesInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        IconTilesInteractor$largeTilesSpecs$2 iconTilesInteractor$largeTilesSpecs$2 = new IconTilesInteractor$largeTilesSpecs$2(this.this$0, continuation);
        iconTilesInteractor$largeTilesSpecs$2.L$0 = obj;
        return iconTilesInteractor$largeTilesSpecs$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        IconTilesInteractor$largeTilesSpecs$2 iconTilesInteractor$largeTilesSpecs$2 = (IconTilesInteractor$largeTilesSpecs$2) create((Set) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        iconTilesInteractor$largeTilesSpecs$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.L$0;
        IconTilesInteractor iconTilesInteractor = this.this$0;
        iconTilesInteractor.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        IconTilesInteractor$logChange$2 iconTilesInteractor$logChange$2 = new Function1() { // from class: com.android.systemui.qs.panels.domain.interactor.IconTilesInteractor$logChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Large tiles change: ", ((LogMessage) obj2).getStr1());
            }
        };
        LogBuffer logBuffer = iconTilesInteractor.logBuffer;
        LogMessage obtain = logBuffer.obtain("LargeTilesSpecsChange", logLevel, iconTilesInteractor$logChange$2, null);
        ((LogMessageImpl) obtain).str1 = set.toString();
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
