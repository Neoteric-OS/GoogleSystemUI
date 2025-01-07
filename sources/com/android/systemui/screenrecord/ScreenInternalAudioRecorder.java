package com.android.systemui.screenrecord;

import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import java.nio.ByteBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenInternalAudioRecorder {
    public AudioRecord mAudioRecord;
    public AudioRecord mAudioRecordMic;
    public MediaCodec mCodec;
    public Config mConfig;
    public boolean mMic;
    public MediaMuxer mMuxer;
    public long mPresentationTime;
    public boolean mStarted;
    public Thread mThread;
    public long mTotalBytes;
    public int mTrackId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public final String toString() {
            return "channelMask=4\n   encoding=2\n sampleRate=44100\n bufferSize=131072\n privileged=true\n legacy app looback=false";
        }
    }

    public final void writeOutput() {
        while (true) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.mCodec.dequeueOutputBuffer(bufferInfo, 500L);
            if (dequeueOutputBuffer == -2) {
                this.mTrackId = this.mMuxer.addTrack(this.mCodec.getOutputFormat());
                this.mMuxer.start();
            } else {
                if (dequeueOutputBuffer == -1 || this.mTrackId < 0) {
                    return;
                }
                ByteBuffer outputBuffer = this.mCodec.getOutputBuffer(dequeueOutputBuffer);
                if ((bufferInfo.flags & 2) == 0 || bufferInfo.size == 0) {
                    this.mMuxer.writeSampleData(this.mTrackId, outputBuffer, bufferInfo);
                }
                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
            }
        }
    }
}
