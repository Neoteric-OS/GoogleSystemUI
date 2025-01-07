package com.android.wm.shell.compatui.api;

import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CompatUISpec {
    public final CompatUILayout layout;
    public final Function1 log;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.compatui.api.CompatUISpec$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_COMPAT_UI, (String) obj, new Object[0]);
            return Unit.INSTANCE;
        }
    }

    public CompatUISpec(CompatUILifecyclePredicates compatUILifecyclePredicates, CompatUILayout compatUILayout) {
    }
}
