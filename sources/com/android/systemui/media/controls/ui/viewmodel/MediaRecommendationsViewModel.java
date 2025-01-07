package com.android.systemui.media.controls.ui.viewmodel;

import android.content.Context;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecommendationsViewModel {
    public final Context applicationContext;
    public final MediaRecommendationsInteractor interactor;
    public int location;
    public final MediaUiEventLogger logger;
    public final Flow mediaRecsCard;

    public MediaRecommendationsViewModel(Context context, CoroutineDispatcher coroutineDispatcher, MediaRecommendationsInteractor mediaRecommendationsInteractor, MediaUiEventLogger mediaUiEventLogger) {
        this.applicationContext = context;
        this.interactor = mediaRecommendationsInteractor;
        this.logger = mediaUiEventLogger;
        FlowKt.flowOn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(mediaRecommendationsInteractor.onAnyMediaConfigurationChange, new MediaRecommendationsViewModel$special$$inlined$flatMapLatest$1(null, this))), coroutineDispatcher);
        this.location = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x005b, code lost:
    
        r10 = r10.getString("com.google.android.apps.gsa.smartspace.extra.SMARTSPACE_INTENT");
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$onClicked(com.android.systemui.media.controls.ui.viewmodel.MediaRecommendationsViewModel r10, com.android.systemui.animation.Expandable r11, android.content.Intent r12, java.lang.String r13, com.android.internal.logging.InstanceId r14, int r15) {
        /*
            r10.getClass()
            if (r12 == 0) goto L98
            android.os.Bundle r0 = r12.getExtras()
            if (r0 != 0) goto Ld
            goto L98
        Ld:
            r0 = 0
            com.android.systemui.media.controls.util.MediaUiEventLogger r1 = r10.logger
            r2 = -1
            if (r15 != r2) goto L1b
            com.android.internal.logging.UiEventLogger r1 = r1.logger
            com.android.systemui.media.controls.util.MediaUiEvent r3 = com.android.systemui.media.controls.util.MediaUiEvent.MEDIA_RECOMMENDATION_CARD_TAP
            r1.logWithInstanceId(r3, r0, r13, r14)
            goto L26
        L1b:
            com.android.internal.logging.UiEventLogger r4 = r1.logger
            com.android.systemui.media.controls.util.MediaUiEvent r5 = com.android.systemui.media.controls.util.MediaUiEvent.MEDIA_RECOMMENDATION_ITEM_TAP
            r6 = 0
            r7 = r13
            r8 = r14
            r9 = r15
            r4.logWithInstanceIdAndPosition(r5, r6, r7, r8, r9)
        L26:
            com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor r14 = r10.interactor
            com.android.systemui.media.controls.data.repository.MediaFilterRepository r1 = r14.repository
            r1.mediaFromRecPackageName = r13
            int r10 = r10.location
            r4 = 760(0x2f8, float:1.065E-42)
            if (r15 != r2) goto L46
            boolean r13 = com.android.systemui.media.controls.util.MediaSmartspaceLogger.DEBUG
            int r10 = com.android.systemui.media.controls.util.MediaSmartspaceLogger.Companion.getSurface(r10)
            int r5 = com.android.systemui.media.controls.util.MediaSmartspaceLogger.Companion.getSurface(r10)
            com.android.systemui.media.controls.data.repository.MediaFilterRepository r3 = r14.repository
            r8 = 28
            r6 = 0
            r7 = 0
            com.android.systemui.media.controls.data.repository.MediaFilterRepository.logSmartspaceCardUserEvent$default(r3, r4, r5, r6, r7, r8)
            goto L55
        L46:
            boolean r13 = com.android.systemui.media.controls.util.MediaSmartspaceLogger.DEBUG
            int r5 = com.android.systemui.media.controls.util.MediaSmartspaceLogger.Companion.getSurface(r10)
            com.android.systemui.media.controls.data.repository.MediaFilterRepository r3 = r14.repository
            r7 = 0
            r8 = 16
            r6 = r15
            com.android.systemui.media.controls.data.repository.MediaFilterRepository.logSmartspaceCardUserEvent$default(r3, r4, r5, r6, r7, r8)
        L55:
            android.os.Bundle r10 = r12.getExtras()
            if (r10 == 0) goto L7f
            java.lang.String r13 = "com.google.android.apps.gsa.smartspace.extra.SMARTSPACE_INTENT"
            java.lang.String r10 = r10.getString(r13)
            if (r10 != 0) goto L64
            goto L7f
        L64:
            r13 = 1
            android.content.Intent r13 = android.content.Intent.parseUri(r10, r13)     // Catch: java.net.URISyntaxException -> L70
            java.lang.String r15 = "KEY_OPEN_IN_FOREGROUND"
            boolean r10 = r13.getBooleanExtra(r15, r0)     // Catch: java.net.URISyntaxException -> L70
            goto L80
        L70:
            r13 = move-exception
            java.lang.String r15 = "Failed to create intent from URI: "
            java.lang.String r10 = r15.concat(r10)
            java.lang.String r15 = "MediaRecommendationsInteractor"
            android.util.Log.wtf(r15, r10)
            r13.printStackTrace()
        L7f:
            r10 = r0
        L80:
            if (r10 == 0) goto L92
            r10 = 31
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            com.android.systemui.animation.ActivityTransitionAnimator$Controller r10 = r11.activityTransitionController(r10)
            com.android.systemui.plugins.ActivityStarter r11 = r14.activityStarter
            r11.postStartActivityDismissingKeyguard(r12, r0, r10)
            goto L9f
        L92:
            android.content.Context r10 = r14.applicationContext
            r10.startActivity(r12)
            goto L9f
        L98:
            java.lang.String r10 = "MediaRecommendationsViewModel"
            java.lang.String r11 = "No tap action can be set up"
            android.util.Log.e(r10, r11)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.viewmodel.MediaRecommendationsViewModel.access$onClicked(com.android.systemui.media.controls.ui.viewmodel.MediaRecommendationsViewModel, com.android.systemui.animation.Expandable, android.content.Intent, java.lang.String, com.android.internal.logging.InstanceId, int):void");
    }
}
