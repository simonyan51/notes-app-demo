package io.gnelsimonyan.notesetl.processors.json;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.codehaus.jettison.json.JSONObject;

public class NoteEntityToJSONProcessorImpl implements NoteEntityToJSONProcessor {

    @Override
    public JSONObject process(NoteEntity noteEntity) throws Exception {

        return new JSONObject()
                .append("id", noteEntity.id())
                .append("title", noteEntity.title())
                .append("text", noteEntity.text())
                .append("createdAt", noteEntity.createdAt())
                .append("updatedAt", noteEntity.updatedAt());
    }
}
