package com.android.systemui.screenrecord;

import android.R;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.hardware.display.VirtualDisplay;
import android.media.AudioFormat;
import android.media.AudioPlaybackCaptureConfiguration;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionManager;
import android.media.projection.MediaProjection;
import android.net.Uri;
import android.os.Handler;
import android.os.ServiceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.MathUtils;
import android.util.Size;
import android.view.Surface;
import android.view.WindowManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.mediaprojection.MediaProjectionCaptureTarget;
import com.android.systemui.screenrecord.ScreenInternalAudioRecorder;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenMediaRecorder extends MediaProjection.Callback {
    public ScreenInternalAudioRecorder mAudio;
    public final ScreenRecordingAudioSource mAudioSource;
    public final MediaProjectionCaptureTarget mCaptureRegion;
    public final Context mContext;
    public final Handler mHandler;
    public Surface mInputSurface;
    public final RecordingService mListener;
    public MediaProjection mMediaProjection;
    public MediaRecorder mMediaRecorder;
    public File mTempAudioFile;
    public File mTempVideoFile;
    public final int mUid;
    public VirtualDisplay mVirtualDisplay;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Closer implements Closeable {
        public final List mCloseables = new ArrayList();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            Throwable th = null;
            for (int i = 0; i < ((ArrayList) this.mCloseables).size(); i++) {
                try {
                    ((Closeable) ((ArrayList) this.mCloseables).get(i)).close();
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    } else {
                        th2.printStackTrace();
                    }
                }
            }
            if (th != null) {
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                if (!(th instanceof RuntimeException)) {
                    throw ((Error) th);
                }
                throw ((RuntimeException) th);
            }
        }

        public final void register(Closeable closeable) {
            this.mCloseables.add(closeable);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedRecording {
        public Icon mThumbnailIcon;
        public Uri mUri;
    }

    public ScreenMediaRecorder(Context context, Handler handler, int i, ScreenRecordingAudioSource screenRecordingAudioSource, MediaProjectionCaptureTarget mediaProjectionCaptureTarget, RecordingService recordingService) {
        this.mContext = context;
        this.mHandler = handler;
        this.mUid = i;
        this.mCaptureRegion = mediaProjectionCaptureTarget;
        this.mListener = recordingService;
        this.mAudioSource = screenRecordingAudioSource;
    }

    public final void end() {
        Closer closer = new Closer();
        final MediaRecorder mediaRecorder = this.mMediaRecorder;
        Objects.requireNonNull(mediaRecorder);
        final int i = 0;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i2 = i;
                MediaRecorder mediaRecorder2 = mediaRecorder;
                switch (i2) {
                    case 0:
                        mediaRecorder2.stop();
                        break;
                    default:
                        mediaRecorder2.release();
                        break;
                }
            }
        });
        final MediaRecorder mediaRecorder2 = this.mMediaRecorder;
        Objects.requireNonNull(mediaRecorder2);
        final int i2 = 1;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i22 = i2;
                MediaRecorder mediaRecorder22 = mediaRecorder2;
                switch (i22) {
                    case 0:
                        mediaRecorder22.stop();
                        break;
                    default:
                        mediaRecorder22.release();
                        break;
                }
            }
        });
        final Surface surface = this.mInputSurface;
        Objects.requireNonNull(surface);
        final int i3 = 0;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda3
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i4 = i3;
                Object obj = surface;
                switch (i4) {
                    case 0:
                        ((Surface) obj).release();
                        break;
                    case 1:
                        ((VirtualDisplay) obj).release();
                        break;
                    case 2:
                        ((MediaProjection) obj).stop();
                        break;
                    default:
                        ScreenMediaRecorder screenMediaRecorder = (ScreenMediaRecorder) obj;
                        ScreenRecordingAudioSource screenRecordingAudioSource = screenMediaRecorder.mAudioSource;
                        if (screenRecordingAudioSource == ScreenRecordingAudioSource.INTERNAL || screenRecordingAudioSource == ScreenRecordingAudioSource.MIC_AND_INTERNAL) {
                            ScreenInternalAudioRecorder screenInternalAudioRecorder = screenMediaRecorder.mAudio;
                            screenInternalAudioRecorder.mAudioRecord.stop();
                            boolean z = screenInternalAudioRecorder.mMic;
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.stop();
                            }
                            screenInternalAudioRecorder.mAudioRecord.release();
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.release();
                            }
                            try {
                                screenInternalAudioRecorder.mThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            screenInternalAudioRecorder.mCodec.stop();
                            screenInternalAudioRecorder.mCodec.release();
                            screenInternalAudioRecorder.mMuxer.stop();
                            screenInternalAudioRecorder.mMuxer.release();
                            screenInternalAudioRecorder.mThread = null;
                            screenMediaRecorder.mAudio = null;
                            break;
                        }
                        break;
                }
            }
        });
        final VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        Objects.requireNonNull(virtualDisplay);
        final int i4 = 1;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda3
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i42 = i4;
                Object obj = virtualDisplay;
                switch (i42) {
                    case 0:
                        ((Surface) obj).release();
                        break;
                    case 1:
                        ((VirtualDisplay) obj).release();
                        break;
                    case 2:
                        ((MediaProjection) obj).stop();
                        break;
                    default:
                        ScreenMediaRecorder screenMediaRecorder = (ScreenMediaRecorder) obj;
                        ScreenRecordingAudioSource screenRecordingAudioSource = screenMediaRecorder.mAudioSource;
                        if (screenRecordingAudioSource == ScreenRecordingAudioSource.INTERNAL || screenRecordingAudioSource == ScreenRecordingAudioSource.MIC_AND_INTERNAL) {
                            ScreenInternalAudioRecorder screenInternalAudioRecorder = screenMediaRecorder.mAudio;
                            screenInternalAudioRecorder.mAudioRecord.stop();
                            boolean z = screenInternalAudioRecorder.mMic;
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.stop();
                            }
                            screenInternalAudioRecorder.mAudioRecord.release();
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.release();
                            }
                            try {
                                screenInternalAudioRecorder.mThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            screenInternalAudioRecorder.mCodec.stop();
                            screenInternalAudioRecorder.mCodec.release();
                            screenInternalAudioRecorder.mMuxer.stop();
                            screenInternalAudioRecorder.mMuxer.release();
                            screenInternalAudioRecorder.mThread = null;
                            screenMediaRecorder.mAudio = null;
                            break;
                        }
                        break;
                }
            }
        });
        final MediaProjection mediaProjection = this.mMediaProjection;
        Objects.requireNonNull(mediaProjection);
        final int i5 = 2;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda3
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i42 = i5;
                Object obj = mediaProjection;
                switch (i42) {
                    case 0:
                        ((Surface) obj).release();
                        break;
                    case 1:
                        ((VirtualDisplay) obj).release();
                        break;
                    case 2:
                        ((MediaProjection) obj).stop();
                        break;
                    default:
                        ScreenMediaRecorder screenMediaRecorder = (ScreenMediaRecorder) obj;
                        ScreenRecordingAudioSource screenRecordingAudioSource = screenMediaRecorder.mAudioSource;
                        if (screenRecordingAudioSource == ScreenRecordingAudioSource.INTERNAL || screenRecordingAudioSource == ScreenRecordingAudioSource.MIC_AND_INTERNAL) {
                            ScreenInternalAudioRecorder screenInternalAudioRecorder = screenMediaRecorder.mAudio;
                            screenInternalAudioRecorder.mAudioRecord.stop();
                            boolean z = screenInternalAudioRecorder.mMic;
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.stop();
                            }
                            screenInternalAudioRecorder.mAudioRecord.release();
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.release();
                            }
                            try {
                                screenInternalAudioRecorder.mThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            screenInternalAudioRecorder.mCodec.stop();
                            screenInternalAudioRecorder.mCodec.release();
                            screenInternalAudioRecorder.mMuxer.stop();
                            screenInternalAudioRecorder.mMuxer.release();
                            screenInternalAudioRecorder.mThread = null;
                            screenMediaRecorder.mAudio = null;
                            break;
                        }
                        break;
                }
            }
        });
        final int i6 = 3;
        closer.register(new Closeable() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda3
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                int i42 = i6;
                Object obj = this;
                switch (i42) {
                    case 0:
                        ((Surface) obj).release();
                        break;
                    case 1:
                        ((VirtualDisplay) obj).release();
                        break;
                    case 2:
                        ((MediaProjection) obj).stop();
                        break;
                    default:
                        ScreenMediaRecorder screenMediaRecorder = (ScreenMediaRecorder) obj;
                        ScreenRecordingAudioSource screenRecordingAudioSource = screenMediaRecorder.mAudioSource;
                        if (screenRecordingAudioSource == ScreenRecordingAudioSource.INTERNAL || screenRecordingAudioSource == ScreenRecordingAudioSource.MIC_AND_INTERNAL) {
                            ScreenInternalAudioRecorder screenInternalAudioRecorder = screenMediaRecorder.mAudio;
                            screenInternalAudioRecorder.mAudioRecord.stop();
                            boolean z = screenInternalAudioRecorder.mMic;
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.stop();
                            }
                            screenInternalAudioRecorder.mAudioRecord.release();
                            if (z) {
                                screenInternalAudioRecorder.mAudioRecordMic.release();
                            }
                            try {
                                screenInternalAudioRecorder.mThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            screenInternalAudioRecorder.mCodec.stop();
                            screenInternalAudioRecorder.mCodec.release();
                            screenInternalAudioRecorder.mMuxer.stop();
                            screenInternalAudioRecorder.mMuxer.release();
                            screenInternalAudioRecorder.mThread = null;
                            screenMediaRecorder.mAudio = null;
                            break;
                        }
                        break;
                }
            }
        });
        closer.close();
        this.mMediaRecorder = null;
        this.mMediaProjection = null;
        Log.d("ScreenMediaRecorder", "end recording");
    }

    @Override // android.media.projection.MediaProjection.Callback
    public final void onStop() {
        Log.d("ScreenMediaRecorder", "The system notified about stopping the projection");
        RecordingService recordingService = this.mListener;
        if (recordingService.mController.isRecording()) {
            Log.d(recordingService.getTag(), "Stopping recording because the system requested the stop");
            recordingService.stopService(-1);
        }
    }

    public final SavedRecording save() {
        String format = new SimpleDateFormat("'screen-'yyyyMMdd-HHmmss'.mp4'").format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", format);
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Video.Media.getContentUri("external_primary"), contentValues);
        Log.d("ScreenMediaRecorder", insert.toString());
        ScreenRecordingAudioSource screenRecordingAudioSource = this.mAudioSource;
        if (screenRecordingAudioSource == ScreenRecordingAudioSource.MIC_AND_INTERNAL || screenRecordingAudioSource == ScreenRecordingAudioSource.INTERNAL) {
            try {
                Log.d("ScreenMediaRecorder", "muxing recording");
                File createTempFile = File.createTempFile("temp", ".mp4", this.mContext.getCacheDir());
                new ScreenRecordingMuxer(createTempFile.getAbsolutePath(), this.mTempVideoFile.getAbsolutePath(), this.mTempAudioFile.getAbsolutePath()).mux();
                this.mTempVideoFile.delete();
                this.mTempVideoFile = createTempFile;
            } catch (IOException e) {
                Log.e("ScreenMediaRecorder", "muxing recording " + e.getMessage());
                e.printStackTrace();
            }
        }
        OutputStream openOutputStream = contentResolver.openOutputStream(insert, "w");
        Files.copy(this.mTempVideoFile.toPath(), openOutputStream);
        openOutputStream.close();
        File file = this.mTempAudioFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mTempVideoFile;
        boolean isLowRamDeviceStatic = ActivityManager.isLowRamDeviceStatic();
        Size size = new Size(this.mContext.getResources().getDimensionPixelSize(isLowRamDeviceStatic ? R.dimen.notification_custom_view_max_image_width : R.dimen.notification_custom_view_max_image_height_low_ram), this.mContext.getResources().getDimensionPixelSize(isLowRamDeviceStatic ? R.dimen.notification_custom_view_max_image_height : R.dimen.notification_conversation_header_separating_margin));
        SavedRecording savedRecording = new SavedRecording();
        savedRecording.mUri = insert;
        try {
            savedRecording.mThumbnailIcon = Icon.createWithBitmap(ThumbnailUtils.createVideoThumbnail(file2, size, null));
        } catch (IOException e2) {
            Log.e("ScreenMediaRecorder", "Error creating thumbnail", e2);
        }
        this.mTempVideoFile.delete();
        return savedRecording;
    }

    public final void start() {
        String str;
        ScreenRecordingAudioSource screenRecordingAudioSource;
        int[] iArr;
        char c;
        Log.d("ScreenMediaRecorder", "start recording");
        IMediaProjection asInterface = IMediaProjection.Stub.asInterface(IMediaProjectionManager.Stub.asInterface(ServiceManager.getService("media_projection")).createProjection(this.mUid, this.mContext.getPackageName(), 0, false).asBinder());
        MediaProjectionCaptureTarget mediaProjectionCaptureTarget = this.mCaptureRegion;
        if (mediaProjectionCaptureTarget != null) {
            asInterface.setLaunchCookie(mediaProjectionCaptureTarget.launchCookie);
            asInterface.setTaskId(this.mCaptureRegion.taskId);
        }
        MediaProjection mediaProjection = new MediaProjection(this.mContext, asInterface);
        this.mMediaProjection = mediaProjection;
        mediaProjection.registerCallback(this, this.mHandler);
        File cacheDir = this.mContext.getCacheDir();
        cacheDir.mkdirs();
        this.mTempVideoFile = File.createTempFile("temp", ".mp4", cacheDir);
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mMediaRecorder = mediaRecorder;
        ScreenRecordingAudioSource screenRecordingAudioSource2 = this.mAudioSource;
        ScreenRecordingAudioSource screenRecordingAudioSource3 = ScreenRecordingAudioSource.MIC;
        if (screenRecordingAudioSource2 == screenRecordingAudioSource3) {
            mediaRecorder.setAudioSource(0);
        }
        this.mMediaRecorder.setVideoSource(2);
        this.mMediaRecorder.setOutputFormat(2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int refreshRate = (int) windowManager.getDefaultDisplay().getRefreshRate();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType("video/avc");
        MediaCodecInfo.VideoCapabilities videoCapabilities = createDecoderByType.getCodecInfo().getCapabilitiesForType("video/avc").getVideoCapabilities();
        createDecoderByType.release();
        int intValue = videoCapabilities.getSupportedWidths().getUpper().intValue();
        int intValue2 = videoCapabilities.getSupportedHeights().getUpper().intValue();
        int widthAlignment = i % videoCapabilities.getWidthAlignment() != 0 ? i - (i % videoCapabilities.getWidthAlignment()) : i;
        int heightAlignment = i2 % videoCapabilities.getHeightAlignment() != 0 ? i2 - (i2 % videoCapabilities.getHeightAlignment()) : i2;
        if (intValue < widthAlignment || intValue2 < heightAlignment || !videoCapabilities.isSizeSupported(widthAlignment, heightAlignment)) {
            double d = intValue;
            str = "temp";
            double d2 = i;
            screenRecordingAudioSource = screenRecordingAudioSource3;
            double d3 = i2;
            double min = Math.min(d / d2, intValue2 / d3);
            int i3 = (int) (d2 * min);
            int i4 = (int) (d3 * min);
            if (i3 % videoCapabilities.getWidthAlignment() != 0) {
                i3 -= i3 % videoCapabilities.getWidthAlignment();
            }
            if (i4 % videoCapabilities.getHeightAlignment() != 0) {
                i4 -= i4 % videoCapabilities.getHeightAlignment();
            }
            int intValue3 = videoCapabilities.getSupportedFrameRatesFor(i3, i4).getUpper().intValue();
            if (intValue3 < refreshRate) {
                refreshRate = intValue3;
            }
            Log.d("ScreenMediaRecorder", "Resized by " + min + ": " + i3 + ", " + i4 + ", " + refreshRate);
            iArr = new int[]{i3, i4, refreshRate};
            c = 0;
        } else {
            int intValue4 = videoCapabilities.getSupportedFrameRatesFor(widthAlignment, heightAlignment).getUpper().intValue();
            if (intValue4 < refreshRate) {
                refreshRate = intValue4;
            }
            ExifInterface$$ExternalSyntheticOutline0.m("Screen size supported at rate ", "ScreenMediaRecorder", refreshRate);
            iArr = new int[]{widthAlignment, heightAlignment, refreshRate};
            str = "temp";
            c = 0;
            screenRecordingAudioSource = screenRecordingAudioSource3;
        }
        int i5 = iArr[c];
        int i6 = iArr[1];
        int i7 = iArr[2];
        this.mMediaRecorder.setVideoEncoder(2);
        this.mMediaRecorder.setVideoEncodingProfileLevel(8, 256);
        this.mMediaRecorder.setVideoSize(i5, i6);
        this.mMediaRecorder.setVideoFrameRate(i7);
        this.mMediaRecorder.setVideoEncodingBitRate((((i5 * i6) * i7) / 30) * 6);
        this.mMediaRecorder.setMaxDuration(3600000);
        this.mMediaRecorder.setMaxFileSize(5000000000L);
        if (this.mAudioSource == screenRecordingAudioSource) {
            this.mMediaRecorder.setAudioEncoder(4);
            this.mMediaRecorder.setAudioChannels(1);
            this.mMediaRecorder.setAudioEncodingBitRate(196000);
            this.mMediaRecorder.setAudioSamplingRate(44100);
        }
        this.mMediaRecorder.setOutputFile(this.mTempVideoFile);
        this.mMediaRecorder.prepare();
        Surface surface = this.mMediaRecorder.getSurface();
        this.mInputSurface = surface;
        this.mVirtualDisplay = this.mMediaProjection.createVirtualDisplay("Recording Display", i5, i6, displayMetrics.densityDpi, 16, surface, new VirtualDisplay.Callback() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder.1
            @Override // android.hardware.display.VirtualDisplay.Callback
            public final void onStopped() {
                ScreenMediaRecorder.this.onStop();
            }
        }, this.mHandler);
        this.mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() { // from class: com.android.systemui.screenrecord.ScreenMediaRecorder$$ExternalSyntheticLambda0
            @Override // android.media.MediaRecorder.OnInfoListener
            public final void onInfo(MediaRecorder mediaRecorder2, int i8, int i9) {
                RecordingService recordingService = ScreenMediaRecorder.this.mListener;
                Log.d(recordingService.getTag(), "Media recorder info: " + i8);
                recordingService.onStartCommand(new Intent(recordingService, (Class<?>) RecordingService.class).setAction("com.android.systemui.screenrecord.STOP").putExtra("android.intent.extra.user_handle", recordingService.getUserId()), 0, 0);
            }
        });
        ScreenRecordingAudioSource screenRecordingAudioSource4 = this.mAudioSource;
        ScreenRecordingAudioSource screenRecordingAudioSource5 = ScreenRecordingAudioSource.INTERNAL;
        ScreenRecordingAudioSource screenRecordingAudioSource6 = ScreenRecordingAudioSource.MIC_AND_INTERNAL;
        if (screenRecordingAudioSource4 == screenRecordingAudioSource5 || screenRecordingAudioSource4 == screenRecordingAudioSource6) {
            File createTempFile = File.createTempFile(str, ".aac", this.mContext.getCacheDir());
            this.mTempAudioFile = createTempFile;
            String absolutePath = createTempFile.getAbsolutePath();
            MediaProjection mediaProjection2 = this.mMediaProjection;
            boolean z = this.mAudioSource == screenRecordingAudioSource6;
            final ScreenInternalAudioRecorder screenInternalAudioRecorder = new ScreenInternalAudioRecorder();
            screenInternalAudioRecorder.mConfig = new ScreenInternalAudioRecorder.Config();
            screenInternalAudioRecorder.mTrackId = -1;
            screenInternalAudioRecorder.mMic = z;
            screenInternalAudioRecorder.mMuxer = new MediaMuxer(absolutePath, 0);
            FragmentManagerViewModel$$ExternalSyntheticOutline0.m("creating audio file ", absolutePath, "ScreenAudioRecorder");
            final int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2) * 2;
            Log.d("ScreenAudioRecorder", "audio buffer size: " + minBufferSize);
            screenInternalAudioRecorder.mAudioRecord = new AudioRecord.Builder().setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(44100).setChannelMask(4).build()).setAudioPlaybackCaptureConfig(new AudioPlaybackCaptureConfiguration.Builder(mediaProjection2).addMatchingUsage(1).addMatchingUsage(0).addMatchingUsage(14).build()).build();
            if (z) {
                screenInternalAudioRecorder.mAudioRecordMic = new AudioRecord(7, 44100, 16, 2, minBufferSize);
            }
            screenInternalAudioRecorder.mCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 1);
            createAudioFormat.setInteger("aac-profile", 2);
            createAudioFormat.setInteger("bitrate", 196000);
            createAudioFormat.setInteger("pcm-encoding", 2);
            screenInternalAudioRecorder.mCodec.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            screenInternalAudioRecorder.mThread = new Thread(new Runnable() { // from class: com.android.systemui.screenrecord.ScreenInternalAudioRecorder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    short[] sArr;
                    short[] sArr2;
                    int i8;
                    int read;
                    boolean z2;
                    short[] sArr3;
                    int i9;
                    ScreenInternalAudioRecorder screenInternalAudioRecorder2 = ScreenInternalAudioRecorder.this;
                    int i10 = minBufferSize;
                    byte[] bArr = new byte[i10];
                    boolean z3 = screenInternalAudioRecorder2.mMic;
                    if (z3) {
                        int i11 = i10 / 2;
                        sArr = new short[i11];
                        sArr2 = new short[i11];
                    } else {
                        sArr = null;
                        sArr2 = null;
                    }
                    short s = 0;
                    int i12 = 0;
                    int i13 = 0;
                    int i14 = 0;
                    int i15 = 0;
                    while (true) {
                        if (z3) {
                            int read2 = screenInternalAudioRecorder2.mAudioRecord.read(sArr, i12, sArr.length - i12);
                            int read3 = screenInternalAudioRecorder2.mAudioRecordMic.read(sArr2, i13, sArr2.length - i13);
                            if (read2 < 0 && read3 < 0) {
                                break;
                            }
                            if (read2 < 0) {
                                Arrays.fill(sArr, s);
                                i12 = i13;
                                read2 = read3;
                            }
                            if (read3 < 0) {
                                Arrays.fill(sArr2, s);
                                i13 = i12;
                                read3 = read2;
                            }
                            i14 = read2 + i12;
                            i15 = read3 + i13;
                            int min2 = Math.min(i14, i15);
                            read = min2 * 2;
                            int i16 = s;
                            while (true) {
                                i9 = 32767;
                                if (i16 >= min2) {
                                    break;
                                }
                                sArr2[i16] = (short) MathUtils.constrain((int) (sArr2[i16] * 1.4f), -32768, 32767);
                                i16++;
                            }
                            int i17 = 0;
                            while (i17 < min2) {
                                short constrain = (short) MathUtils.constrain(sArr[i17] + sArr2[i17], -32768, i9);
                                int i18 = i17 * 2;
                                bArr[i18] = (byte) (constrain & 255);
                                bArr[i18 + 1] = (byte) ((constrain >> 8) & 255);
                                i17++;
                                i9 = 32767;
                            }
                            for (int i19 = 0; i19 < i12 - min2; i19++) {
                                sArr[i19] = sArr[min2 + i19];
                            }
                            for (int i20 = 0; i20 < i13 - min2; i20++) {
                                sArr2[i20] = sArr2[min2 + i20];
                            }
                            i12 = i14 - min2;
                            i13 = i15 - min2;
                            i8 = 0;
                        } else {
                            i8 = 0;
                            read = screenInternalAudioRecorder2.mAudioRecord.read(bArr, 0, i10);
                        }
                        if (read < 0) {
                            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(read, i14, "read error ", ", shorts internal: ", ", shorts mic: ");
                            m.append(i15);
                            Log.e("ScreenAudioRecorder", m.toString());
                            break;
                        }
                        int i21 = i8;
                        while (true) {
                            if (read <= 0) {
                                z2 = z3;
                                sArr3 = sArr2;
                                break;
                            }
                            z2 = z3;
                            sArr3 = sArr2;
                            int dequeueInputBuffer = screenInternalAudioRecorder2.mCodec.dequeueInputBuffer(500L);
                            if (dequeueInputBuffer < 0) {
                                screenInternalAudioRecorder2.writeOutput();
                                break;
                            }
                            ByteBuffer inputBuffer = screenInternalAudioRecorder2.mCodec.getInputBuffer(dequeueInputBuffer);
                            inputBuffer.clear();
                            int capacity = inputBuffer.capacity();
                            if (read <= capacity) {
                                capacity = read;
                            }
                            read -= capacity;
                            inputBuffer.put(bArr, i21, capacity);
                            i21 += capacity;
                            screenInternalAudioRecorder2.mCodec.queueInputBuffer(dequeueInputBuffer, 0, capacity, screenInternalAudioRecorder2.mPresentationTime, 0);
                            long j = screenInternalAudioRecorder2.mTotalBytes + capacity;
                            screenInternalAudioRecorder2.mTotalBytes = j;
                            screenInternalAudioRecorder2.mConfig.getClass();
                            screenInternalAudioRecorder2.mPresentationTime = ((j / 2) * 1000000) / 44100;
                            screenInternalAudioRecorder2.writeOutput();
                            z3 = z2;
                            sArr2 = sArr3;
                        }
                        z3 = z2;
                        sArr2 = sArr3;
                        s = 0;
                    }
                    screenInternalAudioRecorder2.mCodec.queueInputBuffer(screenInternalAudioRecorder2.mCodec.dequeueInputBuffer(500L), 0, 0, screenInternalAudioRecorder2.mPresentationTime, 4);
                    screenInternalAudioRecorder2.writeOutput();
                }
            });
            this.mAudio = screenInternalAudioRecorder;
        }
        this.mMediaRecorder.start();
        ScreenRecordingAudioSource screenRecordingAudioSource7 = this.mAudioSource;
        if (screenRecordingAudioSource7 == screenRecordingAudioSource5 || screenRecordingAudioSource7 == screenRecordingAudioSource6) {
            ScreenInternalAudioRecorder screenInternalAudioRecorder2 = this.mAudio;
            synchronized (screenInternalAudioRecorder2) {
                try {
                    if (screenInternalAudioRecorder2.mStarted) {
                        if (screenInternalAudioRecorder2.mThread != null) {
                            throw new IllegalStateException("Recording already started");
                        }
                        throw new IllegalStateException("Recording stopped and can't restart (single use)");
                    }
                    screenInternalAudioRecorder2.mStarted = true;
                    screenInternalAudioRecorder2.mAudioRecord.startRecording();
                    if (screenInternalAudioRecorder2.mMic) {
                        screenInternalAudioRecorder2.mAudioRecordMic.startRecording();
                    }
                    Log.d("ScreenAudioRecorder", "channel count " + screenInternalAudioRecorder2.mAudioRecord.getChannelCount());
                    screenInternalAudioRecorder2.mCodec.start();
                    if (screenInternalAudioRecorder2.mAudioRecord.getRecordingState() != 3) {
                        throw new IllegalStateException("Audio recording failed to start");
                    }
                    screenInternalAudioRecorder2.mThread.start();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
