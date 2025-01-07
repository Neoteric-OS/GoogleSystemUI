package com.android.systemui.common.domain.interactor;

import com.android.systemui.common.data.repository.PackageChangeRepository;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageChangeInteractor {
    public final PackageChangeRepository packageChangeRepository;
    public final SelectedUserInteractor userInteractor;

    public PackageChangeInteractor(PackageChangeRepository packageChangeRepository, SelectedUserInteractor selectedUserInteractor) {
        this.packageChangeRepository = packageChangeRepository;
        this.userInteractor = selectedUserInteractor;
    }
}
