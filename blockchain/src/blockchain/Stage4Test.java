package blockchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Stage4Test {
	class Account {
		String number;
		String balance;
		public String getNumber() {
			return number;
		}
	}
	class Trans {
		Long sum;
		Account account;
		String uui;
		public Long getSum() {
			return sum;
		}
		public Account getAccount() {
			return account;
		}
	}
	
	public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator> reduceIntOperator =
	        (a, func) -> (b, c) -> {
	            int res = a;
	            for(int i = b; i <= c; i++) {
	                res = func.applyAsInt(res, i);
	            }
	        return res;
	        };
	/**
	 * The operator calculates the sum in the given range (inclusively)
	 */
	public static final IntBinaryOperator sumOperator = (a, b) -> reduceIntOperator.apply(0, (i1,i2)-> i1+i2).applyAsInt(a,b);// write your code here


	/**
	 * The operator calculates the product in the given range (inclusively)
	 */
	public static final IntBinaryOperator productOperator = (a, b) ->  reduceIntOperator.apply(1, (i1,i2)-> i1*i2).applyAsInt(a,b);
	
	
	
	 private static List<LongStream> invertedStreams(List<LongStream> streams) {
	        // write your code here
	        
	        return streams.stream().map(el -> el.isParallel() ? el.sequential() : el.parallel()).collect(Collectors.toList());
	    }
	
	public static void main(String[] args) {
		List<Trans> res = new ArrayList<Stage4Test.Trans>();
		Map<String, Long> rrr = res.stream().collect(Collectors.groupingBy(t -> t.getAccount().getNumber(), Collectors.summingLong(t -> t.getSum())));
		String[] arr = {"dfd", "fd", "dfdfr", "Hghef", "fgfs"};
		//count 10 most frequent words in the text
		 Map<String, Long> m = Arrays.stream(arr).map(el -> el.toLowerCase())
				 .collect(Collectors.groupingBy(el -> el, Collectors.counting()));
		 m.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(el -> el.getKey()))
		 .limit(10).forEach(el -> System.out.println(el.getKey()));
		String[] words = {"dfddf","Fdfd"};
		Map<Boolean, List<String>> map = Arrays.stream(words).collect(Collectors.partitioningBy(a -> {
			StringBuilder b = new StringBuilder(a);
			
			return a.equals(b.reverse().toString());
		}));
		
		int sum = IntStream.rangeClosed(10, 20).reduce(0, (acc, el) -> {
			if (el % 2 == 0 || el % 3 == 0) acc = acc + el;
			return acc;
		});
		IntFunction<IntFunction<IntFunction<Integer>>> fff = x -> y -> z -> x+y*y + z*z*z;
		List<Integer> ll = Arrays.asList(1,2,3,4,5,6);
		 UnaryOperator<List<Integer>> l =  multifunctionalMapper.apply(Arrays.asList(x -> 2*x, x -> x+1));
	}
	
	public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper = funcList -> list -> {
		List<Integer> res = new ArrayList(list);
		for(int i = 0; i < funcList.size(); i++) {
			 for(int j = 0; j < list.size(); j++) {
				 res.set(j, funcList.get(i).applyAsInt(res.get(j)));
			 }
		}
		return res;
	};
	
	public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper2 =
	        operators -> integers -> integers.stream()
	        .map(operators.stream()
	        .reduce(IntUnaryOperator.identity(), IntUnaryOperator::andThen)
	        ::applyAsInt)
	        .collect(Collectors.toList());

}
