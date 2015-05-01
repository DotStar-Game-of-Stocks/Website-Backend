# author: Yu Wu 
# update time: 2015/5/1 18:10

import re
f = open('advancedSample.txt', 'r')
""" f.read()"""
line=f.readline()
userName=line[6:]
print userName

line=f.readline()
a= re.findall(r'\d+',line)
date=a[0]
hour=a[1]
min=a[2]
print date+' '+hour+' '+min

line=f.readline()
line=f.readline()

while "END" not in line :
   
   stock = line[:4]
   print stock
   
   if 'buy' in line:
      action='buy'
   if 'sell' in line:
       action='sell'

   a= re.findall(r'\d+',line)
   amount = int(a[0])
   price = float(a[1]+'.'+a[2])
   print "amount :"+str(amount) +" @ price "+str(price)
   
   print
   
   line = f.readline()
   
   


