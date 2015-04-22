package populationErrorRecords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import api.MyContext;
import api.MyReducer;

import impl.StringWritable;

public class SSReducer implements MyReducer<StringWritable, StringWritable> {


    @Override
    public void reduce(StringWritable key, Iterator<StringWritable> values, MyContext myContext) {

        StringBuilder sb = new StringBuilder();
        while (values.hasNext()) {
            String val = values.next().getString();
            String[] nfo = val.split("\t");
            try {
                if (nfo[10].equals("E")) {
                    sb.append(val + "\n");
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        try {
            myContext.write(key, new StringWritable(new String(sb)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}