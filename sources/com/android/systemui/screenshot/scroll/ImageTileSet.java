package com.android.systemui.screenshot.scroll;

import android.graphics.Region;
import android.graphics.RenderNode;
import android.os.Handler;
import com.android.internal.util.CallbackRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ImageTileSet {
    public CallbackRegistry mContentListeners;
    public final Handler mHandler;
    public final List mTiles = new ArrayList();
    public final Region mRegion = new Region();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.scroll.ImageTileSet$1, reason: invalid class name */
    public final class AnonymousClass1 extends CallbackRegistry.NotifierCallback {
        public final void onNotifyCallback(Object obj, Object obj2, int i, Object obj3) {
            TiledImageDrawable tiledImageDrawable = ((TiledImageDrawable$$ExternalSyntheticLambda0) obj).f$0;
            RenderNode renderNode = tiledImageDrawable.mNode;
            if (renderNode != null && renderNode.hasDisplayList()) {
                tiledImageDrawable.mNode.discardDisplayList();
            }
            tiledImageDrawable.invalidateSelf();
        }
    }

    public ImageTileSet(Handler handler) {
        this.mHandler = handler;
    }

    public final void addTile(final ImageTile imageTile) {
        Handler handler = this.mHandler;
        if (!handler.getLooper().isCurrentThread()) {
            handler.post(new Runnable() { // from class: com.android.systemui.screenshot.scroll.ImageTileSet$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageTileSet.this.addTile(imageTile);
                }
            });
            return;
        }
        this.mTiles.add(imageTile);
        Region region = this.mRegion;
        region.op(imageTile.mLocation, region, Region.Op.UNION);
        CallbackRegistry callbackRegistry = this.mContentListeners;
        if (callbackRegistry != null) {
            callbackRegistry.notifyCallbacks(this, 0, (Object) null);
        }
    }

    public final void clear() {
        if (this.mTiles.isEmpty()) {
            return;
        }
        this.mRegion.setEmpty();
        Iterator it = this.mTiles.iterator();
        while (it.hasNext()) {
            ((ImageTile) it.next()).close();
            it.remove();
        }
        CallbackRegistry callbackRegistry = this.mContentListeners;
        if (callbackRegistry != null) {
            callbackRegistry.notifyCallbacks(this, 0, (Object) null);
        }
    }

    public final int getHeight() {
        return this.mRegion.getBounds().height();
    }

    public final int getTop() {
        return this.mRegion.getBounds().top;
    }

    public final int getWidth() {
        return this.mRegion.getBounds().width();
    }
}
