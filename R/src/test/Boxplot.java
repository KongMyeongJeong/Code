package test;

import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Boxplot {
	public static byte[] getImage() throws Exception {
		RConnection r = new RConnection();
		REXP x = null;

		r.parseAndEval("library(ggplot2)");
		r.parseAndEval("setwd('D:/student/Jeong/R/tmp')");
		r.parseAndEval("cctv <- read.csv('cctv.csv',header=T)");
		r.parseAndEval("boxplot(cctv,col=c('blue','yellow','pink','red','green','black','orange'), names=c('GiGuan','SoGae','Boan','NolTu','Child','TuKi','GiTa'),horizontal=T)");

		r.parseAndEval("savePlot('cctv.png',type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('cctv.png','raw', 1024*1024)");
		//r.parseAndEval("unlink('word.png')");

		byte[] arr = x.asBytes();
		System.out.println(arr.length);

		int[] iArr = r.parseAndEval("0:9").asIntegers();
		System.out.println(Arrays.toString(iArr));

		r.close();
		return arr;
	}

}
