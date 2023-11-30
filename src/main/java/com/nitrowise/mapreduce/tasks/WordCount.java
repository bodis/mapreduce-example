package com.nitrowise.mapreduce.tasks;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCount {

    /**
     * Mapper: key-value mapper másik key-value párokra (1->0, 1->1 vagy 1->N esetek)
     * esetünkben:
     * - bemeneti kulcs: lényegtelen
     * - bemeneti value: a sor
     * - kimeneti kulcs: szavak amikre feldaraboltuk
     * - kimeneti value: fix:1
     */
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    /**
     * REDUCER: ugyanazon KEY-hez tartozó összes value-t látja és ezekből készít egy másik KEY-VALUE párt
     * esetünkben:
     * - bemeneti kulcs: előforduló szavak
     * - bemeneti lista: sok 1-es érték lesz amiket a Mapper tett így bele minden szónál 1-1-t
     * - kimeneti kulcs: megegyezik a bemeneti kulccsal
     * - kimeneti érték: ahány érték jött azt mind összeadja (mivel mind 1 volt így emiatt a vége a teljes összeg lesz)
     */
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

}