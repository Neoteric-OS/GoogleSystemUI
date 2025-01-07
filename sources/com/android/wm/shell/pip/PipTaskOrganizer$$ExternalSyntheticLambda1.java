package com.android.wm.shell.pip;

import android.app.RemoteAction;
import com.android.wm.shell.pip.PipTaskOrganizer;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTaskOrganizer$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PipTaskOrganizer$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((StringJoiner) obj2).add(((RemoteAction) obj).getTitle());
                break;
            default:
                ((PipTaskOrganizer.AnonymousClass1) obj2).getClass();
                break;
        }
    }
}
