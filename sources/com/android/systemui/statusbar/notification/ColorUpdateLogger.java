package com.android.systemui.statusbar.notification;

import android.icu.text.SimpleDateFormat;
import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColorUpdateLogger implements Dumpable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final List frames = new ArrayList();

    static {
        new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
    }

    public ColorUpdateLogger(DumpManager dumpManager) {
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("enabled: false");
        ArrayList arrayList = (ArrayList) this.frames;
        asIndenting.append("frames").append((CharSequence) ": ").println(arrayList.size());
        asIndenting.increaseIndent();
        try {
            Iterator it = arrayList.iterator();
            if (!it.hasNext()) {
                asIndenting.decreaseIndent();
            } else {
                if (it.next() != null) {
                    throw new ClassCastException();
                }
                throw null;
            }
        } catch (Throwable th) {
            asIndenting.decreaseIndent();
            throw th;
        }
    }
}
