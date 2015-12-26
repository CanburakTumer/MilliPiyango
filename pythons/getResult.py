import urllib as ll
#from datetime import datetime
#from datetime import timedelta
import datetime
import os

dateFormat = "%Y%m%d"
fileUrl = "http://www.millipiyango.gov.tr/sonuclar/cekilisler/sayisal/"
localUrl = "/home/pi/Desktop/sayisal/sonuclar/"
fileExtension = ".json"
today = datetime.datetime.now()
startDate = "19961116"
startDate = datetime.datetime.strptime(startDate, dateFormat)

#print(today)
#print(startDate)

os.system("rm -rf sonuclar")
os.system("mkdir sonuclar")
os.system("chmod -R 777 sonuclar")

print "Starting to fetch..."
while today>startDate:
	fileName = startDate.strftime(dateFormat)
#	print fileName + "started..."
	ll.urlretrieve(fileUrl+fileName+fileExtension, localUrl+fileName+fileExtension)
#	print fileName + "completed..."
	startDate = startDate + datetime.timedelta(days=7)

print "Fetch ended"
