import org.w3c.dom.css.Counter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Files {
    Object[][] strSentences =
        {
            {null , 0},
            {null , 0},
            {null , 0},
            {null , 0},
            {null , 0},
            {null , 0}
    };
    int SentenceChaser = 0;
    int totalnumber = 0;
    double CounterSimilarity;
    double CounterWords;
    LinkedList<String> Sentence = new LinkedList<String>();

    public void addLinkedList (String Word){
        Sentence.add(Word);
    }
    public void deleteLinkedList (){
        Sentence.removeAll(Sentence);
    }
    public void equalityOfWord (String Word){
         Iterator<String> iterator = Sentence.iterator();
        String index;
         while (iterator.hasNext()){
             index = iterator.next();
             if (index.equals(Word)){
                 SentenceChaser++;
                 CounterSimilarity++;
                 break;
             }
         }
    }
    public void Ranking (LinkedList<String> Sentence, int SentenceChaser){
        String strSentence = Sentence.toString();
        for(int i = 0; i<5; i++){
            int a = (int) strSentences[i][1];
            if (SentenceChaser > a){
                for(int j = 5; j>i; j--){
                    strSentences[j][0] = strSentences[j-1][0];
                    strSentences[j][1] = strSentences[j-1][1];
                }
                strSentences[i][0] = strSentence;
                strSentences[i][1] = SentenceChaser;
                break;
            }
        }
    }
    public Files (String mainFileStr, String scanFileStr) throws FileNotFoundException {
            File mainFile = new File(mainFileStr);
            File scanFile = new File(scanFileStr);
            String mainData;
            String scanData;
            char mainDataLastChar;
            char scanDataLastChar;
        try {
            Scanner mainFileReader = new Scanner(mainFile);
            Scanner scanFileReader = new Scanner(scanFile);

            while(mainFileReader.hasNext()){
                mainData = mainFileReader.next();
                addLinkedList(mainData);
                mainDataLastChar = mainData.charAt(mainData.length()-1);
                CounterWords++;
                if (mainDataLastChar == '.'|| mainDataLastChar == '?' || mainDataLastChar == '!') {
                    scanFileReader = new Scanner(scanFile);
                    while (scanFileReader.hasNext()) {
                        scanData = scanFileReader.next();
                        scanDataLastChar = scanData.charAt(scanData.length()-1);
                        equalityOfWord(scanData);
                            if (scanDataLastChar == '.' || scanDataLastChar == '?' || scanDataLastChar == '!') {
                                if (totalnumber < SentenceChaser){
                                    totalnumber = SentenceChaser;
                                }
                                SentenceChaser = 0;
                            }
                        }
                    if(totalnumber != 0){
                        Ranking(Sentence,totalnumber);
                        totalnumber = 0;
                    }
                    deleteLinkedList();
                    }
                }
            mainFileReader.close();
            scanFileReader.close();
            for (int i = 0; i<5; i++){
                System.out.println((i+1)+"-) "+ strSentences[i][0]);
            }
            System.out.println("Similarity Rate is " + CounterSimilarity/CounterWords*100);
        }
            catch (FileNotFoundException e) {
            System.out.println("An Error Occured");
            e.printStackTrace();
        }
    }
}