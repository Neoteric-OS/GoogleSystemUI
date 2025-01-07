package com.android.systemui.media.controls.ui.controller;

import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.util.MediaUiEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda5 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaControlPanel f$0;

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda5(MediaControlPanel mediaControlPanel, int i) {
        this.$r8$classId = i;
        this.f$0 = mediaControlPanel;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        InstanceId instanceId;
        switch (this.$r8$classId) {
            case 0:
                MediaControlPanel mediaControlPanel = this.f$0;
                String str = mediaControlPanel.mPackageName;
                if (str != null && (instanceId = mediaControlPanel.mInstanceId) != null) {
                    mediaControlPanel.mLogger.logger.logWithInstanceId(MediaUiEvent.ACTION_SEEK, mediaControlPanel.mUid, str, instanceId);
                }
                mediaControlPanel.logSmartspaceCardReported(760, 0, 0);
                break;
            case 1:
                MediaControlPanel mediaControlPanel2 = this.f$0;
                mediaControlPanel2.setMediaCoversVisibility(mediaControlPanel2.getNumberOfFittedRecommendations());
                break;
            default:
                this.f$0.mMediaViewController.refreshState();
                break;
        }
        return Unit.INSTANCE;
    }
}
