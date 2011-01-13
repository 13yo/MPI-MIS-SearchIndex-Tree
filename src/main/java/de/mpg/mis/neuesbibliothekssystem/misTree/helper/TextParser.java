package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

public class TextParser {

    public static String[] parseTextToParagraphs(String text) {
	return StringUtils.tokenizeToStringArray(text, "\n");
    }

    public static String[] parseTextToSentences(String text) {
	return StringUtils.tokenizeToStringArray(text, "[^\\.\\?!]+[\\.\\?!]");
    }

    public static String[] parseTextToWords(String text) {
	String[] temp = StringUtils.tokenizeToStringArray(text, " ");
	for (int i = 0; i < temp.length; i++) {
	    temp[i] = temp[i].replaceAll("\\p{Punct}", "");
	}
	return temp;
    }

}
