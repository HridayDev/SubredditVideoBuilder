import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//import javax.sound.sampled.AudioFileFormat;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
//import org.json.JSONObject;

//import edu.cmu.sphinx.api.Configuration;
//import edu.cmu.sphinx.api.SpeechAligner;
//import edu.cmu.sphinx.api.SpeechResult;
//import edu.cmu.sphinx.api.StreamSpeechRecognizer;
//import edu.cmu.sphinx.result.WordResult;
//import io.github.jetkai.openai.api.CreateTranscription;
//import io.github.jetkai.openai.api.data.audio.AudioData;
//import io.github.jetkai.openai.openai.OpenAI;

public class Main {
	// USER_INPUTS
//	String VideoFilePath = "F:\\code\\java\\EE\\YtVidAudioAndSubtitles\\Test_Vid.mp4";
//	String[] lang = { "hi", "es", "id", "pa", "pt", "ja", "de", "ur", "vi" };
//	boolean fastMode = true;
//	String Key = "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";
	// CODE_VARS
//	@SuppressWarnings("unused")
//	private String ENG_SRT;
//	@SuppressWarnings("unused")
//	private Path AudioFile;
//	@SuppressWarnings("unused")
//	private HashMap<String, String> TranslatedSRT = new HashMap<>();
//	@SuppressWarnings("unused")
//	private HashMap<String, Path> TranslatedAudioFile = new HashMap<>();

	public static void main(String[] args) throws Exception {
//		String EngSub = "Test Subtitles! 1:1,1->1:1,1[1]";
//		String lang = "hi";
//		new Main();
//		String lang = "hi";
//		String EngSub = "Hello, World!";
//		String returnVal = "NOPE!";
//		System.out.println("lang: " + lang);
//		String json = "{" + "\"q\": \"" + EngSub + "\"," + "\"source\": \"en\"," + "\"target\": \"" + lang + "\","
//				+ "\"format\": \"text\"" + "}";
//
//		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:5000/translate").openConnection();
//		con.setRequestMethod("POST");
//		con.setRequestProperty("Content-Type", "application/json");
//		con.setDoOutput(true);
//		try (OutputStream os = con.getOutputStream()) {
//			byte[] input = json.getBytes(StandardCharsets.UTF_8);
//			os.write(input, 0, input.length);
//		}
//
//		int status = con.getResponseCode();
//		if (status >= 200 && status < 300) {
//			BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String read;
//			StringBuffer responseStr = new StringBuffer();
//			while ((read = bf.readLine()) != null) {
//				responseStr.append(read);
//			}
//			bf.close();
//			returnVal = new JSONObject(responseStr.toString()).getString("translatedText");
//		} else {
//			// handle error response
//			System.err.println("Error response from server: "
//					+ new BufferedReader(new InputStreamReader(con.getErrorStream())).readLine());
//		}
//		con.disconnect();
//		System.out.println("Return Value: " + returnVal);
//		
		new Redo();
		
		
		
//        HttpClient httpClient = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:5000/translate"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString("{\"q\": \"" + EngSub + "\",\"source\":\"en\",\"target\":\"" + lang + "\"}"))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
	}

