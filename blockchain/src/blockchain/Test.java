package blockchain;
import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Test {
	
	 public static Supplier<Integer> getInfiniteRange() {
	        // write your code here
		 return new Supplier<Integer>() {
			 int range = 0;
			@Override
			public Integer get() {
				// TODO Auto-generated method stub
				return range++;
			}
		};
	    }
	 
	 public static Function<Integer, Integer> rrr(int x) {
		 return el -> el+x;
	 }
	 
	 public static <T> void findMinMax(
		        Stream<? extends T> stream,
		        Comparator<? super T> order,
		        BiConsumer<? super T, ? super T> minMaxConsumer) {
		 		List<T> sort = stream.sorted(order).collect(Collectors.toList());
		 		if(sort.size()==0) minMaxConsumer.accept(null, null);
		 		else minMaxConsumer.accept(sort.get(0), sort.get(sort.size()-1));
		 		
		}

	public static <T, U> Function<T, U> ternaryOperator(
	        Predicate<? super T> condition,
	        Function<? super T, ? extends U> ifTrue,
	        Function<? super T, ? extends U> ifFalse) {

	   return s -> {
	    	if(condition.test(s)) return ifTrue.apply(s);
	    	return ifFalse.apply(s);
	    };

	}
	
	public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
		IntPredicate res = predicates.get(0);
		for(int i  = 1; i < predicates.size(); i++) {
			res = res.or(predicates.get(i));
		}
		return res;
	}
	
    public static void main(String[] args) {
    	
//    	Function<Integer, Integer> xxx = ternaryOperator(el -> el >5, el -> 10, el -> -1);
//    	Function<Integer, Integer> qqq  = rrr(5);
//    	System.out.println(qqq.apply(7));
//    	System.out.println(xxx.apply(6));
    	Consumer<Integer> printer = System.out::println;
    	Consumer<Integer> devNull = (val) -> { int v = val * 2; };
    	 
    	Consumer<Integer> combinedConsumer = devNull.andThen(devNull.andThen(printer));
    	combinedConsumer.accept(100);
	}
    
}