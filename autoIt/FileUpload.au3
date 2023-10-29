#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
ControlFocus("Open","","Edit")
Sleep(5000)
ControlSetText("Open","","Edit1",$CmdLine[1])
Sleep(5000)
ControlClick("Open","","Button1")
