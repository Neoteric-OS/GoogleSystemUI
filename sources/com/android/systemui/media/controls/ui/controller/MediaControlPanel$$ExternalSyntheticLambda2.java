package com.android.systemui.media.controls.ui.controller;

import android.app.WallpaperColors;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.Style;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaControlPanel f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda2(MediaControlPanel mediaControlPanel, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = mediaControlPanel;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.mSeekBarViewModel.updateController((MediaController) this.f$1);
                break;
            case 1:
                MediaControlPanel mediaControlPanel = this.f$0;
                SmartspaceMediaData smartspaceMediaData = (SmartspaceMediaData) this.f$1;
                mediaControlPanel.closeGuts(false);
                ((MediaDataManager) mediaControlPanel.mMediaDataManagerLazy.get()).dismissSmartspaceRecommendation(334L, smartspaceMediaData.targetId);
                Intent intent = smartspaceMediaData.dismissIntent;
                if (intent != null) {
                    if (intent.getComponent() != null && intent.getComponent().getClassName().equals("com.google.android.apps.gsa.staticplugins.opa.smartspace.ExportedSmartspaceTrampolineActivity")) {
                        mediaControlPanel.mContext.startActivity(intent);
                        break;
                    } else {
                        mediaControlPanel.mBroadcastSender.sendBroadcast(intent);
                        break;
                    }
                } else {
                    Log.w("MediaControlPanel", "Cannot create dismiss action click action: extras missing dismiss_intent.");
                    break;
                }
                break;
            case 2:
                MediaControlPanel mediaControlPanel2 = this.f$0;
                Drawable drawable = (Drawable) this.f$1;
                mediaControlPanel2.getClass();
                ((ExecutorImpl) mediaControlPanel2.mMainExecutor).execute(new MediaControlPanel$$ExternalSyntheticLambda2(mediaControlPanel2, new ColorScheme(WallpaperColors.fromDrawable(drawable), true, Style.TONAL_SPOT), 3));
                break;
            case 3:
                MediaControlPanel mediaControlPanel3 = this.f$0;
                ColorScheme colorScheme = (ColorScheme) this.f$1;
                if (mediaControlPanel3.mRecommendationViewHolder != null) {
                    int s800 = colorScheme.mAccent2.getS800();
                    final int s50 = colorScheme.mNeutral1.getS50();
                    final int s200 = colorScheme.mNeutral2.getS200();
                    mediaControlPanel3.mRecommendationViewHolder.cardTitle.setTextColor(s50);
                    mediaControlPanel3.mRecommendationViewHolder.recommendations.setBackgroundTintList(ColorStateList.valueOf(s800));
                    final int i = 0;
                    ((ArrayList) mediaControlPanel3.mRecommendationViewHolder.mediaTitles).forEach(new Consumer() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda31
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i2 = i;
                            int i3 = s50;
                            switch (i2) {
                                case 0:
                                    ((TextView) obj).setTextColor(i3);
                                    break;
                                case 1:
                                    ((TextView) obj).setTextColor(i3);
                                    break;
                                default:
                                    ((SeekBar) obj).setProgressTintList(ColorStateList.valueOf(i3));
                                    break;
                            }
                        }
                    });
                    final int i2 = 1;
                    ((ArrayList) mediaControlPanel3.mRecommendationViewHolder.mediaSubtitles).forEach(new Consumer() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda31
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i22 = i2;
                            int i3 = s200;
                            switch (i22) {
                                case 0:
                                    ((TextView) obj).setTextColor(i3);
                                    break;
                                case 1:
                                    ((TextView) obj).setTextColor(i3);
                                    break;
                                default:
                                    ((SeekBar) obj).setProgressTintList(ColorStateList.valueOf(i3));
                                    break;
                            }
                        }
                    });
                    final int i3 = 2;
                    ((ArrayList) mediaControlPanel3.mRecommendationViewHolder.mediaProgressBars).forEach(new Consumer() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda31
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i22 = i3;
                            int i32 = s50;
                            switch (i22) {
                                case 0:
                                    ((TextView) obj).setTextColor(i32);
                                    break;
                                case 1:
                                    ((TextView) obj).setTextColor(i32);
                                    break;
                                default:
                                    ((SeekBar) obj).setProgressTintList(ColorStateList.valueOf(i32));
                                    break;
                            }
                        }
                    });
                    mediaControlPanel3.mRecommendationViewHolder.gutsViewHolder.setColors(colorScheme);
                    break;
                }
                break;
            default:
                MediaControlPanel mediaControlPanel4 = this.f$0;
                MediaData mediaData = (MediaData) this.f$1;
                if (mediaControlPanel4.mKey == null) {
                    Log.w("MediaControlPanel", "Dismiss media with null notification. Token uid=" + mediaData.token.getUid());
                    break;
                } else {
                    mediaControlPanel4.closeGuts(false);
                    if (!((MediaDataManager) mediaControlPanel4.mMediaDataManagerLazy.get()).dismissMediaData(mediaControlPanel4.mKey, 334L, true)) {
                        Log.w("MediaControlPanel", "Manager failed to dismiss media " + mediaControlPanel4.mKey);
                        mediaControlPanel4.mMediaCarouselController.removePlayer(mediaControlPanel4.mKey, false, false, true);
                        break;
                    }
                }
                break;
        }
    }
}
