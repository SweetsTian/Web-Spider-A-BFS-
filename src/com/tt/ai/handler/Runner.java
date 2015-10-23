package com.tt.ai.handler;

import java.io.IOException;

import org.jsoup.helper.Validate;

import com.tt.ai.entity.AStarSpider;
import com.tt.ai.entity.BFSSipder;
import com.tt.ai.entity.WebSpider;

public class Runner {

	public static void main(String[] args) {
		
		Validate.isTrue(args.length>=2, "Error: Parameters Error");

		String url = args[0];
		String keywords = "";
		for(int i = 1; i <args.length; i++){
			keywords+=" ";
			keywords+=args[i];
		}
		WebSpider s = new AStarSpider(url
				, keywords);
		s.getTarget();

//
//		WebSpider ws = new BFSSipder("http://www.europcar.ie/dublinairport.html"
//				, "airport car galway car");
//		
//		ws.getTarget();

	}

}
