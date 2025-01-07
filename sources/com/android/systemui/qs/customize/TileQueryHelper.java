package com.android.systemui.qs.customize;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArraySet;
import android.widget.Button;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.systemui.settings.UserTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileQueryHelper {
    public final Executor mBgExecutor;
    public final Context mContext;
    public boolean mFinished;
    public TileAdapter mListener;
    public final Executor mMainExecutor;
    public final UserTracker mUserTracker;
    public final ArrayList mTiles = new ArrayList();
    public final ArraySet mSpecs = new ArraySet();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileCollector implements QSTile.Callback {
        public final QSHost mQSHost;
        public final List mQSTileList = new ArrayList();

        public TileCollector(List list, QSHost qSHost) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                QSTile qSTile = (QSTile) it.next();
                TilePair tilePair = new TilePair();
                tilePair.mReady = false;
                tilePair.mTile = qSTile;
                this.mQSTileList.add(tilePair);
            }
            this.mQSHost = qSHost;
            if (list.isEmpty()) {
                TileQueryHelper.this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.qs.customize.TileQueryHelper$TileCollector$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TileQueryHelper.TileCollector tileCollector = TileQueryHelper.TileCollector.this;
                        TileQueryHelper tileQueryHelper = TileQueryHelper.this;
                        tileQueryHelper.getClass();
                        tileQueryHelper.mMainExecutor.execute(new TileQueryHelper$$ExternalSyntheticLambda1(tileQueryHelper, new ArrayList(tileQueryHelper.mTiles), false));
                        tileQueryHelper.mBgExecutor.execute(new TileQueryHelper$$ExternalSyntheticLambda0(tileQueryHelper, tileCollector.mQSHost));
                    }
                });
            }
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Callback
        public final void onStateChanged(QSTile.State state) {
            boolean z;
            Iterator it = this.mQSTileList.iterator();
            boolean z2 = true;
            while (true) {
                z = false;
                if (!it.hasNext()) {
                    break;
                }
                TilePair tilePair = (TilePair) it.next();
                if (!tilePair.mReady) {
                    QSTile qSTile = tilePair.mTile;
                    if (qSTile.isTileReady()) {
                        qSTile.removeCallback(this);
                        qSTile.setListening(this, false);
                        tilePair.mReady = true;
                    }
                }
                if (!tilePair.mReady) {
                    z2 = false;
                }
            }
            if (!z2) {
                return;
            }
            Iterator it2 = this.mQSTileList.iterator();
            while (true) {
                boolean hasNext = it2.hasNext();
                TileQueryHelper tileQueryHelper = TileQueryHelper.this;
                if (!hasNext) {
                    tileQueryHelper.getClass();
                    tileQueryHelper.mMainExecutor.execute(new TileQueryHelper$$ExternalSyntheticLambda1(tileQueryHelper, new ArrayList(tileQueryHelper.mTiles), z));
                    tileQueryHelper.mBgExecutor.execute(new TileQueryHelper$$ExternalSyntheticLambda0(tileQueryHelper, this.mQSHost));
                    return;
                }
                QSTile qSTile2 = ((TilePair) it2.next()).mTile;
                QSTile.State copy = qSTile2.getState().copy();
                copy.label = qSTile2.getTileLabel();
                qSTile2.destroy();
                tileQueryHelper.addTile(qSTile2.getTileSpec(), null, copy, true);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileInfo {
        public boolean isSystem;
        public String spec;
        public QSTile.State state;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TilePair {
        public boolean mReady;
        public QSTile mTile;
    }

    public TileQueryHelper(Context context, UserTracker userTracker, Executor executor, Executor executor2) {
        this.mContext = context;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mUserTracker = userTracker;
    }

    public final void addTile(String str, CharSequence charSequence, QSTile.State state, boolean z) {
        if (this.mSpecs.contains(str)) {
            return;
        }
        state.dualTarget = false;
        state.expandedAccessibilityClassName = Button.class.getName();
        if (z || TextUtils.equals(state.label, charSequence)) {
            charSequence = null;
        }
        state.secondaryLabel = charSequence;
        TileInfo tileInfo = new TileInfo();
        tileInfo.spec = str;
        tileInfo.state = state;
        tileInfo.isSystem = z;
        this.mTiles.add(tileInfo);
        this.mSpecs.add(str);
    }
}
