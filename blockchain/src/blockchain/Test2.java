package blockchain;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Test2 {
	/**
	 * It represents a handler and has two methods: one for handling requests and other for combining handlers
	 */
	@FunctionalInterface
	interface RequestHandler {
	    public Request handle(Request req);

	    // !!! write a method handle that accept request and returns new request here
	    // it allows to use lambda expressions for creating handlers below

	    // !!! write a default method for combining this and other handler single one
	    // the order of execution may be any but you need to consider it when composing handlers
	    // the method may has any name
	    
	    default RequestHandler combine(RequestHandler h) {
	    	
	    	return req -> h.handle(this.handle(req));
	    }
	    
	    
	}

	/**
	 * Accepts a request and returns new request with data wrapped in the tag <transaction>...</transaction>
	 */
	 static RequestHandler wrapInTransactionTag =
	        (req) -> new Request(String.format("<transaction>%s</transaction>", req.getData()));
	        

	/**
	 * Accepts a request and returns a new request with calculated digest inside the tag <digest>...</digest>
	 */
	final static RequestHandler createDigest =
	        (req) -> {
	            String digest = "";
	            try {
	                final MessageDigest md5 = MessageDigest.getInstance("MD5");
	                final byte[] digestBytes = md5.digest(req.getData().getBytes("UTF-8"));
	                digest = new String(Base64.getEncoder().encode(digestBytes));
	            } catch (Exception ignored) { }
	            return new Request(req.getData() + String.format("<digest>%s</digest>", digest));
	        };

	/**
	 * Accepts a request and returns a new request with data wrapped in the tag <request>...</request>
	 */
	final static RequestHandler wrapInRequestTag =
	        (req) -> new Request(String.format("<request>%s</request>", req.getData()));

	/**
	 * It should represents a chain of responsibility combined from another handlers.
	 * The format: commonRequestHandler = handler1.setSuccessor(handler2.setSuccessor(...))
	 * The combining method setSuccessor may has another name
	 */
	//final static RequestHandler commonRequestHandler = wrapInTransactionTag

	/**
	 * Immutable class for representing requests.
	 * If you need to change the request data then create new request.
	 */
	        public static void main(String[] args) {
//	        	Request r = new Request("fdfd");
//	        	RequestHandler rr = wrapInTransactionTag.combine(createDigest).combine(wrapInRequestTag);
//	        	Request ss = rr.handle(r);
//	        	System.out.println(ss.getData());
	        	List<String> countries = Arrays.asList("Costa Rica", "Hungary", "Saint Kitts and Nevis", "Norway");
	        	List<Integer> numbers = countries.stream()
	        			.map(country -> country.split("\\s+"))
	        			        .map(something -> something.length)
	        			        .collect(Collectors.toList());
	        	for(Integer i : numbers) {
	        		System.out.println(i);
	        	}
	    
			}
	        
	        public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
	        	return IntStream.concat(evenStream, oddStream).filter(el -> el %3 == 0 && el % 5 == 0).sorted().skip(2);
	        }
	        
	        private static Stream<String> createBadWordsDetectingStream(String text, 
                    List<String> badWords) {
	        	// write your code here
	        	return Arrays.stream(text.split(" ")).filter(el -> !badWords.contains(el)).distinct().sorted().collect(Collectors.toList()).stream();
	        }
	static class Request {
	    private final String data;

	    public Request(String requestData) {
	        this.data = requestData;
	    }

	    public String getData() {
	        return data;
	    }
	}

}
