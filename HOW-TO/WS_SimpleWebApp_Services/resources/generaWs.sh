WSGEN=/usr/lib/jvm/jdk1.8.0_161/bin/wsgen
J1=/home/vmm_admin/Lavoro/Workspaces/MATTEO/HOW-TO/WS_SimpleWebApp_Dao/bin/
J2=/home/vmm_admin/Lavoro/Workspaces/MATTEO/HOW-TO/WS_SimpleWebApp_Services/bin/
OUTPUT_DIR=/home/vmm_admin/Lavoro/Workspaces/MATTEO/HOW-TO/WS_SimpleWebApp_Services/src/
J3=
J4=
echo $WSGEN
echo "$J1:$J2:$J3:$J4"
echo $OUTPUT_DIR
#rm -rf $OUTPUT_DIR/*
$WSGEN -classpath "$J1:$J2" it.simple.app.services.SimpleWebAppWs -d $OUTPUT_DIR -keep -s $OUTPUT_DIR
