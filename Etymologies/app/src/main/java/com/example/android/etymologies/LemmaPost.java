package com.example.android.etymologies;

import com.google.gson.annotations.SerializedName;

import java.security.Provider;
import java.util.ArrayList;

public class LemmaPost {


    @SerializedName("results")
    private ArrayList<Result> results;


    public String getResult(){
        String info = "";
        for (Result result : results)
            info = result.getLexicalEntries();
        return info;
    }


    private class Result{
        private ArrayList<LexicalEntries> lexicalEntries;


        private String getLexicalEntries(){
            String lex = "";
            for(LexicalEntries lexicalEntry : lexicalEntries)
                lex = lexicalEntry.getInflectionOf();
            return lex;
        }

    }

    private class LexicalEntries{
        private ArrayList<InflectionOf> inflectionOf;

        private String getInflectionOf(){
            String str = "";
            for(InflectionOf inflection : inflectionOf)
                str = inflection.getText();
            return str;
        }
    }

    private class InflectionOf{
        @SerializedName("text")
        private String text;

        private String getText(){
            return text;
        }
    }

}
