package i.mehesz.Interview.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final Client client;
    private final String model;

    public GeminiService(@Value("${gemini.model:gemini-2.5-flash}") String model) {
        this.client = new Client();
        this.model = model;
    }


    public String generateText(String prompt) {
        GenerateContentResponse resp =
                client.models.generateContent(model, prompt, null);
        return resp.text();
    }
}
