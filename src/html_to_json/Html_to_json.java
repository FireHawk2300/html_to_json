/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html_to_json;

import java.io.IOException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author FireHawk2300
 */
public class Html_to_json {

    public static void main(String[] args) throws IOException {
        
        //JSOUP lib connects to URL and gets HTML        
        String html = Jsoup.connect("https://github.com/egis/handbook/blob/master/Cultural-Manifesto.md").get().html();
        
        //Parse retrieved html to JSOUP for interpretation
        Document doc = Jsoup.parse(html);
        
        //Interrogates <table> tag in html
        Element table = doc.select("table").first();
        
        //Interrogates <tbody> tag in html
        Element tBody = doc.select("tbody").first();
        
        //Gets all table headers <th> from <table>
        Elements tHead = table.getElementsByTag("th");
        
        //Gets all rows <tr> from <tbody>
        Elements row = tBody.getElementsByTag("tr");
        
        //Loop through each header, so that it prints each row on a new line
        for (int i = 0, l = tHead.size(); i < l; i++) {            
            //re-initialise for each object (header) - so we have unique (rows)
            JSONObject jsonParentObject = new JSONObject();
            
            for (int j = 0, k = row.size(); j < k; j++) {                
                //re-initialise contents for each row
                JSONObject jsonObject = new JSONObject();                
                
                //cloumn contents retrieved for each row
                Elements cols = row.eq(i).select("td");
                String capability = cols.get(0).text();
                String junior = cols.get(1).text();
                String dev = cols.get(2).text();
                String senior = cols.get(3).text();
                String fullStack = cols.get(4).text();
                String teamLead = cols.get(5).text();
                String architect = cols.get(6).text();        
                jsonObject.put("Junior", junior);
                jsonObject.put("Dev", dev);
                jsonObject.put("Senior", senior);
                jsonObject.put("Full Stack", fullStack);
                jsonObject.put("Team Lead", teamLead);
                jsonObject.put("Architect", architect);
                jsonParentObject.put(capability,jsonObject);
             }
            
            System.out.println(jsonParentObject.toString());
        }
    }
    
}
