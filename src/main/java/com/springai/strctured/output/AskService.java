package com.springai.strctured.output;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

@Service
public class AskService {

	private final ChatClient chatClient;

	public AskService(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	public MovieRecommendation returnStructuredResponse(String movie) {

		BeanOutputConverter<MovieRecommendation> converter = new BeanOutputConverter<>(MovieRecommendation.class);

		String format = converter.getFormat();

		PromptTemplate pt = new PromptTemplate("""
				Provide movie details for "{movie}".
				{format}
				""");

		Prompt prompt = pt.create(Map.of("movie", movie, "format", format));

	    String content = chatClient
	            .prompt(prompt)
	            .call()
	            .content();

	    System.out.println("RAW RESPONSE: " + content);

	    return converter.convert(content); 
	    
	}
	
	
	public List<String> returnListStructuredResponse(String movie) {
        ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());

        String format = listOutputConverter.getFormat();
        
    	PromptTemplate pt = new PromptTemplate("""
				Provide movie details for "{movie}".
				{format}
				""");    	
        
        Prompt prompt = pt.create(Map.of("movie", movie, "format", format));
        
        String content = chatClient
	            .prompt(prompt)
	            .call()
	            .content();

        return listOutputConverter.convert(content);

    }
	
	
	public Map<String, Object> returnMapStructuredResponse(String movie) {
        MapOutputConverter outputConverter = new MapOutputConverter();
        String format = outputConverter.getFormat();
        String template = """
                return all movie details {movie}.
                {format}
                """;
         
        PromptTemplate pt = new PromptTemplate("""
				Provide movie details for "{movie}".
				{format}
				"""); 
        
        Prompt prompt = pt.create(Map.of("movie", movie, "format", format));
        
        String content = chatClient
	            .prompt(prompt)
	            .call()
	            .content();

        return outputConverter.convert(content);

    }
}


