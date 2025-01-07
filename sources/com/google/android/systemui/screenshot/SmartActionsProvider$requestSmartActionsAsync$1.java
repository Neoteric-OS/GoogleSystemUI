package com.google.android.systemui.screenshot;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SmartActionsProvider$requestSmartActionsAsync$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType $actionType;
    final /* synthetic */ ComponentName $component;
    final /* synthetic */ String $id;
    final /* synthetic */ Bitmap $image;
    final /* synthetic */ Function1 $onActions;
    final /* synthetic */ long $timeoutMs;
    final /* synthetic */ Uri $uri;
    final /* synthetic */ UserHandle $user;
    int label;
    final /* synthetic */ SmartActionsProvider this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.screenshot.SmartActionsProvider$requestSmartActionsAsync$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType $actionType;
        final /* synthetic */ ComponentName $component;
        final /* synthetic */ String $id;
        final /* synthetic */ Bitmap $image;
        final /* synthetic */ Function1 $onActions;
        final /* synthetic */ long $timeoutMs;
        final /* synthetic */ Uri $uri;
        final /* synthetic */ UserHandle $user;
        int label;
        final /* synthetic */ SmartActionsProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SmartActionsProvider smartActionsProvider, String str, Bitmap bitmap, ComponentName componentName, UserHandle userHandle, Uri uri, ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType, long j, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.this$0 = smartActionsProvider;
            this.$id = str;
            this.$image = bitmap;
            this.$component = componentName;
            this.$user = userHandle;
            this.$uri = uri;
            this.$actionType = screenshotSmartActionType;
            this.$timeoutMs = j;
            this.$onActions = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$id, this.$image, this.$component, this.$user, this.$uri, this.$actionType, this.$timeoutMs, this.$onActions, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SmartActionsProvider smartActionsProvider = this.this$0;
            String str = this.$id;
            Bitmap bitmap = this.$image;
            ComponentName componentName = this.$component;
            UserHandle userHandle = this.$user;
            Uri uri = this.$uri;
            ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType = this.$actionType;
            long j = this.$timeoutMs;
            Function1 function1 = this.$onActions;
            smartActionsProvider.getClass();
            ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus = ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR;
            ScreenshotNotificationSmartActionsProvider.ScreenshotOp screenshotOp = ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS;
            boolean z = !(userHandle != Process.myUserHandle()) && DeviceConfig.getBoolean("systemui", "enable_screenshot_notification_smart_actions", true);
            Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
            if (!z) {
                Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
                function1.invoke(EmptyList.INSTANCE);
            } else if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
                Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
                function1.invoke(EmptyList.INSTANCE);
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                try {
                    try {
                        List list = (List) smartActionsProvider.smartActions.getActions(str, uri, bitmap, componentName, screenshotSmartActionType, userHandle).get(j, TimeUnit.MILLISECONDS);
                        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
                        Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
                        smartActionsProvider.notifyScreenshotOp(str, screenshotOp, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, uptimeMillis2);
                        Intrinsics.checkNotNull(list);
                        function1.invoke(list);
                    } catch (Throwable th) {
                        long uptimeMillis3 = SystemClock.uptimeMillis() - uptimeMillis;
                        Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
                        smartActionsProvider.notifyScreenshotOp(str, screenshotOp, th instanceof TimeoutException ? ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.TIMEOUT : screenshotOpStatus, uptimeMillis3);
                        function1.invoke(EmptyList.INSTANCE);
                    }
                } catch (Throwable unused) {
                    long uptimeMillis4 = SystemClock.uptimeMillis() - uptimeMillis;
                    Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
                    smartActionsProvider.notifyScreenshotOp(str, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.REQUEST_SMART_ACTIONS, screenshotOpStatus, uptimeMillis4);
                    function1.invoke(EmptyList.INSTANCE);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartActionsProvider$requestSmartActionsAsync$1(SmartActionsProvider smartActionsProvider, String str, Bitmap bitmap, ComponentName componentName, UserHandle userHandle, Uri uri, ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType, long j, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = smartActionsProvider;
        this.$id = str;
        this.$image = bitmap;
        this.$component = componentName;
        this.$user = userHandle;
        this.$uri = uri;
        this.$actionType = screenshotSmartActionType;
        this.$timeoutMs = j;
        this.$onActions = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SmartActionsProvider$requestSmartActionsAsync$1(this.this$0, this.$id, this.$image, this.$component, this.$user, this.$uri, this.$actionType, this.$timeoutMs, this.$onActions, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SmartActionsProvider$requestSmartActionsAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SmartActionsProvider smartActionsProvider = this.this$0;
            CoroutineDispatcher coroutineDispatcher = smartActionsProvider.bgDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(smartActionsProvider, this.$id, this.$image, this.$component, this.$user, this.$uri, this.$actionType, this.$timeoutMs, this.$onActions, null);
            this.label = 1;
            if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
