package com.android.systemui.keyboard.shortcut.domain.interactor;

import com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperCategoriesInteractor {
    public final ShortcutHelperCategoriesInteractor$special$$inlined$map$1 shortcutCategories;

    public ShortcutHelperCategoriesInteractor(ShortcutHelperCategoriesRepository shortcutHelperCategoriesRepository) {
        this.shortcutCategories = new ShortcutHelperCategoriesInteractor$special$$inlined$map$1(shortcutHelperCategoriesRepository.categories, this);
    }
}
