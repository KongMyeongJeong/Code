package test;

import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Igraph {
	public static byte[] getImage() throws Exception {
		RConnection r = new RConnection();
		REXP x = null;

		r.parseAndEval("library(DBI)");
		r.parseAndEval("library(RJDBC)");
		r.parseAndEval("library(igraph)");
		r.parseAndEval("driv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/oraclexe/app/oracle/product/11.2.0/server/jdbc/lib/ojdbc6.jar')");
		r.parseAndEval("conn <- dbConnect(driv,'jdbc:oracle:thin:@localhost:1521:xe','hr','hr')");
		r.parseAndEval("conn");
		r.parseAndEval("df <- dbGetQuery(conn,'select e2.first_name || e2.last_name temple, e1.first_name || e1.last_name boss from employees e1 right join employees e2 on e1.employee_id = e2.manager_id')");
		r.parseAndEval("class(df)");
		r.parseAndEval("g1 <- graph.data.frame(df,directed=T)");
		r.parseAndEval("library(igraph)");
		r.parseAndEval("plot(g1,layout=layout.kamada.kawai,vertex.size=2,edge.arrow.size=0.5,vertex.color='green',vertex.label=NA)");

		r.parseAndEval("savePlot('igraph.png',type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('igraph.png','raw', 1024*1024)");
		//r.parseAndEval("unlink('word.png')");

		byte[] arr = x.asBytes();
		System.out.println(arr.length);

		int[] iArr = r.parseAndEval("0:9").asIntegers();
		System.out.println(Arrays.toString(iArr));

		r.close();
		return arr;
	}

}
