package com.android.systemui.statusbar.notification.stack.domain.interactor;

import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.stack.data.repository.NotificationPlaceholderRepository;
import com.android.systemui.statusbar.notification.stack.data.repository.NotificationViewHeightRepository;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationStackAppearanceInteractor {
    public final ReadonlyStateFlow alphaForBrightnessMirror;
    public final ReadonlyStateFlow constrainedAvailableSpace;
    public final ReadonlyStateFlow isCurrentGestureOverscroll;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isExpandingFromHeadsUp;
    public final NotificationPlaceholderRepository placeholderRepository;
    public final ReadonlyStateFlow scrolledToTop;
    public final ReadonlyStateFlow shadeScrimBounds;
    public final Flow shadeScrimRounding;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 shouldCloseGuts;
    public final ReadonlyStateFlow syntheticScroll;
    public final NotificationViewHeightRepository viewHeightRepository;

    public NotificationStackAppearanceInteractor(NotificationViewHeightRepository notificationViewHeightRepository, NotificationPlaceholderRepository notificationPlaceholderRepository, SceneInteractor sceneInteractor, ShadeInteractor shadeInteractor) {
        new ReadonlyStateFlow(notificationPlaceholderRepository.shadeScrimBounds);
        FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((ShadeInteractorImpl) shadeInteractor).$$delegate_1.getShadeMode(), new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE), new NotificationStackAppearanceInteractor$shadeScrimRounding$1(3, null)));
        new ReadonlyStateFlow(notificationPlaceholderRepository.alphaForBrightnessMirror);
        new ReadonlyStateFlow(notificationPlaceholderRepository.constrainedAvailableSpace);
        new ReadonlyStateFlow(notificationPlaceholderRepository.scrolledToTop);
        new ReadonlyStateFlow(notificationViewHeightRepository.syntheticScroll);
        new ReadonlyStateFlow(notificationViewHeightRepository.isCurrentGestureOverscroll);
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(sceneInteractor.isSceneContainerUserInputOngoing, notificationViewHeightRepository.isCurrentGestureInGuts, new NotificationStackAppearanceInteractor$shouldCloseGuts$1(3, null));
    }
}
