package ru.tn.nnjack.GismeteoApiService.models.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResponseDataDeserializer extends JsonDeserializer<CitiesDto.ResponseData>  {
    @Override
    public CitiesDto.ResponseData deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);
        CitiesDto.ResponseData responseData = new CitiesDto.ResponseData();

        if (node.isArray()) {
            List<CitiesDto.City> items = new ArrayList<>();
            Iterator<JsonNode> elements = node.elements();
            while (elements.hasNext()) {
                items.add(mapper.treeToValue(elements.next(), CitiesDto.City.class));
            }
            responseData.setItems(items);
        } else {
            JsonNode itemsNode = node.get("items");
            if (itemsNode != null && itemsNode.isArray()) {
                List<CitiesDto.City> items = new ArrayList<>();
                Iterator<JsonNode> elements = itemsNode.elements();
                while (elements.hasNext()) {
                    items.add(mapper.treeToValue(elements.next(), CitiesDto.City.class));
                }
                responseData.setItems(items);
            }
            responseData.setTotal(node.get("total").asInt());
        }

        return responseData;
    }
}
