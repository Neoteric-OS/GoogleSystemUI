package com.android.systemui.communal.widgets;

import androidx.activity.ComponentActivity;
import com.android.systemui.util.ReferenceExtKt$nullableAtomicReference$1;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetConfigurationController implements WidgetConfigurator {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final ComponentActivity activity;
    public final CommunalAppWidgetHost appWidgetHost;
    public final CoroutineDispatcher bgDispatcher;
    public final ReferenceExtKt$nullableAtomicReference$1 result$delegate = new ReferenceExtKt$nullableAtomicReference$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(WidgetConfigurationController.class, "result", "getResult()Lkotlinx/coroutines/CompletableDeferred;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
    }

    public WidgetConfigurationController(ComponentActivity componentActivity, CommunalAppWidgetHost communalAppWidgetHost, CoroutineDispatcher coroutineDispatcher) {
        this.activity = componentActivity;
        this.appWidgetHost = communalAppWidgetHost;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final Object configureWidget(int i, SuspendLambda suspendLambda) {
        return BuildersKt.withContext(this.bgDispatcher, new WidgetConfigurationController$configureWidget$2(this, i, null), suspendLambda);
    }

    public final CompletableDeferred getResult() {
        KProperty kProperty = $$delegatedProperties[0];
        return (CompletableDeferred) this.result$delegate.t.get();
    }
}
