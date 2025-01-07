package com.android.systemui.screenshot.scroll;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.HardwareRenderer;
import android.graphics.Matrix;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ScrollCaptureResponse;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.view.OneShotPreDrawListener;
import com.android.systemui.screenshot.ActionIntentCreator;
import com.android.systemui.screenshot.ActionIntentExecutor;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.ImageExporter$$ExternalSyntheticLambda0;
import com.android.systemui.screenshot.ScreenshotEvent;
import com.android.systemui.screenshot.scroll.LongScreenshotActivity;
import com.android.systemui.screenshot.scroll.ScrollCaptureClient;
import com.android.systemui.screenshot.scroll.ScrollCaptureController;
import com.android.wm.shell.R;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LongScreenshotActivity extends Activity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActionIntentExecutor mActionExecutor;
    public final Executor mBackgroundExecutor;
    public CallbackToFutureAdapter.SafeFuture mCacheLoadFuture;
    public CallbackToFutureAdapter.SafeFuture mCacheSaveFuture;
    public View mCancel;
    public CropView mCropView;
    public View mEdit;
    public ImageView mEnterTransitionView;
    public final ImageExporter mImageExporter;
    public ScrollCaptureController.LongScreenshot mLongScreenshot;
    public final LongScreenshotData mLongScreenshotHolder;
    public MagnifierView mMagnifierView;
    public Bitmap mOutputBitmap;
    public ImageView mPreview;
    public View mSave;
    public File mSavedImagePath;
    public UserHandle mScreenshotUserHandle;
    public ScrollCaptureResponse mScrollCaptureResponse;
    public View mShare;
    public boolean mTransitionStarted;
    public ImageView mTransitionView;
    public final UiEventLogger mUiEventLogger;
    public final Executor mUiExecutor;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class PendingAction {
        public static final /* synthetic */ PendingAction[] $VALUES;
        public static final PendingAction EDIT;
        public static final PendingAction SAVE;
        public static final PendingAction SHARE;

        static {
            PendingAction pendingAction = new PendingAction("SHARE", 0);
            SHARE = pendingAction;
            PendingAction pendingAction2 = new PendingAction("EDIT", 1);
            EDIT = pendingAction2;
            PendingAction pendingAction3 = new PendingAction("SAVE", 2);
            SAVE = pendingAction3;
            $VALUES = new PendingAction[]{pendingAction, pendingAction2, pendingAction3};
        }

        public static PendingAction valueOf(String str) {
            return (PendingAction) Enum.valueOf(PendingAction.class, str);
        }

        public static PendingAction[] values() {
            return (PendingAction[]) $VALUES.clone();
        }
    }

    public static void $r8$lambda$AKhoAjDWW24UTwZdM5QLD3oY6ZA(LongScreenshotActivity longScreenshotActivity, View view) {
        int id = view.getId();
        view.setPressed(true);
        longScreenshotActivity.setButtonsEnabled(false);
        if (id == R.id.save) {
            longScreenshotActivity.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_SAVED);
            longScreenshotActivity.startExport(PendingAction.SAVE);
            return;
        }
        if (id == R.id.edit) {
            longScreenshotActivity.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_EDIT);
            longScreenshotActivity.startExport(PendingAction.EDIT);
        } else if (id == R.id.share) {
            longScreenshotActivity.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_SHARE);
            longScreenshotActivity.startExport(PendingAction.SHARE);
        } else if (id == R.id.cancel) {
            longScreenshotActivity.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_EXIT);
            longScreenshotActivity.finishAndRemoveTask();
        }
    }

    public LongScreenshotActivity(UiEventLogger uiEventLogger, ImageExporter imageExporter, Executor executor, Executor executor2, LongScreenshotData longScreenshotData, ActionIntentExecutor actionIntentExecutor) {
        this.mUiEventLogger = uiEventLogger;
        this.mUiExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mImageExporter = imageExporter;
        this.mLongScreenshotHolder = longScreenshotData;
        this.mActionExecutor = actionIntentExecutor;
    }

    public final void onCachedImageLoaded(ImageLoader$Result imageLoader$Result) {
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_ACTIVITY_CACHED_IMAGE_LOADED);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), imageLoader$Result.mBitmap);
        this.mPreview.setImageDrawable(bitmapDrawable);
        this.mPreview.setAlpha(1.0f);
        MagnifierView magnifierView = this.mMagnifierView;
        int width = imageLoader$Result.mBitmap.getWidth();
        int height = imageLoader$Result.mBitmap.getHeight();
        magnifierView.mDrawable = bitmapDrawable;
        bitmapDrawable.setBounds(0, 0, width, height);
        magnifierView.invalidate();
        this.mCropView.setVisibility(0);
        this.mSavedImagePath = imageLoader$Result.mFilename;
        setButtonsEnabled(true);
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setDecorFitsSystemWindows(false);
        setContentView(R.layout.long_screenshot);
        this.mPreview = (ImageView) requireViewById(R.id.preview);
        this.mSave = requireViewById(R.id.save);
        this.mEdit = requireViewById(R.id.edit);
        this.mShare = requireViewById(R.id.share);
        this.mCancel = requireViewById(R.id.cancel);
        this.mCropView = (CropView) requireViewById(R.id.crop_view);
        MagnifierView magnifierView = (MagnifierView) requireViewById(R.id.magnifier);
        this.mMagnifierView = magnifierView;
        this.mCropView.mCropInteractionListener = magnifierView;
        this.mTransitionView = (ImageView) requireViewById(R.id.transition);
        this.mEnterTransitionView = (ImageView) requireViewById(R.id.enter_transition);
        this.mSave.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongScreenshotActivity.$r8$lambda$AKhoAjDWW24UTwZdM5QLD3oY6ZA(LongScreenshotActivity.this, view);
            }
        });
        this.mCancel.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongScreenshotActivity.$r8$lambda$AKhoAjDWW24UTwZdM5QLD3oY6ZA(LongScreenshotActivity.this, view);
            }
        });
        this.mEdit.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongScreenshotActivity.$r8$lambda$AKhoAjDWW24UTwZdM5QLD3oY6ZA(LongScreenshotActivity.this, view);
            }
        });
        this.mShare.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongScreenshotActivity.$r8$lambda$AKhoAjDWW24UTwZdM5QLD3oY6ZA(LongScreenshotActivity.this, view);
            }
        });
        this.mPreview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                LongScreenshotActivity longScreenshotActivity = LongScreenshotActivity.this;
                int i9 = LongScreenshotActivity.$r8$clinit;
                longScreenshotActivity.updateImageDimensions();
            }
        });
        requireViewById(R.id.root).setOnApplyWindowInsetsListener(new LongScreenshotActivity$$ExternalSyntheticLambda3());
        Intent intent = getIntent();
        this.mScrollCaptureResponse = intent.getParcelableExtra("capture-response");
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("screenshot-userhandle", UserHandle.class);
        this.mScreenshotUserHandle = userHandle;
        if (userHandle == null) {
            this.mScreenshotUserHandle = Process.myUserHandle();
        }
        if (bundle != null) {
            String string = bundle.getString("saved-image-path");
            if (string == null) {
                Log.e("Screenshot", "Missing saved state entry with key 'saved-image-path'!");
                finishAndRemoveTask();
            } else {
                this.mSavedImagePath = new File(string);
                getContentResolver();
                final File file = this.mSavedImagePath;
                this.mCacheLoadFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.screenshot.scroll.ImageLoader$$ExternalSyntheticLambda0
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        File file2 = file;
                        try {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                            try {
                                ImageLoader$Result imageLoader$Result = new ImageLoader$Result();
                                imageLoader$Result.mFilename = file2;
                                imageLoader$Result.mBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                                completer.set(imageLoader$Result);
                                bufferedInputStream.close();
                                return "BitmapFactory#decodeStream";
                            } finally {
                            }
                        } catch (IOException e) {
                            completer.setException(e);
                            return "BitmapFactory#decodeStream";
                        }
                    }
                });
            }
        }
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        File file = this.mSavedImagePath;
        if (file != null) {
            bundle.putString("saved-image-path", file.getPath());
        }
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_ACTIVITY_STARTED);
        if (this.mPreview.getDrawable() != null) {
            return;
        }
        if (this.mCacheLoadFuture != null) {
            Log.d("Screenshot", "mCacheLoadFuture != null");
            final CallbackToFutureAdapter.SafeFuture safeFuture = this.mCacheLoadFuture;
            safeFuture.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LongScreenshotActivity longScreenshotActivity = LongScreenshotActivity.this;
                    CallbackToFutureAdapter.SafeFuture safeFuture2 = safeFuture;
                    int i = LongScreenshotActivity.$r8$clinit;
                    Log.d("Screenshot", "cached bitmap load complete");
                    try {
                        longScreenshotActivity.onCachedImageLoaded((ImageLoader$Result) safeFuture2.delegate.get());
                    } catch (InterruptedException | CancellationException | ExecutionException e) {
                        Log.e("Screenshot", "Failed to load cached image", e);
                        File file = longScreenshotActivity.mSavedImagePath;
                        if (file != null) {
                            file.delete();
                            longScreenshotActivity.mSavedImagePath = null;
                        }
                        longScreenshotActivity.finishAndRemoveTask();
                    }
                }
            }, this.mUiExecutor);
            this.mCacheLoadFuture = null;
            return;
        }
        ScrollCaptureController.LongScreenshot longScreenshot = (ScrollCaptureController.LongScreenshot) this.mLongScreenshotHolder.mLongScreenshot.getAndSet(null);
        if (longScreenshot == null) {
            Log.e("Screenshot", "No long screenshot available!");
            finishAndRemoveTask();
            return;
        }
        Log.i("Screenshot", "Completed: " + longScreenshot);
        this.mLongScreenshot = longScreenshot;
        TiledImageDrawable tiledImageDrawable = new TiledImageDrawable(longScreenshot.mImageTileSet);
        this.mPreview.setImageDrawable(tiledImageDrawable);
        MagnifierView magnifierView = this.mMagnifierView;
        TiledImageDrawable tiledImageDrawable2 = new TiledImageDrawable(this.mLongScreenshot.mImageTileSet);
        int width = this.mLongScreenshot.mImageTileSet.getWidth();
        int height = this.mLongScreenshot.mImageTileSet.getHeight();
        magnifierView.mDrawable = tiledImageDrawable2;
        tiledImageDrawable2.setBounds(0, 0, width, height);
        magnifierView.invalidate();
        Log.i("Screenshot", "Completed: " + longScreenshot);
        float max = Math.max(0.0f, ((float) (-this.mLongScreenshot.mImageTileSet.getTop())) / ((float) this.mLongScreenshot.mImageTileSet.getHeight()));
        float min = Math.min(1.0f, 1.0f - (((float) (this.mLongScreenshot.mImageTileSet.mRegion.getBounds().bottom - ((ScrollCaptureClient.SessionWrapper) this.mLongScreenshot.mSession).mBoundsInWindow.height())) / ((float) this.mLongScreenshot.mImageTileSet.getHeight())));
        Log.i("Screenshot", "topFraction: " + max);
        Log.i("Screenshot", "bottomFraction: " + min);
        this.mEnterTransitionView.setImageDrawable(tiledImageDrawable);
        OneShotPreDrawListener.add(this.mEnterTransitionView, new LongScreenshotActivity$$ExternalSyntheticLambda4(this, max, min, 0));
        final Executor executor = this.mBackgroundExecutor;
        final Bitmap bitmap = this.mLongScreenshot.toBitmap();
        final File file = new File(getCacheDir(), "long_screenshot_cache.png");
        final ImageExporter imageExporter = this.mImageExporter;
        imageExporter.getClass();
        CallbackToFutureAdapter.SafeFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.screenshot.ImageExporter$$ExternalSyntheticLambda1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(final CallbackToFutureAdapter.Completer completer) {
                Executor executor2 = executor;
                final File file2 = file;
                final Bitmap bitmap2 = bitmap;
                final ImageExporter imageExporter2 = ImageExporter.this;
                imageExporter2.getClass();
                executor2.execute(new Runnable() { // from class: com.android.systemui.screenshot.ImageExporter$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        ImageExporter imageExporter3 = ImageExporter.this;
                        File file3 = file2;
                        Bitmap bitmap3 = bitmap2;
                        CallbackToFutureAdapter.Completer completer2 = completer;
                        imageExporter3.getClass();
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                bitmap3.compress(imageExporter3.mCompressFormat, imageExporter3.mQuality, fileOutputStream);
                                completer2.set(file3);
                                fileOutputStream.close();
                            } finally {
                            }
                        } catch (IOException e) {
                            if (file3.exists()) {
                                file3.delete();
                            }
                            completer2.setException(e);
                        }
                    }
                });
                return "Bitmap#compress";
            }
        });
        this.mCacheSaveFuture = future;
        future.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                LongScreenshotActivity longScreenshotActivity = LongScreenshotActivity.this;
                int i = LongScreenshotActivity.$r8$clinit;
                try {
                    longScreenshotActivity.mSavedImagePath = (File) longScreenshotActivity.mCacheSaveFuture.delegate.get();
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    Log.e("Screenshot", "Error saving temp image file", e);
                    longScreenshotActivity.finishAndRemoveTask();
                }
            }
        }, this.mUiExecutor);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        if (this.mTransitionStarted) {
            finish();
        }
        if (isFinishing()) {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_ACTIVITY_FINISHED);
            ScrollCaptureResponse scrollCaptureResponse = this.mScrollCaptureResponse;
            if (scrollCaptureResponse != null) {
                scrollCaptureResponse.close();
            }
            CallbackToFutureAdapter.SafeFuture safeFuture = this.mCacheSaveFuture;
            if (safeFuture != null) {
                safeFuture.cancel(true);
            }
            File file = this.mSavedImagePath;
            if (file != null) {
                file.delete();
                this.mSavedImagePath = null;
            }
            ScrollCaptureController.LongScreenshot longScreenshot = this.mLongScreenshot;
            if (longScreenshot != null) {
                longScreenshot.mImageTileSet.clear();
                ((ScrollCaptureClient.SessionWrapper) longScreenshot.mSession).mReader.close();
            }
        }
    }

    public final void setButtonsEnabled(boolean z) {
        this.mSave.setEnabled(z);
        this.mEdit.setEnabled(z);
        this.mShare.setEnabled(z);
    }

    public final void startExport(final PendingAction pendingAction) {
        Drawable drawable = this.mPreview.getDrawable();
        if (drawable == null) {
            Log.e("Screenshot", "No drawable, skipping export!");
            return;
        }
        Rect cropBoundaries = this.mCropView.getCropBoundaries(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if (cropBoundaries.isEmpty()) {
            Log.w("Screenshot", "Crop bounds empty, skipping export.");
            return;
        }
        updateImageDimensions();
        RenderNode renderNode = new RenderNode("Bitmap Export");
        renderNode.setPosition(0, 0, cropBoundaries.width(), cropBoundaries.height());
        RecordingCanvas beginRecording = renderNode.beginRecording();
        beginRecording.translate(-cropBoundaries.left, -cropBoundaries.top);
        beginRecording.clipRect(cropBoundaries);
        drawable.draw(beginRecording);
        renderNode.endRecording();
        this.mOutputBitmap = HardwareRenderer.createHardwareBitmap(renderNode, cropBoundaries.width(), cropBoundaries.height());
        Executor executor = this.mBackgroundExecutor;
        UUID randomUUID = UUID.randomUUID();
        Bitmap bitmap = this.mOutputBitmap;
        ZonedDateTime now = ZonedDateTime.now();
        UserHandle userHandle = this.mScreenshotUserHandle;
        ImageExporter imageExporter = this.mImageExporter;
        ContentResolver contentResolver = imageExporter.mResolver;
        Bitmap.CompressFormat compressFormat = imageExporter.mCompressFormat;
        final CallbackToFutureAdapter.SafeFuture future = CallbackToFutureAdapter.getFuture(new ImageExporter$$ExternalSyntheticLambda0(executor, new ImageExporter.Task(contentResolver, randomUUID, bitmap, now, compressFormat, imageExporter.mQuality, userHandle, ImageExporter.createFilename(now, compressFormat, 0))));
        future.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                LongScreenshotActivity longScreenshotActivity = LongScreenshotActivity.this;
                LongScreenshotActivity.PendingAction pendingAction2 = pendingAction;
                CallbackToFutureAdapter.SafeFuture safeFuture = future;
                int i = LongScreenshotActivity.$r8$clinit;
                longScreenshotActivity.setButtonsEnabled(true);
                try {
                    Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(((ImageExporter.Result) safeFuture.delegate.get()).uri);
                    Log.e("Screenshot", pendingAction2 + " uri=" + uriWithoutUserId);
                    int ordinal = pendingAction2.ordinal();
                    ActionIntentExecutor actionIntentExecutor = longScreenshotActivity.mActionExecutor;
                    Bundle bundle = null;
                    if (ordinal == 0) {
                        actionIntentExecutor.launchIntentAsync(ActionIntentCreator.createShare(uriWithoutUserId, null), longScreenshotActivity.mScreenshotUserHandle);
                        return;
                    }
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            return;
                        }
                        longScreenshotActivity.finishAndRemoveTask();
                        return;
                    }
                    if (longScreenshotActivity.mScreenshotUserHandle != Process.myUserHandle()) {
                        Uri uriWithoutUserId2 = ContentProvider.getUriWithoutUserId(uriWithoutUserId);
                        Intent intent = new Intent("android.intent.action.EDIT");
                        String string = longScreenshotActivity.getString(R.string.config_screenshotEditor);
                        if (string.length() > 0) {
                            intent.setComponent(ComponentName.unflattenFromString(string));
                        }
                        actionIntentExecutor.launchIntentAsync(intent.setDataAndType(uriWithoutUserId2, "image/png").putExtra("edit_source", "screenshot").addFlags(1).addFlags(2).addFlags(268435456).addFlags(32768), longScreenshotActivity.mScreenshotUserHandle);
                        return;
                    }
                    String string2 = longScreenshotActivity.getString(R.string.config_screenshotEditor);
                    Intent intent2 = new Intent("android.intent.action.EDIT");
                    intent2.setDataAndType(uriWithoutUserId, "image/png");
                    intent2.addFlags(3);
                    if (!TextUtils.isEmpty(string2)) {
                        intent2.setComponent(ComponentName.unflattenFromString(string2));
                        longScreenshotActivity.mTransitionView.setImageBitmap(longScreenshotActivity.mOutputBitmap);
                        longScreenshotActivity.mTransitionView.setVisibility(0);
                        longScreenshotActivity.mTransitionView.setTransitionName("screenshot_preview_image");
                        bundle = ActivityOptions.makeSceneTransitionAnimation(longScreenshotActivity, longScreenshotActivity.mTransitionView, "screenshot_preview_image").toBundle();
                        longScreenshotActivity.mTransitionStarted = true;
                    }
                    longScreenshotActivity.startActivity(intent2, bundle);
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    Log.e("Screenshot", "failed to export", e);
                }
            }
        }, this.mUiExecutor);
    }

    public final void updateImageDimensions() {
        int i;
        float intrinsicHeight;
        Drawable drawable = this.mPreview.getDrawable();
        if (drawable == null) {
            return;
        }
        Rect bounds = drawable.getBounds();
        float width = bounds.width() / bounds.height();
        int width2 = (this.mPreview.getWidth() - this.mPreview.getPaddingLeft()) - this.mPreview.getPaddingRight();
        int height = (this.mPreview.getHeight() - this.mPreview.getPaddingTop()) - this.mPreview.getPaddingBottom();
        float f = width2;
        float f2 = height;
        float f3 = f / f2;
        int paddingLeft = this.mPreview.getPaddingLeft();
        int paddingTop = this.mPreview.getPaddingTop();
        if (width > f3) {
            int i2 = (int) ((f3 * f2) / width);
            int i3 = (height - i2) / 2;
            CropView cropView = this.mCropView;
            int paddingTop2 = this.mPreview.getPaddingTop() + i3;
            int paddingBottom = this.mPreview.getPaddingBottom() + i3;
            cropView.mExtraTopPadding = paddingTop2;
            cropView.mExtraBottomPadding = paddingBottom;
            cropView.invalidate();
            paddingTop += i3;
            CropView cropView2 = this.mCropView;
            cropView2.mImageWidth = width2;
            cropView2.invalidate();
            intrinsicHeight = f / this.mPreview.getDrawable().getIntrinsicWidth();
            height = i2;
            i = i3;
        } else {
            int i4 = (int) ((f * width) / f3);
            paddingLeft += (width2 - i4) / 2;
            CropView cropView3 = this.mCropView;
            int paddingTop3 = this.mPreview.getPaddingTop();
            int paddingBottom2 = this.mPreview.getPaddingBottom();
            cropView3.mExtraTopPadding = paddingTop3;
            cropView3.mExtraBottomPadding = paddingBottom2;
            cropView3.invalidate();
            CropView cropView4 = this.mCropView;
            cropView4.mImageWidth = (int) (width * f2);
            cropView4.invalidate();
            i = 0;
            intrinsicHeight = f2 / this.mPreview.getDrawable().getIntrinsicHeight();
            width2 = i4;
        }
        Rect cropBoundaries = this.mCropView.getCropBoundaries(width2, height);
        this.mTransitionView.setTranslationX(paddingLeft + cropBoundaries.left);
        this.mTransitionView.setTranslationY(paddingTop + cropBoundaries.top);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mTransitionView.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = cropBoundaries.width();
        ((ViewGroup.MarginLayoutParams) layoutParams).height = cropBoundaries.height();
        this.mTransitionView.setLayoutParams(layoutParams);
        if (this.mLongScreenshot != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mEnterTransitionView.getLayoutParams();
            float max = Math.max(0.0f, (-this.mLongScreenshot.mImageTileSet.getTop()) / this.mLongScreenshot.mImageTileSet.getHeight());
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = (int) (drawable.getIntrinsicWidth() * intrinsicHeight);
            ((ViewGroup.MarginLayoutParams) layoutParams2).height = (int) (((ScrollCaptureClient.SessionWrapper) this.mLongScreenshot.mSession).mBoundsInWindow.height() * intrinsicHeight);
            this.mEnterTransitionView.setLayoutParams(layoutParams2);
            Matrix matrix = new Matrix();
            matrix.setScale(intrinsicHeight, intrinsicHeight);
            matrix.postTranslate(0.0f, (-intrinsicHeight) * drawable.getIntrinsicHeight() * max);
            this.mEnterTransitionView.setImageMatrix(matrix);
            this.mEnterTransitionView.setTranslationY((max * f2) + this.mPreview.getPaddingTop() + i);
        }
    }
}
