WSGEN=/usr/lib/jvm/jdk1.8.0_161/bin/wsgen
PRJ_FOLDER=/home/vmm_admin/Lavoro/Workspaces/MATTEO/HOW-TO
J1=$PRJ_FOLDER/WS_SimpleWebApp_Web/build/classes
J2=$PRJ_FOLDER/WS_SimpleWebApp_Services/bin
J3=$PRJ_FOLDER/WS_SimpleWebApp_Dao/bin
OUTPUT_DIR=$PRJ_FOLDER/WS_SimpleWebApp_Web/src
echo WSGEN: $WSGEN
CP="$J1:$J2:$J3"
echo CLASSPATH: "$CP"
echo OUTPUT DIR: $OUTPUT_DIR
#rm -rf $OUTPUT_DIR/*
echo COMMAND: $WSGEN -classpath "$CP" it.simple.app.services.SimpleWebAppWs -d $OUTPUT_DIR -keep -s $OUTPUT_DIR
$WSGEN -classpath "$CP" it.simple.app.services.SimpleWebAppWs -d $OUTPUT_DIR -keep -s $OUTPUT_DIR
