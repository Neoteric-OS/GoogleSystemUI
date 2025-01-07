package com.android.systemui.qs.tileimpl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.metrics.LogMaker;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSEvent;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.SideLabelTileLayout;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tiles.ModesTile;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QSTileImpl implements QSTile, LifecycleOwner, Dumpable {
    public static final Object ARG_SHOW_TRANSIENT_ENABLING = new Object();
    public final ActivityStarter mActivityStarter;
    public final Context mContext;
    protected RestrictedLockUtils.EnforcedAdmin mEnforcedAdmin;
    public final FalsingManager mFalsingManager;
    public final H mHandler;
    public final QSHost mHost;
    public final InstanceId mInstanceId;
    public int mIsFullQs;
    public final MetricsLogger mMetricsLogger;
    public final QSLogger mQSLogger;
    public volatile int mReadyState;
    public final StatusBarStateController mStatusBarStateController;
    public String mTileSpec;
    public QSTile.State mTmpState;
    public final QsEventLoggerImpl mUiEventLogger;
    public final Handler mUiHandler;
    public final String TAG = "Tile.".concat(getClass().getSimpleName());
    public final boolean DEBUG = Log.isLoggable("Tile", 3);
    public final ArraySet mListeners = new ArraySet();
    public int mClickEventId = 0;
    public final ArraySet mCallbacks = new ArraySet();
    public final Object mStaleListener = new Object();
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    public QSTile.State mState = newTileState();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class DrawableIcon extends QSTile.Icon {
        public final Drawable mDrawable;
        public final Drawable mInvisibleDrawable;

        public DrawableIcon(Drawable drawable) {
            this.mDrawable = drawable;
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                this.mInvisibleDrawable = constantState.newDrawable();
                return;
            }
            if (!(drawable instanceof SignalDrawable)) {
                Log.w("QSTileImpl", "DrawableIcon: drawable has null ConstantState and is not a SignalDrawable");
            }
            this.mInvisibleDrawable = drawable;
        }

        public boolean equals(Object obj) {
            return (obj instanceof DrawableIcon) && ((DrawableIcon) obj).mDrawable == this.mDrawable;
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final Drawable getDrawable(Context context) {
            return this.mDrawable;
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final Drawable getInvisibleDrawable(Context context) {
            return this.mInvisibleDrawable;
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final int hashCode() {
            return Objects.hash(this.mDrawable);
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public String toString() {
            return "DrawableIcon";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DrawableIconWithRes extends DrawableIcon {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        protected static final int STALE = 11;

        public H(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            QSTileImpl qSTileImpl = QSTileImpl.this;
            try {
                int i = message.what;
                boolean z = true;
                if (i == 1) {
                    QSTile.Callback callback = (QSTile.Callback) message.obj;
                    qSTileImpl.mCallbacks.add(callback);
                    callback.onStateChanged(qSTileImpl.mState);
                    return;
                }
                if (i == 8) {
                    qSTileImpl.mCallbacks.clear();
                    return;
                }
                if (i == 9) {
                    qSTileImpl.mCallbacks.remove((QSTile.Callback) message.obj);
                    return;
                }
                if (i == 2) {
                    if (qSTileImpl.mState.disabledByPolicy) {
                        qSTileImpl.mActivityStarter.postStartActivityDismissingKeyguard(RestrictedLockUtils.getShowAdminSupportDetailsIntent(qSTileImpl.mEnforcedAdmin), 0);
                        return;
                    } else {
                        qSTileImpl.mQSLogger.logHandleClick(message.arg1, qSTileImpl.mTileSpec);
                        qSTileImpl.handleClick((Expandable) message.obj);
                        return;
                    }
                }
                if (i == 3) {
                    qSTileImpl.mQSLogger.logHandleSecondaryClick(message.arg1, qSTileImpl.mTileSpec);
                    qSTileImpl.handleSecondaryClick((Expandable) message.obj);
                    return;
                }
                if (i == 4) {
                    qSTileImpl.mQSLogger.logHandleLongClick(message.arg1, qSTileImpl.mTileSpec);
                    qSTileImpl.handleLongClick((Expandable) message.obj);
                    return;
                }
                if (i == 5) {
                    qSTileImpl.handleRefreshState(message.obj);
                    return;
                }
                if (i == 6) {
                    qSTileImpl.handleUserSwitch(message.arg1);
                    return;
                }
                if (i == 7) {
                    qSTileImpl.handleDestroy();
                    return;
                }
                if (i == 10) {
                    Object obj = message.obj;
                    if (message.arg1 == 0) {
                        z = false;
                    }
                    QSTileImpl.m849$$Nest$mhandleSetListeningInternal(qSTileImpl, obj, z);
                    return;
                }
                if (i == 11) {
                    qSTileImpl.handleStale();
                } else if (i == 12) {
                    qSTileImpl.handleInitialize();
                } else {
                    throw new IllegalArgumentException("Unknown msg: " + message.what);
                }
            } catch (Throwable th) {
                Log.w(qSTileImpl.TAG, AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Error in ", null), th);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResourceIcon extends QSTile.Icon {
        public static final SparseArray ICONS = new SparseArray();
        public final int mResId;

        public ResourceIcon(int i) {
            this.mResId = i;
        }

        public static synchronized QSTile.Icon get(int i) {
            QSTile.Icon icon;
            synchronized (ResourceIcon.class) {
                SparseArray sparseArray = ICONS;
                icon = (QSTile.Icon) sparseArray.get(i);
                if (icon == null) {
                    icon = new ResourceIcon(i);
                    sparseArray.put(i, icon);
                }
            }
            return icon;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof ResourceIcon) && ((ResourceIcon) obj).mResId == this.mResId;
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final Drawable getDrawable(Context context) {
            return context.getDrawable(this.mResId);
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final Drawable getInvisibleDrawable(Context context) {
            return context.getDrawable(this.mResId);
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public final String toString() {
            return String.format("ResourceIcon[resId=0x%08x]", Integer.valueOf(this.mResId));
        }
    }

    /* renamed from: -$$Nest$mhandleSetListeningInternal, reason: not valid java name */
    public static void m849$$Nest$mhandleSetListeningInternal(QSTileImpl qSTileImpl, Object obj, boolean z) {
        Handler handler = qSTileImpl.mUiHandler;
        String str = qSTileImpl.TAG;
        boolean z2 = qSTileImpl.DEBUG;
        if (z) {
            if (qSTileImpl.mListeners.add(obj) && qSTileImpl.mListeners.size() == 1) {
                if (z2) {
                    Log.d(str, "handleSetListening true");
                }
                qSTileImpl.handleSetListening(z);
                handler.post(new QSTileImpl$$ExternalSyntheticLambda0(qSTileImpl, 2));
            }
        } else if (qSTileImpl.mListeners.remove(obj) && qSTileImpl.mListeners.size() == 0) {
            if (z2) {
                Log.d(str, "handleSetListening false");
            }
            qSTileImpl.handleSetListening(z);
            handler.post(new QSTileImpl$$ExternalSyntheticLambda0(qSTileImpl, 3));
        }
        Iterator it = qSTileImpl.mListeners.iterator();
        while (it.hasNext()) {
            if (SideLabelTileLayout.class.equals(it.next().getClass())) {
                qSTileImpl.mIsFullQs = 1;
                return;
            }
        }
        qSTileImpl.mIsFullQs = 0;
    }

    public QSTileImpl(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger) {
        this.mHost = qSHost;
        this.mContext = ((QSHostAdapter) qSHost).context;
        this.mInstanceId = qsEventLoggerImpl.sequence.newInstanceId();
        this.mUiEventLogger = qsEventLoggerImpl;
        this.mUiHandler = handler;
        this.mHandler = new H(looper);
        this.mFalsingManager = falsingManager;
        this.mQSLogger = qSLogger;
        this.mMetricsLogger = metricsLogger;
        this.mStatusBarStateController = statusBarStateController;
        this.mActivityStarter = activityStarter;
        QSTile.State newTileState = newTileState();
        this.mTmpState = newTileState;
        QSTile.State state = this.mState;
        String str = this.mTileSpec;
        state.spec = str;
        newTileState.spec = str;
        handler.post(new QSTileImpl$$ExternalSyntheticLambda0(this, 0));
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void addCallback(QSTile.Callback callback) {
        this.mHandler.obtainMessage(1, callback).sendToTarget();
    }

    public final void checkIfRestrictionEnforcedByAdminOnly(QSTile.State state, String str) {
        Context context = this.mContext;
        QSHost qSHost = this.mHost;
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, str, ((QSHostAdapter) qSHost).getUserId());
        if (checkIfRestrictionEnforced == null || RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, str, ((QSHostAdapter) qSHost).getUserId())) {
            state.disabledByPolicy = false;
            this.mEnforcedAdmin = null;
        } else {
            state.disabledByPolicy = true;
            this.mEnforcedAdmin = checkIfRestrictionEnforced;
        }
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void click(Expandable expandable) {
        MetricsLogger metricsLogger = this.mMetricsLogger;
        LogMaker type = new LogMaker(925).setType(4);
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        metricsLogger.write(populate(type.addTaggedData(1592, Integer.valueOf(statusBarStateController.getState()))));
        this.mUiEventLogger.logWithInstanceId(QSEvent.QS_ACTION_CLICK, 0, getMetricsSpec(), this.mInstanceId);
        int i = this.mClickEventId;
        this.mClickEventId = i + 1;
        this.mQSLogger.logTileClick(this.mTileSpec, statusBarStateController.getState(), this.mState.state, i);
        if (this.mFalsingManager.isFalseTap(1)) {
            return;
        }
        this.mHandler.obtainMessage(2, i, 0, expandable).sendToTarget();
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void destroy() {
        this.mHandler.sendEmptyMessage(7);
    }

    @Override // com.android.systemui.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName().concat(":"));
        printWriter.print("    ");
        printWriter.println(this.mState.toString());
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final InstanceId getInstanceId() {
        return this.mInstanceId;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public abstract Intent getLongClickIntent();

    @Override // com.android.systemui.plugins.qs.QSTile
    public int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public String getMetricsSpec() {
        return this.mTileSpec;
    }

    public long getStaleTimeout() {
        return 600000L;
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final QSTile.State getState() {
        return this.mState;
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public abstract CharSequence getTileLabel();

    @Override // com.android.systemui.plugins.qs.QSTile
    public final String getTileSpec() {
        return this.mTileSpec;
    }

    public abstract void handleClick(Expandable expandable);

    public void handleDestroy() {
        this.mQSLogger.logTileDestroyed(this.mTileSpec);
        if (this.mListeners.size() != 0) {
            handleSetListening(false);
            this.mListeners.clear();
        }
        this.mCallbacks.clear();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mUiHandler.post(new QSTileImpl$$ExternalSyntheticLambda0(this, 1));
    }

    public void handleLongClick(Expandable expandable) {
        this.mActivityStarter.postStartActivityDismissingKeyguard(getLongClickIntent(), 0, expandable != null ? expandable.activityTransitionController(32) : null);
    }

    public final void handleRefreshState(Object obj) {
        handleUpdateState(this.mTmpState, obj);
        boolean copyTo = this.mTmpState.copyTo(this.mState);
        if (this.mReadyState == 1) {
            this.mReadyState = 2;
            copyTo = true;
        }
        if (copyTo) {
            this.mQSLogger.logTileUpdated(this.mState, this.mTileSpec);
            if (!this.mCallbacks.isEmpty()) {
                for (int i = 0; i < this.mCallbacks.size(); i++) {
                    ((QSTile.Callback) this.mCallbacks.valueAt(i)).onStateChanged(this.mState);
                }
            }
        }
        this.mHandler.removeMessages(11);
        this.mHandler.sendEmptyMessageDelayed(11, getStaleTimeout());
        setListening(this.mStaleListener, false);
    }

    public void handleSecondaryClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleSetListening(boolean z) {
        String str = this.mTileSpec;
        if (str != null) {
            this.mQSLogger.logTileChangeListening(str, z);
        }
    }

    public void handleStale() {
        if (this.mListeners.isEmpty()) {
            setListening(this.mStaleListener, true);
        } else {
            refreshState(null);
        }
    }

    public abstract void handleUpdateState(QSTile.State state, Object obj);

    public void handleUserSwitch(int i) {
        handleRefreshState(null);
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public boolean isAvailable() {
        return !(this instanceof ModesTile);
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final boolean isListening() {
        return this.mLifecycle.state.isAtLeast(Lifecycle.State.RESUMED);
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final boolean isTileReady() {
        return this.mReadyState == 2;
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void longClick(Expandable expandable) {
        MetricsLogger metricsLogger = this.mMetricsLogger;
        LogMaker type = new LogMaker(366).setType(4);
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        metricsLogger.write(populate(type.addTaggedData(1592, Integer.valueOf(statusBarStateController.getState()))));
        this.mUiEventLogger.logWithInstanceId(QSEvent.QS_ACTION_LONG_PRESS, 0, getMetricsSpec(), this.mInstanceId);
        int i = this.mClickEventId;
        this.mClickEventId = i + 1;
        this.mQSLogger.logTileLongClick(this.mTileSpec, statusBarStateController.getState(), this.mState.state, i);
        if (this.mFalsingManager.isFalseLongTap(1)) {
            return;
        }
        this.mHandler.obtainMessage(4, i, 0, expandable).sendToTarget();
    }

    public abstract QSTile.State newTileState();

    @Override // com.android.systemui.plugins.qs.QSTile
    public LogMaker populate(LogMaker logMaker) {
        QSTile.State state = this.mState;
        if (state instanceof QSTile.BooleanState) {
            logMaker.addTaggedData(928, Integer.valueOf(((QSTile.BooleanState) state).value ? 1 : 0));
        }
        return logMaker.setSubtype(getMetricsCategory()).addTaggedData(1593, Integer.valueOf(this.mIsFullQs)).addTaggedData(927, Integer.valueOf(((QSHostAdapter) this.mHost).getSpecs().indexOf(this.mTileSpec)));
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void refreshState() {
        refreshState(null);
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void removeCallback(QSTile.Callback callback) {
        this.mHandler.obtainMessage(9, callback).sendToTarget();
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void removeCallbacks() {
        this.mHandler.sendEmptyMessage(8);
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public void secondaryClick(Expandable expandable) {
        MetricsLogger metricsLogger = this.mMetricsLogger;
        LogMaker type = new LogMaker(926).setType(4);
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        metricsLogger.write(populate(type.addTaggedData(1592, Integer.valueOf(statusBarStateController.getState()))));
        this.mUiEventLogger.logWithInstanceId(QSEvent.QS_ACTION_SECONDARY_CLICK, 0, getMetricsSpec(), this.mInstanceId);
        int i = this.mClickEventId;
        this.mClickEventId = i + 1;
        this.mQSLogger.logTileSecondaryClick(this.mTileSpec, statusBarStateController.getState(), this.mState.state, i);
        this.mHandler.obtainMessage(3, i, 0, expandable).sendToTarget();
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void setListening(Object obj, boolean z) {
        this.mHandler.obtainMessage(10, z ? 1 : 0, 0, obj).sendToTarget();
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void setTileSpec(String str) {
        this.mTileSpec = str;
        this.mState.spec = str;
        this.mTmpState.spec = str;
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void userSwitch(int i) {
        H h = this.mHandler;
        h.obtainMessage(6, i, 0).sendToTarget();
        h.sendEmptyMessage(11);
    }

    public final void refreshState(Object obj) {
        this.mHandler.obtainMessage(5, obj).sendToTarget();
    }

    public void handleInitialize() {
    }

    @Override // com.android.systemui.plugins.qs.QSTile
    public final void setDetailListening(boolean z) {
    }
}
