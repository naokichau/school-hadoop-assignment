import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ImgDupReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Text imgFilePath = null;
        for (Text filePath : values) {
            imgFilePath = filePath;
            break;// only the first one
        }
        context.write(imgFilePath, key);
    }
}