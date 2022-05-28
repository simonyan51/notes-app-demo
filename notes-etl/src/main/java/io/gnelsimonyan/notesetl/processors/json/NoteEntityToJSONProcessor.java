package io.gnelsimonyan.notesetl.processors.json;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class NoteEntityToJSONProcessor implements ItemProcessor<NoteEntity, JSONObject> {
    Logger logger = LoggerFactory.getLogger(NoteEntityToJSONProcessor.class);

    @Override
    public JSONObject process(NoteEntity noteEntity) throws Exception {
        logger.info("Processing note entity {}", noteEntity);

        JSONObject jsonData = new JSONObject()
                .append("id", noteEntity.id())
                .append("title", noteEntity.title())
                .append("text", noteEntity.text())
                .append("createdAt", noteEntity.createdAt())
                .append("updatedAt", noteEntity.updatedAt());

        logger.debug("Successfully processed to json format {}", jsonData);
        return jsonData;
    }
}
