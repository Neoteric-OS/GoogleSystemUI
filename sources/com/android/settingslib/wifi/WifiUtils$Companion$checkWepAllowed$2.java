package com.android.settingslib.wifi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WifiUtils$Companion$checkWepAllowed$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $dialogWindowType;
    final /* synthetic */ Function0 $onAllowed;
    final /* synthetic */ Function1 $onStartActivity;
    final /* synthetic */ String $ssid;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiUtils$Companion$checkWepAllowed$2(Context context, Function0 function0, Function1 function1, int i, String str, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
        this.$onAllowed = function0;
        this.$onStartActivity = function1;
        this.$dialogWindowType = i;
        this.$ssid = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new WifiUtils$Companion$checkWepAllowed$2(this.$context, this.$onAllowed, this.$onStartActivity, this.$dialogWindowType, this.$ssid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WifiUtils$Companion$checkWepAllowed$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            WifiManager wifiManager = (WifiManager) this.$context.getSystemService(WifiManager.class);
            if (wifiManager == null) {
                return unit;
            }
            if (wifiManager.isWepSupported()) {
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.Default, new WifiUtils$Companion$queryWepAllowed$2(wifiManager, null), this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            Intent intent = new Intent("android.intent.action.MAIN");
            int i2 = this.$dialogWindowType;
            String str = this.$ssid;
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.network.WepNetworkDialogActivity"));
            intent.putExtra("dialog_window_type", i2);
            intent.putExtra("ssid", str);
            this.$onStartActivity.invoke(intent.addFlags(268435456));
            return unit;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (((Boolean) obj).booleanValue()) {
            this.$onAllowed.invoke();
            return unit;
        }
        Intent intent2 = new Intent("android.intent.action.MAIN");
        int i22 = this.$dialogWindowType;
        String str2 = this.$ssid;
        intent2.setComponent(new ComponentName("com.android.settings", "com.android.settings.network.WepNetworkDialogActivity"));
        intent2.putExtra("dialog_window_type", i22);
        intent2.putExtra("ssid", str2);
        this.$onStartActivity.invoke(intent2.addFlags(268435456));
        return unit;
    }
}
