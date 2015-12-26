import os
#import glob

os.system("rm -rf sonuclarTrimmed")
os.system("mkdir sonuclarTrimmed")

fileList = os.listdir("sonuclar")
#print fileList

#fileList = glob.glob("sonuclar/*.json")
#print fileList

print "Starting to trim..."
finalString = ""
for file in fileList:
	f = open("sonuclar/"+file, "r")
	str = f.read()
	str = str.replace("\"","")
	startIndex = str.find("rakamlar:")
	endIndex = str.find("rakamlarNumaraSirasi")
	str = str[startIndex+9:endIndex-1]
	str = str.replace("#", " ")
	#print str
	finalString += str + "\n"
	f.close()

f = open("sonuclarTrimmed/trimmed.txt","w")
f.write(finalString)
f.close()
print "Trim ended."

