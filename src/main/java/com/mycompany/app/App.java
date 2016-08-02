package com.mycompany.app;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println("Hello Maven");
        //System.out.println(args[0]);
        //System.out.println(args[1]);
        //FFmpeg ffmpeg = new FFmpeg("C://ffmpeg//bin//ffmpeg.exe");
        FFmpeg ffmpeg = new FFmpeg();
        //FFprobe ffprobe = new FFprobe("C://ffmpeg//bin//ffprobe.exe");
        FFprobe ffprobe = new FFprobe();


        //System.out.println(args[0]);
        //System.out.println(args[1]);

        FFmpegBuilder builder = new FFmpegBuilder()

                //.setInput("/home/titinho/Videos/CH_SLO/raw_video.mp4")     // Filename, or a FFmpegProbeResult
                //.setInput("C://Videos//CH_SLO//raw_video.mp4")     // Filename, or a FFmpegProbeResult
                .setInput(args[0])     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

                //.addOutput("/home/titinho/Videos/CH_SLO/raw_video_.mp4")   // Filename for the destination
                //.addOutput("C://Videos//CH_SLO//___.mp4")   // Filename for the destination
                .addOutput(args[1])   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                /*.setTargetSize(250_000)  // Aim for a 250KB file

                .disableSubtitle()       // No subtiles

                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(32768)      // at 32 kbit/s*/

                .setVideoCodec("libx265")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                //.setVideoResolution(640, 480) // at 640x480 resolution
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();

        // Or run a two-pass encode (which is slower at the cost of better quality)
        executor.createTwoPassJob(builder).run();

        System.out.println( "Hello World!" );
    }
}
