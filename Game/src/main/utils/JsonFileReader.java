package main.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.entities.physicalEntities.MonsterSprite;

import java.io.File;
import java.io.IOException;

public class JsonFileReader {
   // private final JsonNode parent;
    private final String filePath;
    ;
    ;

    public JsonFileReader(String filePath) {
        this.filePath = filePath;
        File newFile =new File(filePath);
        //parent = new ObjectMapper().readValue(newFile);
    }



   public MonsterSprite getJSONMonster(){
       // String content = parent.path("MonsterSprite").asText();
       //System.out.println(content);
       return null;
   }

}

