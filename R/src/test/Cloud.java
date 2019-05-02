package test;

import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Cloud {
	public static byte[] getImage() throws Exception {
		RConnection r = new RConnection();
		REXP x = null;

		r.parseAndEval("library(wordcloud)");
		r.parseAndEval("library(KoNLP)");
		r.parseAndEval("useSejongDic()");
		r.parseAndEval("setwd('D:/student/Jeong/R/tmp')");
		r.parseAndEval("data1 <- readLines('rcrawl1.txt')");
		r.parseAndEval("data2 <- sapply(data1,extractNoun,USE.NAMES=F)");
		r.parseAndEval("data3 <- unlist(data2)");
		r.parseAndEval("data3 <- Filter(function(x){nchar(x) <= 10}, data3)");
		r.parseAndEval("head(unlist(data3),30)");
		r.parseAndEval("write(unlist(data3),'coffee.txt')");
		r.parseAndEval("data4 <- read.table('coffee.txt')");
		r.parseAndEval("nrow(data4)");
		r.parseAndEval("wordcount <- table(data4)");
		r.parseAndEval("library(RColorBrewer)");
		r.parseAndEval("palete <- brewer.pal(9,'Set3')");
		r.parseAndEval("wordcloud(names(wordcount),freq=wordcount,scale=c(5,1),rot.per=0.25,min.freq=2,random.order=F,random.color=T,colors=palete)");

		r.parseAndEval("savePlot('wordcloud.png',type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('wordcloud.png','raw', 1024*1024)");
		//r.parseAndEval("unlink('word.png')");

		byte[] arr = x.asBytes();
		System.out.println(arr.length);

		int[] iArr = r.parseAndEval("0:9").asIntegers();
		System.out.println(Arrays.toString(iArr));

		r.close();
		return arr;
	}

}
