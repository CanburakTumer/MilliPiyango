from nltk.util import everygrams
from operator import itemgetter

#creating dictionaries
unigrams = {}
bigrams = {}
trigrams = {}
fourgrams = {}
fivegrams = {}
sixgrams = {}

print "Opening file..."
with open("sonuclarTrimmed/trimmed.txt") as trimmed:
	for line in trimmed:
		splitted = line.split()

#		print "Starting unigrams"
		unigramList = everygrams(splitted, max_len=1)
		for unigram in unigramList:
			try:
				gramCount = unigrams[unigram]
				unigrams[unigram] = gramCount+1
			except KeyError:
				unigrams[unigram] = 1
		sortedUnigrams = sorted(unigrams.items(), key=itemgetter(1))


#               print "Starting bigrams"
		bigramList = everygrams(splitted, max_len=2, min_len=2)
                for bigram in bigramList:
                        try:
                                gramCount = bigrams[bigram]
                                bigrams[bigram] = gramCount+1
                        except KeyError:
                                bigrams[bigram] = 1
                sortedbigrams = sorted(bigrams.items(), key=itemgetter(1))


#                print "Starting trigrams"
		trigramList = everygrams(splitted, max_len=3, min_len=3)
                for trigram in trigramList:
                        try:
                                gramCount = trigrams[trigram]
                                trigrams[trigram] = gramCount+1
                        except KeyError:
                                trigrams[trigram] = 1
                sortedtrigrams = sorted(trigrams.items(), key=itemgetter(1))


#               print "Starting fourgrams"
		fourgramList = everygrams(splitted, max_len=4, min_len=4)
                for fourgram in fourgramList:
                        try:
                                gramCount = fourgrams[fourgram]
                                fourgrams[fourgram] = gramCount+1
                        except KeyError:
                                fourgrams[fourgram] = 1
                sortedfourgrams = sorted(fourgrams.items(), key=itemgetter(1))


#               print "Starting fivegrams"
		fivegramList = everygrams(splitted, max_len=5, min_len=5)
                for fivegram in fivegramList:
                        try:
                                gramCount = fivegrams[fivegram]
                                fivegrams[fivegram] = gramCount+1
                        except KeyError:
                                fivegrams[fivegram] = 1
                sortedfivegrams = sorted(fivegrams.items(), key=itemgetter(1))


#               print "Starting sixgrams"
		sixgramList = everygrams(splitted, max_len=6, min_len=6)
                for sixgram in sixgramList:
                        try:
                                gramCount = sixgrams[sixgram]
                                sixgrams[sixgram] = gramCount+1
                        except KeyError:
                                sixgrams[sixgram] = 1
                sortedsixgrams = sorted(sixgrams.items(), key=itemgetter(1))

print "Preparing finalString"
for num in range(0,4):
        print(sortedUnigrams[num])
        print(sortedUnigrams[48-num])
        print(sortedbigrams[num])
        print(sortedbigrams[len(sortedbigrams)-1-num])
        print(sortedtrigrams[num])
        print(sortedtrigrams[len(sortedtrigrams)-1-num])
        print(sortedfourgrams[num])
        print(sortedfourgrams[len(sortedfourgrams)-1-num])
        print(sortedfivegrams[num])
        print(sortedfivegrams[len(sortedfivegrams)-1-num])                         

print "Program complete"
