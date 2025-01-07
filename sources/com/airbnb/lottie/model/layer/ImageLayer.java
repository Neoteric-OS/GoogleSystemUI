package com.airbnb.lottie.model.layer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Base64;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImageLayer extends BaseLayer {
    public ValueCallbackKeyframeAnimation colorFilterAnimation;
    public final Rect dst;
    public ValueCallbackKeyframeAnimation imageAnimation;
    public final LottieImageAsset lottieImageAsset;
    public final LPaint paint;
    public final Rect src;

    public ImageLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.paint = new LPaint(3);
        this.src = new Rect();
        this.dst = new Rect();
        LottieComposition lottieComposition = lottieDrawable.composition;
        this.lottieImageAsset = lottieComposition == null ? null : (LottieImageAsset) lottieComposition.getImages().get(layer.refId);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        super.addValueCallback(lottieValueCallback, obj);
        if (obj == LottieProperty.COLOR_FILTER) {
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
                return;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
                return;
            }
        }
        if (obj == LottieProperty.IMAGE) {
            if (lottieValueCallback == null) {
                this.imageAnimation = null;
            } else {
                this.imageAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void drawLayer(Canvas canvas, Matrix matrix, int i) {
        Bitmap bitmap;
        Bitmap bitmap2;
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.imageAnimation;
        LottieDrawable lottieDrawable = this.lottieDrawable;
        LottieImageAsset lottieImageAsset = this.lottieImageAsset;
        if (valueCallbackKeyframeAnimation == null || (bitmap = (Bitmap) valueCallbackKeyframeAnimation.getValue()) == null) {
            String str = this.layerModel.refId;
            ImageAssetManager imageAssetManager = lottieDrawable.getImageAssetManager();
            if (imageAssetManager != null) {
                String str2 = imageAssetManager.imagesFolder;
                LottieImageAsset lottieImageAsset2 = (LottieImageAsset) imageAssetManager.imageAssets.get(str);
                if (lottieImageAsset2 != null) {
                    bitmap2 = lottieImageAsset2.bitmap;
                    if (bitmap2 == null) {
                        Context context = imageAssetManager.context;
                        if (context != null) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inScaled = true;
                            options.inDensity = 160;
                            String str3 = lottieImageAsset2.fileName;
                            if (!str3.startsWith("data:") || str3.indexOf("base64,") <= 0) {
                                try {
                                    if (TextUtils.isEmpty(str2)) {
                                        throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                                    }
                                    try {
                                        Bitmap decodeStream = BitmapFactory.decodeStream(context.getAssets().open(str2 + str3), null, options);
                                        if (decodeStream == null) {
                                            Logger.warning("Decoded image `" + str + "` is null.");
                                        } else {
                                            bitmap2 = Utils.resizeBitmapIfNeeded(decodeStream, lottieImageAsset2.width, lottieImageAsset2.height);
                                            imageAssetManager.putBitmap(str, bitmap2);
                                        }
                                    } catch (IllegalArgumentException e) {
                                        Logger.warning("Unable to decode image `" + str + "`.", e);
                                    }
                                } catch (IOException e2) {
                                    Logger.warning("Unable to open asset.", e2);
                                }
                            } else {
                                try {
                                    byte[] decode = Base64.decode(str3.substring(str3.indexOf(44) + 1), 0);
                                    bitmap2 = BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
                                    imageAssetManager.putBitmap(str, bitmap2);
                                } catch (IllegalArgumentException e3) {
                                    Logger.warning("data URL did not have correct base64 format.", e3);
                                }
                            }
                        }
                    }
                    bitmap = bitmap2;
                }
                bitmap2 = null;
                bitmap = bitmap2;
            } else {
                bitmap = null;
            }
            if (bitmap == null) {
                bitmap = lottieImageAsset != null ? lottieImageAsset.bitmap : null;
            }
        }
        if (bitmap == null || bitmap.isRecycled() || lottieImageAsset == null) {
            return;
        }
        float dpScale = Utils.dpScale();
        LPaint lPaint = this.paint;
        lPaint.setAlpha(i);
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = this.colorFilterAnimation;
        if (valueCallbackKeyframeAnimation2 != null) {
            lPaint.setColorFilter((ColorFilter) valueCallbackKeyframeAnimation2.getValue());
        }
        canvas.save();
        canvas.concat(matrix);
        this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (lottieDrawable.maintainOriginalImageBounds) {
            this.dst.set(0, 0, (int) (lottieImageAsset.width * dpScale), (int) (lottieImageAsset.height * dpScale));
        } else {
            this.dst.set(0, 0, (int) (bitmap.getWidth() * dpScale), (int) (bitmap.getHeight() * dpScale));
        }
        canvas.drawBitmap(bitmap, this.src, this.dst, lPaint);
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        if (this.lottieImageAsset != null) {
            float dpScale = Utils.dpScale();
            rectF.set(0.0f, 0.0f, r3.width * dpScale, r3.height * dpScale);
            this.boundsMatrix.mapRect(rectF);
        }
    }
}
