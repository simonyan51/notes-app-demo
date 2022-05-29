package io.gnelsimonyan.notesetl.processors.parquet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class NoteSchema {
    private Long id;

    private Long user_id;

    private String title;

    private String text;

    private String created_at;

    private String updated_at;
}
