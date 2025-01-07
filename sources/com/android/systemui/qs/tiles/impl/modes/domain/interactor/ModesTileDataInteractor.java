package com.android.systemui.qs.tiles.impl.modes.domain.interactor;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.qs.tiles.impl.modes.domain.model.ModesTileModel;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.statusbar.policy.domain.model.ActiveZenModes;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesTileDataInteractor implements QSTileDataInteractor {
    public final CoroutineDispatcher bgDispatcher;
    public final Context context;
    public final ZenModeInteractor zenModeInteractor;

    public ModesTileDataInteractor(Context context, ZenModeInteractor zenModeInteractor, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.zenModeInteractor = zenModeInteractor;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(false);
    }

    public final ModesTileModel buildTileData(ActiveZenModes activeZenModes) {
        boolean z = !activeZenModes.modeNames.isEmpty();
        Drawable drawable = this.context.getDrawable(R.drawable.jog_dial_bg);
        Intrinsics.checkNotNull(drawable);
        return new ModesTileModel(z, activeZenModes.modeNames, new Icon.Loaded(drawable, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentTileModel(kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.impl.modes.domain.interactor.ModesTileDataInteractor.getCurrentTileModel(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        ZenModeInteractor zenModeInteractor = this.zenModeInteractor;
        String walkStackForClassName = FlowExtKt.walkStackForClassName();
        return FlowKt.distinctUntilChanged(FlowKt.flowOn(new ModesTileDataInteractor$tileData$$inlined$map$default$1(new FlowExtKt$withTraceName$1(zenModeInteractor.activeModes, walkStackForClassName), walkStackForClassName, this), this.bgDispatcher));
    }
}
