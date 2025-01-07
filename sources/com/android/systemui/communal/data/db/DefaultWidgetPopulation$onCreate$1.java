package com.android.systemui.communal.data.db;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.systemui.log.core.Logger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultWidgetPopulation$onCreate$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DefaultWidgetPopulation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWidgetPopulation$onCreate$1(DefaultWidgetPopulation defaultWidgetPopulation, Continuation continuation) {
        super(2, continuation);
        this.this$0 = defaultWidgetPopulation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DefaultWidgetPopulation$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DefaultWidgetPopulation$onCreate$1 defaultWidgetPopulation$onCreate$1 = (DefaultWidgetPopulation$onCreate$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        defaultWidgetPopulation$onCreate$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer allocateIdAndBindWidget;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserHandle mainUser = this.this$0.userManager.getMainUser();
        Unit unit = Unit.INSTANCE;
        if (mainUser == null) {
            Logger.w$default(this.this$0.logger, "Skipped populating default widgets. Reason: device does not have a main user", null, 2, null);
            return unit;
        }
        int userSerialNumber = this.this$0.userManager.getUserSerialNumber(mainUser.getIdentifier());
        DefaultWidgetPopulation defaultWidgetPopulation = this.this$0;
        String[] strArr = defaultWidgetPopulation.defaultWidgets;
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = strArr[i];
            int i3 = i2 + 1;
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
            if (unflattenFromString != null && (allocateIdAndBindWidget = defaultWidgetPopulation.communalWidgetHost.allocateIdAndBindWidget(unflattenFromString, mainUser)) != null) {
                ((CommunalWidgetDao_Impl) defaultWidgetPopulation.communalWidgetDaoProvider.get()).addWidget(allocateIdAndBindWidget.intValue(), str, new Integer(i2), userSerialNumber);
            }
            i++;
            i2 = i3;
        }
        Logger.i$default(this.this$0.logger, "Populated default widgets in the database.", null, 2, null);
        return unit;
    }
}
