package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.util.IndentingPrintWriter;
import com.android.systemui.CoreStartable;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarIconViewBindingFailureTracker implements CoreStartable {
    public Collection aodFailures;
    public Collection shelfFailures;
    public Collection statusBarFailures;

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        Collection collection = this.aodFailures;
        asIndenting.append("AOD Icon binding failures:").append((CharSequence) ": ").println(collection.size());
        asIndenting.increaseIndent();
        try {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                asIndenting.println(it.next());
            }
            asIndenting.decreaseIndent();
            Collection collection2 = this.statusBarFailures;
            asIndenting.append("Status Bar Icon binding failures:").append((CharSequence) ": ").println(collection2.size());
            asIndenting.increaseIndent();
            try {
                Iterator it2 = collection2.iterator();
                while (it2.hasNext()) {
                    asIndenting.println(it2.next());
                }
                asIndenting.decreaseIndent();
                Collection collection3 = this.shelfFailures;
                asIndenting.append("Shelf Icon binding failures:").append((CharSequence) ": ").println(collection3.size());
                asIndenting.increaseIndent();
                try {
                    Iterator it3 = collection3.iterator();
                    while (it3.hasNext()) {
                        asIndenting.println(it3.next());
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
