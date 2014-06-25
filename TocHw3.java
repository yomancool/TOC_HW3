/*
F74006014 陳兆元 TocHw3

把url存到json的格式，在依照題目要求比對jsonarray，把答案output出來


*/


import java.net.*;
import java.io.*;

import org.json.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TocHw3 {
	public static void main(String[] args) throws IOException, JSONException {
		URL oracle = new URL(args[0]);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream(),"UTF-8"));
        
        
        JSONTokener x = new JSONTokener(in);
        JSONArray json = new JSONArray(x);
        
        //System.out.println(json.getJSONObject(3));
        int y=0,avg_price=0,count=0;
        
        while (y!=json.length())
        	{        		
 
            Pattern pattern1 = Pattern.compile(".*"+args[1]+".*");
            Matcher matcher1 = pattern1.matcher((CharSequence) json.getJSONObject(y).get("鄉鎮市區"));
            
            Pattern pattern2 = Pattern.compile(".*"+args[2]+".*");
            Matcher matcher2 = pattern2.matcher((CharSequence) json.getJSONObject(y).get("土地區段位置或建物區門牌"));
            
            Pattern pattern3 = Pattern.compile(".*"+args[3]+".*");
            Matcher matcher3 = pattern3.matcher(Integer.toString(json.getJSONObject(y).getInt("交易年月")));

            
            //System.out.println(json.get("鄉鎮市區"));
            if(matcher1.find() && matcher2.find() && matcher3.find())
            {
            	System.out.println(matcher1.group() +"   " + matcher2.group()+"   " +  matcher3.group() +"   "+json.getJSONObject(y).get("總價元"));
            	avg_price+=json.getJSONObject(y).getInt("總價元");
            	count++;
            }
            

        	y++;
        	}

          
        System.out.println(avg_price/count);
        

	 }
}



