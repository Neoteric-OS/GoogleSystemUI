package com.android.systemui.notetask;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.notetask.NoteTaskBubblesController;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.function.Consumer;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskBubblesController$NoteTaskBubblesService$onBind$1 extends Binder implements INoteTaskBubblesService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ NoteTaskBubblesController.NoteTaskBubblesService this$0;

    public NoteTaskBubblesController$NoteTaskBubblesService$onBind$1(NoteTaskBubblesController.NoteTaskBubblesService noteTaskBubblesService) {
        this.this$0 = noteTaskBubblesService;
        attachInterface(this, "com.android.systemui.notetask.INoteTaskBubblesService");
    }

    @Override // com.android.systemui.notetask.INoteTaskBubblesService
    public final boolean areBubblesAvailable() {
        return this.this$0.mOptionalBubbles.isPresent();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.notetask.INoteTaskBubblesService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.notetask.INoteTaskBubblesService");
            return true;
        }
        if (i == 1) {
            boolean areBubblesAvailable = areBubblesAvailable();
            parcel2.writeNoException();
            parcel2.writeBoolean(areBubblesAvailable);
        } else {
            if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
            UserHandle userHandle = (UserHandle) parcel.readTypedObject(UserHandle.CREATOR);
            Icon icon = (Icon) parcel.readTypedObject(Icon.CREATOR);
            parcel.enforceNoDataAvail();
            showOrHideAppBubble(intent, userHandle, icon);
            parcel2.writeNoException();
        }
        return true;
    }

    @Override // com.android.systemui.notetask.INoteTaskBubblesService
    public final void showOrHideAppBubble(final Intent intent, final UserHandle userHandle, final Icon icon) {
        final NoteTaskBubblesController.NoteTaskBubblesService noteTaskBubblesService = this.this$0;
        noteTaskBubblesService.mOptionalBubbles.ifPresentOrElse(new Consumer() { // from class: com.android.systemui.notetask.NoteTaskBubblesController$NoteTaskBubblesService$onBind$1$showOrHideAppBubble$1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                final Intent intent2 = intent;
                final UserHandle userHandle2 = userHandle;
                final Icon icon2 = icon;
                final BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) ((Bubbles) obj);
                ((HandlerExecutor) bubblesImpl.this$0.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        String str;
                        BubbleController.BubblesImpl bubblesImpl2 = BubbleController.BubblesImpl.this;
                        Intent intent3 = intent2;
                        UserHandle userHandle3 = userHandle2;
                        Icon icon3 = icon2;
                        BubbleController bubbleController = bubblesImpl2.this$0;
                        bubbleController.getClass();
                        if (intent3 == null || intent3.getPackage() == null) {
                            StringBuilder sb = new StringBuilder("App bubble failed to show, invalid intent: ");
                            sb.append(intent3);
                            if (intent3 != null) {
                                str = " with package: " + intent3.getPackage();
                            } else {
                                str = " ";
                            }
                            sb.append(str);
                            Log.w("Bubbles", sb.toString());
                            return;
                        }
                        String appBubbleKeyForApp = Bubble.getAppBubbleKeyForApp(intent3.getPackage(), userHandle3);
                        if (BubbleController.isResizableActivity(intent3, BubbleController.getPackageManagerForUser(userHandle3.getIdentifier(), bubbleController.mContext), appBubbleKeyForApp)) {
                            BubbleData bubbleData = bubbleController.mBubbleData;
                            Bubble bubbleInStackWithKey = bubbleData.getBubbleInStackWithKey(appBubbleKeyForApp);
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                String valueOf = String.valueOf(appBubbleKeyForApp);
                                String valueOf2 = String.valueOf(bubbleInStackWithKey);
                                BubbleStackView bubbleStackView = bubbleController.mStackView;
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -7069550301890004257L, 0, valueOf, valueOf2, String.valueOf(bubbleStackView != null ? Integer.valueOf(bubbleStackView.getVisibility()) : "null"), String.valueOf(bubbleController.mIsStatusBarShade));
                            }
                            if (bubbleInStackWithKey == null) {
                                Bubble overflowBubbleWithKey = bubbleData.getOverflowBubbleWithKey(appBubbleKeyForApp);
                                if (overflowBubbleWithKey != null) {
                                    bubbleData.dismissBubbleWithKey(5, appBubbleKeyForApp);
                                    overflowBubbleWithKey.mAppIntent = intent3;
                                } else {
                                    overflowBubbleWithKey = new Bubble(intent3, userHandle3, icon3, Bubble.getAppBubbleKeyForApp(intent3.getPackage(), userHandle3), bubbleController.mMainExecutor, bubbleController.mBackgroundExecutor);
                                }
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -1428177178668878758L, 0, String.valueOf(appBubbleKeyForApp));
                                }
                                overflowBubbleWithKey.setShouldAutoExpand(true);
                                bubbleController.inflateAndAdd(overflowBubbleWithKey, true, false);
                                return;
                            }
                            BubbleViewProvider bubbleViewProvider = bubbleData.mSelectedBubble;
                            if (!bubbleData.mExpanded) {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -9169024674370276453L, 0, String.valueOf(appBubbleKeyForApp));
                                }
                                bubbleData.setSelectedBubbleAndExpandStack(bubbleInStackWithKey);
                            } else if (bubbleViewProvider != null && appBubbleKeyForApp.equals(bubbleViewProvider.getKey())) {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 8825060701225958311L, 0, appBubbleKeyForApp);
                                }
                                bubbleController.collapseStack();
                            } else {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -6686345507646064545L, 0, String.valueOf(appBubbleKeyForApp));
                                }
                                bubbleData.setSelectedBubbleInternal(bubbleInStackWithKey);
                                bubbleData.dispatchPendingChanges();
                            }
                        }
                    }
                });
            }
        }, new Runnable(intent, noteTaskBubblesService, icon) { // from class: com.android.systemui.notetask.NoteTaskBubblesController$NoteTaskBubblesService$onBind$1$showOrHideAppBubble$2
            @Override // java.lang.Runnable
            public final void run() {
                NoteTaskBubblesController$NoteTaskBubblesService$onBind$1 noteTaskBubblesController$NoteTaskBubblesService$onBind$1 = NoteTaskBubblesController$NoteTaskBubblesService$onBind$1.this;
                boolean z = Build.IS_DEBUGGABLE;
                Reflection.getOrCreateKotlinClass(noteTaskBubblesController$NoteTaskBubblesService$onBind$1.getClass()).getSimpleName();
            }
        });
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
