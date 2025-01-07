package com.android.systemui.keyboard.shortcut.data.source;

import android.view.KeyboardShortcutInfo;
import java.util.Comparator;
import java.util.Locale;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCategoriesShortcutsSource$shortcutGroups$2$invokeSuspend$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        CharSequence label = ((KeyboardShortcutInfo) obj).getLabel();
        Intrinsics.checkNotNull(label);
        String obj3 = label.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = obj3.toLowerCase(locale);
        CharSequence label2 = ((KeyboardShortcutInfo) obj2).getLabel();
        Intrinsics.checkNotNull(label2);
        return ComparisonsKt___ComparisonsJvmKt.compareValues(lowerCase, label2.toString().toLowerCase(locale));
    }
}
