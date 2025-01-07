package com.android.systemui.statusbar.notification.row;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import android.view.DragEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.app.animation.Interpolators;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.logging.NotificationPanelLogger$NotificationPanelEvent;
import com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl;
import com.android.systemui.statusbar.notification.logging.nano.Notifications$NotificationList;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.wm.shell.R;
import com.google.protobuf.nano.MessageNano;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExpandableNotificationRowDragController {
    public final Context mContext;
    public final HeadsUpManager mHeadsUpManager;
    public final int mIconSize;
    public final NotificationPanelLoggerImpl mNotificationPanelLogger;
    public final ShadeController mShadeController;

    public ExpandableNotificationRowDragController(Context context, HeadsUpManager headsUpManager, ShadeController shadeController, NotificationPanelLoggerImpl notificationPanelLoggerImpl) {
        this.mContext = context;
        this.mHeadsUpManager = headsUpManager;
        this.mShadeController = shadeController;
        this.mNotificationPanelLogger = notificationPanelLoggerImpl;
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drag_and_drop_icon_size);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r7v6, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.content.pm.PackageManager] */
    public void startDragAndDrop(View view) {
        ExpandableNotificationRow expandableNotificationRow = view instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) view : null;
        Notification notification = expandableNotificationRow.mEntry.mSbn.getNotification();
        PendingIntent pendingIntent = notification.contentIntent;
        if (pendingIntent == null) {
            pendingIntent = notification.fullScreenIntent;
        }
        ShadeController shadeController = this.mShadeController;
        if (pendingIntent == null) {
            if (!expandableNotificationRow.mIsPinned) {
                shadeController.animateCollapseShade(0, true, false, 1.1f);
            }
            Toast.makeText(this.mContext, R.string.drag_split_not_supported, 0).show();
            return;
        }
        ?? packageName = expandableNotificationRow.mEntry.mSbn.getPackageName();
        ?? packageManager = this.mContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 795136);
            if (applicationInfo != null) {
                packageName = packageManager.getApplicationIcon(applicationInfo);
            } else {
                Log.d("ExpandableNotificationRowDragController", " application info is null ");
                packageName = packageManager.getDefaultActivityIcon();
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d("ExpandableNotificationRowDragController", "can not find package with : " + packageName);
            packageName = packageManager.getDefaultActivityIcon();
        }
        Bitmap createBitmap = Bitmap.createBitmap(packageName.getIntrinsicWidth(), packageName.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        packageName.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        packageName.draw(canvas);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageBitmap(createBitmap);
        int i = this.mIconSize;
        imageView.layout(0, 0, i, i);
        ClipDescription clipDescription = new ClipDescription("Drag And Drop", new String[]{"application/vnd.android.activity"});
        Intent intent = new Intent();
        intent.putExtra("android.intent.extra.PENDING_INTENT", pendingIntent);
        intent.putExtra("android.intent.extra.USER", Process.myUserHandle());
        ClipData.Item item = new ClipData.Item(intent);
        item.getIntent().putExtra("android.intent.extra.LOGGING_INSTANCE_ID", (Parcelable) new InstanceIdSequence(Integer.MAX_VALUE).newInstanceId());
        ClipData clipData = new ClipData(clipDescription, item);
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(imageView);
        view.setOnDragListener(new View.OnDragListener(this) { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController$$ExternalSyntheticLambda0
            public final /* synthetic */ ExpandableNotificationRowDragController f$0;

            @Override // android.view.View.OnDragListener
            public final boolean onDrag(View view2, DragEvent dragEvent) {
                ExpandableNotificationRow expandableNotificationRow2;
                NotificationClicker.AnonymousClass1 anonymousClass1;
                int action = dragEvent.getAction();
                if (action == 1) {
                    return true;
                }
                if (action != 4) {
                    return false;
                }
                if (!dragEvent.getResult()) {
                    final SurfaceControl dragSurface = dragEvent.getDragSurface();
                    final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    ofFloat.setDuration(200L);
                    ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
                    ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController$$ExternalSyntheticLambda1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            SurfaceControl.Transaction transaction2 = transaction;
                            transaction2.setAlpha(dragSurface, 1.0f - valueAnimator.getAnimatedFraction());
                            transaction2.apply();
                        }
                    });
                    ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController.1
                        public boolean mCanceled = false;

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            transaction.remove(dragSurface);
                            transaction.apply();
                            transaction.close();
                            this.mCanceled = true;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            if (this.mCanceled) {
                                return;
                            }
                            transaction.remove(dragSurface);
                            transaction.apply();
                            transaction.close();
                        }
                    });
                    ofFloat.start();
                } else if ((view2 instanceof ExpandableNotificationRow) && (anonymousClass1 = (expandableNotificationRow2 = (ExpandableNotificationRow) view2).mOnDragSuccessListener) != null) {
                    NotificationEntry notificationEntry = expandableNotificationRow2.mEntry;
                    StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = NotificationClicker.this.mNotificationActivityStarter;
                    NotificationVisibility obtain = ((NotificationVisibilityProviderImpl) statusBarNotificationActivityStarter.mVisibilityProvider).obtain(notificationEntry);
                    boolean z = (notificationEntry.mSbn.getNotification().flags & 16) == 16;
                    String str = notificationEntry.mKey;
                    if (z || statusBarNotificationActivityStarter.mRemoteInputManager.isNotificationKeptForRemoteInputHistory(str)) {
                        statusBarNotificationActivityStarter.mMainThreadHandler.post(new StatusBarNotificationActivityStarter$$ExternalSyntheticLambda1(statusBarNotificationActivityStarter, statusBarNotificationActivityStarter.mOnUserInteractionCallback.registerFutureDismissal(notificationEntry, 1), 1));
                    }
                    statusBarNotificationActivityStarter.mClickNotifier.onNotificationClick(str, obtain);
                    statusBarNotificationActivityStarter.mIsCollapsingToShowActivityOverLockscreen = false;
                }
                view2.setOnDragListener(null);
                return true;
            }
        });
        if (view.startDragAndDrop(clipData, dragShadowBuilder, null, 2304)) {
            NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
            this.mNotificationPanelLogger.getClass();
            Notifications$NotificationList notificationProto = NotificationPanelLoggerImpl.toNotificationProto(Collections.singletonList(notificationEntry));
            SysUiStatsLog.write(MessageNano.toByteArray(notificationProto), NotificationPanelLogger$NotificationPanelEvent.NOTIFICATION_DRAG.getId(), notificationProto.notifications.length);
            view.performHapticFeedback(0);
            if (expandableNotificationRow.mIsPinned) {
                ((BaseHeadsUpManager) this.mHeadsUpManager).releaseAllImmediately();
            } else {
                shadeController.animateCollapseShade(0, true, false, 1.1f);
            }
        }
    }
}
