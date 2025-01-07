package com.android.systemui.unfold;

import com.android.systemui.CoreStartable;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldInitializationStartable implements CoreStartable {
    public final Optional foldStateLoggerOptional;
    public final Optional foldStateLoggingProviderOptional;
    public final Optional unfoldBgTransitionProgressProviderOptional;
    public final Optional unfoldComponentOptional;
    public final Optional unfoldTransitionProgressForwarder;

    public UnfoldInitializationStartable(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6) {
        this.unfoldComponentOptional = optional;
        this.foldStateLoggingProviderOptional = optional2;
        this.foldStateLoggerOptional = optional3;
        this.unfoldBgTransitionProgressProviderOptional = optional4;
        this.unfoldTransitionProgressForwarder = optional6;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.unfoldComponentOptional.ifPresent(UnfoldInitializationStartable$start$1.INSTANCE);
        this.foldStateLoggingProviderOptional.ifPresent(UnfoldInitializationStartable$start$1.INSTANCE$1);
        this.foldStateLoggerOptional.ifPresent(UnfoldInitializationStartable$start$1.INSTANCE$2);
        this.unfoldBgTransitionProgressProviderOptional.ifPresent(new UnfoldInitializationStartable$start$4(0, this));
    }
}
