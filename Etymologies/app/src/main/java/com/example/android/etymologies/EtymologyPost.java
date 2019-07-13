package com.example.android.etymologies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EtymologyPost {
    @SerializedName("results")
    private ArrayList<Results> results;

    public String getResults(){
        String str = "";
        for(Results result : results)
            str = result.getLexicalEntries();
        return str;
    }

    private class Results{
        private LexicalEntries[] lexicalEntries;
//        private Object lexicalEntries;

        private String getLexicalEntries(){
//            return lexicalEntries.toString();
            String str = "";
            str = lexicalEntries[0].getEntries();
            return str;
        }
    }

    private class LexicalEntries{
        private Entries[] entries;

        private String getEntries(){
            String str = "";
            str = entries[0].getEtymologies();
            return str;
        }
    }

    private class Entries{
        private Object etymologies;

        private String getEtymologies(){
            String etymology = etymologies.toString();
            return etymology.substring(1, etymology.length()-1);
        }
    }
}
