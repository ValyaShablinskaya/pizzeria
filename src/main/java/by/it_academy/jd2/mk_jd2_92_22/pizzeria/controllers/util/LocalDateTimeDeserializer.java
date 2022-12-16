//package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util;
//
//import org.codehaus.jackson.JsonParser;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.DeserializationContext;
//import org.codehaus.jackson.map.JsonDeserializer;
//
//import java.io.IOException;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
//    @Override
//    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
//            throws IOException, JsonProcessingException {
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonParser.getLongValue()), ZoneId.of("UTC"));
//    }
//}
