package com.android.systemui.statusbar.phone;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.graphics.Color;
import android.os.Handler;
import android.view.IWindowManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LetterboxBackgroundProvider implements CoreStartable, Dumpable {
    public final Executor backgroundExecutor;
    public boolean isLetterboxBackgroundMultiColored;
    public final Handler mainHandler;
    public final WallpaperManager wallpaperManager;
    public final IWindowManager windowManager;
    public int letterboxBackgroundColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    public final LetterboxBackgroundProvider$wallpaperColorsListener$1 wallpaperColorsListener = new WallpaperManager.OnColorsChangedListener() { // from class: com.android.systemui.statusbar.phone.LetterboxBackgroundProvider$wallpaperColorsListener$1
        @Override // android.app.WallpaperManager.OnColorsChangedListener
        public final void onColorsChanged(WallpaperColors wallpaperColors, int i) {
            LetterboxBackgroundProvider letterboxBackgroundProvider = LetterboxBackgroundProvider.this;
            letterboxBackgroundProvider.backgroundExecutor.execute(new LetterboxBackgroundProvider$fetchBackgroundColorInfo$1(letterboxBackgroundProvider));
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.phone.LetterboxBackgroundProvider$wallpaperColorsListener$1] */
    public LetterboxBackgroundProvider(IWindowManager iWindowManager, Executor executor, WallpaperManager wallpaperManager, Handler handler) {
        this.windowManager = iWindowManager;
        this.backgroundExecutor = executor;
        this.wallpaperManager = wallpaperManager;
        this.mainHandler = handler;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(StringsKt__IndentKt.trimIndent("\n           letterboxBackgroundColor: " + Color.valueOf(this.letterboxBackgroundColor) + "\n           isLetterboxBackgroundMultiColored: " + this.isLetterboxBackgroundMultiColored + "\n       "));
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.backgroundExecutor.execute(new LetterboxBackgroundProvider$fetchBackgroundColorInfo$1(this));
        this.wallpaperManager.addOnColorsChangedListener(this.wallpaperColorsListener, this.mainHandler);
    }
}
