package com.android.systemui.qs.tiles.impl.airplane.domain.interactor;

import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AirplaneModeTileUserActionInteractor implements QSTileUserActionInteractor {
    public final AirplaneModeInteractor airplaneModeInteractor;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public AirplaneModeTileUserActionInteractor(AirplaneModeInteractor airplaneModeInteractor, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.airplaneModeInteractor = airplaneModeInteractor;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object handleInput(com.android.systemui.qs.tiles.base.interactor.QSTileInput r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor$handleInput$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor$handleInput$1 r0 = (com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor$handleInput$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor$handleInput$1 r0 = new com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor$handleInput$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r6 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r6
            r0.<init>(r4, r6)
        L1a:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r4 = r0.L$1
            r5 = r4
            com.android.systemui.qs.tiles.base.interactor.QSTileInput r5 = (com.android.systemui.qs.tiles.base.interactor.QSTileInput) r5
            java.lang.Object r4 = r0.L$0
            com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor r4 = (com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L59
        L32:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.qs.tiles.viewmodel.QSTileUserAction r6 = r5.action
            boolean r2 = r6 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.Click
            if (r2 == 0) goto L75
            java.lang.Object r6 = r5.data
            com.android.systemui.qs.tiles.impl.airplane.domain.model.AirplaneModeTileModel r6 = (com.android.systemui.qs.tiles.impl.airplane.domain.model.AirplaneModeTileModel) r6
            boolean r6 = r6.isEnabled
            r6 = r6 ^ r3
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor r2 = r4.airplaneModeInteractor
            java.lang.Object r6 = r2.setIsAirplaneMode(r6, r0)
            if (r6 != r1) goto L59
            return r1
        L59:
            com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor$SetResult r6 = (com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor.SetResult) r6
            int r6 = r6.ordinal()
            if (r6 == r3) goto L62
            goto L8c
        L62:
            com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl r4 = r4.qsTileIntentUserActionHandler
            com.android.systemui.qs.tiles.viewmodel.QSTileUserAction r5 = r5.action
            com.android.systemui.animation.Expandable r5 = r5.getExpandable()
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS"
            r6.<init>(r0)
            com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl.handle$default(r4, r5, r6)
            goto L8c
        L75:
            boolean r5 = r6 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.LongClick
            if (r5 == 0) goto L8a
            com.android.systemui.qs.tiles.viewmodel.QSTileUserAction$LongClick r6 = (com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.LongClick) r6
            com.android.systemui.animation.Expandable r5 = r6.expandable
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.settings.AIRPLANE_MODE_SETTINGS"
            r6.<init>(r0)
            com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl r4 = r4.qsTileIntentUserActionHandler
            com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl.handle$default(r4, r5, r6)
            goto L8c
        L8a:
            boolean r4 = r6 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.ToggleClick
        L8c:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor.handleInput(com.android.systemui.qs.tiles.base.interactor.QSTileInput, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
