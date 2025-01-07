package com.android.systemui.biometrics.domain.interactor;

import android.content.Context;
import com.android.systemui.biometrics.data.repository.DisplayStateRepository;
import com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepository;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$1;
import com.android.systemui.unfold.compat.ScreenSizeFoldProvider;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayStateInteractorImpl implements DisplayStateInteractor {
    public final ReadonlyStateFlow currentRotation;
    public final DisplayRepositoryImpl$special$$inlined$map$1 displayChanges;
    public final Flow isDefaultDisplayOff;
    public final ReadonlyStateFlow isInRearDisplayMode;
    public final ReadonlyStateFlow isLargeScreen;
    public final ScreenSizeFoldProvider screenSizeFoldProvider;

    public DisplayStateInteractorImpl(CoroutineScope coroutineScope, Context context, Executor executor, DisplayStateRepository displayStateRepository, DisplayRepository displayRepository) {
        ScreenSizeFoldProvider screenSizeFoldProvider = new ScreenSizeFoldProvider();
        screenSizeFoldProvider.callbacks = new ArrayList();
        screenSizeFoldProvider.onConfigurationChange(context.getResources().getConfiguration());
        this.screenSizeFoldProvider = screenSizeFoldProvider;
        DisplayRepositoryImpl displayRepositoryImpl = (DisplayRepositoryImpl) displayRepository;
        this.displayChanges = displayRepositoryImpl.displayChangeEvent;
        FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new DisplayStateInteractorImpl$isFolded$1(this, executor, null)), coroutineScope, SharingStarted.Companion.Eagerly, Boolean.FALSE);
        DisplayStateRepositoryImpl displayStateRepositoryImpl = (DisplayStateRepositoryImpl) displayStateRepository;
        this.isInRearDisplayMode = displayStateRepositoryImpl.isInRearDisplayMode;
        this.currentRotation = displayStateRepositoryImpl.currentRotation;
        this.isDefaultDisplayOff = displayRepositoryImpl.defaultDisplayOff;
        this.isLargeScreen = displayStateRepositoryImpl.isLargeScreen;
    }
}
