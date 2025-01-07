package com.android.systemui.statusbar.notification;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpTouchHelper implements Gefingerpoken {
    public final NotificationStackScrollLayout.AnonymousClass6 mCallback;
    public boolean mCollapseSnoozes;
    public final HeadsUpManager mHeadsUpManager;
    public float mInitialTouchX;
    public float mInitialTouchY;
    public final NotificationPanelViewController.AnonymousClass10 mPanel;
    public ExpandableNotificationRow mPickedChild;
    public final IStatusBarService mStatusBarService;
    public final float mTouchSlop;
    public boolean mTouchingHeadsUpView;
    public boolean mTrackingHeadsUp;
    public int mTrackingPointer;

    public HeadsUpTouchHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, NotificationStackScrollLayout.AnonymousClass6 anonymousClass6, NotificationPanelViewController.AnonymousClass10 anonymousClass10) {
        Context context;
        this.mHeadsUpManager = headsUpManager;
        this.mStatusBarService = iStatusBarService;
        this.mCallback = anonymousClass6;
        this.mPanel = anonymousClass10;
        context = ((ViewGroup) NotificationStackScrollLayout.this).mContext;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        HeadsUpManagerPhone headsUpManagerPhone;
        BaseHeadsUpManager.HeadsUpEntry headsUpEntry;
        int pointerId;
        if (!this.mTouchingHeadsUpView && motionEvent.getActionMasked() != 0) {
            return false;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.mTrackingPointer);
        if (findPointerIndex < 0) {
            this.mTrackingPointer = motionEvent.getPointerId(0);
            findPointerIndex = 0;
        }
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        int actionMasked = motionEvent.getActionMasked();
        HeadsUpManager headsUpManager = this.mHeadsUpManager;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float f = y - this.mInitialTouchY;
                    if (this.mTouchingHeadsUpView && Math.abs(f) > this.mTouchSlop && Math.abs(f) > Math.abs(x - this.mInitialTouchX)) {
                        setTrackingHeadsUp$1(true);
                        this.mCollapseSnoozes = f < 0.0f;
                        this.mInitialTouchX = x;
                        this.mInitialTouchY = y;
                        int translationY = (int) (this.mPickedChild.getTranslationY() + r11.mActualHeight);
                        NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                        notificationPanelViewController.setHeadsUpDraggingStartingHeight(translationY);
                        NotificationPanelViewController.m860$$Nest$mstartExpandMotion(notificationPanelViewController, x, y, true, translationY);
                        ((HeadsUpManagerPhone) headsUpManager).unpinAll();
                        try {
                            this.mStatusBarService.clearNotificationEffects();
                        } catch (RemoteException unused) {
                        }
                        this.mTrackingPointer = -1;
                        this.mPickedChild = null;
                        this.mTouchingHeadsUpView = false;
                        return true;
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 6 && this.mTrackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                        int i = motionEvent.getPointerId(0) != pointerId ? 0 : 1;
                        this.mTrackingPointer = motionEvent.getPointerId(i);
                        this.mInitialTouchX = motionEvent.getX(i);
                        this.mInitialTouchY = motionEvent.getY(i);
                    }
                }
            }
            ExpandableNotificationRow expandableNotificationRow = this.mPickedChild;
            if (expandableNotificationRow != null && this.mTouchingHeadsUpView && (headsUpEntry = (headsUpManagerPhone = (HeadsUpManagerPhone) headsUpManager).getHeadsUpEntry(expandableNotificationRow.mEntry.mSbn.getKey())) != null) {
                ((SystemClockImpl) headsUpManagerPhone.mSystemClock).getClass();
                if (SystemClock.elapsedRealtime() < headsUpEntry.mPostTime) {
                    this.mTrackingPointer = -1;
                    this.mPickedChild = null;
                    this.mTouchingHeadsUpView = false;
                    return true;
                }
            }
            this.mTrackingPointer = -1;
            this.mPickedChild = null;
            this.mTouchingHeadsUpView = false;
        } else {
            this.mInitialTouchY = y;
            this.mInitialTouchX = x;
            setTrackingHeadsUp$1(false);
            NotificationStackScrollLayout.AnonymousClass6 anonymousClass6 = this.mCallback;
            ExpandableView childAtRawPosition = NotificationStackScrollLayout.this.getChildAtRawPosition(x, y);
            this.mTouchingHeadsUpView = false;
            boolean z = childAtRawPosition instanceof ExpandableNotificationRow;
            NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
            if (z) {
                ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) childAtRawPosition;
                boolean z2 = !notificationStackScrollLayout.mIsExpanded && expandableNotificationRow2.mIsHeadsUp && expandableNotificationRow2.mIsPinned;
                this.mTouchingHeadsUpView = z2;
                if (z2) {
                    this.mPickedChild = expandableNotificationRow2;
                }
            } else if (childAtRawPosition == null && !notificationStackScrollLayout.mIsExpanded) {
                BaseHeadsUpManager.HeadsUpEntry topHeadsUpEntry = ((BaseHeadsUpManager) headsUpManager).getTopHeadsUpEntry();
                NotificationEntry notificationEntry = topHeadsUpEntry != null ? topHeadsUpEntry.mEntry : null;
                if (notificationEntry != null && notificationEntry.isRowPinned()) {
                    this.mPickedChild = notificationEntry.row;
                    this.mTouchingHeadsUpView = true;
                }
            }
        }
        return false;
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mTrackingHeadsUp) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            this.mTrackingPointer = -1;
            this.mPickedChild = null;
            this.mTouchingHeadsUpView = false;
            setTrackingHeadsUp$1(false);
        }
        return true;
    }

    public final void setTrackingHeadsUp$1(boolean z) {
        this.mTrackingHeadsUp = z;
        ((HeadsUpManagerPhone) this.mHeadsUpManager).mTrackingHeadsUp = z;
        ExpandableNotificationRow expandableNotificationRow = z ? this.mPickedChild : null;
        NotificationPanelViewController.AnonymousClass10 anonymousClass10 = this.mPanel;
        if (expandableNotificationRow != null) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            NotificationPanelViewController notificationPanelViewController2 = NotificationPanelViewController.this;
            notificationPanelViewController2.mTrackedHeadsUpNotification = expandableNotificationRow;
            for (int i = 0; i < notificationPanelViewController2.mTrackingHeadsUpListeners.size(); i++) {
                ((Consumer) notificationPanelViewController2.mTrackingHeadsUpListeners.get(i)).accept(expandableNotificationRow);
            }
            notificationPanelViewController.mExpandingFromHeadsUp = true;
        }
    }
}
