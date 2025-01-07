package com.android.systemui.brightness.data.repository;

import android.content.Context;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.utils.UserRestrictionChecker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BrightnessPolicyRepositoryImpl {
    public final Context applicationContext;
    public final Flow restrictionPolicy;
    public final UserRestrictionChecker userRestrictionChecker;

    public BrightnessPolicyRepositoryImpl(UserRepositoryImpl userRepositoryImpl, UserRestrictionChecker userRestrictionChecker, Context context, CoroutineDispatcher coroutineDispatcher) {
        this.userRestrictionChecker = userRestrictionChecker;
        this.applicationContext = context;
        this.restrictionPolicy = FlowKt.flowOn(FlowKt.mapLatest(new BrightnessPolicyRepositoryImpl$restrictionPolicy$1(this, null), userRepositoryImpl.selectedUserInfo), coroutineDispatcher);
    }
}
