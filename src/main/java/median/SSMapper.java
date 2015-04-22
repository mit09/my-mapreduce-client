package median;

import api.MyContext;
import api.MyMapper;
import impl.IntWritable;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;

/**
 * Created by mit on 4/22/15.
 */
/*
    Mapper class : takes as input each line from the input file and parse it.
     Outputs the category and the bin number.
    Bin number is price divided by bin size.
*/


public class SSMapper implements MyMapper<LongWritable, StringWritable> {


        int indexProductCategory = 3;
        int indexPrice = 4;

        Integer binSize = 10;
        // Integer binSize = defaultValueBinSize;

        public void map(LongWritable key, StringWritable value, MyContext myContext) {
            try {

                //            System.out.println(key.get());
                String[] mapperInputKeyValue = value.getString().split("\t");
                try {
                    myContext.write(new StringWritable(mapperInputKeyValue[indexProductCategory]), new IntWritable((int) (Float.parseFloat(mapperInputKeyValue[indexPrice]) / this.binSize)));
                } catch (NumberFormatException e) {
                    System.out.println("Ignoring input price " + mapperInputKeyValue[indexPrice] + " for the item " + mapperInputKeyValue[indexProductCategory] + " as its a non-float value.\n Line : " + value.toString() + "\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Input values less than expected for line:" + value.toString() + "\n Line : " + value.toString() + "\n\n");
            }
        }

    }

