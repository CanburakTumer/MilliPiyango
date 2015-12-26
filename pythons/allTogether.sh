#python /home/pi/Desktop/sayisal/pythons/getResult.py
python /home/pi/Desktop/sayisal/pythons/trimResults.py
#python /home/pi/Desktop/sayisal/pythons/getNgrams.py > /home/pi/Desktop/sayisal/sonuclarTrimmed/bestWorst.txt
$NOW = date+"%Y%m%d"
echo $NOW
git add .
git commit -m '$NOW'
git push
