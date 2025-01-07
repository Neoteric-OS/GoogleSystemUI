package com.android.systemui.screenshot.scroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.internal.util.CallbackRegistry;
import com.android.systemui.screenshot.scroll.ImageTileSet;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TiledImageDrawable extends Drawable {
    public RenderNode mNode;
    public final ImageTileSet mTiles;

    public TiledImageDrawable(ImageTileSet imageTileSet) {
        this.mTiles = imageTileSet;
        TiledImageDrawable$$ExternalSyntheticLambda0 tiledImageDrawable$$ExternalSyntheticLambda0 = new TiledImageDrawable$$ExternalSyntheticLambda0(this);
        if (imageTileSet.mContentListeners == null) {
            imageTileSet.mContentListeners = new CallbackRegistry(new ImageTileSet.AnonymousClass1());
        }
        imageTileSet.mContentListeners.add(tiledImageDrawable$$ExternalSyntheticLambda0);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        RenderNode renderNode;
        RenderNode renderNode2 = this.mNode;
        if (renderNode2 == null || !renderNode2.hasDisplayList()) {
            if (this.mNode == null) {
                this.mNode = new RenderNode("TiledImageDrawable");
            }
            this.mNode.setPosition(0, 0, this.mTiles.getWidth(), this.mTiles.getHeight());
            RecordingCanvas beginRecording = this.mNode.beginRecording();
            beginRecording.translate(-this.mTiles.mRegion.getBounds().left, -this.mTiles.getTop());
            for (int i = 0; i < ((ArrayList) this.mTiles.mTiles).size(); i++) {
                ImageTile imageTile = (ImageTile) ((ArrayList) this.mTiles.mTiles).get(i);
                beginRecording.save();
                Rect rect = imageTile.mLocation;
                beginRecording.translate(rect.left, rect.top);
                synchronized (imageTile) {
                    try {
                        if (imageTile.mNode == null) {
                            imageTile.mNode = new RenderNode("Tile{" + Integer.toHexString(imageTile.mImage.hashCode()) + "}");
                        }
                        if (imageTile.mNode.hasDisplayList()) {
                            renderNode = imageTile.mNode;
                        } else {
                            int min = Math.min(imageTile.mImage.getWidth(), imageTile.mLocation.width());
                            int min2 = Math.min(imageTile.mImage.getHeight(), imageTile.mLocation.height());
                            imageTile.mNode.setPosition(0, 0, min, min2);
                            RecordingCanvas beginRecording2 = imageTile.mNode.beginRecording(min, min2);
                            beginRecording2.save();
                            beginRecording2.clipRect(0, 0, imageTile.mLocation.width(), imageTile.mLocation.height());
                            beginRecording2.drawBitmap(Bitmap.wrapHardwareBuffer(imageTile.mImage.getHardwareBuffer(), ImageTile.COLOR_SPACE), 0.0f, 0.0f, (Paint) null);
                            beginRecording2.restore();
                            imageTile.mNode.endRecording();
                            renderNode = imageTile.mNode;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                beginRecording.drawRenderNode(renderNode);
                beginRecording.restore();
            }
            this.mNode.endRecording();
        }
        if (!canvas.isHardwareAccelerated()) {
            Log.d("TiledImageDrawable", "Canvas is not hardware accelerated. Skipping draw!");
            return;
        }
        Rect bounds = getBounds();
        canvas.save();
        canvas.clipRect(0, 0, bounds.width(), bounds.height());
        canvas.translate(-bounds.left, -bounds.top);
        canvas.drawRenderNode(this.mNode);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.mTiles.getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.mTiles.getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.mNode.setAlpha(i / 255.0f)) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        throw new IllegalArgumentException("not implemented");
    }
}
