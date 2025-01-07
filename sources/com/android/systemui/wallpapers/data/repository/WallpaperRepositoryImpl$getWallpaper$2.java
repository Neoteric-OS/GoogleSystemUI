package com.android.systemui.wallpapers.data.repository;

import com.android.systemui.user.data.model.SelectedUserModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WallpaperRepositoryImpl$getWallpaper$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ SelectedUserModel $selectedUser;
    int label;
    final /* synthetic */ WallpaperRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WallpaperRepositoryImpl$getWallpaper$2(WallpaperRepositoryImpl wallpaperRepositoryImpl, SelectedUserModel selectedUserModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wallpaperRepositoryImpl;
        this.$selectedUser = selectedUserModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new WallpaperRepositoryImpl$getWallpaper$2(this.this$0, this.$selectedUser, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WallpaperRepositoryImpl$getWallpaper$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.this$0.wallpaperManager.getWallpaperInfoForUser(this.$selectedUser.userInfo.id);
    }
}
