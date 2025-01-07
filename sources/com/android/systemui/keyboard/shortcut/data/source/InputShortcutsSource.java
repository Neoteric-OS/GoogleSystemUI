package com.android.systemui.keyboard.shortcut.data.source;

import android.content.res.Resources;
import android.view.WindowManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InputShortcutsSource implements KeyboardShortcutGroupsSource {
    public final Resources resources;
    public final WindowManager windowManager;

    public InputShortcutsSource(Resources resources, WindowManager windowManager) {
        this.resources = resources;
        this.windowManager = windowManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object shortcutGroups(int r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$shortcutGroups$1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$shortcutGroups$1 r0 = (com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$shortcutGroups$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$shortcutGroups$1 r0 = new com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$shortcutGroups$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r10 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r10
            r0.<init>(r8, r10)
        L1a:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r8 = r0.L$0
            java.util.Collection r8 = (java.util.Collection) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L98
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r10)
            android.view.KeyboardShortcutGroup r10 = new android.view.KeyboardShortcutGroup
            android.content.res.Resources r2 = r8.resources
            r4 = 2131954186(0x7f130a0a, float:1.9544864E38)
            java.lang.String r2 = r2.getString(r4)
            r4 = 2
            android.view.KeyboardShortcutInfo[] r4 = new android.view.KeyboardShortcutInfo[r4]
            android.content.res.Resources r5 = r8.resources
            r6 = 2131952833(0x7f1304c1, float:1.954212E38)
            java.lang.String r5 = r5.getString(r6)
            com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1 r6 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1
                static {
                    /*
                        com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1 r0 = new com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1) com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1.INSTANCE com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoBuilder r1 = (com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoBuilder) r1
                        r0 = 4096(0x1000, float:5.74E-42)
                        r1.modifiers = r0
                        r0 = 62
                        r1.keyCode = r0
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            android.view.KeyboardShortcutInfo r5 = com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoKt.shortcutInfo(r5, r6)
            r6 = 0
            r4[r6] = r5
            android.content.res.Resources r5 = r8.resources
            r6 = 2131952834(0x7f1304c2, float:1.9542122E38)
            java.lang.String r5 = r5.getString(r6)
            com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2 r6 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2
                static {
                    /*
                        com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2 r0 = new com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2) com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2.INSTANCE com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoBuilder r1 = (com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoBuilder) r1
                        r0 = 4097(0x1001, float:5.741E-42)
                        r1.modifiers = r0
                        r0 = 62
                        r1.keyCode = r0
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$inputLanguageShortcuts$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            android.view.KeyboardShortcutInfo r5 = com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoKt.shortcutInfo(r5, r6)
            r4[r3] = r5
            java.util.List r4 = kotlin.collections.CollectionsKt__CollectionsKt.listOf(r4)
            r10.<init>(r2, r4)
            java.util.List r10 = java.util.Collections.singletonList(r10)
            r0.L$0 = r10
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r2.<init>(r3, r0)
            r2.initCancellability()
            com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$getImeShortcutGroup$2$shortcutsReceiver$1 r0 = new com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource$getImeShortcutGroup$2$shortcutsReceiver$1
            r0.<init>()
            android.view.WindowManager r8 = r8.windowManager
            r8.requestImeKeyboardShortcuts(r0, r9)
            java.lang.Object r8 = r2.getResult()
            if (r8 != r1) goto L95
            return r1
        L95:
            r7 = r10
            r10 = r8
            r8 = r7
        L98:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.List r8 = kotlin.collections.CollectionsKt.plus(r10, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource.shortcutGroups(int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
