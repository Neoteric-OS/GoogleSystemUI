package com.android.systemui.keyboard.shortcut.ui;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyboard.shortcut.ui.viewmodel.ShortcutHelperViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperActivityStarter implements CoreStartable {
    public final CoroutineScope applicationScope;
    public final Context context;
    public final Function1 startActivity;
    public final ShortcutHelperViewModel viewModel;

    public ShortcutHelperActivityStarter(final Context context, CoroutineScope coroutineScope, ShortcutHelperViewModel shortcutHelperViewModel) {
        Function1 function1 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.ShortcutHelperActivityStarter.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                context.startActivity((Intent) obj);
                return Unit.INSTANCE;
            }
        };
        this.context = context;
        this.applicationScope = coroutineScope;
        this.viewModel = shortcutHelperViewModel;
        this.startActivity = function1;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.applicationScope, null, null, new ShortcutHelperActivityStarter$start$1(this, null), 3);
    }
}
