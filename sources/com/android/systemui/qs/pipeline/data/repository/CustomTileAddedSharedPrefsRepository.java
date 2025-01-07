package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.settings.UserFileManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTileAddedSharedPrefsRepository implements CustomTileAddedRepository {
    public final UserFileManager userFileManager;

    public CustomTileAddedSharedPrefsRepository(UserFileManager userFileManager) {
        this.userFileManager = userFileManager;
    }
}
