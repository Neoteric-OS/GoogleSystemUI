package com.android.systemui.dump;

import android.content.Context;
import com.android.systemui.Dumpable;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemUIConfigDumpable implements Dumpable {
    public final Context context;
    public final Map startables;

    public SystemUIConfigDumpable(DumpManager dumpManager, Context context, Map map) {
        this.context = context;
        this.startables = map;
        dumpManager.registerCriticalDumpable("SystemUiServiceComponents", this);
    }

    public static void dumpServiceList(PrintWriter printWriter, String str, String[] strArr) {
        printWriter.print(str);
        printWriter.print(": ");
        if (strArr == null) {
            printWriter.println("N/A");
            return;
        }
        printWriter.print(strArr.length);
        printWriter.println(" services");
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            printWriter.print("  ");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.println(strArr[i]);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SystemUiServiceComponents configuration:");
        printWriter.print("vendor component: ");
        printWriter.println(this.context.getResources().getString(R.string.config_systemUIVendorServiceComponent));
        Set keySet = this.startables.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(keySet, 10));
        Iterator it = keySet.iterator();
        while (it.hasNext()) {
            arrayList.add(((Class) it.next()).getSimpleName());
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        arrayList2.add(this.context.getResources().getString(R.string.config_systemUIVendorServiceComponent));
        dumpServiceList(printWriter, "global", (String[]) arrayList2.toArray(new String[0]));
        dumpServiceList(printWriter, "per-user", this.context.getResources().getStringArray(R.array.config_systemUIServiceComponentsPerUser));
    }
}
