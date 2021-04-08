package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadController {
	private String currUrl;
	public String download(String url) throws IOException {
		try {
			currUrl = url;
			final InputStream inputStream = new URL(url).openStream();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			final StringBuilder stringBuilder = new StringBuilder();
			 
			String nextLine;
			while ((nextLine = reader.readLine()) != null) {
			    stringBuilder.append(nextLine);
			    stringBuilder.append("\n");
			}
			 
			final String siteText = stringBuilder.toString();
			//Pattern p = Pattern.compile("href\\s*=\\s*\".*\"");
			Pattern p = Pattern.compile("href\\s*=\\s*\".*\"");
			Matcher m = p.matcher(siteText);
			StringBuilder b = new StringBuilder();
			int limit = 0;
			while(m.find() && limit < 10 ) {
				if(m.group().contains(".css")) continue; 
				if(m.group().contains("javascript")) continue;
				if(m.group().contains("googleapis")) continue;
				if(m.group().contains("png")) continue;
				String link = m.group().replaceAll("href\\s*=\\s*","");
				link = link.replace("\"", "").trim();
				link = createAbsolutePath(link);
				link = link.replaceAll("\\s+.*", "");
				//System.out.println(link);
				String page = visitLink(link);
				if(page == null) continue;
				String pageTitle = parseTitle(page);
				pageTitle = pageTitle.replace("<title>", "");
				if(page != null) {
					String row = "Link: " +  link + "		" + "Title: " + pageTitle;
					row = row.replaceAll("\\s+", " ");
					System.out.println("Link: " +  link + " Title: " + pageTitle);
					b.append(row).append("\n");
					limit++;
				}
			}
			return b.toString();		
			}
		catch(IOException e) {
			throw new IOException();
		}
	}
	
	
	public String visitLink(String url) {
		try {
			URLConnection con = new URL(url).openConnection();
			//System.out.println("TYPE: " + con.getContentType());
			if(con.getContentType().contains("text/html")) {
				InputStream in = con.getInputStream();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
				final StringBuilder stringBuilder = new StringBuilder();
					 
				String nextLine;
				while ((nextLine = reader.readLine()) != null) {
					stringBuilder.append(nextLine);
					stringBuilder.append("\n");
				}
				String res = parseTitle(stringBuilder.toString());
				return res;
			}
			return null;
		} catch(IOException e) {
			System.out.println("fddd");
			return "no shit";
		}
	}
	
	public String parseTitle(String page) {
		Pattern p = Pattern.compile("<title>.*<");
		Matcher m = p.matcher(page);
		String res = "";
		String test = "";
		if(m.find()) {
			System.out.println("brra " + m.group());
			//System.out.println(m.group());
			 return m.group();
		}
		return test;
		
	}
	
	private String createAbsolutePath(String link) {
		if(currUrl.charAt(currUrl.length()-1) == '/') currUrl = currUrl.substring(0, currUrl.length()-1);
		if(!link.contains("https")) {
			String abP = currUrl + link;
			return abP;
		}
		return link;
		
	}
}
