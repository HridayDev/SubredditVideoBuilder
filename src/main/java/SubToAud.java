import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class SubToAud {

	@SuppressWarnings("unused")
	private HashMap<String, String> TransSubToTransAudio(String[] Langs, HashMap<String, String> Sub) throws Exception {
		HashMap<String, String> TranslatedAudio = new HashMap<String, String>();
		for (String lang : Langs) {

			String LangSubtitles = Sub.get(lang);
			// Parse SRT subtitles
			String[] lines = LangSubtitles.split("\\r?\\n");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].matches("\\d+")) {
					// Skip subtitle number
					i++;
				} else if (lines[i].matches("\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}")) {
					// Skip timecode
					i++;
				} else if (!lines[i].isEmpty()) {
					// Add subtitle text to speech synthesis input
					sb.append(lines[i]);
					sb.append(' ');
				}
			}
			String textToSpeak = sb.toString().trim();

			// Synthesize speech from text
			String audioUrl = null;
			URL url = new URL("http://localhost:5500/api/tts?vocoder=high&denoiserStrength=0.03&cache=false&text="
					+ URLEncoder.encode(textToSpeak, "UTF-8") + "&voice=" + "espeak:" + lang);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "*/*");
			connection.connect();
			if (connection.getResponseCode() == 200) {
				// Save audio file to temporary location
				Path tempAudioFile = Paths.get("AUDIO_" + lang + ".wav");
				try (InputStream in = connection.getInputStream()) {
					Files.copy(in, tempAudioFile, StandardCopyOption.REPLACE_EXISTING);
				}
				audioUrl = tempAudioFile.toAbsolutePath().toString();
			} else {
				throw new IOException("Unexpected HTTP response code: " + connection.getResponseCode());
			}

			TranslatedAudio.put(lang, audioUrl);
		}
		return TranslatedAudio;
	}

}
