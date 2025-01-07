package com.android.systemui.statusbar.notification.collection;

import android.util.IndentingPrintWriter;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.Pluggable;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipelineDumper {
    public final IndentingPrintWriter ipw;

    public PipelineDumper(PrintWriter printWriter) {
        this.ipw = DumpUtilsKt.asIndenting(printWriter);
    }

    public final void dump(Object obj, String str) {
        this.ipw.print(str.concat(": "));
        dump(obj);
    }

    public final void println(Object obj) {
        this.ipw.println(obj);
    }

    public final void dump(Object obj) {
        String str;
        String bareClassName;
        if (obj == null ? true : obj instanceof String ? true : obj instanceof Integer) {
            this.ipw.println(obj);
            return;
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            this.ipw.println(collection.size());
            this.ipw.increaseIndent();
            try {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    dump(it.next());
                }
                return;
            } finally {
            }
        }
        IndentingPrintWriter indentingPrintWriter = this.ipw;
        if (obj instanceof NotifLifetimeExtender) {
            str = ((NotifLifetimeExtender) obj).getName();
        } else if (obj instanceof BubbleCoordinator.AnonymousClass2) {
            ((BubbleCoordinator.AnonymousClass2) obj).getClass();
            str = "BubbleCoordinator";
        } else {
            str = obj instanceof Pluggable ? ((Pluggable) obj).mName : null;
        }
        if (str == null || (bareClassName = MotionLayout$$ExternalSyntheticOutline0.m("\"", str, "\" (", PipelineDumperKt.getBareClassName(obj), ")")) == null) {
            bareClassName = PipelineDumperKt.getBareClassName(obj);
        }
        indentingPrintWriter.println(bareClassName);
        PipelineDumpable pipelineDumpable = obj instanceof PipelineDumpable ? (PipelineDumpable) obj : null;
        if (pipelineDumpable != null) {
            this.ipw.increaseIndent();
            try {
                pipelineDumpable.dumpPipeline(this);
            } finally {
            }
        }
    }
}
