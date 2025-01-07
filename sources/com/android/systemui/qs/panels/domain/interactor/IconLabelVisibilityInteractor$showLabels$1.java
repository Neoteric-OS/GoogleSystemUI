package com.android.systemui.qs.panels.domain.interactor;

import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class IconLabelVisibilityInteractor$showLabels$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ IconLabelVisibilityInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IconLabelVisibilityInteractor$showLabels$1(IconLabelVisibilityInteractor iconLabelVisibilityInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = iconLabelVisibilityInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        IconLabelVisibilityInteractor$showLabels$1 iconLabelVisibilityInteractor$showLabels$1 = new IconLabelVisibilityInteractor$showLabels$1(this.this$0, continuation);
        iconLabelVisibilityInteractor$showLabels$1.Z$0 = ((Boolean) obj).booleanValue();
        return iconLabelVisibilityInteractor$showLabels$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        IconLabelVisibilityInteractor$showLabels$1 iconLabelVisibilityInteractor$showLabels$1 = (IconLabelVisibilityInteractor$showLabels$1) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        iconLabelVisibilityInteractor$showLabels$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        IconLabelVisibilityInteractor iconLabelVisibilityInteractor = this.this$0;
        iconLabelVisibilityInteractor.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        IconLabelVisibilityInteractor$logChange$2 iconLabelVisibilityInteractor$logChange$2 = new Function1() { // from class: com.android.systemui.qs.panels.domain.interactor.IconLabelVisibilityInteractor$logChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Icon tile label visibility changed: ", ((LogMessage) obj2).getBool1());
            }
        };
        LogBuffer logBuffer = iconLabelVisibilityInteractor.logBuffer;
        LogMessage obtain = logBuffer.obtain("IconLabelVisibilityChange", logLevel, iconLabelVisibilityInteractor$logChange$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
