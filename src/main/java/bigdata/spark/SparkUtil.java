package bigdata.spark;

import org.apache.spark.sql.Column;
import scala.Tuple2;
import scala.collection.JavaConverters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

public class SparkUtil {
    
    public static <E> scala.collection.Seq toScalaSeq(Iterator<E> iter) {
        
        return JavaConverters.asScalaIterator(iter).toSeq();
    }
    
    public static <E> scala.collection.Seq toScalaSeq(List<E> elements) {
        
        return toScalaSeq(elements.iterator());
    }
    
    public static <E> scala.collection.Seq toScalaSeq(E... elements) {
        
        return toScalaSeq(Arrays.asList(elements));
    }
    
    public static Column joinExprs(String... joinExprs) {
        LinkedList<Column> joinColumns = Arrays.stream(joinExprs)
                                               .map(oneJoinExpr -> oneJoinExpr.split("="))
                                               .map(joinCol -> col(joinCol[0].trim()).equalTo(col(joinCol[1].trim())))
                                               .collect(Collectors.toCollection(LinkedList::new));
        Column first = joinColumns.pop();
        
        while(!joinColumns.isEmpty()) {
            Column nextCol = joinColumns.pop();
            first.and(nextCol);
        }
        
        return first;
    }
    
    public static Column joinExprs(Tuple2<String, String>... joinExprsCols) {
      
        LinkedList<Column> joinColumns = Arrays.stream(joinExprsCols)
                                               .map(joinCol -> col(joinCol._1.trim()).equalTo(col(joinCol._2.trim())))
                                               .collect(Collectors.toCollection(LinkedList::new));
        Column first = joinColumns.pop();
        
        while(!joinColumns.isEmpty()) {
            Column nextCol = joinColumns.pop();
            first.and(nextCol);
        }
        
        return first;
    }
}
