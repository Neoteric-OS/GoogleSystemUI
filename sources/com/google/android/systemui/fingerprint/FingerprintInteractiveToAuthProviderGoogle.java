package com.google.android.systemui.fingerprint;

import android.content.Context;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FingerprintInteractiveToAuthProviderGoogle {
    public final Context context;
    public final Flow enabledForCurrentUser;
    public final SecureSettings secureSettings;

    public FingerprintInteractiveToAuthProviderGoogle(CoroutineDispatcher coroutineDispatcher, Context context, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor) {
        this.context = context;
        this.secureSettings = secureSettings;
        this.enabledForCurrentUser = FlowKt.flowOn(FlowKt.transformLatest(selectedUserInteractor.selectedUser, new FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1(null, this)), coroutineDispatcher);
    }
}
