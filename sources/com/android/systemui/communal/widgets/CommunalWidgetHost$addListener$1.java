package com.android.systemui.communal.widgets;

import android.appwidget.AppWidgetProviderInfo;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class CommunalWidgetHost$addListener$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalWidgetHost communalWidgetHost = (CommunalWidgetHost) this.receiver;
        communalWidgetHost.getClass();
        BuildersKt.launch$default(communalWidgetHost.bgScope, null, null, new CommunalWidgetHost$onProviderInfoUpdated$1(communalWidgetHost, ((Number) obj).intValue(), (AppWidgetProviderInfo) obj2, null), 3);
        return Unit.INSTANCE;
    }
}
