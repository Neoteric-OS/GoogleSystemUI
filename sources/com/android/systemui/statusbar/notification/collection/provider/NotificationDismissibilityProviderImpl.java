package com.android.systemui.statusbar.notification.collection.provider;

import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.EmptySet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationDismissibilityProviderImpl implements Dumpable {
    public volatile Set nonDismissableEntryKeys;

    public NotificationDismissibilityProviderImpl(DumpManager dumpManager) {
        dumpManager.registerNormalDumpable("NotificationDismissibilityProvider", this);
        this.nonDismissableEntryKeys = EmptySet.INSTANCE;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        Set set = this.nonDismissableEntryKeys;
        asIndenting.append("non-dismissible entries").append((CharSequence) ": ").println(set.size());
        asIndenting.increaseIndent();
        try {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                asIndenting.println(it.next());
            }
        } finally {
            asIndenting.decreaseIndent();
        }
    }

    public static /* synthetic */ void getNonDismissableEntryKeys$annotations() {
    }
}
