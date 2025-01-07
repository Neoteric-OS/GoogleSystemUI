package com.android.systemui.media.controls.ui.controller;

import android.animation.ValueAnimator;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                LoadingEffect loadingEffect = (LoadingEffect) obj;
                if (loadingEffect.state == LoadingEffect.AnimationState.MAIN) {
                    ValueAnimator valueAnimator = loadingEffect.currentAnimator;
                    if (valueAnimator != null) {
                        valueAnimator.pause();
                    }
                    loadingEffect.currentAnimator = null;
                    loadingEffect.playEaseOut();
                    break;
                }
                break;
            default:
                final MediaControlPanel mediaControlPanel = (MediaControlPanel) obj;
                MediaData mediaData = mediaControlPanel.mMediaData;
                final MediaButton mediaButton = mediaData.semanticActions;
                mediaControlPanel.bindScrubbingTime(mediaData);
                MediaControlPanel.SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING.forEach(new Consumer() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda23
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        MediaControlPanel mediaControlPanel2 = MediaControlPanel.this;
                        MediaButton mediaButton2 = mediaButton;
                        Integer num = (Integer) obj2;
                        mediaControlPanel2.getClass();
                        mediaControlPanel2.setSemanticButtonVisibleAndAlpha(num.intValue(), mediaButton2.getActionById(num.intValue()), mediaButton2);
                    }
                });
                if (!mediaControlPanel.mMetadataAnimationHandler.isRunning()) {
                    mediaControlPanel.mMediaViewController.refreshState();
                    break;
                }
                break;
        }
    }
}
