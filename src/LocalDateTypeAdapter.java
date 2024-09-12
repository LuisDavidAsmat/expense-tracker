import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate>
{
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException
    {
        jsonWriter.value(localDate.format(DATE_TIME_FORMATTER));
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException
    {
        return LocalDate.parse(jsonReader.nextString(), DATE_TIME_FORMATTER);
    }
}
