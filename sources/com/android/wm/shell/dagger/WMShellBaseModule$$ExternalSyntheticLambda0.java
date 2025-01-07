package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import dagger.Lazy;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WMShellBaseModule$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Context f$0;

    public /* synthetic */ WMShellBaseModule$$ExternalSyntheticLambda0(int i, Context context) {
        this.$r8$classId = i;
        this.f$0 = context;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        Context context = this.f$0;
        Lazy lazy = (Lazy) obj;
        switch (i) {
            case 0:
                if (!DesktopModeStatus.canEnterDesktopMode(context)) {
                    break;
                } else {
                    break;
                }
            default:
                if (!DesktopModeStatus.canEnterDesktopMode(context)) {
                    break;
                } else {
                    break;
                }
        }
        return Optional.empty();
    }
}
