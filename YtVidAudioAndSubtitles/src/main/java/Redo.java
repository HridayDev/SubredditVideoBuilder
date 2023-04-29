import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import main.java.gusthavo.srt.SRTParser;
import main.java.gusthavo.srt.Subtitle;

@SuppressWarnings("unused")
public class Redo {
	// USER_INPUTS
	String VideoFile = "F:\\code\\java\\EE\\YtVidAudioAndSubtitles\\Test_Vid.mp4";
	String[] Languages = { "hi", "es", "id", "pt", "ja", "de", "ur", "vi" };
	String LibreTranslate__Url = "http://localhost:5000/";
	String SEPIA_STT__Url = "http://ip172-19-0-39-ch3t8bo1k7jg00eikitg-20741.direct.labs.play-with-docker.com/";// SLASH
	String OpenTTS__Url = "http://ip172-18-0-122-ch3sarqe69v0008i3390-5500.direct.labs.play-with-docker.com/";

	// CODE_VARS
	private String AudioFile;
	private String EnglishSubtitles;
	private HashMap<String, String> TranslatedSRT = new HashMap<>();
	private HashMap<String, String> TranslatedAudioFile = new HashMap<>();
	private String FinalVideoPath;

	public Redo() throws Exception {

//		/* 1. OG Video -> Audio */ AudioFile = VideoToAudio(VideoFile);
//		System.out.println("\n\nAudioFile: " + AudioFile.toString());
//
//		/* 2. Audio -> Subtitles */ EnglishSubtitles = AudioToSubtitles(AudioFile);
//		System.out.println("\n\nEnglishSubtitles: " + !EnglishSubtitles.isEmpty());
//
//		/* 3. Subtitles -> Translated Subtitles */ TranslatedSRT = TranslateSubtitles(EnglishSubtitles, Languages);
//		System.out.println(
//				"\n\nTranslatedSRT: " + !(TranslatedSRT.toString().isBlank() && TranslatedSRT.toString().isEmpty()));
//
//		/* 4. Translated SRT -> Translated WAV */ TranslatedAudioFile = TransSubToTransAudio(Languages, TranslatedSRT);
//		System.out.println("\n\nTranslatedAudioFile: " + TranslatedAudioFile);
//
//		/* 5. OG Video = OG Video + Translated Audio + Translated Subtitles */ FinalVideoPath = GenerateFinalVideo(
//				TranslatedAudioFile, VideoFile, TranslatedSRT);
//		System.out.println("\n\nFinalVideoPath: " + FinalVideoPath);

		String in = "1\n" + "00:00:00,498 --> 00:00:02,827\n" + "- Here's what I love most\n" + "about food and diet.\n"
				+ "\n" + "2\n" + "00:00:02,827 --> 00:00:06,383\n" + "We all eat several times a day,\n"
				+ "and we're totally in charge\n" + "\n" + "3\n" + "00:00:06,383 --> 00:00:09,427\n"
				+ "of what goes on our plate\n" + "and what stays off." + "\n";
		System.out.println("In: \n" + in);

		String spacer = "#";
		int count = 150;
		for (int i = 0; i < count; i++) {
			System.out.print(spacer);
		}
		System.out.println();
		for (int i = 0; i < count; i++) {
			System.out.print(spacer);
		}
		System.out.println();
		for (int i = 0; i < count; i++) {
			System.out.print(spacer);
		}

		System.out.println("\nOut: \n" + SrtParser(in));

//		System.out.println(AudioToSubtitles(VideoToAudio(VideoFile)));
	}

	// DONE
	private String VideoToAudio(String VideoFilePath) throws Exception {
		String AudioFilePath = Path.of(VideoFilePath).getFileName() + "_Wave-Audio.wav";
		String cmd = ("ffmpeg -i \"{{[[((VideoFilePath))]]}}\" " + AudioFilePath).replace("{{[[((VideoFilePath))]]}}",
				VideoFilePath);
		@SuppressWarnings("deprecation")
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while (reader.ready()) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		reader.close();

		return AudioFilePath;
	}

	// TODO
	private String AudioToSubtitles(String AudioFilePath) throws Exception {
		return null;
	}

	// DONE
	private HashMap<String, String> TranslateSubtitles(String engSub, String[] languages) {
		HashMap<String, String> translatedSubtitles = new HashMap<>();
		for (String lang : languages) {
			System.out.println("lang: " + lang);
			String url = LibreTranslate__Url
					+ ((LibreTranslate__Url.charAt(LibreTranslate__Url.length() - 1) == '/') ? "translate"
							: "/translate");
			HashMap<String, String> data = new HashMap<>();
			data.put("q", engSub);
			data.put("source", "en");
			data.put("target", lang);
			data.put("format", "text");
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(new JSONObject(data).toString())).build();

			HttpResponse<String> response;
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());

				String translatedText = new JSONObject(response.body().toString()).getString("translatedText");
				translatedSubtitles.put(lang, translatedText);
			} catch (JSONException e) {
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return translatedSubtitles;
	}

	// TODO
	public HashMap<String, String> TransSubToTransAudio(String[] Langs, HashMap<String, String> Sub) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		for (String lang : Langs) {

		}
		return result;
	}

	// Helper Method:
	// DONE
	private ArrayList<Subtitle> SrtParser(String SRT) {
		
		
		
		ArrayList<Subtitle> subtitles = SRTParser.getSubtitlesFromFile(SRT);
		
		return subtitles;
	}

	// LATE
	private String GenerateFinalVideo(HashMap<String, String> AudioMap, String VideoPath, HashMap<String, String> Sub) {

		return "Final-Combined__" + Path.of(VideoPath).getFileName();
	}

}
