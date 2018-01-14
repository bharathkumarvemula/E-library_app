<?php
$file_name = date("U").".pdf/"; //name of your file
$server_path = "/Applications/XAMPP/xamppfiles/htdocs/Comedy"; //server path to folder
$web_path = "http://192.168.0.19/Comedy/"; //web path to folder

$file = $server_path.$file_name;
file_put_contents($file,"");

$fp = fopen("php://input", 'r');
while ($buffer =  fread($fp, 8192)) {
    file_put_contents($file,$buffer,FILE_APPEND | LOCK_SH : 0);
}

echo $web_path.$file_name;
?>
