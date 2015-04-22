package median;

import api.MyContext;
import api.MyReducer;
import impl.FloatWritable;
import impl.IntWritable;
import impl.StringWritable;

import java.io.IOException;
import java.util.*;

/**
 * Created by mit on 4/22/15.
 */
public class SSReducer implements MyReducer<StringWritable, IntWritable> {


    /*
    Reducer takes as input the category and a collection of bin numbers.
    It forms a frequency table for the bin numbers i.e. hashmap containing bin number as key and frequency as value.
    Using this hashmap, it computes the cumulative frequency of the bin number and updates the hashmap.
    The median will have cumulative frequency equal to half of the total number of elements.
    So the bin with cumulative frequency closest to half of the total number of elements will contain the median.
    We get the bin which contains the median. Later we use Grouped frequency distribution to find approximate value of median.
    Formula for Grouped frequency distribution: http://www.statcan.gc.ca/edu/power-pouvoir/ch11/median-mediane/5214872-eng.htm
     */

        Integer reducerCounter;
        Integer defaultValueBinSize = 10;
        Integer binSize = defaultValueBinSize;
        @Override

        public void reduce(StringWritable key, Iterator<IntWritable> values, MyContext myContext)  {


            /*calculating frequency table*/
            HashMap<Integer,Integer> freqTable = formFrequencyTable(values);

            /*sorting bin numbers to find cumulative frequency*/
            List<Integer> sortedHashMapKey = sortHashMapKey(freqTable);

            /*finding culmulative frequency*/
            int cummulativeFrequency = 0;
            for(Integer eachSortedKey : sortedHashMapKey){
                int bin_size = freqTable.get(eachSortedKey);
                cummulativeFrequency = cummulativeFrequency + bin_size;
                freqTable.put(eachSortedKey, cummulativeFrequency);
            }

            int medianIndex=cummulativeFrequency/2;
            int targetBinSize = 0;

            /*finding bin what contains the median*/
            for(Integer eachSortedKey : sortedHashMapKey) {
                if (medianIndex < freqTable.get(eachSortedKey)) {
                    targetBinSize = eachSortedKey;
                    break;
                }
            }

            /* applying Grouped freqency distribution to find approximate value of median*/
            float median = calMedianUsingGroupedFreqDistribution(freqTable, cummulativeFrequency, this.binSize, targetBinSize);

            /*collecting op*/
            try {
                myContext.write(key, new FloatWritable(median));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static float calMedianUsingGroupedFreqDistribution(HashMap<Integer, Integer> cumulativeFreq, int totalNumber, int binSize, int medianBin){

            /* Base cases if number of elements is zero or median is in first bin*/
            if (totalNumber == 0)  return 0;
            if(medianBin == 0) return binSize/2;

            float a = 0.0f;
            int i=1;
            /* find the bin, with non zero frequency, previous to medianBin*/
            while(!cumulativeFreq.containsKey(medianBin -i) && medianBin-i >=0) {
                i++;
            }
            if(medianBin-i < 0){
                return medianBin*binSize+binSize/2;
            }

            a = (float)cumulativeFreq.get(medianBin-i) / totalNumber * 100;

            float b = 50 - a;
            float c = binSize;
            float d = ((float)cumulativeFreq.get(medianBin) - cumulativeFreq.get(medianBin - i)) / totalNumber * 100;
            /*e is the approximate median*/
            float e = (b / d)*c;

            return binSize*medianBin + e;
        }

        private List<Integer> sortHashMapKey(HashMap<Integer, Integer> freqTable) {
            List<Integer> sortedList = new ArrayList<Integer>(freqTable.size());
            sortedList.addAll(freqTable.keySet());
            Collections.sort(sortedList);
            return sortedList;
        }

        private HashMap<Integer, Integer> formFrequencyTable(Iterator<IntWritable> values) {
            HashMap<Integer,Integer> freqTable = new HashMap<Integer, Integer>();
            while(values.hasNext()){
                int current_bin = values.next().getNumber();
                if(freqTable.containsKey(current_bin)){
                    freqTable.put(current_bin, freqTable.get(current_bin)+1);
                }else{
                    freqTable.put(current_bin, 1);
                }
            }
            return freqTable;
        }
    }

