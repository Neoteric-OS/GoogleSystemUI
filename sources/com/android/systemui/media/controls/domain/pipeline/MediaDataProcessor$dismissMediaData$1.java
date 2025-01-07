package com.android.systemui.media.controls.domain.pipeline;

import android.media.session.MediaSession;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import java.util.Iterator;
import java.util.Map;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataProcessor$dismissMediaData$1 implements Runnable {
    public final /* synthetic */ String $key;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaDataProcessor this$0;

    public /* synthetic */ MediaDataProcessor$dismissMediaData$1(MediaDataProcessor mediaDataProcessor, String str, int i) {
        this.$r8$classId = i;
        this.this$0 = mediaDataProcessor;
        this.$key = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSession.Token token;
        switch (this.$r8$classId) {
            case 0:
                MediaData mediaData = (MediaData) ((Map) ((StateFlowImpl) this.this$0.mediaDataRepository.mediaEntries.$$delegate_0).getValue()).get(this.$key);
                if (mediaData != null) {
                    MediaDataProcessor mediaDataProcessor = this.this$0;
                    if (mediaData.playbackLocation == 0 && (token = mediaData.token) != null) {
                        mediaDataProcessor.mediaControllerFactory.create(token).getTransportControls().stop();
                        break;
                    }
                }
                break;
            default:
                MediaDataProcessor mediaDataProcessor2 = this.this$0;
                String str = this.$key;
                Iterator it = mediaDataProcessor2.internalListeners.iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataRemoved(str, true);
                }
                break;
        }
    }
}
