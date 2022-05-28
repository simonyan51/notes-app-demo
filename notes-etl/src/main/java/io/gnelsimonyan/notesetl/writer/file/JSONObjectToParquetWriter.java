package io.gnelsimonyan.notesetl.writer.file;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.stream.Collectors;

public class JSONObjectToParquetWriter implements ItemWriter<JSONObject> {
    @Override
    public void write(List<? extends JSONObject> list) throws Exception {
        convertToParquet(list);
    }

    private void convertToParquet(List<? extends JSONObject> list) {
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("notesConverter")
                .getOrCreate();

        List<String> dataset = list.stream()
                .map(JSONObject::toString)
                .collect(Collectors.toList());

        Dataset<String> jsonStringDataset = spark.createDataset(dataset, Encoders.STRING());

        Dataset<Row> dataFrame = spark.read().json(jsonStringDataset);

        dataFrame.printSchema();


    }
}
//        return tempFile;
    //Create the temp file path to copy converted parquet data
//    private File createTempFile() throws IOException {
//        Path tempPath = Files.createTempDirectory("");
//        File tempFile = tempPath.toFile();
//        if (tempFile != null && tempFile.exists()) {
//            String tempFilePath = tempFile.getAbsolutePath();
//            tempFile.deleteOnExit();
//            Files.deleteIfExists(tempFile.toPath());
//            log.debug("Deleted tempFile[ {} ]}", tempFilePath);
//        }
//        return tempFile;
//    }
//    //Retrieve the parquet file path
//    private File retrieveParquetFileFromPath(File tempFilePath) {
//        List<File> files = Arrays.asList(tempFilePath.listFiles());
//        return files.stream()
//                .filter(
//                        tmpFile -> tmpFile.getPath().contains(FILE_EXTENSION) && tmpFile.getPath().endsWith(FILE_EXTENSION))
//                .findAny()
//                .orElse(null);
//    }
//}
