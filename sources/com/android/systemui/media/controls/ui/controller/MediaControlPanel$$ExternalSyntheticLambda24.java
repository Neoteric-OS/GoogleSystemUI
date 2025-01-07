package com.android.systemui.media.controls.ui.controller;

import android.app.WallpaperColors;
import android.app.smartspace.SmartspaceAction;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Trace;
import android.util.Log;
import android.widget.ImageView;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.Style;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ MediaControlPanel f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda24(MediaControlPanel mediaControlPanel, int i, int i2, int i3, Drawable drawable, String str, String str2, int i4) {
        this.f$0 = mediaControlPanel;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = i3;
        this.f$1 = drawable;
        this.f$5 = str;
        this.f$6 = str2;
        this.f$7 = i4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                MediaControlPanel mediaControlPanel = this.f$0;
                SmartspaceAction smartspaceAction = (SmartspaceAction) this.f$1;
                int i = this.f$2;
                int i2 = this.f$3;
                int i3 = this.f$4;
                String str = this.f$5;
                String str2 = this.f$6;
                int i4 = this.f$7;
                mediaControlPanel.getClass();
                Icon icon = smartspaceAction.getIcon();
                WallpaperColors wallpaperColor = mediaControlPanel.getWallpaperColor(icon);
                ((ExecutorImpl) mediaControlPanel.mMainExecutor).execute(new MediaControlPanel$$ExternalSyntheticLambda24(mediaControlPanel, i3, i, i2, wallpaperColor != null ? mediaControlPanel.addGradientToRecommendationAlbum(icon, new ColorScheme(wallpaperColor, true, Style.CONTENT), i, i2) : new ColorDrawable(0), str, str2, i4));
                break;
            default:
                MediaControlPanel mediaControlPanel2 = this.f$0;
                int i5 = this.f$2;
                int i6 = this.f$3;
                int i7 = this.f$4;
                Drawable drawable = (Drawable) this.f$1;
                String str3 = this.f$5;
                String str4 = this.f$6;
                int i8 = this.f$7;
                ImageView imageView = (ImageView) ((ArrayList) mediaControlPanel2.mRecommendationViewHolder.mediaCoverItems).get(i5);
                Matrix matrix = new Matrix(imageView.getImageMatrix());
                matrix.postScale(1.25f, 1.25f, i6 * 0.5f, i7 * 0.5f);
                imageView.setImageMatrix(matrix);
                imageView.setImageDrawable(drawable);
                ImageView imageView2 = (ImageView) ((ArrayList) mediaControlPanel2.mRecommendationViewHolder.mediaAppIcons).get(i5);
                imageView2.clearColorFilter();
                try {
                    imageView2.setImageDrawable(mediaControlPanel2.mContext.getPackageManager().getApplicationIcon(str3));
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w("MediaControlPanel", "Cannot find icon for package " + str3, e);
                    imageView2.setImageResource(R.drawable.ic_music_note);
                }
                Trace.endAsyncSection(str4, i8);
                break;
        }
    }

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda24(MediaControlPanel mediaControlPanel, SmartspaceAction smartspaceAction, int i, int i2, int i3, String str, String str2, int i4) {
        this.f$0 = mediaControlPanel;
        this.f$1 = smartspaceAction;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = i3;
        this.f$5 = str;
        this.f$6 = str2;
        this.f$7 = i4;
    }
}
