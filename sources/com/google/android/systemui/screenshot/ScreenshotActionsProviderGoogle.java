package com.google.android.systemui.screenshot;

import android.app.Notification;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.UserHandle;
import android.util.Log;
import androidx.appcompat.content.res.AppCompatResources;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.screenshot.ActionExecutor;
import com.android.systemui.screenshot.ScreenshotActionsController;
import com.android.systemui.screenshot.ScreenshotData;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.screenshot.ScreenshotSavedResult;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonAppearance;
import com.android.wm.shell.R;
import com.google.android.apps.pixel.pearl.suggestion.IPearlActionService;
import com.google.android.apps.pixel.pearl.suggestion.PearlActionClientImpl;
import java.util.List;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotActionsProviderGoogle {
    public final PearlActionClientImpl actionClient;
    public final ActionExecutor actionExecutor;
    public final ScreenshotActionsController.ActionsCallback actionsCallback;
    public boolean addedScrollChip;
    public final CoroutineScope applicationScope;
    public long containerId;
    public final Context context;
    public final boolean isCurrentProfile;
    public Runnable onScrollClick;
    public final boolean pearlEnabled;
    public Lambda pendingAction;
    public final int reminderButtonId;
    public final ScreenshotData request;
    public final UUID requestId;
    public final String requestIdString;
    public ScreenshotSavedResult result;
    public final SmartActionsProvider smartActionsProvider;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ScreenshotActionsProviderGoogle.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PearlActionClientImpl pearlActionClientImpl = ScreenshotActionsProviderGoogle.this.actionClient;
                this.label = 1;
                if (pearlActionClientImpl.connect(this) == coroutineSingletons) {
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

    /* JADX WARN: Removed duplicated region for block: B:12:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ScreenshotActionsProviderGoogle(android.content.Context r19, com.google.android.systemui.screenshot.SmartActionsProvider r20, com.android.internal.logging.UiEventLogger r21, com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$70 r22, com.google.android.systemui.screenshot.ThumbnailObserverGoogle r23, kotlinx.coroutines.CoroutineScope r24, java.util.UUID r25, com.android.systemui.screenshot.ScreenshotData r26, com.android.systemui.screenshot.ActionExecutor r27, com.android.systemui.screenshot.ScreenshotActionsController.ActionsCallback r28) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle.<init>(android.content.Context, com.google.android.systemui.screenshot.SmartActionsProvider, com.android.internal.logging.UiEventLogger, com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$70, com.google.android.systemui.screenshot.ThumbnailObserverGoogle, kotlinx.coroutines.CoroutineScope, java.util.UUID, com.android.systemui.screenshot.ScreenshotData, com.android.systemui.screenshot.ActionExecutor, com.android.systemui.screenshot.ScreenshotActionsController$ActionsCallback):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void access$onDeferrableActionTapped(ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle, Function1 function1) {
        Unit unit;
        ScreenshotSavedResult screenshotSavedResult = screenshotActionsProviderGoogle.result;
        if (screenshotSavedResult != null) {
            function1.invoke(screenshotSavedResult);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            screenshotActionsProviderGoogle.pendingAction = (Lambda) function1;
        }
    }

    public final void mayContainerAndScreenshotUriReady() {
        ScreenshotSavedResult screenshotSavedResult = this.result;
        if (screenshotSavedResult != null) {
            long j = this.containerId;
            if (j != 0) {
                Uri uri = screenshotSavedResult.uri;
                IPearlActionService iPearlActionService = this.actionClient.actionService;
                if (iPearlActionService != null) {
                    IPearlActionService.Stub.Proxy proxy = (IPearlActionService.Stub.Proxy) iPearlActionService;
                    Parcel obtain = Parcel.obtain(proxy.mRemote);
                    try {
                        obtain.writeInterfaceToken("com.google.android.apps.pixel.pearl.suggestion.IPearlActionService");
                        obtain.writeLong(j);
                        obtain.writeTypedObject(uri, 0);
                        proxy.mRemote.transact(3, obtain, null, 1);
                    } finally {
                        obtain.recycle();
                    }
                }
            }
        }
    }

    public final void onAssistContent(AssistContent assistContent) {
        if (this.pearlEnabled) {
            Reflection.getOrCreateKotlinClass(ScreenshotActionsProviderGoogle.class).getSimpleName();
            BuildersKt.launch$default(this.applicationScope, null, null, new ScreenshotActionsProviderGoogle$onAssistContent$2(this, assistContent, null), 3);
        }
    }

    public final void onScrollChipReady(Runnable runnable) {
        this.onScrollClick = runnable;
        if (this.addedScrollChip) {
            return;
        }
        this.actionsCallback.provideActionButton(new ActionButtonAppearance(AppCompatResources.getDrawable(R.drawable.ic_screenshot_scroll, this.context), this.pearlEnabled ? null : this.context.getResources().getString(R.string.screenshot_scroll_label), this.context.getResources().getString(R.string.screenshot_scroll_label), false, 24), new Function0() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$onScrollChipReady$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Runnable runnable2 = ScreenshotActionsProviderGoogle.this.onScrollClick;
                if (runnable2 != null) {
                    runnable2.run();
                }
                return Unit.INSTANCE;
            }
        });
        this.addedScrollChip = true;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void setCompletedScreenshot(ScreenshotSavedResult screenshotSavedResult) {
        if (this.result != null) {
            Log.e("ScreenshotActionsProvider", "Got a second completed screenshot for existing request!");
            return;
        }
        this.result = screenshotSavedResult;
        if (this.pearlEnabled) {
            mayContainerAndScreenshotUriReady();
        }
        ?? r0 = this.pendingAction;
        if (r0 != 0) {
            r0.invoke(screenshotSavedResult);
        }
        if (this.isCurrentProfile) {
            final Function1 function1 = new Function1() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$setCompletedScreenshot$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    final ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle = ScreenshotActionsProviderGoogle.this;
                    for (final Notification.Action action : (List) obj) {
                        ScreenshotActionsController.ActionsCallback actionsCallback = screenshotActionsProviderGoogle.actionsCallback;
                        Drawable loadDrawable = action.getIcon().loadDrawable(screenshotActionsProviderGoogle.context);
                        CharSequence charSequence = action.title;
                        actionsCallback.provideActionButton(new ActionButtonAppearance(loadDrawable, charSequence, charSequence, false, 16), new Function0() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$setCompletedScreenshot$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                ScreenshotActionsProviderGoogle.this.actionExecutor.sendPendingIntent(action.actionIntent);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            };
            SmartActionsProvider smartActionsProvider = this.smartActionsProvider;
            ScreenshotData screenshotData = this.request;
            Bitmap bitmap = screenshotData.bitmap;
            if (bitmap == null) {
                return;
            }
            ComponentName componentName = screenshotData.topComponent;
            if (componentName == null) {
                componentName = new ComponentName("", "");
            }
            UserHandle userOrDefault = screenshotData.getUserOrDefault();
            Uri uri = screenshotSavedResult.uri;
            ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType = ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType.REGULAR_SMART_ACTIONS;
            Function1 function12 = new Function1() { // from class: com.google.android.systemui.screenshot.SmartActionsProvider$requestSmartActions$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Function1.this.invoke((List) obj);
                    return Unit.INSTANCE;
                }
            };
            BuildersKt.launch$default(smartActionsProvider.applicationScope, null, null, new SmartActionsProvider$requestSmartActionsAsync$1(smartActionsProvider, this.requestIdString, bitmap, componentName, userOrDefault, uri, screenshotSmartActionType, 500L, function12, null), 3);
        }
    }
}
