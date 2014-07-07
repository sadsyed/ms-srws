#!/bin/bash
cd /home/ubuntu/apache-tomcat-7.0.54/models
echo "Current Path: "
pwd
echo "lm text file: "
echo $1
cat $1 | /home/ubuntu/pocketsphinx/CMU-Cam_Toolkit_v2/bin/text2wfreq > temp.wfreq
cat temp.wfreq | /home/ubuntu/pocketsphinx/CMU-Cam_Toolkit_v2/bin/wfreq2vocab > training.vocab
cat $1 | /home/ubuntu/pocketsphinx/CMU-Cam_Toolkit_v2/bin/text2idngram -vocab training.vocab -temp ./tmp/ > training.idngram
/home/ubuntu/pocketsphinx/CMU-Cam_Toolkit_v2/bin/idngram2lm -vocab_type 0 -idngram training.idngram -vocab training.vocab -arpa $2.arpa