package com.android.systemui.demomode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.util.Assert;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DemoModeController implements CallbackController, Dumpable {
    public final DemoModeController$broadcastReceiver$1 broadcastReceiver;
    public final Context context;
    public final GlobalSettings globalSettings;
    public boolean initialized;
    public boolean isInDemoMode;
    public final Map receiverMap;
    public final List receivers = new ArrayList();
    public final DemoModeController$tracker$1 tracker;

    /* JADX WARN: Type inference failed for: r2v6, types: [com.android.systemui.demomode.DemoModeController$broadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.demomode.DemoModeController$tracker$1] */
    public DemoModeController(Context context, DumpManager dumpManager, GlobalSettings globalSettings, BroadcastDispatcher broadcastDispatcher) {
        this.context = context;
        this.globalSettings = globalSettings;
        Settings.Global.putString(((GlobalSettingsImpl) globalSettings).mContentResolver, "sysui_tuner_demo_on", String.valueOf(0));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<String> list = DemoMode.COMMANDS;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (String str : list) {
            Intrinsics.checkNotNull(str);
            arrayList.add((List) linkedHashMap.put(str, new ArrayList()));
        }
        this.receiverMap = linkedHashMap;
        final Context context2 = this.context;
        final GlobalSettings globalSettings2 = this.globalSettings;
        this.tracker = new DemoModeAvailabilityTracker(context2, globalSettings2) { // from class: com.android.systemui.demomode.DemoModeController$tracker$1
            @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
            public final void onDemoModeAvailabilityChanged() {
                boolean z = this.isDemoModeAvailable;
                DemoModeController demoModeController = DemoModeController.this;
                if (!demoModeController.isInDemoMode || z) {
                    return;
                }
                GlobalSettings globalSettings3 = demoModeController.globalSettings;
                Settings.Global.putString(((GlobalSettingsImpl) globalSettings3).mContentResolver, "sysui_tuner_demo_on", String.valueOf(0));
            }

            @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
            public final void onDemoModeFinished() {
                DemoModeController demoModeController = DemoModeController.this;
                if (demoModeController.isInDemoMode != this.isInDemoMode) {
                    demoModeController.exitDemoMode$1();
                }
            }

            @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
            public final void onDemoModeStarted() {
                DemoModeController demoModeController = DemoModeController.this;
                if (demoModeController.isInDemoMode != this.isInDemoMode) {
                    demoModeController.enterDemoMode();
                }
            }
        };
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.demomode.DemoModeController$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context3, Intent intent) {
                Bundle extras;
                if ("com.android.systemui.demo".equals(intent.getAction()) && (extras = intent.getExtras()) != null) {
                    String lowerCase = StringsKt.trim(extras.getString("command", "")).toString().toLowerCase(Locale.ROOT);
                    if (lowerCase.length() == 0) {
                        return;
                    }
                    try {
                        DemoModeController.this.dispatchDemoCommand(extras, lowerCase);
                    } catch (Throwable th) {
                        Log.w("DemoModeController", "Error running demo command, intent=" + intent + " " + th);
                    }
                }
            }
        };
    }

    public final Flow demoFlowForCommand() {
        return FlowConflatedKt.conflatedCallbackFlow(new DemoModeController$demoFlowForCommand$1(this, "network", null));
    }

    public final void dispatchDemoCommand(Bundle bundle, String str) {
        Assert.isMainThread();
        if (this.tracker.isDemoModeAvailable) {
            if (str.equals("enter")) {
                enterDemoMode();
            } else if (str.equals("exit")) {
                exitDemoMode$1();
            } else if (!this.isInDemoMode) {
                enterDemoMode();
            }
            Object obj = this.receiverMap.get(str);
            Intrinsics.checkNotNull(obj);
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                ((DemoMode) it.next()).dispatchDemoCommand(bundle, str);
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        List list;
        printWriter.println("DemoModeController state -");
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  isInDemoMode=", this.isInDemoMode, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  isDemoModeAllowed=", this.tracker.isDemoModeAvailable, printWriter);
        synchronized (this) {
            list = CollectionsKt.toList(this.receivers);
        }
        printWriter.println("  receivers=[" + CollectionsKt.joinToString$default(list, ", ", null, null, new Function1() { // from class: com.android.systemui.demomode.DemoModeController$dump$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DemoMode demoMode = (DemoMode) obj;
                return demoMode.getClass().isAnonymousClass() ? demoMode.getClass().getName() : demoMode.getClass().getSimpleName();
            }
        }, 30) + "]");
        printWriter.println("  receiverMap= [");
        for (Map.Entry entry : this.receiverMap.entrySet()) {
            printWriter.println(MotionLayout$$ExternalSyntheticOutline0.m("    ", (String) entry.getKey(), " : [", CollectionsKt.joinToString$default((List) entry.getValue(), ", ", null, null, new Function1() { // from class: com.android.systemui.demomode.DemoModeController$dump$3$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DemoMode demoMode = (DemoMode) obj;
                    return demoMode.getClass().isAnonymousClass() ? demoMode.getClass().getName() : demoMode.getClass().getSimpleName();
                }
            }, 30), "]"));
        }
        printWriter.println(" ]");
    }

    public final void enterDemoMode() {
        List list;
        this.isInDemoMode = true;
        Assert.isMainThread();
        synchronized (this) {
            list = CollectionsKt.toList(this.receivers);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((DemoModeCommandReceiver) it.next()).onDemoModeStarted();
        }
    }

    public final void exitDemoMode$1() {
        List list;
        this.isInDemoMode = false;
        Assert.isMainThread();
        synchronized (this) {
            list = CollectionsKt.toList(this.receivers);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((DemoModeCommandReceiver) it.next()).onDemoModeFinished();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(DemoMode demoMode) {
        List<String> demoCommands = demoMode.demoCommands();
        Intrinsics.checkNotNull(demoCommands);
        for (String str : demoCommands) {
            if (!this.receiverMap.containsKey(str)) {
                throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Command (", str, ") not recognized. See DemoMode.java for valid commands"));
            }
            Object obj = this.receiverMap.get(str);
            Intrinsics.checkNotNull(obj);
            ((List) obj).add(demoMode);
        }
        synchronized (this) {
            this.receivers.add(demoMode);
        }
        if (this.isInDemoMode) {
            demoMode.onDemoModeStarted();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(DemoMode demoMode) {
        synchronized (this) {
            try {
                Iterator it = demoMode.demoCommands().iterator();
                while (it.hasNext()) {
                    Object obj = this.receiverMap.get((String) it.next());
                    Intrinsics.checkNotNull(obj);
                    ((List) obj).remove(demoMode);
                }
                this.receivers.remove(demoMode);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
