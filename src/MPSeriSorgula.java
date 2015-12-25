import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class MPSeriSorgula {
	
	static HashMap <String, String> sonuclarMap;

	public static void main(String[] args) {
		sonuclarMap = new HashMap<String, String>();
		int ilkBiletNo = 1234501;
		int sonBiletNo = 1234600;
		String cekilisTarihi = "20141231"; // YYYYMMDD formatýnda
		
		//downloadCekilisSonucu(cekilisTarihi);
		mapSonuclarToHashMap(cekilisTarihi);
		String ikramiye = ikramiyeBul(ilkBiletNo, sonBiletNo);
		
		try{
			FileUtils.writeStringToFile(new File("piyango/"+cekilisTarihi+".tsv"), ikramiye);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// Bütün sonuçlarýn olduðu json dosyasýný indirir.
	private static void downloadCekilisSonucu(String cekilisTarihi){
		try {
			FileUtils.copyURLToFile(new URL("http://www.millipiyango.gov.tr/sonuclar/cekilisler/piyango/"+cekilisTarihi+".json"), new File("piyango/"+cekilisTarihi+".json"));
			System.out.println("Dosya indirme tamamlandý.");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// json dosyasýndan sonuçlarý alýp kýyas için hashmap'e atar.
	private static void mapSonuclarToHashMap(String cekilisTarihi){
		String tumSonuclar;
		try{
			tumSonuclar = FileUtils.readFileToString( new File("piyango/"+cekilisTarihi+".json"));
			JsonParser jsonParser = new JsonParser(); 
			JsonObject json = jsonParser.parse(tumSonuclar).getAsJsonObject();
			JsonArray sonuclar = (JsonArray)json.get("sonuclar");
			for(int i = 0 ; i<sonuclar.size(); i++){
				JsonObject sonuc = sonuclar.get(i).getAsJsonObject();
				String ikramiyeMiktari = sonuc.get("ikramiye").getAsString();
				JsonArray numaralar = sonuc.get("numaralar").getAsJsonArray();
				for(int j = 0; j<numaralar.size(); j++){
					sonuclarMap.put(numaralar.get(j).getAsString(), ikramiyeMiktari);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// amortiden baþlayarak yükselen þekilde bilete vuran ikramiye var mý bakar, en yüksek ikramiyeyi döner.
	private static String ikramiyeBul(int ilkBiletNo, int sonBiletNo){
		String ikramiye = "";
		String enBuyukIkramiye = "";
		
		for(int i = ilkBiletNo; i <= sonBiletNo; i++){
			String biletNo = String.valueOf(i);
			enBuyukIkramiye = "";
			for(int j = 6; j >= 0 ; j--){
				String tuttu = sonuclarMap.get(biletNo.substring(j));
				if(tuttu != null)
						enBuyukIkramiye = biletNo+"\t"+tuttu+"\n";
			}
			if(enBuyukIkramiye == "") enBuyukIkramiye = biletNo+"\0\n";
			ikramiye += enBuyukIkramiye;
		}
		
		return ikramiye;
	}

}
