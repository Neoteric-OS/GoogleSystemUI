package com.android.wm.shell.transition.tracing;

import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import com.android.internal.util.TraceBuffer;
import com.android.wm.shell.nano.HandlerMapping;
import com.android.wm.shell.nano.Transition;
import com.android.wm.shell.nano.WmShellTransitionTraceProto;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.transition.Transitions;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LegacyTransitionTracer implements ShellCommandHandler.ShellCommandActionHandler {
    public final AnonymousClass1 mProtoProvider;
    public final Object mEnabledLock = new Object();
    public final TraceBuffer mTraceBuffer = new TraceBuffer(15360, new AnonymousClass1(), new Consumer() { // from class: com.android.wm.shell.transition.tracing.LegacyTransitionTracer$$ExternalSyntheticLambda0
        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            LegacyTransitionTracer legacyTransitionTracer = LegacyTransitionTracer.this;
            if (legacyTransitionTracer.mRemovedFromTraceCallbacks.containsKey(obj)) {
                ((Runnable) legacyTransitionTracer.mRemovedFromTraceCallbacks.get(obj)).run();
                legacyTransitionTracer.mRemovedFromTraceCallbacks.remove(obj);
            }
        }
    });
    public final Map mRemovedFromTraceCallbacks = new HashMap();
    public final Map mHandlerIds = new HashMap();
    public final Map mHandlerUseCountInTrace = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.transition.tracing.LegacyTransitionTracer$1, reason: invalid class name */
    public final class AnonymousClass1 implements TraceBuffer.ProtoProvider {
        public final byte[] getBytes(Object obj) {
            return MessageNano.toByteArray((MessageNano) obj);
        }

        public final int getItemSize(Object obj) {
            return ((MessageNano) obj).getCachedSize();
        }

        public final void write(Object obj, Queue queue, OutputStream outputStream) {
            WmShellTransitionTraceProto wmShellTransitionTraceProto = (WmShellTransitionTraceProto) obj;
            wmShellTransitionTraceProto.transitions = (Transition[]) queue.toArray(new Transition[0]);
            outputStream.write(MessageNano.toByteArray(wmShellTransitionTraceProto));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class LogAndPrintln {
        /* renamed from: -$$Nest$sme, reason: not valid java name */
        public static void m909$$Nest$sme(PrintWriter printWriter) {
            Log.e("ShellTransitionTracer", "Tracing is not supported on user builds.");
            if (printWriter != null) {
                printWriter.println("ERROR: Tracing is not supported on user builds.");
                printWriter.flush();
            }
        }
    }

    public final void logDispatched(int i, final Transitions.TransitionHandler transitionHandler) {
        int size;
        if (this.mHandlerIds.containsKey(transitionHandler)) {
            size = ((Integer) this.mHandlerIds.get(transitionHandler)).intValue();
        } else {
            size = this.mHandlerIds.size() + 1;
            this.mHandlerIds.put(transitionHandler, Integer.valueOf(size));
        }
        Transition transition = new Transition();
        transition.id = i;
        transition.dispatchTimeNs = SystemClock.elapsedRealtimeNanos();
        transition.handler = size;
        this.mHandlerUseCountInTrace.put(transitionHandler, Integer.valueOf(((Integer) ((HashMap) this.mHandlerUseCountInTrace).getOrDefault(transitionHandler, 0)).intValue() + 1));
        this.mRemovedFromTraceCallbacks.put(transition, new Runnable() { // from class: com.android.wm.shell.transition.tracing.LegacyTransitionTracer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LegacyTransitionTracer legacyTransitionTracer = LegacyTransitionTracer.this;
                legacyTransitionTracer.mHandlerUseCountInTrace.put(transitionHandler, Integer.valueOf(((Integer) legacyTransitionTracer.mHandlerUseCountInTrace.get(r2)).intValue() - 1));
            }
        });
        this.mTraceBuffer.add(transition);
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final boolean onShellCommand(PrintWriter printWriter, String[] strArr) {
        char c;
        String str = strArr[0];
        int hashCode = str.hashCode();
        if (hashCode == -390772652) {
            if (str.equals("save-for-bugreport")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 3540994) {
            if (hashCode == 109757538 && str.equals("start")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("stop")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            if (Build.IS_USER) {
                LogAndPrintln.m909$$Nest$sme(printWriter);
            } else {
                Trace.beginSection("Tracer#startTrace");
                Log.i("ShellTransitionTracer", "Starting shell transition trace.");
                if (printWriter != null) {
                    printWriter.println("Starting shell transition trace.");
                    printWriter.flush();
                }
                synchronized (this.mEnabledLock) {
                    this.mTraceBuffer.resetBuffer();
                    this.mTraceBuffer.setCapacity(5120000);
                }
                Trace.endSection();
            }
            return true;
        }
        if (c != 1) {
            if (c != 2) {
                printWriter.println("Invalid command: " + strArr[0]);
                printShellCommandHelp(printWriter, "");
                return false;
            }
            if (Build.IS_USER) {
                LogAndPrintln.m909$$Nest$sme(printWriter);
            } else {
                Trace.beginSection("TransitionTracer#saveForBugreport");
                synchronized (this.mEnabledLock) {
                    writeTraceToFileLocked(printWriter, new File("/data/misc/wmtrace/shell_transition_trace.winscope"));
                }
                Trace.endSection();
            }
            return true;
        }
        File file = new File("/data/misc/wmtrace/shell_transition_trace.winscope");
        if (Build.IS_USER) {
            LogAndPrintln.m909$$Nest$sme(printWriter);
        } else {
            Trace.beginSection("Tracer#stopTrace");
            Log.i("ShellTransitionTracer", "Stopping shell transition trace.");
            if (printWriter != null) {
                printWriter.println("Stopping shell transition trace.");
                printWriter.flush();
            }
            synchronized (this.mEnabledLock) {
                writeTraceToFileLocked(printWriter, file);
                this.mTraceBuffer.resetBuffer();
                this.mTraceBuffer.setCapacity(15360);
            }
            Trace.endSection();
        }
        return true;
    }

    @Override // com.android.wm.shell.sysui.ShellCommandHandler.ShellCommandActionHandler
    public final void printShellCommandHelp(PrintWriter printWriter, String str) {
        printWriter.println(str + "start");
        printWriter.println(str + "  Start tracing the transitions.");
        printWriter.println(str + "stop");
        printWriter.println(str + "  Stop tracing the transitions.");
        printWriter.println(str + "save-for-bugreport");
        printWriter.println(str + "  Flush in memory transition trace to file.");
    }

    public final void writeHandlerMappingToProto(WmShellTransitionTraceProto wmShellTransitionTraceProto) {
        ArrayList arrayList = new ArrayList();
        for (Transitions.TransitionHandler transitionHandler : this.mHandlerUseCountInTrace.keySet()) {
            if (((Integer) this.mHandlerUseCountInTrace.get(transitionHandler)).intValue() > 0) {
                HandlerMapping handlerMapping = new HandlerMapping();
                handlerMapping.id = ((Integer) this.mHandlerIds.get(transitionHandler)).intValue();
                handlerMapping.name = transitionHandler.getClass().getName();
                arrayList.add(handlerMapping);
            }
        }
        wmShellTransitionTraceProto.handlerMappings = (HandlerMapping[]) arrayList.toArray(new HandlerMapping[0]);
    }

    public final void writeTraceToFileLocked(PrintWriter printWriter, File file) {
        Trace.beginSection("TransitionTracer#writeTraceToFileLocked");
        try {
            WmShellTransitionTraceProto wmShellTransitionTraceProto = new WmShellTransitionTraceProto();
            wmShellTransitionTraceProto.magicNumber = 4990904633914510679L;
            writeHandlerMappingToProto(wmShellTransitionTraceProto);
            wmShellTransitionTraceProto.realToElapsedTimeOffsetNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - SystemClock.elapsedRealtimeNanos();
            String str = "Writing file to " + file.getAbsolutePath() + " from process " + Process.myPid();
            Log.i("ShellTransitionTracer", str);
            if (printWriter != null) {
                printWriter.println(str);
                printWriter.flush();
            }
            this.mTraceBuffer.writeTraceToFile(file, wmShellTransitionTraceProto);
        } catch (IOException e) {
            Log.e("ShellTransitionTracer", "Unable to write buffer to file", e);
            if (printWriter != null) {
                printWriter.println("ERROR: Unable to write buffer to file ::\n " + e);
                printWriter.flush();
            }
        }
        Trace.endSection();
    }
}
