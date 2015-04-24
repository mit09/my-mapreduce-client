package ProjFibo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import api.MyContext;
import api.MyReducer;
import impl.StringWritable;

/**
 *  Created by Vishal
 */
public class SSReducer implements
        MyReducer<StringWritable, StringWritable> {

    /**
     * calculate fibonacci number in recursive way
     * @param number Integer for which fibonacci will be calculated
     * @param al Array where the input number will be saved
     * @return
     */
    public static int fibonacciRecusion(int number, ArrayList<StringWritable> al) {
        al.add(new StringWritable(String.valueOf(number)));
        if (number == 1 || number == 2) {
            return 1;
        }

        return fibonacciRecusion(number - 1, al) + fibonacciRecusion(number - 2, al);
    }

    /**
     * Perform reducer job
     * @param key Key value
     * @param values Iterator for values of the key
     * @param context MyContext objecet where output is written
     */
    @Override
    public void reduce(StringWritable key, Iterator<StringWritable> values, MyContext context) {
        StringBuilder sb = new StringBuilder();
        ArrayList<StringWritable> al;
        int result = 0;
        int intValue;
        while (values.hasNext()) {
            String value = values.next().getString();
            al = new ArrayList();
            try {
                intValue = Integer.parseInt(value);
            }  catch (NumberFormatException num) {
                intValue = 20;
            }
                if (intValue > 50) {
                    continue;
                }
            result = fibonacciRecusion(intValue, al);

            Iterator<StringWritable> it = al.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getString())
                        .append(" ");
            }
            sb.append(String.valueOf(result) + " " + value + "\n");

        }
        try {
            context.write(key, new StringWritable(sb.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}