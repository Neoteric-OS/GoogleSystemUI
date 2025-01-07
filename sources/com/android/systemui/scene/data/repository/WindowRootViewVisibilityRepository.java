package com.android.systemui.scene.data.repository;

import com.android.internal.statusbar.IStatusBarService;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WindowRootViewVisibilityRepository {
    public final StateFlowImpl _isLockscreenOrShadeVisible;
    public final ReadonlyStateFlow isLockscreenOrShadeVisible;
    public final IStatusBarService statusBarService;
    public final Executor uiBgExecutor;

    public WindowRootViewVisibilityRepository(IStatusBarService iStatusBarService, Executor executor) {
        this.statusBarService = iStatusBarService;
        this.uiBgExecutor = executor;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isLockscreenOrShadeVisible = MutableStateFlow;
        this.isLockscreenOrShadeVisible = new ReadonlyStateFlow(MutableStateFlow);
    }

    public final void onLockscreenOrShadeInteractive(final int i, final boolean z) {
        this.uiBgExecutor.execute(new WindowRootViewVisibilityRepository$executeServiceCallOnUiBg$1(new Function0() { // from class: com.android.systemui.scene.data.repository.WindowRootViewVisibilityRepository$onLockscreenOrShadeInteractive$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                WindowRootViewVisibilityRepository.this.statusBarService.onPanelRevealed(z, i);
                return Unit.INSTANCE;
            }
        }));
    }

    public final void onLockscreenOrShadeNotInteractive() {
        this.uiBgExecutor.execute(new WindowRootViewVisibilityRepository$executeServiceCallOnUiBg$1(new Function0() { // from class: com.android.systemui.scene.data.repository.WindowRootViewVisibilityRepository$onLockscreenOrShadeNotInteractive$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                WindowRootViewVisibilityRepository.this.statusBarService.onPanelHidden();
                return Unit.INSTANCE;
            }
        }));
    }
}
