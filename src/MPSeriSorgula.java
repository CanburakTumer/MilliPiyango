import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;


public class MPSeriSorgula {
	
	static HashMap <String, String> sonuclar;

	public static void main(String[] args) {
		sonuclar = new HashMap<String, String>();
		int ilkBiletNo = 1234500;
		int sonBiletNo = 1234599;
		String cekilisTarihi = "20141231"; // YYYYMMDD format�nda
		
		downloadCekilisSonucu(cekilisTarihi);
		mapSonuclarToHashMap(cekilisTarihi);
		String ikramiye = ikramiyeBul(ilkBiletNo, sonBiletNo);
	}
	
	// B�t�n sonu�lar�n oldu�u json dosyas�n� indirir.
	private static void downloadCekilisSonucu(String cekilisTarihi){
		try {
			FileUtils.copyURLToFile(new URL("http://www.millipiyango.gov.tr/sonuclar/cekilisler/piyango/"+cekilisTarihi+".json"), new File("piyango/"+cekilisTarihi+".json"));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// json dosyas�ndan sonu�lar� al�p k�yas i�in hashmap'e atar.
	private static void mapSonuclarToHashMap(String cekilisTarihi){
		String tumSonuclar;
		try{
			tumSonuclar = FileUtils.readFileToString( new File("piyango/"+cekilisTarihi+".json"));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static String ikramiyeBul(int ilkBiletNo, int sonBiletNo){
		String ikramiye = "";
		String enBuyukIkramiye = "";
		
		for(int i = ilkBiletNo; i <= sonBiletNo; i++){
			String biletNo = String.valueOf(i);
			for(int j = 6; j >= 0 ; j--){
				System.out.println(biletNo.substring(j));
				String tuttu = sonuclar.get(biletNo.substring(j));
				if(tuttu != null)
						enBuyukIkramiye += biletNo+","+tuttu+"\n";
			}
		}
		
		return ikramiye;
	}

}
