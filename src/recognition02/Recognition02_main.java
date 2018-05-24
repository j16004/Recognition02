package recognition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition02_main {

	public static void main(String args[]){

		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("j16004");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("img/fruitbowl.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .imagesFile(imagesStream)
		  .imagesFilename("fruitbowl.jpg")
		  .threshold((float) 0.6)
		  .owners(Arrays.asList("IBM"))
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);


		String s = String.valueOf(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;

		try {
			node = mapper.readTree(s);

			String class_0 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").asText();
			System.out.println("class_0 : " + class_0);

			float class_0_s = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score").floatValue();
			System.out.println("class_0_s : " + class_0_s);

			String class_1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class").asText();
			System.out.println("class_1 : " +class_1);

			float class_1_s = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score").floatValue();
			System.out.println("class_1_s : " + class_1_s);

			String class_2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class").asText();
			System.out.println("class_2 : " + class_2);

			float class_2_s = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score").floatValue();
			System.out.println("class_2_s : " + class_2_s);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
