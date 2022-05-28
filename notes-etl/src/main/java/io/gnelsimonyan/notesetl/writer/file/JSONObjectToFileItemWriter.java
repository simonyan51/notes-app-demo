package io.gnelsimonyan.notesetl.writer.file;

import netscape.javascript.JSObject;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.util.List;

class JSONObjectToFileItemWriter extends FlatFileItemWriter<JSONObject> {

    @Override
    public void write(List<? extends JSONObject> list) throws Exception {
        System.out.println(list);
    }
}