	/*
	 * 
	 * mp4 -> mp3 ==> `VidToAud`____[-] mp3 -> srt ==>'MAKE_IT'____[] eng(srt) ->
	 * lang[](srt) ==> 'loop over `lang` with `TRANSLATE`____[] store
	 * 
	 * `TranslatedSRT`' `TranslatedSRT` - audio[mp3]____[]
	 * 
	 * public Main() throws Exception {
	 * System.out.println("mp4 -> mp3 ==> `VidToAud`"); AudioFile =
	 * VidToAud(Path.of(VideoFilePath)); System.out.println("Yo");
	 * System.out.println("mp3 -> srt ==>`generateSrtSubtitles`");
	 * 
	 * String sub = fastMode ? fastTranscript(AudioFile, Key) :
	 * slowTranscript(AudioFile); FileWriter myWriter = new
	 * FileWriter("subtitles.srt"); myWriter.write(sub + "\n"); myWriter.close();
	 * 
	 * System.out.println("Written To File!"); ENG_SRT =
	 * generateSrtSubtitles(AudioFile,
	 * "Welcome back students. This is a new course free course that I'm making on YouTube for coding for absolute beginners who do not know how to code.nothing about coding. I am making this course. I am Hedi Dev. Let's get started. So first, why should we even code? Why should we even bother watching this?video because coding is a very valuable skill in the digital age and is increasingly becoming a crucial component of many industries.This is basically the next generation and helps us develop problem solving skills and logical thinking which can also apply to various real world scenarios.one big misconception, you don't need to be a maths expert to be a programmer. And it is a highly sought after skill.in the job market. And it's a real big job market which is increasingly growing and AI isn't going to take over the world anytime soon in the next 100 years.so you don't have to worry about it and it's fun it's not hard, it's not easy it's just fun if you want to do it, do ityou should do it. And making and building softwares can be empowering and basically you will love it. I know.Why should you watch this course? There are hundreds of others available. This is because this is just the starting point.you will build a solid foundation and know all the basics of programming and know everything to build a good enough app after that.this course.assignments so you could practice and my personal favorite we'll be using java it's really easy I'm really a manand first what is Java? So basically it's a programming language. Basically think a programming language.is a thing that we use, programmers use to actually write something that we can understand. to write something in a language.instead of writing the human instead of the right thing the computer readable code that we can't read which is the human. So, we have to write the human. So, we have to write thejust a long string of zeros and ones. And now for why Java it's very popular widely used. It's everywhere and the biggest thing you all know.you only have to write java code once and it will run on almost every mainstream OS and device it runs it just takes a few secondsworks almost every time and it has unlimited support and possibilities. It is easy to pick up on the internet.and as a huge community. It's used everywhere in the enterprise world and we can make basically anything and everything. And so, I'm going to go ahead and start.the first thing we need to download the JDK which just includes everything like the java virtual machine, the java compiler and everything that we need to download.need to start coding running a java code which can be downloaded from here. Link in the description.just go down here choose your OS I'm on Windows so I'm gonna use that and click on the installer either this or thisIt's your choice. I'm going to choose this and just download it Now that it's downloaded, I'm going to run it.don't install just run this file. It's the installer. I actually forgot.to record this but it's the same thing you get the point and then click on close and then you're done.successfully installed Java and then we can set up the IDE. but before that to verify that we have installed JavaJava. Just click on Windows R type gnd or if you are on Linux or Mac OS just click onopen the terminal app type java-versionand it will show up Underscore it Let's now start with IDE It is basically software that provides developers with a free access to the IDEtools and features for writing debugging and testing code. It just makes our lives a lot easier and makes coding infinitely better, faster and more fun.more efficient."
	 * ); System.out.println("\n\n" + ENG_SRT);
	 * 
	 * }
	 * 
	 * public Path VidToAud(Path VIDEO_PATH) { Path audioPath =
	 * Path.of(VIDEO_PATH.getFileName() + "_AUDIO-ONLY_ENG.wav"); String cmd =
	 * "ffmpeg -i \"" + VIDEO_PATH.toString() + "\" " + audioPath.toString(); try {
	 * 
	 * @SuppressWarnings("deprecation") Process process =
	 * Runtime.getRuntime().exec(cmd); System.out.println("Converting To Audio");
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(process.getInputStream())); while (reader.ready()) { String
	 * line = reader.readLine(); if (line == null) { break; }
	 * System.out.println(line); }
	 * 
	 * reader.close(); } catch (Exception e) { e.printStackTrace(); } return
	 * audioPath; } public String fastTranscript(Path AUDIO_PATH, String OpenAIKey)
	 * throws Exception { StringBuilder transcript = new StringBuilder();
	 * AudioInputStream audioInputStream = null; AudioFormat format = null; File
	 * file = new File(AUDIO_PATH.toString());
	 * 
	 * try { audioInputStream = AudioSystem.getAudioInputStream(file); format =
	 * audioInputStream.getFormat(); } catch (UnsupportedAudioFileException e) {
	 * throw new Exception("Unsupported file format"); }
	 * System.out.println(format.getSampleRate() + "-" + format.getFrameSize());
	 * byte[] buffer = new byte[(int) (format.getSampleRate() *
	 * format.getFrameSize() * 100)]; int chunkIndex = 0; int bytesRead;
	 * 
	 * while ((bytesRead = audioInputStream.read(buffer)) != -1) { // write the
	 * chunk to a temporary file String tempFileName = "temp_" + chunkIndex +
	 * ".wav"; Path tempFilePath = Paths.get(tempFileName); AudioSystem.write( new
	 * AudioInputStream(new ByteArrayInputStream(buffer), format, bytesRead /
	 * format.getFrameSize()), AudioFileFormat.Type.WAVE, tempFilePath.toFile());
	 * 
	 * System.out.println("In " +
	 * chunkIndex);/////////////////////////////////////////////////////////////////
	 * /// // create AudioData from the temporary file and pass it to the custom
	 * library AudioData transcriptionData = AudioData.create(tempFilePath); OpenAI
	 * openAI =
	 * OpenAI.builder().setApiKey(OpenAIKey).createTranscription(transcriptionData).
	 * build() .sendRequest(); Optional<CreateTranscription> createTranscription =
	 * openAI.transcription(); String currentTranscript =
	 * createTranscription.map(CreateTranscription::asText).orElse(null);
	 * transcript.append(currentTranscript);
	 * 
	 * System.out.println("Out " +
	 * chunkIndex);/////////////////////////////////////////////////////////////////
	 * // // delete the temporary file Files.deleteIfExists(tempFilePath);
	 * 
	 * chunkIndex++; }
	 * 
	 * return transcript.toString(); }
	 * 
	 * public String slowTranscript(Path AUDIO_PATH) throws Exception {
	 * 
	 * // Step 1: Set up configuration and load models Configuration configuration =
	 * new Configuration();
	 * 
	 * // Set path to acoustic and language models
	 * configuration.setAcousticModelPath(
	 * "resource:/edu/cmu/sphinx/models/en-us/en-us");
	 * configuration.setDictionaryPath(
	 * "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
	 * configuration.setLanguageModelPath(
	 * "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
	 * 
	 * // Step 2: Create StreamSpeechRecognizer StreamSpeechRecognizer recognizer =
	 * new StreamSpeechRecognizer(configuration);
	 * 
	 * // Step 3: Create input stream from audio file File audioFile = new
	 * File(AUDIO_PATH.toString()); FileInputStream inputStream = new
	 * FileInputStream(audioFile);
	 * 
	 * // Step 4: Start recognition recognizer.startRecognition(inputStream);
	 * System.out.println("Slow Transcript");
	 * 
	 * // Step 5: Generate word results and add to StringBuilder StringBuilder
	 * transcriptBuilder = new StringBuilder(); SpeechResult result; while ((result
	 * = recognizer.getResult()) != null) { List<WordResult> wordResults =
	 * result.getWords(); for (WordResult wordResult : wordResults) {
	 * transcriptBuilder.append(wordResult.getWord()).append(' '); } }
	 * 
	 * recognizer.stopRecognition();
	 * 
	 * inputStream.close(); return transcriptBuilder.toString().trim(); }
	 * 
	 * @SuppressWarnings("unused") public String generateSrtSubtitles(Path
	 * AUDIO_PATH, String transcript) throws Exception { String AcousticModelPath =
	 * "resource:/edu/cmu/sphinx/models/en-us/en-us"; String DictionaryPath =
	 * "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict"; String
	 * LanguageModelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin";
	 * 
	 * SpeechAligner aligner = new SpeechAligner(AcousticModelPath, DictionaryPath,
	 * null); List<WordResult> results = aligner.align(AUDIO_PATH.toUri().toURL(),
	 * transcript); List<String> stringResults = new ArrayList<String>(); for
	 * (WordResult wr : results) { stringResults.add(wr.getWord().getSpelling()); }
	 * 
	 * FileWriter myWriter = new FileWriter(AUDIO_PATH.getFileName() +
	 * "__SUBTITLES_ENG.srt"); myWriter.write(String.join("", stringResults) +
	 * "\n"); myWriter.close();
	 * 
	 * return AUDIO_PATH.getFileName() + "__SUBTITLES_ENG.srt"; }
	 * 
	 * public String TRANSLATE(String ENG_TXT, String LANG) { String json =
	 * "Can't Translate!\nCheck If LibreTranslate Is Running!"; try { URI url =
	 * URI.create("http://localhost:5000/translate"); String body = "{\"q\":\"" +
	 * ENG_TXT + "\",\"source\":\"en\",\"target\":\"" + LANG + "\"}";
	 * System.out.println("Translating!"); HttpRequest req =
	 * HttpRequest.newBuilder().uri(url) .header("Content-Type",
	 * "application/json; charset=UTF-8")
	 * .POST(HttpRequest.BodyPublishers.ofString(body)).build();
	 * 
	 * json = HttpClient.newHttpClient().send(req,
	 * HttpResponse.BodyHandlers.ofString()).body(); } catch (Exception e) {
	 * e.printStackTrace(); return json; } try { return new
	 * JSONObject(json).getString("translatedText"); } catch (Exception e) { return
	 * json.toString(); } } public String TTS(String TXT, String EngineTTS, String
	 * LANG) { try { String Url =
	 * "http://localhost:5500/api/tts?cache=false&vocoder=high&denoiserStrength=0.03";
	 * Url += "&text=" + TXT; Url += "&voice=" + EngineTTS + ":" + LANG; URL url =
	 * new URL(Url); HttpURLConnection conn = (HttpURLConnection)
	 * url.openConnection(); conn.setRequestMethod("GET");
	 * conn.setRequestProperty("accept", "*\\*"); InputStream in =
	 * conn.getInputStream(); FileOutputStream out = new
	 * FileOutputStream("Audio-Output__" + LANG + "__.wav"); byte[] buffer = new
	 * byte[8192]; int bytesRead; while ((bytesRead = in.read(buffer)) != -1) {
	 * out.write(buffer, 0, bytesRead); } out.close(); in.close();
	 * System.out.println("File saved to disk."); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 */

}