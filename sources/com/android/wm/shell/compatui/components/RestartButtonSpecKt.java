package com.android.wm.shell.compatui.components;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.wm.shell.R;
import com.android.wm.shell.compatui.api.CompatUILayout;
import com.android.wm.shell.compatui.api.CompatUILifecyclePredicates;
import com.android.wm.shell.compatui.api.CompatUISpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class RestartButtonSpecKt {
    public static final CompatUISpec RestartButtonSpec = new CompatUISpec(new CompatUILifecyclePredicates(), new CompatUILayout(new Function3() { // from class: com.android.wm.shell.compatui.components.RestartButtonSpecKt$RestartButtonSpec$3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Context context = (Context) obj;
            if (obj3 == null) {
                return LayoutInflater.from(context).inflate(R.layout.compat_ui_restart_button_layout, (ViewGroup) null);
            }
            throw new ClassCastException();
        }
    }, new Function4() { // from class: com.android.wm.shell.compatui.components.RestartButtonSpecKt$RestartButtonSpec$4
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            View view = (View) obj;
            if (obj4 != null) {
                throw new ClassCastException();
            }
            view.setVisibility(0);
            View findViewById = view.findViewById(R.id.size_compat_restart_button);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
            return Unit.INSTANCE;
        }
    }, new Function4() { // from class: com.android.wm.shell.compatui.components.RestartButtonSpecKt$RestartButtonSpec$5
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            if (obj4 == null) {
                return new Point(500, 500);
            }
            throw new ClassCastException();
        }
    }));
}
