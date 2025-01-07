package com.android.systemui.statusbar.connectivity.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.view.ContextThemeWrapper;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileContextProvider implements Dumpable, DemoMode {
    public static final Companion Companion = null;
    public Integer demoMcc;
    public Integer demoMnc;
    public final DemoModeController demoModeController;
    public final Map subscriptions = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final Context access$createCarrierConfigContext(int i, int i2, Context context) {
            Companion companion = MobileContextProvider.Companion;
            Configuration configuration = new Configuration(context.getResources().getConfiguration());
            configuration.mcc = i;
            configuration.mnc = i2;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, context.getTheme());
            contextThemeWrapper.applyOverrideConfiguration(configuration);
            return contextThemeWrapper;
        }
    }

    public MobileContextProvider(NetworkController networkController, DumpManager dumpManager, DemoModeController demoModeController) {
        this.demoModeController = demoModeController;
        ((NetworkControllerImpl) networkController).addCallback(new SignalCallback() { // from class: com.android.systemui.statusbar.connectivity.ui.MobileContextProvider$signalCallback$1
            @Override // com.android.systemui.statusbar.connectivity.SignalCallback
            public final void setSubs(List list) {
                MobileContextProvider mobileContextProvider = MobileContextProvider.this;
                mobileContextProvider.subscriptions.clear();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    SubscriptionInfo subscriptionInfo = (SubscriptionInfo) it.next();
                    mobileContextProvider.subscriptions.put(Integer.valueOf(subscriptionInfo.getSubscriptionId()), subscriptionInfo);
                }
            }
        });
        dumpManager.registerDumpable(this);
        demoModeController.addCallback((DemoMode) this);
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        return Collections.singletonList("network");
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        String string = bundle.getString("mccmnc");
        if (string == null) {
            return;
        }
        if (string.length() == 5 || string.length() == 6) {
            this.demoMcc = Integer.valueOf(Integer.parseInt(string.subSequence(0, 3).toString()));
            this.demoMnc = Integer.valueOf(Integer.parseInt(string.subSequence(3, string.length()).toString()));
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Subscriptions below will be inflated with a configuration context with MCC/MNC overrides");
        for (Map.Entry entry : this.subscriptions.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) entry.getValue();
            int mcc = subscriptionInfo.getMcc();
            int mnc = subscriptionInfo.getMnc();
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(intValue, mcc, "  Subscription with subId(", ") with MCC/MNC(", "/");
            m.append(mnc);
            m.append(")");
            printWriter.println(m.toString());
        }
        Object obj = this.demoMcc;
        if (obj == null) {
            obj = "(none)";
        }
        printWriter.println("  MCC override: " + obj);
        Object obj2 = this.demoMnc;
        printWriter.println("  MNC override: " + (obj2 != null ? obj2 : "(none)"));
    }

    public final Context getMobileContextForSub(int i, Context context) {
        if (!this.demoModeController.isInDemoMode) {
            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) this.subscriptions.get(Integer.valueOf(i));
            return subscriptionInfo == null ? context : Companion.access$createCarrierConfigContext(subscriptionInfo.getMcc(), subscriptionInfo.getMnc(), context);
        }
        Integer num = this.demoMcc;
        int intValue = num != null ? num.intValue() : 0;
        Integer num2 = this.demoMnc;
        return Companion.access$createCarrierConfigContext(intValue, num2 != null ? num2.intValue() : 0, context);
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        this.demoMcc = null;
        this.demoMnc = null;
    }
}
