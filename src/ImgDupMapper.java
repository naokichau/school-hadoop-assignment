import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class ImgDupMapper extends Mapper<Text, BytesWritable, Text, Text> {
    public void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
        String skeinSt;
        try {
            skeinSt = calculateSkein(value.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            context.setStatus("Internal error - can't find the algorithm for calculating the skein512");
            return;
        }
        // System.out.println(skeinSt);
        Text skeintxt = new Text(skeinSt);
        context.write(skeintxt, key);
    }

    static String calculateSkein(byte[] imageData) throws NoSuchAlgorithmException {
        byte[] hash = new byte[64];
        Skein512.hash(imageData, imageData.length * 8, hash);
        String hexStr = new String();
        for (int i = 0; i < hash.length; i++) {
            hexStr += Integer.toString((hash[i]) + 0x100, 16).substring(1);
        }
        return hexStr;
    }
}