package wave;

/**
 * This class contains a structure of WAVE file.
 *
 * The header of a WAV (RIFF) file is 44 bytes long and has the following format:
 * 
 * Positions	Sample Value	Description
 * 1 - 4	"RIFF"	Marks the file as a riff file. Characters are each 1 byte long.
 * 5 - 8	File size (integer)	Size of the overall file - 8 bytes, in bytes (32-bit integer). Typically, you'd fill this in after creation.
 * 9 -12	"WAVE"	File Type Header. For our purposes, it always equals "WAVE".
 * 13-16	"fmt "	Format chunk marker. Includes trailing null
 * 17-20	16	Length of format data as listed above
 * 21-22	1	Type of format (1 is PCM) - 2 byte integer
 * 23-24	2	Number of Channels - 2 byte integer
 * 25-28	44100	Sample Rate - 32 byte integer. Common values are 44100 (CD), 48000 (DAT). Sample Rate = Number of Samples per second, or Hertz.
 * 29-32	176400	(Sample Rate * BitsPerSample * Channels) / 8.
 * 33-34	4	(BitsPerSample * Channels) / 8.1 - 8 bit mono2 - 8 bit stereo/16 bit mono4 - 16 bit stereo
 * 35-36	16	Bits per sample
 * 37-40	"data"	"data" chunk header. Marks the beginning of the data section.
 * 41-44	File size (data)	Size of the data section.
 *
 * @see https://github.com/tkaczenko/WavReader
 * @see <a href ="https://github.com/tkacz-/WavReader/blob/master/wav-sound-format.gif">
 * Structure of wave format
 * </a>
 * @see http://www.topherlee.com/software/pcm-tut-wavformat.html 
 */
public class WavHeader {
    private byte[] chunkID = new byte[4];
    private int chunkSize;
    private byte[] format = new byte[4];
    private byte[] subChunk1ID = new byte[4];
    private int subChunk1Size;
    private short audioFormat;
    private short numChannels;
    private int sampleRate;
    private int byteRate;
    private short blockAlign;
    private short bitsPerSample;
    private byte[] subChunk2ID = new byte[4];
    private int subChunk2Size;

    @Override
    public String toString() {
        return "The RIFF chunk desriptor: " + new String(this.getChunkID()) + "\n" +
                "Size of this chunk: " + this.getChunkSize() + "\n" +
                "Format: " + new String(this.getFormat()) + "\n" + "\n" +
                "fmt subchunk: " + new String(this.getSubChunk1ID()) + "\n" +
                "Size of this chunk: " + this.getSubChunk1Size() + "\n" +
                "Audio format: " + this.getAudioFormat() + "\n" +
                "Number of channels: " + this.getNumChannels() + "\n" +
                "Sample rate: " + this.getSampleRate() + "\n" +
                "Byte rate: " + this.getByteRate() + "\n" +
                "Block align: " + this.getBlockAlign() + "\n" +
                "Bits per sample: " + this.getBitsPerSample() + "\n" + "\n" +
                "data subchunk: " + new String(this.getSubChunk2ID()) + "\n" +
                "Size of this chunk: " + this.getSubChunk2Size();
    }

    public byte[] getChunkID() {
        return chunkID;
    }

    public void setChunkID(byte[] chunkID) {
        this.chunkID = chunkID;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] getFormat() {
        return format;
    }

    public void setFormat(byte[] format) {
        this.format = format;
    }

    public byte[] getSubChunk1ID() {
        return subChunk1ID;
    }

    public void setSubChunk1ID(byte[] subChunk1ID) {
        this.subChunk1ID = subChunk1ID;
    }

    public int getSubChunk1Size() {
        return subChunk1Size;
    }

    public void setSubChunk1Size(int subChunk1Size) {
        this.subChunk1Size = subChunk1Size;
    }

    public short getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(short audioFormat) {
        this.audioFormat = audioFormat;
    }

    public short getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(short numChannels) {
        this.numChannels = numChannels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getByteRate() {
        return byteRate;
    }

    public void setByteRate(int byteRate) {
        this.byteRate = byteRate;
    }

    public short getBlockAlign() {
        return blockAlign;
    }

    public void setBlockAlign(short blockAlign) {
        this.blockAlign = blockAlign;
    }

    public short getBitsPerSample() {
        return bitsPerSample;
    }

    public void setBitsPerSample(short bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public byte[] getSubChunk2ID() {
        return subChunk2ID;
    }

    public void setSubChunk2ID(byte[] subChunk2ID) {
        this.subChunk2ID = subChunk2ID;
    }

    public int getSubChunk2Size() {
        return subChunk2Size;
    }

    public void setSubChunk2Size(int subChunk2Size) {
        this.subChunk2Size = subChunk2Size;
    }
}