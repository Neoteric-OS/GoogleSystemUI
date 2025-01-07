package com.android.systemui.keyboard.shortcut.data.source;

import android.view.WindowManager;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CurrentAppShortcutsSource implements KeyboardShortcutGroupsSource {
    public final WindowManager windowManager;

    public CurrentAppShortcutsSource(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @Override // com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource
    public final Object shortcutGroups(int i, Continuation continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        this.windowManager.requestAppKeyboardShortcuts(new WindowManager.KeyboardShortcutsReceiver() { // from class: com.android.systemui.keyboard.shortcut.data.source.CurrentAppShortcutsSource$shortcutGroups$2$shortcutsReceiver$1
            public final void onKeyboardShortcutsReceived(List list) {
                CancellableContinuationImpl cancellableContinuationImpl2 = CancellableContinuationImpl.this;
                if (list == null) {
                    list = EmptyList.INSTANCE;
                }
                cancellableContinuationImpl2.resumeWith(list);
            }
        }, i);
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }
}
