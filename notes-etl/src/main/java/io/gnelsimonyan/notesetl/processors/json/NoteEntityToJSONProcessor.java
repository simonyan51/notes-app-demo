package io.gnelsimonyan.notesetl.processors.json;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;

public interface NoteEntityToJSONProcessor extends ItemProcessor<NoteEntity, JSONObject> {
}
