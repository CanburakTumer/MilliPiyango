import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.io.*;

/** 
 * 
 * @author Canburak
 * @description 11 Kasým 1996 tarihinden baþlayarak bütün Sayýsal Loto çekiliþ sonuçlarýný
 * her hafta ayrý dosyada olacak þekilde json formatýnda kaydeder.
 */

public class SayisalGetir {
	
	public static void main(String args[]){
		GregorianCalendar takvim = new GregorianCalendar(1996,10,16);
		GregorianCalendar bugun = new GregorianCalendar();
		String filename = "";
		
		while(bugun.after(takvim)){
			filename = "";
			filename += takvim.get(Calendar.YEAR);
			filename += String.valueOf(takvim.get(Calendar.MONTH)+1).length() == 1 ? "0"+(takvim.get(Calendar.MONTH)+1) : (takvim.get(Calendar.MONTH)+1);
			filename += String.valueOf(takvim.get(Calendar.DATE)).length() == 1 ? "0"+takvim.get(Calendar.DATE) : takvim.get(Calendar.DATE);
			System.out.println(filename+" started...");
			try {
				FileUtils.copyURLToFile(new URL("http://www.millipiyango.gov.tr/sonuclar/cekilisler/sayisal/"+filename+".json"), new File("sonuclar/"+filename+".json"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			takvim.add(Calendar.DATE, 7);
			System.out.println(filename+" finished...");
		}
	}

}