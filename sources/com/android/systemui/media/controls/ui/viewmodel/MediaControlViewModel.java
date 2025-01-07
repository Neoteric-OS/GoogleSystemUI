package com.android.systemui.media.controls.ui.viewmodel;

import android.content.Context;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControlViewModel {
    public final Context applicationContext;
    public final Executor backgroundExecutor;
    public final MediaControlInteractor interactor;
    public boolean isAnyButtonClicked;
    public boolean isPlaying;
    public int location;
    public final MediaUiEventLogger logger;
    public final Flow player;
    public static final List SEMANTIC_ACTIONS_COMPACT = CollectionsKt__CollectionsKt.listOf(Integer.valueOf(R.id.actionPlayPause), Integer.valueOf(R.id.actionPrev), Integer.valueOf(R.id.actionNext));
    public static final List SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING = CollectionsKt__CollectionsKt.listOf(Integer.valueOf(R.id.actionPrev), Integer.valueOf(R.id.actionNext));
    public static final List SEMANTIC_ACTIONS_ALL = CollectionsKt__CollectionsKt.listOf(Integer.valueOf(R.id.actionPlayPause), Integer.valueOf(R.id.actionPrev), Integer.valueOf(R.id.actionNext), Integer.valueOf(R.id.action0), Integer.valueOf(R.id.action1));

    public MediaControlViewModel(Context context, CoroutineDispatcher coroutineDispatcher, Executor executor, MediaControlInteractor mediaControlInteractor, MediaUiEventLogger mediaUiEventLogger) {
        this.applicationContext = context;
        this.backgroundExecutor = executor;
        this.interactor = mediaControlInteractor;
        this.logger = mediaUiEventLogger;
        FlowKt.flowOn(FlowKt.distinctUntilChanged(new Function2() { // from class: com.android.systemui.media.controls.ui.viewmodel.MediaControlViewModel$player$2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                boolean z;
                boolean z2;
                MediaPlayerViewModel mediaPlayerViewModel = (MediaPlayerViewModel) obj;
                MediaPlayerViewModel mediaPlayerViewModel2 = (MediaPlayerViewModel) obj2;
                if (mediaPlayerViewModel2 != null || mediaPlayerViewModel != null) {
                    z = false;
                    if (mediaPlayerViewModel2 != null) {
                        mediaPlayerViewModel2.getClass();
                        z2 = false;
                        if (mediaPlayerViewModel != null && Intrinsics.areEqual(mediaPlayerViewModel.backgroundCover, mediaPlayerViewModel2.backgroundCover) && Intrinsics.areEqual(mediaPlayerViewModel2.appIcon, mediaPlayerViewModel.appIcon) && mediaPlayerViewModel2.useGrayColorFilter == mediaPlayerViewModel.useGrayColorFilter && mediaPlayerViewModel2.artistName.equals(mediaPlayerViewModel.artistName) && mediaPlayerViewModel2.titleName.equals(mediaPlayerViewModel.titleName) && mediaPlayerViewModel2.isExplicitVisible == mediaPlayerViewModel.isExplicitVisible && mediaPlayerViewModel2.canShowTime == mediaPlayerViewModel.canShowTime && mediaPlayerViewModel2.playTurbulenceNoise == mediaPlayerViewModel.playTurbulenceNoise && mediaPlayerViewModel2.useSemanticActions == mediaPlayerViewModel.useSemanticActions) {
                            List list = mediaPlayerViewModel.actionButtons;
                            Iterator it = mediaPlayerViewModel2.actionButtons.iterator();
                            int i = 0;
                            while (true) {
                                if (it.hasNext()) {
                                    Object next = it.next();
                                    int i2 = i + 1;
                                    if (i < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                        throw null;
                                    }
                                    MediaActionViewModel mediaActionViewModel = (MediaActionViewModel) next;
                                    MediaActionViewModel mediaActionViewModel2 = (MediaActionViewModel) list.get(i);
                                    if (mediaActionViewModel2 == null) {
                                        mediaActionViewModel.getClass();
                                        break;
                                    }
                                    if (!Intrinsics.areEqual(mediaActionViewModel.contentDescription, mediaActionViewModel2.contentDescription) || mediaActionViewModel.isVisibleWhenScrubbing != mediaActionViewModel2.isVisibleWhenScrubbing || mediaActionViewModel.notVisibleValue != mediaActionViewModel2.notVisibleValue || mediaActionViewModel.showInCollapsed != mediaActionViewModel2.showInCollapsed || !Intrinsics.areEqual(mediaActionViewModel.rebindId, mediaActionViewModel2.rebindId) || !Intrinsics.areEqual(mediaActionViewModel.buttonId, mediaActionViewModel2.buttonId) || mediaActionViewModel.isEnabled != mediaActionViewModel2.isEnabled) {
                                        break;
                                    }
                                    i = i2;
                                } else {
                                    MediaOutputSwitcherViewModel mediaOutputSwitcherViewModel = mediaPlayerViewModel2.outputSwitcher;
                                    MediaOutputSwitcherViewModel mediaOutputSwitcherViewModel2 = mediaPlayerViewModel.outputSwitcher;
                                    if (mediaOutputSwitcherViewModel.isTapEnabled == mediaOutputSwitcherViewModel2.isTapEnabled && mediaOutputSwitcherViewModel.deviceString.equals(mediaOutputSwitcherViewModel2.deviceString) && mediaOutputSwitcherViewModel.isCurrentBroadcastApp == mediaOutputSwitcherViewModel2.isCurrentBroadcastApp && mediaOutputSwitcherViewModel.isIntentValid == mediaOutputSwitcherViewModel2.isIntentValid && mediaOutputSwitcherViewModel.alpha == mediaOutputSwitcherViewModel2.alpha) {
                                        z2 = true;
                                    }
                                }
                            }
                        }
                    } else {
                        z2 = false;
                    }
                    return Boolean.valueOf(z);
                }
                z = true;
                return Boolean.valueOf(z);
            }
        }, FlowKt.transformLatest(mediaControlInteractor.onAnyMediaConfigurationChange, new MediaControlViewModel$special$$inlined$flatMapLatest$1(null, this))), coroutineDispatcher);
        this.location = -1;
    }
}
